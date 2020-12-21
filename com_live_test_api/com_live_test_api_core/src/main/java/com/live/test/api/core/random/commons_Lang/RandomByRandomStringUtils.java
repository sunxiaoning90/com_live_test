package com.live.test.api.core.random.commons_Lang;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomByRandomStringUtils {

	 public static void main(String[] args) {
	        // Creates a 64 chars length random string of number.
	        String result = RandomStringUtils.random(64, false, true);
	        System.out.println("random = " + result);

	        // Creates a 64 chars length of random alphabetic string.
	        result = RandomStringUtils.randomAlphabetic(64);
	        System.out.println("random = " + result);

	        // Creates a 32 chars length of random ascii string.
	        result = RandomStringUtils.randomAscii(32);
	        System.out.println("random = " + result);

	        // Creates a 32 chars length of string from the defined array of
	        // characters including numeric and alphabetic characters.
	        result = RandomStringUtils.random(32, 0, 20, true, true, "qw32rfHIJk9iQ8Ud7h0X".toCharArray());
	        System.out.println("random = " + result);

	    }
}
