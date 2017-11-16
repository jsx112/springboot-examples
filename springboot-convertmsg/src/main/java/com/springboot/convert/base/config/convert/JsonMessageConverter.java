package com.springboot.convert.base.config.convert;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.JavaType;
import com.springboot.convert.base.entity.ResEntity;
import com.springboot.convert.base.util.UserReqContextUtil;
import com.springboot.convert.base.enums.CodeMsg;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
* 消息转换器
* @author jiasx
* @create 2017/11/13 14:44
**/
public class JsonMessageConverter extends MappingJackson2HttpMessageConverter {

    @Override
    protected void init(ObjectMapper objectMapper) {
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));    //日期统一格式化
        super.init(objectMapper);
    }

    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        if("java.lang.String".equals(((Class) type).getName())){
           return inputStream2String(inputMessage.getBody());
        }
        return super.read(type, contextClass, inputMessage);
    }

    public   static   String   inputStream2String(InputStream is)   throws   IOException{
        ByteArrayOutputStream baos   =   new   ByteArrayOutputStream();
        int   i=-1;
        while((i=is.read())!=-1){
            baos.write(i);
        }
        return   baos.toString();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
    	ResEntity resEntity = new ResEntity();
    	if (object instanceof ResEntity ) {  
    		resEntity = (ResEntity)object;
        }else{
        	resEntity.setCode(CodeMsg.success.getCode());
    		resEntity.setPath(UserReqContextUtil.getRequestUri());
    		resEntity.setData(object);
        }
        super.writeInternal(resEntity, type, outputMessage);
    }
}
