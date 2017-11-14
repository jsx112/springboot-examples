package com.springboot.mongodb.base.config.convert;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import com.springboot.mongodb.base.entity.ResEntity;
import com.springboot.mongodb.base.enums.CodeMsg;
import com.springboot.mongodb.base.util.UserReqContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpOutputMessage;
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
