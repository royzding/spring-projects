package com.sample.microservices.common.chapter;

import static java.lang.System.out;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Locale.Category;

public class Chapter16_ExceptionsAndAssertionsAndLocalization {


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

		//Exceptions as the same as in chapter 10.
		
		//Learning the new Effectively Final Feature
		
		//it is possible to use resources declared prior to the try-with-resources statement, 
		//provided they are marked final or effectively final otherwise causing the compile error.
		
		//The syntax is just to use the resource name in place of the resource declaration, separated by a semicolon ( ;).


		final var bookReader = new MyFileReader("book-1");
		MyFileReader movieReader = new MyFileReader("movie-3");
		
		try (bookReader;
		     var tvReader = new MyFileReader("tv-2");
		     movieReader) {
		     System.out.println("Try Block");
		}catch (Exception e) {
		  	System.out.println("Catch Block");
		} finally {
		    System.out.println("Finally Block");
		}
		
		var freeReader = new MyFileReader("free-1");
		var busyReader = new MyFileReader("busy-2");
		
		try (//freeReader; //do not compile since freeReader is not effectively final
			 busyReader) {
		     System.out.println("Try Block");
		}catch (Exception e) {
		  	System.out.println("Catch Block");
		} finally {
		    System.out.println("Finally Block");
		}
		
		freeReader = null;
		
		//busyReader.readSomething(); //exception may be caused in runtime since the resource was already closed.
		
		//Take Care When Using Resources Declared before try‐with‐resources Statements
		
		//41: var writer = Files.newBufferedWriter(path);
		//42: writer.append("This write is permitted but a really bad idea!"); //do not do this.
		//43: try(writer) {
		//44:    writer.append("Welcome to the zoo!");
		//45: }
		//46: writer.append("This write will fail!");  // IOException

		//51: var reader = Files.newBufferedReader(path1);
	    //52: var writer = Files.newBufferedWriter(path2);  // Don’t do this!
	    //53: try (reader; writer) {}
		
		//if line 42 or line 52 throws an exception resources declared on line 41 or 51 will never be closed. 
		
		//understanding Suppressed Exceptions
		
		//Declaring Assertions
		
		//the syntax for an assert statement has two forms
		
		//1. assert test_value;
		//2. assert test_value : message;
		
		//assert: keyword
		//test_value: Boolean expression
		
		//The three possible outcomes of an assert statement are as follows: 
		
		//1.	If assertions are disabled, Java skips the assertion and goes on in the code. 
		//2.	If assertions are enabled and the boolean expression is true, 
		//		then our assertion has been validated and nothing happens. 
		//		The program continues to execute in its normal manner. 
		//3.	If assertions are enabled and the boolean expression is false, 
		//		then our assertion is invalid and an AssertionError is thrown.

		//Presuming assertions are enabled, an assertion is a shorter way of writing the following:
		//if (!boolean_expression) throw new AssertionError(error_message);
		
		//enabling assertions in all classes except system classes
		
		//java -enableassertions Rectangle
		//java -ea Rectangle

		//enabling assertions in specific class or package 
		//(only for classes in the com.demos package and any subpackages.)
		
		//java -enableassertions:com.demos... Rectangle
		//java -ea:com.demos... Rectangle

		//(only for class TestColor.)
		//java -enableassertions:com.demos.TestColor Rectangle
		//java -ea:com.demos.TestColor Rectangle
		
		//disabling assertions
		//Java offers the ‐disableassertions or ‐da flag for just such an occasion.

		//(enable classes in the com.demos package and any subpackages but diable the class TestColor.)
		//java -ea:com.demos… -da:com.demos.TestColor my.programs.Main
		
		//By default, all assertions are disabled.

		//One of the most important rules you should remember from this section is: 
		//assertions should never alter program state (outcomes).

		//Working with Dates and Times
		
		//creating Dates and Times
		
		//Class  					Description  								Example  
		//---------------------------------------------------------------------------------------------
		//java.time.LocalDate  		Date with day, month, year 					Birth date 
		//java.time.LocalTime  		Time of day 								Midnight 
		//java.time.LocalDateTime  	Day and time with no time zone 				10 a.m. next Monday 
		//java.time.ZonedDateTime  	Date and time with a specific time zone 	9 a.m. EST on 2/20/2021
		
		//public static LocalDate now();

		System.out.println(LocalDate.now());
		System.out.println(LocalTime.now());
		System.out.println(LocalDateTime.now());
		System.out.println(ZonedDateTime.now());

		//2021-12-27
		//16:18:23.382609
		//2021-12-27T16:18:23.382609
		//2021-12-27T16:18:23.384576600-05:00[America/New_York]
		
		//of() method
		
		//static LocalDate	of(int year, int month, int dayOfMonth);
		//static LocalDate	of(int year, Month month, int dayOfMonth);
		
		LocalDate date1 = LocalDate.of(2020, Month.OCTOBER, 20);
		LocalDate date2 = LocalDate.of(2020, 10, 20);

		LocalTime time1 = LocalTime.of(6, 15);           // hour and minute
		LocalTime time2 = LocalTime.of(6, 15, 30);       // + seconds
		LocalTime time3 = LocalTime.of(6, 15, 30, 200);  // + nanoseconds

		var dt1 = LocalDateTime.of(2020, Month.OCTOBER, 20, 6, 15, 30);
		 
		LocalDate date3 = LocalDate.of(2020, Month.OCTOBER, 20);
		LocalTime time = LocalTime.of(6, 15);
		var dt2 = LocalDateTime.of(date3, time);
		
		//getting data out of the Dates and Times
		
		LocalDate date4 = LocalDate.of(2020, Month.OCTOBER, 20);
		System.out.println(date4.getDayOfWeek());  // TUESDAY
		System.out.println(date4.getMonth());      // OCTOBER
		System.out.println(date4.getYear());       // 2020
		System.out.println(date4.getDayOfYear());  // 294


		//Formatting Dates and Times
		
		//java.time.format.DateTimeFormatter
		
		LocalDate date5 = LocalDate.of(2020, Month.OCTOBER, 20);
		LocalTime time5 = LocalTime.of(11, 12, 34);
		LocalDateTime datm5 = LocalDateTime.of(date5, time5);
		 
		System.out.println(date5.format(DateTimeFormatter.ISO_LOCAL_DATE));
		System.out.println(time5.format(DateTimeFormatter.ISO_LOCAL_TIME));
		System.out.println(datm5.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)); 
		
		//The code snippet prints the following: 
		//2020-10-20
		//11:12:34
		//2020-10-20T11:12:34

		//The DateTimeFormatter will throw an exception if it encounters an incompatible type.
		//System.out.println(date5.format(DateTimeFormatter.ISO_LOCAL_TIME));
		//System.out.println(time5.format(DateTimeFormatter.ISO_LOCAL_DATE));
		
		//static DateTimeFormatter	ofPattern(String pattern);
		//Creates a formatter using the specified pattern.

		var f = DateTimeFormatter.ofPattern("MMMM dd, yyyy 'at' hh:mm");
		System.out.println(datm5.format(f));  // October 20, 2020 at 11:12

		//Symbol 	Meaning 			Examples 
		//---------------------------------------------------------
		//y 		Year 				20, 2020 
		//M 		Month 				1, 01, Jan, January 
		//d 		Day 				5, 05 
		//h 		Hour 				9, 09 
		//m 		Minute 				45  
		//s 		Second 				52  
		//a 		a.m./p.m. 			AM, PM
		//z 		Time Zone Name 		Eastern Standard Time, EST 
		//Z 		Time Zone Offset 	‐0400

		var datm6 = LocalDateTime.of(2020, Month.OCTOBER, 20, 6, 15, 30);
		 
		var formatter1 = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss");
		System.out.println(datm6.format(formatter1));
		 
		var formatter2 = DateTimeFormatter.ofPattern("MM_yyyy_-_dd");
		System.out.println(datm6.format(formatter2));
		 
		var formatter3 = DateTimeFormatter.ofPattern("h:mm z");
		//System.out.println(dt.format(formatter3)); 
		
		//The output is as follows: 
		//10/20/2020 06:15:30
		//10_2020_-_20
		//Exception in thread "main" java.time.DateTimeException:
		//   Unable to extract ZoneId from temporal 2020-10-20T06:15:30

		//The date/time classes contain a format() method that will take a formatter, 
		//while the formatter classes contain a format() method that will take a date/time value. 
		//The result is that either of the following is acceptable: 
		var dateTime = LocalDateTime.of(2020, Month.OCTOBER, 20, 6, 15, 30);
		var formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss");
		 
		System.out.println(dateTime.format(formatter)); // 10/20/2020 06:15:30
		System.out.println(formatter.format(dateTime)); // 10/20/2020 06:15:30
		
		//Adding Custom Text Values
		
		//You can escape the text by surrounding it with a pair of single quotes ( '). 
		//Escaping text instructs the formatter to ignore the values inside the single quotes 
		//and just insert them as part of the final value.

		var dt = LocalDateTime.of(2020, Month.OCTOBER, 20, 6, 15, 30);
		 
		var f1 = DateTimeFormatter.ofPattern("MMMM dd, yyyy ");
		var f2 = DateTimeFormatter.ofPattern(" hh:mm");
		System.out.println(dt.format(f1) + "at" + dt.format(f2));

		var f3 = DateTimeFormatter.ofPattern("MMMM dd, yyyy 'at' hh:mm");
		System.out.println(dt.format(f3));  // October 20, 2020 at 06:15

		//to display a single quote in the output too? Welcome to the fun of escaping characters! 
		//Java supports this by putting two single quotes next to each other.

		var g1 = DateTimeFormatter.ofPattern("MMMM dd', Party''s at' hh:mm");
		System.out.println(dt.format(g1)); // October 20, Party's at 06:15
		 
		var g2 = DateTimeFormatter.ofPattern("'System format, hh:mm: 'hh:mm");
		System.out.println(dt.format(g2)); // System format, hh:mm: 06:15
		 
		var g3 = DateTimeFormatter.ofPattern("'NEW! 'yyyy', yay!'");
		System.out.println(dt.format(g3)); // NEW! 2020, yay!

		//DateTimeFormatter.ofPattern("The time is hh:mm");  
		// Exception thrown (java.lang.IllegalArgumentException: Unknown pattern letter: T)
		
		//Supporting Internationalization and Localization
		
		//Local formats
		
		Locale locale = Locale.getDefault();
		System.out.println(locale);

		//Locale (language) ex. fr (lowercase language code)
		//Locale (language, country) ex. en_US (lowercase language code underscore Uppercase country code)
		
		//creating Locale
		
		//1.	The first is to use the built‐in constants in the Locale class,
		System.out.println(Locale.GERMAN);  // de
		System.out.println(Locale.GERMANY); // de_DE

		//2.	The second way of selecting a Locale is to use the constructors to create a new object. 
		//		You can pass just a language, or both a language and country:
		System.out.println(new Locale("fr"));       // fr
		System.out.println(new Locale("hi", "IN")); // hi_IN
		
		//3.	The third way to create a Locale is using builder design pattern
		Locale l1 = new Locale.Builder()
				   .setLanguage("en")
				   .setRegion("US")
				   .build();
				 
		Locale l2 = new Locale.Builder()
				   .setRegion("US")
				   .setLanguage("en")
				   .build();

		System.out.println(l1);     // en_US
		System.out.println(l2); 	// en_US
		
		Locale l3 = new Locale.Builder()
				   .setLanguage("xx")
				   .setRegion("XX")
				   .build();
				 
		System.out.println(l3);     // xx_XX

		Locale l4 = new Locale.Builder()
				   .setLanguage("xX")
				   .setRegion("Xx")
				   .build();
				 
		System.out.println(l4);     // xx_XX

		System.out.println(new Locale("fR"));       // fr
		System.out.println(new Locale("Hi", "iN")); // hi_IN

		//Java will let you create a Locale with an invalid language or country, such as xx_XX. However, 
		//it will not match the Locale that you want to use, and your program will not behave as expected.
		
		//Change default Locale
		System.out.println(Locale.getDefault()); 	// en_US
		Locale l5 = new Locale("fr");
		Locale.setDefault(l5);               		// change the default
		System.out.println(Locale.getDefault()); 	// fr
		
		//LOcalizing NUmbers
		//java.text.NumberFormat
		
		//Factory methods to get a NumberFormat
		
		//Using default Locale and a specified Locale 											Description 
		//-----------------------------------------------------------------------------------------------------------------------------
		//NumberFormat.getInstance(); 			NumberFormat.getInstance(locale);  				A general‐purpose formatter 
		//NumberFormat.getNumberInstance(); 	NumberFormat.getNumberInstance(locale);  		Same as getInstance 
		//NumberFormat.getCurrencyInstance(); 	NumberFormat.getCurrencyInstance(locale); 		For formatting monetary amounts 
		//NumberFormat.getPercentInstance(); 	NumberFormat.getPercentInstance(locale);  		For formatting percentages 
		//NumberFormat.getIntegerInstance(); 	NumberFormat.getIntegerInstance(locale);		Rounds decimal values before displaying 

		//String	format(double number);
		//String	format(long number);
		//Specialization of format.
		
		//formatting numbers
		
		int attendeesPerYear = 3_200_000;
		int attendeesPerMonth = attendeesPerYear / 12;
		 
		var us = NumberFormat.getInstance(Locale.US);
		System.out.println(us.format(attendeesPerMonth));
		 
		var gr = NumberFormat.getInstance(Locale.GERMANY);
		System.out.println(gr.format(attendeesPerMonth));
		 
		var ca_fr = NumberFormat.getInstance(Locale.CANADA_FRENCH);
		System.out.println(ca_fr.format(attendeesPerMonth));
		
		var ca = NumberFormat.getInstance(Locale.CANADA);
		System.out.println(ca.format(attendeesPerMonth));
		
		//The output looks like this: 
		//266,666
		//266.666
		//266 666
		//266,666
		
		//formating currency
		
		double price = 48;
		var us_cu = NumberFormat.getCurrencyInstance(Locale.US);
		System.out.println(us_cu.format(price));

		var gr_cu = NumberFormat.getCurrencyInstance(Locale.GERMANY);
		System.out.println(gr_cu.format(price));
		 
		var ca_fr_cu = NumberFormat.getCurrencyInstance(Locale.CANADA_FRENCH);
		System.out.println(ca_fr_cu.format(price));
		
		var ca_cu = NumberFormat.getCurrencyInstance(Locale.CANADA);
		System.out.println(ca_cu.format(price));
		
		//The output looks like this: 
		//$48.00
		//48,00 €
		//48,00 $
		//$48.00		
	
		//parsing Numbers
		
		//Number	parse(String source);
		//Parses text from the beginning of the given string to produce a number.
		
		String s = "40.45";
		 
		try {
			var us_pa = NumberFormat.getInstance(Locale.US);
			System.out.println(us_pa.parse(s));						// 40.45
			
			var fr_pa = NumberFormat.getInstance(Locale.FRANCE);
			System.out.println(fr_pa.parse(s));  					// 40
			
			var gr_pa = NumberFormat.getInstance(Locale.GERMAN);
			System.out.println(gr_pa.parse(s));  					// 4045
			
			String income = "$92,807.99";
			var cf = NumberFormat.getCurrencyInstance(Locale.US);
			double value = (Double) cf.parse(income);
			System.out.println(value); 								// 92807.99			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

		//The lesson is to make sure that you parse the string using the right locale!
		//otherwise the parseException will be thrown or the wrong results.
		
		//Writing a Custom Number Formatter
		
		//DecimalFormat extends NumberFormat
		
		//DecimalFormat symbols
		
		//Symbol 				Meaning 												Examples 
		//# Omit 				the position if no digit exists for it. 				$2.2  
		//0 					Put a 0 in the position if no digit exists for it. 		$002.20

		Locale.setDefault(Locale.US);
		
		double dd = 1234567.467;
		NumberFormat fd1 = new DecimalFormat("###,###,###.0");
		System.out.println(fd1.format(dd));  // 1,234,567.5
		
		NumberFormat fd3 = new DecimalFormat("###.0");
		System.out.println(fd3.format(dd));  // 1234567.5
		
		NumberFormat fd4 = new DecimalFormat("$#,###,###.##");
		System.out.println(fd4.format(dd));  // $1,234,567.47
		
		NumberFormat fd5 = new DecimalFormat("%###,###,###.##");
		System.out.println(fd5.format(dd));  // %123,456,746.7			//unpredicatible
		
		NumberFormat fd6 = new DecimalFormat("&###,###,###.##");
		System.out.println(fd6.format(dd));  // &1,234,567.47			//unpredicatible
		
		NumberFormat fd2 = new DecimalFormat("000,000,000.00000");
		System.out.println(fd2.format(dd));  // 001,234,567.46700

		//Localizing Dates

		//Factory methods to get a DateTimeFormatter
		
		//Using default Locale  										Description 
		//-----------------------------------------------------------------------------------------------
		//DateTimeFormatter.ofLocalizedDate(dateStyle)  				For formatting dates
		//DateTimeFormatter.ofLocalizedTime(timeStyle)  				For formatting times  
		//DateTimeFormatter.ofLocalizedDateTime(dateStyle, timeStyle) 	For formatting dates and times
		//DateTimeFormatter.ofLocalizedDateTime(dateTimeStyle)        	For formatting dates and times

		//Using specific Locale
		//dtf.withLocale(locale).format(dateTime);
		
		Locale.setDefault(new Locale("en", "US"));
		var italy = new Locale("it", "IT");
		var dtx1 = LocalDateTime.of(2020, Month.OCTOBER, 20, 15, 12, 34);

		// 10/20/20, 20/10/20
		print(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT),dt,italy);

		// 3:12 PM, 15:12
		print(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT),dt,italy);
		 
		// 10/20/20, 3:12 PM, 20/10/20, 15:12
		print(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT,FormatStyle.SHORT),dt,italy);
		
		//Specifying a Locale Category
		
		//Locale.Category values
		
		//Value 				Description
		//------------------------------------------------------------------------------------
		//DISPLAY  				Category used for displaying data about the locale 
		//FORMAT  				Category used for formatting dates, numbers, or currencies

		var spain = new Locale("es", "ES");
		var money = 1.23;
		
		// Print with default locale
		Locale.setDefault(new Locale("en", "US"));
		printCurrency(spain, money);  // $1.23, Spanish
		 
		// Print with default locale and selected locale display
		Locale.setDefault(Category.DISPLAY, spain);
		printCurrency(spain, money);  // $1.23, espaÑol

		// Print with default locale and selected locale format
		Locale.setDefault(Category.FORMAT, spain);
		printCurrency(spain, money);  // 1,23 €, espaÑol
		
		//Loading Properties with Resource Bundles
		
		Locale usa           = new Locale("en", "US");
		Locale france        = new Locale("fr", "FR");
		Locale englishCanada = new Locale("en", "CA");
		Locale frenchCanada  = new Locale("fr", "CA");


		
		
	}
	
	//catched the Exception which was thrown from close
	public static void CatchedExceptionFromClose() {

		try (JammedTurkeyCage t = new JammedTurkeyCage()) {
		       System.out.println("Put turkeys in");
		} catch (IllegalStateException e) {
			System.out.println("Caught: " + e.getMessage());
		}
	}
	
	//Caught: Cage door does not close

	
	//When multiple exceptions are thrown, all but the first are called suppressed exceptions. 
	//The idea is that Java treats the first exception as the primary one and 
	//tacks on any that come up while automatically closing.

	//catched the primary Exception with some suppressed exceptions
	public static void CatchedPrimaryException() {

		try (JammedTurkeyCage t = new JammedTurkeyCage()) {			           
			throw new IllegalStateException("Turkeys ran off");			
		} catch (IllegalStateException e) {		
			System.out.println("Caught: " + e.getMessage());			
			for (Throwable t: e.getSuppressed())			
				System.out.println("Suppressed: "+t.getMessage());			    
		}
	}

	//Caught: Turkeys ran off
	//Suppressed: Cage door does not close

	//Keep in mind that the catch block looks for matches on the primary exception.

	//do not catch the primary Exception with some suppressed exceptions
	public static void DoNotCatchPrimaryException() {

		try (JammedTurkeyCage t = new JammedTurkeyCage()) {			           
			throw new RuntimeException("Turkeys ran off");
		} catch (IllegalStateException e) {		
			System.out.println("Caught: " + e.getMessage());			
			for (Throwable t: e.getSuppressed())			
				System.out.println("Suppressed: "+t.getMessage());			    
		}
	}

	/*
		Exception in thread "main" java.lang.RuntimeException: Turkeys ran off
		   at JammedTurkeyCage.main(JammedTurkeyCage.java:7)
		   Suppressed: java.lang.IllegalStateException: 
		         Cage door does not close
		      at JammedTurkeyCage.close(JammedTurkeyCage.java:3)
		      at JammedTurkeyCage.main(JammedTurkeyCage.java:8)
	*/
	
	//Java remembers the suppressed exceptions that go with a primary exception 
	//even if we don't handle them in the code.

	//Keep in mind that suppressed exceptions apply only to exceptions thrown in the try clause. 
	//The following example does not throw a suppressed exception:

	//do not catch the primary Exception with some suppressed exceptions
	//@SuppressWarnings("finally")
	public static void LostSuppressedException() {
		try (JammedTurkeyCage t = new JammedTurkeyCage()) {			           
			throw new IllegalStateException("Turkeys ran off");			//7
		} finally {		
			throw new RuntimeException("and we couldn't find them");	//9		
		}		
	}

	//Line 7 throws an exception. Then Java tries to close the resource and adds a suppressed exception to it. 
	//Now we have a problem. The finally block runs after all this. Since line 9 also throws an exception, 
	//the previous exception from line 7 is lost, with the code printing the following:
	
	/*
		Exception in thread "main" java.lang.RuntimeException:
		   and we couldn't find them
		   at JammedTurkeyCage.main(JammedTurkeyCage.java:9)
	*/

	public static void print(DateTimeFormatter dtf,LocalDateTime dateTime, Locale locale) {   
		System.out.println(dtf.format(dateTime) + ", " + dtf.withLocale(locale).format(dateTime));
	}

	public static void printCurrency(Locale locale, double money) {		    
		System.out.println(NumberFormat.getCurrencyInstance().format(money) + ", " + locale.getDisplayLanguage());
	}

		
	private static void testTest() {
		
		out.println("--------------testTest-----------");

/*
		1.	

*/
		
	}
	
}

class MyFileReader implements AutoCloseable {
	
	private final String name;
	
	public MyFileReader(final String name) {
		this.name = name;
	}

	@Override
	public void close() {
		System.out.println("MyFileReader:close-" + this.name);		
	}
	
	public void readSomething() {
		System.out.println("MyFileReader:readSomething-" + this.name);
	}
	
}

class JammedTurkeyCage implements AutoCloseable {
    public void close() throws IllegalStateException {
        throw new IllegalStateException("Cage door does not close");
   }
}



