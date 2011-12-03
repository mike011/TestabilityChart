package ca.charland.bgm.graph;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import ca.charland.bgm.change.Change;
import ca.charland.bgm.change.Line;
import ca.charland.bgm.graph.Bubble;
import ca.charland.bgm.graph.GraphParser;

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
		
		List<Change> changes = new ArrayList<Change>();
		ArrayList<Line> lines = new ArrayList<Line>();
		lines.add(new Line("2", "5", "bob.txt"));
		changes.add(new Change(null, "bob", "Date:  Tue Nov 29 10:36:43 2011 -0500", null, lines));
		
		Map<String, ArrayList<Bubble>> bubbles = GraphParser.bubbles(changes);
		
		assertNotNull(bubbles);
	}

}
