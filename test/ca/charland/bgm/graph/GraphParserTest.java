package ca.charland.bgm.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

import ca.charland.bgm.change.Change;
import ca.charland.bgm.change.Line;

/**
 * Test for GraphParser.
 * 
 * @author mcharland
 */
public class GraphParserTest {

	/**
	 * Test method for {@link ca.charland.bgm.graph.GraphParser#bubbles(java.util.List)}.
	 */
	@Test
	public void testBubbles() {

		// Setup
		List<Change> changes = new ArrayList<Change>();
		ArrayList<Line> lines = new ArrayList<Line>();
		lines.add(new Line("2", "5", "bob.txt"));
		String author = "Author: defected <defected@defected-P5K-EPU.(none)>";
		changes.add(new Change(null, author, "Date:  Tue Nov 29 10:36:43 2011 -0500", null, lines));

		// Exercise
		Map<String, ArrayList<Bubble>> bubbles = GraphParser.bubbles(changes);

		// Verify
		assertNotNull(bubbles);
	}

	/**
	 * Gets the normalized bubbles.
	 * 
	 * @return the normalized bubbles
	 */
	@Test
	public void getNormalisedBubblesX() {

		// Setup
		ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
		Date date = new Date();
		bubbles.add(new Bubble(date, 0, 0));
		bubbles.add(new Bubble(date, 0, 0));

		Map<String, ArrayList<Bubble>> map = new TreeMap<String, ArrayList<Bubble>>();
		map.put("author", bubbles);
		Graph graph = new Graph();
		graph.addBubbles(map);

		// Exercise
		GraphParser.normaliseBubbleData(map);

		// Verify
		List<ArrayCollection> normalized = graph.getAllBubbleData();
		assertEquals(1, normalized.size());

		ArrayList<Bubble> normalizedBubbles = map.get("author");
		assertEquals(date.getTime(), normalizedBubbles.get(0).getDate(), 1E5);
		assertEquals(date.getTime(), normalizedBubbles.get(1).getDate(), 1E5);
	}

	/**
	 * Gets the normalized bubbles.
	 * 
	 * @return the normalized bubbles
	 */
	@Test
	public void getNormalisedBubblesSize() {

		// Setup
		ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
		bubbles.add(new Bubble(null, 0, 500));
		bubbles.add(new Bubble(null, 0, 01000));

		Map<String, ArrayList<Bubble>> map = new TreeMap<String, ArrayList<Bubble>>();
		map.put("author", bubbles);
		Graph graph = new Graph();
		graph.addBubbles(map);

		// Exercise
		GraphParser.normaliseBubbleData(map);

		// Verify
		List<ArrayCollection> normalized = graph.getAllBubbleData();
		assertEquals(1, normalized.size());

		ArrayList<Bubble> normalizedBubbles = map.get("author");
		assertEquals(1.0, normalizedBubbles.get(0).getSize(), 0.1);
		assertEquals(2.17, normalizedBubbles.get(1).getSize(), 0.1);
	}

}
