package ca.charland.bgm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jfree.ui.RefineryUtilities;

import ca.charland.bgm.change.Change;
import ca.charland.bgm.change.FileParser;
import ca.charland.bgm.flash.Bubble;
import ca.charland.bgm.flash.Graph;
import ca.charland.bgm.flash.GraphParser;
import ca.charland.bgm.flash.GraphWriter;
import ca.charland.bgm.graph.BubbleChart;

/**
 * The main entry point for the application.
 * 
 * @author mcharland
 */
public class Main {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String args[]) {

		// Gather change info.
		List<String> lines = FileAccessing.read(args[0]);
		List<Change> changes = FileParser.changes(lines);

		Map<String, ArrayList<Bubble>> bubbles = GraphParser.bubbles(changes);
		GraphParser.normaliseBubbleData(bubbles);

		Graph graph = new Graph();
		graph.addBubbles(bubbles);

		GraphWriter writer = new GraphWriter(graph);
		writer.loadRawFile();
		writer.createOutput();
		writer.writeFile();
		
		BubbleChart bubblechartdemo1 = new BubbleChart();
		bubblechartdemo1.pack();
		RefineryUtilities.centerFrameOnScreen(bubblechartdemo1);
		bubblechartdemo1.setVisible(true);
	}
}
