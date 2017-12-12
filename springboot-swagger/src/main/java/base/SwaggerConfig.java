package base;

import com.google.common.base.Predicates;
import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
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
 *
 * @author jiasx
 * @create 2017/9/11 15:08
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig   extends WebMvcConfigurerAdapter {

    /**
     * 配置servlet处理
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


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


        //http://127.0.0.1:8080/swagger-resources/configuration/ui
    }

    /**
     * 可以定义多个组，比如本类中定义把test和demo区分开了
     * （访问页面就可以看到效果了）
     */

    @SuppressWarnings("unchecked")
    @Bean
    public Docket testApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("Api")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select()
                //                .apis(RequestHandlerSelectors.basePackage("com.swagger.test"))//根据包名去过滤接口
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))//根据注解去过滤接口
                .paths(Predicates.or(PathSelectors.regex("/api/.*")))//通过路径的方式过滤的接口
                .build()
                .apiInfo(apiInfo());
        ;
        return docket;
    }

    @SuppressWarnings("unchecked")
    @Bean
    public Docket demoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Demo")
//                .genericModelSubstitutes(DeferredResult.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .pathMapping("/")
                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.swagger.test"))//根据包名去过滤接口
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))//根据注解去过滤接口
                .paths(Predicates.or(PathSelectors.regex("/demo/.*")))//过滤的接口
                .build()
                .apiInfo(apiInfo());
    }


    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo("短链接平台相关接口",
                "短链接平台相关接口.",
                "1.0",
                "http://shorturl.com",
                new Contact("", "", ""),
                "华海乐盈",
                "http://shorturl.com",
                new ArrayList<>()
        );
        return apiInfo;
    }

}