package com.sample.microservices.common.chapter;

import java.util.ArrayList;
import java.util.List;

public class Chapter4_MakingDecisions {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void testForLoop() {
		
		List<Integer> myFavoriteNumbers = new ArrayList<>();
		  myFavoriteNumbers.add(10);
		  myFavoriteNumbers.add(14);
		  for (var a : myFavoriteNumbers) {
		     System.out.print(a + ", ");
		     break;
		  }

		  for (int b : myFavoriteNumbers) {
			     continue;
			     //System.out.print(b + ", ");
		  }

		  for (Object c : myFavoriteNumbers)
			     System.out.print(c + ", ");

	}
	
	public void testDoWhile() {
		
/*		
		do {
			
			int x = 0;
			
		}while(x>1);
*/		
	}

}
