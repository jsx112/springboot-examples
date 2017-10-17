package com.springboot.shiro;/**
 * Created by dell on 2017/9/13.
 */

import org.apache.shiro.mgt.DefaultSubjectFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * 无状态的session管理器
 *
 * @author jiasx
 * @create 2017-09-13 10:41
 **/
public class StatelessDefaultSubjectFactory extends DefaultWebSubjectFactory {
    @Override
    public Subject createSubject(SubjectContext context) {
        //不创建session.
        context.setSessionCreationEnabled(false);
        return super.createSubject(context);
    }
}
