package ca.charland.bgm.change;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

/**
 * Tests for Change.
 * 
 * @author mcharland
 */
public class ChangeTest {

	/**
	 * Test constructor.
	 */
	@Test
	public void test() {
		assertNotNull(new Change(null, null, null, null, null));
	}

	/**
	 * Test get coverage none.
	 */
	@Test
	public void testGetCoverageNone() {
		// Setup
		List<Line> lines = new ArrayList<Line>();
		Change change = new Change(null, null, null, null, lines);
		
		// Exercise
		double diff = change.getCoverage(null);
		
		// Verify
		assertEquals(0, diff, 0.1);
	}

	/**
	 * Test get coverage zero.
	 */
	@Test
	public void testGetCoverageZero() {
		// Setup
		List<Line> lines = new ArrayList<Line>();
		lines.add(new Line("3", "3", "dog.java"));
		lines.add(new Line("2", "55", "dog.java"));
		Change change = new Change(null, null, null, null, lines);

		List<String> types = new ArrayList<String>();
		types.add("java");
		
		// Exercise
		double diff = change.getCoverage(types);
		
		// Verify
		assertEquals(0, diff, 0.1);
	}

	/**
	 * Test get coverage half.
	 */
	@Test
	public void testGetCoverageHalf() {
		// Setup
		List<Line> lines = new ArrayList<Line>();
		lines.add(new Line("3", "3", "dog.java"));
		lines.add(new Line("3", "3", "test.java"));
		Change change = new Change(null, null, null, null, lines);

		List<String> types = new ArrayList<String>();
		types.add("java");
		
		// Exercise
		double diff = change.getCoverage(types);
		
		// Verify
		assertEquals(0.5, diff, 0.1);
	}

	/**
	 * Test get coverage one.
	 */
	@Test
	public void testGetCoverageOne() {
		// Setup
		List<Line> lines = new ArrayList<Line>();
		lines.add(new Line("3", "3", "test"));
		lines.add(new Line("3", "3", "test"));
		Change change = new Change(null, null, null, null, lines);

		List<String> types = new ArrayList<String>();
		types.add("java");
		
		// Exercise
		double diff = change.getCoverage(types);
		
		// Verify
		assertEquals(1, diff, 0.1);
	}

	/**
	 * Test get lines covered.
	 */
	@Test
	public void testGetLinesCovered() {
		// Setup
		List<Line> lines = new ArrayList<Line>();
		lines.add(new Line("3", "3", "test"));
		lines.add(new Line("3", "3", "test"));
		Change change = new Change(null, null, null, null, lines);

		List<String> types = new ArrayList<String>();
		types.add("java");
		
		// Exercise
		int covered = change.getLinesCovered(types);
		
		// Verify
		assertEquals(12, covered, 0.1);
	}

	/**
	 * Test get date.
	 */
	@Test
	public void testGetDate() {
		// Setup
		Change change = new Change(null, null, "Date:     Wed Nov 30 13:51:05 2011 -0500", null, null);
		
		// Exercise
		Date date = change.getDate();
		
		// Verify
		assertNotNull(date);
	}
	
	/**
	 * Test get date, but will throw a ParseException.
	 */
	@Test
	public void testGetDateParseException() {
		// Setup
		Change change = new Change(null, null, "Date:     UU", null, null);
		
		// Exercise
		Date date = change.getDate();
		
		// Verify
		assertNull(date);
	}

	/**
	 * Test get author.
	 */
	@Test
	public void testGetAuthor() {
		// Setup
		List<Line> lines = new ArrayList<Line>();
		lines.add(new Line("3", "3", "test"));

		String authorString = "Author: defected <defected@defected-P5K-EPU.(none)>";
		Change change = new Change(null, authorString, null, null, lines);

		// Exercise
		String author = change.getAuthor();

		// Verify
		assertEquals("defected", author);
	}

	/**
	 * Test if the change is valid.
	 */
	@Test
	public void testIsValidNo() {

		// Setup
		List<Line> lines = new ArrayList<Line>();

		String expectedAuthor = "frank";
		Change change = new Change(null, expectedAuthor, null, null, lines);

		// Exercise
		boolean valid = change.isValid();

		// Verify
		assertFalse(valid);
	}

	/**
	 * Test if the change is valid.
	 */
	@Test
	public void testIsValidYes() {

		// Setup
		List<Line> lines = new ArrayList<Line>();
		lines.add(new Line("3", "3", "test"));

		String expectedAuthor = "frank";
		Change change = new Change(null, expectedAuthor, null, null, lines);

		// Exercise
		boolean valid = change.isValid();

		// Verify
		assertTrue(valid);
	}

	/**
	 * Test equals.
	 */
	@Test
	public void testEquals() {

		// Setup
		Change c = new Change("commit ax", null, null, null, null);

		// Exercise
		boolean equals = c.equals(c);

		// Verify
		assertTrue("not equal", equals);
	}

	/**
	 * Test get commit.
	 */
	@Test
	public void testGetCommit() {

		// Setup
		Change c = new Change("commit bob", null, null, null, null);

		// Exercise
		String commit = c.getCommit();

		// Verify
		assertEquals("bob", commit);
	}

	/**
	 * Test to string.
	 */
	@Test
	public void testToString() {

		// Setup
		Change c = new Change("commit bob", null, null, null, null);

		// Exercise
		String commit = c.toString();

		// Verify
		assertEquals("bob", commit);
	}
}
