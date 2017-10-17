package com.springboot.rabbitmq.domain;/**
 * Created by dell on 2017/10/17.
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 业务订单消息
 *
 * @author jiasx
 * @create 2017-10-17 15:55
 **/
@Data
@ApiModel(value="业务对象")
public class BusinessOrder {
    @ApiModelProperty(value="订单主键ID" ,dataType="Long")
    private Long id;

    @ApiModelProperty(value="业务订单金额" ,dataType="BigDecimal")
    private BigDecimal orderAmount;

    @ApiModelProperty(value="业务编码" ,dataType="String")
    private String bussinessCode;

    @ApiModelProperty(value="业务名称" ,dataType="String")
    private String bussinessName;

}
