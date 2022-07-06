package me.eun.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class StreamExample1 {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("짱구");
		list.add("신짱구");
		list.add("떡잎마을");
		list.add("스트림생성");
	
		
		//스트림 생성
		Stream<String> stream = list.stream();
			
		//파라미터로 익명 구현 객체 전달	
		stream.forEach(new Consumer<String>() {   //추상메소드 구현 해줘야딤 interface 라서   ForEach는 반환타입이 없는 반복문
			@Override
			public void accept(String e) {      //e 는 element약자
				System.out.println(e);
				System.out.println("길이"+e.length());
				System.out.println("=========================");
			}
		});//forEach end
		
		
	}//main end
}
