package com.adicse.facturador.config;


import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

//@Configuration
//@EnableWebMvc
public class WebConfig   {
	
	public InternalResourceViewResolver viewResolver(){
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("classpath:/static/");
		viewResolver.setSuffix(".*");
		//viewResolver.setReportDataKey("datasource");
		viewResolver.setViewNames("*");
		viewResolver.setOrder(0);

	    return viewResolver;		
		
	}


 
}
