package com.kh.test.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.test.todo.model.service.TodoService;
import com.kh.test.todo.model.vo.Todo;

@Controller
public class MainController {
	
	@Autowired
	private TodoService service;
	
	@RequestMapping("/")
	public String selectTodoList(Model model) {
		
		List<Todo> todoList = service.selectTodoList();
		
		System.out.println(todoList);
		
		model.addAttribute("todoList", todoList);
		
		return "todo/todoList";
		
	}
	
	
	@GetMapping("/loginError")
	public String loginError(RedirectAttributes ra) {
		
		ra.addFlashAttribute("message", "로그인 후 이용해주세요"); // 메세지 추가
		
		return "redirect:/"; // 메인페이지로 이동
	}
	
}
