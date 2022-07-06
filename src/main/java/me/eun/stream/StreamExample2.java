package me.eun.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StreamExample2 {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("짱구");
		list.add("신짱구");
		list.add("떡잎마을");
		list.add("스트림생성");
	
		
		//스트림 생성
		Stream<String> stream = list.stream();
			
		//파라미터로 람다식 전달
		//파라미터 타입 생략 가능 
		//파라미터가 하나이면 소괄호 생략 가능 (두개면 생략 못한다)   (e) <- 이부분 지워도 됨  변수명은 사용자 마음대로 지정
		stream.forEach( (e) -> {      
				System.out.println(e);
				System.out.println("길이"+e.length());
				System.out.println("=========================");
				
			});//forEach end
		System.out.println("람다식");
		//Stream<String> stream2 = list.stream();
		
		//stream2.forEach(e-> {System.out.println(e);});
		//중괄호 내 코드가 한 줄인 경우  = 중괄호 생략 가능    = {}; ★ 세미콜론도 반드시 삭제해야 됨 ★
		list.stream().forEach(e-> System.out.println(e));    //**
		
		//메소드 참조
		System.out.println("메소드 참조");
		Stream<String> stream3 = list.stream();
		stream3.forEach(System.out::println);          //** 부분과 결국 동일한 코드 
		
	}//main end
}//StreamExample end
