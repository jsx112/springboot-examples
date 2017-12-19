package com.sprinboot.kafka.core;/**
 * Created by dell on 2017/11/27.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * demo类
 *
 * @author jiasx
 * @create 2017-11-27 9:56
 **/
@Controller
public class DemoController {


    @RequestMapping("/")
    public void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("/web/index.html");
    }

}
