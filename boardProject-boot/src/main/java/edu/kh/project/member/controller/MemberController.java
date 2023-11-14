package edu.kh.project.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.member.model.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/member")
@Controller
@SessionAttributes({"loginMember"})
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@PostMapping("/login")
	public String login(Member inputMember, Model model,
						@RequestHeader("referer") String referer,
						RedirectAttributes ra,
						@RequestParam(value="saveId", required = false) String saveId,
						HttpServletResponse resp
						) {

		
		// 로그인 서비스 호출
		Member loginMember = service.login(inputMember);
		
		String path = "redirect:";
		
		if(loginMember != null) {
			path += "/";  
		
			model.addAttribute("loginMember", loginMember);
		
			Cookie cookie = new Cookie("saveId", loginMember.getMemberEmail());
			
			if(saveId != null) { // 체크가 되었을 때
				
				// 한 달(30일) 동안 유지되는 쿠키 생성
				cookie.setMaxAge(60*60*24*30); // 초단위 지정
				
				
			} else { // 체크가 안되었을 때 
				
				cookie.setMaxAge(0);
				
			}
			
			cookie.setPath("/");
		
			resp.addCookie(cookie);
			
			
		} else { // 로그인 실패
			path += referer;
			ra.addFlashAttribute("message", "아이디 또는 비밀번호 불일치");
		}
		
		return path;
	}
	
	
	@GetMapping("/logout")
	public String logout(SessionStatus status, HttpSession session) {
		status.setComplete();
		return "redirect:/";
	}
	
	

	// 회원 가입 페이지 이동
	@GetMapping("/signUp")
	public String signUp() {
		
		return "member/signUp";
	}
	
	
	// 회원 가입 진행
	@PostMapping("/signUp")
	public String signUp(Member inputMember,
						String[] memberAddress,
						RedirectAttributes ra ) {	
		
		// Member inputMember : 커맨드 객체 (제출된 파라미터가 저장된 객체)
		
		// String[] memberAddress : 
		//	input name="memberAddress" 3개가 저장된 배열
		
		// RedirectAttributes ra : 
		// 리다이렉트 시 데이터를 request scope로 전달하는 객체
		
		System.out.println("주소 : " + inputMember.getMemberAddress());
		
		// 01234,서울 성동구 어쩌구,2층
		// 만약에 입력하지 않았다면 ,, 이런식으로 구분자만 나옴
		// 주소를 입력하지 않은 경우 null 로 변경 
		if(inputMember.getMemberAddress().equals(",,")) {
			inputMember.setMemberAddress(null);
			
		}else {
			// String.join("구분자", String[])
			// 배열의 요소를 하나의 문자열로 변경
			// 요소 사이에 구분자를 추가함
			String addr = String.join("^^^", memberAddress);
			inputMember.setMemberAddress(addr);
			
		}
		
		
		
		// 회원 가입 서비스 호출
		int result = service.signUp(inputMember);
		
		
		// 가입 성공 여부에 따라서 주소 결정
		String path = "redirect:";
		String message = null;
		
		if(result > 0) { // 가입 성공
			path += "/"; // 메인페이지로
			
			message = inputMember.getMemberNickname() + "님의 가입을 환영합니다";
			
		}else { // 가입 실패
			
			// 회원 가입 페이지
			//path += "/member/signUp"; // 절대경로
			path += "signUp"; // 상대 경로
			
			message = "회원 가입 실패";
			
		}
		
		// 리다이렉트 시 session에 잠깐 올라갔다 request로 복귀하도록 세팅
		ra.addFlashAttribute("message", message);
		
		return path;
	}
	
	
	

}
