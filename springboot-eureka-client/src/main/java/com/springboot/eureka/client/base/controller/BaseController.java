package com.springboot.eureka.client.base.controller;

import com.springboot.eureka.client.base.config.convert.DateEditor;
import com.springboot.eureka.client.base.util.UserReqContextUtil;
import com.springboot.eureka.client.base.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.Date;


/**
 * Controller基类，适用当前应用场景：前后端分离，集成SSO，前端请求参数为JSON字符串形式（requestbody）
 */
public class BaseController {

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

}
