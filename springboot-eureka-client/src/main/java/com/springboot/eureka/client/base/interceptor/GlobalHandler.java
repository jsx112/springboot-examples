package com.springboot.eureka.client.base.interceptor;

import com.springboot.eureka.client.base.entity.ResEntity;
import com.springboot.eureka.client.base.enums.CodeMsg;
import com.springboot.eureka.client.base.exception.BizException;
import com.springboot.eureka.client.base.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
* 异常信息处理控制器
* @author jiasx
* @create 2017/11/13 15:22
**/
@ControllerAdvice
public class GlobalHandler {
	private Logger logger = LoggerFactory.getLogger(GlobalHandler.class);
	
    /**
     * 处理异常
     */
    @ExceptionHandler
    @ResponseBody
    public ResEntity<String> handleException(HttpServletRequest request, Exception e) {
        logger.error("操作异常：",e);
    	ResEntity<String> resEntity = new ResEntity<String>();
    	resEntity.setPath(request.getRequestURI());
        CodeMsg exceptionCode = null;
        if (e instanceof BizException) {
        	BizException bizExcepton = (BizException)e;
        	exceptionCode = bizExcepton.getCodeMsg();
        } else {
        	exceptionCode = CodeMsg.system_error;
        }
        resEntity.setCode(exceptionCode.getCode());
        resEntity.setMsg(Utils.getExceptionStr(e));
        resEntity.setData(exceptionCode.getMsg());
        return resEntity;
    }
}
