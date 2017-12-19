package com.sprinboot.kafka.conf.filter;

import com.sprinboot.kafka.admin.user.entity.User;
import lombok.Data;

/**
* 使用ThreadLocal封装每次请求的一些参数
* @author jiasx
* @create 2017/11/30 12:57
**/
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
            userReqContext = new UserReqContext();
            reqContextLocal.set(userReqContext);
        }else{
            userReqContext = reqContextLocal.get();
        }
    	userReqContext.setToken(token);
    }
    
    /**
     * 设置请求路径uri
     */
    public static void setRequestUri(String requestUri){
    	UserReqContext userReqContext = null;
        if(reqContextLocal.get()==null){
            userReqContext = new UserReqContext();
            reqContextLocal.set(userReqContext);
        }else{
            userReqContext = reqContextLocal.get();
        }
    	userReqContext.setRequestUri(requestUri);;
    }

    /**
     * 设置请求客户端Ip
     */
    public static void setRequestClientIp(String clientIp){
    	UserReqContext userReqContext = null;
    	if(reqContextLocal.get()==null){
            userReqContext = new UserReqContext();
            reqContextLocal.set(userReqContext);
    	}else{
            userReqContext = reqContextLocal.get();
    	}
    	userReqContext.setClientIp(clientIp);;
    }

    /**
     * 设置请求客户端Ip
     */
    public static void setRequestUser(User user){
    	UserReqContext userReqContext = null;
        if(reqContextLocal.get()==null){
            userReqContext = new UserReqContext();
            reqContextLocal.set(userReqContext);
        }else{
            userReqContext = reqContextLocal.get();
        }
    	userReqContext.setUser(user);;
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
    public static String getRequestClientIp(){
    	return reqContextLocal.get().getClientIp();
    }

    /**
     * 获取请求路径uri
     */
    public static User getRequestUser(){
    	return reqContextLocal.get().getUser();
    }
    
    /**
     * 获取当前用户id
     */
    public static String getRequestUserId(){
        if(reqContextLocal.get().getUser()==null){
            return "-1";
        }
    	return reqContextLocal.get().getUser().getId();
    }



    /**
     * 用户请求对象
     * @author jiasx
     */
    @Data
    public static class UserReqContext {
        /**
         * 请求路径uri
         **/
        private String requestUri;

        /**
         * 请求客户端ip
         **/
        private String clientIp;

        /**
         * 请求token
         **/
        private String token;

        /**
         * 用户信息
         **/
        private User user;

    }
}
