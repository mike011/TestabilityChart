package ca.charland.bgm.graph;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Testing of JLink
 * 
 * @author mcharland
 *
 */
public class JLinkTest {

	/**
	 * Test setting and getting a URL.
	 */
	@Test
	public void testURL() {
		
		// Setup
		JLink link = new JLink();
		
		// Exercise
		link.setURL("url");
		
		// Verify
		assertEquals("url", link.getURL());
	}
}
