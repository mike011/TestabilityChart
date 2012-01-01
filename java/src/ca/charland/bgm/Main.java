package ca.charland.bgm;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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

	/** The properties file. */
	private String _propertiesFile = "common.properties";

	/** The base URL. */
	private String _baseURL;

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String args[]) {
		Main main = new Main();
		main.setup(args);
		main.go();
	}

	/**
	 * Setup.
	 * 
	 * @param args
	 *            the new up
	 */
	void setup(String[] args) {
		checkPropertiesFile();
		parseArgs(args);
	}

	/**
	 * Check properties file.
	 */
	private void checkPropertiesFile() {

		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(_propertiesFile));
		} catch (IOException e) {
			System.err.println("File not found: " + e.getMessage() + ". Relying on arguments passed in.");
		}

		if (properties != null) {
			_logFile = properties.getProperty("log.file");
			String types = properties.getProperty("types");
			if (types != null) {
				_types = new ArrayList<String>();
				for (String type : types.split(" ")) {
					_types.add(type);
				}
			}
			_baseURL = properties.getProperty("base.url");
		}
	}

	/**
	 * Go.
	 */
	void go() {

		// Gather change info.
		List<String> lines = FileAccessing.read(getLogFile());
		List<Change> changes = FileParser.changes(lines, getTypes());

		// Create the bubbles.
		Map<String, ArrayList<Bubble>> bubbles = ChartParser.bubbles(changes, getTypes(), _baseURL);
		ChartParser.normaliseBubbleData(bubbles);

		// Debug
		for (String person : bubbles.keySet()) {
			System.out.println(person);
			for (Bubble b : bubbles.get(person)) {
				System.out.println(b);
			}
		}

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
		if (args != null) {
			if (args.length > 0 && args[0].length() > 0) {
				_logFile = args[0];
			}
			if (args.length > 1) {
				_types = new ArrayList<String>();
				for (int x = 1; x < args.length; x++) {
					_types.add(args[x]);
				}
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

	/**
	 * Sets the properties file.
	 * 
	 * @param file
	 *            the new properties file
	 */
	void setPropertiesFile(String file) {
		_propertiesFile = file;
	}

	/**
	 * Gets the base URL.
	 * 
	 * @return the base URL.
	 */
	String getBaseURL() {
		return _baseURL;
	}
}