package edu.kh.project.common.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource("classpath:/config.properties") // config.properties 이메일 인증 이용하겠다!
public class EmailConfig {

	// @Value : properties 파일에서 key가 일치하는 부분의 value를 얻어와 대입
	@Value("${spring.mail.username}")
	private String username; // o3odw98@gmail.com
	
	@Value("${spring.mail.password}")
	private String password; // hsrnftupgpomuqjh
	
	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		
		// 메일 관련 설정
		mailSender.setUsername(username); // o3odw98@gmail.com
		mailSender.setPassword(password); // hsrnftupgpomuqjh
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		
		Properties prop = new Properties(); // 속성을 묶을 수 있는 객체 생성
		
		prop.setProperty("mail.transport.protocol", "smtp"); // -> 추가된 속성 세팅(깊게 알 필요 X)
		prop.setProperty("mail.smtp.auth", "true");
		prop.setProperty("mail.smtp.starttls.enable", "true");
		prop.setProperty("mail.debug", "true");
		prop.setProperty("mail.smtp.ssl.trust","smtp.gmail.com");
		prop.setProperty("mail.smtp.ssl.protocols","TLSv1.2");
		
		mailSender.setJavaMailProperties(prop);
		
		
		return mailSender; // 반환된 객체가 bean 등록됨
	}
	
	
}
