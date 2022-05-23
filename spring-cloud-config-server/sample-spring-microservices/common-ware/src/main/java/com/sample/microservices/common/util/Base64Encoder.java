package com.sample.microservices.common.util;

import java.util.Base64;

public class Base64Encoder {
	
	public static void main(String[] args) {
		
		String testStr = "xyz1234567sss";
		
		System.out.println(testStr);
		System.out.println(encodeToBase64Str(testStr));
		System.out.println(encodeStrToBase64Str(testStr));
		System.out.println(decodeBase64StrToStr(encodeToBase64Str(testStr)));		
		
	}
	
	private Base64Encoder() {

	}
	
	public static String encodeToBase64Str(String encodeMe) {
		return Base64.getEncoder().encodeToString(encodeMe.getBytes());
	}

	public static String encodeStrToBase64Str(String encodeMe) {
		return new String(Base64.getEncoder().encode(encodeMe.getBytes()));
	}

	public static String decodeBase64StrToStr(String decodeMe) {
		return new String(Base64.getDecoder().decode(decodeMe.getBytes()));
	}
}
