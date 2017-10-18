package com.springboot.shiro.security;/**
 * Created by dell on 2017/9/13.
 */

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
* 无状态的session管理器
* @author jiasx
* @create 2017/10/18 15:28
**/
public class StatelessDefaultSubjectFactory extends DefaultWebSubjectFactory {
    @Override
    public Subject createSubject(SubjectContext context) {
        //不创建session.
        context.setSessionCreationEnabled(false);
        return super.createSubject(context);
    }
}
