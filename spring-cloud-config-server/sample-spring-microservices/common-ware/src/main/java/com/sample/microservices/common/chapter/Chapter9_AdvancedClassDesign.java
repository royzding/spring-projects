package com.sample.microservices.common.chapter;

import static java.lang.System.out;

public class Chapter9_AdvancedClassDesign {


	public static void main(String[] args) {

		testTemplet();
		TestClass();
		
		testTest();
		
		Test t = new Test();
		SubTest st = new SubTest();
		
		Test ct = st;
		
		
	}

	private static void testTemplet() {
		
	}
	
	private static void TestClass() {
		
		out.println("--------------TestClass-----------");

		//abstract class
		//abstract method
		//placement of the abstract modifier
		
		/*

			abstract public class Tiger {
			   abstract public int claw();
			}
			
			public class abstract Jackal {  // DOES NOT COMPILE
			   public int abstract howl();  // DOES NOT COMPILE
			}

		 */
		
		//constructors in abstract classes
		//remember that abstract classes are initialized with constructors in the same way as nonabstract classes. 
		//For example, if an abstract class does not provide a constructor, the compiler will automatically 
		//insert a default no-argument constructor. 
		//The primary difference between a constructor in an abstract class and a nonabstract class is 
		//that a constructor in abstract class can be called only when it is being initialized 
		//by a nonabstract subclass. This makes sense, as abstract classes cannot be instantiated.

		//abstract and final modifiers
		//Java does not permit a class or method to be marked both abstract and final.
		
		//abstract and private modifiers
		//a method cannot be marked as both abstract and private
		//it is possible to declare a method final and private
		
		//abstract and static modifiers
		//a static method cannot be overridden. it is defined as belonging to the class, not an instance of the class.
		//If a static method cannot be overridden, then it follows that it also cannot be marked abstract 
		//since it can never be implemented.

		/*
			public abstract final class Tortoise {  // DOES NOT COMPILE
			   public abstract final void walk();   // DOES NOT COMPILE
			   
			   private abstract void sing();  // DOES NOT COMPILE

			   abstract static void swim();  // DOES NOT COMPILE

			}


		 */

		//creating a concrete class
		
		//abstract class rules.
		//1. 	Abstract classes cannot be instantiated.
		//2.	All top-level types, including abstract classes, cannot be marked protected or private. 
		//3.	Abstract classes cannot be marked final. 
		//4.	Abstract classes may include zero or more abstract and nonabstract methods. 
		//5.	An abstract class that extends another abstract class inherits all of its abstract methods. 
		//6.	The first concrete class that extends an abstract class must provide an implementation 
		//		for all of the inherited abstract methods. 
		//7.	Abstract class constructors follow the same rules for initialization as regular constructors, 
		//		except they can be called only as part of the initialization of a subclass.

		//abstract method rules.
		//1.	Abstract methods can be defined only in abstract classes or interfaces. 
		//2.	Abstract methods cannot be declared private or final. 
		//3.	Abstract methods must not provide a method body/implementation in the abstract class 
		//		in which they are declared. 
		//4.	Implementing an abstract method in a subclass follows the same rules for overriding a method, 
		//		including covariant return types, exception declarations, etc.

		//implementing interfaces
		
		//Like a class, an interface can extend another interface using the extends keyword.
		//Unlike a class, which can extend only one class, an interface can extend multiple interfaces.

		/*
			interface Nocturnal {
			   public int hunt();
			}
			 
			interface CanFly {
			   public void flap();
			}
			 
			interface HasBigEyes extends Nocturnal, CanFly {}
			 
			public class Owl implements Nocturnal, CanFly {
			
			   public int hunt() { return 5; }
			   public void flap() { System.out.println("Flap!"); }
			   
			}

		 */
		
		//rules for interfaces
		//1.	A Java file may have at most one public top-level class or interface, 
		//		and it must match the name of the file. 
		//2.	A top-level class or interface can only be declared with public or package-private access.
		//3.	Just remember that an interface does not follow the same rules for single inheritance 
		//		and instance initialization with constructors, as a class does.

		//inserting implicit modifiers
		//1.	Interfaces are assumed to be abstract. 
		//2.	Interface variables are assumed to be public, static, and final. 
		//3.	Interface methods without a body are assumed to be abstract and public.

		//conflicting modifiers
		
		/*
			public interface Dance {
			   private int count = 4;  // DOES NOT COMPILE
			   protected void step();  // DOES NOT COMPILE
			}
		
		 */
		
		//inheriting an interface
		//An interface can be inherited in one of three ways. 
		//1.	An interface can extend another interface. 
		//2.	A class can implement an interface. 
		//3.	A class can extend another class whose ancestor implements an interface.
		
		//mixing class and interface keywords
		
		/*
			public interface CanRun {}
			public class Cheetah extends CanRun {}   // DOES NOT COMPILE
			 
			public class Hyena {}
			public interface HasFur extends Hyena {} // DOES NOT COMPILE

			class1 extends class2
			interface1 extends interface2, interface3, ...
			class1 implements interface2, interface3, ...

		 */

		//duplicate interface method declarations
		
		//1. 	identical method declarations (compatible) --You can define a class that fulfills both interfaces simultaneously.
		//2. 	if the duplicate methods have different signatures? If the method name is the same 
		//		but the input parameters are different, there is no conflict because this is considered a method overload.
		//3.	if the duplicate methods have the same signature but different return types? 
		//		In that case, you need to review the rules for overriding methods.
		//4.	if the duplicate methods are incompatible methods there will be compile error.

		/*
		 	//overriding ok:
		 	 
			interface Dances {
			   String swingArms();
			}
			interface EatsFish {
			   CharSequence swingArms();
			}
			 
			public class Penguin implements Dances, EatsFish {
			   public String swingArms() {
			      return "swing!";
			   }
			}

			//no way for overriding.

			interface Dances {
			   int countMoves();
			}
			interface EatsFish {
			   boolean countMoves();
			}
			 
			public class Penguin implements Dances, EatsFish { // DOES NOT COMPILE
				...
			}

			interface LongEars {
			   int softSkin();
			}
			interface LongNose {
			
			void softSkin();
			}
			 
			interface Donkey extends LongEars, LongNose {}  // DOES NOT COMPILE
			 
			abstract class Aardvark implements LongEars, LongNose {}    // DOES NOT COMPILE

		 */
		
		//casting interfaces.
		//With interfaces, there are limitations to what the compiler can validate.
		//the following code allows the invalid cast to the Dog reference type, 
		//even though Dog and Wolf are not related. The code compiles but throws a ClassCastException at runtime.
		
		//This limitation aside, the compiler can enforce one rule around interface casting. 
		//The compiler does not allow a cast from an interface reference to an object reference if the
		//object type does not implement the interface.
		//Since String does not implement Canine, the compiler recognizes that this cast is not possible.

		/*
			interface Canine {}
			class Dog implements Canine {}
			class Wolf implements Canine {}
			public class BadCasts {
			   public static void main(String[] args) {
			       Canine canine = new Wolf();
			      Canine badDog = (Dog)canine;
			    } 
			}
			
			Object badDog = (String)canine;  // DOES NOT COMPILE
		
		 */

		//interfaces and the instanceof operator
		//With interfaces, the compiler has limited ability to enforce this rule 
		//because even though a reference type may not implement an interface, one of its subclasses could.

		/*
		 
			Number tickets = 5;
			if(tickets instanceof List) {}
			
			public class MyNumber extends Number implements List

		 */
		
		//interface definition rules
		//1.	Rules  Interfaces cannot be instantiated. 
		//2.	All top-level types, including interfaces, cannot be marked protected or private. 
		//3.	Interfaces are assumed to be abstract and cannot be marked final. 
		//4.	Interfaces may include zero or more abstract methods. 
		//5.	An interface can extend any number of interfaces. 
		//6.	An interface reference may be cast to any reference that inherits the interface, 
		//		although this may produce an exception at runtime if the classes aren’t related. 
		//7.	The compiler will only report an unrelated type error for an instanceof operation 
		//		with an interface on the right side if the reference on the left side is a final class 
		//		that does not inherit the interface.
		//8.	An interface method with a body must be marked default, private, static, or private static

		//abstract interface method rules
		//1.	Abstract methods can be defined only in abstract classes or interfaces. 
		//2.	Abstract methods cannot be declared private or final. 
		//3.	Abstract methods must not provide a method body/implementation in the abstract class in which is it declared. 
		//4.	Implementing an abstract method in a subclass follows the same rules for overriding a method, 
		//		including covariant return types, exception declarations, etc.
		//5.	Interface methods without a body are assumed to be abstract and public.
		
		//interface variable rules
		//1.	Interface variables are assumed to be public, static, and final. 
		//2.	Because interface variables are marked final, they must be initialized with a value when they are declared.
		
		//interface vs abstract class
		//1.	that interfaces include implicit modifiers, 
		//2.	do not contain constructors, 
		//3.	do not participate in the instance initialization process, 
		//4.	and support multiple inheritance.

		//defining a member inner class
		
		//A member inner class is a class defined at the member level of a class 
		//(the same level as the methods, instance variables, and constructors). 
		//It is the opposite of a top-level class, in that it cannot be declared unless it is inside another class.
		
		//While top-level classes and interfaces can only be set with public or package-private access, 
		//member inner classes do not have the same restriction. A member inner class can be declared 
		//with all of the same access modifiers as a class member, such as public, protected, default (package-private), 
		//or private.
		
		//A member inner class can contain many of the same methods and variables as a top-level class. 
		//Some members are disallowed in member inner classes, such as static members.
		
		//using a member inner class
		
		//One of the ways a member inner class can be used is by calling it in the outer class.
		//The advantage of using a member inner class is that the out class completely 
		//manages the life cycle of the inner class.


	}
	
	private static void testTest() {

/*
		1.	be
		2.	abde
		3.	bc
		4.	f(e)
		5.	cf
		6.	de
		7.	b(c)
		8.	bc(c)
		9.	bcdef(bcef)
		10.	g(cg)
		11.	abf
		12.	ade(ae)
		13.	g
		14.	ce(ace)
		15.	d
		16.	e
		17.	ae
		18.	abd
		19.	bef(aef)
		20.	d(ad)

*/
		
		out.println("--------------testTest-----------");
		//10.
		//default constructor has the same access scope as the class itself.


		
	}
	
	//11. using class name.
	private final static void testStatic() {
		out.println("static ok");
	}
	
	//20.
	//public void roar(long... longs){};
	//public void roar(long long) {};

}
