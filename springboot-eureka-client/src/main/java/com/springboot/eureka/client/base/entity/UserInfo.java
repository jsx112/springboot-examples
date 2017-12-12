package com.springboot.eureka.client.base.entity;/**
 * Created by dell on 2017/11/13.
 */

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 登录用户的基本信息
 *
 * @author jiasx
 * @create 2017-11-13 14:30
 **/
@Data
@ToString
public class UserInfo {

    private Long userId;
    private String account;
    private String password;
    private String nickName;
    private String name;
    private String phone;
    private Date createTime;

}
