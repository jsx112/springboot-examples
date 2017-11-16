package com.springboot.convert.controller;/**
 * Created by dell on 2017/11/13.
 */

import com.springboot.convert.base.controller.BaseController;
import com.springboot.convert.base.entity.UserInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 用户服务控制类
 *
 * @author jiasx
 * @create 2017-11-13 16:13
 **/
@RestController
@RequestMapping("/json")
public class JsonController extends BaseController {



    @PostMapping(value = "/get")
    public UserInfo get(@RequestBody String json) {
        UserInfo userInfo = new UserInfo();
        userInfo.setAccount("111");
        userInfo.setPhone(json);
        userInfo.setCreateTime(new Date());
        return userInfo;
    }

}
