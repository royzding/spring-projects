package com.sample.microservices.common.chapter;

import static java.lang.System.out;

public class Chapter7_MethodAndEncapsulation {

	private static final int NUM_SECONDS_PER_MINUTE;
	private static final int NUM_MINUTES_PER_HOUR;
	private static final int NUM_SECONDS_PER_HOUR;
	
	static {
	   NUM_SECONDS_PER_MINUTE = 60;
	   NUM_MINUTES_PER_HOUR = 60;
	   
	   int sxs = 10;
	}
	
	static {
	   NUM_SECONDS_PER_HOUR
	      = NUM_SECONDS_PER_MINUTE * NUM_MINUTES_PER_HOUR;
	}


	public static void main(String[] args) {

		testTemplet();
		testMethod();
		
		testTest();
		
		
		testOverloading(1,2l);
		
		testVarargs(1,2f);
		
		Chapter7_MethodAndEncapsulation x = null;
		x.testStatic();
	}

	private static void testOverloading(int i, long l) {
		out.println("int,long");		
	}
	
	private static void testOverloading(long l, int i) {
		out.println("long, int");		
	}
	
	private static void testOverloading(long i, long l) {
		out.println("long,long");		
	}
	
	//private static var testVar() {
	//	return 12;
	//}
	
	private static void testTemplet() {
		
	}
	
	final private void t() {return;}
	
	public static void testVarargs(int i, float f, double... d) {
		out.println("--------------testVarargs-----------");
		
		out.println(d.length);
		
		for(int x=0; x>0; x++) {
			out.println(d[x]);
		}
		
		for(double dx : d) {
			out.println(dx);
		}
		
		double[] dAr = new double[10];
		for(double xd : dAr) {
			
		}
	}
	
	private static void testMethod() {
		
		out.println("--------------testMethod-----------");

		//-----------Access modifiers (required but empty is Default)
		//private, Default(Package-private), protected, and public
		
		//-----------Optional Specifiers (Optional)
		//static, abstract, final, synchronized, native, and strictfp
		
		//-----------return type (required)
		//primitive, object, and void
		//when returning a value, it needs to be assignable to the return type.
		
		//parameter list (required but can be empty)
		
		//optional Exception List (throws e1,e2)
		
		//method body (required but can be empty)
		
		//-----------Varargs: 
		//A varargs parameter must be the last element in a method’s parameter list. 
		//This means you are allowed to have only one varargs parameter per method.
		//Accessing a varargs parameter is just like accessing an array. It uses array indexing.

		//-----------static
		
		//static class/method/field
		
		//final static int iv=12; //as constant
		
		//static initialization
		
		//All static initializers run when the class is loaded in the order they are defined.
		
		//static import
		//import static java.lang.System.*;
		//import static java.lang.System.out;       
		//import static java.lang.System;		// DOES NOT COMPILE
		//import static java.util.Arrays;       // DOES NOT COMPILE
		//import static java.util.Arrays.asList;
		//static import java.util.Arrays.*;     // DOES NOT COMPILE

		//passing data among methods
		//Java is a “pass-by-value” language. (primitive passes value and object passes reference value)
		//This means that a copy of the variable is made and the method receives that copy. 
		//Assignments made in the method do not affect the caller.
		
		//-----------Overloading Methods
		//Method overloading occurs when methods have the same name but different method signatures, 
		//which means they differ by method parameters.
		
		//public void fly(int numMiles) {}
		//public static void fly(int numMiles) {}     // DOES NOT COMPILE
		
		//public void fly(int numMiles) {}
		//public int fly(int numMiles) {}     // DOES NOT COMPILE

		//public void fly(int[] lengths) {}
		//public void fly(int... lengths) {}     // DOES NOT COMPILE
		
		//autoboxing/reference/primitive for overloading methods
		//Java tries to use the most specific parameter list it can find.
		
		//Generics for overloading methods
		
		//public void walk(List<String> strings) {}
		//public void walk(List<Integer> integers) {}    // DOES NOT COMPILE
		//Java has a concept called type erasure where generics are used only at compile time.

		//at runtime they will look like the following
		//public void walk(List strings) {}
		//public void walk(List integers) {}    // DOES NOT COMPILE

		//Arrays for overloading methods (ok)
		//public static void walk(int[] ints) {}
		//public static void walk(Integer[] integers) {}
		
		//Java calls the most specific method it can. When some of the types interact, 
		//the Java rules focus on backward compatibility. 
		//A long time ago, autoboxing and varargs didn’t exist. 
		//Since old code still needs to work, 
		//this means autoboxing and varargs come last when Java looks at overloaded methods.
		
		//at last that As accommodating as Java is with trying to find a match, 
		//it will do only one conversion:

		//Encapsulationg Data ... ...

	}
	
	private static void testTest() {

/*
		1.	bc
		2.	ad
		3.	acd
		4.	abg
		5.	df
		6.	d
		7.	bcdf
		8.	abe
		9.	bdf
		10.	e[b]
		11.	bf[be]
		12.	a[d]
		13.	b[e]
		14.	b
		15.	e
		16.	c[b]
		17.	ade
		18.	ce[bce]
		19.	ae
		20.	acef[acf]
		21.	a[abc]
		

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

