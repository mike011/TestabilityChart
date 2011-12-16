package ca.charland.bgm.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import junit.framework.Assert;

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
		Chart g = new Chart();
		assertNotNull(g.getPanel());
		XYZDataset dataSet = g.getDataSet();
		assertNotNull(dataSet);
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
		XYZDataset ds = g.getDataSet();
		Assert.assertEquals(1, ds.getSeriesCount());
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
		XYZDataset ds = g.getDataSet();
		Assert.assertEquals(2, ds.getSeriesCount());
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
		XYZDataset bubbleData = g.getDataSet();
		Assert.assertEquals(0, bubbleData.getSeriesCount());
	}

	/**
	 * Test method for {@link ca.charland.bgm.graph.Chart#addSeries()}.
	 */
	@Test
	public final void testAddSeries() {
		Chart c = new Chart();
		c.addSeries("Series 1", getData());
		assertEquals(1, c.getDataSet().getSeriesCount());
	}
	
	/**
	 * Test getting the data.
	 */
	@Test
	public void testGetData() {
		Chart g = new Chart();
		XYZDataset dataSet = g.getDataSet();
		assertEquals(0, dataSet.getSeriesCount());
	}

	/**
	 * Gets the data.
	 * 
	 * @return the data
	 */
	static double[][] getData() {
		// date
		double x[] = { 5.7003213E8, 5.6990106E8, 5.09607936E8, 5.06068992E8, 3.54156544E8, 3.51535104E8, 3.4865152E8,
		        3.43277568E8, 3.40918272E8, 2.76955136E8, 2.69221888E8, 2.59260416E8, 1.8808832E8, 1.81796864E8,
		        1.57548544E8, 7.3007104E7, 6.5273856E7, 5.3215232E7, 3538944.0, 0.0 };

		// coverage
		double y[] = { 54, 68, 54, 76, 72, 41, 81, 40, 55, 8, 100, 40, 45, 57, 59, 55, 34, 33, 12, 53 };

		// size
		double z[] = { 50.0, 2.9733925, 2.097561, 1.9866962, 1.9977827, 1.654102, 2.7627494, 1.0332594, 5.490022, 2.053215,
		        1.0, 2.8736143, 5.9113083, 4.0266075, 2.8514411, 1.7982261, 2.7627494, 5.7117515, 1.4545455, 14.425721 };
		double ad3[][] = { x, y, z };
		return ad3;
	}
}
