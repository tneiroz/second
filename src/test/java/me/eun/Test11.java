package me.eun;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class Test11 {

	@Test
	public void Test00() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		Date date = new Date();
		String formDate = sdf.format(date);
		System.out.println(formDate);
		String test = formDate.replace("-", File.separator);
		System.out.println(test);
	}
	
	@Test
	public void test01 () {
		String contentType = "image...";
		System.out.println(contentType.startsWith("image"));
	}
}
