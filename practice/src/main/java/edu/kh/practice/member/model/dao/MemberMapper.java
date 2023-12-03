package edu.kh.practice.member.model.dao;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.practice.member.model.dto.Member;

@Mapper
public interface MemberMapper {

	Member login(Member member);

}
