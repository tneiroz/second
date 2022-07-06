package me.eun.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import me.eun.stream.domain.Person;

public class StreamMap2 {
	public static void main(String[] args) {
		List<Person> personList = new ArrayList<>();
		personList.add(new Person(1L,"신짱구",5));
		personList.add(new Person(2L,"원장선생님",35));
		personList.add(new Person(3L,"봉미선",28));
		
		
		//모든 나이의 합계 
		int ageSum = personList.stream()
			.mapToInt(Person::getAge)
			.sum();   //최종연산 
		System.out.println(ageSum);
		
		//나이의 평균
		double asDouble = personList.stream()
		  .mapToInt(Person::getAge)
		  .average().getAsDouble();
		System.out.println(asDouble);
	}
}
