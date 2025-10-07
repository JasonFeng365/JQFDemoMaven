package org.example.ptrie;

import edu.berkeley.cs.jqf.fuzz.Fuzz;
import edu.berkeley.cs.jqf.fuzz.JQF;
import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;
import org.junit.runner.RunWith;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

/**
 * To run JQF on this class, run the following script in the project's root directory.
 * <code>
 *     mvn jqf:fuzz -f pom.xml -Dclass="org.example.ptrie.PatriciaTrieTest" -Dmethod="testMap2Trie" -DexitOnCrash
 * </code>
 * <br>
 * <br>
 * This demo is copied from the JQF repo, linked below.
 * <br>
 * <a href="https://github.com/rohanpadhye/JQF?tab=readme-ov-file#example">
 *     https://github.com/rohanpadhye/JQF?tab=readme-ov-file#example
 * </a>
 */

@RunWith(JQF.class)
public class PatriciaTrieTest {
	@Fuzz
	public void testMap2Trie(Map<String, Integer> map, String key) {
		// Key should exist in map
		assumeTrue(map.containsKey(key));   // the test is invalid if this predicate is not true

		// Create new trie with input `map`
		Trie trie = new PatriciaTrie(map);

		// The key should exist in the trie as well
		assertTrue(trie.containsKey(key));  // fails when map = {"x": 1, "x\0": 2} and key = "x"
	}

	// put(key, value) method
	// https://commons.apache.org/proper/commons-collections/apidocs/org/apache/commons/collections4/trie/AbstractPatriciaTrie.html#put(K,V)
	@Fuzz
	public void testPut(String key, int value) {
		PatriciaTrie trie = new PatriciaTrie();
		trie.put(key, value);

		assertTrue(trie.containsKey(key));
		assertEquals(Integer.valueOf(value), trie.get(key));
	}
}
