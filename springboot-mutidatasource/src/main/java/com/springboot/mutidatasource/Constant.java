package com.springboot.mutidatasource;

/**
* 工程常量类
* @author jiasx
* @create 2017/12/2 16:16
**/
public class Constant {

    /**
     * 项目名称，用于在redis中进行区分
     */
    public static final String PROJ_NAME = "shorturl";


    /**
     * http前缀
     */
    public static final String HTTP_PREFIX = "http://";

    /**
     * https前缀
     */
    public static final String HTTPS_PREFIX = "https://";

    /**
     * 用户登录token key
     */
    public static final String USER_TOKEN = "token";


    /**
     * 用户登录token【keyType=string,value=user对象】
     */
    public static final String USER_TOKEN_REDIS_KEY = PROJ_NAME+":user_token:token=%s";


    /**2小时*/
    public static final int USER_TOKEN_EXPIRE = 60 * 60 * 2;

    /**第三方访问*/
    public static final String THIRD_VISIT_TOKEN = "token";


    /**第三方用户前缀*/
    public static final String USER_ACCOUNT_PREFIX = "td";

    /**第三方用户账号唯一id生成key，通过redis生成*/
    public static final String USER_INCREMENT_NUM_KEY = PROJ_NAME+":global_id:user_increment_num_key";

    /**短链接唯一id生成key，通过redis生成*/
    public static final String URL_INCREMENT_NUM_KEY = PROJ_NAME+":global_id:url_increment_num_key";







}
