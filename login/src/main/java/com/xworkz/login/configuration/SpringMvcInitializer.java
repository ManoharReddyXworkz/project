package com.xworkz.login.configuration;

import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer implements WebMvcConfigurer{
	

	Class[] getServletConfigClasses = {SpringConfiguration.class,DBConfiguration.class};
	String[] mappings = {"/"};
	
	public SpringMvcInitializer() {
		System.out.println("calling default constructor of:"+this.getClass().getSimpleName());
	}
	@Override
	protected Class<?>[] getRootConfigClasses() {
		System.out.println("Calling getRootConfigClasses Method");
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		System.out.println(" Calling getServletConfigClasses Method");
		return getServletConfigClasses;
	}

	@Override
	protected String[] getServletMappings() {
		System.out.println(" Calling getServletMappings method");
		return mappings;
	}
@Override
public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	System.out.println("calling onfigureDefaultServletHandling method");
	configurer.enable();
} 
}
