package com.sprinboot.kafka.conf.filter;

import com.sprinboot.kafka.Constant;
import com.sprinboot.kafka.base.entity.CodeMsg;
import com.sprinboot.kafka.base.exception.BizException;
import com.sprinboot.kafka.util.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* 拦截器，每次请求之前需要做的事情
* 针对内网的请求（通过白名单ip去判定），不做任何拦截
* 针对外网的请求，需要验证签名的正确性。
* @author jiasx
* @create 2017/11/14 11:08
**/
public class AuthInterceptor implements HandlerInterceptor {

	private Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//经过nginx代理，获取真实的客户端请求地址.如果为空直接取客户端地址
		String clientIp = request.getHeader("X-Forwarded-For");
		if(StringUtils.isEmpty(clientIp)){
			clientIp = request.getRemoteAddr();
		}else{
			clientIp = clientIp.split(",")[0];
		}
		String token = CookieUtil.getCookieValue(request, Constant.USER_TOKEN);
		UserReqContextUtil.setRequestClientIp(clientIp);
		UserReqContextUtil.setToken(token);
		if (StringUtils.isEmpty(token)){
			throw new BizException(CodeMsg.token_is_valid);
		}

		//是否合法用户
		boolean isLegalUser = false;

		//执行拦截逻辑,直接设置为true
		isLegalUser = true;

		if(!isLegalUser){
			logger.error("用户不合法");
			throw new BizException(CodeMsg.token_is_valid);
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}
}