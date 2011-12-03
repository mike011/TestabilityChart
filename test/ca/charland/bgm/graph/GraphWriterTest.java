/**
 * 
 */
package ca.charland.bgm.graph;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import ca.charland.bgm.change.Change;
import ca.charland.bgm.change.Line;
import ca.charland.bgm.graph.Bubble;
import ca.charland.bgm.graph.GraphWriter;

/**
 * The Class GraphWriterTest.
 *
 * @author mcharland
 */
public class GraphWriterTest {
	
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
		changes.add(new Change(null, null, "Date:  Tue Nov 29 10:36:43 2011 -0500", null, lines));
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
		changes.add(new Change(null, null, "Date:  Tue Nov 29 10:36:43 2011 -0500" , null, lines));
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
		GraphWriter graph = new GraphWriter();
		
		ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
		bubbles.add(new Bubble(0, 0, 0));
		graph.addDataForBubbles(bubbles);
		List<ArrayCollection> bubbleData = graph.getBubbleData();
		
		Assert.assertNotNull(bubbleData);
		Assert.assertEquals(1, bubbleData.size());
	}
}
