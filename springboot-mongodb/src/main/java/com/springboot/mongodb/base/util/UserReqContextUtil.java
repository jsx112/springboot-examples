package com.springboot.mongodb.base.util;

import com.springboot.mongodb.user.entity.UserInfo;
import lombok.Data;

/**
 * 使用ThreadLocal封装每次请求的一些参数
 * @author ysh
 */
public class UserReqContextUtil {

    /**
    * 用户请求信息上下文
    **/
    private static ThreadLocal<UserReqContext> reqContextLocal = new ThreadLocal<UserReqContext>();
	
    /**
     * 设置请求信息
     * @param reqContext
     */
    public static void set(UserReqContext reqContext){
    	reqContextLocal.set(reqContext);
    }
    
    
    /**
     * 设置请求token
     */
    public static void setToken(String token){
    	UserReqContext userReqContext = null;
    	if(reqContextLocal.get()==null){
    		userReqContext = reqContextLocal.get();
    	}else{
    		userReqContext = new UserReqContext();
    	}
    	userReqContext.setToken(token);
    }
    
    /**
     * 设置请求路径uri
     */
    public static void setRequestUri(String requestUri){
    	UserReqContext userReqContext = null;
    	if(reqContextLocal.get()==null){
    		userReqContext = reqContextLocal.get();
    	}else{
    		userReqContext = new UserReqContext();
    	}
    	userReqContext.setRequestUri(requestUri);;
    }
    
    /**
     * 获取请求信息
     * @return
     */
    public static UserReqContext get(){
    	return reqContextLocal.get();
    }
	
    
    /**
     * 获取请求token
     */
    public static String getToken(){
    	return reqContextLocal.get().getToken();
    }
    
    /**
     * 获取请求路径uri
     */
    public static String getRequestUri(){
    	return reqContextLocal.get().getRequestUri();
    }
    
    /**
     * 获取请求路径uri
     */
    public static UserInfo getLoginUserInfo(){
    	return reqContextLocal.get().getUserInfo();
    }
    
    /**
     * 获取当前用户id
     */
    public static Long getLoginUserId(){
    	return reqContextLocal.get().getUserInfo().getUserId();
    }



    /**
     * 用户请求对象
     * @author ysh
     */
    @Data
    public static class UserReqContext {
        /**
         * 请求路径uri
         **/
        private String requestUri;

        /**
         * 请求token
         **/
        private String token;

        /**
         * 用户信息
         **/
        private UserInfo userInfo;

    }
}
