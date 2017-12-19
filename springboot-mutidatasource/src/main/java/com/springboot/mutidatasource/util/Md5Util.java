package com.springboot.mutidatasource.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 * @author renzh 2017/5/16.
 */
public class Md5Util {
    private static final String	MD5_ENCODEING	= "utf-8";

    private static final char[]	DIGITS			= { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    private static String getMD5ofStr(byte[] digest){
        StringBuilder digestHexStr = new StringBuilder();
        for (int i = 0; i < 16; i++){
            digestHexStr.append(byteHEX(digest[i]));
        }
        return digestHexStr.toString();
    }

    private static String byteHEX(byte ib){
        char[] ob = new char[2];
        ob[0] = DIGITS[(ib >>> 4) & 0X0F];
        ob[1] = DIGITS[ib & 0X0F];
        return new String(ob);
    }

    /**
     * 对字符串进行MD5加密
     *
     * @param text
     *            明文
     * @return 密文
     */
    public static String md5(String text){
        MessageDigest msgDigest = null;
        try{
            msgDigest = MessageDigest.getInstance("MD5");
        }catch (NoSuchAlgorithmException e){
            throw new IllegalStateException("System doesn't support MD5 algorithm.");
        }
        try{
            msgDigest.update(text.getBytes(MD5_ENCODEING));
        }catch (UnsupportedEncodingException e){
            throw new IllegalStateException("System doesn't support your  EncodingException.");
        }
        return getMD5ofStr(msgDigest.digest());
    }

}
