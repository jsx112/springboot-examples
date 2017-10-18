package com.springboot.shiro.exception;

import com.springboot.shiro.domain.CODE;

import java.util.HashMap;
import java.util.Map;

/**
* 顶层异常Bean，必须捕获
* @author jiasx
* @create 2017/10/18 14:30
**/

public class BizException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /** 返回码：
     *      00000000：表示请求成功
     *      非00000000：表示请求失败，此时前端需要根据i18nKey从国际化配置文件中读取对应的value来显示给用户
     **/
    private String rtnCode;

    /**
     * 国际化key ：对应国际化配置文件中的key  【前端国际化配置文件格式：basename_language_country.properties】
     */
    private String i18nKey;

    /**
     * 前端提示信息：默认值【中文】，当国际化配置文件中没有定义时显示
     */
    private String msg;

    public BizException() {
    }

    /**
     * 构造函数  通过CODE构造顶层异常
     * @param code 异常码实体类
     */
    public BizException(CODE code) {
        this.rtnCode = code.getRtnCode();
        this.i18nKey = code.name();     //code name与国际化配置文件中的key对应
        this.msg = code.getMsg();       //默认显示内容
    }

    public Map<String, Object> toMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("rtnCode",this.rtnCode);
        map.put("i18nKey", this.i18nKey);
        map.put("msg", this.msg);
        return map;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRtnCode() {
        return rtnCode;
    }

    public void setRtnCode(String rtnCode) {
        this.rtnCode = rtnCode;
    }

    public String getI18nKey() {
        return i18nKey;
    }

    public void setI18nKey(String i18nKey) {
        this.i18nKey = i18nKey;
    }

}
