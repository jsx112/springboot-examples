package com.springboot.mongodb.base.util;/**
 * Created by dell on 2017/11/13.
 */

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 各种工具汇总
 *
 * @author jiasx
 * @create 2017-11-13 14:49
 **/
public class Utils {


    /**
     * 获取异常信息的字符串描述
     * @param e
     * @return
     */
    public static String getExceptionStr(Exception e) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        String s = stringWriter.toString();
        String separator = "\r\n\t";
        int i = s.indexOf(separator);
        StringBuffer errCauseBuf = new StringBuffer("错误：").append(s.substring(0, i)).append(", ").append(s.substring(i + 3, s.indexOf(separator, i + 6)));
        int j;
        String cause = "Caused by:";
        if ((j = s.lastIndexOf(cause)) != -1){
            errCauseBuf.append("；").append(s.substring(j, s.indexOf(separator, j + 10)));
        }

        return errCauseBuf.toString();

    }
}
