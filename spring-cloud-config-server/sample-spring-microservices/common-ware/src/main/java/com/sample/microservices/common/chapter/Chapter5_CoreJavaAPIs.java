package com.sample.microservices.common.chapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.lang.System.*;

public class Chapter5_CoreJavaAPIs {

	public static void main(String[] args) {

		testTemplet();
		testArray();
		testArrayList();
		testWrapperClasses();
		testArrayAndListConvertion();
		
		testSets();
		testMaps();
		testMathAPIs();
		
		testTest();

	}

	private static void testTemplet() {
		
	}
	
	private static void testArray() {
		
		out.println("--------------testArray-----------");

		int[] ia0 = new int[] {3,1,2};
		int[] ia1 = new int[] {1,2};
		int[] ia2 = new int[] {1,2};
		
		Arrays.sort(ia0);
		out.println(Arrays.toString(ia0));

		out.println(Arrays.binarySearch(ia1, 1));

		out.println(Arrays.equals(ia1,ia2));
		out.println(Arrays.compare(ia1,ia2));
		out.println(Arrays.mismatch(ia1,ia2));
		

	}
	
	private static void testArrayList() {
		out.println("--------------testArrayList-----------");

/*		
		boolean add(E element);
		void add(int index, E element);
		boolean remove(Object object);
		E remove(int index);
		E set(int index, E newElement);
		boolean isEmpty();
		int size();
		void clear();
		boolean contains(Object object);
		boolean equals(Object object);
*/
		//sorting:
		List<Integer> numbers = new ArrayList<>();
		numbers.add(99);
		numbers.add(5);
		numbers.add(81);
		Collections.sort(numbers);
		out.println(numbers); // [5, 81, 99]
		
		List<Integer> l1 = new ArrayList<>();
		l1.add(1);
		l1.add(2);

		List<Integer> l2 = new ArrayList<>();
		l2.add(1);
		l2.add(2);
		
		out.println(l1.equals(l2));

	}
	
	private static void testWrapperClasses() {
		out.println("--------------testWrapperClasses-----------");
/*		
 		Boolean,Byte,Short,Integer,Long,Float,Double,Character
 		
 		Integer = Integer.valueOf(int);		
 		int = Integer.intValue();
 		
 		Integer = Integer.Integer.valueOf(String);
 		int = Integer.parseInt(String);
 		
 		//autoboxing/unboxing
 		
 		//unboxing a null will cause NullPointerException.

*/
		
	}
	
	private static void testArrayAndListConvertion() {

		out.println("--------------testArrayAndListConvertion-----------");
/*	

		List -> Array:
		
	 	List<String> list = new ArrayList<>();
		Object[] objArray = list.toArray();
		String[] strArray = list.toArray(new String[0]);
		
		Array -> List
		
		1. backed List:
		List<String> list = Arrays.asList(strArray); // returns fixed size list.
	
		String[] array = { "hawk", "robin" };     	// [hawk, robin]
		List<String> list = Arrays.asList(array); 	// returns fixed size list
		System.out.println(list.size());         	// 2
		list.set(1, "test");                     	// [hawk, test]
		array[0] = "new";                        	// [new, test]
		System.out.print(Arrays.toString(array));	// [new, test]
		list.remove(1);     						// throws UnsupportedOperationException
	
		2. immutable List:
		List<String> list = List.of(array);      	// returns immutable list
	
		String[] array = { "hawk", "robin" };       // [hawk, robin]
		List<String> list = List.of(array);      	// returns immutable list
		System.out.println(list.size());            // 2
		array[0] = "new";
		System.out.println(Arrays.toString(array)); // [new, robin]
		System.out.println(list);                   // [hawk, robin]
		list.set(1, "test");      					// throws UnsupportedOperationException
	
		3. using Varargs to create a Fixed-Size List
		
		List<String> list1 = Arrays.asList("one", "two");
		List<String> list2 = List.of("one", "two");
	
		//try to change the list.
		List<String> fixedSizeList  = Arrays.asList("a", "b", "c");
		List<String> expandableList = new ArrayList<>(fixedSizeList);

*/
		
	}

	private static void testSets() {
		out.println("--------------testSets-----------");
		Set<Integer> set = new HashSet<>();
		out.println(set.add(66)); // true
		out.println(set.add(66)); // false
		out.println(set.size()); // 1
		set.remove(66);
		out.println(set.isEmpty()); // true
	}
	
	private static void testMaps() {
		out.println("--------------testMaps-----------");
		
		Map<String, String> map = new HashMap<>();
		
		map.put("koala", "bamboo");
		
		String food = map.get("koala"); // bamboo
		
		String other = map.getOrDefault("ant", "leaf"); // leaf
		
		for (String key: map.keySet()) {			
			out.println(key + " " + map.get(key)); // koala bamboo			
		}
		
		for (String value: map.values()) {			
			out.println(value); // bamboo			
		}
	}
	
	private static void testMathAPIs() {
		
		out.println("--------------testMathAPIs-----------");
		//max() and min()
		
		//double min(double a, double b)
		//float min(float a, float b)
		//int min(int a, int b)
		//long min(long a, long b)

		int first = Math.max(3, 7);  // 7
		int second = Math.min(7, -9);  // -9

		//round()
		//long round(double num)
		//int round(float num) 
		long high = Math.round(123.50); // 124
		int fromFloat = Math.round(123.45f); // 123

		//pow()
		//double pow(double number, double exponent)
		double squared = Math.pow(5, 2); // 25.0

		//random()
		//double random()
		double num = Math.random();
		
		
	}
	
	private static void testTest() {
		
		out.println("--------------testTest-----------");
		//3.
		List<String> gorillas = new ArrayList<>();
		  for(var koko : gorillas)
		     System.out.println(koko);
		   
		  var monkeys = new ArrayList<>();
		  for(var albert : monkeys)

			  System.out.println(albert);
		   
		  List chimpanzees = new ArrayList<Integer>();
		  chimpanzees.add("1");
		  for(var ham : chimpanzees) {
			  if(ham instanceof Integer) {
				  out.println("ham is a Integer");
			  } else {
				  out.println("ham is an Object");
			  }
			  
		  }
		  
		//10.
		  var letters = new StringBuilder("abcdefg");

		  out.println(letters.substring(1,2));

		//12.
		  //StringBuilder b = "rumble";
		  
		//15.
		  int[][] java = new int[2][];
		  //int[][] java = new int[][];
		  
		//16.
		  List<String> l = new ArrayList<>(0);
		  //l.remove(0); //runtime exception.
		  
		//17.
		  var list = new ArrayList<String>();
		  list.add("one");
		  list.add("two");
		  //list.add(7);
		  for(var s : list)  
			  out.println(s);
		  
		//23.
		  List<String> one = new ArrayList<String>();
		  one.add("abc");
		  List<String> two = new ArrayList<>();
		  two.add("abc");
		  if (one == two)
			     System.out.println("A");
			  else if (one.equals(two))
			     System.out.println("B");
			  else
			     System.out.println("C");

		//25.
		  String[] s1 = { "Camel", "Peacock", "Llama"};
		  String[] s2 = { "Camel", "Llama", "Peacock"};
		  String[] s3 = { "Camel"};
		  String[] s4 = { "Camel", "D"};
		  String[] s5 = { "Camel", null};

		  out.println("s1.c.s2 = " + Arrays.compare(s1, s2));
		  out.println("s3.m.s4 = " + Arrays.mismatch(s3, s4));
		  out.println("s3.c.s5 = " + Arrays.compare(s3, s5));
		  out.println(Arrays.compare(s4, s4));
		  out.println(Arrays.mismatch(s4, s4));

/*
		1.	f
		2.	acd
		3.	ace[acf]
		4.	b
		5.	g
		6.	b
		7.	abf
		8.	ade
		9.	cf
		10.	a[ag]
		11.	a
		12.	f
		13.	ac
		14.	cef
		15.	ah
		16.	aeg[afg]
		17.	f
		18.	c
		19.	af
		20.	bce[bce]
		21.	d
		22.	abc[abd]
		23.	b
		24.	de
		25.	bd[abd]

*/
		
	}
	
	
}
