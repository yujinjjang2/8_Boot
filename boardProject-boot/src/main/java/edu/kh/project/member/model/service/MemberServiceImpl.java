package edu.kh.project.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.member.model.dao.MemberDAO;
import edu.kh.project.member.model.dto.Member;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDAO dao;
	
	@Autowired // bean으로 등록된 객체 중 타입이 일치하는 객체를 DI(의존성 주입)
	private BCryptPasswordEncoder bcrypt;
	
	// 암호화가 필요한 곳? 로그인, 회원가입
	
	@Override
	public Member login(Member inputMember) {
		
		// dao 메서드 호출
		Member loginMember = dao.login(inputMember);
		
		if(loginMember != null) { // 아이디가 일치하는 회원이 조회된 경우
			
			if(bcrypt.matches(inputMember.getMemberPw(), loginMember.getMemberPw())) {
				
				// 비밀번호를 유지하지 않기 위해서 로그인 정보에서 제거
				loginMember.setMemberPw(null);
				
			} else { // 다를 경우
				loginMember = null;
			}
			
		} 
		
		return loginMember;
	}

	
	// 회원 가입 서비스
	@Transactional
	@Override
	public int signUp(Member inputMember) {
		
		// 비밀번호 암호화 (Bcrypt) 후 다시 inputMember 세팅
		String encPw = bcrypt.encode(inputMember.getMemberPw());
		inputMember.setMemberPw(encPw);
		
		return dao.signUp(inputMember);
	}

}