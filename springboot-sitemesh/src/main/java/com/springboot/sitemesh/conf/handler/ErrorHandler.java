package com.springboot.sitemesh.conf.handler;/**
 * Created by dell on 2017/11/22.
 */

import com.springboot.sitemesh.base.entity.CodeMsg;
import com.springboot.sitemesh.base.entity.ResEntity;
import com.springboot.sitemesh.base.exception.BizException;
import com.springboot.sitemesh.core.controller.SitemeshController;
import com.springboot.sitemesh.util.JacksonUtil;
import com.springboot.sitemesh.util.Toolkit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

///**
// * 异常处理
// *
// * @author jiasx
// * @create 2017-11-22 18:05
// **/
//@ControllerAdvice
//public class ErrorHandler {
//    private Logger logger = LoggerFactory.getLogger(SitemeshController.class);
//
//    /**
//     * 统一异常处理
//     *
//     * @param exception exception
//     * @return
//     */
//    @ExceptionHandler({ Exception.class })
//    @ResponseStatus(HttpStatus.OK)
//    public Object jsonErrorHandler(HttpServletResponse response,Exception exception) {
//        logger.error("异常：",exception);
//        ResEntity<String> resEntity = new ResEntity<>();
//        CodeMsg exceptionCode = null;
//        if (exception instanceof BizException) {
//            BizException bizExcepton = (BizException)exception;
//            exceptionCode = bizExcepton.getCodeMsg();
//        } else {
//            exceptionCode = CodeMsg.system_error;
//        }
//        resEntity.setCode(exceptionCode.getCode());
//        resEntity.setMsg(Toolkit.getExceptionStr(exception));
//        resEntity.setData(exceptionCode.getMsg());
//        try {
//            response.setContentType("application/json;charset=UTF-8");
//            PrintWriter writer = response.getWriter();
//            writer.write(JacksonUtil.toJson(resEntity));
//            writer.flush();
//            writer.close();
//        } catch (IOException e) {
//            return new BizException(CodeMsg.system_error);
//        }
//        return null;
//    }
//}
