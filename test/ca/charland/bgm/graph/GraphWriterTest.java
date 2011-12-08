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
		GraphWriter graph = new GraphWriter(null);
		assertList(graph.getGeneratedMXML());
	}

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
	 * Test load raw file.
	 */
	@Test
	public void testLoadRawFile() {
		GraphWriter graph = new GraphWriter(null);

		boolean loaded = graph.loadRawFile();

		Assert.assertTrue(loaded);
	}

	/**
	 * Creates the output.
	 */
	@Test
	public void testCreateOutput() {

		// Setup
		ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
		bubbles.add(new Bubble(null, 0, 0));
		Map<String, ArrayList<Bubble>> changes = new TreeMap<String, ArrayList<Bubble>>();
		changes.put("b1", bubbles);

		Graph g = new Graph();
		g.addBubbles(changes);

		GraphWriter gw = new GraphWriter(g);
		gw.loadRawFile();

		// Exercise
		boolean createOutput = gw.createOutput();

		// Verify
		Assert.assertTrue(createOutput);
		List<String> out = gw.getGeneratedMXML();
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
			} else if (line.contains("private var s") && line.contains(":ArrayCollection = new ArrayCollection( [")) {
				data = true;
			} else if (line.contains("{2}")) {
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
		GraphWriter graph = new GraphWriter(null);
		boolean wrote = graph.writeFile();
		Assert.assertFalse(wrote);
	}

	/**
	 * Adds the data for bubbles.
	 */
	@Test
	public void addDataForBubbles() {
		// Setup
		ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
		bubbles.add(new Bubble(null, 0, 0));

		// Exercise
		Graph g = new Graph();
		g.addDataForBubbles(75, bubbles);

		// Verify
		List<ArrayCollection> bubbleData = g.getAllBubbleData();
		Assert.assertEquals(1, bubbleData.size());
	}
}