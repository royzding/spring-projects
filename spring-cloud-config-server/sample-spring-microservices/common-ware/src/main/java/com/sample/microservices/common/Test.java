package com.sample.microservices.common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.sample.microservices.common.util.UtilFuns;

public class Test {

	public static void main(String[] args) {

		var $_= 6_000;
		var $2= 6_000f;
		
		var var= 3_0_00.0;
		
		System.out.println("test " + $_ + " "+ $2 + " " + var);
		
		long reptile = 192301398193810323l;
		
		int x = 1;
		
		System.out.println(x=5);
		
		boolean a = true;
		
		boolean bx = (boolean)a;
		
		LocalDate ld = LocalDate.now();
		
		UtilFuns.printLocalDateTime();


	}

}
