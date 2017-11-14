package com.springboot.mongodb.base.enums;


/**
 * 定义全局的枚举，用于返回给终端用户
 * 异常码实体类：code name与国际化配置文件中的key对应（若是需要国际化的话）
 *      code  返回码
 *              00000000：表示请求成功
 *              非00000000：表示请求异常
 *      msg  前端提示信息：默认值【中文】，当国际化配置文件中没有定义时显示
 * @author ysh
 *
 */
public enum CodeMsg {
	//1、异常码定义区
	/**
     * 1.1 成功返回码
     */
    success("成功", "00000000"),

    /**
     * 1.2 系统异常
     */
    system_error("操作失败，程序发生异常", "10000000"),

    /**
     * 1.3 业务异常
     */
    invalid_json_data("不是有效的JSON数据","20000001"),
    token_not_blank("token不能为空","20000002"),
    invalid_token_data("无效的token","20000003"),
    request_param_error("请求参数格式有误","20000004");
	
	//2、属性
    private String code; //返回码
    private String msg;     //前端提示信息（默认显示内容）

    //3、构造方法
    CodeMsg(String msg,String code) {
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
