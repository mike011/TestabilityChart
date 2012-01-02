package ca.charland.bgm.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * Tests for Bubble.
 * 
 * @author mcharland
 */
public class BubbleTest {

	/**
	 * Test the constructor.
	 */
	@Test
	public void test() {
		assertNotNull(new Bubble(null, 0, 0, null, null));
	}

	/**
	 * Test get the X value.
	 */
	@Test
	public void testGetDate() {
		// Setup
		Bubble object = new Bubble(null, 0, 0, null, null);

		// Exercise
		float date = object.getDate();

		// Verify
		assertEquals(0.0, date, 0.1);
	}

	/** Test getting the size. */
	@Test
	public void testGetSize() {

		// Setup
		Bubble object = new Bubble(null, 0, 0, null, null);

		// Exercise
		float date = object.getSize();

		// Verify
		assertEquals(0.0, date, 0.1);
	}

	/** Test getting the y value. */
	@Test
	public void testGetCoverage() {
		// Setup
		Bubble object = new Bubble(null, 1, 0, null, null);

		// Exercise
		float date = object.getCoverage();

		// Verify
		assertEquals(100.0, date, 0.1);
	}

	/** Test getting the y string value. */
	@Test
	public void testGetCoverageString() {
		// Setup
		Bubble object = new Bubble(null, 1, 0, null, null);

		// Exercise
		String date = object.getCoverageString();

		// Verify
		assertEquals("100", date);
	}

	/**
	 * Test normalise size for the minimum amount.
	 */
	@Test
	public void testNormaliseSizeMin() {
		// Setup
		long size = 0;
		Bubble object = new Bubble(null, 0, size, null, null);

		long min = 0;
		long max = 120;

		// Exercise
		object.normaliseSize(min, max);

		// Verify
		float date = object.getSize();
		assertEquals(1.0, date, 0.1);
	}

	/**
	 * Test normalise size for the maximum amount.
	 */
	@Test
	public void testNormaliseSizeMax() {
		// Setup
		long size = 120;
		Bubble object = new Bubble(null, 0, size, null, null);

		long min = 0;
		long max = 120;

		// Exercise
		object.normaliseSize(min, max);

		// Verify
		float date = object.getSize();
		assertEquals(50, date, 0.1);
	}

	/**
	 * Test get change.
	 */
	@Test
	public void testGetChange() {
		// Setup
		Bubble object = new Bubble(null, 0, 120, "98a", null);

		// Exercise
		String change = object.getChange();

		// Verify
		assertEquals("Change is wrong", "98a", change);
	}
	
	/**
	 * Test a different link.
	 */
	@Test
	public void testLink() {
		// Setup
		Bubble object = new Bubble(null, 0, 120, "a", "http://www.nba.com/");
		
		// Exercise
		String link = object.getLink();

		// Verify
		assertEquals("Link is wrong", "http://www.nba.com/a", link);
	}

	/**
	 * Test get link, but the base URL is null.
	 */
	@Test
	public void testGetLinkNull() {
		// Setup
		Bubble object = new Bubble(null, 0, 120, "98a", null);

		// Exercise
		String link = object.getLink();

		// Verify
		assertNull(link);
	}

	/**
	 * Test to string.
	 */
	@Test
	public void testToString() {
		// Setup
		Bubble bubble = new Bubble(null, 0, 120, "123423", null);

		// Exercise
		String out = bubble.toString();

		// Verify
		StringBuffer bubblesData = new StringBuffer();
		bubblesData.append("Date:").append("0.00");
		bubblesData.append(", Coverage:").append("0");
		bubblesData.append(", Size:").append("120.00");
		bubblesData.append(", Change:").append(bubble.getChange());
		assertEquals(bubblesData.toString(), out);
	}
}
