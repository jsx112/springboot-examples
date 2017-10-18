package com.springboot.shiro.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Value;

/**
* 自定义relam，负责登录认证和权限校验
* @author jiasx
* @create 2017/10/18 12:21
**/
public class StatelessAuthorizingRealm extends AuthorizingRealm{

    @Value("${client.appSecret}")
    private String appSecret;
 
    /**
     * 仅支持StatelessToken 类型的Token，
     * 那么如果在StatelessAuthcFilter类中返回的是UsernamePasswordToken，那么将会报如下错误信息：
     * Please ensure that the appropriate Realm implementation is configured correctly or
     * that the realm accepts AuthenticationTokens of this type.StatelessAuthcFilter.isAccessAllowed()
     */
    @Override
    public boolean supports(AuthenticationToken token) {
       return token instanceof StatelessAuthenticationToken;
    }

    /**
     * 身份验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
       StatelessAuthenticationToken statelessToken = (StatelessAuthenticationToken)token;
       String username = (String)statelessToken.getPrincipal();//不能为null,否则会报错的.

       //在服务器端生成客户端参数消息摘要
       String serverDigest = HmacSHA256Utils.digest(appSecret, statelessToken.getParams());
      
      
       //然后进行客户端消息摘要和服务器端消息摘要的匹配
       SimpleAuthenticationInfo  authenticationInfo = new SimpleAuthenticationInfo(
              username,
              serverDigest,
              getName());
       return authenticationInfo;
    }
   
    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
       //根据用户名查找角色，请根据需求实现
       String username = (String) principals.getPrimaryPrincipal();
       SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
      
       //这里模拟admin账号才有role的权限.
       if("admin".equals(username)){
           authorizationInfo.addRole("admin");
       }
       if("test".equals(username)){
           authorizationInfo.addStringPermission("user:add");
       }
       return authorizationInfo;
    }
}