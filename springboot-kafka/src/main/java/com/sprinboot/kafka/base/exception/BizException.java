package com.sprinboot.kafka.base.exception;


import com.sprinboot.kafka.base.entity.CodeMsg;
import lombok.Getter;

/**
 * 基础枚举类
 * @author jiasx
 *
 */
public class BizException extends RuntimeException {

	@Getter
	private CodeMsg codeMsg;
	
	public BizException(CodeMsg codeMsg){
		super(codeMsg.getMsg());
		this.codeMsg = codeMsg;
	}

	public BizException(CodeMsg codeMsg,Throwable e){
		super(codeMsg.getMsg(),e);
		this.codeMsg = codeMsg;
	}

	@Override
	public String getMessage() {
		return codeMsg.getMsg();
	}
}