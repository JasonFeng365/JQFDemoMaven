package org.example.handwritten;

/**
 *
 */

import com.pholser.junit.quickcheck.From;
import edu.berkeley.cs.jqf.fuzz.Fuzz;
import edu.berkeley.cs.jqf.fuzz.JQF;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

@RunWith(JQF.class)
public class CustomClass {
	@Fuzz
	public void testCustomClassInput(@From(CustomLongGenerator.class) CustomLong customLong) {
		assumeTrue(-100 <= customLong.value && customLong.value <= 100);
		assertTrue(customLong.value*2 == customLong.twiceValue);
		assertTrue(customLong.value == customLong.twiceValue/2);
	}
}
