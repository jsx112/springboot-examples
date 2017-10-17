package com.jsx.conf;

import com.jsx.converter.DateConverter;
import com.jsx.converter.JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author renzh on 2017/5/9.
 */
@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    /**
     * 自定义 数据转换器
     * @return
     */
    @Bean
    public ConversionServiceFactoryBean conversionService(){
        ConversionServiceFactoryBean conversionServiceFactoryBean=new ConversionServiceFactoryBean();
        Set<Converter> converters=new HashSet<Converter>();
        converters.add(new DateConverter());//日期转换器
        conversionServiceFactoryBean.setConverters(converters);
        return conversionServiceFactoryBean;
    }

    /**
     * 解决eureka中引入了xml转换导致应该返回json时实际返回xml的问题
     * @param configurer
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.ignoreAcceptHeader(true).defaultContentType(
                MediaType.APPLICATION_JSON_UTF8);
    }

    /**
     * 自定义消息转换器，用于统一封装响应数据
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
        //排除拦截的url:登录接口、获取验证码接口、校验验证码接口等等；根据具体情况排除
        //现在在ShiroConfiguration中进行配置
//        registry.addInterceptor(localInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns(
//                        "/login",
//                        "/getCaptcha",
//                        "/validateCaptcha",
//                        "/checkLoginStatus",
//                        "/imgUpload",
//                        "/fileUpload",
//                        "/fileDownload/*",
//                        "/appUpdate/upload",
//                        "/v2/api-docs",
//                        "/swagger-resources/**",
//                        "/appUpdate/download/**"
//                );
        super.addInterceptors(registry);
    }

    /**
     * 解决跨域
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
