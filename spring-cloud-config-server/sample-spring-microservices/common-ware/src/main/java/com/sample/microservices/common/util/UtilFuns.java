package com.sample.microservices.common.util;

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
	
}
