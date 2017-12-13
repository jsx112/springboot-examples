package com.springboot.sitemesh.base.entity;

/**
* 请求结果
* @author jiasx
* @create 2017/11/22 17:37
**/
public enum CodeMsg {
	//1、异常码定义区
    success("成功", "200"),
    system_error("系统出错，请稍后再试", "500"),
    url_not_effective("URL不合法，请确认", "501"),
    not_allow_visit("不允许访问", "401"),
    resource_not_fount("资源丢失了。。。", "404"),
    business_error("业务异常，请稍后再试", "400");


	//2、属性
    private String code; //返回码
    private String msg;     //前端提示信息（默认显示内容）

    //3、构造方法
    CodeMsg(String msg, String code) {
        this.code = code;
        this.msg = msg;
    }

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

}
