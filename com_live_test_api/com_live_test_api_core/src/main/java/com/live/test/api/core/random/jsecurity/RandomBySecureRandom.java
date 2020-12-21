package com.live.test.api.core.random.jsecurity;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class RandomBySecureRandom {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		SecureRandom random1 = SecureRandom.getInstance("SHA1PRNG");
		SecureRandom random2 = SecureRandom.getInstance("SHA1PRNG");

		for (int i = 0; i < 5; i++) {
		    System.out.println(random1.nextInt() + " != " + random2.nextInt());
		}
	}
}
