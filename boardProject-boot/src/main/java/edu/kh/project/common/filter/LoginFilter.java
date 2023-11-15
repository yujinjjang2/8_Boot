package edu.kh.project.common.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request; // 다운캐스팅
		HttpServletResponse resp = (HttpServletResponse)response;
		
		HttpSession session = req.getSession();
		
		if ( session.getAttribute("loginMember") == null ) { // 로그인이 되어있지 않은 경우
			// (cf) 타임리프 -> session 화면에서 지울 수 없음
			resp.sendRedirect("/loginError");
			
		} else { // 로그인 된 경우
			chain.doFilter(request, response);
			// 다음 필터 또는 컨트롤러로 이동
			
		}
		
	}

}
