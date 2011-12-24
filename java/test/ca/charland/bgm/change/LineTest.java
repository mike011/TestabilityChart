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
		String added = "1";
		String removed = "1";
		assertNotNull(new Line(added, removed, "dank.java"));
	}

	/**
	 * Test get src diff.
	 */
	@Test
	public void testGetSrcDiff() {
		String added = "1";
		String removed = "1";
		Line line = new Line(added, removed, "dank.java");

		List<String> types = new ArrayList<String>();
		types.add("java");
		int diff = line.getSrcDiff(types);

		assertEquals(2, diff);

		int test = line.getTestDiff(types);
		assertEquals(0, test);
	}

	/**
	 * Test get test diff.
	 */
	@Test
	public void testGetTestDiff() {
		String added = "1";
		String removed = "1";
		Line line = new Line(added, removed, "test/dank.java");
		List<String> types = new ArrayList<String>();
		types.add("java");

		int diff = line.getSrcDiff(types);

		assertEquals(0, diff);

		int test = line.getTestDiff(types);
		assertEquals(2, test);
	}

	/**
	 * Test get diff added.
	 */
	@Test
	public void testGetDiffAdded() {
		String added = "-";
		String removed = "1";
		Line line = new Line(added, removed, "dank.java");
		List<String> types = new ArrayList<String>();
		types.add("java");
		int src = line.getSrcDiff(types);

		assertEquals(1, src);

	}

	/**
	 * Test get diff removed.
	 */
	@Test
	public void testGetDiffRemoved() {
		String added = "1";
		String removed = "-";
		Line line = new Line(added, removed, "dank.java");
		List<String> types = new ArrayList<String>();
		types.add("java");

		int diff = line.getSrcDiff(types);

		assertEquals(1, diff);

	}

	/**
	 * Test is valid yes.
	 */
	@Test
	public void testisValidYes() {
		String added = "1";
		String removed = "2";
		Line line = new Line(added, removed, "dank.java");
		List<String> types = new ArrayList<String>();
		types.add("java");

		boolean valid = line.isValid(types);

		assertTrue(valid);
	}

	/**
	 * Test is valid yes2.
	 */
	@Test
	public void testisValidYes2() {
		String added = "-";
		String removed = "2";
		Line line = new Line(added, removed, "dank.java");
		List<String> types = new ArrayList<String>();
		types.add("java");

		boolean valid = line.isValid(types);

		assertTrue(valid);
	}

	/**
	 * Test is valid yes3.
	 */
	@Test
	public void testisValidYes3() {
		String added = "3";
		String removed = "-";
		Line line = new Line(added, removed, "dank.java");
		List<String> types = new ArrayList<String>();
		types.add("java");

		boolean valid = line.isValid(types);

		assertTrue(valid);
	}

	/**
	 * Test is valid no, nothing covered.
	 */
	@Test
	public void testisValidNo() {
		String added = "-";
		String removed = "-";
		Line line = new Line(added, removed, "dank.java");
		List<String> types = new ArrayList<String>();
		types.add("java");

		boolean valid = line.isValid(types);

		assertFalse(valid);
	}

	/**
	 * Test is valid no, not a java file.
	 */
	@Test
	public void testisValidNo2() {
		String added = "3";
		String removed = "5";
		Line line = new Line(added, removed, "dank.tt");
		List<String> types = new ArrayList<String>();
		types.add("java");

		boolean valid = line.isValid(types);

		assertFalse(valid);
	}

	/**
	 * Test is valid no, not a valid file type.
	 */
	@Test
	public void testisValidNo3() {
		String added = "3";
		String removed = "5";
		Line line = new Line(added, removed, "dank.java");
		List<String> types = new ArrayList<String>();
		types.add("tt");

		boolean valid = line.isValid(types);

		assertFalse(valid);
	}

	/**
	 * Test is src.
	 */
	@Test
	public void testIsSrc() {
		String added = "6";
		String removed = "7";
		String file = "dank.java";
		Line line = new Line(added, removed, file);
		List<String> types = new ArrayList<String>();
		types.add("java");

		boolean src = line.isSource(types);

		assertTrue(src);
	}

	/**
	 * Test is src no.
	 */
	@Test
	public void testIsSrcNo() {
		String added = "-";
		String removed = "-";
		String file = "dank.tt";
		Line line = new Line(added, removed, file);
		List<String> types = new ArrayList<String>();
		types.add("java");

		boolean src = line.isSource(types);

		assertFalse(src);
	}

	/**
	 * Test is src no2.
	 */
	@Test
	public void testIsSrcNo2() {
		String added = "6";
		String removed = "4";
		String file = "test/dank.tt";
		Line line = new Line(added, removed, file);
		List<String> types = new ArrayList<String>();
		types.add("java");

		boolean src = line.isSource(types);

		assertFalse(src);
	}
}
