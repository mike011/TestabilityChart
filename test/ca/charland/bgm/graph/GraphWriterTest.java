package ca.charland.bgm.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Tests for GraphWriter.
 * 
 * @author mcharland
 */
public class GraphWriterTest {

	/**
	 * Test the constructor.
	 */
	@Test
	public void testGraphWriter() {
		GraphWriter graph = new GraphWriter();

		assertList(graph.getAllBubbleData());
		assertList(graph.getAllBubbleSeries());
		assertList(graph.getGeneratedMXML());
	}

	/**
	 * Assert list.
	 *
	 * @param list the list
	 */
	private void assertList(List<?> list) {
		Assert.assertNotNull(list);
		Assert.assertEquals("Should be empty", 0, list.size());
	}

	/**
	 * Test load raw file.
	 */
	@Test
	public void testLoadRawFile() {
		GraphWriter graph = new GraphWriter();

		boolean loaded = graph.loadRawFile();

		Assert.assertTrue(loaded);
	}

	/**
	 * Creates the output.
	 */
	@Test
	public void testCreateOutput() {

		// Setup
		GraphWriter graph = new GraphWriter();
		graph.loadRawFile();
		ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
		bubbles.add(new Bubble(0, 0, 0));
		Map<String, ArrayList<Bubble>> changes = new TreeMap<String, ArrayList<Bubble>>();
		changes.put("b1", bubbles);

		graph.addBubbles(changes);

		// Exercise
		boolean createOutput = graph.createOutput();

		// Verify
		Assert.assertTrue(createOutput);
		List<String> out = graph.getGeneratedMXML();
		assertParsed(out);

	}

	/**
	 * Assert parsed.
	 * 
	 * @param out
	 *            the out
	 */
	private void assertParsed(List<String> out) {
		boolean one = false;
		boolean two = false;
		boolean data = false;
		boolean series = false;
		for (String line : out) {
			if (line.contains("{1}")) {
				one = true;
			} else if (line.contains("private var s0:ArrayCollection = new ArrayCollection( [")) {
				data = true;
			}else if (line.contains("{2}")) {
				two = true;
			} else if (line.contains("<mx:BubbleSeries")) {
				series = true;
			}
		}
		Assert.assertFalse("data parsing string not removed", one);
		Assert.assertTrue("data not added", data);
		
		Assert.assertFalse("series parsing not removed", two);
		Assert.assertTrue("series not added", series);
	}

	/**
	 * Write file.
	 */
	@Test
	public void writeFile() {
		GraphWriter graph = new GraphWriter();
		boolean wrote = graph.writeFile();
		Assert.assertFalse(wrote);
	}

	/**
	 * Adds the data for bubbles.
	 */
	@Test
	public void addDataForBubbles() {
		// Setup
		GraphWriter graph = new GraphWriter();
		ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
		bubbles.add(new Bubble(0, 0, 0));

		// Exercise
		graph.addDataForBubbles(75, bubbles);

		// Verify
		List<ArrayCollection> bubbleData = graph.getAllBubbleData();
		Assert.assertEquals(1, bubbleData.size());
	}

	/**
	 * Testing an empty set of changes.
	 */
	@Test
	public void testAddBubblesEmpty() {
		// Setup
		GraphWriter graph = new GraphWriter();

		// Exercise
		graph.addBubbles(null);

		// Verify
		List<ArrayCollection> bubbleData = graph.getAllBubbleData();
		Assert.assertEquals(0, bubbleData.size());
	}

	/**
	 * Test adding the bubbles.
	 */
	@Test
	public void testAddBubbles() {

		// Setup
		GraphWriter graph = new GraphWriter();
		ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
		bubbles.add(new Bubble(0, 0, 0));
		Map<String, ArrayList<Bubble>> changes = new TreeMap<String, ArrayList<Bubble>>();
		changes.put("b1", bubbles);

		// Exercise
		graph.addBubbles(changes);

		// Verify
		List<ArrayCollection> bubbleData = graph.getAllBubbleData();
		Assert.assertEquals(1, bubbleData.size());

		List<BubbleSeries> bubbleSeries = graph.getAllBubbleSeries();
		Assert.assertEquals(1, bubbleSeries.size());
	}
	
	/**
	 * Test adding the bubbles.
	 */
	@Test
	public void testAddBubblesTwo() {

		// Setup
		GraphWriter graph = new GraphWriter();
		ArrayList<Bubble> bubblesOne = new ArrayList<Bubble>();
		bubblesOne.add(new Bubble(0, 0, 0));
		
		ArrayList<Bubble> bubblesTwo = new ArrayList<Bubble>();
		bubblesTwo.add(new Bubble(0, 0, 0));
		Map<String, ArrayList<Bubble>> changes = new TreeMap<String, ArrayList<Bubble>>();
		changes.put("b1", bubblesOne);
		changes.put("b2", bubblesTwo);

		// Exercise
		graph.addBubbles(changes);

		// Verify
		List<ArrayCollection> bubbleData = graph.getAllBubbleData();
		Assert.assertEquals(2, bubbleData.size());

		List<BubbleSeries> bubbleSeries = graph.getAllBubbleSeries();
		Assert.assertEquals(2, bubbleSeries.size());
	}
}