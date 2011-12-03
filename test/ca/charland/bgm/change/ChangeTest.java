package ca.charland.bgm.change;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import ca.charland.bgm.change.Change;
import ca.charland.bgm.change.Line;

/**
 * Tests for Change.
 *
 * @author mcharland
 */
public class ChangeTest {

	/**
	 * Test.
	 */
	@Test
	public void test() {
		assertNotNull(new Change(null, null, null, null, null));
	}

	/**
	 * Test get diff none.
	 */
	@Test
	public void testGetDiffNone() {
		List<Line> lines = new ArrayList<Line>();
		Change change = new Change(null, null, null, null, lines);
		double diff = change.getCoverage();
		assertEquals(0, diff, 0.1);
	}

	/**
	 * Test get coverage zero.
	 */
	@Test
	public void testGetCoverageZero() {
		List<Line> lines = new ArrayList<Line>();
		lines.add(new Line("3", "3", "dog"));
		lines.add(new Line("2", "55", "dog"));
		Change change = new Change(null, null, null, null, lines);
		double diff = change.getCoverage();
		assertEquals(0, diff, 0.1);
	}
	
	/**
	 * Test get coverage half.
	 */
	@Test
	public void testGetCoverageHalf() {
		List<Line> lines = new ArrayList<Line>();
		lines.add(new Line("3", "3", "dog"));
		lines.add(new Line("3", "3", "test"));
		Change change = new Change(null, null, null, null, lines);
		double diff = change.getCoverage();
		assertEquals(0.5, diff, 0.1);
	}
	
	/**
	 * Test get coverage one.
	 */
	@Test
	public void testGetCoverageOne() {
		List<Line> lines = new ArrayList<Line>();
		lines.add(new Line("3", "3", "test"));
		lines.add(new Line("3", "3", "test"));
		Change change = new Change(null, null, null, null, lines);
		double diff = change.getCoverage();
		assertEquals(1, diff, 0.1);
	}
	
	/**
	 * Test get lines covered.
	 */
	@Test
	public void testGetLinesCovered() {
		List<Line> lines = new ArrayList<Line>();
		lines.add(new Line("3", "3", "test"));
		lines.add(new Line("3", "3", "test"));
		Change change = new Change(null, null, null, null, lines);
		int covered = change.getLinesCovered();
		assertEquals(12, covered, 0.1);
	}
	
	/**
	 * Test get date.
	 */
	@Test
	public void testGetDate() {
		Change change = new Change(null, null, "Date:     Wed Nov 30 13:51:05 2011 -0500", null, null);
		Date date = change.getDate();
		assertNotNull(date);
	}
	
	/**
	 * Test get author.
	 */
	@Test
	public void testGetAuthor() {
		List<Line> lines = new ArrayList<Line>();
		lines.add(new Line("3", "3", "test"));
		
		String expectedAuthor = "frank";
		Change change = new Change(null, expectedAuthor, null, null, lines);
		String author = change.getAuthor();
		
		assertEquals(expectedAuthor, author);
	}
}
