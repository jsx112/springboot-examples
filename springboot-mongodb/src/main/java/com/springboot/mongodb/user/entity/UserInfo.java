package com.springboot.mongodb.user.entity;/**
 * Created by dell on 2017/11/13.
 */

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 登录用户的基本信息
 *
 * @author jiasx
 * @create 2017-11-13 14:30
 **/
@Data
@ToString
@Document(collection="UserInfo")
public class UserInfo {

    @Id
    private Long userId;
    private String account;
    private String password;
    private String nickName;
    private String name;
    private String phone;
    private Date createTime;

    /**
    * 分组查询数据，非持久化
    **/
    @Transient
    private int accountNum;
}
