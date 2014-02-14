package ca.charland.bgm;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import ca.charland.bgm.change.Change;
import ca.charland.bgm.change.GitFileParser;
import ca.charland.bgm.change.IntegrityFileParser;
import ca.charland.bgm.graph.Bubble;
import ca.charland.bgm.graph.Chart;
import ca.charland.bgm.graph.ChartParser;

/**
 * The main entry point for the application.
 * 
 * @author mcharland
 */
public class Main {

	/** The current version number. */
	public static final String VERSION = "0.17";

	/** The log file. */
	private String logFile;

	/** The types of source files to look at. */
	private ArrayList<String> types;

	/** The properties file. */
	private String propertiesFile = "common.properties";

	/** The base URL. */
	private String baseURL;

	/** Are you debugging?. */
	private boolean debug;

	/** The title of project. */
	private String projectName;

	private String repoType;

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String args[]) {
		Main main = new Main();
		main.setup();
		main.go();
	}

	void setup() {
		checkPropertiesFile();
	}

	private void checkPropertiesFile() {

		// See if the project folder has been set by system argument.
		Properties sys = System.getProperties();
		String projectFolder = sys.getProperty("PROJECT_FOLDER", "");

		// Try to load the properties file.
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(projectFolder + propertiesFile));
		} catch (IOException e) {
			System.err.println("File not found: " + e.getMessage() + ". Relying on arguments passed in.");
		}

		// Was the properties file found?
		if (properties != null) {
			String logFileProperty = properties.getProperty("log.file");
			if(logFileProperty != null) {
				logFile = projectFolder + logFileProperty;
			}
			String typesProps = properties.getProperty("types");
			if (typesProps != null) {
				types = new ArrayList<String>();
				for (String type : typesProps.split(" ")) {
					types.add(type);
				}
			}
			baseURL = properties.getProperty("base.url");
			debug = Boolean.parseBoolean(properties.getProperty("debug"));
			projectName = properties.getProperty("project.name");
			repoType = properties.getProperty("repo.type");
		}
	}

	/**
	 * Go.
	 */
	void go() {

		List<? extends Change> changes = gatherChangeInfo();

		// Create the bubbles.
		Map<String, ArrayList<Bubble>> bubbles = ChartParser.bubbles(changes, getTypes(), baseURL);
		ChartParser.normaliseBubbleData(bubbles);

		if (debug) {
			// Debug
			for (String person : bubbles.keySet()) {
				System.out.println(person);
				for (Bubble b : bubbles.get(person)) {
					System.out.println(b);
				}
			}
		}

		// Make the graph.
		Chart graph = new Chart(projectName);
		graph.addBubbles(bubbles);
		graph.show();
	}

	private List<? extends Change> gatherChangeInfo() {
		if(getRepoType().equals("git")) {
			List<String> lines = FileAccessing.read(getLogFile());
			List<? extends Change> changes = new GitFileParser().changes(lines, getTypes());
			return changes;
		} else if(getRepoType().equals("integrity")) {
			IntegrityFileParser ifp = new IntegrityFileParser(getLogFile());
			ifp.load();
			return ifp.parse();
		} 
		throw new IllegalArgumentException("Invalid repo type " + getRepoType());
    }

	/**
	 * Gets the log file.
	 * 
	 * @return the log file
	 */
	String getLogFile() {
		return logFile;
	}

	/**
	 * Gets the file types.
	 * 
	 * @return the types
	 */
	List<String> getTypes() {
		return types;
	}

	/**
	 * Sets the properties file.
	 * 
	 * @param file
	 *            the new properties file
	 */
	void setPropertiesFile(String file) {
		propertiesFile = file;
	}

	/**
	 * Gets the base URL.
	 * 
	 * @return the base URL.
	 */
	String getBaseURL() {
		return baseURL;
	}

	/**
	 * Checks if you are debugging.
	 * 
	 * @return true, if you are debugging
	 */
	boolean isDebugging() {
		return debug;
	}

	/**
	 * Gets the name of the project.
	 * 
	 * @return the title
	 */
	String getProjectName() {
		return projectName;
	}

	public String getRepoType() {
		if(null == repoType) {
			return "git";
		}
		return repoType;
    }
}