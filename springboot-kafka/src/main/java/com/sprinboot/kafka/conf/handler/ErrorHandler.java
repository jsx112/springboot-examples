package com.sprinboot.kafka.conf.handler;/**
 * Created by dell on 2017/11/22.
 */

import com.sprinboot.kafka.base.entity.CodeMsg;
import com.sprinboot.kafka.base.entity.ResEntity;
import com.sprinboot.kafka.base.exception.BizException;
import com.sprinboot.kafka.util.JacksonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 异常处理
 *
 * @author jiasx
 * @create 2017-11-22 18:05
 **/
@ControllerAdvice
public class ErrorHandler {
    private Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    /**
     * 统一异常处理
     *
     * @param exception exception
     * @return
     */
    @ExceptionHandler({ Exception.class })
    public Object jsonErrorHandler(HttpServletResponse response, Exception exception) {
        ResEntity<String> resEntity = new ResEntity<>();
        CodeMsg exceptionCode = null;
        if (exception instanceof BizException) {
            BizException bizExcepton = (BizException)exception;
            exceptionCode = bizExcepton.getCodeMsg();
        } else {
            exceptionCode = CodeMsg.system_error;
            logger.error("系统异常：",exception);
        }
        resEntity.setCode(exceptionCode.getCode());
        resEntity.setMsg(exceptionCode.getMsg());
        resEntity.setData(null);
        try {
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(JacksonUtil.toJson(resEntity));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            return new BizException(CodeMsg.system_error);
        }
        return null;
    }

}
