package com.springboot.jpa.entry;

/**
* 请求结果
* @author jiasx
* @create 2017/11/22 17:37
**/
public enum CodeMsg {
    success("成功", "200"),
    system_error("操作失败，程序发生异常", "500"),
    business_error("地址丢失了...", "400");


    private String code; //返回码
    private String msg;     //前端提示信息（默认显示内容）
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
