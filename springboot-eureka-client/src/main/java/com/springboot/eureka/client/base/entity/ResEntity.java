package com.springboot.eureka.client.base.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * http响应数据包装类【支持泛型】
 * @author ysh
 */
@Data
public class ResEntity<T> implements Serializable {
	/**
	 * 请求结果编码
	 */
	private String code = "00000000";// 是否成功 00000000：成功

	/**
	 * 记录每次请求的路径，不会为空。
	 */
	private String path = "";

    /**
     * 业务数据体 【当请求失败时，该属性值为空】
     */
    private T data;


	/**
	 * 前端提示信息：【当请求失败时，该属性有值；否则，为空】  默认值【中文】，当国际化配置文件中没有定义时显示
	 */
	private String msg;
}
