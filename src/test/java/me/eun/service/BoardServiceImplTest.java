package me.eun.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import me.eun.AppTest;

public class BoardServiceImplTest extends AppTest{

	@Autowired
	BoardService service;
	
	@Test
	public void getListTest() {
//		List<Board> list = service.getList();
//		assertEquals(4, list.size());
	}

}
