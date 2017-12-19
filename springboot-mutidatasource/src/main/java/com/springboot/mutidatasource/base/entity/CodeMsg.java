package com.springboot.mutidatasource.base.entity;

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
    business_error("业务异常，请稍后再试", "400"),

	//业务异常
    id_param_blank("参数id不能为空", "biz_001"),
    account_password_error("用户名或者密码出错", "biz_002"),
    param_note_blank("参数不能为空", "biz_003"),
    record_not_exist("记录数据不存在", "biz_004"),
    token_not_blank("token不能为空", "biz_005"),
    token_is_valid("token无效", "biz_006"),
    user_not_effective("用户未生效", "biz_007"),
    user_has_disabled("用户已禁用", "biz_007"),
    user_has_lapsed("用户已过期", "biz_007"),
    effect_time_not_gt_lapsed_time("生效时间不能大于过期时间", "biz_008");


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
