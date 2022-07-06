package me.eun.stream;

import java.util.Arrays;
import java.util.stream.Stream;

public class StreamExample3 {
	public static void main(String[] args) {
		String[]arr = {"신짱구" ,"김철수" ,"채성아","나미리"};
		
		//배열을 스트림으로 생성
		Stream<String> stream = Arrays.stream(arr);
		stream.forEach(System.out::println);
		
	}
}
