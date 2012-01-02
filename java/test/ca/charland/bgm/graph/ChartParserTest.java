package ca.charland.bgm.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.jfree.data.xy.XYZDataset;
import org.junit.Test;

import ca.charland.bgm.change.Change;
import ca.charland.bgm.change.Line;

/**
 * Test for GraphParser.
 * 
 * @author mcharland
 */
public class ChartParserTest {

	/**
	 * Test method for {@link ca.charland.bgm.graph.ChartParser#bubbles(List, List, String)}.
	 */
	@Test
	public void testBubbles() {

		// Setup
		List<Change> changes = new ArrayList<Change>();
		ArrayList<Line> lines = new ArrayList<Line>();
		lines.add(new Line("2", "5", "bob.txt"));
		String author = "Author: defected <defected@defected-P5K-EPU.(none)>";
		changes.add(new Change("commit b", author, "Date:  Tue Nov 29 10:36:43 2011 -0500", null, lines));
		List<String> types = new ArrayList<String>();
		types.add("java");

		// Exercise
		Map<String, ArrayList<Bubble>> bubbles = ChartParser.bubbles(changes, types, null);

		// Verify
		assertNotNull(bubbles);
	}

	/**
	 * Test method for {@link ca.charland.bgm.graph.ChartParser#bubbles(List, List, String)}.
	 */
	@Test
	public void testTwoBubblesDiff() {

		// Setup
		List<Change> changes = new ArrayList<Change>();
		ArrayList<Line> lines = new ArrayList<Line>();
		lines.add(new Line("2", "5", "bob.txt"));
		String author = "Author: defected <defected@defected-P5K-EPU.(none)>";
		changes.add(new Change("commit b", author, "Date:  Tue Nov 29 10:36:43 2011 -0500", null, lines));
		changes.add(new Change("commit b", "Author: frank <", "Date:  Tue Nov 29 10:36:43 2011 -0500", null, lines));
		List<String> types = new ArrayList<String>();
		types.add("java");

		// Exercise
		Map<String, ArrayList<Bubble>> bubbles = ChartParser.bubbles(changes, types, null);

		// Verify
		assertNotNull(bubbles);
		assertEquals(2, bubbles.keySet().size());
	}
	
	/**
	 * Test method for {@link ca.charland.bgm.graph.ChartParser#bubbles(List, List, String)}.
	 */
	@Test
	public void testTwoBubblesSame() {

		// Setup
		List<Change> changes = new ArrayList<Change>();
		ArrayList<Line> lines = new ArrayList<Line>();
		lines.add(new Line("2", "5", "bob.txt"));
		String author = "Author: defected <defected@defected-P5K-EPU.(none)>";
		changes.add(new Change("commit b", author, "Date:  Tue Nov 29 10:36:43 2011 -0500", null, lines));
		changes.add(new Change("commit b", author, "Date:  Tue Nov 29 10:36:43 2011 -0500", null, lines));
		List<String> types = new ArrayList<String>();
		types.add("java");

		// Exercise
		Map<String, ArrayList<Bubble>> bubbles = ChartParser.bubbles(changes, types, null);

		// Verify
		assertNotNull(bubbles);
		assertEquals(1, bubbles.keySet().size());
	}
	
	/**
	 * Gets the normalised bubbles.
	 * 
	 * @return the normalised bubbles
	 */
	@Test
	public void getNormalisedBubblesDate() {

		// Setup
		ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
		Date date = new Date(0);
		bubbles.add(new Bubble(date, 0, 0, "", null));
		bubbles.add(new Bubble(date, 0, 0, "", null));

		Map<String, ArrayList<Bubble>> map = new TreeMap<String, ArrayList<Bubble>>();
		map.put("author", bubbles);
		Chart graph = new Chart(null);
		graph.addBubbles(map);

		// Exercise
		ChartParser.normaliseBubbleData(map);

		// Verify
		XYZDataset normalized = graph.getDataSet();
		assertEquals(1, normalized.getSeriesCount());

		ArrayList<Bubble> normalizedBubbles = map.get("author");
		assertEquals(date.getTime(), normalizedBubbles.get(0).getDate(), 1E5);
		assertEquals(date.getTime(), normalizedBubbles.get(1).getDate(), 1E5);
	}

	/**
	 * Gets the normalised bubbles.
	 * 
	 * @return the normalised bubbles
	 */
	@Test
	public void getNormalisedBubblesSize() {

		// Setup
		ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
		bubbles.add(new Bubble(new Date(0), 0, 500, "", null));
		bubbles.add(new Bubble(new Date(0), 0, 01000, "", null));

		Map<String, ArrayList<Bubble>> map = new TreeMap<String, ArrayList<Bubble>>();
		map.put("author", bubbles);
		Chart graph = new Chart(null);
		graph.addBubbles(map);

		// Exercise
		ChartParser.normaliseBubbleData(map);

		// Verify
		XYZDataset normalized = graph.getDataSet();
		assertEquals(1, normalized.getSeriesCount());

		ArrayList<Bubble> normalizedBubbles = map.get("author");
		assertEquals(1.0, normalizedBubbles.get(0).getSize(), 0.1);
		assertEquals(2.17, normalizedBubbles.get(1).getSize(), 0.1);
	}
}