package com.swagger.test; /**
 * Created by dell on 2017/9/9.
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 城市实体类
 *
 * @author jiasx
 * @create 2017-09-09 14:43
 **/
@ApiModel(value="城市信息对象")
public class City {

    //required 默认为false，如果改为true，就要求所有用到改bean的地方都必须要传递改参数，限制性太大，一般不建议写其他属性
    @ApiModelProperty(value="主键ID" ,dataType="String")
    private String id;

    @ApiModelProperty(value="城市名称" ,dataType="String")
    private String name;

    @ApiModelProperty(value="所属省份" ,dataType="String")
    private String provience;

    public City(){}

    public City(String id, String name, String provience) {
        this.id = id;
        this.name = name;
        this.provience = provience;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvience() {
        return provience;
    }

    public void setProvience(String provience) {
        this.provience = provience;
    }
}
