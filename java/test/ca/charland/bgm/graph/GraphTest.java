package ca.charland.bgm.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import junit.framework.Assert;

import org.junit.Test;

import ca.charland.bgm.graph.ArrayCollection;
import ca.charland.bgm.graph.Bubble;
import ca.charland.bgm.graph.BubbleSeries;
import ca.charland.bgm.graph.Graph;

/**
 * Tests for Graph.
 * 
 * @author mcharland
 */
public class GraphTest {

	/**
	 * Test the constructor.
	 */
	@Test
	public void testGraph() {
		Graph g = new Graph();
		GraphWriterTest.assertList(g.getAllBubbleData());
		GraphWriterTest.assertList(g.getAllBubbleSeries());
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
		Graph g = new Graph();

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

		Graph g = new Graph();

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
		Graph g = new Graph();

		// Exercise
		g.addBubbles(null);

		// Verify
		List<ArrayCollection> bubbleData = g.getAllBubbleData();
		Assert.assertEquals(0, bubbleData.size());
	}
}
