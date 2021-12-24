package com.sample.microservices.common.chapter;

import static java.lang.System.out;

public class Chapter11_Modules {


	public static void main(String[] args) {

		testTemplet();
		TestModules();
		
		testTest();
		
		Test t = new Test();
		SubTest st = new SubTest();
		
		Test ct = st;
		
		
	}

	private static void testTemplet() {
		
	}
	
	private static void TestModules() {
		
		out.println("--------------TestModules-----------");

		
		
	}
	
	private static void testTest() {
		
		out.println("--------------testTest-----------");

/*
		1.	b
		2.	d
		3.	b
		4.	f(g)
		5.	afg
		6.	b(bc)
		7.	dg
		8.	abcd(abd)
		9.	a(ab)
		10.	b
		11.	bef(bdef)
		12.	c(b)
		13.	e
		14.	a
		15.	(bd)
		16.	bc
		17.	e
		18.	ac
		19.	bc
		20.	e(be)
		21.	g(c)
		

*/
		
	}
	
}


