package me.eun.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.eun.model.SampleVO;

@RestController
@RequestMapping("/sample")
public class SampleController {

	@GetMapping(value= "/getText" , produces = "text/plain;charset=utf-8")
	public String getText() {
		return "안녕하세요";
		
	}
	@GetMapping(value="/getSample",produces= {MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE})
	public SampleVO getSample() {
		return new SampleVO(112,"홍길","홍");		
	}
}
