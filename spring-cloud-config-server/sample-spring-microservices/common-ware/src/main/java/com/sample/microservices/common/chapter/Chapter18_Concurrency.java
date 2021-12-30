package com.sample.microservices.common.chapter;

import static java.lang.System.out;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chapter18_Concurrency {


	public static void main(String[] args) throws Exception {

		testTemplet();
		TestConcurrency();
			
	}

	private static void testTemplet() {
		
	}
	
    static int counter = 0;

    private static void TestConcurrency() throws Exception {
		
		out.println("--------------TestConcurrency-----------");

		//one process could have multiple threads
		//threads share the common resource such as shared memory.
		
		//threads have system thread, which is created by JVM and run in the background of the application.
		//and user-defined thread, which is created by application developer.
		
		//thread scheduler
		//context switch
		//thread priority
		
		//Defining a Task with Runnable
		
		/*
			//Runnable interface: 
			
			@FunctionalInterface 
			public interface Runnable {
			   void run();
			}
		 */
		
		Runnable sloth = () -> System.out.println("Hello World");
		Runnable snake = () -> {int i=10; i++;};
		Runnable beaver = () -> {return;};
		Runnable coyote = () -> {};

		//Runnable capybara = () -> "";                 // DOES NOT COMPILE
		//Runnable Hippopotamus = () -> 5;              // DOES NOT COMPILE
		//Runnable emu = () -> {return new Object();};  // DOES NOT COMPILE

		//Defining the task that a Thread instance will execute can be done two ways in Java: 
		
		//1.	Provide a Runnable object or lambda expression to the Thread constructor. 
		//2.	Create a class that extends Thread and overrides the run() method.

		(new Thread(new PrintData())).start();
		(new ReadInventoryThread()).start();

		//Creating Threads with the Concurrency API
		
		//interfaces
		//ExecutorService -> Executor
		
		//Executors factory class that can be used to create instances of the ExecutorService object.

		ExecutorService service1 = null;
	      
		Runnable task1 = () ->  System.out.println("Printing zoo inventory");
	    Runnable task2 = () -> {for(int i = 0; i < 3; i++) {System.out.println("Printing record: "+i);}};
	    
	    try {
	         service1 = Executors.newSingleThreadExecutor();
	         System.out.println("begin");
	         service1.execute(task1);
	         service1.execute(task2);
	         service1.execute(task1);
	         System.out.println("end");
	    } finally {
	         if(service1 != null) service1.shutdown();
	    }

	    //Shutting Down a Thread Executor
	    
	    //The shutdown process for a thread executor involves first rejecting any new tasks submitted 
	    //to the thread executor while continuing to execute any previously submitted tasks. 
	    //During this time, calling isShutdown() will return true, while isTerminated() will return false. 
	    //If a new task is submitted to the thread executor while it is shutting down, 
	    //a RejectedExecutionException will be thrown. Once all active tasks have been completed, 
	    //isShutdown() and isTerminated() will both return true.

	    //Submitting Tasks
	    
	    //ExecutorService methods
	    
		//Method name 												Description 
	    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	    //void execute(Runnable command)  							Executes a Runnable task at some point in the future 
	    //Future<?> submit(Runnable task)  							Executes a Runnable task at some point in the future and returns a Future representing the task
	    //<T> Future<T> submit(Callable<T> task)  					Executes a Callable task at some point in the future and returns a Future representing the pending results of the task 
	    //<T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException  
	    //															Executes the given tasks and waits for all tasks to complete. Returns a List of Future instances, in the same order they were in the original collection 
	    //<T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException  
	    //															Executes the given tasks and waits for at least one to complete. Returns a Future instance for a complete task and cancels any unfinished tasks

	    //Waiting for Results
	    
	    //Future<?> future = service1.submit(() -> System.out.println("Hello"));

	    //Future methods
	    //Method 											name Description 
	    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	    //boolean isDone()  								Returns true if the task was completed, threw an exception, or was cancelled 
	    //boolean isCancelled()  							Returns true if the task was cancelled before it completed normally 
	    //boolean cancel(boolean mayInterruptIfRunning)  	Attempts to cancel execution of the task and returns true if it was successfully cancelled or false if it could not be cancelled or is complete 
	    //V get()  											Retrieves the result of a task, waiting endlessly if it is not yet available
	    //V get(long timeout, TimeUnit unit)  				Retrieves the result of a task, waiting the specified amount of time. If the result is not ready by the time the timeout is reached, a checked TimeoutException will be thrown.

	    ExecutorService service2 = null;
	      
	    try {
	         service2 = Executors.newSingleThreadExecutor();
	         Future<?> result1 = service2.submit(() -> {
	            for(int i = 0; i < 10; i++) counter++;
	         });
	         
	         Object obj = result1.get(10, TimeUnit.SECONDS);
	         System.out.println("Reached! result = " + obj);
	         
	    } catch (TimeoutException e) {
	    	  System.out.println("Not reached in time");     
	    } catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} finally {
	    	
         if(service2 != null) service2.shutdown();
      
	    }  
	    
	    //As Future<V> is a generic interface, the type V is determined by the return type of the Runnable method. 
	    //Since the return type of Runnable.run() is void, the get() method always returns null 
	    //when working with Runnable expressions.

	    //TimeUnit values
	    
	    //Enum name 				Description
	    //-----------------------------------------------------------------------------
	    //TimeUnit.NANOSECONDS  	Time in one‐billionth of a second (1/1,000,000,000) 
	    //TimeUnit.MICROSECONDS  	Time in one‐millionth of a second (1/1,000,000) 
	    //TimeUnit.MILLISECONDS  	Time in one‐thousandth of a second (1/1,000) 
	    //TimeUnit.SECONDS  		Time in seconds 
	    //TimeUnit.MINUTES  		Time in minutes 
	    //TimeUnit.HOURS  			Time in hours 
	    //TimeUnit.DAYS  			Time in days
	    
	    //Introducing Callable
	    
	    /*
			//Callabble interface
			 
			@FunctionalInterface 
			public interface Callable<V> {
			   V call() throws Exception;
			}
        */

	    ExecutorService service3 = null;
	    
	    try {
	         service3 = Executors.newSingleThreadExecutor();
	         Future<Integer> result2 = service3.submit(() -> 30 + 11);
	         System.out.println(result2.get());   // 41
        	    
	    } catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} finally {
	         if(service3 != null) service3.shutdown();
	    }

	    //Unlike Runnable, in which the get() methods always return null, the get() methods for Callable 
	    //on a Future instance return the matching generic type (which could also be a null value).

	    //Waiting for all Tasks to finish
	    
	    ExecutorService service4 = null;
	    
	    try {
	       service4 = Executors.newSingleThreadExecutor();
	       // Add tasks to the thread executor
	    } finally {
	       if(service4 != null) service4.shutdown();
	    }
	    
	    if(service4 != null) {
		    
	    	try {
				service4.awaitTermination(1, TimeUnit.MINUTES);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	     
	       // Check whether all tasks are finished
	       if(service4.isTerminated()) System.out.println("Finished!");
	       else System.out.println("At least one task is still running");
	    }

	    //submitting Task Collections
	    
	    ExecutorService service5 =  Executors.newSingleThreadExecutor();
	    System.out.println("begin");
	    Callable<String> task3 = () -> "result";
	    List<Future<String>> list = new ArrayList<>();
		try {
			list = service5.invokeAll(List.of(task3, task3, task3));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
		       if(service5 != null) service5.shutdown();
		}
	    
	    for (Future<String> future : list) {
	       try {
			System.out.println(future.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
	    }
	    
	    System.out.println("end");

	    ExecutorService service6 = Executors.newSingleThreadExecutor();
	    
	    try {
	    	
		    System.out.println("begin");
		    Callable<String> task4 = () -> "result";
		    String data = service6.invokeAny(List.of(task4, task4, task4));
		    System.out.println(data);
		    System.out.println("end");
	    	
	    } finally {
		       if(service6 != null) service6.shutdown();
		}

	    //Scheduling Tasks

	    //ScheduledExecutorService, which is a subinterface of ExecutorService, can be used for scheduling tasks.

	    ScheduledExecutorService service7 = Executors.newSingleThreadScheduledExecutor();
	    
	    //ScheduledExecutorService methods
	    //Method Name 																				Description
	    //---------------------------------------------------------------------------------------------------------------------------------
	    //schedule(Callable<V> callable, long delay, TimeUnit unit)  								
	    //			Creates and executes a Callable task after the given delay 
	    //schedule(Runnable command, long delay, TimeUnit unit)  									
	    //			Creates and executes a Runnable task after the given delay 
	    //scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)  	
	    //			Creates and executes a Runnable task after the given initial delay, 
	    //			creating a new task every period value that passes 
	    //scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit)  	
	    //			Creates and executes a Runnable task after the given initial delay and subsequently 
	    //			with the given delay between the termination of one execution and the commencement of the next


	    ScheduledExecutorService service8  = Executors.newSingleThreadScheduledExecutor();
	    
	    try {
		    Runnable task81 = () -> System.out.println("Hello Zoo at " + LocalTime.now());	 
		    Callable<String> task82 = () -> { System.out.println("Monkey at " + LocalTime.now()); return "Monkey";};
		    System.out.println(LocalTime.now());
		    //ScheduledFuture<?> r81 = service8.schedule(task81, 10, TimeUnit.SECONDS);	    
		    //ScheduledFuture<?> r82 = service8.schedule(task82, 1,  TimeUnit.MINUTES);
	    	
	    } finally {
	    	if(service8 != null) service8.shutdown();
	    }
	 
	    //Increasing Concurrency with Thread Pools
	    
	    //Method 												Description 
	    //-----------------------------------------------------------------------------------------------------------
	    //ExecutorService newSingleThreadExecutor()  
	    //		Creates a single‐threaded executor that uses a single worker thread operating off an unbounded queue. 
	    //		Results are processed sequentially in the order in which they are submitted. 
	    //ScheduledExecutorService newSingleThreadScheduledExecutor()  
	    //		Creates a single‐threaded executor that can schedule commands to run
	    //		after a given delay or to execute periodically
	    //ExecutorService newCachedThreadPool()  
	    //		Creates a thread pool that creates new threads as needed but will reuse previously 
	    //		constructed threads when they are available 
	    //ExecutorService newFixedThreadPool(int)  
	    //		Creates a thread pool that reuses a fixed number of threads operating off a shared unbounded queue 
	    //ScheduledExecutorService newScheduledThreadPool(int)  
	    //		Creates a thread pool that can schedule commands to run after a given delay or to execute periodically

	    //Writing Thread-Safe Code
	    
	    //Thread‐safety is the property of an object that guarantees safe execution by multiple threads at the same time.

	    //Protecting Data with Atomic Classes
	    
	    //Class Name 			Description 
	    //--------------------------------------------------------------------------
	    //AtomicBoolean  		A boolean value that may be updated atomically 
	    //AtomicInteger  		An int value that may be updated atomically 
	    //AtomicLong  			A long value that may be updated atomically
	    
	    //Common atomic methods
	    
	    //Method name 			Description 
	    //-------------------------------------------------------------------------------------------------
	    //get()  				Retrieves the current value 
	    //set()  				Sets the given value, equivalent to the assignment = operator 
	    //getAndSet()  			Atomically sets the new value and returns the old value 
	    //incrementAndGet()  	For numeric classes, atomic pre‐increment operation equivalent to ++value
	    //getAndIncrement()  	For numeric classes, atomic post‐increment operation equivalent to value++ 
	    //decrementAndGet()  	For numeric classes, atomic pre‐decrement operation equivalent to ‐‐value 
	    //getAndDecrement()  	For numeric classes, atomic post‐decrement operation equivalent to value‐‐

	    SheepManager.test();
	    
	    //Improving Access with Synchronized Blocks
	    
	    //Understanding the Locak Framework
	    //Instead of synchronizing on any Object, though, we can “lock” only on an object 
	    //that implements the Lock interface.
	    
	    //Applying a ReentrantLock, 
	    //which is a simple monitor class that implements the Lock interface and supports mutual exclusion.

	    // Implementation #1 with a synchronized block
	    Object object = new Object();
	    synchronized(object) {	       
	    	// Protected code
	    }

	    // Implementation #2 with a Lock
	    Lock lock1 = new ReentrantLock();
	    try {
	       lock1.lock();
	       // Protected code
	    } finally {
	       lock1.unlock();
	    }

	    //make sure that you only release a lock that you actually have. 
	    //If you attempt to release a lock that you do not have, you will get an exception at runtime.

	    Lock lock2 = new ReentrantLock();
	    //lock2.unlock();  // IllegalMonitorStateException
	    
	    //Lock methods
	    
	    //Method 								Description
	    //-------------------------------------------------------------------------------------------------
	    //void lock()  							Requests a lock and blocks until lock is acquired 
	    //void unlock()  						Releases a lock 
	    //boolean tryLock()  					Requests a lock and returns immediately. Returns a boolean indicating whether the lock was successfully acquired 
	    //boolean tryLock(long,TimeUnit)  		Requests a lock and blocks up to the specified time until lock is required. Returns a boolean indicating whether the lock was successfully acquired

	    Lock lock3 = new ReentrantLock();
	    
	    //threads do not waits to acquire the lock.
	    if(lock3.tryLock()) {
	       try {
	          System.out.println("Lock obtained, entering protected code");
	       } finally {
	          lock3.unlock();
	       }
	    } else {
	    	System.out.println("Unable to acquire lock, doing something else");
	    }


	    //threads waits up to 10 seconds to acquire the lock.
	    Lock lock4 = new ReentrantLock();
	    
	    if(lock4.tryLock(10,TimeUnit.SECONDS)) {
	       try {
	          System.out.println("Lock obtained, entering protected code");
	       } finally {
	          lock4.unlock();
	       }
	    } else {
	       System.out.println("Unable to acquire lock, doing something else");
	    }

	    //Lock Frameword
	    
	    //1.	Ability to request a lock without blocking 
	    //2.	Ability to request a lock while blocking for a specified amount of time 
	    //3.	A lock can be created with a fairness property, 
	    //		in which the lock is granted to threads in the order it was requested.

	    //ReentrantReadWriteLock
	    
	    //Orchestrating Tasks with a CyclicBarrier

	    //LionPenManager.test();
	    LionPenManager_CB.test();
	    
	    //Using Concurrent Collections
	    
	    //When two threads try to modify the same nonconcurrent collection, 
	    //the JVM may throw a ConcurrentModificationException at runtime. 
	    //In fact, it can happen with a single thread.
	    
	    //Concurrent collection classes
	    
	    //Class name 				Java Collections Framework interfaces 	ordered? 	Sorted? 	Blocking? 
	    //----------------------------------------------------------------------------------------------------
	    //ConcurrentHashMap  		ConcurrentMap  							No 			No 			No 
	    //ConcurrentLinkedQueue 	Queue  									Yes 		No 			No 
	    //ConcurrentSkipListMap  	ConcurrentMap SortedMap NavigableMap  	Yes 		Yes 		No 
	    //ConcurrentSkipListSet  	SortedSet NavigableSet  				Yes 		Yes 		No 
	    //CopyOnWriteArrayList  	List  									Yes 		No 			No 
	    //CopyOnWriteArraySet  		Set  									No 			No 			No 
	    //LinkedBlockingQueue  		BlockingQueue  							Yes 		No 			Yes

	    Map<String,Integer> cMap1 = new ConcurrentHashMap<>();
	    cMap1.put("zebra", 52);
	    cMap1.put("elephant", 10);
	    System.out.println(cMap1.get("elephant"));  // 10
	     
	    Queue<Integer> cQueue1 = new ConcurrentLinkedQueue<>();
	    cQueue1.offer(31);
	    System.out.println(cQueue1.peek());  // 31
	    System.out.println(cQueue1.poll());  // 31

	    //Understanding SkipList Collections  (Tree)
	    //The SkipList classes, ConcurrentSkipListSet and ConcurrentSkipListMap, 
	    //are concurrent versions of their sorted counterparts, TreeSet and TreeMap, respectively.

	    Set<String> gardenAnimals = new ConcurrentSkipListSet<>();
	    gardenAnimals.add("rabbit");
	    gardenAnimals.add("gopher");
	    System.out.println(gardenAnimals.stream().collect(Collectors.joining(",")));  // gopher,rabbit
	     
	    Map<String, String> rainForestAnimalDiet = new ConcurrentSkipListMap<>();
	    rainForestAnimalDiet.put("koala", "bamboo");
	    rainForestAnimalDiet.entrySet()
	       .stream().forEach((e) -> System.out.println(e.getKey() + "-" + e.getValue())); // koala-bamboo

	    //Understanding CopyOnWrite Collections 
	    //CopyOnWriteArrayList and CopyOnWriteArraySet, that behave a little differently than the other concurrent.
	    //These classes copy all of their elements to a new underlying structure anytime an element is added, modified, 
	    //or removed from the collection. By a modified element, we mean that the reference in the collection is changed. 
	    //Modifying the actual contents of objects within the collection will not cause a new structure to be allocated.

	    //Although the data is copied to a new underlying structure, our reference to the Collection object does not change. 
	    //This is particularly useful in multithreaded environments that need to iterate the collection. 
	    //Any iterator established prior to a modification will not see the changes, 
	    //but instead it will iterate over the original elements prior to the modification.

	    List<Integer> favNumbers = new CopyOnWriteArrayList<>(List.of(4,3,42));
	    		
	    for(var n: favNumbers) {	    		   
	    	System.out.print(n + " ");
	    	favNumbers.add(9);
	    }
	    
	    System.out.println();
	    System.out.println("Size: " + favNumbers.size());
	    
	    favNumbers.forEach(x->System.out.print(x + " "));

	    //4 3 42
	    //Size: 6
	    //4 3 42 9 9 9 
	    
	    //The CopyOnWriteArraySet is used just like a HashSet and 
	    //has similar properties as the CopyOnWriteArrayList class.

	    Set<Character> favLetters = new CopyOnWriteArraySet<>(List.of('a','t'));
	    		
	    for(char c: favLetters) {	    		   
	    	System.out.print(c+" ");	    		   
	    	favLetters.add('s');	    		
	    }
	    		
	    System.out.println();	    		
	    System.out.println("Size: "+ favLetters.size());
	    
	    favLetters.forEach(x->System.out.print(x + " "));
	    		 
	    //a t 
	    //Size: 3
	    //a t s

	    //The CopyOnWrite classes can use a lot of memory, since a new collection structure 
	    //needs be allocated anytime the collection is modified. They are commonly 
	    //used in multithreaded environment situations where reads are far more common than writes.

	    //understanding Blocking Queues
	    
	    //BlockingQueue waiting methods
	    
	    //Method name 										Description 
	    //-------------------------------------------------------------------------------------
	    //offer(E e, long timeout, TimeUnit unit)  			Adds an item to the queue, 
	    //		waiting the specified time and returning false if the time elapses before space is available 
	    //poll(long timeout, TimeUnit unit)  				Retrieves and removes an item from the queue, 
	    //		waiting the specified time and returning null if the time elapses before the item is available

	    try {
	    	   var blockingQueue = new LinkedBlockingQueue<Integer>();
	    	   blockingQueue.offer(39);
	    	   blockingQueue.offer(3, 4, TimeUnit.SECONDS);
	    	   System.out.println(blockingQueue.poll());
	    	   System.out.println(blockingQueue.poll(10, TimeUnit.MILLISECONDS));
	    	} catch (InterruptedException e) {
	    	   // Handle interruption
	    	}

	    //39
	    //3
	    
	    //Obtaining Synchronized Collections from Nonconcurrent collection objects
	    
	    //Synchronized Collections methods
	    //---------------------------------------------
	    //synchronizedCollection(Collection<T> c)  
	    //synchronizedList(List<T> list)  
	    //synchronizedMap(Map<K,V> m)  
	    //synchronizedNavigableMap(NavigableMap<K,V> m)  
	    //synchronizedNavigableSet(NavigableSet<T> s)  
	    //synchronizedSet(Set<T> s)
	    //synchronizedSortedMap(SortedMap<K,V> m)  
	    //synchronizedSortedSet(SortedSet<T> s)

	    //Identitying Threading Problems
	    
	    //Liveness is the ability of an application to be able to execute in a timely manner.
	    //Liveness has three type problems: deadlock, starvation and livelock
	    
	    //Deadlock occurs when two or more threads are blocked forever, each waiting on the other.
	    
	    //Starvation occurs when a single thread is perpetually denied access to a shared resource 
	    //or lock. The thread is still active, but it is unable to complete its work as a result 
	    //of other threads constantly taking the resource that they are trying to access.
	    
	    //Livelock occurs when two or more threads are conceptually blocked forever, 
	    //although they are each still active and trying to complete their task. 
	    //Livelock is a special case of resource starvation in which two or more threads 
	    //actively try to acquire a set of locks, are unable to do so, and restart part of the process.

	    //Managing Race Conditions
	    
	    //A race condition is an undesirable result that occurs when two tasks, which should be completed sequentially,
	    //are completed at the same time.

	    //Working with Parallel Streams
	    
	    //Calling parallel() on an Existing Stream
	    
	    Stream<Integer> s1 = List.of(1,2).stream();
	    Stream<Integer> s2 = s1.parallel(); 
	    
	    //Be aware that parallel() is an intermediate operation that operates on the original stream. 
	    //For example, applying a terminal operation to s2 also makes s1 unavailable for further use.

	    //Calling parallelStream() on a Collection Object

	    Stream<Integer> s3 = List.of(1,2).parallelStream();

	    //Performing a Parallel Decomposition
	    
	    //A parallel decomposition is the process of taking a task, breaking it up into smaller pieces 
	    //that can be performed concurrently, and then reassembling the results. 
	    //The more concurrent a decomposition, the greater the performance improvement of using parallel streams.

	    long start1 = System.currentTimeMillis();
	    List.of(1,2,3,4,5)
	       .stream()
	       .map(w -> doWork(w))
	       .forEach(s -> System.out.print(s + " "));
	     
	    System.out.println();
	    var timeTaken1 = (System.currentTimeMillis()-start1)/1000;
	    System.out.println("Time: "+timeTaken1+" seconds");

	    //1 2 3 4 5 
	    //Time: 25 seconds
	    
	    long start2 = System.currentTimeMillis();
	    List.of(1,2,3,4,5)
	       .parallelStream()
	       .map(w -> doWork(w))
	       .forEach(s -> System.out.print(s + " "));
	     
	    System.out.println();
	    var timeTaken2 = (System.currentTimeMillis()-start2)/1000;
	    System.out.println("Time: "+timeTaken2+" seconds"); 
	    
	    //With a parallel stream, the map() and forEach() operations are applied concurrently. 
	    //The following is sample output: 
	    //3 2 1 5 4 
	    //Time: 5 seconds

	    //combining results with reduce()
	    
	    //<U> U reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator<U> combiner);
	
		System.out.println(List.of('w', 'o', 'l', 'f')
	    		   .parallelStream()                                   
	    		   .reduce("", 
	    		      (sp1,c) -> sp1 + c, 
	    		      (sp2,sp3) -> sp2 + sp3));  // wolf
		
		//Combining Results with collect()
		
		//<R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner);

		Stream<String> stream = Stream.of("w", "o", "l", "f").parallel();
		SortedSet<String> set = stream.collect(ConcurrentSkipListSet::new,
		   Set::add,
		   Set::addAll);
		System.out.println(set);  // [f, l, o, w]

		//Performing a Parallel Reduction on a Collector

		//Requirements for Parallel Reduction with collect()
		//1.	The stream is parallel. 
		//2.	The parameter of the collect() operation has the Characteristics.CONCURRENT characteristic. 
		//3.	Either the stream is unordered or the collector has the characteristic Characteristics.UNORDERED.

		Stream<String> ohMy1 = Stream.of("lions","tigers","bears").parallel();
		ConcurrentMap<Integer, String> map1 = ohMy1
		   .collect(Collectors.toConcurrentMap(String::length,
		      k -> k,
		      (sm1, sm2) -> sm1 + "," + sm2));
		System.out.println(map1); 				// {5=lions,bears, 6=tigers}
		System.out.println(map1.getClass());  	// java.util.concurrent.ConcurrentHashMap

		var ohMy2 = Stream.of("lions","tigers","bears").parallel();
		ConcurrentMap<Integer, List<String>> map2 = ohMy2.collect(
		   Collectors.groupingByConcurrent(String::length));
		System.out.println(map2); 				// {5=[lions, bears], 6=[tigers]}



    }
	
    private void incrementAndReportx() {
    	   synchronized(this) {
    	      System.out.print("dymmy");
    	   }
    }
    
    private synchronized void incrementAndReporty() {
    	   System.out.print("dymmy");
    }

    private static int doWork(int input) {
    	   
    	try {
    	      Thread.sleep(5000);    	   
    	} catch (InterruptedException e) {}
    	   
    	return input;    	
    }
    	
	private static void testTest() {
		
//		out.println("--------------testTest-----------");

/*
		1.	

*/
		
	}
	
}


class PrintData implements Runnable {
	
	   @Override public void run() { // Overrides method in Runnable
	      for(int i = 0; i < 3; i++)
	         System.out.println("Printing record: "+i);
	   
	   }
}

class ReadInventoryThread extends Thread {
	
	   @Override public void run() { // Overrides method in Thread
	      System.out.println("Printing zoo inventory");
	   }
	   
	   public static void main(String[] args) {
	      (new ReadInventoryThread()).start();
	   }
}


class SheepManager {
	
   private AtomicInteger sheepCount = new AtomicInteger(0);

   private void incrementAndReport() {	
	   System.out.print(sheepCount.incrementAndGet()+" ");
   }
	   
   public static void test() {
	      
	   ExecutorService service = null;
	   try {
		   
		   service = Executors.newFixedThreadPool(20);
	       SheepManager manager = new SheepManager();
	       for(int i = 0; i < 10; i++)
	             service.submit(() -> manager.incrementAndReport());
	       } finally {
	          if(service != null) service.shutdown();
	       }
    }
	 
}

class LionPenManager {
	
	   private void removeLions() {System.out.println("Removing lions");}
	   
	   private void cleanPen() {System.out.println("Cleaning the pen");}
	   
	   private void addLions() {System.out.println("Adding lions");}
	   
	   public void performTask() {
	      removeLions();
	      cleanPen();
	      addLions();
	   }
	   
	   public static void test() {
	      ExecutorService service = null;
	      
	      try {
	         service = Executors.newFixedThreadPool(4);
	         var manager = new LionPenManager();
	         for (int i = 0; i < 4; i++) {
		            service.submit(() -> manager.performTask());	        	 
	         }
	         
	      } finally {
	         if (service != null) service.shutdown();
	      }
	   }	
}

	
class LionPenManager_CB {
	
	   private void removeLions() {System.out.println("Removing lions");}
	   
	   private void cleanPen() {System.out.println("Cleaning the pen");}
	   
	   private void addLions() {System.out.println("Adding lions");}
	   
	   public void performTask(CyclicBarrier c1, CyclicBarrier c2) {
	      try {
	         removeLions();
	         c1.await();
	         cleanPen();
	         c2.await();
	         addLions();
	      } catch (BrokenBarrierException e) {
	         // Handle checked exceptions here
	      } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	   
	   public static void test() {

		   ExecutorService service = null;
		      try {
		         service = Executors.newFixedThreadPool(6);
		         var manager = new LionPenManager_CB();
		         var c1 = new CyclicBarrier(5);
		         var c2 = new CyclicBarrier(5, 
		            () -> System.out.println("*** Pen Cleaned!"));
		         for (int i = 0; i < 5; i++)
		            service.submit(() -> manager.performTask(c1, c2));
		      } finally {
		         if (service != null) service.shutdown();
		      }
	   }
}
	
	
	
	
	
	
	
	
	

