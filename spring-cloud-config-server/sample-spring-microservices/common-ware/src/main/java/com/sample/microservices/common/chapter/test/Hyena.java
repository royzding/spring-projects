package com.sample.microservices.common.chapter.test;

import java.util.function.Predicate;
public class Hyena {
   private int age = 1;
   public static void main(String[] args) {
      var p = new Hyena();
      double height = 10;
      int age = 1;
      testLaugh(p, var->p.age<=10);
      age = 2;
   }
   static void testLaugh(Hyena panda, Predicate<Hyena> joke) {
      var r = joke.test(panda) ? "hahaha" : "silence";
      System.out.print(r);
   }
}

interface BigCat {
	abstract String getName();
	//static int hunt() { getName(); return 5; }
	default void climb() { rest(); }
	//private void roar() { getName();  climb(); hunt(); }
	//private static boolean sneak() { roar(); return true; }
	private int rest() { return 2; };
}

