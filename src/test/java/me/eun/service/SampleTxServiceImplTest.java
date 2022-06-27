package me.eun.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import me.eun.AppTest;

public class SampleTxServiceImplTest extends AppTest {

	@Autowired
	SampleTxService service;
	
	@Test
	public void testTx() {
		service.addData("HELLOWORLD");
	}

}
