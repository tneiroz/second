package me.eun.mapper;

import me.eun.model.MemberVO;

public interface MemberMapper {
	
	MemberVO read(String userId);
}
