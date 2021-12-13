package com.sample.microservices.common.chapter;

import static com.sample.microservices.common.chapter.test.Rope.*;

import com.sample.microservices.common.chapter.test.*;

   public class RopeSwing {
      private static Rope rope1 = new Rope();
      private static Rope rope2 = new Rope();
      
      {
         System.out.println(rope1.length);
      }
      
      public static void main(String[] args) {
          rope1.length = 2;
          rope2.length = 8;
          System.out.println(rope1.length);
       }
    }



 