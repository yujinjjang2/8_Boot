package com.kh.test.todo.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.test.todo.model.vo.Todo;

@Mapper
public interface TodoMapper {

	List<Todo> selectTodoList();

}
