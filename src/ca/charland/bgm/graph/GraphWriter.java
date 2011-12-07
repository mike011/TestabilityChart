package ca.charland.bgm.graph;

import java.util.ArrayList;
import java.util.List;

import ca.charland.bgm.FileAccessing;

/**
 * Creates the mxml file.
 * 
 * @author mcharland
 */
public class GraphWriter {

	/** The raw data from the MXML. */
	private List<String> _raw;

	/** The graph to write. */
	private Graph _graph;

	/** The output. */
	private List<String> _out;

	/** The Constant NEW_LINE. */
	static final String NEW_LINE = "\r\n";

	/**
	 * Instantiates a new graph writer.
	 * 
	 * @param g
	 *            The graph
	 */
	public GraphWriter(Graph g) {
		_graph = g;
		_out = new ArrayList<String>();
	}

	/**
	 * Load raw file.
	 * 
	 * @return true, if successful
	 */
	public boolean loadRawFile() {
		_raw = FileAccessing.read("src/res/raw_graph.mxml");
		return _raw != null;
	}

	/**
	 * Creates the output.
	 * 
	 * @return true, if successful
	 */
	public boolean createOutput() {
		for (String line : _raw) {
			if (line.startsWith("{1}")) {
				for (ArrayCollection ac : _graph.getAllBubbleData()) {
					_out.add(ac.toString() + NEW_LINE);
				}
			} else if (line.startsWith("{2}")) {
				for (BubbleSeries bc : _graph.getAllBubbleSeries()) {
					_out.add(bc.toString() + NEW_LINE);
				}
			} else {
				_out.add(line + NEW_LINE);
			}
		}
		return _out.size() != 0;
	}

	/**
	 * Write file.
	 * 
	 * @return true, if successful
	 */
	public boolean writeFile() {

		if (_out.size() > 0) {
			StringBuffer out = new StringBuffer();
			for (String line : _out) {
				out.append(line);
			}

			FileAccessing.write("src/res/graph.mxml", out.toString());

			return true;
		}

		return false;
	}

	/**
	 * Gets the generated MXML.
	 * 
	 * @return the generated MXML
	 */
	public List<String> getGeneratedMXML() {
		return _out;
	}
}
