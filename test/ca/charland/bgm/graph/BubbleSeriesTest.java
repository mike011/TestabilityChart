package ca.charland.bgm.graph;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Tests for Bubble Series
 * 
 * @author mcharland
 * 
 */
public class BubbleSeriesTest {

	@Test
	public void test() {
		Assert.assertNotNull(new BubbleSeries(3));
	}

	/**
	 * Testing to string.
	 */
	@Test
	public void testToString() {
		BubbleSeries bs = new BubbleSeries(2);

		// Exercise
		String string = bs.toString();

		// Verify
		StringBuffer actual = new StringBuffer();
		actual.append("<mx:BubbleSeries\r\n");
		actual.append("dataProvider=\"{s2}\"\r\n");
		actual.append("displayName=\"series2\"\r\n");
		actual.append("xField=\"Date\"\r\n");
		actual.append("yField=\"Coverage\"\r\n");
		actual.append("radiusField=\"Size\"\r\n");
		actual.append("/>\r\n");
		Assert.assertEquals(actual.toString(), string);
	}
}
