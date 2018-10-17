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
 * @author mcharland
 */
public class GitFileParserTest {

	@Test
	public void testOneChange() {
		// Setup
		List<String> read = FileAccessing.read("test/res/one.txt");
		List<String> types = new ArrayList<String>();
		types.add("java");

		// Exercise
		List<? extends Change> parse = new GitFileParser().changes(read, types);

		// Verify
		assertNotNull(parse);
		assertTrue("Not empty", parse.isEmpty());
	}

	@Test
	public void testTwoChanges() {
		// Setup
		List<String> read = FileAccessing.read("test/res/two.txt");
		List<String> types = new ArrayList<String>();
		types.add("java");

		// Exercise
		List<? extends Change> parse = new GitFileParser().changes(read, types);

		// Verify
		assertTrue(parse.isEmpty());
	}

	@Test
	public void testAlot() {
		// Setup
		List<String> read = FileAccessing.read("test/res/alot.txt");
		List<String> types = new ArrayList<String>();
		types.add("xml");

		// Exercise
		List<? extends Change> parse = new GitFileParser().changes(read, types);

		// Verify
		assertEquals(259, parse.size());
	}

	@Test
	public void testTwoJava() {
		// Setup
		List<String> read = FileAccessing.read("test/res/java_two.txt");
		List<String> types = new ArrayList<String>();
		types.add("java");

		// Exercise
		List<? extends Change> parse = new GitFileParser().changes(read, types);

		// Verify
		assertEquals(2, parse.size());

		Change change = parse.get(0);
		Change change2 = parse.get(1);
		assertFalse(change.equals(change2));

		assertEquals(change.getCoverage(types), change2.getCoverage(types), 0.1);
	}

	@Test
	public void testPodFile() {
		// Setup
		List<String> read = FileAccessing.read("test/res/pods.txt");
		List<String> types = new ArrayList<String>();
		types.add("swift");

		// Exercise
		List<? extends Change> parse = new GitFileParser().changes(read, types);

		// Verify
		assertEquals(0, parse.size());
	}

	@Test
	public void testParseLine() {
		// Setup & Exercise
		Line line = new GitFileParser().line("1	1	permissions.java");

		// Verify
		assertNotNull(line);
		List<String> types = new ArrayList<String>();
		types.add("java");
		assertTrue(line.isValid(types));
		assertEquals(2, line.getSourceDifference(types));
	}

	@Test
	public void testParseLineIngorningPods() {
		// Setup & Exercise
		Line line = new GitFileParser().line("1	1	Pods/permissions.java");

		// Verify
		assertNotNull(line);
		List<String> types = new ArrayList<String>();
		types.add("java");
		assertFalse(line.isValid(types));
	}

	@Test
	public void testIsChangeValid() {
		// Setup
		Line line = new Line(null, null, null);
		List<Line> lines = new ArrayList<Line>();
		lines.add(line);
		GitChange change = new GitChange(null, null, null, null, lines);

		// Exercise & Verify
		assertTrue(new GitFileParser().isChangeValid(change, null));
	}
}