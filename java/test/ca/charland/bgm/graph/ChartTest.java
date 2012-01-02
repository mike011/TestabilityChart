package ca.charland.bgm.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.jfree.data.xy.XYZDataset;
import org.junit.Test;

/**
 * Tests for Chart.
 * 
 * @author mcharland
 */
public class ChartTest {

	/**
	 * Test the constructor.
	 */
	@Test
	public void testConstructor() {
		// Setup & Exercise
		Chart g = new Chart(null);
		
		// Verify
		assertNotNull(g.getPanel());
		XYZDataset dataSet = g.getDataSet();
		assertNotNull(dataSet);
	}

	/**
	 * Test adding one bubble.
	 */
	@Test
	public void testAddBubbles() {
		// Setup
		ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
		bubbles.add(new Bubble(null, 0, 0, "", null));
		Map<String, ArrayList<Bubble>> changes = new TreeMap<String, ArrayList<Bubble>>();
		changes.put("b1", bubbles);
		Chart g = new Chart(null);

		// Exercise
		g.addBubbles(changes);

		// Verify
		XYZDataset ds = g.getDataSet();
		assertEquals(1, ds.getSeriesCount());
	}

	/**
	 * Test adding two bubbles.
	 */
	@Test
	public void testAddBubblesTwo() {
		// Setup
		ArrayList<Bubble> bubblesOne = new ArrayList<Bubble>();
		bubblesOne.add(new Bubble(null, 0, 0, "", null));

		ArrayList<Bubble> bubblesTwo = new ArrayList<Bubble>();
		bubblesTwo.add(new Bubble(null, 0, 0, "", null));
		Map<String, ArrayList<Bubble>> changes = new TreeMap<String, ArrayList<Bubble>>();
		changes.put("b1", bubblesOne);
		changes.put("b2", bubblesTwo);

		Chart g = new Chart(null);

		// Exercise
		g.addBubbles(changes);

		// Verify
		XYZDataset ds = g.getDataSet();
		assertEquals(2, ds.getSeriesCount());
	}

	/**
	 * Testing an empty set of changes.
	 */
	@Test
	public void testAddBubblesEmpty() {
		// Setup
		Chart g = new Chart(null);

		// Exercise
		g.addBubbles(null);

		// Verify
		XYZDataset bubbleData = g.getDataSet();
		assertEquals(0, bubbleData.getSeriesCount());
	}

	/**
	 * Test getting the data.
	 */
	@Test
	public void testGetData() {
		// Setup
		Chart g = new Chart(null);
		
		// Exercise
		XYZDataset dataSet = g.getDataSet();
		
		// Verify
		assertEquals(0, dataSet.getSeriesCount());
	}
}
