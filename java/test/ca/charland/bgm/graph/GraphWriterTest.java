package ca.charland.bgm.graph;

import java.util.ArrayList;
import java.util.List;

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
		bubbles.add(new Bubble(null, 0, 0, ""));

		// Exercise
		Graph g = new Graph();
		g.addDataForBubbles(75, bubbles);

		// Verify
		List<ArrayCollection> bubbleData = g.getAllBubbleData();
		Assert.assertEquals(1, bubbleData.size());
	}
}