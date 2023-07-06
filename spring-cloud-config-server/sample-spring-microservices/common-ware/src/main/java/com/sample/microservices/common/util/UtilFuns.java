package com.sample.microservices.common.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.core.io.ResourceLoader;

import com.sample.microservices.common.enums.ActivityType;

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
	
	public static String getFileInfo(ResourceLoader resourceLoader, String filePath) throws IOException {
		
		Path path = resourceLoader.getResource(filePath).getFile().toPath();  //"classpath:fileInfo.json"
		
		return Files.readAllLines(path).stream().map(Object::toString).collect(Collectors.joining("\n"));
		
	}
	
	//Comma separated String to List
	static String str01 = "01,02,05,12,39";
	public static List<Integer> convertStrToList() {
		if(str01 == null) { return Collections.emptyList(); }
		return Stream.of(str01.split(",", -1)).map(e->Integer.valueOf(e.trim())).collect(Collectors.toList());
	}
	
	//int list to string
	static List<Integer> intList = List.of(1,2,5,12);
	public static String convertListToStr01() {
		return intList.stream().map(Object::toString).collect(Collectors.joining(","));
	}
	
	//int list to string
	public static String convertListToStr02() {
		return intList.stream().map(e->String.format("%02d", e)).collect(Collectors.joining(","));
	}
	
	//list to string
	static List<String> str03 = List.of("x1","x2","x5","x12");
	public static String convertListToStr03() {
		return String.join(",", str03);
	}

	//string to string through enum
	public static String convertStr01ToStr02() {
		return ActivityType.valueOf("GET").toString();
	}
	
}
