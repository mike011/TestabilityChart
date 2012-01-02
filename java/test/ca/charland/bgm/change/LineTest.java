package ca.charland.bgm.change;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Tests for Line.
 * 
 * @author mcharland
 */
public class LineTest {

	/**
	 * Test the constructor.
	 */
	@Test
	public void test() {
		// Setup
		String added = "1";
		String removed = "1";
		
		// Exercise & Verify
		assertNotNull(new Line(added, removed, "dank.java"));
	}

	/**
	 * Test get source difference.
	 */
	@Test
	public void testGetSourceDifference() {
		
		// Setup
		String added = "1";
		String removed = "1";
		Line line = new Line(added, removed, "dank.java");

		List<String> types = new ArrayList<String>();
		types.add("java");
		
		// Exercise
		int diff = line.getSourceDifference(types);

		// Verify
		assertEquals(2, diff);

		int test = line.getTestDiff(types);
		assertEquals(0, test);
	}

	/**
	 * Test get test difference.
	 */
	@Test
	public void testGetTestDifference() {
		// Setup
		String added = "1";
		String removed = "1";
		Line line = new Line(added, removed, "test/dank.java");
		List<String> types = new ArrayList<String>();
		types.add("java");

		// Exercise
		int diff = line.getSourceDifference(types);

		// Verify
		assertEquals(0, diff);

		int test = line.getTestDiff(types);
		assertEquals(2, test);
	}

	/**
	 * Test get difference added.
	 */
	@Test
	public void testGetDifferenceAdded() {
		// Setup
		String added = "-";
		String removed = "1";
		Line line = new Line(added, removed, "dank.java");
		List<String> types = new ArrayList<String>();
		types.add("java");
		
		// Exercise
		int src = line.getSourceDifference(types);

		// Verify
		assertEquals(1, src);

	}

	/**
	 * Test get difference removed.
	 */
	@Test
	public void testGetDifferenceRemoved() {
		// Setup
		String added = "1";
		String removed = "-";
		Line line = new Line(added, removed, "dank.java");
		List<String> types = new ArrayList<String>();
		types.add("java");

		// Exercise
		int diff = line.getSourceDifference(types);

		// Verify
		assertEquals(1, diff);

	}

	/**
	 * Test is valid yes.
	 */
	@Test
	public void testisValidYesLinesAddedAndRemoved() {
		// Setup
		String added = "1";
		String removed = "2";
		Line line = new Line(added, removed, "dank.java");
		List<String> types = new ArrayList<String>();
		types.add("java");

		// Exercise
		boolean valid = line.isValid(types);

		// Verify
		assertTrue(valid);
	}

	/**
	 * Test is valid yes, nothing added, but lines removed.
	 */
	@Test
	public void testisValidYesNothingAddedButLinesRemoved() {
		// Setup
		String added = "-";
		String removed = "2";
		Line line = new Line(added, removed, "dank.java");
		List<String> types = new ArrayList<String>();
		types.add("java");

		// Exercise
		boolean valid = line.isValid(types);

		// Verify
		assertTrue(valid);
	}

	/**
	 * Test is valid yes, lines added, nothing removed.
	 */
	@Test
	public void testisValidYesLinesAddedButNoneRemoved() {
		// Setup
		String added = "3";
		String removed = "-";
		Line line = new Line(added, removed, "dank.java");
		List<String> types = new ArrayList<String>();
		types.add("java");

		// Exercise
		boolean valid = line.isValid(types);

		// Verify
		assertTrue(valid);
	}

	/**
	 * Test is valid no, nothing covered.
	 */
	@Test
	public void testisValidNoNothingAddedorRmoved() {
		// Setup
		String added = "-";
		String removed = "-";
		Line line = new Line(added, removed, "dank.java");
		List<String> types = new ArrayList<String>();
		types.add("java");

		// Exercise
		boolean valid = line.isValid(types);

		// Verify
		assertFalse(valid);
	}

	/**
	 * Test is valid no, not a java file.
	 */
	@Test
	public void testisValidNoNotAJavaFile() {
		// Setup
		String added = "3";
		String removed = "5";
		Line line = new Line(added, removed, "dank.tt");
		List<String> types = new ArrayList<String>();
		types.add("java");

		// Exercise
		boolean valid = line.isValid(types);

		// Verify
		assertFalse(valid);
	}

	/**
	 * Test is valid no, not a valid file type.
	 */
	@Test
	public void testisValidNoNotaTTFile() {
		// Setup
		String added = "3";
		String removed = "5";
		Line line = new Line(added, removed, "dank.java");
		List<String> types = new ArrayList<String>();
		types.add("tt");

		// Exercise
		boolean valid = line.isValid(types);

		// Verify
		assertFalse(valid);
	}

	/**
	 * Test is source.
	 */
	@Test
	public void testIsSource() {
		// Setup
		String added = "6";
		String removed = "7";
		String file = "dank.java";
		Line line = new Line(added, removed, file);
		List<String> types = new ArrayList<String>();
		types.add("java");

		// Exercise
		boolean src = line.isSource(types);

		// Verify
		assertTrue(src);
	}

	/**
	 * Test is source no, with nothing added or removed.
	 */
	@Test
	public void testIsSourceNoNothingAddedOrRemoved() {
		// Setup
		String added = "-";
		String removed = "-";
		String file = "dank.tt";
		Line line = new Line(added, removed, file);
		List<String> types = new ArrayList<String>();
		types.add("tt");

		// Exercise
		boolean src = line.isSource(types);

		// Verify
		assertFalse(src);
	}

	/**
	 * Test is source no, the file is a test file.
	 */
	@Test
	public void testIsSrcNoTestFile() {
		// Setup
		String added = "6";
		String removed = "4";
		String file = "test/dank.tt";
		Line line = new Line(added, removed, file);
		List<String> types = new ArrayList<String>();
		types.add("tt");

		// Exercise
		boolean src = line.isSource(types);

		// Verify
		assertFalse(src);
	}
	
	/**
	 * Test to string.
	 */
	@Test
	public void testToString() {
		// Setup
		Line line = new Line(null, null, "file");
		
		// Exericse and Verify
		assertEquals("file", line.toString());
	}
}
