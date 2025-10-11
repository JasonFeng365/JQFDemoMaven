package org.example.handwritten;

import com.pholser.junit.quickcheck.From;
import edu.berkeley.cs.jqf.fuzz.Fuzz;
import edu.berkeley.cs.jqf.fuzz.JQF;
import org.junit.runner.RunWith;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeTrue;

@RunWith(JQF.class)
public class TestRandomString {
//	From Contest Administration, Notifications, and Information System backend
//	https://jasonfeng365.github.io/canis/
	/**
	 * Generates a random number of specified length.
	 * Example: if length is 3, output any number in range [100, 1000).
	 *
	 * @param length The number of digits in the generated number
	 * @return A random number with the specified number of digits
	 */
	private static int generateRandomNumber(int length, int seed) {
		int minVal = (int)Math.pow(10, length-1);
//		+1 makes the range inclusive, instead of exclusive
//		Bug in Jason's production-level code. Whoops! Glad nobody saw that
		Random random = new Random(seed);
		int rand = (int)(random.nextDouble()*minVal*9 + 1);
		return minVal + rand;
	}

	@Fuzz
//	Maybe just use any integer? But too slow, even with assumeTrue...
//	public void testRNG(int length) {
	public void testRNG(@From(RandomStringIntGen.class) int length, int seed) {
		assumeTrue(1 <= length && length <= 8);
		int randNum = generateRandomNumber(length, seed);
		String randNumString = Integer.toString(randNum);

		assertEquals(length, randNumString.length());
	}
}
