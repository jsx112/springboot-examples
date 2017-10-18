package com.springboot.quartz.shiro;

import com.springboot.quartz.entry.*;
import com.springboot.quartz.exception.BizException;
import com.springboot.quartz.redis.IRedisRepository;
import com.springboot.quartz.system.entity.SysUser;
import com.springboot.quartz.util.CookieUtil;
import com.springboot.quartz.util.JacksonUtil;
import com.springboot.quartz.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
* 访问控制过滤器
* @author jiasx
* @create 2017/9/14 9:36
**/
public class PlatformAccessControlFilter extends PathMatchingFilter {

    private Logger logger = LoggerFactory.getLogger(PlatformAccessControlFilter.class);

    @Autowired
    IRedisRepository iRedisRepository;

    /**
    * 用户登录信息上下文
    **/
    private static ThreadLocal<UserLoginContext> userLoginContext = new ThreadLocal<>();
    
    private String[] unAuthUrls = null;

    public void setUnAuthUrl(String[] unAuthUrls) {
        this.unAuthUrls = unAuthUrls;
    }

    protected boolean isUnAuthUrl(ServletRequest request) {
        boolean flag = false;
        for(String unAuthUrl:unAuthUrls){
            if(this.pathsMatch(unAuthUrl, request)){
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * 先执行：isAccessAllowed 再执行onAccessDenied
     *
     * 表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，
     * 如果允许访问返回true，否则false；
     *
     * 如果返回true的话，就直接返回交给下一个filter进行处理。
     * 如果返回false表示该拦截器实例已经处理了，将直接返回即可。
     */
    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
           throws Exception {
       logger.info("PlatformAccessControlFilter.onPreHandle()");
       if(isUnAuthUrl(request)){
           return true;
       }

        String userToken = CookieUtil.getCookieValue((HttpServletRequest) request, CookieKeys.USER_TOKEN);
        if (userToken == null) {
            userLoginContext.set(null); // 后台没有获取到用户令牌时，清空用户登录状态
            onLoginFail(response, CODE.user_token_invalid);
            return false;//就直接返回给请求者.
        }
        String redisKey = String.format(RedisKeys.USER_TOKEN, userToken);
        Object object = iRedisRepository.get(redisKey);

        logger.info("登录拦截器：userToken=" + userToken);

        if (object == null || !(object instanceof SysUser)) {
            onLoginFail(response,CODE.user_token_invalid);
            return false;//就直接返回给请求者.
        }else {
            //刷新token有效期【redis,cookie】
            iRedisRepository.expire(redisKey, RedisKeys.USER_TOKEN_EXPIRE, TimeUnit.SECONDS);
            CookieUtil.add((HttpServletResponse) response, CookieKeys.USER_TOKEN, userToken, RedisKeys.USER_TOKEN_EXPIRE);

            //记录登录状态
            UserLoginContext context = new UserLoginContext();
            context.setUserToken(userToken);
            context.setUserId(((SysUser) object).getId());
            context.setLoginId(((SysUser) object).getAccount());
            userLoginContext.set(context);

            logger.info("登录拦截器：userId=" + context.getUserId() + ",loginId=" + context.getLoginId());
            PlatformAuthenticationToken token = new PlatformAuthenticationToken((SysUser) object,userToken);
            try {
                SecurityUtils.getSubject().login(token);
            } catch (AuthenticationException e) {
                onLoginFail(response,CODE.user_no_permission);
                return false;//就直接返回给请求者.
            }
            return true;
        }
    }
 
    //登录失败时默认返回401 状态码
    private void onLoginFail(ServletResponse response, CODE code){
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        //响应数据
        ResponseT responseT = new ResponseT(new BizException(code));
        String errorJsonStr = JacksonUtil.toJson(responseT);//最终响应数据【暂时支持ajax请求】

        try {
            httpResponse.setContentType("application/json;charset=UTF-8");
            PrintWriter writer = httpResponse.getWriter();
            writer.write(errorJsonStr);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得当前登录用户上下文
     */
    public static UserLoginContext getUserLoginContext() {
        if (StringUtil.hasNull(userLoginContext.get(), userLoginContext.get().getUserToken(), userLoginContext.get().getUserId(), userLoginContext.get().getLoginId())) {
            throw new BizException(CODE.user_token_invalid);
        }
        return userLoginContext.get();
    }

    /**
     * 清空登录用户上下文
     */
    public static void clearLoginContext() {
        userLoginContext.set(null);
    }
 
}