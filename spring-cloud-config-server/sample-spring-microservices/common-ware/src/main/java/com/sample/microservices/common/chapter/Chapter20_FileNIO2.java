package com.sample.microservices.common.chapter;

import static java.lang.System.out;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Chapter20_FileNIO2 {


	public static void main(String[] args) throws Exception {

		//testTemplet();
		TestFileNIO2();
		//testTest();
		
			
	}

	private static void testTemplet() {
		
	}
	
    static int counter = 0;
    
    static String ZOO_FILE = "C:/tmp/tmp2/zoo.log";

    private static void TestFileNIO2() throws Exception {
		
		out.println("--------------TestFileNIO2-----------");

		//Introduction Path interface
		
		//Obtaining a Path with the Path interface
		
		//Path factory method
		//static Path	of​(String first, String... more)	Returns a Path by converting a path string, or a sequence of strings that when joined form a path string.
		//static Path	of​(URI uri)							Returns a Path by converting a URI.

		Path path1 = Path.of("pandas/cuddly.png");
		Path path2 = Path.of("c:\\zooinfo\\November\\employees.txt");
		Path path3 = Path.of("/home/zoodirectory");

		Path path4 = Path.of("pandas", "cuddly.png");
		Path path5 = Path.of("c:", "zooinfo", "November", "employees.txt");
		Path path6 = Path.of("/", "home", "zoodirectory");

		//Obtaining a Path with the Paths class
		
		//static Path	get​(String first, String... more)	Converts a path string, or a sequence of strings that when joined form a path string, to a Path.
		//static Path	get​(URI uri)						Converts the given URI to a Path object.
		
		Path path7 = Paths.get("pandas/cuddly.png");
		Path path8 = Paths.get("c:\\zooinfo\\November\\employees.txt");
		Path path9 = Paths.get("/", "home", "zoodirectory");
		
		//// URI Constructor
		//public URI(String str) throws URISyntaxException

		// Path to URI, using Path instance method
		//public URI toURI()

		URI a = new URI("file://C:/tmp/tmp.txt");
		Path b = Path.of(a);
		Path c = Paths.get(a);
		URI d = b.toUri();

		//Path path10 = Paths.get(new URI("https://www.yahoo.com/"));
		//Path path11 = Paths.get(new URI("ftp://username:secret@ftp.example.com"));
		
		//Obtaining a Path from the FileSystem/FileSystems class
		
		//FileSystems factory method
		//static FileSystem	getDefault()			Returns the default FileSystem.
		//static FileSystem	getFileSystem​(URI uri)	Returns a reference to an existing FileSystem.

		//FileSystem instance method
		//abstract Path	getPath​(String first, String... more)	Converts a path string, or a sequence of strings that when joined form a path string, to a Path.

		Path path12 = FileSystems.getDefault().getPath("pandas/cuddly.png");
		Path path13 = FileSystems.getDefault().getPath("c:\\zooinfo\\November\\employees.txt");
		Path path14 = FileSystems.getDefault().getPath("/home/zoodirectory");

		//FileSystem fileSystem = FileSystems.getFileSystem(new URI("http://www.selikoff.net"));
		//Path path15 = fileSystem.getPath("duck.txt");
		
		//Obtaining a Path from the java.io.File class
		
		// Path to File, using Path instance method
		//public default File toFile()
		 
		// File to Path, using java.io.File instance method
		//public Path toPath()

		File file = new File("husky.png");
		Path path = file.toPath();
		File backToFile = path.toFile();

		//Common NIO.2 method arguments 
		
		//Enum type 			Interface inherited  	Enum value 			Details 
		//-------------------------------------------------------------------------------------------------------
		//LinkOption  			CopyOption, OpenOption  NOFOLLOW_LINKS  	Do not follow symbolic links. 
		//StandardCopyOption  	CopyOption  			ATOMIC_MOVE  		Move file as atomic file system operation. 
		//												COPY_ATTRIBUTES  	Copy existing attributes to new file. 
		//												REPLACE_EXISTING  	Overwrite file if it already exists. 
		//StandardOpenOption  	OpenOption  			APPEND  			If file is already open for write, then append to the end. 
		//												CREATE  			Create a new file if it does not exist. 
		//												CREATE_NEW  		Create a new file only if it does not exist, fail otherwise. 
		//												READ  				Open for read access. 
		//												TRUNCATE_EXISTING  	If file is already open for write, then erase file and append to beginning. 
		//												WRITE  				Open for write access. 
		//FileVisitOption  		N/A 					FOLLOW_LINKS  		Follow symbolic links.

		//Common causes of a method throwing this exception include the following: 
		//1.	Loss of communication to underlying file system. 
		//2.	File or directory exists but cannot be accessed or modified. 
		//3.	File exists but cannot be overwritten. 
		//4.	File or directory is required but does not exist.

		//Viewing the Path with toString(), getNameCount(), and getName() 
		
		//public String toString()
		//public int getNameCount() 
		//public Path getName(int index)

		Path path16 = Paths.get("/land/hippo/harry.happy");
		System.out.println("The Path Name is: " + path16);
		for(int i=0; i<path16.getNameCount(); i++) {
			System.out.println("   Element " + i + " is: " + path16.getName(i));
		}
		
		//The Path Name is: /land/hippo/harry.happy
		//   Element 0 is: land
		//   Element 1 is: hippo
		//   Element 2 is: harry.happy

		var p17 = Path.of("/");
		System.out.print(p17.getNameCount()); // 0
		//System.out.print(p17.getName(0));     // IllegalArgumentException

		//Creating a New Path with subpath() The Path interface includes a method to select portions of a path. 
		//public Path subpath(int beginIndex, int endIndex)
		//The references are inclusive of the beginIndex, and exclusive of the endIndex.

		var path18 = Paths.get("/mammal/omnivore/raccoon.image");
		System.out.println("Path is: " + path18);
		for (int i = 0; i < path18.getNameCount(); i++) {
			   System.out.println("   Element " + i + " is: " + path18.getName(i));
			}
			
		System.out.println();
		System.out.println("subpath(0,3): " + path18.subpath(0, 3));
		System.out.println("subpath(1,2): " + path18.subpath(1, 2));
		System.out.println("subpath(1,3): " + path18.subpath(1, 3)); 
			
		/*
			The output of this code snippet is the following: Path is: /mammal/omnivore/raccoon.image
			   Element 0 is: mammal
			   Element 1 is: omnivore
			   Element 2 is: raccoon.image
			 
			subpath(0,3): mammal/omnivore/raccoon.image
			subpath(1,2): omnivore
			subpath(1,3): omnivore/raccoon.image
		*/

		//Like getNameCount() and getName(), subpath() is 0‐indexed and does not include the root. 
		//Also like getName(), subpath() throws an exception if invalid indices are provided.

		//var q = p.subpath(0, 4); // IllegalArgumentException
		//var x = p.subpath(1, 1); // IllegalArgumentException

		//Accessing Path Elements with getFileName(), getParent(), and getRoot() 
		//public Path getFileName() 
		//public Path getParent()
		//public Path getRoot()

		printPathInformation(Path.of("zoo"));
		printPathInformation(Path.of("/zoo/armadillo/shells.txt"));
		printPathInformation(Path.of("./armadillo/../shells.txt"));

		/*
			Filename is: zoo
			   Root is: null
			
			Filename is: shells.txt
			   Root is: /
			   Current parent is: /zoo/armadillo
			   Current parent is: /zoo
			   Current parent is: /
			 
			Filename is: shells.txt
			   Root is: null
			   Current parent is: ./armadillo/..
			   Current parent is: ./armadillo
			   Current parent is: .
		*/
		
		//Checking Path Type with isAbsolute() and toAbsolutePath()

		//public boolean isAbsolute()
		//public Path toAbsolutePath()
		
		//toAbsolutePath(), converts a relative Path object to an absolute Path object 
		//by joining it to the current working directory. If the Path object is already absolute, 
		//then the method just returns the Path object.

		var path19 = Paths.get("C:\\birds\\egret.txt");
		System.out.println("Path1 is Absolute? " + path19.isAbsolute());
		System.out.println("Absolute Path1: " + path19.toAbsolutePath());
		 
		var path20 = Paths.get("birds/condor.txt");
		System.out.println("Path2 is Absolute? " + path20.isAbsolute());
		System.out.println("Absolute Path2 " + path20.toAbsolutePath());
		
		/*
			Path1 is Absolute? true
			Absolute Path1: C:\birds\egret.txt
			Path2 is Absolute? false
			Absolute Path2 C:\workspace\spring-projects\spring-cloud-config-server\sample-spring-microservices\common-ware\birds\condor.txt 
		*/

		//Joining Paths with resolve()
		
		//public Path resolve(Path other) 
		//public Path resolve(String other)

		Path path21 = Path.of("/cats/../panther");
		Path path22 = Path.of("food");
		System.out.println(path21.resolve(path22)); 	//output: /cats/../panther/food
		
		System.out.println(path22.resolve(path21)); 	//output: \cats\..\panther

		Path path23 = Path.of("/cats/../panther");
		Path path24 = Path.of("/food");
		System.out.println(path23.resolve(path24)); 	//output: \food
		System.out.println(path24.resolve(path23)); 	//output: \cats\..\panther

		//If an absolute path is provided as input to the method, then that is the value that is returned.

		//path.resolve() like the concatenation.
		
		//Deriving a Path with relativize()
		
		//public Path relativize(Path path);

		var path25 = Path.of("fish.txt");
		var path26 = Path.of("friendly/birds.txt");
		System.out.println(path25.relativize(path26));	//output:	../friendly/birds.txt
		System.out.println(path26.relativize(path25)); 	//			../../fish.txt
		
		//If both path values are relative, then the relativize() method computes the paths 
		//as if they are in the same current working directory. Alternatively, if both path values are absolute, 
		//then the method computes the relative path from one absolute location to another, 
		//regardless of the current working directory.
		
		//path1.relativize(path2) is from path1 goes to path2.

		Path path27 = Paths.get("E:\\habitat");
		Path path28 = Paths.get("E:\\sanctuary\\raven\\poe.txt");
		System.out.println(path27.relativize(path28));	//output:	..\sanctuary\raven\poe.txt
		System.out.println(path28.relativize(path27)); 	//output:	..\..\..\habitat

		//The relativize() method requires that both paths are absolute or both relative 
		//and throws an exception if the types are mixed. 
		//Path path1 = Paths.get("/primate/chimpanzee");
		//Path path2 = Paths.get("bananas.txt");
		//path1.relativize(path2); // IllegalArgumentException On Windows‐based systems, 
		
		//it also requires that if absolute paths are used, then both paths must have the same root directory 
		//or drive letter. For example, the following would also throw an IllegalArgumentException 
		//on a Windows‐based system:

		//Path path3 = Paths.get("c:\\primate\\chimpanzee");
		//Path path4 = Paths.get("d:\\storage\\bananas.txt");
		//path3.relativize(path4); // IllegalArgumentException

		//Cleaning up a Path with normalize()
		
		//public Path normalize()
		
		var p29 = Path.of("./armadillo/../shells.txt");
		System.out.println(p29.normalize()); // shells.txt
		 
		var p30 = Path.of("/cats/../panther/food");
		System.out.println(p30.normalize()); // /panther/food
		 
		var p31 = Path.of("../../fish.txt");
		System.out.println(p31.normalize()); // ../../fish.txt

		var p31x = Path.of("x0/x1/x2/../../fish.txt");
		System.out.println(p31x.normalize()); // x0/fish.txt

		var p32 = Paths.get("/pony/../weather.txt");
		var p33 = Paths.get("/weather.txt");
		System.out.println(p32.equals(p33));                         // false
		System.out.println(p32.normalize().equals(p33.normalize())); // true

		//Retrieving the File System Path with toRealPath() While working with theoretical paths is useful, 
		//sometimes you want to verify the path actually exists within the file system.
		
		//public Path toRealPath(LinkOption… options) throws IOException
		
		//this method will normalize(), then toAbsolutePath(), then check if the path exists
		//if path does not exist an IOException will be thrown.
		
		System.out.println(Paths.get(".").toRealPath());

		//Path methods Summary.
		
		//static Path of(String, String…)  
		//Path subpath(int, int)  
		
		//Path getRoot()  
		//Path getParent()  
		//Path getName(int)  
		//Path getFileName()  
		
		//File toFile()  
		//URI toURI()
		
		//boolean isAbsolute()  
		//String toString()  
		//int getNameCount()  
		
		//Path resolve(Path)  
		//Path relativize()  
		//Path normalize()  

		//Path toAbsolutePath()  
		//Path toRealPath(LinkOption…) throws IOException


		//Operating on Files and Directories using Files helper class
		
		//public static boolean exists(Path path, LinkOption… options) 
		
		var b34 = Files.exists(Paths.get("C:/tmp/tmp.txt"));
		System.out.println("Path " + (b34 ? "Exists" : "Missing"));
		 
		var b35 = Files.exists(Paths.get("/ostrich"));
		System.out.println("Path " + (b35 ? "Exists" : "Missing"));

		//public static boolean isSameFile​(Path path, Path path2)   throws IOException

		System.out.println(Files.isSameFile(Path.of("C:/tmp/tmp.txt"),  Path.of("C:/tmp/tmp.txt")));
		System.out.println(Files.isSameFile(Path.of("C:/tmp/"),  Path.of("C:/tmp/")));
		System.out.println(Files.isSameFile(Path.of("C:/tmp/tmp.txt"),  Path.of("C:/tmp/zoo2.log")));
		//System.out.println(Files.isSameFile(Path.of("C:/tmp/tmp.txt"),  Path.of("C:/tmp/zoo.log")));   //java.nio.file.NoSuchFileException

		//Making Directories with createDirectory() and createDirectories()
		
		//public static Path createDirectory​(Path dir, FileAttribute<?>… attrs) throws IOException
		//public static Path createDirectories​(Path dir, FileAttribute<?>… attrs) throws IOException

		//The createDirectory() will create a directory and throw an exception if it already exists 
		//or the paths leading up to the directory do not exist.
		
		//The createDirectories() works just like the java.io.File method mkdirs(), in that it creates 
		//the target directory along with any nonexistent parent directories leading up to the path. 
		//If all of the directories already exist, createDirectories() will simply complete without doing anything.

		//Files.createDirectory(Path.of("C:/tmp/tmp3"));			//java.nio.file.FileAlreadyExistsException: C:\tmp\tmp3
		Files.createDirectories(Path.of("C:/tmp/tmp3/tmp4"));

		//Copying Files with copy
		
		//public static Path copy​(Path source, Path target, CopyOption… options) throws IOException

		//Files.copy(Paths.get("C:/tmp/tmp.txt"), Paths.get("C:/tmp/tmp3/tmp.txt"));	//java.nio.file.FileAlreadyExistsException
		//Files.copy(Paths.get("C:/tmp/tmp3"), Paths.get("C:/tmp/tmp3_copy"));			//java.nio.file.FileAlreadyExistsException
		 
		//When directories are copied, the copy is shallow.

		Files.copy(Paths.get("C:/tmp/tmp.txt"), Paths.get("C:/tmp/tmp3/tmp.txt"),StandardCopyOption.REPLACE_EXISTING);
		Files.copy(Paths.get("C:/tmp/tmp3"), Paths.get("C:/tmp/tmp3_copy"),StandardCopyOption.REPLACE_EXISTING);
		
		//Copying Files with I/O Streams The Files class includes two copy() methods that operate with I/O streams. 
		
		//public static long copy​(InputStream in, Path target, CopyOption… options) throws IOException
		//public static long copy​(Path source, OutputStream out) throws IOException

		Files.copy(Paths.get("C:/tmp/tmp.txt"), System.out);
		
		//Moving or Renaming Paths with move() 
				
		//public static Path move​(Path source, Path target, CopyOption… options) throws IOException 
				
		//Files.move(Path.of("c:\\zoo"), Path.of("c:\\zoo-new"));
        //Files.move(Path.of("c:\\user\\addresses.txt"), Path.of("c:\\zoo-new\\addresses2.txt"));

		//Like copy(), move() requires REPLACE_EXISTING to overwrite the target if it exists, 
		//else it will throw an exception. Also like copy(), move() will not put a file in a directory 
		//if the source is a file and the target is a directory. Instead, 
		//it will create a new file with the name of the directory.

		//Deleting a File with delete() and deleteIfExists() 

		//public static void delete​(Path path) throws IOException
		//public static boolean deleteIfExists​(Path path) throws IOException
		
		//Files.delete(Paths.get("/vulture/feathers.txt"));
		//Files.deleteIfExists(Paths.get("/pigeon"));

		//Reading and Writing Data with newBufferedReader() and newBufferedWriter() 
		
		//public static BufferedReader newBufferedReader​(Path path) throws IOException				 
		//public static BufferedWriter newBufferedWriter​(Path path, OpenOption… options) throws IOException
				
		var p36 = Path.of("C:/tmp/tmp.txt");
		try (var reader = Files.newBufferedReader(p36)) {
		   String currentLine = null;
		   while((currentLine = reader.readLine()) != null)
		      System.out.println(currentLine);
		}

		var list = new ArrayList<String>();
		list.add("Smokey");
		list.add("Yogix");
		 
		var p37 = Path.of("C:/tmp/tmp_w.txt");
		try (var writer = Files.newBufferedWriter(p37)) {
		   for(var line : list) {
		      writer.write(line);
		      writer.newLine();
		   }
		}

		//Reading a File with readAllLines()
		
		//public static List<String> readAllLines​(Path path) throws IOException 
		
		var p38 = Path.of("C:/tmp/tmp_w.txt");
		final List<String> lines = Files.readAllLines(p38);
		lines.forEach(System.out::println);

		//Files class methods: All of these methods except exists() declare IOException.
		
		//boolean exists(Path, LinkOption…)  
		//boolean isSameFile(Path, Path)  
		//Path createDirectory(Path, FileAttribute<?>…)  
		//Path createDirectories(Path, FileAttribute<?>…)  
		//Path copy(Path, Path, CopyOption…)  
		//long copy(InputStream, Path, CopyOption…)  
		//long copy(Path, OutputStream)
		//Path move(Path, Path, CopyOption…)  
		//void delete(Path)
		//boolean deleteIfExists(Path)  
		//BufferedReader newBufferedReader(Path)  
		//BufferedWriter newBufferedWriter(Path, OpenOption…)  
		//List<String> readAllLines(Path)  

		//Managing File Attributes
		
		//Reading Common Attributes with isDirectory(), isSymbolicLink(), and isRegularFile() 
		
		//public static boolean isDirectory​(Path path, LinkOption… options)		 
		//public static boolean isSymbolicLink​(Path path)
		//public static boolean isRegularFile​(Path path, LinkOption… options)

		//System.out.print(Files.isDirectory(Paths.get("/canine/fur.jpg")));
		//System.out.print(Files.isSymbolicLink(Paths.get("/canine/coyote")));
		//System.out.print(Files.isRegularFile(Paths.get("/canine/types.txt")));

		//Checking File Accessibility with isHidden(), isReadable(), isWritable(), and isExecutable() 

		//public static boolean isHidden​(Path path) throws IOException 
		//public static boolean isReadable(Path path)		 
		//public static boolean isWritable(Path path)		 
		//public static boolean isExecutable(Path path)

		//System.out.print(Files.isHidden(Paths.get("/walrus.txt")));
		//System.out.print(Files.isReadable(Paths.get("/seal/baby.png")));
		//System.out.print(Files.isWritable(Paths.get("dolphin.txt")));
		//System.out.print(Files.isExecutable(Paths.get("whale.png")));

		//Reading File Size with size()
		
		//public static long size​(Path path) throws IOException

		System.out.print(Files.size(Paths.get("C:/tmp/tmp.txt")));

		//Checking for File Changes with getLastModifiedTime()

		//public static FileTime getLastModifiedTime​(Path path, LinkOption… options) throws IOException

		final Path p39 = Paths.get("C:/tmp/tmp.txt");
		System.out.println(Files.getLastModifiedTime(p39).toMillis());

		//Improving Attribute Access
		
		//The Attributes and view types
		
		//Attributes interface 		View interface 			Description 
		//-----------------------------------------------------------------------------------------
		//BasicFileAttributes  		BasicFileAttributeView  Basic set of attributes supported by all file systems
		//DosFileAttributes  		DosFileAttributeView  	Basic set of attributes along with those supported by DOS/Windows‐based systems 
		//PosixFileAttributes  		PosixFileAttributeView  Basic set of attributes along with those supported by POSIX systems, such as UNIX, Linux, Mac, etc.

		//Retrieving Attributes with readAttributes()

		//public static <A extends BasicFileAttributes> A readAttributes(Path path, Class<A> type, LinkOption… options) throws IOException

		var p40 = Paths.get("C:/tmp/tmp.txt");
		getBasicFileAttributes(p40);

		//Modifying Attributes with getFileAttributeView() 

		//public static <V extends FileAttributeView> V getFileAttributeView​(Path path, Class<V> type, LinkOption… options)

		// Read file attributes
		var p41 = Paths.get("C:/tmp/tmp.txt");
		BasicFileAttributeView view = Files.getFileAttributeView(p41, BasicFileAttributeView.class);
		BasicFileAttributes attributes = view.readAttributes();
		 
		// Modify file last modified time
		FileTime lastModifiedTime = FileTime.fromMillis(attributes.lastModifiedTime().toMillis() + 10_000);
		view.setTimes(lastModifiedTime, null, null); 

		// BasicFileAttributeView instance method
		//public void setTimes(FileTime lastModifiedTime, FileTime lastAccessTime, FileTime createTime);
		
		getBasicFileAttributes(p41);
		
		//Applying Functional Programming
		
		//Listing Directory Contents
		
		//public static Stream<Path> list​(Path dir) throws IOException

		try (Stream<Path> s = Files.list(Path.of("C:/tmp"))) {
			   s.forEach(System.out::println);
		}

		//Traversing a Directory Tree
		
		//Search Strategy: Depth-First & Breadth-first
		
		//NIO.2 Streams API methods use depth‐first searching with a depth limit, which can be optionally changed.

		//Walking a Directory with walk()
		
		//public static Stream<Path> walk​(Path start, FileVisitOption… options) throws IOException
		//public static Stream<Path> walk​(Path start, int maxDepth, FileVisitOption… options) throws IOException
		
		var fileSize1 = getPathSize(Path.of("C:/tmp/"), 10)/1000000.0;
		System.out.println("File Size = " + fileSize1);
		System.out.format("Total Size: %.2f megabytes\n", fileSize1);

		var fileSize2 = getPathSize(Path.of("C:/tmp/"), 2)/1000000.0;
		System.out.println("File Size = " + fileSize2);
		System.out.format("Total Size: %.2f megabytes\n", fileSize2);
		
		//Searching a Directory with find()
		
		//public static Stream<Path> find​(Path start, int maxDepth,  BiPredicate<Path,​BasicFileAttributes> matcher,  FileVisitOption… options) throws IOException

		getFileWithSize(Path.of("C:/tmp/"), 10, ".txt", 100);
		
		//Reading a File with lines()
		
		//public static Stream<String> lines​(Path path) throws IOException
		
		//comparing with readAllLines(), using lines the contents of the file are read and processed lazily, 
		//which means that only a small portion of the file is stored in memory at any given time.

		Path p42 = Paths.get("C:/tmp/tmp.txt");
		try (var s = Files.lines(p42)) {
		   s.forEach(System.out::println);
		}

		try (var s = Files.lines(p42)) {
			s.filter(f -> f.startsWith("Thank"))
				.map(f -> f.substring(5))
			    .forEach(System.out::println);			
		}

		//Comparison of java.io.File and NIO.2 methods 
		
		//Legacy I/O File method 		NIO.2 method 
		//file.delete()  				Files.delete(path)  
		//file.exists()  				Files.exists(path)  
		//file.getAbsolutePath()  		path.toAbsolutePath()  
		//file.getName()  				path.getFileName()  
		//file.getParent()  			path.getParent()  
		//file.isDirectory()  			Files.isDirectory(path)  
		//file.isFile()  				Files.isRegularFile(path)  
		//file.lastModified()  			Files.getLastModifiedTime(path)  
		//file.length()  				Files.size(path)  
		//file.listFiles()  			Files.list(path)  
		//file.mkdir()  				Files.createDirectory(path)  
		//file.mkdirs()  				Files.createDirectories(path)  
		//file.renameTo(otherFile)  	Files.move(path,otherPath)




    }
    
    public static void printPathInformation(Path path) {
 	   System.out.println("Filename is: " + path.getFileName());
 	   System.out.println("   Root is: " + path.getRoot());
 	   Path currentParent = path;
 	   while((currentParent = currentParent.getParent()) != null) {
 	      System.out.println("   Current parent is: " + currentParent);
 	   }
	}
 
    public static void getBasicFileAttributes(Path path) throws IOException {
		BasicFileAttributes data = Files.readAttributes(path, BasicFileAttributes.class);
		
		System.out.println("Is a directory? " + data.isDirectory());
		System.out.println("Is a regular file? " + data.isRegularFile());
		System.out.println("Is a symbolic link? " + data.isSymbolicLink());
		System.out.println("Size (in bytes): " + data.size());
		System.out.println("Last modified: " + data.lastModifiedTime());
	}

    public static long getPathSize(Path source, int depthLimit) throws IOException {
        try (var s = Files.walk(source, depthLimit)) {
           return s.parallel()
                 .filter(p -> !Files.isDirectory(p))
                 .mapToLong(Chapter20_FileNIO2::getSize)
                 .sum();
        }
     }

    public static void getFileWithSize(Path path, int depthLimit, String ext, long minSize) throws IOException {

    	try (var s = Files.find(path, depthLimit, 
    	      (p, a) -> a.isRegularFile() && p.toString().endsWith(ext) && a.size() > minSize)) 
    	{
    	   s.forEach(System.out::println);
    	}


    }
    
    private static long getSize(Path p) {
        try {
           return  Files.size(p);
        } catch (IOException e) {
           // Handle exception
        }
        return 0L;
     }
     	 
    private static void testTest() {
		
		out.println("--------------testTest-----------");
		
		Bank.test();

/*
		1.	a(e)
		2.	c(f)
		3.	e(ce)
		4.	c
		5.	bc
		6.	c
		7.	f
		8.	bdfg(bdg)
		9.	c
		10.	bc
		11.	d
		12.	ce(ace)
		13.	a
		14.	c
		15.	e
		16.	df(def)
		17.	b
		18.	a(ad)
		19.	d
		20.	a(b)
		21.	b
		22.	b(be)


*/
		
		
	}
	
}
