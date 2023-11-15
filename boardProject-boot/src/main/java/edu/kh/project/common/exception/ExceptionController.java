package edu.kh.project.common.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // 예외처리용 Controller에 붙이는 어노테이션
public class ExceptionController {

	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		e.printStackTrace(); // 에러 내용 콘솔에 출력
		return "error/500"; // /templates/error/500.html
		// 만약 return 구문이 없다면 흰 화면이 뜸! 500.html 페이지로 자동 mapping 해주지 않음
	}
	
}
