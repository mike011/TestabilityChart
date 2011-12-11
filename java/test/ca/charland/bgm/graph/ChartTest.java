package ca.charland.bgm.graph;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Tests for Chart.
 * 
 * @author mcharland
 */
public class ChartTest {

	/**
	 * Assert list.
	 * 
	 * @param list
	 *            the list
	 */
	static void assertList(List<?> list) {
		Assert.assertNotNull(list);
		Assert.assertEquals("Should be empty", 0, list.size());
	}

	/**
	 * Test the constructor.
	 */
	@Test
	public void testConstructor() {
		Chart g = new Chart();
		assertNotNull(g.getPanel());
		assertList(g.getAllBubbleData());
		assertList(g.getAllBubbleSeries());
	}

	/**
	 * Test adding the bubbles.
	 */
	@Test
	public void testAddBubbles() {

		// Setup
		ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
		bubbles.add(new Bubble(null, 0, 0, ""));
		Map<String, ArrayList<Bubble>> changes = new TreeMap<String, ArrayList<Bubble>>();
		changes.put("b1", bubbles);
		Chart g = new Chart();

		// Exercise
		g.addBubbles(changes);

		// Verify
		List<ArrayCollection> bubbleData = g.getAllBubbleData();
		Assert.assertEquals(1, bubbleData.size());

		List<BubbleSeries> bubbleSeries = g.getAllBubbleSeries();
		Assert.assertEquals(1, bubbleSeries.size());
	}

	/**
	 * Test adding the bubbles.
	 */
	@Test
	public void testAddBubblesTwo() {

		// Setup
		ArrayList<Bubble> bubblesOne = new ArrayList<Bubble>();
		bubblesOne.add(new Bubble(null, 0, 0, ""));

		ArrayList<Bubble> bubblesTwo = new ArrayList<Bubble>();
		bubblesTwo.add(new Bubble(null, 0, 0, ""));
		Map<String, ArrayList<Bubble>> changes = new TreeMap<String, ArrayList<Bubble>>();
		changes.put("b1", bubblesOne);
		changes.put("b2", bubblesTwo);

		Chart g = new Chart();

		// Exercise
		g.addBubbles(changes);

		// Verify
		List<ArrayCollection> bubbleData = g.getAllBubbleData();
		Assert.assertEquals(2, bubbleData.size());

		List<BubbleSeries> bubbleSeries = g.getAllBubbleSeries();
		Assert.assertEquals(2, bubbleSeries.size());
	}

	/**
	 * Testing an empty set of changes.
	 */
	@Test
	public void testAddBubblesEmpty() {
		// Setup
		Chart g = new Chart();

		// Exercise
		g.addBubbles(null);

		// Verify
		List<ArrayCollection> bubbleData = g.getAllBubbleData();
		Assert.assertEquals(0, bubbleData.size());
	}
}
