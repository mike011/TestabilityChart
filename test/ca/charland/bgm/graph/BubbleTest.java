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
	 * Test normalize date max.
	 */
	@Test
	public void testNormalizeDateMax() {
		Bubble object = new Bubble(200, 0, 0);

		long min = 0;
		long max = 200;
		object.normalizeX(min, max);

		float date = object.getX();
		Assert.assertEquals(100000, date, 0.1);
	}
	
	/**
	 * Test normalize date min.
	 */
	@Test
	public void testNormalizeDateMin() {
		Bubble object = new Bubble(0, 0, 0);

		long min = 0;
		long max = 200;
		object.normalizeX(min, max);

		float date = object.getX();
		Assert.assertEquals(0, date, 0.1);
	}
	
	/**
	 * Test normalize date mid.
	 */
	@Test
	public void testNormalizeDateMid() {
		Bubble object = new Bubble(60, 0, 0);

		long min = 0;
		long max = 120;
		object.normalizeX(min, max);

		float date = object.getX();
		Assert.assertEquals(50000, date, 0.1);
	}
	
	/**
	 * Test normalize size.
	 */
	@Test
	public void testNormalizeSize() {
		Bubble object = new Bubble(0, 0, 60);

		long min = 0;
		long max = 120;
		object.normalizeSize(min, max);

		float date = object.getSize();
		Assert.assertEquals(26, date, 0.1);
	}
}
