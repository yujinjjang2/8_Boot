package com.kh.test.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.kh.test.todo.model.service.TodoService;
import com.kh.test.todo.model.vo.Todo;

@Controller
public class TodoController {
	
	@Autowired
	private TodoService service;
	
	public String selectTodoList(Model model) {
		
		List<Todo> todoList = service.selectTodoList();
		
		System.out.println(todoList);
		
		model.addAttribute("todoList", todoList);
		
		return "todo/todoList";
		
	}

}
