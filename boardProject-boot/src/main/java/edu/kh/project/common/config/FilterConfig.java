package edu.kh.project.common.config;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.kh.project.common.filter.BoardFilter;
import edu.kh.project.common.filter.LoginFilter;

@Configuration // 설정 관련 파일에서 꼭 작성해줘야 하는 어노테이션!
public class FilterConfig {

	@Bean
	public FilterRegistrationBean<LoginFilter> loginFilter(){
		
		FilterRegistrationBean<LoginFilter> resiRegistrationBean = new FilterRegistrationBean<LoginFilter>();
		
		resiRegistrationBean.setFilter(new LoginFilter());
		
		String[] url = {"/myPage/*", "/board2/*"}; // , 로 추가 계속 가능함!
		resiRegistrationBean.setUrlPatterns(Arrays.asList(url)); // url 패턴 여러 개 지정  --> 컬렉션 중 리스트 형태로 만들어줌
		resiRegistrationBean.setName("loginFilter"); // 이름
		resiRegistrationBean.setOrder(1); // 여러 필터가 있을 때 순서
		return resiRegistrationBean;
	}
	
	@Bean
	public FilterRegistrationBean<BoardFilter> boardFilter(){
		
		FilterRegistrationBean<BoardFilter> resiRegistrationBean = new FilterRegistrationBean<BoardFilter>();
		
		resiRegistrationBean.setFilter(new BoardFilter());
		
		String[] url = {"/board/*", "/board2/*"}; // , 로 추가 계속 가능함!
		resiRegistrationBean.setUrlPatterns(Arrays.asList(url)); // url 패턴 여러 개 지정  --> 컬렉션 중 리스트 형태로 만들어줌
		resiRegistrationBean.setName("boardFilter"); // 이름
		resiRegistrationBean.setOrder(2); // 여러 필터가 있을 때 순서
		return resiRegistrationBean;
	}
	
}
