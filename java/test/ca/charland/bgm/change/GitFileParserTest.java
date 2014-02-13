package ca.charland.bgm.change;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ca.charland.bgm.FileAccessing;

/**
 * Tests for FileParser.
 * 
 * @author mcharland
 */
public class GitFileParserTest {

	/**
	 * Test the simple case with one change.
	 */
	@Test
	public void testOneChange() {
		// Setup
		List<String> read = FileAccessing.read("test/res/one.txt");
		List<String> types = new ArrayList<String>();
		types.add("java");
		
		// Exercise
		List<Change> parse = GitFileParser.changes(read, types);
		
		// Verify
		assertNotNull(parse);
		assertTrue("Not empty", parse.isEmpty());
	}

	/**
	 * Test two changes.
	 */
	@Test
	public void testTwoChanges() {
		// Setup
		List<String> read = FileAccessing.read("test/res/two.txt");
		List<String> types = new ArrayList<String>();
		types.add("java");
		
		// Exercise
		List<Change> parse = GitFileParser.changes(read, types);
		
		// Verify
		assertTrue(parse.isEmpty());
	}

	/**
	 * Test two java files.
	 */
	@Test
	public void testTwoJava() {
		// Setup
		List<String> read = FileAccessing.read("test/res/java_two.txt");
		List<String> types = new ArrayList<String>();
		types.add("java");
		
		// Exercise
		List<Change> parse = GitFileParser.changes(read, types);
		
		// Verify
		assertEquals(2, parse.size());

		Change change = parse.get(0);
		Change change2 = parse.get(1);
		assertFalse(change.equals(change2));

		assertEquals(change.getCoverage(types), change2.getCoverage(types), 0.1);
	}

	/**
	 * Test parse line.
	 */
	@Test
	public void testParseLine() {
		// Setup & Exercise
		Line line = GitFileParser.line("1	1	permissions.java");
		
		// Verify
		assertNotNull(line);
		List<String> types = new ArrayList<String>();
		types.add("java");
		assertTrue(line.isValid(types));
		assertEquals(2, line.getSourceDifference(types));
	}

	/**
	 * Tests if a file is valid.
	 */
	@Test
	public void testIsChangeValid() {
		// Setup
		Line line = new Line(null, null, null);
		List<Line> lines = new ArrayList<Line>();
		lines.add(line);
		Change change = new Change(null, null, null, null, lines);
		
		// Exercise & Verify
		assertTrue(GitFileParser.isChangeValid(change, null));
	}
}