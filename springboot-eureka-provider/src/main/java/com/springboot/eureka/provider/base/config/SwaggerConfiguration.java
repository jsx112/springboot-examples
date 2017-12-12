package com.springboot.eureka.provider.base.config;

import com.google.common.base.Predicates;
import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
* Swagger配置类:
* @author jiasx
* @create 2017/9/11 15:08
**/
@Configuration
@EnableSwagger2
public class SwaggerConfiguration extends WebMvcConfigurerAdapter {

    /**
     * SpringBoot默认已经将classpath:/META-INF/resources/和classpath:/META-INF/resources/webjars/映射
     * 所以该方法不需要重写，如果在SpringMVC中，需要在xml中定义
     * 重写该方法需要 extends WebMvcConfigurerAdapter
     *
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 可以定义多个组，比如本类中定义把test和demo区分开了
     * （访问页面就可以看到效果了）
     */

    @SuppressWarnings("unchecked")
    @Bean
    public Docket api() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("Api")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))//根据注解去过滤接口
                .paths(Predicates.or(PathSelectors.regex("/api/.*")))//通过路径的方式过滤的接口
                .build()
                .apiInfo(apiInfo());
        ;
        return docket;
    }


    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo("Demo相关接口",//大标题
                "Demo相关接口，主要用于demo演示.",//小标题
                "1.0",//版本
                "http://baidu.com",
                new Contact("演示人", "", ""),//作者
                "一只小蚂蚁",//链接显示文字
                "http://baidu.com",//网站链接
                new ArrayList<>()
        );
        return apiInfo;
    }

}