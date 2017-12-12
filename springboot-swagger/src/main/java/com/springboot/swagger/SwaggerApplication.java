package com.springboot.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


/**
 * 主程序启动类
 */
@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages={"com.springboot.swagger","base"})
public class SwaggerApplication {
	public static void main(String[] args) {
		ApplicationContext ctx =  SpringApplication.run(SwaggerApplication.class, args);
		//@Controller注解的bean：
		String[] beanControllerNames =  ctx.getBeanNamesForAnnotation(Controller.class);
		System.out.println("Controller注解beanNames个数："+beanControllerNames.length);
		for(String bn:beanControllerNames){
			System.out.println("Controller注解："+bn);
		}
		//@service注解的bean：
		String[] beanServiceNames =  ctx.getBeanNamesForAnnotation(Service.class);
		System.out.println("Service注解beanNames个数："+beanServiceNames.length);
		for(String bn:beanServiceNames){
			System.out.println("Service注解："+bn);
		}

		//@Repository注解的bean：
		String[] beanRepositoryNames =  ctx.getBeanNamesForAnnotation(Repository.class);
		System.out.println("Repository注解beanNames个数："+beanRepositoryNames.length);
		for(String bn:beanRepositoryNames){
			System.out.println("Repository注解："+bn);
		}
	}
}
