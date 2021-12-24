package com.sample.microservices.common.chapter;

import static java.lang.System.out;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Chapter15_FunctionalProgramming {


	public static void main(String[] args) {

		TestFunctionalProgramming();
		
		//testTest();
		
		
	}

	private static void TestFunctionalProgramming() {
		
		out.println("--------------TestFunctionalProgramming-----------");

		//Built-in Functional interfaces
		
		//Functional interface 		Return type 		Method name 		# of parameters 
		//----------------------------------------------------------------------------------------
		//Supplier<T>  				T  					get()  				0 
		//Consumer<T>  				void  				accept(T)  			1 (T) 
		//BiConsumer<T, U>  		void  				accept(T,U)  		2 (T, U) 
		//Predicate<T>  			boolean  			test(T)  			1 (T) 
		//BiPredicate<T, U>  		boolean  			test(T,U) 			2 (T, U) 
		//Function<T, R>  			R  					apply(T)  			1 (T) 
		//BiFunction<T, U, R>  		R  					apply(T,U)  		2 (T, U) 
		//UnaryOperator<T>  		T  					apply(T)  			1 (T) 
		//BinaryOperator<T>  		T  					apply(T,T)  		2 (T, T)


		//implementing Supplier
		/*
			@FunctionalInterface
			public interface Supplier<T> {
			   T get();
			}			
		*/
		
		System.out.println("//implementing Supplier");
		
		Supplier<LocalDate> s1 = LocalDate::now;
		Supplier<LocalDate> s2 = ()->LocalDate.now();
		 
		LocalDate d1 = s1.get();
		LocalDate d2 = s2.get();
		 
		System.out.println(d1);
		System.out.println(d2);

		Supplier<StringBuilder> s3 = StringBuilder::new;
		Supplier<StringBuilder> s4 = () -> new StringBuilder();
		 
		System.out.println(s3.get());
		System.out.println(s4.get());

		Supplier<ArrayList<String>> s5 = ArrayList<String>::new;

		ArrayList<String> a1 = s5.get();
		System.out.println(a1);

		System.out.println(s5);
		
		//implementing Consumer and BiConsumer
		/*
			@FunctionalInterface
			public interface Consumer<T> {
			   void accept(T t);
			   // omitted default method
			}
			 
			@FunctionalInterface
			public interface BiConsumer<T, U> {
			   void accept(T t, U u);
			   // omitted default method
			}
		*/
		
		System.out.println("//implementing Consumer and BiConsumer");
		
		Consumer<String> c1 = System.out::println;
		Consumer<String> c2 = x -> System.out.println(x);
		 
		c1.accept("Annie");
		c2.accept("Annie");

		var map1 = new HashMap<String, Integer>();
		BiConsumer<String, Integer> b1 = map1::put;
		BiConsumer<String, Integer> b2 = (k, v) -> map1.put(k, v);
		 
		b1.accept("chicken", 7);
		b2.accept("chick", 1);
		 
		System.out.println(map1);

		var map2 = new HashMap<String, String>();
		BiConsumer<String, String> b21 = map2::put;
		BiConsumer<String, String> b22 = (k, v) -> map2.put(k, v);
		 
		b21.accept("chicken", "Cluck");
		b22.accept("chick", "Tweep");
		 
		System.out.println(map2);
		
		//implementing Predicate and BiPredicate
		/*
			@FunctionalInterface
			public interface Predicate<T> {
			   boolean test(T t);
			   // omitted default and static methods
			}
			 
			@FunctionalInterface
			public interface BiPredicate<T, U> {
			   boolean test(T t, U u);
			   // omitted default methods
			}
		*/
		
		System.out.println("//implementing Predicate and BiPredicate");
		
		Predicate<String> p1 = String::isEmpty;
		Predicate<String> p2 = x -> x.isEmpty();

		System.out.println(p1.test(""));  // true
		System.out.println(p2.test(""));  // true

		BiPredicate<String, String> biP1 = String::startsWith;
		BiPredicate<String, String> biP2 = (string, prefix) -> string.startsWith(prefix);
		 
		System.out.println(biP1.test("chicken", "chick"));  // true
		System.out.println(biP2.test("chicken", "chick"));  // true


		//implementing Function and BiFunction
		/*
			@FunctionalInterface
			public interface Function<T,R> {
			R apply(T t);
			   // omitted default and static methods
			}
			 
			@FunctionalInterface
			public interface BiFunction<T, U, R> {
			   R apply(T t, U u);
			   // omitted default method
			}
		*/
		
		System.out.println("//implementing Function and BiFunction");
		
		Function<String, Integer> f1 = String::length;
		Function<String, Integer> f2 = x -> x.length();
		 
		System.out.println(f1.apply("cluck")); // 5
		System.out.println(f2.apply("cluck")); // 5

		BiFunction<String, String, String> bF1 = String::concat;
		BiFunction<String, String, String> bF2 =	(string, toAdd) -> string.concat(toAdd);
				 
		System.out.println(bF1.apply("baby ", "chick")); // baby chick
		System.out.println(bF2.apply("baby ", "chick")); // baby chick

		
		//implementing UnaryOperator and BinaryOperator
		/*
			@FunctionalInterface
			public interface UnaryOperator<T> extends Function<T, T> { 
				//T apply(T t);
			}
			 
			@FunctionalInterface
			public interface BinaryOperator<T> extends BiFunction<T, T, T> { 
			 	//T apply(T t1, T t2);
			   	// omitted static methods
			}
		*/
		
		System.out.println("//implementing UnaryOperator and BinaryOperator");
		
		UnaryOperator<String> u1 = String::toUpperCase;
		UnaryOperator<String> u2 = x -> x.toUpperCase();
		 
		System.out.println(u1.apply("chirp"));  // CHIRP
		System.out.println(u2.apply("chirp"));  // CHIRP

		BinaryOperator<String> bO1 = String::concat;
		BinaryOperator<String> bO2 = (string, toAdd) -> string.concat(toAdd);

		System.out.println(bO1.apply("baby ", "chick")); // baby chick
		System.out.println(bO2.apply("baby ", "chick")); // baby chick

		//Checking FunctionalInterface
		
		//Function<List<String>> ex1 = x -> x.get(0); // DOES NOT COMPILE
		//UnaryOperator<Long> ex2 = (Long l) -> 3.14; // DOES NOT COMIPLE
		//Predicate ex4 = String::isEmpty;            // DOES NOT COMPILE

		//Convenience Methods on Functional Interfaces
		
		//Interface instance 		Method return type 		Method name 		Method parameters 
		//-----------------------------------------------------------------------------------------
		//Consumer  				Consumer  				andThen()  			Consumer  
		//Function  				Function  				andThen()  			Function  
		//Function  				Function  				compose()  			Function  
		//Predicate  				Predicate  				and()  				Predicate
		//Predicate  				Predicate  				negate()  			— 
		//Predicate  				Predicate  				or()  				Predicate

		System.out.println("//Convenience Methods on Functional Interfaces");

		Predicate<String> egg = s -> s.contains("egg");
		Predicate<String> brown = s -> s.contains("brown");
		
		Predicate<String> brownEggs1 = s -> s.contains("egg") && s.contains("brown");
		Predicate<String> otherEggs1 = s -> s.contains("egg") && ! s.contains("brown");

		Predicate<String> brownEggs2 = egg.and(brown);
		Predicate<String> otherEggs2 = egg.and(brown.negate());

		Consumer<String> cx1 = x -> System.out.print("1: " + x);
		Consumer<String> cx2 = x -> System.out.print(",2: " + x);

		Consumer<String> combined1 = cx1.andThen(cx2);
		combined1.accept("Annie");              // 1: Annie,2: Annie

		Function<Integer, Integer> before = x -> x + 1;
		Function<Integer, Integer> after = x -> x * 2;
		 
		Function<Integer, Integer> combined2 = after.compose(before); 	//The before runs first then the after runs.
		System.out.println(combined2.apply(3));   // 8

		//Returning an Optional
		
		//creating an Optional
		//Optional<Type> Optional.empty(); 
		//Optional<Type> Optional.of(value);
		
		System.out.println("//creating an Optional");
		
		System.out.println(average(90, 100)); // Optional[95.0]
		System.out.println(average());        // Optional.empty
		
		Optional<Double> opt1 = average(90, 100);
		if (opt1.isPresent())   System.out.println(opt1.get()); // 95.0
		
		Optional<Double> opt2 = average();
		//System.out.println(opt2.get()); // NoSuchElementException
		
		Integer value1 = 10;
		Optional<Integer> o1 = (value1 == null) ? Optional.empty() : Optional.of(value1);
		
		//If value is null, o is assigned the empty Optional. Otherwise, we wrap the value.
		Optional<Integer> o2 = Optional.ofNullable(value1);
		
		System.out.println(o1);
		System.out.println(o2);
		
		Integer value2 = null;
		Optional o21 = (value2 == null) ? Optional.empty() : Optional.of(value2);
		Optional o22 = Optional.ofNullable(value2);
		
		System.out.println(o21);
		System.out.println(o22);
		
		//Optional instance Methods
		
		//Method 					When Optional is empty 							When Optional contains a value 
		//-----------------------------------------------------------------------------------------------------------
		//get()  					Throws an exception 							Returns value 
		//ifPresent(Consumer c)  	Does nothing 									Calls Consumer with value 
		//isPresent()  				Returns false 									Returns true 
		//orElse(T other)  			Returns other parameter 						Returns value 
		//orElseGet(Supplier s)  	Returns result of calling Supplier 				Returns value 
		//orElseThrow()  			Throws NoSuchElementException 					Returns value 
		//orElseThrow(Supplier s)  	Throws exception created by calling Supplier 	Returns value

		Optional<Double> opt10 = average(90, 100);
		opt10.ifPresent(System.out::println);

		//dealing with an Empty Optional
		
		Optional<Double> opt11 = average();
		System.out.println(opt11.orElse(Double.NaN));
		System.out.println(opt11.orElseGet(() -> Math.random()));

		Optional<Double> opt12 = average();
		//System.out.println(opt12.orElseThrow()); //throws (java.util.NoSuchElementException: No value present )

		Optional<Double> opt13 = average();
		//System.out.println(opt13.orElseThrow(() -> new IllegalStateException())); //throws (java.lang.IllegalStateException )
		
		//System.out.println(opt13.orElseGet(() -> new IllegalStateException())); // DOES NOT COMPILE 
		//since opt13 variable is an Optional<Double> the Supplier must return a Double.

		//if the value does exist they all return the value
		Optional<Double> opt = average(90, 100);
		System.out.println(opt.orElse(Double.NaN));
		System.out.println(opt.orElseGet(() -> Math.random()));
		System.out.println(opt.orElseThrow());

		//Using Streams
		//A stream in Java is a sequence of data. A stream pipeline consists of the operations 
		//that run on a stream to produce a result.
		
		//Stream pipeline has three parts
		//1.	Source: Where the stream comes from 
		//2.	Intermediate operations: Transforms the stream into another one. There can be as few or as many intermediate operations as you'd like. 
		//		Since streams use lazy evaluation, the intermediate operations do not run until the terminal operation runs. 
		//3.	Terminal operation: Actually produces a result. Since streams can be used only once, 
		//		the stream is no longer valid after a terminal operation completes.

		//intermediate vs. terminal operations
		
		//Scenario 										Intermediate operation 					Terminal operation 
		//----------------------------------------------------------------------------------------------------------
		//Required part of a useful pipeline? 			No 										Yes 
		//Can exist multiple times in a pipeline? 		Yes 									No 
		//Return type is a stream type? 				Yes 									No 
		//Executed upon method call? 					No 										Yes 
		//Stream valid after call? 						Yes 									No

		//Creating Stream Sources
		
		//The methods to create a source
		
		//Method Finite or 									infinite? 				Notes 
		//-----------------------------------------------------------------------------------------------------------------------
		//Stream.empty()  									Finite 					Creates Stream with zero elements 
		//Stream.of(varargs)  								Finite 					Creates Stream with elements listed or an array
		//Arrays.stream(T[])								Finite 					Creates Stream from an array 
		//collection.stream()  								Finite 					Creates Stream from a Collection 
		//collection.parallelStream()  						Finite 					Creates Stream from a Collection where the stream can run in parallel 
		//Stream.generate(supplier)  						Infinite 				Creates Stream by calling the Supplier for each element upon request 
		//Stream.iterate(seed, unaryOperator)  				Infinite 				Creates Stream by using the seed for the first element and then calling the UnaryOperator for each subsequent element upon request 
		//Stream.iterate(seed, predicate, unaryOperator)  	Finite or infinite 		Creates Stream by using the seed for the first element and then calling the UnaryOperator for each subsequent element upon request. Stops if the Predicate returns false
		
		//creating Finite Streams

		System.out.println("//creating Finite Streams");

		Stream<String> empty = Stream.empty();          // count = 0
		Stream<Integer> singleElement = Stream.of(1);   // count = 1
		Stream<Integer> fromArray = Stream.of(1, 2, 3); // count = 3
		
		//Arrays Stream method to create Stream from an array
		//public static <T> Stream<T> stream(T[] array);
		Integer[] iArray = {1,2,3,4,5};
		Stream<Integer> fromiArray1 = Stream.of(iArray); // count = 5
		Stream<Integer> fromiArray2 = Arrays.stream(iArray); // count = 5
		
		//Collection has a stream() method to create Stream from Collection		
		var list = List.of("a", "b", "c");
		Stream<String> fromList = list.stream();
		Stream<String> fromListParallel = list.parallelStream();

		//creating infinite Streams
		
		System.out.println("//creating infinite Streams");

		Stream<Double> randoms = Stream.generate(Math::random);
		Stream<Integer> oddNumbers = Stream.iterate(
				1,                // seed
				n -> n + 2);      // UnaryOperator to get next value

		//odd numbers less than 10
		Stream<Integer> oddNumberUnder10 = Stream.iterate(
				1,                // seed
				n -> n < 10,     // Predicate to specify when done
				n -> n + 2);      // UnaryOperator to get next value

		oddNumberUnder10.forEach(System.out::println);

////////////////////////////////////////////////////////////////////////////////////////////////
		//Terminal Operations :
		//forEach(), toArray(), reduce(), collect(), min(), max(), count(), anyMatch(), 
		//allMatch(), noneMatch(), findFirst(), findAny()

		//Using common Terminal Operations
		
		//Terminal Stream Operations
		
		//Method 								What happens for infinite streams 		Return value 			Reduction 
		//------------------------------------------------------------------------------------------------------------------
		//count()  								Does not terminate 						long  					Yes 
		//min(),max()  							Does not terminate 						Optional<T>  			Yes 
		//findAny(),findFirst()  				Terminates 								Optional<T>  			No 
		//allMatch(),anyMatch(),noneMatch()  	Sometimes terminates 					boolean  				No 
		//forEach()  							Does not terminate 						void  					No 
		//reduce()  							Does not terminate 						Varies 					Yes
		//collect()  							Does not terminate 						Varies 					Yes
		//toArray()								Does not terminate						Object[]				Yes
		//forEachOrdered()
		
		//long count();
		
		Stream<String> sx1 = Stream.of("monkey", "gorilla", "bonobo");
		System.out.println(sx1.count());   // 3
		
		//Optional<T> min(Comparator<? super T> comparator)
		//Optional<T> max(Comparator<? super T> comparator)

		Stream<String> sx2 = Stream.of("monkey", "ape", "bonobo");
		Optional<String> min = sx2.min((sv1, sv2) -> sv1.length()-sv2.length());
		min.ifPresent(System.out::println); // ape

		Stream<String> sx3 = Stream.of("monkey", "ape", "bonobo");
		Optional<String> max = sx3.min((sv1, sv2) -> sv2.length() - sv1.length());
		max.ifPresent(System.out::println); // bonobo

		Optional<?> minEmpty = Stream.empty().min((sv1, sv2) -> 0);
		System.out.println(minEmpty.isPresent()); // false
		
		//Optional<T> findAny()
		//Optional<T> findFirst()

		Stream<String> sx4 = Stream.of("monkey", "gorilla", "bonobo");
		Stream<String> infinite1 = Stream.generate(() -> "chimp");
		 
		sx4.findAny().ifPresent(System.out::println);        // monkey (usually)
		infinite1.findAny().ifPresent(System.out::println); // chimp

		//boolean anyMatch(Predicate <? super T> predicate)
		//boolean allMatch(Predicate <? super T> predicate)
		//boolean noneMatch(Predicate <? super T> predicate)

		var listx1 = List.of("monkey", "2", "chimp");
		Stream<String> infinite2 = Stream.generate(() -> "chimp");
		Predicate<String> pred = x -> Character.isLetter(x.charAt(0));
		 
		System.out.println(listx1.stream().anyMatch(pred));  // true
		System.out.println(listx1.stream().allMatch(pred));  // false
		System.out.println(listx1.stream().noneMatch(pred)); // false
		System.out.println(infinite2.anyMatch(pred));       // true

		//void forEach(Consumer<? super T> action)

		Stream<String> sx5 = Stream.of("Monkey", "Gorilla", "Bonobo");
		sx5.forEach(System.out::print); // MonkeyGorillaBonobo

		Stream<Integer> sx6 = Stream.of(1);
		//for (Integer i  : sx6) {} // DOES NOT COMPILE

		//T reduce(T identity, BinaryOperator<T> accumulator)		 
		//Optional<T> reduce(BinaryOperator<T> accumulator)		 
		//<U> U reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator<U> combiner)

		var array = new String[] { "w", "o", "l", "f" };
		var result = "";
		for (var s: array) result = result + s;
		System.out.println(result); // wolf

		Stream<String> sx7 = Stream.of("w", "o", "l", "f");
		String word = sx7.reduce("", (s, c) -> s + c);
		System.out.println(word); // wolf

		Stream<String> sx8 = Stream.of("w", "o", "l", "f");
		String wordx1 = sx8.reduce("", String::concat);
		System.out.println(wordx1); // wolf

		Stream<Integer> sx9 = Stream.of(3, 5, 6);
		System.out.println(sx9.reduce(1, (a, b) -> a*b));  // 90

		//the following illustrates:
		//1.	If the stream is empty, an empty Optional is returned. 
		//2.	If the stream has one element, it is returned. 
		//3.	If the stream has multiple elements, the accumulator is applied to combine them.

		BinaryOperator<Integer> op = (a, b) -> a * b;
		Stream<Integer> empty1 = Stream.empty();
		Stream<Integer> oneElement = Stream.of(3);
		Stream<Integer> threeElements = Stream.of(3, 5, 6);
		 
		empty1.reduce(op).ifPresent(System.out::println);         // no output
		oneElement.reduce(op).ifPresent(System.out::println);    // 3
		threeElements.reduce(op).ifPresent(System.out::println); // 90

		Stream<String> sx10 = Stream.of("w", "o", "l", "f!");
		int length = sx10.reduce(0, (i, s) -> i+s.length(), (a, b) -> a+b);
		System.out.println(length); // 5

		//<R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner)
		//<R,A> R collect(Collector<? super T, A,R> collector)

		Stream<String> stream = Stream.of("w", "o", "l", "f");
		 
		StringBuilder word2 = stream.collect(
		   StringBuilder::new,
		   StringBuilder::append, 
		   StringBuilder::append);
		 
		System.out.println(word2); // wolf

		Stream<String> sx11 = Stream.of("w", "o", "l", "f");
		 
		TreeSet<String> setx0 = sx11.collect(
		   TreeSet::new, 
		   TreeSet::add,
		   TreeSet::addAll);
		 
		System.out.println(setx0); // [f, l, o, w]

		Stream<String> sx12 = Stream.of("w", "o", "l", "f");
		TreeSet<String> setx1 = sx12.collect(Collectors.toCollection(TreeSet::new));
		System.out.println(setx1); // [f, l, o, w]

		Stream<String> sx13 = Stream.of("w", "o", "l", "f");
		Set<String> setx2 = sx13.collect(Collectors.toSet());
		System.out.println(setx2); // [f, w, l, o]
		
		//Object[] toArray()
		
		Stream<Integer> sx13x = Stream.of(5, 6, 7, 8, 9, 10);
		  
        // Using Stream toArray()
        //Object[] arr = sx13x.toArray();
		Integer[] arr = sx13x.collect(Collectors.toList()).toArray(new Integer[0]);
  
        // Displaying the elements in array arr
        System.out.println(Arrays.toString(arr));
        
        
        //void forEachOrdered(Consumer<? super T> action)
        
		Stream<Integer> sx13y = Stream.of(5, 4, 7, 8, 11, 6, 10, 9);
		sx13y.forEachOrdered(System.out::println);
		
		//void forEach(Consumer<? super T> action) 
		//1. Performs an action as Consumer for each element of this stream.
		//2. This is a terminal operation.
		//3. The behavior of this operation is non-deterministic.
		//4. In parallel operation this method does not guarantee to respect the encounter order.

		//void forEachOrdered(Consumer<? super T> action) 
		//1. Performs an action as Consumer for each element of this stream in the encounter order of this stream if the stream has a defined encounter order.
		//2. This is a terminal operation.
		//3. This method guarantees to respect the encounter order in sequential and parallel streams both.
		
		//Using Sequential Stream
		//In sequential stream forEach and forEachOrdered both method will perform action in encounter order.
		//Find the code for forEach method.
		System.out.println();
		Stream.of("A","B","C", "D").forEach(e -> System.out.print(e)); 
		System.out.println();
		Stream.of("A","B","C", "D").forEachOrdered(e -> System.out.print(e)); 
		
		//Using Parallel Stream
		//In parallel stream forEach does not guarantee encounter order. could be C,D,B,A
		System.out.println();
		Stream.of("A","B","C", "D").parallel().forEach(e -> System.out.print(e)); 
		
		//The forEachOrdered method always guarantee the encounter order. always A,B,C,D
		System.out.println();
		Stream.of("A","B","C", "D").parallel().forEachOrdered(e -> System.out.print(e)); 
		System.out.println();
		
//////////////////////////////////////////////////////////////////////////////////////////////////////
		//Intermediate Operations : 
		//map(), filter(), distinct(), sorted(), limit(), skip(), peek()
		
		//Using common intermediate operations
		
		//Stream<T> filter(Predicate<? super T> predicate)

		Stream<String> sx14 = Stream.of("monkey", "gorilla", "bonobo");
		sx14.filter(x -> x.startsWith("m")).forEach(System.out::print); // monkey

		//Stream<T> distinct()

		Stream<String> sx15 = Stream.of("duck", "duck", "duck", "goose");
		sx15.distinct().forEach(System.out::print); // duckgoose

		//Stream<T> limit(long maxSize)
		//Stream<T> skip(long n)

		Stream<Integer> sx16 = Stream.iterate(1, n -> n + 1);
		sx16.skip(5).limit(2).forEach(System.out::print); // 67

		//<R> Stream<R> map(Function<? super T, ? extends R> mapper)

		Stream<String> sx17 = Stream.of("monkey", "gorilla", "bonobo");
		sx17.map(String::length)
		   .forEach(System.out::print); // 676

		//<R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)

		List<String> zero = List.of();
		var one = List.of("Bonobo");
		var two = List.of("Mama Gorilla", "Baby Gorilla");
		Stream<List<String>> animals = Stream.of(zero, one, two);
		animals.flatMap(m -> m.stream()).forEach(System.out::println);

		//Bonobo
		//Mama Gorilla
		//Baby Gorilla

		//Stream<T> sorted()
		//Stream<T> sorted(Comparator<? super T> comparator)

		Stream<String> sx18 = Stream.of("brown-", "bear-");
		sx18.sorted()
		   .forEach(System.out::print); // bear-brown-

		Stream<String> sx19 = Stream.of("brown bear-", "grizzly-");
		sx19.sorted(Comparator.reverseOrder())
		   .forEach(System.out::print); // grizzly-brown bear-
		
		//sx19.sorted(Comparator::reverseOrder); // DOES NOT COMPILE

		//Stream<T> peek(Consumer<? super T> action)

		var sx20 = Stream.of("black bear", "brown bear", "grizzly");
		long count = sx20.filter(s -> s.startsWith("g"))
		   .peek(System.out::println).count();              // grizzly
		System.out.println(count);                          // 1

		//Summery
		//1) 	The main difference between intermediate and terminal operations is that intermediate operations 
		//		return a stream as a result and terminal operations return non-stream values like primitive 
		//		or object or collection or may not return anything.

		//2) 	As intermediate operations return another stream as a result, they can be chained together 
		//		to form a pipeline of operations. Terminal operations can not be chained together.

		//3) 	Pipeline of operations may contain any number of intermediate operations, 
		//		but there has to be only one terminal operation, that too at the end of pipeline.
		
		//4) 	Intermediate operations are lazily loaded. When you call intermediate operations, 
		//		they are actually not executed. They are just stored in the memory and executed 
		//		when the terminal operation is called on the stream.
		
		//5) 	As the names suggest, intermediate operations doesn’t give end result. They just transform 
		//		one stream to another stream. On the other hand, terminal operations give end result.
		
		

		//Working with Primitive Streams
		//Here are three types of primitive streams
		//1.	IntStream: Used for the primitive types int, short, byte, and char 
		//2.	LongStream: Used for the primitive type long 
		//3.	DoubleStream: Used for the primitive types double and float

		//common primitive stream methods
		
		//Method 										Primitive stream 						Description 
		//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		//OptionalDouble 	average()  					IntStream,LongStream,DoubleStream  		The arithmetic mean of the elements 
		//Stream<T> 		boxed()  					IntStream,LongStream,DoubleStream  		A Stream<T> where T is the wrapper class associated with the primitive value 
		//OptionalInt 		max()  						IntStream  								The maximum element of the stream 
		//OptionalLong 		max()  						LongStream  
		//OptionalDouble 	max()  						DoubleStream  
		//OptionalInt 		min()  						IntStream  								The minimum element of the stream  
		//OptionalLong 		min()  						LongStream  
		//OptionalDouble 	min()  						DoubleStream
		
		//IntStream 		range(int a, int b)  		IntStream  								Returns a primitive stream from a (inclusive) to b (exclusive)  
		//LongStream 		range(long a, long b)  		LongStream  
		//IntStream 		rangeClosed(int a, int b)  	IntStream  								Returns a primitive stream from a (inclusive) to b (inclusive)  
		//LongStream 		rangeClosed(long a, long b) LongStream  
		//int 				sum()  						IntStream  								Returns the sum of the elements in the stream  
		//long 				sum()  						LongStream  
		//double 			sum()  						DoubleStream  
		//IntSummaryStatistics 		summaryStatistics() IntStream  								Returns an object containing numerous stream statistics such as the average, min, max, etc.  
		//LongSummaryStatistics 	summaryStatistics() LongStream  
		//DoubleSummaryStatistics 	summaryStatistics() DoubleStream

		//creating primitive streams
		
		DoubleStream emptyx1 = DoubleStream.empty();

		DoubleStream oneValuex1 = DoubleStream.of(3.14);
		oneValuex1.forEach(System.out::println);

		DoubleStream varargsx1 = DoubleStream.of(1.0, 1.1, 1.2);
		varargsx1.forEach(System.out::println);

		var randomx1 = DoubleStream.generate(Math::random);
		var fractionsx1 = DoubleStream.iterate(.5, d -> d / 2);
		randomx1.limit(3).forEach(System.out::println);
		fractionsx1.limit(3).forEach(System.out::println);

		IntStream countx1 = IntStream.iterate(1, n -> n+1).limit(5);
		countx1.forEach(System.out::println);

		IntStream rangex1 = IntStream.range(1, 6);
		rangex1.forEach(System.out::println); //1-5

		IntStream rangeClosedx1 = IntStream.rangeClosed(1, 6);
		rangeClosedx1.forEach(System.out::println); //1-6
		
		//mapping streams
		//Another way to create a primitive stream is by mapping from another stream type.

		//Mapping methods between types of streams. Obviously, they have to be compatible types for this to work.

		//Source stream class 	To create Stream 	To create DoubleStream 	To create IntStream 	To create LongStream 
		//-----------------------------------------------------------------------------------------------------------------
		//Stream<T>  			map()  				mapToDouble()  			mapToInt()  			mapToLong()  
		//DoubleStream  		mapToObj()  		map()  					mapToInt()  			mapToLong()  
		//IntStream  			mapToObj()  		mapToDouble()  			map()  					mapToLong()  
		//LongStream  			mapToObj()  		mapToDouble()  			mapToInt()  			map()

		Stream<String> objStreamx1 = Stream.of("penguin", "fish");
		IntStream intStreamx1 = objStreamx1.mapToInt(s -> s.length());
		
		//Function parameters when mapping between types of streams

		//Source stream class 	To create Stream 	To create DoubleStream 	To create IntStream 	To create LongStream 
		//----------------------------------------------------------------------------------------------------------------
		//Stream<T>  			Function<T,R>  		ToDoubleFunction<T>  	ToIntFunction<T>  		ToLongFunction<T>  
		//DoubleStream  		Double Function<R>  DoubleUnary Operator  	DoubleToInt Function  	DoubleToLong Function  
		//IntStream  			IntFunction<R>  	IntToDouble Function  	IntUnary Operator  		IntToLong Function  
		//LongStream  			Long Function<R>  	LongToDouble Function  	LongToInt Function  	LongUnary Operator
		
		//using flatMap() for primitive stream
		
		var integerList = new ArrayList<Integer>();
		IntStream ints = integerList.stream()
		.flatMapToInt(x -> IntStream.of(x));
		
		DoubleStream doubles = integerList.stream()
		.flatMapToDouble(x -> DoubleStream.of(x));
		
		LongStream longs = integerList.stream()
		.flatMapToLong(x -> LongStream.of(x));

		Stream<Double> dBoxedx1 = DoubleStream.of(1.0, 1.1, 1.2).mapToObj(x->x);
		Stream<Double> dBoxedx2 = DoubleStream.of(1.0, 1.1, 1.2).boxed();
		
		//using Optional with Primitive Streams.
		
		var streamx1 = IntStream.rangeClosed(1,10);
		OptionalDouble optionalx1 = streamx1.average();

		optionalx1.ifPresent(System.out::println);                  // 5.5
		System.out.println(optionalx1.getAsDouble());               // 5.5
		System.out.println(optionalx1.orElseGet(() -> Double.NaN)); // 5.5
		
		//Optional types for primitives
		
		//Description 						OptionalDouble  			OptionalInt  			OptionalLong  
		//-----------------------------------------------------------------------------------------------------
		//Getting as a primitive 			getAsDouble()  				getAsInt()  			getAsLong()  
		//orElseGet() parameter type 		DoubleSupplier  			IntSupplier  			LongSupplier  
		//Return type of max() and min() 	OptionalDouble  			OptionalInt  			OptionalLong  
		//Return type of sum() 				double  					int  					long  
		//Return type of average() 			OptionalDouble  			OptionalDouble  		OptionalDouble

		LongStream longsx1 = LongStream.of(5, 10);
		long sumx1 = longsx1.sum();
		System.out.println(sumx1);     // 15
		DoubleStream doublesx1 = DoubleStream.generate(() -> Math.PI);
		//OptionalDouble minx1 = doublesx1.min(); // runs infinitely
		
		//Summarizing statistics
		
		//SummaryStatistics include the following
		//----------------------------------------------------
		//1.	Smallest number (minimum): 		getMin() 
		//2.	Largest number (maximum): 		getMax() 
		//3.	Average: 						getAverage() 
		//4.	Sum: 							getSum() 
		//5.	Number of values: 				getCount()
		

		DoubleSummaryStatistics dssx1 = DoubleStream.of(1.0, 1.1, 1.2).summaryStatistics();
		System.out.println(dssx1); 
		//DoubleSummaryStatistics{count=3, sum=3.300000, min=1.000000, average=1.100000, max=1.200000}
		
		//Functional Interfaces for Primitive
		
		//Functional Interfaces for boolean 
		//BooleanSupplier is a separate type. It has one method to implement: 
		//boolean getAsBoolean()

		BooleanSupplier b1x1 = () -> true;
		BooleanSupplier b2x1 = () -> Math.random()> .5;
		System.out.println(b1x1.getAsBoolean());  // true
		System.out.println(b2x1.getAsBoolean());  // false

		//Functional interfaces for double, int, and long
		
		//Functional interfaces 		# parameters 		Return type 		Single abstract method
		//----------------------------------------------------------------------------------------------
		//DoubleSupplier 				0					double				getAsDouble
		//IntSupplier 					0					int					getAsInt
		//LongSupplier  				0 					long				getAsLong
		//DoubleConsumer				1(double)			void				accept					 
		//IntConsumer 					1(int)				void				accept
		//LongConsumer  				1(long)				void				accept
		//DoublePredicate 				1(double)			boolean				test
		//IntPredicate 					1(int)				boolean				test
		//LongPredicate  				1(long)				boolean				test
		//DoubleFunction<R> 			1(double)			R					apply
		//IntFunction<R> 				1(int)				R					apply
		//LongFunction<R>  				1(long)				R					apply
		//DoubleUnaryOperator 			1(double)			double				applyAsDouble
		//IntUnaryOperator 				1(int)				int					applyAsInt
		//LongUnaryOperator  			1(long)				long				applyAsLong
		//DoubleBinaryOperator 			2(double,double)	double				applyAsDouble
		//IntBinaryOperator 			2(int,int)			int					applyAsInt
		//LongBinaryOperator			2(long,long)		long				applyAsLong

		//Generics are gone from some of the interfaces, and instead the type name tells us what primitive type is involved. 
		//In other cases, such as IntFunction, only the return type generic is needed because we're converting a primitive int into an object. 
		//The single abstract method is often renamed when a primitive type is returned.
		
		//Primitive-specific functional interfaces
		
		//Functional interfaces 		# parameters 		Return type 		Single abstract method 
		//----------------------------------------------------------------------------------------------------
		//ToDoubleFunction<T> 			1(T)				double				applyAsDouble
		//ToIntFunction<T> 				1(T)				int					applyAsInt
		//ToLongFunction<T>  			1(T) 				long  				applyAsLong  
		//ToDoubleBiFunction<T, U>   	2(T,U) 				double				applyAsDouble
		//ToIntBiFunction<T, U>   		2(T,U) 				int					applyAsInt
		//ToLongBiFunction<T, U>   		2(T,U) 				long  				applyAsDouble 
		//DoubleToIntFunction 			1(double)			int					applyAsInt
		//DoubleToLongFunction 			1(double)			long				applyAsLong
		//IntToDoubleFunction 			1(int)				double				applyAsDouble
		//IntToLongFunction 			1(int)				long				applyAsLong
		//LongToDoubleFunction 			1(long)				double				applyAsDouble
		//LongToIntFunction  			1(long)				int					applyAsInt
		//ObjDoubleConsumer<T> 			2(T,double)			void				accept
		//ObjIntConsumer<T> 			2(T,int)			void				accept
		//ObjLongConsumer<T>  			2(T,long)			void				accept

	
	}
	
	public static Optional<Double> average(int... scores) {
	    if (scores.length == 0) return Optional.empty();
	    int sum = 0;
	    for (int score: scores) sum += score;
	    return Optional.of((double) sum / scores.length);
	}


	private static void testTest() {
		
		out.println("--------------testTest-----------");
		


/*
		1.	b
		2.	e(d)
		3.	cg
		4.	f(d)
		5.	bdf(bf)
		6.	b
		7.	ac(ad)
		8.	b(bf)
		9.	e
		10.	a
		11.	a
		12.	bd(abd)
		13.	be
		14.	c
		15.	a
		16.	bdf
		17.	bd
		18.	ab
		19.	ad
		20.	e
		21.	d(ae)
		22.	b
		23.	be
		24.	f
		25.	f

		

*/

		
	
	}
	
}

    