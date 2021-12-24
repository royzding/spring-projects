package com.sample.microservices.common.chapter.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Platypus {
	       String name;
	       int beakLength;
	  
	       public String toString() {return "" + beakLength;}
	  
	      public static void main(String[] args) {
	         Platypus p1 = new Platypus("Paula", 30);
	         Platypus p2 = new Platypus("Peter", 5);
	         Platypus p3 = new Platypus("Peter", 7);
	  
	         List<Platypus> list = Arrays.asList(p1, p2, p3);
	  
	         Collections.sort(list, Comparator.comparing(Platypus::getName));
	  
	         System.out.println(list);
	         
	         Collections.sort(list, Comparator.comparing(Platypus::getBeakLength));
	  
	         System.out.println(list);
	         
	         Collections.sort(list, Comparator.comparing(Platypus::getBeakLength)
	        	       .reversed());
	  
	         System.out.println(list);
	         
	         Collections.sort(list, Comparator.comparing(Platypus::getName)
	        	       .thenComparingInt(Platypus::getBeakLength)
	        	       .reversed());
	  
	         System.out.println(list);
	         
	         var map = Map.of(1,2, 3, 6);
	          var listx = List.copyOf(map.entrySet());
	         
	          List<Integer> one = List.of(8, 16, 2);
	          var copy = List.copyOf(one);
	          var copyOfCopy = List.copyOf(copy);
	          var thirdCopy = new ArrayList<>(copyOfCopy);
	          //listx.replaceAll(x -> x * 2);
	          one.replaceAll(x -> x * 2);
	          thirdCopy.replaceAll(x -> x * 2);
	          
	          System.out.println(thirdCopy);


	      }
 }

