package com.liqihua.tally.config.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liqihua.tally.config.mvc.interceptor.CorsInterceptor;
import com.liqihua.tally.config.mvc.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * mvc相关配置
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
	protected String[] corsArr = new String[]{"/**"};

	@Bean
	CorsInterceptor corsInterceptor() {
		return new CorsInterceptor();
	}

	@Bean
	LoginInterceptor loginInterceptor(){
		return new LoginInterceptor();
	}

	/**
	 * 添加拦截器
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//跨域拦截器
		registry.addInterceptor(corsInterceptor()).addPathPatterns(corsArr);
		//登录校验拦截器
		registry.addInterceptor(loginInterceptor()).addPathPatterns("/api/logPayApiController/**","/api/countApiController/**");
		//registry.addInterceptor(loginInterceptor()).addPathPatterns("/**").excludePathPatterns("/api_doc/**","/api/loginApiController/**","/api/testApiController/**");
		super.addInterceptors(registry);
	}






	/**
	 * 设置controller的字段json转换
	 * WebMvcConfigurationSupport方式
	 * @param converters
	 */
	@Override
	protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		//设置日期格式
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		objectMapper.setDateFormat(smt);
		converter.setObjectMapper(objectMapper);
		//设置支持的类型
		List<MediaType> list = new ArrayList<MediaType>();
		list.add(MediaType.APPLICATION_JSON_UTF8);
		list.add(MediaType.APPLICATION_JSON);
		list.add(MediaType.APPLICATION_ATOM_XML);
		list.add(MediaType.APPLICATION_FORM_URLENCODED);
		list.add(MediaType.APPLICATION_OCTET_STREAM);
		list.add(MediaType.APPLICATION_PDF);
		list.add(MediaType.APPLICATION_RSS_XML);
		list.add(MediaType.APPLICATION_XHTML_XML);
		list.add(MediaType.APPLICATION_XML);
		list.add(MediaType.IMAGE_GIF);
		list.add(MediaType.IMAGE_JPEG);
		list.add(MediaType.IMAGE_PNG);
		list.add(MediaType.TEXT_EVENT_STREAM);
		list.add(MediaType.TEXT_HTML);
		list.add(MediaType.TEXT_MARKDOWN);
		list.add(MediaType.TEXT_PLAIN);
		list.add(MediaType.TEXT_XML);
		converter.setSupportedMediaTypes(list);

		converters.add(converter);
		super.configureMessageConverters(converters);
	}


	/**
	 * 静态路径映射
	 * 继承WebMvcConfigurationSupport时需要配置
	 */
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}



}
