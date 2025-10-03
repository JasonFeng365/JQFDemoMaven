package org.example;

import edu.berkeley.cs.jqf.fuzz.Fuzz;
import edu.berkeley.cs.jqf.fuzz.JQF;
import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;
import org.junit.runner.RunWith;


import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import java.util.Map;

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

	public static void main(String[] args) {
		System.out.println(Map.class.getPackageName());
		System.out.println(PatriciaTrieTest.class.getPackageName());

		String filepath = "C:\\Users\\jason\\IdeaProjects\\JQFDemoMaven\\target\\fuzz-results\\PatriciaTrieTest\\testMap2Trie\\failures\\id_000000";

	}
}
