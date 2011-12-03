package ca.charland.bgm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ca.charland.bgm.change.Change;
import ca.charland.bgm.change.FileParser;
import ca.charland.bgm.graph.Bubble;
import ca.charland.bgm.graph.Graph;
import ca.charland.bgm.graph.GraphParser;
import ca.charland.bgm.graph.GraphWriter;

/**
 * The main entry point for the application.
 * 
 * @author mcharland
 */
public class Main {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main (String args[]) {
		
		// Gather change info.
		List<String> lines = FileAccessing.read(args[0]);		
		List<Change> changes = FileParser.changes(lines);		
		
		Map<String, ArrayList<Bubble>> bubbles = GraphParser.bubbles(changes);
		Graph g = new Graph(bubbles);
		
		GraphWriter graph = new GraphWriter();
		graph.loadRawFile();
		graph.addBubbles(g.getNormalizedBubbles());
		graph.createOutput();
		graph.writeFile();
	}
}
