package ca.charland.bgm.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

import ca.charland.bgm.graph.Bubble;
import ca.charland.bgm.graph.Graph;

/**
 * Tests for Graph.
 *
 * @author mcharland
 */
public class GraphTest {

	/**
	 * Test method for {@link ca.charland.bgm.graph.Graph#Graph(java.util.List)}.
	 */
	@Test
	public void testGraph() {
		assertNotNull(new Graph(null));
	}
	
	/**
	 * Gets the normalized bubbles.
	 *
	 * @return the normalized bubbles
	 */
	@Test
	public void getNormalizedBubbles() {
		
		ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
		bubbles.add(new Bubble(500, 0, 0));
		bubbles.add(new Bubble(10000, 0, 0));
		
		Map<String, ArrayList<Bubble>> map = new TreeMap<String, ArrayList<Bubble>>();
		String author = "author";
		map.put(author, bubbles);
		
		Graph graph = new Graph(map);
		
		Map<String, ArrayList<Bubble>> normalized = graph.getNormalizedBubbles();
		assertEquals(1, normalized.size());
		assertEquals(2, normalized.get(author).size());
	}
}
