package com.kh.test.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.test.user.model.service.UserService;
import com.kh.test.user.model.vo.User;

@Controller
@RequestMapping("/member")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping("/info")
	public String selectUserInfo(User user, Model model) {
		
		User selectUser = service.selectUserInfo(user);
		
		System.out.println(selectUser);
		
		String path = "";
		
		if(selectUser != null) { // 조회 성공
			
			model.addAttribute("selectUser", selectUser);
			path = "searchSuccess";
			
		} else { // 조회 실패
			
			path = "searchFail";
			
		}
		
		return path;
	}
	
}