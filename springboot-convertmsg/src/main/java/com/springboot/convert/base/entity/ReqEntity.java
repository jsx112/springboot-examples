package com.springboot.convert.base.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * http响应数据包装类【支持泛型】
 * @author ysh
 */
@Data
public class ReqEntity<T> implements Serializable {

	/**
	 * 每次请求的token
	 */
	private String token = "";

    /**
     * 请求数据体
     */
    private T body;

}
