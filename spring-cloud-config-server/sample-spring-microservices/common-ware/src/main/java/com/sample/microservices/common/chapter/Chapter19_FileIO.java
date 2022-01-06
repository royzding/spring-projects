package com.sample.microservices.common.chapter;

import static java.lang.System.out;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import lombok.Data;

public class Chapter19_FileIO {


	public static void main(String[] args) throws Exception {

		//testTemplet();
		TestFileIO();
		//testTest();
		
			
	}

	private static void testTemplet() {
		
	}
	
    static int counter = 0;
    
    static String ZOO_FILE = "C:/tmp/tmp2/zoo.log";

    private static void TestFileIO() throws Exception {
		
		out.println("--------------TestFileIO-----------");

		//Understanding Files and Directories
		
		//Local separator character
		
		System.out.println(System.getProperty("file.separator"));
		System.out.println(java.io.File.separator);
		
		//creates a File object and determines whether the path it references exists within the file system: 
			 
		var zooFile1 = new File("C:\\tmp\\zoo.txt");
		System.out.println(zooFile1.exists());  // true if the file exists

		var zooFile2 = new File("C:\\tmp\\zoo.log");
		System.out.println(zooFile2.exists());  // true if the file exists
		
		//public File(String pathname);
		//public File(File parent, String child);
		//public File(String parent, String child);
		 
		File tmp1 = new File("C:/tmp/tmp2/tmp.txt");
		File parent = new File("C:/tmp");
		File tmp2 = new File(parent, "tmp2/tmp.txt");
		File tmp3 = new File("C:/tmp", "tmp2/tmp.txt");
		System.out.println(tmp1.exists());  // true if the file exists
		System.out.println(tmp2.exists());  // true if the file exists
		System.out.println(tmp3.exists());  // true if the file exists
		
		//Commonly used java.io.File methods
		
		//Method Name 					Description 
		//-----------------------------------------------------------------------------------------
		//boolean delete()  			Deletes the file or directory and returns true only if successful. If this instance denotes a directory, then the directory must be empty in order to be deleted. 
		//boolean exists()  			Checks if a file exists 
		//String getAbsolutePath()  	Retrieves the absolute name of the file or directory within the file system 
		//String getName()  			Retrieves the name of the file or directory. 
		//String getParent()  			Retrieves the parent directory that the path is contained in or null if there is none 
		//boolean isDirectory()  		Checks if a File reference is a directory within the file system 
		//boolean isFile()  			Checks if a File reference is a file within the file system
		//long lastModified()  			Returns the number of milliseconds since the epoch (number of milliseconds since 12 a.m. UTC on January 1, 1970) when the file was last modified 
		//long length()  				Retrieves the number of bytes in the file 
		//File[] listFiles()  			Retrieves a list of files within a directory 
		//boolean mkdir()  				Creates the directory named by this path 
		//boolean mkdirs()  			Creates the directory named by this path including any nonexistent parent directories 
		//boolean renameTo(File dest)  	Renames the file or directory denoted by this path to dest and returns true only if successful

		var file = new File("c:\\tmp\\zoo.log");
		System.out.println("File Exists: " + file.exists());
		
		System.out.println("file.delete() = " + file.delete());
		
		if (file.exists()) {
		   System.out.println("Absolute Path: " + file.getAbsolutePath());
		   System.out.println("Is Directory: " + file.isDirectory());
		   System.out.println("Parent Path: " + file.getParent());

		   if (file.isFile()) {			      
			   System.out.println("Size: " + file.length());			      
			   System.out.println("Last Modified: " + file.lastModified());			   
		   } else {			      
			   for (File subfile : file.listFiles()) {			         
				   System.out.println("   " + subfile.getName());			      
			   }			   
		   }			
		}


		//Differences between Byte and Character Streams
		
		//Byte 		streams read/write binary data ( 0s and 1s) and have class names that end in InputStream or OutputStream. 
		//Character streams read/write text data and have class names that end in Reader or Writer.
		
		//Character Encoding in Java
		
		Charset usAsciiCharset = Charset.forName("US-ASCII");
	    Charset utf8Charset = Charset.forName("UTF-8");
	    Charset utf16Charset = Charset.forName("UTF-16");

	    //Review of java.io Class Name Properties 
	    //1.	A class with the word InputStream or OutputStream in its name is used for reading 
	    //		or writing binary or byte data, respectively. 
	    //2.	A class with the word Reader or Writer in its name is used for reading 
	    //		or writing character or string data, respectively. 
	    //3.	Most, but not all, input classes have a corresponding output class. 
	    //4.	A low‐level stream connects directly with the source of the data. 
	    //5.	A high‐level stream is built on top of another stream using wrapping. 
	    //6.	A class with Buffered in its name reads or writes data in groups of bytes 
	    //		or characters and often improves performance in sequential file systems. 
	    //7.	With a few exceptions, you only wrap a stream with another stream if they share the same abstract parent.

	    //The java.io abstract stream base classes 
	    
	    //Class Name 			Description 
	    //-----------------------------------------------------------------
	    //InputStream  			Abstract class for all input byte streams 
	    //OutputStream  		Abstract class for all output byte streams 
	    //Reader  				Abstract class for all input character streams 
	    //Writer  				Abstract class for all output character streams
	    
	    //The java.io concrete stream classes 
	    
	    //Class Name 			Low/High Level 			Description 
	    //----------------------------------------------------------------------------------------------
	    //FileInputStream  		Low 					Reads file data as bytes 
	    //FileOutputStream  	Low 					Writes file data as bytes
	    //FileReader  			Low 					Reads file data as characters 
	    //FileWriter  			Low 					Writes file data as characters 
	    //BufferedInputStream  	High 					Reads byte data from an existing InputStream in a buffered manner, which improves efficiency and performance 
	    //BufferedOutputStream  High 					Writes byte data to an existing OutputStream in a buffered manner, which improves efficiency and performance 
	    //BufferedReader  		High 					Reads character data from an existing Reader in a buffered manner, which improves efficiency and performance 
	    //BufferedWriter  		High 					Writes character data to an existing Writer in a buffered manner, which improves efficiency and performance 
	    //ObjectInputStream  	High	 				Deserializes primitive Java data types and graphs of Java objects from an existing InputStream 
	    //ObjectOutputStream  	High 					Serializes primitive Java data types and graphs of Java objects to an existing OutputStream 
	    //PrintStream  			High 					Writes formatted representations of Java objects to a binary stream 
	    //PrintWriter  			High 					Writes formatted representations of Java objects to a character stream
	    
	    //Common I/O Stream Operations
	    
	    //Reading and Writing Data
	    
	    // InputStream and Reader
	    //public int read() throws IOException

	    // OutputStream and Writer
	    //public void write(int b) throws IOException
	    
	    //see copyStream methods for details
	    
	    // InputStream
	    //public int read(byte[] b) throws IOException
	    //public int read(byte[] b, int offset, int length) throws IOException
	     
	    // OutputStream
	    //public void write(byte[] b) throws IOException
	    //public void write(byte[] b, int offset, int length) throws IOException

	    // Reader
	    //public int read(char[] c) throws IOException
	    //public int read(char[] c, int offset, int length) throws IOException
	     
	    // Writer
	    //public void write(char[] c) throws IOException
	    //public void write(char[] c, int offset, int length) throws IOException
	    
	    //Closing the Stream
	    
	    // All I/O stream classes
	    //public void close() throws IOException

	    //Since all I/O streams implement Closeable, the best way to do this is with a try‐with‐resources statement

	    try (var fis = new FileInputStream(ZOO_FILE)) {
	    	   System.out.print("fis.read()=" + fis.read());
	    }

	    //Closeable extends AutoCloseable and Closeable is mainly directed to IO streams. 
	    //Closeable extends IOException whereas AutoCloseable extends Exception. 
	    //Closeable interface is idempotent (calling close() method more than once 
	    //does not have any side effects) whereas AutoCloseable does not provide this feature
	    
	    //Closing Wrapped Streams
	    
	    //When working with a wrapped stream, you only need to use close() on the topmost object. 
	    //Doing so will close the underlying streams. The following example is valid and will 
	    //result in three separate close() method calls but is unnecessary: 
	    
	    try (var fis = new FileOutputStream(ZOO_FILE); // Unnecessary
	            var bis = new BufferedOutputStream(fis);
	            var ois = new ObjectOutputStream(bis)) {
	          ois.writeObject("Hello");   
	    }
	    
	    //Instead, we can rely on the ObjectOutputStream to close the BufferedOutputStream and 
	    //FileOutputStream. The following will call only one close() method instead of three: 
	    
	    try (var ois = new ObjectOutputStream(
	             new BufferedOutputStream(
	                new FileOutputStream(ZOO_FILE)))) {
	          ois.writeObject("Hello");	          		
	    }

	    //Manipulating Input Streams
	    	    
	    // InputStream and Reader
	    //public boolean markSupported()
	    //public void void mark(int readLimit)
	    //public void reset() throws IOException
	    //public long skip(long n) throws IOException
	    
	    //see readData() for details.
	    
	    //Flushing Output Streams
	    
	    // OutputStream and Writer
	    //public void flush() throws IOException

	    try (var fos = new FileOutputStream(ZOO_FILE)) {
	    	   for(int i=0; i<10; i++) {
	    	      fos.write('a');
	    	      if(i % 2 == 0) {
	    	         fos.flush();
	    	      }
	    	   }	    	
	    }

	    //Common I/O Stream Methods
	    
	    //Stream Class 			Method Name 									Description 
	    //-----------------------------------------------------------------------------------------------
	    //All streams 			void close()  									Closes stream and releases resources 
	    //All input streams 	int read()  									Reads a single byte or returns ‐1 if no bytes were available
	    //InputStream  			int read(byte[] b)  							Reads values into a buffer. Returns number of bytes read 
	    //Reader  				int read(char[] c)  
	    //InputStream  			int read(byte[] b, int offset, int length)  	Reads up to length values into a buffer starting from position offset. Returns number of bytes read 
	    //Reader  				int read(char[] c, int offset, int length)  
	    //All output streams 	void write(int)  								Writes a single byte 
	    //OutputStream  		void write(byte[] b)  							Writes an array of values into the stream 
	    //Writer  				void write(char[] c)  
	    //OutputStream  		void write(byte[] b, int offset, int length)  	Writes length values from an array into the stream, starting with an offset index 
	    //Writer  				void write(char[] c, int offset, int length)  
	    //All input streams 	boolean markSupported()  						Returns true if the stream class supports mark() 
	    //All input streams 	mark(int readLimit)  							Marks the current position in the stream 
	    //All input streams 	void reset()  									Attempts to reset the stream to the mark() position 
	    //All input streams 	long skip(long n)  								Reads and discards a specified number of characters 
	    //All output streams 	void flush()  									Flushes buffered data through the stream

	    //Working with I/O Stream Classes
	    
	    //Reading and Writing Binary Data
	    
	    //public FileInputStream(File file) throws FileNotFoundException
	    //public FileInputStream(String name) throws FileNotFoundException
	     
	    //public FileOutputStream(File file) throws FileNotFoundException
	    //public FileOutputStream(String name) throws FileNotFoundException
	    
	    //see copyFile for details
	    
	    //Buffering Binary Data
	    
	    //public BufferedInputStream(InputStream in)
	    //public BufferedOutputStream(OutputStream out)
	    
	    //see copyFileWithBuffer for details

	    //Reading and Writing Character Data
	    
	    //public FileReader(File file) throws FileNotFoundException
	    //public FileReader(String name) throws FileNotFoundException

	    //public FileWriter(File file) throws FileNotFoundException
	    //public FileWriter(String name) throws FileNotFoundException

	    //see copyTextFile for details
	    
	    //Buffering Character Data
	    
	    //public BufferedReader(Reader in)	    
	    //public BufferedWriter(Writer out)
	 
	    // BufferedReader
	    //public String readLine() throws IOException
	     
	    // BufferedWriter
	    //public void newLine() throws IOException

	    //see copyTextFileWithBuffer for details

	    //Serializing Data
	    
	    //How to Make a Class Serializable 
	    //1.	The class must be marked Serializable. 
	    //2.	Every instance member of the class is serializable, marked transient, 
	    //		or has a null value at the time of serialization.

	    //Storing Data with ObjectOutputStream and ObjectInputStream
	    
	    //public ObjectInputStream(InputStream in) throws IOException
	    //public ObjectOutputStream(OutputStream out) throws IOException

	    // ObjectInputStream
	    //public Object readObject() throws IOException, ClassNotFoundException
	     
	    // ObjectOutputStream
	    //public void writeObject(Object obj) throws IOException
	    
	    //Understanding the Deserialization Creation Process

	    //When you deserialize an object, the constructor of the serialized class, 
	    //along with any instance initializers, is not called when the object is created. 
	    //Java will call the no‐arg constructor of the first nonserializable parent class 
	    //it can find in the class hierarchy. In our Gorilla example, 
	    //this would just be the no‐arg constructor of Object. 
	    
	    //As we stated earlier, any static or transient fields are ignored. 
	    //Values that are not provided will be given their default Java value, 
	    //such as null for String, or 0 for int values.
	    
	    //static fields will be last set if in the same JVM otherwise they will be
	    //static initialized when loading the class

	    var chimpanzees = new ArrayList<Chimpanzee>();
	    chimpanzees.add(new Chimpanzee("Ham", 2, 'A'));
	    chimpanzees.add(new Chimpanzee("Enos", 4, 'B'));
	    File dataFile = new File("C:/tmp/chimpanzee.data");

	    //saveChimpanzeesToFile(chimpanzees, dataFile);
	    var chimpanzeesFromDisk = readChimpanzeesFromFile(dataFile);
	    
	    chimpanzeesFromDisk.forEach(x->System.out.println(x.toString() + " type=" + x.type));
	    
	    //Printing Data
	    
	    //public PrintStream(OutputStream out)	    
	    //public PrintWriter(Writer out)
	    //public PrintStream(File file) throws FileNotFoundException
	    //public PrintStream(String fileName) throws FileNotFoundException
	    //public PrintWriter(File file) throws FileNotFoundException
	    //public PrintWriter(String fileName) throws FileNotFoundException

	    //the PrintWriter class even has a constructor that takes an OutputStream as input. 
	    //This is one of the few exceptions in which we can mix a byte and character stream.
	    //public PrintWriter(OutputStream out)
	    
	    //methods
	    //print(Object obj); int,long,...
	    
	    try (PrintWriter out = new PrintWriter("C:/tmp/zoo2.log")) {
	    	   out.write(String.valueOf(5));  // Writer method
	    	   out.print(5);                  // PrintWriter method
	    	 
	    	   var a = new Chimpanzee();
	    	   out.write(a==null ? "null": a.toString()); // Writer method
	    	   out.print(a);                              // PrintWriter method
	    }

	    //println()
	    //println(Object obj); int,long,...
	    
	    System.out.println(12);
	    System.getProperty("line.separator");
	    System.lineSeparator();

	    // PrintStream
	    //public PrintStream format(String format, Object args…)
	    //public PrintStream format(Locale loc, String format, Object args…)

	    //PrintWriter
	    //PrintWriter	format(Locale l, String format, Object... args); Writes a formatted string to this writer using the specified format string and arguments.
	    //PrintWriter	format(String format, Object... args); Writes a formatted string to this writer using the specified format string and arguments.
	    
	    //printf() is the same as format()
	    
	    String name = "Lindsey";
	    int orderId = 5;
	     
	    // Both print: Hello Lindsey, order 5 is ready
	    System.out.format("Hello "+name+", order "+orderId+" is ready");
	    System.out.format("Hello %s, order %d is ready", name, orderId);

	    //Common print stream format() symbols Symbol Description %s  Applies to any type, commonly String values %d  Applies to integer values like int and long %f  Applies to floating‐point values like float and double %n  Inserts a line break using the system‐dependent line separator

	    String name2 = "James";
	    double score2 = 90.25;
	    int total2 = 100;
	    System.out.format("%s:%n   Score: %f out of %d", name2, score2, total2);

	    //Mixing data types may cause exceptions at runtime.
	    //System.out.format("Food: %d tons", 2.0); // IllegalFormatConversionException

	    var pi = 3.14159265359;
	    System.out.format("[%f]",pi);      // [3.141593]
	    System.out.format("[%12.8f]",pi);  // [  3.14159265]
	    System.out.format("[%012f]",pi);   // [00003.141593]
	    System.out.format("[%12.2f]",pi);  // [        3.14]
	    System.out.format("[%.3f]",pi);    // [3.142]

	    //PrintWriter Program Example
	    
	    File source = new File("C:/tmp/zoo3.log");
	    try (var out = new PrintWriter(
	       new BufferedWriter(new FileWriter(source)))) {
	       out.print("Today's weather is: ");
	       out.println("Sunny");
	       out.print("Today's temperature at the zoo is: ");
	       out.print(1 / 3.0);
	       out.println('C');
	       out.format("It has rained %5.2f inches this year %d", 10.2, 2021);
	       out.println();
	       out.printf("It may rain %s more inches this year", 1.2);
	    }

	    //Interacting with Users
	    
	    //System Streams (System.out, System.err, and System.in)
	    
	    //Console class
	    
	    //Acquiring input with Console
	    
	    //The Console class is a singleton because it is accessible only from a factory method 
	    //and only one instance of it is created by the JVM.

	    //Console c = new Console();  // DOES NOT COMPILE

	    Console console = System.console();
	    if (console != null) {
	       String userInput = console.readLine();
	       console.writer().println("You entered: " + userInput);
	    } else {
	       System.err.println("Console not available");
	    }


    }
    
    void copyStream(InputStream in, OutputStream out) throws IOException {
    	   int b;
    	   while ((b = in.read()) != -1) {
    	      out.write(b);
    	   }
    	
    }
    	    	
    void copyStream(Reader in, Writer out) throws IOException {
    	   int b;
    	   while ((b = in.read()) != -1) {
    	      out.write(b);
    	   }	
    }

    //values are LION
    public static void readData1(InputStream is) throws IOException {
    	
    	   System.out.print((char) is.read());     // L
    	   if (is.markSupported()) {
    	      is.mark(100);  // Marks up to 100 bytes
    	      System.out.print((char) is.read());  // I
    	      System.out.print((char) is.read());  // O
    	      is.reset();    // Resets stream to position before I
    	   }
    	   System.out.print((char) is.read());    // I
    	   System.out.print((char) is.read());    // O
    	   System.out.print((char) is.read());    // N
    	
    }
	    	
    //values are TIGERS
    public static void readData2(InputStream is) throws IOException {
    	
    	System.out.print ((char)is.read()); // T
    	is.skip(2);  // Skips I and G
    	is.read();   // Reads E but doesn't output it
    	System.out.print((char)is.read());  // R
    	System.out.print((char)is.read());  // S

    }

    void copyFile(File src, File dest) throws IOException {
    	   try (var in = new FileInputStream(src);
    	        var out = new FileOutputStream(dest)) {
    	      int b;
    	      while ((b = in.read()) != -1) {
    	         out.write(b);
    	      }
    	   }
    }

    void copyFileWithBuffer(File src, File dest) throws IOException {
    	   try (var in = new BufferedInputStream(
    	           new FileInputStream(src));
    	        var out = new BufferedOutputStream(
    	           new FileOutputStream(dest))) {
    		   var buffer = new byte[1024];
    		      int lengthRead;
    		      while ((lengthRead = in.read(buffer))> 0) {
    		         out.write(buffer, 0, lengthRead);
    		         out.flush();    		      
    		      }    		   
    	   }    		
    }

    void copyTextFile(File src, File dest) throws IOException {
    	   try (var reader = new FileReader(src);
    	        var writer = new FileWriter(dest)) {
    	      int b;
    	      while ((b = reader.read()) != -1) {
    	         writer.write(b);
    	      }    	   
    	   }    	
    }

    void copyTextFileWithBuffer(File src, File dest) throws IOException {
    	   try (var reader = new BufferedReader(new FileReader(src));
    	        var writer = new BufferedWriter(new FileWriter(dest))) {
    	      String s;
    	      while ((s = reader.readLine()) != null) {
    	         writer.write(s);
    	         writer.newLine();
    	      }    	  
    	   }    	
    }

    void saveToFile(List<Gorilla> gorillas, File dataFile)
  	      throws IOException {
  	   try (var out = new ObjectOutputStream(
  	           new BufferedOutputStream(
  	              new FileOutputStream(dataFile)))) {
  	      for (Gorilla gorilla : gorillas)
  	         out.writeObject(gorilla);    	   
  	   }    	
    }

    List<Gorilla> readFromFile(File dataFile) throws IOException, ClassNotFoundException {
		 var gorillas = new ArrayList<Gorilla>();
		 try (var in = new ObjectInputStream(
		         new BufferedInputStream(
		            new FileInputStream(dataFile)))) {
		    while (true) {
		       var object = in.readObject();
		       if (object instanceof Gorilla)
		          gorillas.add((Gorilla) object);
		    }
		 } catch (EOFException e) {
		    // File end reached
		 }
		 return gorillas;
	}

    static void saveChimpanzeesToFile(List<Chimpanzee> chimpanzees, File dataFile)
    	      throws IOException {
    	   try (var out = new ObjectOutputStream(
    	           new BufferedOutputStream(
    	              new FileOutputStream(dataFile)))) {
    	      for (Chimpanzee chimpanzee : chimpanzees)
    	         out.writeObject(chimpanzee);    	   
    	   }    	
    }

    static List<Chimpanzee> readChimpanzeesFromFile(File dataFile) throws IOException, ClassNotFoundException {
		 var chimpanzees = new ArrayList<Chimpanzee>();
		 try (var in = new ObjectInputStream(
		         new BufferedInputStream(
		            new FileInputStream(dataFile)))) {
		    while (true) {
		       var object = in.readObject();
		       if (object instanceof Chimpanzee)
		    	   chimpanzees.add((Chimpanzee) object);
		    }
		 } catch (EOFException e) {
		    // File end reached
		 }
		 return chimpanzees;
	}


    
    private static void testTest() throws InterruptedException, ExecutionException {
		
		out.println("--------------testTest-----------");
		
		Bank.test();

/*
		1.	f
		2.	ceg
		3.	g(b)
		4.	d(ad)
		5.	bef(be)
		6.	ce(cde)
		7.	g
		8.	af
		9.	a
		10.	ef
		11.	c
		12.	ad(e)
		13.	be
		14.	ace
		15.	e
		16.	d(ad)
		17.	c
		18.	bcd
		19.	d(ceg)
		20.	c
		21.	ac
		22.	abc(g)


*/
		
		
	}
	
}


@Data
class Gorilla implements Serializable {
	   private static final long serialVersionUID = 1L;
	   private String name;
	   private int age;
	   private Boolean friendly;
	   private transient String favoriteFood;
}

@Data
class Chimpanzee implements Serializable {
	   
	private static final long serialVersionUID = 2L;	   
	private transient String name;	   
	private transient int age = 10;	   
	public static char type = 'C';
	   
	{ this.age = 14; }
	 
	public Chimpanzee() {
		   this.name = "Unknown";
		      this.age = 12;
		      this.type = 'Q';
	}
		 
	public Chimpanzee(String name, int age, char type) {
		      this.name = name;
		      this.age = age;
		      this.type = type;
	}	
	
	
}
	
	
	

