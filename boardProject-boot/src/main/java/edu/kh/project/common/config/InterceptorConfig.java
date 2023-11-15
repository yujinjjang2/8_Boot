package edu.kh.project.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import edu.kh.project.common.interceptor.BoardTypeInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{

	@Bean // 개발자 @Bean 등록, 관리 Spring
	public BoardTypeInterceptor boardTypeInterceptor() {
		return new BoardTypeInterceptor();
	}
	
//	@Bean // 개발자 @Bean 등록, 관리 Spring
//	public BoardTypeInterceptor 다른인터셉터() {
//		return new 다른인터셉터();
//	}

	@Override // 인터셉터 사용하려면 등록해줘야 함!
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor( boardTypeInterceptor() )
		.addPathPatterns("/**") // 가로챌 경로 지정(여러개 작성시 , 로 구분)
		.excludePathPatterns("/css/**", "/images/**", "/js/**"); // 가로 채지 않을 경로
		
		
		
//		registry.addInterceptor( 다른인터셉터() )
//		.addPathPatterns("/**") // 가로챌 경로 지정(여러개 작성시 , 로 구분)
//		.excludePathPatterns("/css/**", "/images/**", "/js/**"); // 가로 채지 않을 경로
		
	}
	
	
	
	
}
