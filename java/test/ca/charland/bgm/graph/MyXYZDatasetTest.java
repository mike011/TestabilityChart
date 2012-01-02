package ca.charland.bgm.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

/**
 * Testing of MyXYZDataset.
 * 
 * @author mcharland
 * 
 */
public class MyXYZDatasetTest {

	/**
	 * Test method for {@link ca.charland.bgm.graph.MyXYZDataset#MyXYZDataset()}.
	 */
	@Test
	public final void testMyXYZDataset() {
		assertNotNull(new MyXYZDataset());
	}

	/**
	 * Test method for {@link ca.charland.bgm.graph.MyXYZDataset#getBubble(java.lang.String, int)}.
	 */
	@Test
	public final void testGetBubble() {
		// Setup
		MyXYZDataset ds = new MyXYZDataset();
		Map<String, ArrayList<Bubble>> changes = new TreeMap<String, ArrayList<Bubble>>();
		ArrayList<Bubble> arrayList = new ArrayList<Bubble>();
		Bubble expected = new Bubble(null, 0, 0, null, null);
		arrayList.add(expected);
		changes.put("key", arrayList);
		ds.setBubbles(changes);

		// Exercise
		Bubble result = ds.getBubble("key", 0);

		// Verify
		assertEquals(expected, result);
	}
}
