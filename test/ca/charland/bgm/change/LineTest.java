/**
 * 
 */
package ca.charland.bgm.change;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.charland.bgm.change.Line;

/**
 * The Class LineTest.
 *
 * @author mcharland
 */
public class LineTest {

	/**
	 * Test.
	 */
	@Test
	public void test() {
		String added = "1";
		String removed = "1";
		assertNotNull(new Line(added, removed, "dank.tt"));
	}

	/**
	 * Test get src diff.
	 */
	@Test
	public void testGetSrcDiff() {
		String added = "1";
		String removed = "1";
		Line line = new Line(added, removed, "dank.tt");
		
		int diff = line.getSrcDiff();
		
		assertEquals(2, diff);
		
		int test = line.getTestDiff();
		assertEquals(0, test);
	}
	
	/**
	 * Test get test diff.
	 */
	@Test
	public void testGetTestDiff() {
		String added = "1";
		String removed = "1";
		Line line = new Line(added, removed, "test/dank.tt");
		
		int diff = line.getSrcDiff();
		
		assertEquals(0, diff);
		
		int test = line.getTestDiff();
		assertEquals(2, test);
	}
	

	/**
	 * Test get diff added.
	 */
	@Test
	public void testGetDiffAdded() {
		String added = "-";
		String removed = "1";
		Line line = new Line(added, removed, "dank.tt");
		
		int src = line.getSrcDiff();
		
		assertEquals(1, src);

	}
	
	/**
	 * Test get diff removed.
	 */
	@Test
	public void testGetDiffRemoved() {
		String added = "1";
		String removed = "-";
		Line line = new Line(added, removed, "dank.tt");
		
		int diff = line.getSrcDiff();
		
		assertEquals(1, diff);
		
	}
	
	/**
	 * Testis valid yes.
	 */
	@Test
	public void testisValidYes() {
		String added = "1";
		String removed = "2";
		Line line = new Line(added, removed, "dank.tt");
		
		boolean valid = line.isValid();
		
		assertTrue(valid);
	}
	
	/**
	 * Testis valid yes2.
	 */
	@Test
	public void testisValidYes2() {
		String added = "-";
		String removed = "2";
		Line line = new Line(added, removed, "dank.tt");
		
		boolean valid = line.isValid();
		
		assertTrue(valid);
	}
	
	/**
	 * Testis valid yes3.
	 */
	@Test
	public void testisValidYes3() {
		String added = "3";
		String removed = "-";
		Line line = new Line(added, removed, "dank.tt");
		
		boolean valid = line.isValid();
		
		assertTrue(valid);
	}
	
	/**
	 * Testis valid no.
	 */
	@Test
	public void testisValidNo() {
		String added = "-";
		String removed = "-";
		Line line = new Line(added, removed, "dank.tt");
		
		boolean valid = line.isValid();
		
		assertFalse(valid);
	}
	
	/**
	 * Test is src.
	 */
	@Test
	public void testIsSrc() {
		String added = "6";
		String removed = "7";
		String file = "dank.tt";
		Line line = new Line(added, removed, file);
		
		boolean src = line.isSource();
		
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
		
		boolean src = line.isSource();
		
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
		
		boolean src = line.isSource();
		
		assertFalse(src);
	}
}
