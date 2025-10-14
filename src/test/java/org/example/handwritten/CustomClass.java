package org.example.handwritten;

/**
 *
 */

import com.pholser.junit.quickcheck.From;
import edu.berkeley.cs.jqf.fuzz.Fuzz;
import edu.berkeley.cs.jqf.fuzz.JQF;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeTrue;

@RunWith(JQF.class)
public class CustomClass {

	@Fuzz

//	Usage of generator to create a CustomLong data type
//	public void testCustomClassInput(@From(CustomLongGenerator.class) CustomLong customLong) {

	public void testCustomClassInput(long n) {
		CustomLong customLong = new CustomLong(n);

		assumeTrue(-100 <= customLong.value && customLong.value <= 100);
		assertEquals(customLong.value * 2, customLong.twiceValue);
		assertEquals(customLong.value, customLong.twiceValue / 2);
	}
}
