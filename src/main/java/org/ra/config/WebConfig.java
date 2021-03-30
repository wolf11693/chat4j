package org.ra.config;

import org.ra.listner.MySessionListner;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

//	 @Bean
//	   public ServletListenerRegistrationBean<MySessionListner> getSessionListener() {
//		   ServletListenerRegistrationBean<MySessionListner> listenerRegBean = new ServletListenerRegistrationBean<>();
//		   listenerRegBean.setListener(new MySessionListner());
//
//		   return listenerRegBean;
//	   }
}
