package com.springboot.quartz.exception;

import com.springboot.quartz.entry.CODE;
import com.springboot.quartz.entry.ResponseT;
import com.springboot.quartz.util.JacksonUtil;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 统一异常处理类 【均适用于controller，service，dao层】
 * @author renzh on 2017/5/8
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     *
     * @param response
     * @param exception
     * @param handler
     * @return
     */
    @ExceptionHandler       //处理所有异常
    public Object exceptionHandler(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
        if (handler == null) {
            return null;
        }

        logger.info("全局异常处理：begin...");
        logger.error(getErrorInfo(exception)); //打印错误堆栈信息

        //封装异常信息
        BizException bizException;
        if (exception instanceof BizException) {    //业务异常
            bizException = (BizException) exception;
        }else {                                     //非业务异常，统一处理成系统异常
            if(exception instanceof NoHandlerFoundException){//接口url错误打印，方便调试
                logger.error("111111");
            }
            if(exception instanceof AuthorizationException){
                bizException = new BizException(CODE.user_no_permission);
            }else{
                bizException = new BizException(CODE.system_error);
            }
        }

        //响应数据
        ResponseT responseT = new ResponseT(bizException);
        String errorJsonStr = JacksonUtil.toJson(responseT);//最终响应数据【暂时支持ajax请求】
        logger.error(errorJsonStr);

        try {
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(errorJsonStr);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            return new BizException(CODE.system_error);
        }
        return null;
    }

    public static String getErrorInfo(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);// 将出错的栈信息输出到printWriter中
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }

}