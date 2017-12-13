package com.springboot.jpa.exception;


import com.springboot.jpa.entry.CodeMsg;
import lombok.Getter;

/**
 * 基础枚举类
 * @author ysh
 *
 */
public class BizException extends RuntimeException {

	@Getter
	private CodeMsg codeMsg;
	
	public BizException(CodeMsg codeMsg){
		super(codeMsg.getMsg());
		this.codeMsg = codeMsg;
	}

	@Override
	public String getMessage() {
		return codeMsg.getMsg();
	}
}