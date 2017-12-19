package com.sprinboot.kafka.util;

import com.sprinboot.kafka.base.entity.CodeMsg;
import com.sprinboot.kafka.base.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
* 短链接生成算法工具类
* @author jiasx
* @create 2017/12/1 15:11
**/
public class ShortUrlUtil {


    private static Logger logger = LoggerFactory.getLogger(ShortUrlUtil.class);

    private static final Long maxSupportNum = 965660736L;

    /**
     * 要使用生成短链接的字符集，更改了一些顺序
     */
    private static final String[] CHARS = new String[] { "a" , "c" , "b" , "V" , "2" , "f" , "g" , "h" ,
            "i" , "j" , "k" , "l" , "m" , "n" , "o" , "p" , "q" , "r" , "s" , "t" ,
            "u" , "v" , "w" , "X" , "y" , "z" , "0" , "1" , "3" , "3" , "4" , "5" ,
            "6" , "7" , "8" , "9" , "A" , "B" , "C" , "D" , "E" , "F" , "G" , "H" ,
            "I" , "J" , "K" , "L" , "M" , "N" , "O" , "P" , "Q" , "R" , "S" , "T" ,
            "U" , "d" , "W" , "x" , "Y" , "Z"

    };

    /**
     * 短链生成算法
     * 根据数字生成对应的6位字符
     *
     * @param incrNum
     * @return
     */
    public static String shortUrl(Long incrNum) {
        if(incrNum>maxSupportNum){
            logger.error("短链接生成数太多了，已经超过了"+maxSupportNum);
            throw new BizException(CodeMsg.business_error);
        }
        StringBuilder sBuilder = new StringBuilder();
        while (incrNum>0) {
            int remainder = (int) (incrNum % 62);
            sBuilder.append(CHARS[remainder]);
            incrNum = incrNum / 62;
        }
        return sBuilder.toString();

    }

    /**
     * 短链生成算法，暂时不用，重复率太高，大约7000左右就会碰撞
     *
     * @param url 长链
     * @return
     */
    public static String shortUrl(String url) {
        return shortUrlMd5(url)[2];
    }

    public static String[] shortUrlMd5(String url) {
        // 可以自定义生成 MD5 加密字符传前的混合 KEY
        String key = "mykey" ;
        // 对传入网址进行 MD5 加密
        String sMD5EncryptResult = Md5Util.md5(key + url);
        String hex = sMD5EncryptResult;

        String[] resUrl = new String[4];
        for ( int i = 0; i < 4; i++) {

            // 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
            String sTempSubString = hex.substring(i * 8, i * 8 + 8);

            // 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用 long ，则会越界
            long lHexLong = 0x3FFFFFFF & Long.parseLong (sTempSubString, 16);
            String outChars = "" ;
            for ( int j = 0; j < 6; j++) {
                // 把得到的值与 0x0000003D 进行位与运算，取得字符数组 CHARS 索引
                long index = 0x0000003D & lHexLong;
                // 把取得的字符相加
                outChars += CHARS[( int ) index];
                // 每次循环按位右移 5 位
                lHexLong = lHexLong >> 5;
            }
            // 把字符串存入对应索引的输出数组
            resUrl[i] = outChars;
        }
        return resUrl;
    }

    /**
     * md5加密方法
     *
     * @param key
     * @return
     */
    public static String getMD5Encrypted(String key) {
        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(key.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把没一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }
            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * 解密base64字符串
     * @param str
     * @return
     */
    public static String getDecodeBase64Str(String str) {
        if (str == null) {
            return "";
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(str);
            return new String(b,"utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 中文参数进行URLEncoder编码
     * @author jiasx
     * @create 2017/11/24 9:16
     **/
    public static String decodeUrlParams(String sourceUrl) throws UnsupportedEncodingException {
        String[] sourceUrlArr = sourceUrl.split("\\?");
        if(sourceUrlArr.length>=1) {
            StringBuffer urlParams = new StringBuffer(sourceUrlArr[0]);
            if (sourceUrlArr.length == 2) {
                String[] paramKeyValues = sourceUrlArr[1].split("&");
                for (String paramKeyValue : paramKeyValues) {
                    String[] keyvalue = paramKeyValue.split("=");
                    if (keyvalue.length == 2) {
                        if (urlParams.indexOf("?") != -1) {
                            urlParams.append("&").append(keyvalue[0]).append("=").append(URLEncoder.encode(keyvalue[1], "utf-8"));
                        } else {
                            urlParams.append("?").append(keyvalue[0]).append("=").append(URLEncoder.encode(keyvalue[1], "utf-8"));
                        }
                    }
                }
            }
            return urlParams.toString();
        }
        return sourceUrl;
    }

}
