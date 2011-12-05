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
	private List<BubbleSeries> _bubbleSeries;

	/** The output. */
	private List<String> _out;

	/** The Constant NEW_LINE. */
	static final String NEW_LINE = "\r\n";

	/**
	 * Instantiates a new graph writer.
	 */
	public GraphWriter() {
		_arrayCollection = new ArrayList<ArrayCollection>();
		_bubbleSeries = new ArrayList<BubbleSeries>();
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
	 * Adds the bubbles.
	 * 
	 * @param changes
	 *            the changes
	 */
	public void addBubbles(Map<String, ArrayList<Bubble>> changes) {
		if (changes == null) {
			return;
		}
		int x = 0;
		for (String key : changes.keySet()) {
			addDataForBubbles(x, changes.get(key));
			_bubbleSeries.add(new BubbleSeries(x));
			++x;
		}
	}

	/**
	 * Adds the data for bubbles.
	 * @param i The index to identify the element.
	 * 
	 * @param bubbles
	 *            the bubbles
	 */
	void addDataForBubbles(int i, ArrayList<Bubble> bubbles) {
		ArrayCollection ac = new ArrayCollection(i);
		for (Bubble bubble : bubbles) {
			ac.add(bubble);
		}
		_arrayCollection.add(ac);
	}

	/**
	 * Gets all the bubble data.
	 * 
	 * @return the bubble data
	 */
	public List<ArrayCollection> getAllBubbleData() {
		return _arrayCollection;
	}

	/**
	 * Format.
	 * 
	 * @param x
	 *            the x
	 * @return the string
	 */
	private String format(float x) {
		StringBuilder sb = new StringBuilder();
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
		for (String line : _raw) {
			if (line.startsWith("{1}")) {
				for (ArrayCollection ac : _arrayCollection) {
					_out.add(ac.toString() + NEW_LINE);
				}
			} else if (line.startsWith("{2}")) {
				for (BubbleSeries bc : _bubbleSeries) {
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
	 * Gets all bubble series.
	 * 
	 * @return All bubble series
	 */
	public List<BubbleSeries> getAllBubbleSeries() {
		return _bubbleSeries;
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
