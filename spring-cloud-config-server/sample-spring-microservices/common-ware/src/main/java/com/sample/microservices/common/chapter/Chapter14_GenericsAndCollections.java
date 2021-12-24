package com.sample.microservices.common.chapter;

import static java.lang.System.out;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import lombok.Data;

public class Chapter14_GenericsAndCollections {


	public static void main(String[] args) {

		//testTemplet();
		//TestGenericasAndCollections();
		
		testTest();
		
		
	}

	private static void testTemplet() {
		
	}
	
	private static void TestGenericasAndCollections() {
		
		out.println("--------------TestGenericasAndCollections-----------");

		//Using Method References
		
		//A method reference and a lambda behave the same way at runtime. 
		//You can pretend the compiler turns your method references into lambdas for you. 
		
		//There are four formats for method references: 
		//1.	Static methods 
		//2.	Instance methods on a particular instance 
		//3.	Instance methods on a parameter to be determined at runtime 
		//4.	Constructors

		//Calling static methods
		
		Consumer<List<Integer>> methodRef1 = Collections::sort;
		Consumer<List<Integer>> lambda1 = x -> Collections.sort(x);

		//Calling instance methods on a particular object
		
		var str = "abc";
		Predicate<String> methodRef21 = str::startsWith;
		Predicate<String> lambda21 = s -> str.startsWith(s);
		
		var random = new Random();
		Supplier<Integer> methodRef22 = random::nextInt;
		Supplier<Integer> lambda22 = () -> random.nextInt();

		//Calling instance methods on a parameter
		
		Predicate<String> methodRef31 = String::isEmpty;
		Predicate<String> lambda31 = s -> s.isEmpty();

		BiPredicate<String, String> methodRef32 = String::startsWith;
		BiPredicate<String, String> lambda32 = (s, p) -> s.startsWith(p);

		//Calling Constructors
		
		//A constructor reference is a special type of method reference that uses new instead of a method, 
		//and it instantiates an object. It is common for a constructor reference to use a Supplier as shown here:

		Supplier<List<String>> methodRef41 = ArrayList::new;
		Supplier<List<String>> lambda41 = () -> new ArrayList();

		Function<Integer, List<String>> methodRef42 = ArrayList::new;
		Function<Integer, List<String>> lambda42 = x -> new ArrayList(x);


		//Using Wrapper Classes
		
		//Using the Diamond Operator
		
		List<Integer> list10 = new ArrayList<Integer>();
		Map<String,Integer> map10 = new HashMap<String,Integer>();
		Map<Long,List<Integer>> mapLists10 = new HashMap<Long,List<Integer>>();

		List<Integer> list11 = new ArrayList<>();
		Map<String,Integer> map11 = new HashMap<>();
		Map<Long,List<Integer>> mapOfLists11 = new HashMap<>();
		
		//The diamond operator cannot be used as the type in a variable declaration. 
		//It can be used only on the right side of an assignment operation.

		//List<> list2 = new ArrayList<Integer>();      	// DOES NOT COMPILE
		//Map<> map2 = new HashMap<String, Integer>();  	// DOES NOT COMPILE
		//class InvalidUse { void use(List<> data2) {} }  	// DOES NOT COMPILE

		var list31 = new ArrayList<Integer>();
		//List<Integer> list31 = new ArrayList<>();
		
		var list32 = new ArrayList<>();
		//List<Object> list32 = new ArrayList<>();
		
		//Using Lists, Sets, Maps, and Queues
		
		//A collection is a group of objects contained in a single object. 
		//The Java Collections Framework is a set of classes in java.util for storing collections. 
		
		//There are four main interfaces in the Java Collections Framework. 
		//1.	List: A list is an ordered collection of elements that allows duplicate entries. 
		//		Elements in a list can be accessed by an int index. 
		//2.	Set: A set is a collection that does not allow duplicate entries. 
		//3.	Queue: A queue is a collection that orders its elements in a specific order for processing. 
		//		A typical queue processes its elements in a first‐in, first‐out order, but other orderings are possible. 
		//4.	Map: A map is a collection that maps keys to values, with no duplicate keys allowed. 
		//		The elements in a map are key/value pairs.
		
		//Common Collection interface Methods
		//The Collection interface contains useful methods for working with lists, sets, and queues.

		//boolean add(E element);
		
		Collection<String> list4 = new ArrayList<>();
		System.out.println(list4.add("Sparrow")); // true
		System.out.println(list4.add("Sparrow")); // true
		
		Collection<String> set4 = new HashSet<>();
		System.out.println(set4.add("Sparrow")); // true
		System.out.println(set4.add("Sparrow")); // false

		//boolean remove(Object object);
		
		Collection<String> birds1 = new ArrayList<>();
		birds1.add("hawk");                            // [hawk]
		birds1.add("hawk");                            // [hawk, hawk]
		System.out.println(birds1.remove("cardinal")); // false
		System.out.println(birds1.remove("hawk"));     // true
		System.out.println(birds1);                    // [hawk]

		//boolean isEmpty();
		//int size();
		
		Collection<String> birds2 = new ArrayList<>();
		System.out.println(birds2.isEmpty()); // true
		System.out.println(birds2.size());    // 0
		birds2.add("hawk");                   // [hawk]
		birds2.add("hawk");                   // [hawk, hawk]
		System.out.println(birds2.isEmpty()); // false
		System.out.println(birds2.size());    // 2

		//void clear();
		
		Collection<String> birds3 = new ArrayList<>();
		birds3.add("hawk");                   // [hawk]
		birds3.add("hawk");                   // [hawk, hawk]
		System.out.println(birds3.isEmpty()); // false
		System.out.println(birds3.size());    // 2
		birds3.clear();                       // []
		System.out.println(birds3.isEmpty()); // true
		System.out.println(birds3.size());    // 0

		//boolean contains(Object object);
		
		Collection<String> birds4 = new ArrayList<>();
		birds4.add("hawk"); // [hawk]
		System.out.println(birds4.contains("hawk"));  // true
		System.out.println(birds4.contains("robin")); // false

		//boolean removeIf(Predicate<? super E> filter);

		Collection<String> list5 = new ArrayList<>();
		list5.add("Magician");
		list5.add("Assistant");
		System.out.println(list5);     // [Magician, Assistant]
		list5.removeIf(s -> s.startsWith("A"));
		System.out.println(list5);     // [Magician]

		Collection<String> set5 = new HashSet<>();
		set5.add("Wand");
		set5.add("");
		set5.removeIf(String::isEmpty); // s -> s.isEmpty()
		System.out.println(set5);       // [Wand]

		//void forEach(Consumer<? super T> action);
		
		Collection<String> cats = Arrays.asList("Annie", "Ripley");
		cats.forEach(System.out::println);
		cats.forEach(c -> System.out.println(c));

		//Using the List interface
		
		//Comparing List Implementations
		
		//The main benefit of an ArrayList is that you can look up any element in constant time. 
		//Adding or removing an element is slower than accessing an element. 
		//This makes an ArrayList a good choice when you are reading more often than (or the same amount as) 
		//writing to the ArrayList. 
		
		//A LinkedList is special because it implements both List and Queue. It has all the methods of a List. 
		//It also has additional methods to facilitate adding or removing from the beginning and/or end of the list. 
		//The main benefits of a LinkedList are that you can access, add, and remove from the beginning 
		//and end of the list in constant time. The trade‐off is that dealing with an arbitrary index takes linear time. 
		//This makes a LinkedList a good choice when you'll be using it as Queue.

		//Creating a List with a Factory
		
		//Method Description 																		add? replace? delete?

		//Arrays.asList(varargs)  	Returns fixed size list backed by an array 							No Yes No 
		//List.of(varargs)  		Returns immutable list 												No No No 
		//List.copyOf(collection)  	Returns immutable list with copy of original collection's values 	No No No

		String[] array = new String[] {"a", "b", "c"};
		List<String> asList = Arrays.asList(array); // [a, b, c]
		List<String> of = List.of(array);           // [a, b, c]
		List<String> copy = List.copyOf(asList);    // [a, b, c]
		 
		array[0] = "z";
		
		System.out.println(asList); // [z, b, c]
		System.out.println(of);     // [a, b, c]
		System.out.println(copy);   // [a, b, c]
		
		asList.set(0, "x");
		System.out.println(Arrays.toString(array)); // [x, b, c]

		copy.add("y");  // throws UnsupportedOperationException

		//working with List Methods
		
		//Method 								Description 
		
		//boolean add(E element)  				Adds element to end (available on all Collection APIs) 
		//void add(int index, E element)  		Adds element at index and moves the rest toward the end 
		//E get(int index)  					Returns element at index 
		//E remove(int index)  					Removes element at index and moves the rest toward the front 
		//void replaceAll(UnaryOperator<E> op)  Replaces each element in the list with the result of the operator 
		//E set(int index, E e)  				Replaces element at index and returns original. Throws IndexOutOfBoundsException if the index is larger than the maximum one set

		List<String> list6 = new ArrayList<>();
		list6.add("SD");                  // [SD]
		list6.add(0, "NY");               // [NY,SD]
		list6.set(1, "FL");               // [NY,FL]
		System.out.println(list6.get(0)); // NY
		list6.remove("NY");               // [FL]
		list6.remove(0);                  // []
		list6.set(0, "?");                // IndexOutOfBoundsException

		List<Integer> numbers = Arrays.asList(1, 2, 3);
		numbers.replaceAll(x -> x*2);
		System.out.println(numbers);   // [2, 4, 6]


		//Using Set interface
		
		//comparing Set implementations
		
		//A HashSet stores its elements in a hash table, which means the keys are a hash and the values are an Object. 
		//This means that it uses the hashCode() method of the objects to retrieve them more efficiently. 
		
		//The main benefit is that adding elements and checking whether an element is in the set both have constant time. 
		//The trade‐off is that you lose the order in which you inserted the elements.
		
		//A TreeSet stores its elements in a sorted tree structure. The main benefit is that the set is always 
		//in sorted order. The trade‐off is that adding and checking whether an element exists take longer than 
		//with a HashSet, especially as the tree grows larger.

		//working with Set methods
		
		//creating Set with of and copyOf methods
		
		Set<Character> letters2 = Set.of('z', 'o', 'o');
		Set<Character> copy2 = Set.copyOf(letters2);

		Set<Integer> set2 = new HashSet<>();
		boolean b1 = set2.add(66);    // true
		boolean b2 = set2.add(10);    // true
		boolean b3 = set2.add(66);    // false
		boolean b4 = set2.add(8);     // true
		set2.forEach(System.out::println);
		
		//66
		//8
		//10

		Set<Integer> set3 = new TreeSet<>();
		boolean b11 = set3.add(66); // true
		boolean b21 = set3.add(10); // true
		boolean b31 = set3.add(66); // false
		boolean b41 = set3.add(8);    // true
		set3.forEach(System.out::println);
		
		//8
		//10
		//66


		//Using Queue interface (FIFO) or (FILO)
		
		//comparing Queue implementations
		
		//Working with Queue methods
		
		//Method 				Description 																		Throws exception on failure 

		//boolean add(E e)  	Adds an element to the back of the queue and returns true or throws an exception 	Yes 
		//E element()  			Returns next element or throws an exception if empty queue 							Yes 
		//E remove()  			Removes and returns next element or throws an exception if empty queue 				Yes 

		//boolean offer(E e)  	Adds an element to the back of the queue and returns whether successful 			No 
		//E peek()  			Returns next element or returns null if empty queue 								No
		//E poll()  			Removes and returns next element or returns null if empty queue 					No 

		Queue<Integer> queue = new LinkedList<>();
		System.out.println(queue.offer(10)); // true
		System.out.println(queue.offer(4));  // true
		System.out.println(queue.peek());    // 10
		System.out.println(queue.poll());    // 10
		System.out.println(queue.poll());    // 4
		System.out.println(queue.peek());    // null

		//Using Map interface
		
		//Creating Map with factory method of() or copyOf()
		
		Map<String, String> map1 = Map.of("key1", "value1", "key2", "value2");
		
		//Map.of("key1", "value1", "key2"); // INCORRECT (throws an error at runtime)

		Map<String, String> map2 = Map.ofEntries(Map.entry("key1", "value1"), Map.entry("key1", "value1"));
		
		Map<String, String> map3 = Map.copyOf(map1);
		
		//comparing Map implementations
		
		//A HashMap stores the keys in a hash table. This means that it uses the hashCode() method of the keys 
		//to retrieve their values more efficiently. 

		//The main benefit is that adding elements and retrieving the element by key both have constant time. 
		//The trade‐off is that you lose the order in which you inserted the elements. 
	
		//A TreeMap stores the keys in a sorted tree structure. The main benefit is that the keys are always in sorted order. 
		//Like a TreeSet, the trade‐off is that adding and checking whether a key is present takes longer as the tree grows larger.

		//Working with Map methods
		
		//Method 												Description 
		//void clear()  										Removes all keys and values from the map. 
		//boolean containsKey(Object key)  						Returns whether key is in map. 
		//boolean containsValue(Object value)  					Returns whether value is in map. 
		//Set<Map.Entry<K,V>> entrySet()  						Returns a Set of key/value pairs.
		//void forEach(BiConsumer(K key, V value))  			Loop through each key/value pair. 
		//V get(Object key)  									Returns the value mapped by key or null if none is mapped. 
		//V getOrDefault(Object key, V defaultValue)  			Returns the value mapped by the key or the default value if none is mapped. 
		//boolean isEmpty()  									Returns whether the map is empty. 
		//Set<K> keySet()  										Returns set of all keys. 
		//V merge(K key, V value, Function(<V, V, V> func))  	Sets value if key not set. Runs the function if the key is set to determine the new value. Removes if null. 
		//V put(K key, V value)  								Adds or replaces key/value pair. Returns previous value or null. 
		//V putIfAbsent(K key, V value)  						Adds value if key not present and returns null. Otherwise, returns existing value. 
		//V remove(Object key)  								Removes and returns value mapped to key. Returns null if none. 
		//V replace(K key, V value)  							Replaces the value for a given key if the key is set. Returns the original value or null if none. 
		//void replaceAll(BiFunction<K, V, V> func)  			Replaces each value with the results of the function. 
		//int size()  											Returns the number of entries (key/value pairs) in the map. 
		//Collection<V> values()  								Returns Collection of all values.

		//basic methods
		
		Map<String, String> hashMap = new HashMap<>();
		hashMap.put("koala", "bamboo");
		hashMap.put("lion", "meat");
		hashMap.put("giraffe", "leaf");
		String food = hashMap.get("koala"); // bamboo
		for (String key: hashMap.keySet())
		   System.out.print(key + ","); // koala,giraffe,lion,

		Map<String, String> treeMap = new TreeMap<>();
		treeMap.put("koala", "bamboo");
		treeMap.put("lion", "meat");
		treeMap.put("giraffe", "leaf");
		String food1 = treeMap.get("koala"); // bamboo
		for (String key: treeMap.keySet())
		   System.out.print(key + ","); // giraffe,koala,lion,

		//System.out.println(treeMap.contains("lion")); // DOES NOT COMPILE
		System.out.println(treeMap.containsKey("lion")); // true
		System.out.println(treeMap.containsValue("lion")); // false
		System.out.println(treeMap.size()); // 3
		treeMap.clear();
		System.out.println(treeMap.size()); // 0
		System.out.println(treeMap.isEmpty()); // true
		
		//forEach() and entrySet()
		
		treeMap.keySet().forEach(System.out::println);
		treeMap.values().forEach(System.out::println);
		treeMap.forEach((k, v) -> System.out.println(k + v));		
		treeMap.entrySet().forEach(e->System.out.println(e.getKey() + e.getValue()));
		
		//V getOrDefault(Object key, V defaultValue)  			Returns the value mapped by the key or the default value if none is mapped. 
		Map<Character, String> map4 = new HashMap<>();
		map4.put('x', "spot");
		System.out.println("X marks the " + map4.get('x'));					//spot
		System.out.println("X marks the " + map4.getOrDefault('x', ""));		//spot
		System.out.println("Y marks the " + map4.get('y'));					//null
		System.out.println("Y marks the " + map4.getOrDefault('y', "v"));	//v

		//V replace(K key, V value)  							Replaces the value for a given key if the key is set. Returns the original value or null if none. 
		//void replaceAll(BiFunction<K, V, V> func)  			Replaces each value with the results of the function. 

		Map<Integer, Integer> map5 = new HashMap<>();
		map5.put(1, 2);
		map5.put(2, 4);
		Integer original = map5.replace(2, 10); // 4
		System.out.println(map5);    // {1=2, 2=10}

		map5.replaceAll((k, v) -> k + v);
		System.out.println(map5);    // {1=3, 2=12}

		//V putIfAbsent(K key, V value)  						Adds value if key not present and returns null. Otherwise, returns existing value. 
		
		Map<String, String> favorites1 = new HashMap<>();
		favorites1.put("Jenny", "Bus Tour");
		favorites1.put("Tom", null);
		favorites1.putIfAbsent("Jenny", "Tram");
		favorites1.putIfAbsent("Sam", "Tram");
		favorites1.putIfAbsent("Tom", "Tram");
		System.out.println(favorites1); // {Tom=Tram, Jenny=Bus Tour, Sam=Tram}

		//V merge(K key, V value, Function(<V, V, V> func))  	Sets value if key not set. Runs the function if the key is set to determine the new value. Removes if null. 

		BiFunction<String, String, String> mapper = (v1, v2)-> v1.length()> v2.length() ? v1: v2;
		Map<String, String> favorites2 = new HashMap<>();
		favorites2.put("Jenny", "Bus Tour");
		favorites2.put("Tom", "Tram");
		
		String jenny = favorites2.merge("Jenny", "Skyride", mapper);
		String tom = favorites2.merge("Tom", "Skyride", mapper);
		
		System.out.println(favorites2); // {Tom=Skyride, Jenny=Bus Tour}
		System.out.println(jenny);     // Bus Tour
		System.out.println(tom);       // Skyride

		//BiFunction<String, String, String> mapper = (v1, v2) -> v1.length()> v2.length() ? v1 : v2;
		Map<String, String> favorites3 = new HashMap<>();
		favorites3.put("Sam", null);
		favorites3.merge("Tom", "Skyride", mapper);
		favorites3.merge("Sam", "Skyride", mapper);
		System.out.println(favorites3);   // {Tom=Skyride, Sam=Skyride}
		//Notice that the mapping function isn't called. If it were, we'd have a NullPointerException. 
		//The mapping function is used only when there are two actual values to decide between.

		BiFunction<String, String, String> mapper2 = (v1, v2) -> null;
		Map<String, String> favorites4 = new HashMap<>();
		favorites4.put("Jenny", "Bus Tour");
		favorites4.put("Tom", "Bus Tour");
		 
		favorites4.merge("Jenny", "Skyride", mapper2);
		favorites4.merge("Sam", "Skyride", mapper2);
		System.out.println(favorites4);   // {Tom=Bus Tour, Sam=Skyride}
		//The final thing to know about merge() is what happens when the mapping function is called and returns null. 
		//The key is removed from the map when this happens if the key exists.
		//Tom was left alone since there was no merge() call for that key. Sam was added since that key was not 
		//in the original list. Jenny was removed because the mapping function returned null.
		
		//comparing Collection Types.

		//the data structures that involve sorting do not allow null values.
		/*
			Set<String> set = new TreeSet<>();
			set.add("s1");
			set.add("s2");
			set.add(null); //NullPointerException at runtime.
		 */

		//Sorting Data
		
		//creating a Comparable class
		
		//Comparable interface
		
		/*
		 	public interface Comparable<T> {
   				int compareTo(T o);
			}
			
			1.	The number 0 is returned when the current object is equivalent to the argument to compareTo(). 
			2.	A negative number (less than 0) is returned when the current object is smaller than the argument to compareTo(). 
			3.	A positive number (greater than 0) is returned when the current object is larger than the argument to compareTo().

       	*/
		
		//checking for null
		//when overriding the compareTo you should check the data before comparing it if is not validated ahead of time.
		
		//keeping compareTo() and equals() consistent.
		//when compareTo() return 0 the equals() needs to return true. so hashCode also needs to be overridden.
		
		//Comparing data with a Comparator.
		
		/*
		 	public interface Comparator<T> {
				int compare(T t1, T t2);
			}
		
			1.	The number 0 is returned when the current object is equivalent to the argument to compareTo(). 
			2.	A negative number (less than 0) is returned when the current object is smaller than the argument to compareTo(). 
			3.	A positive number (greater than 0) is returned when the current object is larger than the argument to compareTo().

		*/
		
		Comparator<String> longerStr1 = (s1, s2) -> s1.length()-s2.length();
		Comparator<String> longerStr2 = Comparator.comparing(String::length);
		
		Comparator<String> sc1 = Comparator.naturalOrder();
		Comparator<String> sc2 = Comparator.reverseOrder();
		
		
		//Comparing Comparable and Comparator:
		
		//Comparable and Comparator are all Functional Interface
		//Comparable is normally used to be implemented inside the object being compared.
		//Comparator is normally used as a lambda expression.
		
		//comparing multiple fields.
		
		@Data
		class Squirrel {
		   private int weight;
		   private String species;
		}

		class MultiFieldComparator implements Comparator<Squirrel> {
			public int compare(Squirrel s1, Squirrel s2) {
				int result = s1.getSpecies().compareTo(s2.getSpecies());
				if (result != 0) return result;
			    return s1.getWeight()-s2.getWeight();
			}
		}

		Comparator<Squirrel> c1 = Comparator.comparing(Squirrel::getSpecies).thenComparingInt(Squirrel::getWeight);

		//Suppose we want to sort in descending order by species.
		var c2 = Comparator.comparing(Squirrel::getSpecies).reversed();
		var c3 = Comparator.comparing(Squirrel::getSpecies).reversed().thenComparing(Squirrel::getWeight);

		//Sorting and Searching
		//Collections.sort(someObject); if someObject does not implement Comparable the compile error will be caused.
		//Collections.sort(someObject, Comparator);
		
		//The sort() and binarySearch() methods allow you to pass in a Comparator object when you don't want to use the natural order.

		//static <T extends Comparable<? super T>> void sort(List<T> list);
		//static <T> void	sort(List<T> list, Comparator<? super T> c);		

		//static <T> int	binarySearch(List<? extends Comparable<? super T>> list, T key);
		//static <T> int	binarySearch(List<? extends T> list, T key, Comparator<? super T> c);
		
		//The binarySearch() method requires a sorted List.

		List<Integer> list7 = Arrays.asList(6,9,1,8);
		Collections.sort(list7); // [1, 6, 8, 9]
		System.out.println(Collections.binarySearch(list7, 6)); // 1
		System.out.println(Collections.binarySearch(list7, 3)); // -2

		//when you binarySearch an unsorted list the result will be not defined.
		//sort and binarySearch need to use the same Comparator to get right result.
		
		//Working with Generics
		
		//Generic classes
		
		class Holder1<T> {
			private T content;
			
			public T getContents() {
				return this.content;
			}
			
			public void setContent(T content) {
				this.content = content;
			}
			
		}
		
		class Holder2<U,V> {
			private U uContent;
			private V vContent;
			
			public U getContents() {
				return this.uContent;
			}
			
			public void setContent(V vContent) {
				this.vContent = vContent;
			}			
		}
		
		Holder1<String> h1 = new Holder1<>();
		Holder2<String, Number> h2 = new Holder2<>();
		
		//Type Erasure
		//Generics were introduced to the Java language to provide tighter type checks at compile time and 
		//to support generic programming. To implement generics, the Java compiler applies type erasure to:

		//1.	Replace all type parameters in generic types with their bounds or Object if the type parameters are unbounded. 
		//		The produced bytecode, therefore, contains only ordinary classes, interfaces, and methods.
		//2.	Insert type casts if necessary to preserve type safety.
		//3.	Generate bridge methods to preserve polymorphism in extended generic types.

		//Type erasure ensures that no new classes are created for parameterized types; consequently, 
		//generics incur no runtime overhead.
		
		//Generic interface
		
		//what you can't do with generic Types
		
		//1.	Calling a constructor: Writing new T() is not allowed because at runtime it would be new Object(). 
		//2.	Creating an array of that generic type: This one is the most annoying, 
		//		but it makes sense because you'd be creating an array of Object values. 
		//3.	Calling instanceof: This is not allowed because at runtime List<Integer> 
		//		and List<String> look the same to Java thanks to type erasure. 
		//4.	Using a primitive type as a generic type parameter: This isn't a big deal because 
		//		you can use the wrapper class instead. If you want a type of int, just use Integer. 
		//5.	Creating a static variable as a generic type parameter: 
		//		This is not allowed because the type is linked to the instance of the class.
		
		//Generic Methods
		
		SubBird3<String,Number> bs = new SubBird3<>();
		bs.<String>fly("tom");
		bs.fly("stom");
		bs.<Number>test2(12);
		//bs.<Number>test2("12");   //do not compile
		bs.test2("10");
		
		//Bounding Generic Types
		//A wildcard generic type is an unknown generic type represented with a question mark ( ?).
		
		//Type of bound 					Syntax 				Example 
		//Unbounded wildcard 				?  					List<?> a = new ArrayList<String>();  
		//Wildcard with an upper bound 		? extends type  	List<? extends Exception> a = new ArrayList<RuntimeException>();  
		//Wildcard with a lower bound 		? super type  		List<? super Exception> a = new ArrayList<Object>();
		
		List<?> gList11 = new ArrayList<Object>();
		//gList11.add("test1");				//do not compile
		//gList11.add(longerStr2);			//do not compile
		gList11.add(null);
		
		List<?> gList12 = new ArrayList<Number>();
		//gList12.add(12);				//do not compile
		//gList12.add(20.0);			//do not compile
		
		//List<?> is immutable list except adding a null
		
		List<? extends Number> gList31 = new ArrayList<Number>(); 
		List<? extends Number> gList32 = new ArrayList<Integer>(); 
		List<? extends Number> gList33 = new ArrayList<Double>(); 
		
		//gList31.add(12);				//do not compile
		//gList31.add(20.0);				//do not compile
		//gList32.add(10);				//do not compile
		//gList33.add(30.0);				//do not compile
		
		//List<? extends X> : Upper-Bounded Wildcards is immutable list except adding a null
		
		//Upper bounded and unbounded wildcard collections are immutable.
		
		List<? super Integer> a1 = new ArrayList<>();
		List<? super Integer> a2 = new ArrayList<Integer>();
		List<? super Integer> a3 = new ArrayList<Number>();
		List<? super Integer> a4 = new ArrayList<Object>();
		a1.add(12);
		//a1.add(20.0);				//do not compile
		
		List<? super Number> a11 = new ArrayList<>();
		List<? super Number> a13 = new ArrayList<Number>();
		List<? super Number> a14 = new ArrayList<Object>();
		a11.add(12);
		a11.add(20.0);
		a11.add(20L);
		a11.add(40.0f);
		
		
		List<? super IOException> exceptions = new ArrayList<Exception>();
	    //exceptions.add(new Exception()); // DOES NOT COMPILE
	    exceptions.add(new IOException());
	    exceptions.add(new FileNotFoundException());
		
	    class A {}
	    class B extends A {}
	    class C extends B {}

	    List<?> list1 = new ArrayList<A>();
	    List<? extends A> list2 = new ArrayList<A>();
	    List<? super A> list3 = new ArrayList<A>();

	    //List<? extends B> list4 = new ArrayList<A>(); // DOES NOT COMPILE
	    List<? super B> list51 = new ArrayList<A>();
	    //List<?> list6 = new ArrayList<? extends A>(); // DOES NOT COMPILE

	    
	}
	
	public static <T> void prepare(T t) {
		System.out.println("Preparing " + t);
	}
	
	public static <T> Bird<T> fly(T t) {
		return new Bird<T>();
	}
	
	public <T> void preparex(T t) {
		System.out.println("Preparingx " + t);
	}
	
	public <T> Bird<T> flyx(T t) {
		return new Bird<T>();
	}
	
	public static <T> void sink(T t) { }
	public static <T> T identity(T t) { return t; }
	//public static T noGood(T t) { return t; } // DOES NOT COMPILE
	
	public <T> T first1(List<? extends T> list) {   return list.get(0);	}
	//public <T> T first2(List<? super T> list) {   return list.get(0);	} 	// DOES NOT COMPILE
	//public <T> <? extends T> second(List<? extends T> list) { return list.get(0); } 	// DOES NOT COMPILE	
	//public <T> <? super T> third(List<? extends T> list) { return list.get(0); } 		// DOES NOT COMPILE
		   
	public void first3(List<? super B> list) {}
	//public <X> void first4(List<X super B> list) {	} 	// DOES NOT COMPILE

	//<B extends Ax> B third1(List<B> list) {return new B(); }  // DOES NOT COMPILE
	//<B super C> B third2(List<B> list) {return new B(); }  // DOES NOT COMPILE

	public static <U extends Exception> void printException(U u) {
	         System.out.println(u.getMessage());
	}


	private static void testTest() {
		
		out.println("--------------testTest-----------");
		
		final var x = 10;
		
		var greetings = new LinkedList<String>();
		    greetings.offer("hello");
		    greetings.offer("hi");
		    greetings.offer("ola");
		    greetings.pop();
		    greetings.peek();
		    while (greetings.peek() != null)
		      System.out.print(greetings.pop());


		  var map = new HashMap<Integer, Integer>(10);
		for (int i = 1; i <= 10; i++) {
		    map.put(i, i * i);
		 }
		
		System.out.println(map.get(4));
		
		Comparator<Integer> c1 = (o1, o2) -> o2 - o1;
		  Comparator<Integer> c2 = Comparator.naturalOrder();
		  Comparator<Integer> c3 = Comparator.reverseOrder();
		 
		  var list = Arrays.asList(5, 4, 7, 2);
		  
		  Collections.sort(list,c1);
		  System.out.println(list);
		  System.out.println(Collections.binarySearch(list, 2));
		  
		  Collections.sort(list,c1);
		  System.out.println(list);
		  System.out.println(Collections.binarySearch(list, 2, c1));

		  Collections.sort(list,c2);
		  System.out.println(list);
		  System.out.println(Collections.binarySearch(list, 2));
		  
		  Collections.sort(list,c3);
		  System.out.println(list);
		  System.out.println(Collections.binarySearch(list, 2));
		  
		  Collections.sort(list,c3);
		  System.out.println(list);
		  System.out.println(Collections.binarySearch(list, 2, c3));



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
		  
		  class W {}
		  class X extends W {}
		  class Y extends X {}
		  class Z<Y> {

			  W w1 = new W();
			  W w2 = new X();

		  }

		  List<Integer> q = new LinkedList<>();
		  q.add(10);
		  q.add(12);
		  q.remove(1);
		  System.out.println(q);

		  Map m = new HashMap();
		  m.put(123, "456");
		  m.put("abc", "def");
		  //System.out.println(m.contains("123"));

		  var mapx = Map.of(1,2,3,4);
		  var listx = List.copyOf(mapx.entrySet());
		  System.out.println(listx);
		  List<Integer> one = List.of(8, 16, 2);
		   var copy = List.copyOf(one);
		   var copyOfCopy = List.copyOf(copy);
		   var thirdCopy = new ArrayList<>(copyOfCopy);
		   //listx.replaceAll(x1 -> x1 * 2);
		   one.replaceAll(x2 -> x2 * 2);
		   thirdCopy.replaceAll(x3 -> x3 * 2);
		   
		   System.out.println(thirdCopy);

		
	
	}
	
}

class Ax {}
class B extends Ax {}
class C extends B {}

@Data
class Bird<T> {
	private T type;
	
	//T() {}								//do not compile
	//T[] tArray = new T[10];				//do not compile
	//T instanceof Object       			//do not compile
	//List<int> iList = new ArrayList<>();	//do not compile
	//static T st;    						//do not compile
	
	@Override
	public String toString() {
		return type.toString();
	}
}

interface Fly<T> {
	void fly(T t);
}

class SubBird1 implements Fly<SubBird1> {
	@Override
	public void fly(SubBird1 t) {		
	}	
}

class SubBird2<T> implements Fly<T> {
	@Override
	public void fly(T t) {		
	}	
}

class SubBird3<U,V> implements Fly<U> {
	private V value;
	
	@Override
	public void fly(U u) {		
	}	
	
	public V test() {
		return this.value;
	}
	
	public <T> T test2(T t) {
		return t;
	}
}

    