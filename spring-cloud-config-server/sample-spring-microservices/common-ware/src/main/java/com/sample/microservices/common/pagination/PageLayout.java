package com.sample.microservices.common.pagination;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import lombok.Data;

@Data
public class PageLayout<E> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long totalElements;
	private int totalPages;
	private int firstElementNum;
	private List<E> data;


	public static <S, T> PageLayout<T> getPageFromList(List<T> list, int pageNum, int pageSize, List<S> sort, Direction direction) {
		
		//String[] sortStr = sort.stream().map(S::getValue).toArray(String[]::new);
		
		//Pageable pageable = PageRequest.of(pageNum-1, pageSize);
		
		int start = (pageNum-1)*pageSize;
		int end = pageNum*pageSize;
		
		start = (start<list.size()) ? start : list.size() -1;
		end = (end<list.size()) ? end : list.size();
		
		List<T> subList = list.subList(start, end);
		
		Pageable pageable = PageRequest.of(pageNum-1, pageSize);
		
		Page<T> page = new PageImpl<>(subList, pageable, list.size());
				
		return getPage(pageNum, pageSize, subList, page);
	}
	
	
	public static <U, V> PageLayout<U> getPage(int pageNum, int pageSize, List<U> list, Page<V> varPage) {
		
		final PageLayout<U> page = new PageLayout<>();
		
		page.setTotalElements(varPage.getTotalElements());
		page.setTotalPages(varPage.getTotalPages());
		
		int firstElementNum = pageSize * pageNum - pageSize;
		page.setFirstElementNum(firstElementNum < 0 ? 0 : firstElementNum + 1);		
		page.setData(list);
				
		return page;

	}

	
}
