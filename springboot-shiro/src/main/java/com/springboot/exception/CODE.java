package com.springboot.exception;

/**
 * 异常码实体类：code name与国际化配置文件中的key对应（若是需要国际化的话）
 *      rntCode  返回码
 *              00000000：表示请求成功
 *              非00000000：表示请求异常 须唯一 【共两类，系统异常：system_error_10xxx（1开头）、业务异常：biz_error_20xxx（2开头）。数字部分递增】
 *      msg  前端提示信息：默认值【中文】，当国际化配置文件中没有定义时显示
 *
 * @author renzh 2017-5-5
 */
public enum CODE {
    //1、异常码定义区
    /**
     * 1.1 成功返回码
     */
    success("成功", "00000000"),

    /**
     * 1.2 系统异常 【返回码编号以1开头】
     */
    system_error("系统异常", "system_error_10001"),
    request_param_error("请求参数异常", "system_error_10002"),
    json_format_error("json格式错误", "system_error_10003"),
    file_type_error("文件格式错误", "system_error_10004"),
    file_upload_fail("文件上传失败","system_error_10005"),
    no_file_upload("没有文件上传","system_error_10006"),
    file_not_found("文件不存在","system_error_10007"),
    file_upload_server_error("文件服务器异常","system_error_10008"),
    export_error("导出异常","system_error_10009"),
    no_data_export("无数据可导出","system_error_10010"),
    no_handler_found("接口url错误","system_error_10011"),
    illegal_operation("非法操作","system_error_10012"),
    file_download_fail("下载失败","system_error_10013"),
    file_name_null("文件名为空","system_error_10014"),

    /**
     * 1.3 业务异常 【返回码编号以2开头】
     */
    loginid_or_pwd_error("用户名或密码错误","biz_error_20001"),
    user_token_invalid("用户登录信息已失效，请重新登录","biz_error_20002"),
    user_no_permission("权限不足","biz_error_20002"),
    captcha_is_null("验证码不能为空","biz_error_20003"),
    captcha_error("验证码错误","biz_error_20004"),
    captcha_token_not_exists("验证码令牌不存在","biz_error_20005"),
    user_token_not_exists("用户登录令牌不存在","biz_error_20006"),
    captcha_expire("验证码已失效，请刷新","biz_error_20007"),
    game_not_exists("游戏不存在","biz_error_20008"),
    position_not_exists("推荐位不存在","biz_error_20009"),
    user_not_exists("用户不存在","biz_error_20010"),
    can_not_modify_loginid("不能修改用户登录ID","biz_error_20011"),
    role_name_repeat("用户组已存在","biz_error_20012"),
    role_not_exists("用户组不存在","biz_error_20013"),
    user_not_role("用户未加入用户组","biz_error_20014"),
    conf_not_exists("配置项不存在","biz_error_20015"),
    conf_is_too_much("不能配置多条规则","biz_error_20016"),
    lower_greater_than_upper("最低限额不能大于最高限额","biz_error_20017"),
    behavior_not_null("行为日志名称不能为空","biz_error_20018"),
    position_name_exists("该推荐位已存在","biz_error_20019"),
    user_disabled("该账户已被禁用，请联系管理员","biz_error_20020"),
    loginid_exists("账号已被使用","biz_error_20021"),
    admin_can_not_modify("管理员都敢动，KPI准备好了吗","biz_error_20022"),
    admin_role_can_not_modify("超级管理员组不允许删除","biz_error_20023"),
    withdrawal_app_not_exists("提款申请不存在","biz_error_20024"),
    withdrawal_app_not_null("提款申请不能为空","biz_error_20025"),
    withdrawal_range_error("提款限额范围：100-200000","biz_error_20027"),
    qualifiedMember_exists("该游戏类型已配置规则","biz_error_20028"),
    withdrawal_app_audited("该提款申请已审核，不能再次审核","biz_error_20029"),
    withdrawal_app_is_not_passed_unPay("该申请不是待打款状态","biz_error_20030"),
    trade_order_not_exists("提款申请单不存在","biz_error_20031"),
    partner_fund_not_exists("代理资金账户不存在","biz_error_20032"),
    audit_tips_audit_unPass_remark_is_null("审核不通过时，请填写不通过原因","biz_error_20033"),
    pay_tips_audit_pay_fail_remark_is_null("打款失败时，请填写打款失败原因","biz_error_20034"),
    pay_tips_please_audit("未审核，请先审核","biz_error_20035"),
    pay_tips_unPay_please_export("待打款，请导出记录到控台打款","biz_error_20036"),
    pay_tips_audit_unPass_cannot_pay("审核不通过，不能打款","biz_error_20037"),
    pay_tips_payed_cannot_repeat_pay("已打款，不能再次打款","biz_error_20038"),
    pay_tips_pay_fail_cannot_repeat_pay("打款失败，不能再次打款","biz_error_20039"),
    pay_log_save_fail("批量打款日志保存失败","biz_error_20040"),
    export_unPayList_fail("导出待打款列表失败","biz_error_20041"),
    batch_update_wa_fail("批量更新为打款中失败","biz_error_20042"),
    checkNotNull("必填信息不能为空","biz_error_20043"),
    checkRackBackIsNo("该返佣类型会员区间有交集,提交失败","biz_error_20044"),
    proxy_proportion_is_exists("该月份已设置返佣比例，不能重复设置","biz_error_20045"),
    is_before_month("不能设置历史月份的返佣比例","biz_error_20046"),
    proportion_outof_limit("返佣比例超出正常范围【取值范围：[0,100]】","biz_error_20047"),
    proportion_not_exists("该返佣比例不存在","biz_error_20048"),
    feed_back_not_exists("该问题不存在","biz_error_20049"),
    feed_back_reply_null("回复内容不能为空","biz_error_20050"),
    checkShowIndexExit("该类型显示顺序已经存在","biz_error_20051"),
    qualifiedMember_not_exists("该合格会员配置不存在","biz_error_20052"),
    gameTypeCon_not_exists("游戏与游戏类型关联关系不存在","biz_error_20052"),
    unPay_list_is_empty("暂时没有待打款的记录","biz_error_20053"),
    startTime_after_endTime("开始时间不能晚于结束时间","biz_error_20054"),
    partner_fund_update_fail("用户资金账户更新失败，请检查","biz_error_20055"),
    bla_data_not_exists("佣金数据不存在，请核实","biz_error_20056"),
    partner_data_not_exists("找不到待更新的对象，请检查ID","biz_error_20057"),
    withdrawal_rate_range_error("平台费率范围0%-100%","biz_error_20058"),
    withdrawal_service_charge_range_error("银行卡费率范围0-100","biz_error_20059"),
    msg_is_null("消息不能为空","biz_error_20060"),
    order_index_is_null("显示顺序不能为空","biz_error_20061"),
    type_has_problems("该分类下还有问题，请先删除问题","biz_error_20060"),
    has_no_data("查询数据为空，请确认","biz_error_20061"),
    current_state_cannot_oper ("当前状态不能进行此操作，请确认","biz_error_20062"),
    activities_end_please_edit ("当前活动已结束，请点击修改按钮编辑","biz_error_20063"),
    endTime_after_takeDownTime("结束时间不能晚于下架时间","biz_error_20064"),
    banner_not_exists("该banner不存在","biz_error_20065"),
    thirdparty_exists_id("商户ID已存在","biz_error_20066"),
    thirdparty_exists_name("商户名称已存在","biz_error_20067"),
    gameSingle_exists_codeID("游戏唯一标识已存在","biz_error_20068"),
    channelinfo_exists_codeID("渠道编码已存在","biz_error_20069"),
    help_exists_codeID("帮助问题编码已存在","biz_error_20070"),
    game_id_exists("游戏ID已被占用","biz_error_20071");

    //2、属性
    private String rtnCode; //返回码
    private String msg;     //前端提示信息（默认显示内容）

    //3、构造方法
    CODE(String msg, String rtnCode) {
        this.rtnCode = rtnCode;
        this.msg = msg;
    }

    public String getRtnCode() {
        return rtnCode;
    }

    public String getMsg() {
        return msg;
    }

}
