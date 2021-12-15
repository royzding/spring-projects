package com.sample.microservices.common.chapter;

import static java.lang.System.out;

import java.io.IOException;

public class Chapter10_Exceptions {


	public static void main(String[] args) {

		testTemplet();
		TestException();
		
		testTest();
		
		Test t = new Test();
		SubTest st = new SubTest();
		
		Test ct = st;
		
		
	}

	private static void testTemplet() {
		
	}
	
	private static void TestException() {
		
		out.println("--------------TestException-----------");

		//Checked Exceptions
		//A checked exception is an exception that must be declared or handled by the application code where it is thrown.
		
		//Checked exceptions all inherit Exception but not RuntimeException.
		//Checked exceptions also include any class that inherits Throwable, but not Error or RuntimeException.
		
		//The handle or declare rule means that all checked exceptions that could be thrown within a method are either 
		//wrapped in compatible try and catch blocks or declared in the method signature.
		
		//While only checked exceptions must be handled or declared in Java, unchecked exceptions 
		//(which we will present in the next section) may also be handled or declared. 
		//The distinction is that checked exceptions must be handled or declared, 
		//while unchecked exceptions can be optionally handled or declared.

		//unchecked Exceptions
		//An unchecked exception is any exception that does not need to be declared or handled by the application code 
		//where it is thrown. Unchecked exceptions are often referred to as runtime exceptions, 
		//although in Java, unchecked exceptions include any class that inherits RuntimeException or Error.
		
		//A runtime exception is defined as the RuntimeException class and its subclasses. 
		//Runtime exceptions tend to be unexpected but not necessarily fatal.
		
		//throwing an Exception
		//throw vs throws
		//The throw keyword is used as a statement inside a code block to throw a new exception or rethrow an existing exception, 
		//while the throws keyword is used only at the end of a method declaration to indicate what exceptions it supports.
		
		//recognizing Exception classes
		
		//RuntimeException classes
		//common RuntimeException classes:
		//1.	ArithmeticException Thrown when code attempts to divide by zero 
		//2.	ArrayIndexOutOfBoundsException Thrown when code uses an illegal index to access an array 
		//3.	ClassCastException Thrown when an attempt is made to cast an object to a class of which it is not an instance 
		//4.	NullPointerException Thrown when there is a null reference where an object is required 
		//5.	IllegalArgumentException Thrown by the programmer to indicate that a method has been passed an illegal or inappropriate argument
		//6.	NumberFormatException Subclass of IllegalArgumentException thrown when an attempt is made to convert a string to a numeric type 
		//		but the string doesn’t have an appropriate format
		
		//checked Exception classes
		//common checked Exception classes:
		//1.	IOException Thrown programmatically when there’s a problem reading or writing a file
		//2.	FileNotFoundException Subclass of IOException thrown programmatically when code tries to reference a file that does not exist
		
		//Error classes
		//Errors are unchecked exceptions that extend the Error class. They are thrown by the JVM and should not be handled or declared.
		//common Error classes:
		//1.	ExceptionInInitializerError Thrown when a static initializer throws an exception and doesn’t handle it 
		//2.	StackOverflowError Thrown when a method calls itself too many times 
		//		(This is called infinite recursion because the method typically calls itself without end.) 
		//3.	NoClassDefFoundError Thrown when a class that the code uses is available at compile time but not runtime
		
		//handling Exceptions
		
		/*

			try {
			   //protected code
			} catch(Exception e) {
			   //Exception handler
			}

		 */
		
		//Chaining catch blocks.
		
		//applying a multi-catch block

		/*

			try {
			   //protected code
			} catch(Exception1 | Exception2 e) {
			   //Exception handler
			}
	
			catch(Exception1 e | Exception2 e | Exception3 e) // DOES NOT COMPILE
			 
			catch(Exception1 e1 | Exception2 e2 | Exception3 e3) // DOES NOT COMPILE
			 
			catch(Exception1 | Exception2 | Exception3 e)
	
			//Java intends multi-catch to be used for exceptions that aren’t related, 
			//and it prevents you from specifying redundant types in a multi-catch. Do you see what is wrong here? 
			 
			try {
			   throw new IOException();
			} catch (FileNotFoundException | IOException p) {} // DOES NOT COMPILE

		*/
		
		//adding a finally block
		
		//System.exit()
		//There is one exception to “the finally block always be executed” rule: 
		//Java defines a method that you call as System.exit(). 
		//It takes an integer parameter that represents the error code that gets returned. 
		
		/*
			try {
			   System.exit(0);
			} finally {
			   System.out.print("Never going to get here");  // Not printed
			}
			
		*/
		
		//finally closing resources
		
		//try-with-resources
		
		//that one or more resources can be opened in the try clause. When there are multiple resources opened, 
		//they are closed in the reverse order from which they were created.

		//catch and finally blocks are optional but always there is an implicit finally block.
		//and the implicit finally block runs before any programmer coded ones.
		
		/*
			try(FileInputStream in = new FileInputStream("data.txt"); FileOutPutStream out = new FileOutputStream("output.txt"); ) {
				//protected code
			}
		
		*/
		
		//AutoCloseable
		//You can’t just put any random class in a try-with-resources statement. 
		//Java requires classes used in a try-with-resources implement the AutoCloseable interface, 
		//which includes a void close() method.

		//declaring resources
		
		/*
			try (MyFileClass is = new MyFileClass(1),  // DOES NOT COMPILE
			      os = new MyFileClass(2)) {
			}
			 
			try (MyFileClass ab = new MyFileClass(1),  // DOES NOT COMPILE
			      MyFileClass cd = new MyFileClass(2)) {
			}

			try (var f = new BufferedInputStream(new FileInputStream("it.txt"))) {
			   // Process file
			}
        */
		
		//scope of try-with-resources
		//The resources created in the try clause are in scope only within the try block. 
		//This is another way to remember that the implicit finally runs before any catch/finally blocks
		//that you code yourself. The implicit close has run already, and 
		//the resource is no longer available after try block.

		/*
			try (Scanner s = new Scanner(System.in)) {
			   s.nextLine();
			} catch(Exception e) {
			   s.nextInt(); // DOES NOT COMPILE
			} finally {
			   s.nextInt(); // DOES NOT COMPILE
			}
		*/
		
		//In a traditional try statement, the variable has to be declared before the try statement 
		//so that both the try and finally blocks can access it, 
		//which has the unpleasant side effect of making the variable in scope for the rest of the method, 
		//just inviting you to call it by accident.

		//order of operation of try-with-resource
		//1.	Resources are closed after the try clause ends and before any catch/finally clauses. 
		//2.	Resources are closed in the reverse order from which they were created.
		
		//Throwing additional Exceptions
		
		//calling methods that throw Exceptions.
		
		//When you see a checked exception declared inside a catch block on the exam, 
		//check and make sure the code in the associated try block is capable of throwing the
		//exception or a subclass of the exception. If not, the code is unreachable and does not compile. 
		//Remember that this rule does not extend to unchecked exceptions or exceptions declared in a method signature.

		//declaring and Overriding methods with Exceptions
		
		//printing an Exception
		//1.	System.out.println(e);
		//2.	System.out.println(e.getMessage());
		//3.	e.printStackTrace();
		
		//swallowing Exceptions is bad
		
		//finally block is always the last block in try-catch-finally blocks.	
		
		
	}
	
	private static void testTest() {
		
		out.println("--------------testTest-----------");

/*
		1.	acdf
		2.	bde
		3.	g
		4.	bd
		5.	ce(c)
		6.	d(e)
		7.	c
		8.	g
		9.	e
		10.	b
		11.	d
		12.	af(a)
		13.	abcdf
		14.	acde
		15.	g
		16.	abdef
		17.	bcf(bf)
		18.	b
		19.	adef
		20.	bce
		21.	g
		22.	df
		23.	abe(ae)
		//following catch block does not catch exceptions throwing from previous catch blocks but only from 
		//try block. 
		24.	c
		25.	d

*/
		
	}
	
}

class TestException {
	
	void test() throws IOException {
		
		try {
			int x = 10;
		} catch(Exception e) {
			//x = 20; //not compiling
		} finally {
			//x = 30; //not compiling
		}
		
	}
}

class NoMoreCarrotsException extends Exception {}

class Bunny {
	public static void main(String[] args) {
	      //eatCarrot(); // DOES NOT COMPILE
	}
	
	public void test1() throws NoMoreCarrotsException {
		eatCarrot();
	}
	
	public void test2() {
/*		
		try {
			eatCarrot();			
		} finally {
			
		} catch (NoMoreCarrotsException e) {
		
			System.out.println("handling");
		}
*/		
 	}
	
	private static void eatCarrot() throws NoMoreCarrotsException {
	}
/*	
	public void bad() {
		   try {
		      eatCarrot2();
		   } catch (NoMoreCarrotsException e ) { // DOES NOT COMPILE
		      System.out.print("sad rabbit");
		   }
		}
*/
	public void good() throws NoMoreCarrotsException {
		   eatCarrot2();
	}

	private static void eatCarrot2() {

	}

	void rollOut() throws ClassCastException {
		
	}

	public void transform(String c) {
	     try {
	        rollOut();
	     } catch (IllegalArgumentException | NullPointerException e) {

	     } finally {
	    	 
	     }

	}
}
