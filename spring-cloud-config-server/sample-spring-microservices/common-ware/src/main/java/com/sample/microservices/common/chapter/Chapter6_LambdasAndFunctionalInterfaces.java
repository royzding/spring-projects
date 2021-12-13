package com.sample.microservices.common.chapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static java.lang.System.*;

public class Chapter6_LambdasAndFunctionalInterfaces {

	public static void main(String[] args) {

		testTemplet();
		testFunctionalInterface();
		testCallingLambdas();
		
		testTest();

	}

	private static void testTemplet() {
		
	}
	
	private static void testFunctionalInterface() {
		
		out.println("--------------testFunctionalInterface-----------");

		//-----------interfaces that have only one abstract method are called functional interfaces.
		
		Predicate<String> predicate1 = (s)->s.startsWith("x");
		Predicate<String> predicate2 = s->{return s.startsWith("x");};
		Predicate<String> predicate3 = s->s.startsWith("x");
		
		Consumer<String> consumer1 = x->System.out.println(x);
		Consumer<String> consumer2 = System.out::println;
		Consumer<String> consumer3 = out::println;
		
		Supplier<Integer> supplier1 = ()->42;
		Supplier<Integer> supplier2 = ()->new Random().nextInt();
		
		Comparator<Integer> comparator1 = (i1, i2)->i1-i2;
		Comparator<String> comparator2 = (s1, s2)->s1.compareTo(s2);
		Comparator<Boolean> comparator3 = (s1, s2)->0;
		
		
		/*
			//java.util.function
			 
			//1. Predicate

			@FunctionalInterface
			public interface Predicate<T> {
			   boolean test(T t);
			}
			
			//Predicate<Animal> predicate = a->a.canHop();
			//Predicate<String> predicate = s->s.startsWith("x");
			
			//2. Consumer

			public interface Consumer<T> {
			   void accept(T t);
			}
			
			//Consumer<String> consumer = x->System.out.println(x);
			 
			//3. Supplier

			public interface Supplier<T> {
			   T get();
			}
			
			//Supplier<Integer> supplier = ()->42;
			 
			//4. Comparator

			public interface Comparator<T> {
			   int compare(T o1, T o2);
			}
			
			//Comparator<Integer> comparator = (i1, i2)->i1-i2;
			 
		 */

		var xv = 12;
		
		//-----------parameter list
		Predicate<String> p1 = x -> true;
		//Predicate<String> p2 = (var x) -> true;
		Predicate<String> p3 = (String x) -> true;
		
		//Consumer<Integer> c1 = (var c)->out.println(c);

		//-----------Local Variables
		Predicate<String> p4 = (a)->{int c=0; return true;};
		//Predicate<String> p4 = (a)->{int a=0; return true;}; //does not compile
		
		//-----------Rules for accessing a variable from a lambda body inside a method
		//instance variable, static variable, lambda parameter: allowed
		//local variable, method parameter: 					allowed if final or effectively final
		
	}
	
	private static void testCallingLambdas() {
		out.println("--------------testCallingLambdas-----------");
		
		//List and Set declare a removeIf(Predicate p) which is from java.util.collection interface
		//default boolean	removeIf(Predicate<? super E> filter);
		
		List<String> bunnies1 = new ArrayList<>();
		bunnies1.add("long ear");
		bunnies1.add("floppy");
		bunnies1.add("hoppy");
		System.out.println(bunnies1);     // [long ear, floppy, hoppy]
		bunnies1.removeIf(s -> s.charAt(0) != 'h');
		System.out.println(bunnies1);     // [hoppy]
		
		//The removeIf() method works the same way on a Set, but Map does not have this method.
		
		//List interface
		//default void	sort(Comparator<? super E> c);
		//Sorts this list according to the order induced by the specified Comparator.

		List<String> bunnies2 = new ArrayList<>();
		bunnies2.add("long ear");
		bunnies2.add("floppy");
		bunnies2.add("hoppy");
		System.out.println(bunnies2);     // [long ear, floppy, hoppy]
		bunnies2.sort((b1, b2) -> b1.compareTo(b2));
		System.out.println(bunnies2);     // [floppy, hoppy, long ear]
		
		//old way sorting as Collections.sort(list)
		//There is not a sort method on Set or Map. Neither of those types has indexing, so it wouldn’t make sense to sort them.

		//java.lang	Interface Iterable<T>
		//default void forEach(Consumer<? super T> action);
		//List and Set work the same way on forEach()
		List<String> bunnies3 = new ArrayList<>();
		bunnies3.add("long ear");
		bunnies3.add("floppy");
		bunnies3.add("hoppy");
		bunnies3.forEach(b -> System.out.println(b));
		System.out.println(bunnies3);
		
		//Map works as using keySet or values as the following
		Map<String, Integer> bunnies4 =  new HashMap<>();
		bunnies4.put("long ear", 3);
		bunnies4.put("floppy", 8);
		bunnies4.put("hoppy", 1);
		bunnies4.keySet().forEach(b -> System.out.println(b));
		bunnies4.values().forEach(b -> System.out.println(b));
		
		//or using BiConsumer forEach(BiConsumer bc)
		Map<String, Integer> bunnies5 = new HashMap<>();
		bunnies5.put("long ear", 3);
		bunnies5.put("floppy", 8);
		bunnies5.put("hoppy", 1);
		bunnies5.forEach((k, v) -> System.out.println(k + " " + v));


		
	}
	

	private static void testTest() {

/*
		1.	a
		2.	c
		3.	adf
		4.	af
		5.	bd
		6.	e
		7.	abef
		8.	acf
		9.	c[abc]
		10.	c
		11.	a
		12.	cde
		13.	e
		14.	c
		15.	c
		16.	ad
		17.	c
		18.	d
		19.	ac
		20.	ac
		

*/
		
		out.println("--------------testTest-----------");
		//7.
		Map map = Map.of(1, 2, 3, 4);
		
		map.keySet().forEach(out::println);
		map.values().forEach(out::println);


		
	}
	
	//test 9.
	public void remove(List<Character> chars) {
	     char end = 'z';
	     chars.removeIf(c -> {
	        char start = 'a'; return start <= c && c <= end; });
	        // INSERT LINE HERE
	     
	     char start = 'a';
	     char c = 'x';
	     chars = null;
	  }

}


//-----------Rules for accessing a variable from a lambda body inside a method
//instance variable, static variable, lambda parameter: allowed
//local variable, method parameter: 					allowed if final or effectively final

class Crow {
	
     private String color;
     public void caw(String name) {
    	 
        String volume = "loudly";
        name = "Caty";
        color = "black";
  
        Consumer<String> consumer = s ->
//      System.out.println(name + " says " + volume + " that she is " + color); 
        //name and volume cost compiling error
        System.out.println(" says " + " that she is " + color);
          
          volume = "softly";

     }
}