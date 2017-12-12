package com.springboot.eureka.provider.base.interceptor;

import com.springboot.eureka.provider.base.entity.UserInfo;
import com.springboot.eureka.provider.base.util.UserReqContextUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* 拦截器，每次请求之前需要做的事情
* @author jiasx
* @create 2017/11/14 11:08
**/
public class LoginInterceptor implements HandlerInterceptor {


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		UserReqContextUtil.UserReqContext userReqContext = new UserReqContextUtil.UserReqContext();
		userReqContext.setUserInfo(new UserInfo());
		userReqContext.setToken(request.getHeader("token"));
		userReqContext.setRequestUri(request.getRequestURI());
		UserReqContextUtil.set(userReqContext);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}
}