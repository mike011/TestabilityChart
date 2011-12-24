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
public class FileParserTest {

	/**
	 * Test the simple case with one change.
	 */
	@Test
	public void testOne() {
		List<String> read = FileAccessing.read("test/res/one.txt");
		List<String> types = new ArrayList<String>();
		types.add("java");
		List<Change> parse = FileParser.changes(read, types);
		assertNotNull(parse);
		assertTrue("Not empty", parse.isEmpty());
	}

	/**
	 * Test two.
	 */
	@Test
	public void testTwo() {
		List<String> read = FileAccessing.read("test/res/two.txt");
		List<String> types = new ArrayList<String>();
		types.add("java");
		List<Change> parse = FileParser.changes(read, types);
		assertTrue(parse.isEmpty());
	}

	/**
	 * Test two java files.
	 */
	@Test
	public void testTwoJava() {
		List<String> read = FileAccessing.read("test/res/java_two.txt");
		List<String> types = new ArrayList<String>();
		types.add("java");
		List<Change> parse = FileParser.changes(read, types);
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
		Line line = FileParser.line("1	1	permissions.java");
		assertNotNull(line);
		List<String> types = new ArrayList<String>();
		types.add("java");
		assertTrue(line.isValid(types));
		assertEquals(2, line.getSrcDiff(types));
	}

	/**
	 * Tests if a file is valid.
	 */
	@Test
	public void testIsChangeValid() {
		Line line = new Line(null, null, null);
		List<Line> lines = new ArrayList<Line>();
		lines.add(line);
		Change change = new Change(null, null, null, null, lines);
		assertTrue(FileParser.isChangeValid(change, null));
	}
}