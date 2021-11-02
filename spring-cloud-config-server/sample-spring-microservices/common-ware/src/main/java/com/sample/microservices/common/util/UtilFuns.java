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
	
	public static <E> PageLayout<E> getPage(int pageNum, int pageSize, List<E> list) {
		
		
		Page<E> varPage = new PageImpl<>(list);
		
		final PageLayout<E> page = new PageLayout<>();
		
		page.setTotalElements(varPage.getTotalElements());
		page.setTotalPages(varPage.getTotalPages());
		
		int firstElementNum = pageSize * pageNum - pageSize;
		page.setFirstElementNum(firstElementNum < 0 ? 0 : firstElementNum + 1);		
		page.setData(list);
				
		return page;
		
	}
	
}
