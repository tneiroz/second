package me.eun.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import me.eun.stream.domain.Person;

public class StreamMap1 {
	private static List<Integer> collect;

	public static void main(String[] args) {
		List<Person> personList = new ArrayList<>();
		personList.add(new Person(1L,"신짱구",5));
		personList.add(new Person(2L,"원장선생님",35));
		personList.add(new Person(3L,"봉미선",28));
		
		//람다식에서 반환타입이 있는 경우 중괄호 생략
		//반드시 세미콜론 , return 까지 생략해야됨
		List<String> personName = personList.stream()
		.map(p-> p.getName()+"_2022/07/06")
		.collect(Collectors.toList());   //collectors._____ 무엇을 하냐에 따라 받을 수 있는게 여러개 
		
		//나이로 찾기
		List<Integer> personAge = personList.stream()
		.map(Person::getAge)
		.collect(Collectors.toList());
		System.out.println(personAge);
		
		personList.stream()
		.map(Person::getAge)   // [5,35,28]
		.filter(age -> age>=19).forEach(System.out::println); // [35,28]     ★ 람다식은 중괄호, 세미콜론 다 삭제 ★
		
		
		//19세 이상이 몇명인지 알려주는 메소드
		long count = personList.stream()
		.map(Person::getAge)
		.filter(age -> age>=19)
		.count();
		System.out.println(count);
	}
}
