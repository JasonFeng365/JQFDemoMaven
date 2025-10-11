package org.example.handwritten;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

public class RandomStringIntGen extends Generator<Integer> {
	public RandomStringIntGen() {
		super(Integer.class);
	}

	@Override
	public Integer generate(SourceOfRandomness sourceOfRandomness, GenerationStatus generationStatus) {
		// inclusive, exclusive
		return sourceOfRandomness.nextInt(3, 9);
	}
}
