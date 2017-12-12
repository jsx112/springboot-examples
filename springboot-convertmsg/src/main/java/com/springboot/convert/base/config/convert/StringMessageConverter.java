package com.springboot.convert.base.config.convert;/**
 * Created by dell on 2017/11/16.
 */

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.StringHttpMessageConverter;

import java.io.IOException;

/**
 * 字符串转换器
 *
 * @author jiasx
 * @create 2017-11-16 19:38
 **/
public class StringMessageConverter extends StringHttpMessageConverter {

    @Override
    protected void writeInternal(String str, HttpOutputMessage outputMessage) throws IOException {
        super.writeInternal(str, outputMessage);
    }

    @Override
    protected String readInternal(Class<? extends String> clazz, HttpInputMessage inputMessage) throws IOException {
        return super.readInternal(clazz, inputMessage);
    }
}
