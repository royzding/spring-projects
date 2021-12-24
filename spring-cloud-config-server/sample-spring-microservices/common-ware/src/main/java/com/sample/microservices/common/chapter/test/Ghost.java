package com.sample.microservices.common.chapter.test;

public class Ghost {
	
    public static void boo() {
       System.out.println("Not scared");
    }
    
    protected final class Spirit {
       public void boo() {
          System.out.println("Booo!!!");
       }
    }
    
    public static void main(String... haunt) {
       //var g = new Ghost().new Spirit() {}; //do not compile
       new Ghost().boo();
       Ghost.boo();
       
    }
 }

