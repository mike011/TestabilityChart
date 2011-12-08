package ca.charland.bgm.graph;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Tests for Bubble.
 */
public class BubbleTest {

	/**
	 * Test.
	 */
	@Test
	public void test() {
		assertNotNull(new Bubble(null, 0, 0));
	}

	/**
	 * Test get the X value.
	 */
	@Test
	public void testGetDate() {

		// Setup
		Bubble object = new Bubble(null, 0, 0);

		// Exercise
		float date = object.getDate();

		// Verify
		Assert.assertEquals(0.0, date, 0.1);
	}

	/** Test getting the size. */
	@Test
	public void testGetSize() {

		// Setup
		Bubble object = new Bubble(null, 0, 0);

		// Exercise
		float date = object.getSize();

		// Verify
		Assert.assertEquals(0.0, date, 0.1);
	}

	/** Test getting the y value. */
	@Test
	public void testGetCoverage() {

		// Setup
		Bubble object = new Bubble(null, 1, 0);

		// Exercise
		float date = object.getCoverage();

		// Verify
		Assert.assertEquals(100.0, date, 0.1);
	}

	/** Test getting the y string value. */
	@Test
	public void testGetCoverageString() {

		// Setup
		Bubble object = new Bubble(null, 1, 0);

		// Exercise
		String date = object.getCoverageString();

		// Verify
		Assert.assertEquals("100", date);
	}

	/**
	 * Test normalise date max.
	 */
	@Test
	public void testNormaliseDateMax() {

		// Setup
		Date date = new Date();
		Bubble object = new Bubble(date, 0, 0);

		long min = 0;
		long max = 200;

		// Exercise
		object.normaliseX(min, max);

		// Verify
		float actual = object.getDate();
		Assert.assertEquals(date.getTime(), actual, 1E5);
	}

	/**
	 * Test normalise date min.
	 */
	@Test
	public void testNormaliseDateMin() {

		// Setup
		Bubble object = new Bubble(null, 0, 0);

		long min = 0;
		long max = 200;

		// Exercise
		object.normaliseX(min, max);

		// Verify
		float date = object.getDate();
		Assert.assertEquals(0, date, 0.1);
	}

	/**
	 * Test normalise date mid.
	 */
	@Test
	public void testNormaliseDateMid() {

		// Setup
		Date date = new Date();
		Bubble object = new Bubble(date, 0, 0);
		long min = 0;
		long max = 120;

		// Exercise
		object.normaliseX(min, max);

		// Verify
		float actual = object.getDate();
		Assert.assertEquals(date.getTime(), actual, 1E10);
	}

	/**
	 * Test normalise size for the minimum amount.
	 */
	@Test
	public void testNormaliseSizeMin() {
		// Setup
		long size = 0;
		Bubble object = new Bubble(null, 0, size);

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
		Bubble object = new Bubble(null, 0, size);

		long min = 0;
		long max = 120;

		// Exercise
		object.normaliseSize(min, max);

		// Verify
		float date = object.getSize();
		Assert.assertEquals(50, date, 0.1);
	}

	@Test
	public void testToString() {

		// Setup
		Bubble object = new Bubble(null, 0, 120);

		// Exercise
		String out = object.toString();

		// Verify
		StringBuffer bubblesData = new StringBuffer();
		bubblesData.append("{\"Date\":\"").append("0.00").append('"');
		bubblesData.append(", \"Coverage\":\"").append("0").append('"');
		bubblesData.append(", \"Size\":\"").append("120.00").append('"');
		bubblesData.append("}");
		Assert.assertEquals(bubblesData.toString(), out);

	}
}
