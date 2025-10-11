package org.example.handwritten;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

public class CustomLongGenerator extends Generator<CustomLong> {
	public CustomLongGenerator() {
		super(CustomLong.class); // Register the type of objects that we can create
	}

	@Override
	public CustomLong generate(SourceOfRandomness random, GenerationStatus __ignore__) {
		long number = random.nextLong();
		return new CustomLong(number);
	}
}
