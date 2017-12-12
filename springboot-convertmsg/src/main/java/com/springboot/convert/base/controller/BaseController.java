package com.springboot.convert.base.controller;

import com.springboot.convert.base.config.convert.DateEditor;
import com.springboot.convert.base.entity.ReqEntity;
import com.springboot.convert.base.entity.UserInfo;
import com.springboot.convert.base.enums.CodeMsg;
import com.springboot.convert.base.exception.BizException;
import com.springboot.convert.base.util.UserReqContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
 * Controller基类，适用当前应用场景：前后端分离，集成SSO，前端请求参数为JSON字符串形式（requestbody）
 */
public class BaseController<T> {

    public static final String SUCCESS = "success";

    private Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 表单日期提交，支持多种格式
     * @param webDataBinder
     */
	@InitBinder
	protected void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		webDataBinder.registerCustomEditor(Date.class, new DateEditor(true));
	}
	
    /**
     * 得到当前请求用户的基本信息
     */
    public UserInfo getCurrentUser() {
    	logger.info("当前用户信息："+ UserReqContextUtil.getLoginUserInfo());
        return UserReqContextUtil.getLoginUserInfo();
    }

    /**
     * 得到当前请求用户的id
     */
    public Long getCurrentUserId() {
        return UserReqContextUtil.getLoginUserId();
    }

    /**
     * 处理用户token和请求参数
     */
    @SuppressWarnings("rawtypes")
    @ModelAttribute
    public void getToken(@RequestBody ReqEntity<T> reqEntity, HttpServletRequest request) {
        if(StringUtils.isEmpty(reqEntity.getToken())){
            throw new BizException(CodeMsg.token_not_blank);
        }
        UserReqContextUtil.UserReqContext userReqContext = new UserReqContextUtil.UserReqContext();
        userReqContext.setUserInfo(new UserInfo());
        userReqContext.setToken(reqEntity.getToken());
        userReqContext.setRequestUri(request.getRequestURI());
        userReqContext.setRequestBody(reqEntity.getBody());
        UserReqContextUtil.set(userReqContext);
    }

}
