package me.eun.mapper;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import me.eun.AppTest;
import me.eun.model.MemberVO;

public class MemberMapperTest extends AppTest {

	@Autowired
	MemberMapper mapper;
	
	@Test
	public void selectMember() {
		MemberVO read = mapper.read("admin");
		read.getAuthList().forEach(System.out::println);
	}

}
