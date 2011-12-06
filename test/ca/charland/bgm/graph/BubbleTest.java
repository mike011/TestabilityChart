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
	 * Test get date.
	 */
	@Test
	public void testGetDate() {
		Bubble object = new Bubble(0, 0, 0);

		float date = object.getX();

		Assert.assertEquals(0.0, date, 0.1);
	}

	/**
	 * Test normalise date max.
	 */
	@Test
	public void testNormaliseDateMax() {
		Bubble object = new Bubble(200, 0, 0);

		long min = 0;
		long max = 200;
		object.normaliseX(min, max);

		float date = object.getX();
		Assert.assertEquals(100, date, 0.1);
	}

	/**
	 * Test normalise date min.
	 */
	@Test
	public void testNormaliseDateMin() {
		Bubble object = new Bubble(0, 0, 0);

		long min = 0;
		long max = 200;
		object.normaliseX(min, max);

		float date = object.getX();
		Assert.assertEquals(0, date, 0.1);
	}

	/**
	 * Test normalise date mid.
	 */
	@Test
	public void testNormaliseDateMid() {
		Bubble object = new Bubble(60, 0, 0);

		long min = 0;
		long max = 120;
		object.normaliseX(min, max);

		float date = object.getX();
		Assert.assertEquals(50, date, 0.1);
	}

	/**
	 * Test normalise size for the minimum amount.
	 */
	@Test
	public void testNormaliseSizeMin() {
		long size = 0;
		Bubble object = new Bubble(0, 0, size);

		long min = 0;
		long max = 120;
		object.normaliseSize(min, max);

		float date = object.getSize();
		Assert.assertEquals(1.0, date, 0.1);
	}

	/**
	 * Test normalise size for the maximum amount.
	 */
	@Test
	public void testNormaliseSizeMax() {
		long size = 120;
		Bubble object = new Bubble(0, 0, size);

		long min = 0;
		long max = 120;
		object.normaliseSize(min, max);

		float date = object.getSize();
		Assert.assertEquals(50, date, 0.1);
	}
}
