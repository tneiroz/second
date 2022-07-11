package me.eun.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import me.eun.mapper.MemberMapper;
import me.eun.model.MemberVO;

public class CustomUserDetaliService implements UserDetailsService{

	
	@Autowired
	MemberMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO memberVO = mapper.read(username);
		return memberVO != null ? new CustomUser(memberVO): null;
	}

}
