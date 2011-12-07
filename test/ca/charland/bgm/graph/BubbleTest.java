package ca.charland.bgm.graph;

import static org.junit.Assert.assertNotNull;
import junit.framework.Assert;

import org.junit.Test;

import ca.charland.bgm.graph.Bubble;

/**
 * Tests for Bubble.
 */
public class BubbleTest {

	/**
	 * Test.
	 */
	@Test
	public void test() {
		assertNotNull(new Bubble(0, 0, 0));
	}

	/**
	 * Test get the X value.
	 */
	@Test
	public void testGetX() {

		// Setup
		Bubble object = new Bubble(0, 0, 0);

		// Exercise
		float date = object.getX();

		// Verify
		Assert.assertEquals(0.0, date, 0.1);
	}

	/** Test getting the size. */
	@Test
	public void testGetSize() {

		// Setup
		Bubble object = new Bubble(0, 0, 0);

		// Exercise
		float date = object.getSize();

		// Verify
		Assert.assertEquals(0.0, date, 0.1);
	}

	/** Test getting the y value. */
	@Test
	public void testGetY() {

		// Setup
		Bubble object = new Bubble(0, 1, 0);

		// Exercise
		float date = object.getY();

		// Verify
		Assert.assertEquals(100.0, date, 0.1);
	}

	/**
	 * Test normalise date max.
	 */
	@Test
	public void testNormaliseDateMax() {
		
		// Setup
		Bubble object = new Bubble(200, 0, 0);

		long min = 0;
		long max = 200;
		
		// Exercise
		object.normaliseX(min, max);

		// Verify
		float date = object.getX();
		Assert.assertEquals(100, date, 0.1);
	}

	/**
	 * Test normalise date min.
	 */
	@Test
	public void testNormaliseDateMin() {
		
		// Setup
		Bubble object = new Bubble(0, 0, 0);

		long min = 0;
		long max = 200;
		
		// Exercise
		object.normaliseX(min, max);

		// Verify
		float date = object.getX();
		Assert.assertEquals(0, date, 0.1);
	}

	/**
	 * Test normalise date mid.
	 */
	@Test
	public void testNormaliseDateMid() {
		
		// Setup
		Bubble object = new Bubble(60, 0, 0);
		long min = 0;
		long max = 120;
		
		// Exercise
		object.normaliseX(min, max);

		// Verify
		float date = object.getX();
		Assert.assertEquals(50, date, 0.1);
	}

	/**
	 * Test normalise size for the minimum amount.
	 */
	@Test
	public void testNormaliseSizeMin() {
		// Setup
		long size = 0;
		Bubble object = new Bubble(0, 0, size);

		long min = 0;
		long max = 120;
		
		// Exercise
		object.normaliseSize(min, max);

		// Verify
		float date = object.getSize();
		Assert.assertEquals(1.0, date, 0.1);
	}

	/**
	 * Test normalise size for the maximum amount.
	 */
	@Test
	public void testNormaliseSizeMax() {

		// Setup
		long size = 120;
		Bubble object = new Bubble(0, 0, size);

		long min = 0;
		long max = 120;

		// Exercise.
		object.normaliseSize(min, max);

		// Verify
		float date = object.getSize();
		Assert.assertEquals(50, date, 0.1);
	}
}
