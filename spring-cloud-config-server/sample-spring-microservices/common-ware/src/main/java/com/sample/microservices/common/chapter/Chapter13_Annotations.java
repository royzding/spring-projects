package com.sample.microservices.common.chapter;

import static java.lang.System.out;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Chapter13_Annotations {


	public static void main(String[] args) {

		testTemplet();
		TestAnnotations();
		
		testTest();
		
		Test t = new Test();
		SubTest st = new SubTest();
		
		Test ct = st;
		
		
	}

	private static void testTemplet() {
		
	}
	
	private static void TestAnnotations() {
		
		out.println("--------------TestAnnotations-----------");
		
		//Purpose of Annotations
		//The purpose of an annotation is to assign metadata attributes to classes, methods, variables, and other Java types.
		
		//Annotation Rules:
		//1.	annotations function a lot like interfaces.
		//2.	annotations establish relationships that make it easier to manage data about our application.
		//3.	an annotation ascribes custom information on the declaration where it is defined.
		//4.	annotations are optional metadata and by themselves do not do anything.
		//		This means you can take a project filled with thousands of annotations and remove all of them, 
		//		and it will still compile and run, albeit with potentially different behavior at runtime.
		
		//While an annotation can be removed from a class and it will still compile, the opposite is not true; 
		//adding an annotation can trigger a compiler error.
		
		//creating an Annotation
		
		//specifying required Elements
		//An annotation element is an attribute that stores values about the particular usage of an annotation.
		//When declaring an annotation, any element without a default value is considered required.
		
		//providing an Optional Element
		//For an element to be optional, rather than required, it must include a default value.
		//The default value of an annotation cannot be just any value. Similar to case statement values, 
		//the default value of an annotation must be a non‐ null constant expression.
		//and also it must be compatible with the declared type
		
		//when we have more than one element value within an annotation, we separate them by a comma ( ,). 
		//Next, each element is written using the syntax elementName = elementValue. 
		//It's like a shorthand for a Map. Also, the order of each element does not matter.
		
		//selecting an Element Type
		
		//Similar to a default element value, an annotation element cannot be declared with just any type. 
		//It must be a primitive type, a String, a Class, an enum, another annotation, 
		//or an array of any of these types.
		
		//applying Element Modifiers
		
		//Like abstract interface methods, annotation elements are implicitly abstract and public, 
		//whether you declare them that way or not.
		
		//adding a Constant Variable
		
		//Annotations can include constant variables that can be accessed by other classes without actually 
		//creating the annotation.
		
		//Yep, just like interface variables, annotation variables are implicitly public, static, and final. 
		//These constant variables are not considered elements, though. 
		//For example, marker annotations can contain constants.
		
		//Annotation Rules
		
		//Applying Annotations
		
		//Annotations can be applied to any Java declaration including the following:
		//1.	Classes, interfaces, enums, and modules 
		//2.	Variables ( static, instance, local) 
		//3.	Methods and constructors Method, constructor, and lambda parameters 
		//4.	Cast expressions 
		//5.	Other annotations
		
		//mixing Required and Optional Elements
		//One of the most important rules when applying annotations is the following: 
		//to use an annotation, all required values must be provided. 
		//While an annotation may have many elements, values are required only for ones without default values.

		//Creating a value() Element
		
		//An annotation must adhere to the following rules to be used without a name: 
		//The annotation declaration must contain an element named value(), which may be optional or required. 
		//The annotation declaration must not contain any other elements that are required. 
		//The annotation usage must not provide values for any other elements.
		
		//passing an Array of Values
		//Annotations support a shorthand notation for providing an array that contains a single element.
		
		//declaring Annotation-specific Annotations
		
		//Limiting Usage with @Target
		//@Target annotation, which limits the types the annotation can be applied to. More specifically, 
		//the @Target annotation takes an array of ElementType enum values as its value() element.
		
		//ElementType 		[value Applies to]

		//TYPE  			[Classes, interfaces, enums, annotations] 
		//FIELD  			[Instance and static variables, enum values]
		//METHOD  			[Method declarations]
		//PARAMETER  		[Constructor, method, and lambda parameters] 
		//CONSTRUCTOR  		[Constructor declarations] 
		//LOCAL_VARIABLE  	[Local variables] 
		//ANNOTATION_TYPE  	[Annotations]
		//PACKAGE *  		[Packages declared in package‐info.java] 
		//TYPE_PARAMETER *  [Parameterized types, generic declarations] 
		//TYPE_USE  		[Able to be applied anywhere there is a Java type declared or used] 
		//MODULE *  		[Modules]
		
		//Storing Annotations with @Retention
		
		//annotations may be discarded by the compiler or at runtime. We say “may,” because we can actually specify 
		//how they are handled using the @Retention annotation. This annotation takes a value()
		
		//RetentionPolicy 	[value Description ]
		
		//SOURCE  			[Used only in the source file, discarded by the compiler] 
		//CLASS  			[Stored in the .class file but not available at runtime (default compiler behavior)] 
		//RUNTIME  			[Stored in the .class file and available at runtime]
		
		//Generating Javadoc with @Documented
		
		//the marker annotation @Documented. 
		//If present, then the generated Javadoc will include annotation information defined on Java types. 
		//Because it is a marker annotation, it doesn't take any values;
		
		//Inheriting Annotations with @Inherited
		
		//When this marker annotation is applied to a class, 
		//subclasses will inherit the annotation information found in the parent class.
		
		//supporting Duplicates with @Repeatable
		
		//The @Repeatable annotation is used when you want to specify an annotation more than once on a type.
		//Generally, you use repeatable annotations when you want to apply the same annotation with different values.
		
		//Rules:
		//1.	without the @Repeatable annotation, an annotation can be applied only once.
		//2.	to declare a @Repeatable annotation, you must define a containing annotation type value.
		
		//A containing annotation type is a separate annotation that defines a value() array element. 
		//The type of this array is the particular annotation you want to repeat. 
		//By convention, the name of the annotation is often the plural form of the repeatable annotation.
		
		//The Rules for declaring a repeatable annotation
		//1.	The repeatable annotation must be declared with 
		//		@Repeatable and contain a value that refers to the containing type annotation. 
		//2.	The containing type annotation must include an element named value(), 
		//		which is a primitive array of the repeatable annotation type.

		//Using common Annotations
		
		//marking methods with @Override
		
		//declaring Interfaces with @FunctionalInterface
		
		//retiring code with @Deprecated
		
		//ignoring warnings with @SuppressWarnings
		
		//protecting Arguments with @SafeWarargs
		//The @SafeVargs marker annotation indicates that a method does not perform any potential unsafe operations 
		//on its varargs parameter. It can be applied only to constructors or methods 
		//that cannot be overridden (aka methods marked private, static, or final).
		
		//javax.validation annotations:
		//@NotNull: Object cannot be null 
		//@NotEmpty: Object cannot be null or have size of 0 
		//@Size(min=5,max=10): Sets minimum and/or maximum sizes 
		//@Max(600) and 
		//@Min(‐5): Sets the maximum or minimum numeric values 
		//@Email: Validates that the email is in a valid format
		
	}
	
	private static void testTest() {
		
		out.println("--------------testTest-----------");

/*
		1.	e
		2.	df
		3.	cde(bde)
		4.	d
		5.	bc
		6.	bdeg(eg)
		7.	abcdef
		8.	bf
		9.	c(d)
		10.	a(g)
		11.	cdf
		12.	bcd
		13.	a(ad)
		14.	d
		15.	b
		16.	f
		17.	e(be)
		18.	cde(cdef)
		19.	a(af)
		20.	(df)
		21.	b(g)
		22.	f
		23.	b(a)
		24.	ae
		25.	cd(c)
		

*/
		
	}
	
}

///////////////////////////////////////////////////////////////

@interface Alert {}

@interface Exercise {
	int hoursPerDay();
	int startHour() default 8;	
}

@Alert
@Exercise(hoursPerDay=8) 
class Cheetah {}

@Alert()
@Exercise(hoursPerDay=8,startHour=6)
class Sloth {}
 
@Exercise(startHour=6, hoursPerDay=8)
class ZooEmployee {}

///////////////////////////////////////////////////////////////

@interface BadAnnotation {
    String address() default "";
	//String name() default 10;				 // DOES NOT COMPILE
    //String name() default new String("");  // DOES NOT COMPILE
    //String title() default null;           // DOES NOT COMPILE
 }

///////////////////////////////////////////////////////////////

class Bear {}

enum Size {SMALL, MEDIUM, LARGE}
 
@interface Panda {
	//void test();							// DOES NOT COMPILE
	//Integer height();						// DOES NOT COMPILE
	//String[][] generalInfo();				// DOES NOT COMPILE
	//Bear friendlyBear();					// DOES NOT COMPILE
	Size size() default Size.SMALL;
	Exercise exercise() default @Exercise(hoursPerDay=2);
}

///////////////////////////////////////////////////////////////

@interface Material {}

@interface Fluffy {
   int cuteness();
   public abstract int softness() default 11;
   Material material();
   //protected Material material();  // DOES NOT COMPILE
   //private String friendly();      // DOES NOT COMPILE
   //final boolean isBunny();        // DOES NOT COMPILE
}

///////////////////////////////////////////////////////////////

@interface ElectricitySource {
	public int voltage();
	int MIN_VOLTAGE = 2;
	public static final int MAX_VOLTAGE = 18;
}

///////////////////////////////////////////////////////////////

@interface Swimmer {
	   int armLength = 10;
	   String stroke();
	   String name();
	   String favoriteStroke() default "Backstroke";
}

//@Swimmer class Amphibian {}												// DOES NOT COMPILE
//@Swimmer(favoriteStroke="Breaststroke", name="Sally") class Tadpole {}	// DOES NOT COMPILE
//@Swimmer(stroke="Butterfly", name="Kip", armLength=1) class Reptile {}	// DOES NOT COMPILE
//since armLength is a constant, not an element, and cannot be included in an annotation.

@Swimmer(stroke="FrogKick", name="Kermit") class Frog {}
@Swimmer(stroke="", name="", favoriteStroke="") class Snake {}

///////////////////////////////////////////////////////////////

@interface Injured {
	   String veterinarian() default "unassigned";
	   String value() default "foot";
	   int age() default 1;
}
		 
abstract class Elephant {
	   @Injured("Legs") public void fallDown() {}
	   @Injured(value="Legs") public abstract int trip1();
	   @Injured(value="Legs",age=10) public abstract int trip2();
	   @Injured String injuries[];
	   //@Injured("Legs",age=10) public abstract int trip3();	// DOES NOT COMPILE	   
}

//make sure that if the shorthand notation is used, then there is an element named value(). 
//Also, check that there are no other required elements.

@interface Sleep {
   int value();
   String hours();   
}
	 
@interface Wake {
	String hours();   
}

///////////////////////////////////////////////////////////////

@interface Music {
	   String[] genres();
}

class Giraffe {
	   @Music(genres="Classical") String favorite1;
	   @Music(genres={"Rock and roll"}) String mostDisliked;
	   
	   @Music(genres={"Classical","Country"}) String favorite2;	   
}

class Reindeer {
	   //@Music(genres="Blues","Jazz") String favorite;  // DOES NOT COMPILE
	   //@Music(genres=) String mostDisliked;            // DOES NOT COMPILE
	   //@Music(genres=null) String other;               // DOES NOT COMPILE
	   @Music(genres={}) String alternate;
}

//Combining Shorthand Notations

@interface Rhythm {
    String[] value();
 }

class Capybara {
    @Rhythm(value={"Swing"}) String favorite;
    @Rhythm(value="R&B") String secondFavorite;
    @Rhythm({"Classical"}) String mostDisliked;
    @Rhythm("Country") String lastDisliked;
 }

///////////////////////////////////////////////////////////////

//import java.lang.annotation.ElementType;
//import java.lang.annotation.Target;
 
@Target({ElementType.METHOD,ElementType.CONSTRUCTOR})
@interface ZooAttraction {}

//@ZooAttraction class RollerCoaster {}  			// DOES NOT COMPILE

class Events {
    @ZooAttraction 
    String rideTrain() {
        //return (@ZooAttraction String) "Fun!";  	// DOES NOT COMPILE
        return "Fun!";
    }
    
    @ZooAttraction 
    //Events(@ZooAttraction String description) {  	// DOES NOT COMPILE
    Events(String description) {
       super();
    }
    
    //@ZooAttraction int numPassengers;   			// DOES NOT COMPILE
}

///////////////////////////////////////////////////////////////

//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
 
@Retention(RetentionPolicy.CLASS) @interface Flier {}
@Retention(RetentionPolicy.RUNTIME) @interface Winter {}

//In the above example, both annotations will retain the annotation information in their .class files, 
//although only Winter will be available (via reflection) at runtime.

///////////////////////////////////////////////////////////////

//Hunter.java
//import java.lang.annotation.Documented;

@Documented
@interface Hunter {}

//Lion.java
@Hunter class Deer {}

//In this example, the @Hunter annotation would be published with the Deer Javadoc information 
//because it's marked with the @Documented annotation.

///////////////////////////////////////////////////////////////

//Vertebrate.java
//import java.lang.annotation.Inherited;

@Inherited
@interface Vertebrate {} 

//Mammal.java
@Vertebrate
class Mammal {}

//Dolphin.java
class Dolphin extends Mammal {}

//In this example, the @Vertebrate annotation will be applied to both Mammal and Dolphin objects. 
//Without the @Inherited annotation, @Vertebrate would apply only to Mammal instances.

///////////////////////////////////////////////////////////////

@interface Risks {
	   Risk[] value();
}
	 
@Repeatable(Risks.class)
@interface Risk {
	   String danger();
	   int level() default 1;
}


class Zoo {
	   public static class Monkey {}
	 
	   @Risk(danger="Silly")
	   @Risk(danger="Aggressive",level=5)
	   @Risk(danger="Violent",level=10)
	   private Monkey monkey1;
	   
	   @Risks({
	       @Risk(danger="Silly"),
	       @Risk(danger="Aggressive",level=5),
	       @Risk(danger="Violent",level=10)
	    })
	    private Monkey monkey2;

}

@interface Risks2 {
	   Risk2[] value();
}
	 
@interface Risk2 {
	   String danger();
	   int level() default 1;
}


class Zoo2 {
	   public static class Monkey {}
	 
	   //@Risk2(danger="Silly")  				// DOES NOT COMPILE
	   //@Risk2(danger="Aggressive",level=5)  	// DOES NOT COMPILE
	   //@Risk2(danger="Violent",level=10)  	// DOES NOT COMPILE
	   private Monkey monkey1;
	   
	   @Risks2({
	       @Risk2(danger="Silly"),
	       @Risk2(danger="Aggressive",level=5),
	       @Risk2(danger="Violent",level=10)
	   })
	   private Monkey monkey2;

}

////////////////////////////////////////////////////////////////////////

//import java.lang.annotation.*;
@Documented 
@Deprecated
@interface Driver {
   int[] value();
   String name() default "";
}

@Driver(1)
class Taxi {}

@Target(ElementType.TYPE)
@interface Furry {
    public String[] value();
    boolean cute() default true;
 }
 
class Bunnyx {
    //@Furry("Soft") 
    public static int hop() {
       return 1;
    }
}


 
