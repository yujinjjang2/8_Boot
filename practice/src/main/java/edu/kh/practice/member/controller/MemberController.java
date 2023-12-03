package edu.kh.practice.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.practice.member.model.dto.Member;
import edu.kh.practice.member.model.service.MemberService;

@Controller
@RequestMapping("/member")
@SessionAttributes({"loginMember"})
public class MemberController {

	@Autowired
	private MemberService service;
	
	@PostMapping("/login")
	public String login(Member member, Model model, RedirectAttributes ra) {
		
		Member loginMember = service.login(member);
		
		System.out.println(loginMember);
		
		String message = "";
		
		if(loginMember != null) {
			
			message = "로그인 성공";
			model.addAttribute("loginMember", loginMember);
			
		} else {
			
			message = "로그인 실패";
			
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		
		status.setComplete();
		
		return "redirect:/";
		
	}
	
}
