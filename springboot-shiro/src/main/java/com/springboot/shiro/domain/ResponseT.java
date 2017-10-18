package com.springboot.shiro.domain;

import com.springboot.shiro.exception.BizException;

import java.io.Serializable;

/**
 * http响应数据包装类【支持泛型】
 * @author renzh 2017/5/8
 */
public class ResponseT<T> implements Serializable {
    private static final long serialVersionUID = 5351362311925879887L;
    /** 返回码：
     *      00000000：表示请求成功
     *      非00000000：表示请求失败，此时前端需要根据i18nKey从国际化配置文件中读取对应的value来显示给用户
     **/
    private String rtnCode;

    /**
     * 业务数据体 【当请求失败时，该属性为空】
     */
    private T bizData;

    /**
     * 国际化key ：【当请求失败时，该属性有值；否则，为空】对应国际化配置文件中的key
     */
    private String i18nKey;

    /**
     * 前端提示信息：【当请求失败时，该属性有值；否则，为空】  默认值【中文】，当国际化配置文件中没有定义时显示
     */
    private String msg;

    public ResponseT() {}

    public ResponseT(CODE code) {
        this.rtnCode = code.getRtnCode();
        this.i18nKey = code.name();     //code name与国际化配置文件中的key对应
        this.msg = code.getMsg();       //默认显示内容
    }

    public ResponseT(BizException bizException) {
        this.rtnCode = bizException.getRtnCode();
        this.i18nKey = bizException.getI18nKey();
        this.msg = bizException.getMsg();
    }

    public String getRtnCode() {
        return rtnCode;
    }

    public void setRtnCode(String rtnCode) {
        this.rtnCode = rtnCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getBizData() {
        return bizData;
    }

    public void setBizData(T bizData) {
        this.bizData = bizData;
    }

    public String getI18nKey() {
        return i18nKey;
    }

    public void setI18nKey(String i18nKey) {
        this.i18nKey = i18nKey;
    }
}
