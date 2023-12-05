package com.kh.test.todo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.test.todo.model.dao.TodoMapper;
import com.kh.test.todo.model.vo.Todo;

@Service
public class TodoServiceImpl implements TodoService{
	
	@Autowired
	private TodoMapper mapper;

	@Override
	public List<Todo> selectTodoList() {
		return mapper.selectTodoList();
	}
	
	

}
