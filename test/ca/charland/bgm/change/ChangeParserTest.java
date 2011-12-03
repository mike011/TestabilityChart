package ca.charland.bgm.change;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import ca.charland.bgm.FileAccessing;
import ca.charland.bgm.change.Change;
import ca.charland.bgm.change.FileParser;
import ca.charland.bgm.change.Line;

/**
 * Tests for ChangeParser.
 *
 * @author mcharland
 */
public class ChangeParserTest {

	/**
	 * Test simple.
	 */
	@Test
	public void testSimple() {
		List<String> read = FileAccessing.read("test/res/simple.txt");
		List<Change> parse = FileParser.changes(read);
		assertNotNull(parse);
		assertEquals(1, parse.size());
	}

	/**
	 * Test two.
	 */
	@Test
	public void testTwo() {
		List<String> read = FileAccessing.read("test/res/two.txt");
		List<Change> parse = FileParser.changes(read);
		assertEquals(2, parse.size());
		
		Change change = parse.get(0);
		Change change2 = parse.get(1);
		assertFalse(change.equals(change2));
		
		assertEquals(change.getCoverage(), change2.getCoverage(), 0.1);
	}

	/**
	 * Test parse line.
	 */
	@Test
	public void testParseLine() {
		Line line = FileParser.line("1	1	permissions.txt");
		assertNotNull(line);
		assertTrue(line.isValid());
		assertEquals(2, line.getSrcDiff());
	}

}
