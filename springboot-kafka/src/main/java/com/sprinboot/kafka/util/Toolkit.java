package com.sprinboot.kafka.util;/**
 * Created by dell on 2017/11/13.
 */

import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Encoder;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * 各种工具汇总
 *
 * @author jiasx
 * @create 2017-11-13 14:49
 **/
public class Toolkit {


    /**
     * 密码加密格式  [password+salt]
     */
    private static final String ENCRYPT_FORMAT = "%s%s";


    /**
     * 生成Token
     */
    public static String makeToken() {
        String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + "";
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] md5 = md.digest(token.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(md5);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    public static String getSha1(String str) {
        if (null == str || 0 == str.length()) {
            return null;
        }
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
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
     * 加密算法    【算法：sha1(md5(password)+salt)】   每个用户一个salt【salt=loginid】
     *
     * @param passwordByMd5 MD5加密结果
     * @param salt          盐
     * @return
     */
    public static String encrypt(String passwordByMd5, String salt) {
        if (StringUtils.isEmpty(passwordByMd5) || StringUtils.isEmpty(salt)) {
            return null;
        }
        return getSha1(String.format(ENCRYPT_FORMAT, passwordByMd5, salt));
    }


    /**
     * 生成随机盐
     **/
    public static String generateSalt() {
        Random random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return new BASE64Encoder().encode(salt);
    }


    /**
     * 获取异常信息的字符串描述
     *
     * @param e
     * @return
     */
    public static String getExceptionStr(Exception e) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        String s = stringWriter.toString();
        String separator = "\r\n\t";
        if (!isWinOs()) {
            separator = "\n\t";
        }
        int i = s.indexOf(separator);
        StringBuffer errCauseBuf = new StringBuffer("错误：").append(s.substring(0, i)).append(", ").append(s.substring(i + 3, s.indexOf(separator, i + 6)));
        int j;
        String cause = "Caused by:";
        if ((j = s.lastIndexOf(cause)) != -1) {
            errCauseBuf.append("；").append(s.substring(j, s.indexOf(separator, j + 10)));
        }

        return errCauseBuf.toString();
    }


    /**
     * 判断是否是合法的地址
     *
     * @param url
     * @return
     */
    public static boolean isEffectiveUrl(String url) {
        boolean isEffectiveUrl = false;

        String regex = "^(http|ftp|https):\\/\\/[\\w\\-]+(\\.[\\w\\-]+)+([\\w\\.\\(\\)\\-\\*,@?^=%&:/~\\+#\\u4e00-\\u9fa5]*[\\w\\.\\(\\)\\-\\*,@?^=%&:/~\\+#\\u4e00-\\u9fa5])+$";
//        String regex = "^(http|ftp|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&:/~\\+#]*[\\w\\-\\@?^=%&/~\\+#])+$";
        Pattern pattern = Pattern.compile(regex);
        if (pattern.matcher(url).matches()) {
            isEffectiveUrl = true;
        }
        return isEffectiveUrl;
    }

    public static void main(String[] args) {
        String url = "https://redis.io/commands/incr?xx=啥地方";
        System.out.println(isEffectiveUrl(url));
    }


    /**
     * 判断操作系统是win还是linux
     *
     * @return
     */
    public static boolean isWinOs() {
        boolean isWinOs = false;
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            isWinOs = true;
        }
        return isWinOs;
    }
}
