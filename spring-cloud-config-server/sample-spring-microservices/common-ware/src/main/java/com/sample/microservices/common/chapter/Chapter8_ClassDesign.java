package com.sample.microservices.common.chapter;

import static java.lang.System.out;

public class Chapter8_ClassDesign {


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

		//Inheritance
		//Single vs Multiple Inheritances
		//inheriting Object
		//creating classes
		//extending class (extends)
		
		//-----------Access modifiers (required but empty is Default)
		//Top-level class can only have Default(Package-private) and public
		//Inner class can have public,protected, Default, and private since it is the member of another class
		
		//this Reference
		//super reference -- you often only use super when you have a naming conflict via inheritance.

		//constructor -- can have multiple overloading constructors
		
		//var is used for local variable, which could not be used in Method's and Constructor's parameters
		
		//default constructor
		//default constructor is added by compiler anytime a class is declared without any constructors. 
		//We often refer to it as the default no-argument constructor for clarity.
		//default constructor has the same access level as the class, so public in a public class, private in a private class
		
		//calling overloaded constructors with this()
		//this() call must be the first statement in the constructor. that means there can be only on call to this() in any constructor.
		//the constructor call itself using this() will not compile since it will cause infinite looping.
		//constructors call each other using this() will also not compile since it will cause infinite looping too.
		
		//calling parent constructors with super()
		//super() call must be the first statement in the constructor. that means there can be only on call to super() in any constructor.
		//very constructor will call this() or super() as the first statement. if no explicit call compiler will insert a
		//no-argument super() call.
		
		//super() always refers to the most direct parent.
		
		//constructors and final fields
		//final variables have to be assigned a value, once the value is assigned, it cannot be changed. 
		//There is one more place they can be assigned a value—the constructor. 
		//The constructor is part of the initialization process, so it is allowed to assign final instance variables in it.

		//be wary of any instance variables marked final. Make sure they are assigned a value in the line 
		//where they are declared, in an instance initializer, or in a constructor. They should be assigned a value only once, 
		//and failure to assign a value is considered a compiler error in the constructor.

		//order of initialization
		
		//class initialization
		//Initialize Class X 
		//1. If there is a superclass Y of X, then initialize class Y first. 
		//2. Process all static variable declarations in the order they appear in the class. 
		//3. Process all static initializers in the order they appear in the class.

		//instance initialization
		//an instance is initialized any time the new keyword is used.
		//Initialize Instance of X 
		//1. If there is a superclass Y of X, then initialize the instance of Y first.
		//2. Process all instance variable declarations in the order they appear in the class. 
		//3. Process all instance initializers in the order they appear in the class. 
		//4. Initialize the constructor including any overloaded constructors referenced with this().

		//constructor rules:
		//1.	The first statement of every constructor is a call to an overloaded constructor via this(), 
		//		or a direct parent constructor via super(). 
		//2.	If the first statement of a constructor is not a call to this() or super(), 
		//		then the compiler will insert a no-argument super() as the first statement of the constructor. 
		//3.	Calling this() and super() after the first statement of a constructor results in a compiler error. 
		//4.	If the parent class doesn’t have a no-argument constructor, 
		//		then every constructor in the child class must start with an explicit this() or super() constructor call.
		//5.	If the parent class doesn’t have a no-argument constructor and the child doesn’t define any constructors, 
		//		then the child class will not compile. 
		//6.	If a class only defines private constructors, then it cannot be extended by a top-level class. 
		//7.	All final instance variables must be assigned a value exactly once by the end of the constructor. 
		//		Any final instance variables not assigned a value will be reported as a compiler error 
		//		on the line the constructor is declared.
		
		//inheriting members
		//calling inherited members
		//inheriting methods
		
		//overriding a method
		//overriding a method occurs when a subclass declares a new implementation for an inherited method 
		//with the same signature and compatible return type. Remember that a method signature includes 
		//the name of the method and method parameters.
		
		//rules overriding a method 
		//1. 	The method in the child class must have the same signature as the method in the parent class.
		//2. 	The method in the child class must be at least as accessible as the method in the parent class.
		//3. 	The method in the child class may not declare a checked exception that is new or broader than 
		//		the class of any exception declared in the parent class method. 
		//4.	If the method returns a value, it must be the same or a subtype of the method in the parent class, 
		//		known as covariant return types.
		//		Given an inherited return type A and an overriding return type B, 
		//		can you assign an instance of B to a reference variable for A without a cast? 
		//		If so, then they are covariant. this also applies to primitive type

		//overriding a Generic Method
		//Generic Method Parameters.
		//Generic type parameters have to match for overriding a generic method. if generic class or interface are
		//different that will be overloading not overriding.
		
		//generic return types.
		
		/*
		
		//overriding:
		  
			public class LongTailAnimal {
			   protected void chew(List<String> input) {}
			}
			 
			public class Anteater extends LongTailAnimal {
			   protected void chew(List<String> input) {}
			}

		//overloading:

			public class LongTailAnimal {
			   protected void chew(List<Object> input) {}
			}
			 
			public class Anteater extends LongTailAnimal {
			   protected void chew(ArrayList<Double> input) {}
			}

		//returning type for overriding:
		 
			public class Mammal {
			   public List<CharSequence> play() { ... }
			   public CharSequence sleep() { ... }
			}
			
			public class Monkey extends Mammal {
			   public ArrayList<CharSequence> play() { ... }
			}
			 
			public class Goat extends Mammal {
			   public List<String> play() { ... }  // DOES NOT COMPILE
			   public String sleep() { ... }
			}
			
			it might be helpful for you to apply type erasure to questions involving generics to ensure 
			that they compile properly. Once you’ve determined which methods are overridden and 
			which are being overloaded, work backward, making sure the generic types match for overridden methods. 
			And remember, generic methods cannot be overloaded by changing the generic parameter type only.

		*/
		
		//redeclaring private methods
		//since the private is not inherited so no overriding or overloading involved. they are totally independent methods
		//although they may have the same name.
		
		//hiding static methods.
		//a hidden method occurs when a child class define a static method for overriding. so all the four rules of overriding 
		//a method must be followed with the following additional rule 5.
		//5.	The method defined in the child class must be marked as static if it is marked as static in a parent class.
		//Put simply, it is method hiding if the two methods are marked static, and method overriding 
		//if they are not marked static. If one is marked static and the other is not, the class will not compile.

		//creating final methods.
		//By marking a method final, you forbid a child class from replacing this method no matter it is overriding 
		//or hiding (static).

		//hiding variables.
		//A hidden variable occurs when a child class defines a variable with the same name as an 
		//inherited variable defined in the parent class. This creates two distinct copies of the variable 
		//within an instance of the child class: one instance defined in the parent class and 
		//one defined in the child class.

		//understanding polymorphism
		//Once the object has been assigned to a new reference type, only the methods and variables 
		//available to that reference type are callable on the object without an explicit cast.

		//Object vs. Reference
		//1.	The type of the object determines which properties exist within the object in memory.

		//2.	The type of the reference to the object determines which methods and 
		//		variables are accessible to the Java program.
		
		//casting Objects
		//rules:
		//1.	Casting a reference from a subtype to a supertype doesn’t require an explicit cast. 
		//2.	Casting a reference from a supertype to a subtype requires an explicit cast.
		//3.	The compiler disallows casts to an unrelated class. 
		//4.	At runtime, an invalid cast of a reference to an unrelated type results in a ClassCastException being thrown.
		
		//The instanceof Operator
		//(instance instanceof Type) (class/interface name)
		//the compile does not allow instanceof to be used with unrelated types.
		
		//Polymorphism and Mehtod Overriding.
		
		//Overriding vs Hiding Members.
		
		
		
		

		
	}
	
	private static void testTest() {

/*
		1.	e
		2.	bc
		3.	b
		4.	e
		5.	g
		6.	bce
		7.	ac
		8.	c
		9.	bf
		10.	d
		11.	bce
		12.	abef
		13.	ag
		14.	bef
		15.	d
		16.	b
		17.	f
		18.	cf
		19.	cf
		20.	f
		21.	e
		22.	d
		23.	b
		24.	adf
		25.	c
		26.	f

*/
		
		out.println("--------------testTest-----------");
		//10.
		//default constructor has the same access scope as the class itself.


		
	}
	
	//11. using class name.
	private static void testStatic() {
		out.println("static ok");
	}
	
	//20.
	//public void roar(long... longs){};
	//public void roar(long long) {};

}

class Gopher {
	   public Gopher(int dugHoles) {
//	      this(5);  // DOES NOT COMPILE
	   }
}

class Gopher2 {
	   public Gopher2() {
//	      this(5);  // DOES NOT COMPILE
	   }
	   public Gopher2(int dugHoles) {
//	      this();   // DOES NOT COMPILE
	   }
}

class Test {
	int x = 10;
	Object o;
	public Integer y;
	
}

class SubTest extends Test {
	int x = 20;
	String o;
	protected String y;
}