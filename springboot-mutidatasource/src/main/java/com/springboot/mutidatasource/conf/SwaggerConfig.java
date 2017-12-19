package com.springboot.mutidatasource.conf;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
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
public class SwaggerConfig extends WebMvcConfigurerAdapter {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/swagger/v2/api-docs", "/v2/api-docs").setKeepQueryParams(true);;
        registry.addRedirectViewController("/swagger/configuration/security", "/configuration/security");
        registry.addRedirectViewController("/swagger/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
        registry.addRedirectViewController("/swagger/swagger-resources/**", "/swagger-resources/");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger/**").addResourceLocations("classpath:/META-INF/resources/");
    }

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
                .build()
                .apiInfo(apiInfo());
        ;
        return docket;
    }


    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo("短链接平台相关接口",
                "短链接平台相关接口.",
                "1.0",
                "",
                new Contact("", "", ""),
                "短链接",
                "",
                new ArrayList<>()
        );
        return apiInfo;
    }

}