package me.eun.stream;

import java.util.Arrays;

public class DistinctExample {
	public static void main(String[] args) {
	   String[]test = {"1","1","1","2","2","2","3","4","4"};
	   Arrays.stream(test).distinct().forEach(System.out::println);
}
}
