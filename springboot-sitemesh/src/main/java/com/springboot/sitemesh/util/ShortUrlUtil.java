package com.springboot.sitemesh.util;

import sun.misc.BASE64Decoder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ShortUrlUtil {

    /**
     * 短链生成算法
     *
     * @param url 长链
     * @return
     */
    public static String shortUrl(String url) {
        // 要使用生成 URL 的字符
        String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
                "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"

        };
        // 对传入网址进行 MD5 加密
        String sMD5EncryptResult = getMD5Encrypted(url);
        String hex = sMD5EncryptResult;

        // 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
        String sTempSubString = hex.substring(1 * 8, 1 * 8 + 8);

        // 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用 long ，则会越界
        long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
        String outChars = "";
        for (int j = 0; j < 6; j++) {
            // 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
            long index = 0x0000003D & lHexLong;
            // 把取得的字符相加
            outChars += chars[(int) index];
            // 每次循环按位右移 5 位
            lHexLong = lHexLong >> 5;
        }

        return outChars;
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

    public static void main(String[] args) {
        System.out.println(shortUrl("http://blog.csdn.net/yushouling/article/details/550969923"));
    }

}
