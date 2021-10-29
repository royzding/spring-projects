package com.sample.microservices.common.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;

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
}
