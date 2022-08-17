package com.sample.microservices.common.test;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test {

	public static void main(String[] args) {
		Stream<String> s = Stream.of("duck", "duck", "duck", "goose", null, null);
		s.filter(Objects::nonNull).sorted(Comparator.reverseOrder()).distinct()
		   .forEach(System.out::print); // duckgoose
		
		Stream<String> objStreamx1 = Stream.of("penguin", "fish");
		IntStream intStreamx1 = objStreamx1.mapToInt(String::length);
		System.out.println(intStreamx1.sum());
		
		//System.out.println(objStreamx1.count());
		
		//Stream.iterate("", s1->s1+"1").limit(5).forEach(x->System.out.print("["+x+"]"));

		Stream.generate(() -> "1")
	       .limit(10)
	       .peek(System.out::println).forEach(System.out::println);
		
		LocalDateTime dt = LocalDateTime.of(2020, Month.OCTOBER, 20, 6, 15, 30);
		 
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss");
		System.out.println(dt.format(formatter1));
		 
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MM_yyyy_-_dd");

		Set<Integer> favNumbers = 
				   new CopyOnWriteArraySet<>(List.of(4,3,42));
				for(Integer n: favNumbers) {
				   System.out.print(n + " ");
				   favNumbers.add(9);
				}
				System.out.println();
				System.out.println("Size: " + favNumbers.size());
				
				favNumbers.forEach(System.out::println);


	}
}
