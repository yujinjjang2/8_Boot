package com.kh.test.todo.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Todo {
	
	private int todoNo;
	private String todoTitle;
	private String todoContent;

}
