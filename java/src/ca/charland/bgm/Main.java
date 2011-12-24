package ca.charland.bgm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ca.charland.bgm.change.Change;
import ca.charland.bgm.change.FileParser;
import ca.charland.bgm.graph.Bubble;
import ca.charland.bgm.graph.Chart;
import ca.charland.bgm.graph.ChartParser;

/**
 * The main entry point for the application.
 * 
 * @author mcharland
 */
public class Main {

	/** The log file. */
	private String _logFile;

	/** The types of source files to look at. */
	private ArrayList<String> _types;

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String args[]) {
		new Main(args).go();
	}

	/**
	 * Instantiates a new main.
	 * 
	 * @param args
	 *            the args
	 */
	public Main(String[] args) {
		parseArgs(args);
	}

	/**
	 * Go.
	 */
	void go() {

		// Gather change info.
		List<String> lines = FileAccessing.read(getLogFile());
		List<Change> changes = FileParser.changes(lines, getTypes());

		// Create the bubbles.
		Map<String, ArrayList<Bubble>> bubbles = ChartParser.bubbles(changes, getTypes());
		ChartParser.normaliseBubbleData(bubbles);

		// Make the graph.
		Chart graph = new Chart();
		graph.addBubbles(bubbles);
		graph.show();
	}

	/**
	 * Parses the arguments.
	 * 
	 * @param args
	 *            the arguments.
	 */
	private void parseArgs(String[] args) {
		_logFile = args[0];
		if (args.length > 1) {
			_types = new ArrayList<String>();
			for (int x = 1; x < args.length; x++) {
				_types.add(args[x]);
			}
		}
	}

	/**
	 * Gets the log file.
	 * 
	 * @return the log file
	 */
	String getLogFile() {
		return _logFile;
	}

	/**
	 * Gets the file types.
	 * 
	 * @return the types
	 */
	List<String> getTypes() {
		return _types;
	}
}
