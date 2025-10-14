package org.example.ptrie;

import edu.berkeley.cs.jqf.fuzz.Fuzz;
import edu.berkeley.cs.jqf.fuzz.JQF;
import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;
import org.junit.runner.RunWith;

import java.util.*;
import java.util.stream.Collectors;

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



	@Fuzz
	public void printTrie(Map<String, Integer> map, String key) {
		// Key should exist in map
		assumeTrue(map.containsKey(key));   // the test is invalid if this predicate is not true

		// Create new trie with input `map`
		Trie<String, Integer> trie = new PatriciaTrie<>(map);

//		Weird output...
		System.out.println("Printing all keys:");
		System.out.println(new ArrayList<>(map.keySet()));
		System.out.println(new ArrayList<>(trie.keySet()));
		System.out.println(BREAK);

		System.out.println("Key to find, with length = "+key.length()+":");
		System.out.println(key);
		System.out.println(toSpacedHexes(key));
		System.out.println(BREAK);

		System.out.println("Printing map's keys in hex:");
		var mapKeys = sortAndFormatKeys(map.keySet());
		for (var hex : mapKeys) System.out.println(hex);
		System.out.println(BREAK);

		System.out.println("Printing trie's keys in hex:");
		var trieKeys = sortAndFormatKeys(trie.keySet());
		for (var hex : trieKeys) System.out.println(hex);
		System.out.println(BREAK);

		// The key should exist in the trie as well
		assertTrue(trie.containsKey(key));  // fails when map = {"x": 1, "x\0": 2} and key = "x"
	}


	private static final String BREAK = "=".repeat(32);
	private String toSpacedHexes(String string) {
		return string.chars()
				.mapToObj(Integer::toString)
				.collect(Collectors.joining(" "));
	}

	private List<String> sortAndFormatKeys(Collection<String> keys) {
		ArrayList<String> keyList = new ArrayList<>(keys);
		Collections.sort(keyList);

		List<String> hexList = keyList.stream()
				.map(this::toSpacedHexes)
				.collect(Collectors.toList());
		return hexList;
	}
}
