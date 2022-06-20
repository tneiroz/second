package me.eun.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import me.eun.AppTest;

public class ReplyServiceImplTest extends AppTest{

	@Autowired
	ReplyService service;
	
	@Test
	public void test() {
		service.get(4L);
		
	}

}
