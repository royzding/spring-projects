package com.sample.microservices.rabbitmq.util;

import java.util.Base64;

public class Base64Encoder {
	
	public static String encodeStrToBase64Str(String encodeMe) {
		return new String(Base64.getEncoder().encode(encodeMe.getBytes()));
	}

	public static String decodeBase64StrToStr(String decodeMe) {
		return new String(Base64.getDecoder().decode(decodeMe.getBytes()));
	}
}
