package com.springboot.shiro;/**
 * Created by dell on 2017/10/18.
 */

import com.springboot.shiro.security.HmacSHA256Utils;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试服务
 *
 * @author jiasx
 * @create 2017-10-18 17:03
 **/
public class TestApi {


    private String appSecret = "ce7f64b0e557edbb";

    String baseUrl = "http://127.0.0.1:8080";

    @Test
    public  void  testLogin(){
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(baseUrl+"/login?username=jsx&password=123", String.class);
        System.out.println("结果："+result);
    }

    @Test
    public  void  test1(){
        RestTemplate restTemplate = new RestTemplate();
        Map<String,String> paramsMap = new HashMap<>();
        paramsMap.put("username","jsx");
        paramsMap.put("password","123");
        paramsMap = HmacSHA256Utils.sortMapByKey(paramsMap);
        String digest = HmacSHA256Utils.digest(appSecret,paramsMap);
        paramsMap.put("digest",digest);
        StringBuffer paramStr = new StringBuffer();
        for(Map.Entry<String,String> entry:paramsMap.entrySet()){
            if(paramStr.length()==0){
                paramStr.append("?").append(entry.getKey()).append("=").append(entry.getValue());
            }else{
                paramStr.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
        }
        String result = restTemplate.getForObject(baseUrl+"/test1"+paramStr, String.class);
        System.out.println("结果："+result);
    }

    @Test
    public  void  test2(){
        RestTemplate restTemplate = new RestTemplate();
        Map<String,String> paramsMap = new HashMap<>();
        paramsMap.put("username","admin");
        paramsMap.put("password","123");
        paramsMap = HmacSHA256Utils.sortMapByKey(paramsMap);
        String digest = HmacSHA256Utils.digest(appSecret,paramsMap);
        paramsMap.put("digest",digest);
        StringBuffer paramStr = new StringBuffer();
        for(Map.Entry<String,String> entry:paramsMap.entrySet()){
            if(paramStr.length()==0){
                paramStr.append("?").append(entry.getKey()).append("=").append(entry.getValue());
            }else{
                paramStr.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
        }
        String result = restTemplate.getForObject(baseUrl+"/test2"+paramStr, String.class);
        System.out.println("结果："+result);
    }

    @Test
    public  void  test3(){
        RestTemplate restTemplate = new RestTemplate();
        Map<String,String> paramsMap = new HashMap<>();
        paramsMap.put("username","test");
        paramsMap.put("password","123");
        paramsMap = HmacSHA256Utils.sortMapByKey(paramsMap);
        String digest = HmacSHA256Utils.digest(appSecret,paramsMap);
        paramsMap.put("digest",digest);
        StringBuffer paramStr = new StringBuffer();
        for(Map.Entry<String,String> entry:paramsMap.entrySet()){
            if(paramStr.length()==0){
                paramStr.append("?").append(entry.getKey()).append("=").append(entry.getValue());
            }else{
                paramStr.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
        }
        String result = restTemplate.getForObject(baseUrl+"/test3"+paramStr, String.class);
        System.out.println("结果："+result);
    }
}
