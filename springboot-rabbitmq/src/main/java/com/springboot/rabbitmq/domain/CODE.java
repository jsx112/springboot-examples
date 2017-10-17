package com.springboot.rabbitmq.domain;

/**
* 异常码实体类：code name与国际化配置文件中的key对应（若是需要国际化的话）
*      rntCode  返回码
*              00000000：表示请求成功
*              非00000000：表示请求异常 须唯一 *【共两类，系统异常：system_error_10xxx（1开头）、业务异常：biz_error_20xxx（2开头）。数字部分递增】
*      msg  前端提示信息：默认值【中文】，当国际化配置文件中没有定义时显示
* @author jiasx
* @create 2017/10/17 17:09
**/
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
    file_name_null("文件名为空","system_error_10014");


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
