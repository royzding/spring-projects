package com.sample.microservices.common.chapter;

import static java.lang.System.out;

import java.util.function.Predicate;

public class Chapter12_JavaFundamentals {


	public static void main(String[] args) {

		testTemplet();
		TestFundamentals();
		
		testTest();
		
		Test t = new Test();
		SubTest st = new SubTest();
		
		Test ct = st;
		
		
	}

	private static void testTemplet() {
		
	}
	
	private static void TestFundamentals() {
		
		out.println("--------------TestFundamentals-----------");

		//applying the final modifier
		
		//local final variables.
		
		/*
			private void printZooInfo(boolean isWeekend) {
			   final int giraffe = 5;
			   final long lemur;
			   if(isWeekend) lemur = 5;
			   else lemur = 10;
			   System.out.println(giraffe+" "+lemur);
			}

			private void printZooInfo(boolean isWeekend) {
			   final int giraffe = 5;
			   final long lemur;
			   if(isWeekend) lemur = 5;
			   giraffe = 3; // DOES NOT COMPILE
			   System.out.println(giraffe+" "+lemur); // DOES NOT COMPILE
			}
			
			private void test() {
				final StringBuilder cobra = new StringBuilder();
				cobra.append("Hssssss");
				cobra.append("Hssssss!!!");
			}

		 */
		
		//instance and static final variables.
		
		/*
			public class PolarBear {
			   final int age = 10;
			   final int fishEaten;
			   final String name;
			 
			   { fishEaten = 10; }
			public PolarBear() {
			      name = "Robert";
			   }
			   public PolarBear(int height) {
			      this();
			   }
			}

			public class Panda {
			   final static String name = "Ronda";
			   static final int bamboo;
			   static final double height; // DOES NOT COMPILE
			   static { bamboo = 5;}
			}

		 */
		
		//writing final methods
		
		/*
			public abstract class Animal {
			   abstract void chew();
			}
			   
			public class Hippo extends Animal {
			   final void chew() {}
			}
			   
			public class PygmyHippo extends Hippo {
			   void chew() {} // DOES NOT COMPILE
			}
			
			abstract class ZooKeeper {
			   public abstract final void openZoo(); // DOES NOT COMPILE
			}
			
		 */
		
		//making class final
		
		/*
			public final class Reptile {}   
			public class Snake extends Reptile {} // DOES NOT COMPILE 
			public final interface Hawk {} // DOES NOT COMPILE
		 */
		
		//working with enum
		
		//creating a simple enum
		
		/*
			public enum Season {
			   WINTER, SPRING, SUMMER, FALL
			}
			
			Season s = Season.SUMMER;
			System.out.println(Season.SUMMER); // SUMMER
			System.out.println(s == Season.SUMMER); // true
			System.out.println(s.equals(Season.SUMMER)); // true
			
			System.out.println(s.toString());  //SUMMER
			
			for(Season season: Season.values()) {
			   System.out.println(season.name() + " " + season.ordinal());
			}

			WINTER 0
			SPRING 1
			SUMMER 2
			FALL 3

			if ( Season.SUMMER == 2) {} // DOES NOT COMPILE

			Season s = Season.valueOf("SUMMER"); // SUMMER 
			Season t = Season.valueOf("summer"); // Throws an IllegalArgumentException exception at runtime

			//One thing that you can't do is extend an enum. 
			//The values in an enum are all that are allowed. You cannot add more by extending the enum.
			
			public enum ExtendedSeason extends Season { } // DOES NOT COMPILE

 		 */
		
		//using enum in switch statements
		
		/*
			Season summer = Season.SUMMER;
			switch (summer) {
			   case WINTER:
			      System.out.println("Get out the sled!");
			      break;
			   case SUMMER:
			      System.out.println("Time for the pool!");
			      break;
			   default:
			      System.out.println("Is it summer yet?");
			}

			switch (summer) {
			   case Season.FALL:  // DOES NOT COMPILE
			      System.out.println("Rake some leaves!");
			      break;
			   case 0:            // DOES NOT COMPILE
			      System.out.println("Get out the sled!");
			      break;
			}

		 */
		
		//ading constructors, fields, and methods
		//see the blow enum Season
		//All enum constructors are implicitly private, with the modifier being optional. 
		//This is reasonable since you can't extend an enum and the constructors can be called 
		//only within the enum itself. An enum constructor will not compile if it contains a public or protected modifier.
		
		//how to call enum method
		//Season.SUMMER.printExpectedVisitors();
		
		//You might have noticed that in each of these enum examples, the list of values came first. 
		//This was not an accident. Whether the enum is simple or contains a ton of methods, constructors, 
		//and variables, the compiler requires that the list of values always be declared first.

		//The immutable objects pattern is an object‐oriented design pattern 
		//in which an object cannot be modified after it is created. Instead of modifying an immutable object, 
		//you create a new object that contains any properties from the original object you want copied over.


		//creating nested classes
		
		//A nested class is a class that is defined within another class. A nested class can come in one of four flavors.

		//1.	Inner class: A non‐ static type defined at the member level of a class 
		//2.	Static nested class: A static type defined at the member level of a class 
		//3.	Local class: A class defined within a method body 
		//4.	Anonymous class: A special case of a local class that does not have a name
		
		//declaring an inner class
		
		//An inner class, also called a member inner class, is a non‐ static type defined 
		//at the member level of a class (the same level as the methods, instance variables, and constructors). 
		//Inner classes have the following properties:
		
		//1.	Can be declared public, protected, package‐private (default), or private 
		//2.	Can extend any class and implement interfaces 
		//3.	Can be marked abstract or final 
		//4.	Cannot declare static fields or methods or class, except for static final fields 
		//5.	Can access members of the outer class including private members

		//Since an inner class is not static, it has to be used with an instance of the outer class.
		//so in the static method an instance of the outer class has to be created to create inner class instance.
		
		//creating a static nested class
		//A static nested class is a static type defined at the member level. 
		//Unlike an inner class, a static nested class can be instantiated without an instance of the enclosing class. 
		//The trade‐off, though, is it can't access instance variables or methods in the outer class directly. 
		//It can be done but requires an explicit reference to an outer class variable.

		//In other words, it is like a top‐level class except for the following: 
		//1.	The nesting creates a namespace because the enclosing class name must be used to refer to it. 
		//2.	It can be made private or use one of the other access modifiers to encapsulate it. 
		//3.	The enclosing class can refer to the fields and methods of the static nested class.
		
		//Importing a static nested class is interesting. You can import it using a regular import.
		//Since it is static, you can also use a static import.
		//Java treats the enclosing static class as if it were a namespace.
		
		//writing a local class
		//A local class is a nested class defined within a method. Like local variables, 
		//a local class declaration does not exist until the method is invoked, 
		//and it goes out of scope when the method returns. 
		//This means you can create instances only from within the method. 
		//Those instances can still be returned from the method. This is just how local variables work.

		//Local classes are not limited to being declared only inside methods. 
		//They can be declared inside constructors and initializers too. 
		//For simplicity, we limit our discussion to methods in this chapter.
		
		//Local classes have the following properties: 
		//1.	They do not have an access modifier. 
		//2.	They cannot be declared static and cannot declare static fields or methods, except for static final fields. 
		//3.	They have access to all fields and methods of the enclosing class (when defined in an instance method). 
		//4.	They can access local variables if the variables are final or effectively final.
		
		//defining an anonymous class
		//An anonymous class is a specialized form of a local class that does not have a name. 
		//It is declared and instantiated all in one statement using the new keyword, a type name with parentheses, 
		//and a set of braces {}. Anonymous classes are required to extend an existing class or 
		//implement an existing interface. They are useful when you have a short implementation 
		//that will not be used anywhere else.
		
		//with an anonymous class, you cannot implement both an interface and extend a class.
		
		//There is one more thing that you can do with anonymous classes. 
		//You can define them right where they are needed, even if that is an argument to another method.
		
		//understanding interface members
		
		//default interface method.
		//A default method is a method defined in an interface with the default keyword and includes a method body and
		//implicit public
		
		//A default method may be overridden by a class implementing the interface. 
		
		//The name default comes from the concept that it is viewed as an abstract interface method 
		//with a default implementation. The class has the option of overriding the default method, 
		//but if it does not, then the default implementation will be used.
		
		//Default Interface Method Definition Rules 
		//1.	A default method may be declared only within an interface. 
		//2.	A default method must be marked with the default keyword and include a method body. 
		//3.	A default method is assumed to be public. 
		//4.	A default method cannot be marked abstract, final, or static. 
		//5.	A default method may be overridden by a class that implements the interface. 
		//6.	If a class inherits two or more default methods with the same method signature, 
		//		then the class must override the method.
		
		//calling a hidden default method
		//interfaceName.super.defaultMethodName
		
		//static interface methods
		
		//Static Interface Method Definition Rules:
		//1.	A static method must be marked with the static keyword and include a method body. 
		//2.	A static method without an access modifier is assumed to be public.
		//3.	A static method cannot be marked abstract or final. 
		//4.	A static method is not inherited and cannot be accessed in a class 
		//		implementing the interface without a reference to the interface name.
		
		//a class that implements two interfaces containing static methods with the same signature will still compile. 
		//since the interface static method is not inherited. 
		//Contrast this with the behavior you saw for default interface methods in the previous section.
		
		//private interface methods
		//that private interface methods can be used to reduce code duplication.
		
		//private Interface Method Definition Rules: 
		//1.	A private interface method must be marked with the private modifier and include a method body.
		//2.	A private interface method may be called only by default and private (non‐ static) methods 
		//		within the interface definition.
		
		//Private interface methods behave a lot like instance methods within a class. Like private methods in a class, 
		//they cannot be declared abstract since they are not inherited.
		
		//private static interface methods
		//the purpose of private static interface methods is to reduce code duplication in static methods 
		//within the interface declaration. Furthermore, because instance methods can access static methods 
		//within a class, they can also be accessed by default and private methods.
		
		//Private Static Interface Method Definition Rules:
		//1.	A private static method must be marked with the private and static modifiers and include a method body. 
		//2.	A private static interface method may be called only by other methods within the interface definition. 
		
		//Both private and private static methods can be called from default and private methods. 
		//This is equivalent to how an instance method is able to call both static and instance methods. 
		//On the other hand, a private method cannot be called from a private static method. 
		//This would be like trying to access an instance method from a static method in a class.


		//Functional Programming
		//defining a Functional Interface (SAM rule)
		
		//one exception to the single abstract method rule
		//If a functional interface includes an abstract method with the same signature as a public method found in Object, 
		//then those methods do not count toward the single abstract method test. 
		//The motivation behind this rule is that any class that implements the interface will inherit from Object, 
		//as all classes do, and therefore always implement these methods.
		//methods include 
		//String to String();
		//boolean equals(Object);
		//int hashCode();
		
		//Since Java assumes all classes extend from Object, you also cannot declare an interface method 
		//that is incompatible with Object. For example, declaring an abstract method int toString() 
		//in an interface would not compile since Object's version of the method returns a String.
		
		//implementing Functional Interfaces with Lambdas
		
		//lambdas parameter list
		
		Predicate<String> p1 = x -> true;
		//Predicate<String> p2 = (var x) -> true;
		Predicate<String> p3 = (String x) -> true;


		
	}
	
	private static void testTest() {
		
		out.println("--------------testTest-----------");
		
		final var x = 10;

/*
		1.	ad
		2.	c
		3.	c
		4.	bf
		5.	d(bd)
		6.	ce
		7.	f
		8.	ac
		9.	cf(g)
		10.	c(e)
		11.	a(d)
		12.	c
		13.	eg
		14.	e
		15.	g
		16.	ade
		17.	f(af)
		18.	cdf(cdg)
		19.	d
		20.	abcdef(abcd)
		21.	bde(be)
		22.	df
		23.	cf
		24.	c(e)
		25.	b
		

*/
		
	}
	
}


enum Season {
	
    WINTER("Low"), SPRING("Medium"), SUMMER("High"), FALL("Medium");
	
    private final String expectedVisitors;
    
    private Season(String expectedVisitors) {
       this.expectedVisitors = expectedVisitors;
    }
    
    public void printExpectedVisitors() {
       System.out.println(expectedVisitors);
    } 
}

enum Season2 {
	
    WINTER("Winter"), SPRING("Spring"), SUMMER("Summer"), FALL("Fall");
	
    private final String value;
    
    private Season2(String value) {
       this.value = value;
    }
    
    public String getValue() {
        return this.value;
     } 
    
    @Override
    public String toString() {
        return this.value;
    } 
}

enum Season3 {
	
	   WINTER {
	      public String getHours() { return "10am-3pm"; }
	   },
	   SPRING {
	      public String getHours() { return "9am-5pm"; }
	   },
	   SUMMER {
	      public String getHours() { return "9am-7pm"; }
	   },
	   FALL {
	      public String getHours() { return "9am-5pm"; }
	   };
	
	   public abstract String getHours();
	   
}

enum Season4 {
	
	   WINTER {
	      public String getHours() { return "10am-3pm"; }
	   },
	   SUMMER {
	      public String getHours() { return "9am-7pm"; }
	   },
	   
	   SPRING, FALL;
	
	   public String getHours() { return "9am-5pm"; }
}

class IceCream {
    enum Flavors {
       CHOCOLATE, STRAWBERRY, VANILLA
    }

    public static void main(String[] args) {
       Flavors STRAWBERRY = null;
       switch (STRAWBERRY) {
          case VANILLA: System.out.print("v");
          case CHOCOLATE: System.out.print("c");
          case STRAWBERRY: System.out.print("s");
          break;
          default: System.out.println("missing flavor"); }
    }
 }

class Lion {
    class Cub {}
    static class Den {}
    
    static void rest() {
    	
    	Lion.Cub c = new Lion().new Cub();
    	var d = new Den();
    	Lion.Den f = new Lion.Den();
    	
    } 
}

class Outer {
     private String greeting = "Hi";
     
     protected class Inner {
    	
    	//static int x; 				//do not compile    	
    	//public static void m1() {}; 	//do not compile
    	 
    	final static int c = 10;		//except for static final fields
    	//final static void m2() {}; 	//do not compile
    	//static class SC {}			//do not compile
   	
        public int repeat = 3;

        public void go() {
           for (int i = 0; i < repeat; i++)
              System.out.println(greeting);
        }
    }
    
    public void callInner() {
       Inner inner = new Inner();
       inner.go();
    }
    
    public static void main(String[] args) {
       Outer outer = new Outer();
       outer.callInner();
    } 
    
    public static void test() {
    	Outer outer = new Outer();
    	Inner inner = outer.new Inner(); //create the inner class
    	inner.go();
    }
        
}

//Inner classes can have the same variable names as outer classes, 
//making scope a little tricky. There is a special way of calling this to say which variable you want to access.

class A {
     private int x = 10;
     
     class B {
        private int x = 20;
        
        class C {
           private int x = 30;
           public void allTheX() {
             System.out.println(x);        // 30
             System.out.println(this.x);   // 30
             System.out.println(B.this.x); // 20
             System.out.println(A.this.x); // 10    
           }            
        }         
    }
     
    class D {
    	final static int x = 10;
    	
    	//static void test() {} 	//do not compile
    	//static int x = 10;		//do not compile needs to be final
    	
    	//static class E {}			//do not compile    	
        //interface intf {}			//do not compile no interface allowed
    	//enum em {ONE,TWO}           //do not compile no enum allowed
    }
    
     
    public static void main(String[] args) {

	A a = new A();
    A.B b = a.new B();
    A.B.C c = b.new C();
    c.allTheX();
    
    B b2 = a.new B();
    B.C c2 = b2.new C();
    
 }
}

//Inner Class instantiation Requires an Instance
class Fox {
    class Den {
    	void print() {
    		System.out.println("den");
    	}
    }
    public void goHome() {
       new Den();
    }
    public static void visitFriend() {
       //new Den();  // DOES NOT COMPILE
    	
    	Fox f = new Fox();    	
    	Den den = f.new Den();
    	den.print();
    }
}

class Squirrel {
    public void visitFox() {
       //new Den();  // DOES NOT COMPILE
    	
    	Fox.Den den = new Fox().new Den();
    	den.print();
    }
    
    public void callAs() {
    	A a = new A();
        A.B b = a.new B();
        A.B.C c = b.new C();
        c.allTheX();
        
        //B b2 = a.new B();			//do not compile
        //B.C c2 = b2.new C();		//do not compile
        
    }
}


////static nested classes
class Enclosing {
	
	int x = 0;
	static int si = 1;
	
    static class Nested {
        private int price = 6;
        int dprice = 10;
        
        private void test() {
        	//System.out.println("x=" + x); //do not compile
        	System.out.println("si=" + si);
        }

        static class D {
        	static void test() {} 	//ok
        	static int x = 10;		//ok
        	final static int y = 10;
        }
        
        class IClass {}  //ok
    }
    
    public static void main(String[] args) {
       Nested nested = new Nested();
       System.out.println(nested.price);
    } 
    
    public void test() {
    	Nested nested = new Nested();
    	System.out.println(nested.price);
    }
}

class CallStaticEnclosing {
	
    public static void main(String[] args) {
    	Enclosing.Nested nested = new Enclosing.Nested();
    	System.out.println(nested.dprice);
    } 
    
    public void test() {
    	Enclosing.Nested nested = new Enclosing.Nested();
    	System.out.println(nested.dprice);
    }
}


//local nested classes
class PrintNumbers {
	
     private int length = 5;
     
     public void calculate() {
    	 
        final int width = 20;
        
        class MyLocalClass {
           public void multiply() {
              System.out.print(length * width);
           }
        }
        
       MyLocalClass local = new MyLocalClass();
       
       local.multiply();
    }
     
    public static void main(String[] args) {
    	
       PrintNumbers outer = new PrintNumbers();
       
       outer.calculate();
    }
    
    public void processData() {
  	   final int length = 5;
  	   int width = 10;
  	   int height = 2;
  	   
  	   class VolumeCalculator {
  	      public int multiply() {
   	         //return length * width * height; // DOES NOT COMPILE since width is not effectvely final
  	         return length * height;
  	      }
  	   }
  	   
  	   width = 2;   
     }

    private static int sLength = 10;
    
    public static void staticData() {
    	
  	   //final int length = 5;
  	   int width = 10;
  	   int height = 2;
  	   
  	   class VolumeCalculator {
  	      public int multiply() {
    	     //return width * height; // DOES NOT COMPILE since width is not effectvely final
     	     //return length * height; // DOES NOT COMPILE since length is not static
     	     //return side * height; // DOES NOT COMPILE since side declare after the nested class
  	         return sLength * height;
  	      }
  	   }
  	   
  	   width = 2; 
  	   
  	   int side = 20;
     }

}
    
//anonymous classes
class ZooGiftShop {
	
     abstract class SaleTodayOnly {
        abstract int dollarsOff();
     }
     
     public int admission(int basePrice) {
    	 
        SaleTodayOnly sale = new SaleTodayOnly() {
           int dollarsOff() { return 3; }
        };  // Don't forget the semicolon!
        
        return basePrice - sale.dollarsOff();
     } 

     interface ISaleTodayOnly {
    	 int dollarsOff();   	      
     }
     
     public int admission2(int basePrice) {
    	 ISaleTodayOnly sale = new ISaleTodayOnly() {
    	    public int dollarsOff() { return 3; }    	        
    	 };
    	         
    	 return basePrice - sale.dollarsOff();
    	  
     }
}

class ZooGiftShop2 {
	
     interface SaleTodayOnly {
        int dollarsOff();
     }
    
     public int pay() {
	
    	 return admission(5, new SaleTodayOnly() {public int dollarsOff() { return 3; }});
     }
     
    public int admission(int basePrice, SaleTodayOnly sale) {
       return basePrice - sale.dollarsOff();
    }
    
    //You can even define anonymous classes outside a method body.
    interface Climb {}
    Climb climbing = new Climb() {};

    
}

////interface members

interface IsWarmBlooded {
	
	//abstract method
	boolean hasScales();
	
	//default method
	default double getTemperature() {
	      return 10.0;
	}
	
	//static method
	static int getWeight() { return 10;}			//implicit public
	
	default void wakeUp()        { checkTime(7);  }
	default void haveBreakfast() { checkTime(9);  }
	default void haveLunch()     { checkTime(12); }
	default void workOut()       { checkTime(18); }
	
	//private method
	private void checkTime(int hour) {
	   if (hour> 17) {
	      System.out.println("You're late!");
	   } else {
	      System.out.println("You have "+(17-hour)+" hours left " + "to make the appointment");
	   }
	}

	//private static method
	private static void breathe(String type) {
	      System.out.println("Inhale");
	      System.out.println("Performing stroke: " + type);
	      System.out.println("Exhale");
	   }
	   
	static void butterfly()        { breathe("butterfly");  }
	public static void freestyle() { breathe("freestyle");  }
	default void backstroke()      { breathe("backstroke"); }
	private void breaststroke()    { breathe("breaststroke"); }
	
	//protected static int getWeighty() { return 10;} //do not compile for protected.
}

interface Carnivore {	   
	//public default void eatMeat();        // DOES NOT COMPILE
	//public int getRequiredFoodAmount() { return 10; } // DOES NOT COMPILE
}

interface Walk {
	public default int getSpeed() { return 5; }
	int getHight();
}
	 
interface Run {   
	public default int getSpeed() { return 10; }
	int getHight();
}
	 
//class Cat implements Walk, Run { } // DOES NOT COMPILE

class Cat implements Walk, Run { 
	public int getSpeed() {
		
		int wSpeed = Walk.super.getSpeed();
		
		return 20;
	}
	
	public int getHight() {
		
		//int rHight = Run.super.getHight(); //do not compile since getHight not implemented.
		
		return 10;
		
	}
}

//Functional Interface

@FunctionalInterface
interface Sprint {
   public void sprint(int speed);
}
 
class Tiger implements Sprint {
   public void sprint(int speed) {
      System.out.println("Animal is sprinting fast! " + speed);
   }
}

interface CanWalk {
	default void walk() { System.out.print("Walking"); }
	private void testWalk() {}
}

interface CanRun {
	abstract public void run();
	private void testWalk() {}
	default void walk() { System.out.print("Running"); }
}

interface CanSprint extends CanWalk, CanRun {
	
	void sprint();
	
	//must override duplicate walk methods
	default void walk() { System.out.print("CanSprint"); }
	
	default void walk(int speed) { 
		System.out.print("Sprinting");
	}
	
	private void testWalk() {}
	
}


    
    