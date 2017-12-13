package com.springboot.jpa.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 工具类
 */
public class Utils {
    private static Logger logger = LoggerFactory.getLogger(Utils.class);
    public static ThreadLocal<DateFormat> dateFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    public static ThreadLocal<DateFormat> dateFormatStr = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMddHHmmss"));
    public static ThreadLocal<DateFormat> dateFormatYMD = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    public static final String DATE_FORMAT_DAY = "yyyy-MM-dd";
    public static final String DATE_FORMAT_HOUR = "yyyy-MM-dd HH";
    public static final String DATE_FORMAT_MINUTE = "yyyy-MM-dd HH:mm";

    public static final long DATE_Milli_MINUTE = 1000 * 60;
    public static final long DATE_Milli_HOUR = 1000 * 60 * 60;
    public static final long DATE_Milli_DAY = 1000 * 60 * 60 * 24;

    public static boolean isJavaClass(Class<?> clz) {
        return clz != null && clz.getClassLoader() == null;
    }

    /**
     * 格式化日期时间
     *
     * @param date    java.util.Date对象
     * @return 格式化后的时间字符串
     */
    public static String formatDate(Date date) {
        String dateStr = "";
        try {
            dateStr = dateFormat.get().format(date);
        } catch (Exception e) {
            return dateStr;
        }
        return dateStr;
    }

    /**
     * 格式化日期时间为yyyyMMddHHmmss
     *
     * @param date    java.util.Date对象
     * @return 格式化后的时间字符串
     */
    public static String formatDateStr(Date date) {
        String dateStr = "";
        try {
            dateStr = dateFormatStr.get().format(date);
        } catch (Exception e) {
            return dateStr;
        }
        return dateStr;
    }

    /**
     * 格式化日期时间
     * @param dateStr
     * @return 格式化后的时间
     */
    public static Date formatDate(String dateStr) {
        try {
            if (isDate(dateStr)) {
                return dateFormat.get().parse(dateStr);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    /**
     * 格式化日期时间
     * @param dateStr
     * @return 格式化后的时间
     */
    public static Date formatDate(String dateStr,DateFormat dateFormat) {
        try {
            if (isDate(dateStr,dateFormat)) {
                return dateFormat.parse(dateStr);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    /**
     * 格式化日期时间
     * @param date
     * @return 格式化后的时间
     */
    public static String formatDateStr(Date date,DateFormat dateFormat) {
        try {
            return dateFormat.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 判断字符串是不是日期
     * @param dateStr
     * @return
     */
    public static boolean isDate(String dateStr) {
        try {
            if (!StringUtil.isNull(dateStr)) {
                dateFormat.get().parse(dateStr);
                return true;
            }
        } catch (ParseException e) {
            return false;
        }
        return false;
    }

    /**
     * 判断是否是日期【指定格式】
     * @param dateStr
     * @param dateFormat
     * @return
     */
    public static boolean isDate(String dateStr,DateFormat dateFormat) {
        try {
            if (!StringUtil.isNull(dateStr)) {
                dateFormat.parse(dateStr);
                return true;
            }
        } catch (ParseException e) {
            return false;
        }
        return false;
    }

    /**
     * 取得当月1号00:00:00
     * @return
     */
    public static Date getCurrentMonthFirstDay(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, c.get(Calendar.MONTH));
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 取得下月1号00:00:00
     * @return
     */
    public static Date getNextMonthFirstDay(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 取得倒退一个月的日期
     * @return
     */
    public static String getUpMonthFirstDay(){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(c.getTime());
        return time;
    }

    /**
     * 深拷贝POJO集合
     * @param srcList
     * @param <T>
     * @return
     */
    public static <T> List<T> deepCopyList(List<T> srcList)
    {
        List<T> dest = null;
        try
        {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(srcList);
            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);
            dest = (List<T>) in.readObject();
        }catch (IOException e){

        }catch (ClassNotFoundException e){

        }
        return dest;
    }

    /**
     * 获取年,月,日数组 [0-year  1-month  2-day]
     */
    public static String[] getYearMonthDay(Date date) {
        String[] rtn = new String[3];
        try {
            String dateStr = formatDateStr(date, dateFormatYMD.get());
            if (dateStr != null && !dateStr.isEmpty()) {
                rtn = dateStr.split("-");
            }
        } catch (Exception e) {

        }
        return rtn;
    }

    /**
     * 获取传入时间对应格式间隔时间的时间字符串
     *
     * @param date       当前时间
     * @param dateFormat 格式化后的时间
     * @param TimeMillis 格式化时间后需要间隔的时间毫秒值
     * @return 返回格式化后间隔时间的时间字符串, 格式yyyy-MM-dd HH:mm:ss
     * @throws Exception
     */
    public static Date getIntervalDate(Date date, String dateFormat, Long TimeMillis){
        long l;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
            l = simpleDateFormat.parse(simpleDateFormat.format(date)).getTime() + (TimeMillis == null ? 0 :
                    TimeMillis);
        } catch (Exception e) {
            logger.error("时间转换发生异常" + e.getMessage());
            return null;
        }
        return new Date(l);
    }


    public static <T> List<T> json2ObjList(String json, Class<T> t) {
        List<T> objectList = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectList = objectMapper.readValue(json, new TypeReference<List<T>>() {});
        } catch (IOException e) {

        }
        return objectList;
    }

    public static String getSha1(String str){
        if (null == str || 0 == str.length()){
            return null;
        }
        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 求a/b的值【保留两位小数】
     * @param a  分子
     * @param b  分母
     * @return
     */
    public static BigDecimal percent(Integer a, Integer b) {
        if (a == null || b == null || b.equals(0)) {
            return BigDecimal.valueOf(0);
        }
        return BigDecimal.valueOf(a).divide(BigDecimal.valueOf(b), 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
    }


}
