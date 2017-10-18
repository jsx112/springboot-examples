package com.springboot.quartz.entry;

/**
 * redis常量类【key，expire time】
 *      key:统一格式为【键类型:字段1=%s;字段2=%s...】，并使用String.format()生成完整key
 *      expire time:可选单位统一：秒、分钟、小时、天等
 *
 * 简称说明：
 *
 * @author renzh 2017/5/10
 *
 */
public class RedisKeys {
    /**
     * 1 用户登录token【keyType=string,value=user对象】
     */
    public static final String USER_TOKEN = "user_token:token=%s";
    public static final int USER_TOKEN_EXPIRE = 60 * 60 * 2;// 2小时

    /**
     * 2 验证码	【keyType=string,value=验证码内容】
     */
    public static final String CAPTCHA_TOKEN = "captcha_token:token=%s";
    public static final int CAPTCHA_TOKEN_EXPIRE = 60 * 3;// 3分钟

    /**
     * 3 游戏与游戏类型关联关系	【keyType=string,value=gameTypeId】  key:value=platformId:gameTypeId  【不失效】
     */
    public final static String REDIS_GAME_TYPE = "hhly:partner:game:type:%s";

    /**
     * 4 第三方商户与游戏类型关联关系	【keyType=string,value=gameTypeId】  key:value=gameTypeId:mchId  【不失效】
     */
    public final static String REDIS_GAME_TYPE_TO_MCHID = "hhly:partner:gameType2mchId:%s";


}
