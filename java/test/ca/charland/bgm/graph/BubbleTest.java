package ca.charland.bgm.graph;

import static org.junit.Assert.assertNotNull;
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
		assertNotNull(new Bubble(null, 0, 0, ""));
	}

	/**
	 * Test get the X value.
	 */
	@Test
	public void testGetDate() {

		// Setup
		Bubble object = new Bubble(null, 0, 0, "");

		// Exercise
		float date = object.getDate();

		// Verify
		Assert.assertEquals(0.0, date, 0.1);
	}

	/** Test getting the size. */
	@Test
	public void testGetSize() {

		// Setup
		Bubble object = new Bubble(null, 0, 0, "");

		// Exercise
		float date = object.getSize();

		// Verify
		Assert.assertEquals(0.0, date, 0.1);
	}

	/** Test getting the y value. */
	@Test
	public void testGetCoverage() {

		// Setup
		Bubble object = new Bubble(null, 1, 0, "");

		// Exercise
		float date = object.getCoverage();

		// Verify
		Assert.assertEquals(100.0, date, 0.1);
	}

	/** Test getting the y string value. */
	@Test
	public void testGetCoverageString() {

		// Setup
		Bubble object = new Bubble(null, 1, 0, "");

		// Exercise
		String date = object.getCoverageString();

		// Verify
		Assert.assertEquals("100", date);
	}

	/**
	 * Test normalise size for the minimum amount.
	 */
	@Test
	public void testNormaliseSizeMin() {
		// Setup
		long size = 0;
		Bubble object = new Bubble(null, 0, size, "");

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
		Bubble object = new Bubble(null, 0, size, "");

		long min = 0;
		long max = 120;

		// Exercise
		object.normaliseSize(min, max);

		// Verify
		float date = object.getSize();
		Assert.assertEquals(50, date, 0.1);
	}
	
	/**
	 * Test get link.
	 */
	@Test
	public void testGetLink() {

		// Setup
		Bubble object = new Bubble(null, 0, 120, "98a");

		// Exercise
		String link = object.getLink();

		// Verify
		Assert.assertEquals("Link is wrong", "https://github.com/mike011/TestabilityChart/commit/98a", link);
		
	}

	/**
	 * Test to string.
	 */
	@Test
	public void testToString() {

		// Setup
		Bubble bubble = new Bubble(null, 0, 120, "123423");

		// Exercise
		String out = bubble.toString();

		// Verify
		StringBuffer bubblesData = new StringBuffer();
		bubblesData.append("{\"Date\":\"").append("0.00").append('"');
		bubblesData.append(", \"Coverage\":\"").append("0").append('"');
		bubblesData.append(", \"Size\":\"").append("120.00").append('"');
		bubblesData.append(", \"Link\":\"").append(bubble.getLink()).append('"');
		bubblesData.append("}");
		Assert.assertEquals(bubblesData.toString(), out);

	}
}
