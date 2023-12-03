package edu.kh.practice.member.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.practice.member.model.dto.Member;

@Repository
public class MemberDAO {
	
	@Autowired
	private MemberMapper mapper;
	
	public Member login(Member member) {
		return mapper.login(member);
	}

}
