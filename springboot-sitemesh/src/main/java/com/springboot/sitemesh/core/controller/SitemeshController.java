package com.springboot.sitemesh.core.controller;/**
 * Created by dell on 2017/11/22.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * sitemesh服务对外接口
 *
 * @author jiasx
 * @create 2017-11-22 18:05
 **/
@RestController
@RequestMapping("/url")
public class SitemeshController {

    private Logger logger = LoggerFactory.getLogger(SitemeshController.class);

}
