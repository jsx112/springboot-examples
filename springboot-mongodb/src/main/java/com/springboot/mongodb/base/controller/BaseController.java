package com.springboot.mongodb.base.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.springboot.mongodb.base.config.convert.DateEditor;
import com.springboot.mongodb.user.entity.UserInfo;
import com.springboot.mongodb.base.util.UserReqContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;


/**
 * Controller基类，适用当前应用场景：前后端分离，集成SSO，前端请求参数为JSON字符串形式（requestbody）
 */
public class BaseController {

    public static final String SUCCESS = "success";

    @Autowired
    protected HttpServletRequest request;
    
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

}
