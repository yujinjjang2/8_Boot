package edu.kh.practice.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.practice.member.model.dao.MemberDAO;
import edu.kh.practice.member.model.dto.Member;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO dao;

	@Override
	public Member login(Member member) {
		return dao.login(member);
	}

}
