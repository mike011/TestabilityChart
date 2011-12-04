package ca.charland.bgm.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import junit.framework.Assert;

import org.junit.Test;

import ca.charland.bgm.change.Change;
import ca.charland.bgm.change.Line;

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
		
		List<ArrayCollection> bubbleData = graph.getAllBubbleData();
		Assert.assertNotNull(bubbleData);
		List<BubbleSeries> bubbleSeries = graph.getAllBubbleSeries();
		Assert.assertNotNull(bubbleSeries);
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
	 * Test add changes.
	 */
	@Test
	public void testAddChanges() {

		List<Change> changes = new ArrayList<Change>();
		ArrayList<Line> lines = new ArrayList<Line>();
		lines.add(new Line("2", "5", "bob.txt"));
		changes.add(new Change(null, null,
				"Date:  Tue Nov 29 10:36:43 2011 -0500", null, lines));
		GraphWriter graph = new GraphWriter();

		graph.addBubbles(null);
	}

	/**
	 * Creates the output.
	 */
	@Test
	public void createOutput() {

		List<Change> changes = new ArrayList<Change>();
		ArrayList<Line> lines = new ArrayList<Line>();
		lines.add(new Line("2", "5", "bob.txt"));
		changes.add(new Change(null, null,
				"Date:  Tue Nov 29 10:36:43 2011 -0500", null, lines));
		GraphWriter graph = new GraphWriter();
		graph.loadRawFile();
		graph.addBubbles(null);

		boolean createOutput = graph.createOutput();
		Assert.assertTrue(createOutput);
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
		graph.addDataForBubbles(bubbles);

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
}