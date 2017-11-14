package com.springboot.resttemplate;/**
 * Created by dell on 2017/11/1.
 */

import org.springframework.web.client.RestTemplate;

/**
* 测试resttemplate
* @author jiasx
* @create 2017-11-01 12:19
**/
public class RestTemplateTest {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        int count = 0;
        while(count<30){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String result = restTemplate.getForObject("http://192.168.69.232/partnerManager/login",String.class);
                    System.out.println(Thread.currentThread()+"========"+result);
                }
            }).start();
            count++;
        }
    }



}
