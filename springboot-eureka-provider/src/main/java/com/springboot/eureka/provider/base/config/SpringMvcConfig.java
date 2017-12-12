package com.springboot.eureka.provider.base.config;

import com.springboot.eureka.provider.base.config.convert.JsonMessageConverter;
import com.springboot.eureka.provider.base.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;


/**
* springmvc基础配置类
* @author ysh
* @create 2017/11/12
**/
@Configuration
public class SpringMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 自定义消息转换器，用于统一封装响应数据。全部用json去处理
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.clear();
        converters.add(jsonMessageConverter());
    }

    @Bean
    public JsonMessageConverter jsonMessageConverter(){
        return new JsonMessageConverter();
    }



    /**
     * 请求拦截配置
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //排除拦截的url:登录接口、登录状态接口、api文档等等；根据具体情况排除
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/login",
                        "/checkLoginStatus",
                        "/v2/api-docs",
                        "/swagger-ui.html",
                        "/swagger-resources/**"
                );
        super.addInterceptors(registry);
    }

    /**
     * 处理跨域问题
     */
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");//允许域。若是所有：*
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
