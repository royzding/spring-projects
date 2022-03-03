package com.sample.microservices.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.sample.microservices.common.pagination.PageLayout;

public class UtilFuns {

	public static void main(String[] args) {
		
		System.out.println(hasPredicate(Arrays.asList("Plus","Minus")).test("plusx"));
	}
	
	private UtilFuns() {
		throw new IllegalStateException("Utility class");
	}
	
	public static Predicate<String> hasPredicate(Collection<String> c) {
		return op->c.stream().anyMatch(op::equalsIgnoreCase);
		
	}
	
	public static void printLocalDateTime() {
		
        //Get current date time
        LocalDateTime now = LocalDateTime.now();

        System.out.println("Before : " + now);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String formatDateTime = now.format(formatter);

        System.out.println("After : " + formatDateTime);		
		
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSS");

        String formatDateTime2 = now.format(formatter2);

        System.out.println("After2 : " + formatDateTime2);		

        DateTimeFormatter formatter22 = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSSSSSSS");

        String formatDateTime22 = now.format(formatter22);

        System.out.println("After2 : " + formatDateTime22);		

        String formatDateTime3 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSSSSSS"));
        System.out.println("After3 : " + formatDateTime3);		

	}
	
}
