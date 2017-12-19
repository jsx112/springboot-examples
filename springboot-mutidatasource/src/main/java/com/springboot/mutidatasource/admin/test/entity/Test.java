package com.springboot.mutidatasource.admin.test.entity;/**
 * Created by dell on 2017/11/22.
 */

import com.springboot.mutidatasource.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户实体对象
 *
 * @author jiasx
 * @create 2017-11-22 17:39
 **/
@Data
@ToString
@ApiModel(value="测试实体对象")
@Entity
@Table(name="user")
public class Test extends BaseEntity {
    @Id
    @ApiModelProperty(value="主键id" ,dataType="String")
    private String id;

    /**
     * 用户账
     **/
    @ApiModelProperty(value="用户账号" ,dataType="String")
    private String account;


}
