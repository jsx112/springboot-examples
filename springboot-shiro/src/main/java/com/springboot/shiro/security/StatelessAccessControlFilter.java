package com.springboot.shiro.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.springboot.shiro.domain.ResponseT;
import com.springboot.shiro.exception.BizException;
import com.springboot.shiro.domain.CODE;
import com.springboot.shiro.util.JacksonUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.AccessControlFilter;

/**
* 访问控制过滤器
* @author jiasx
* @create 2017/10/18 12:20
**/
public class StatelessAccessControlFilter extends AccessControlFilter{
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
     * isAccessAllowed：表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，
     * 如果允许访问返回true，否则false；
     *
     * 如果返回true的话，就直接返回交给下一个filter进行处理。
     * 如果返回false的话，回往下执行onAccessDenied
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
           throws Exception {
       if(isUnAuthUrl(request)){
           return true;
       }
       return false;
    }
 
    /**
     * onAccessDenied：表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；
     * 如果返回false表示该拦截器实例已经处理了，将直接返回即可。
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws  Exception{

       //1、客户端请求的参数列表
       Enumeration<String> paramKeys = request.getParameterNames();
       Map<String, String> paramsMap = new HashMap<>();
       while (paramKeys.hasMoreElements()){
           String paramKey = paramKeys.nextElement();
           paramsMap.put(paramKey,request.getParameter(paramKey));
       }

       //2、客户端生成的消息摘要
       String clientDigest = paramsMap.get("digest");
       paramsMap.remove("digest");//为什么要移除呢？签名或者消息摘要算法的时候不能包含digest.
       //3、客户端传入的用户身份
       String username = paramsMap.get("username");
       //4、对map按照key进行排序
       paramsMap = HmacSHA256Utils.sortMapByKey(paramsMap);
       //5、生成无状态Token
       StatelessAuthenticationToken token = new StatelessAuthenticationToken(username,paramsMap,clientDigest);
       try {
           //6、委托给Realm进行登录
           getSubject(request, response).login(token);
       } catch (AuthenticationException e) {
           onLoginFail(response, CODE.user_token_invalid);
           return false;//就直接返回给请求者.
       }
       return true;
    }
   
    //登录失败时默认返回401 状态码
    private void onLoginFail(ServletResponse response,CODE code){
       HttpServletResponse httpResponse = (HttpServletResponse) response;
        //响应数据
        ResponseT responseT = new ResponseT(new BizException(code));
        String errorJsonStr = JacksonUtil.toJson(responseT);//最终响应数据【暂时支持ajax请求】

        try {
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(errorJsonStr);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
}