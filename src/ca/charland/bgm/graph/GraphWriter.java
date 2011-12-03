package ca.charland.bgm.graph;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import ca.charland.bgm.FileAccessing;

/**
 * Creates the mxml file.
 * 
 * @author mcharland
 */
public class GraphWriter {

	/** The _raw. */
	private List<String> _raw;
	
	/** The _array collection. */
	private List<ArrayCollection> _arrayCollection;
	
	/** The _bubble series. */
	private List<String> _bubbleSeries;
	
	/** The _out. */
	private StringBuffer _out;

	/**
	 * Instantiates a new graph writer.
	 */
	public GraphWriter() {
		_arrayCollection = new ArrayList<ArrayCollection>();
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
	 * Adds the bubbles.
	 *
	 * @param changes the changes
	 */
	public void addBubbles(Map<String, ArrayList<Bubble>> changes) {
		if (changes == null) {
			return;
		}
		

	}

	/**
	 * Adds the data for bubbles.
	 *
	 * @param bubbles the bubbles
	 */
	public void addDataForBubbles(ArrayList<Bubble> bubbles) {
		ArrayCollection ac = new ArrayCollection();
		for(Bubble bubble : bubbles) {
			ac.add(bubble);
		}
		_arrayCollection.add(ac);
	}

	/**
	 * Gets the bubble data.
	 *
	 * @return the bubble data
	 */
	public List<ArrayCollection> getBubbleData() {
		return _arrayCollection;
	}

	/**
	 * Format.
	 *
	 * @param x the x
	 * @return the string
	 */
	private String format(float x) {
		StringBuilder sb = new StringBuilder();
		// Send all output to the Appendable object sb
		Formatter formatter = new Formatter(sb, Locale.US);
		formatter.format("%.2f", x);
		return sb.toString();
	}

	/**
	 * Creates the output.
	 *
	 * @return true, if successful
	 */
	public boolean createOutput() {
		_out = new StringBuffer();
		for (String line : _raw) {
			if (line.contains("{1}")) {
				for(ArrayCollection ac: _arrayCollection)
				line = ac.toString();
			}
			_out.append(line);
			_out.append("\r\n");
		}
		return _out.length() != 0;
	}

	/**
	 * Write file.
	 *
	 * @return true, if successful
	 */
	public boolean writeFile() {

		String out = "";
		if (_out != null) {
			out = _out.toString();

			FileAccessing.write("src/res/graph.mxml", out);

			return true;
		}

		return false;
	}

}
