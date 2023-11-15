package edu.kh.project.common.interceptor;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import edu.kh.project.board.model.service.BoardService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Interceptor : 요청/응답을 가로채는 객체
// Client <-> (Filter) <-> Dispatcher Servlet <-> (Interceptor) <-> Controller

// Filter : 전반적으로 쓰일 보안/인증/인가 관련 작업, 문자열 인코딩, 이미지 데이터 압축..
// Interceptor : 요청에 대한 '데이터 가공' -> Controller로 넘겨주기 위한 정보/데이터 가공,
//				 세부적으로 쓰일 보안/인증


// Handle = '처리' 의미
public class BoardTypeInterceptor implements HandlerInterceptor {
	
	/* preHandle : 전처리		      Dispathcer Servlet -> Contoller 사이
	 * postHandle : 후처리	      Controller -> Dispathcer Servlet 사이
	 * afterCompletion : 뷰 완성 후  View Resolver -> Dispathcer Servlet 사이
	 * */
	
	@Autowired
	private BoardService service;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// application scope 내장 객체 얻어오기
		ServletContext application = request.getServletContext();
		
		// application scope에 BOARD_TYPE이 조회되어 세팅되지 않았다면
		// -> 서버 시작 후 누구도 요청을 한적이 없을 경우
		if(application.getAttribute("boardTypeList") == null) {
			
			// 조회 서비스 호출
			System.out.println("BOARD_TYPE 조회 서비스 호출");
			
			List<Map<String, Object>> boardTypeList
				= service.selectBoardTypeList();
			
			System.out.println("boardTypeList: " + boardTypeList);
			
			// application scope 세팅
			application.setAttribute("boardTypeList", boardTypeList);
			
	
		}
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

	
}
