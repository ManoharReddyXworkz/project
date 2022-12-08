package com.xworkz.login.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Component
@ComponentScan("com.xworkz")
public class SpringConfiguration {

	public SpringConfiguration() {
		System.out.println("calling default constructor of:"+this.getClass().getSimpleName());
	}
	
	@Bean
	public ViewResolver viewResolver() {
		System.out.println("Calling ViewResolver method");
		return new InternalResourceViewResolver("/",".jsp");
	}
	
	@Bean
	public MultipartResolver multipartResolver() {
		System.out.println("Running MultipartResolver method");
		return new StandardServletMultipartResolver();
	}
}
