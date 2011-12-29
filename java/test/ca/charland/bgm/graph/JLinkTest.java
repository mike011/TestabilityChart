package ca.charland.bgm.graph;

import org.junit.Assert;
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
		
		JLink link = new JLink();
		
		link.setURL("url");
		
		Assert.assertEquals("url", link.getURL());
	}
}
