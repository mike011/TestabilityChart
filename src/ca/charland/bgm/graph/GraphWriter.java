package ca.charland.bgm.graph;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import ca.charland.bgm.FileAccessing;

public class GraphWriter {

	private List<String> _raw;
	private List<ArrayCollection> _arrayCollection;
	private List<String> _bubbleSeries;
	private StringBuffer _out;

	public GraphWriter() {
		_arrayCollection = new ArrayList<ArrayCollection>();
	}

	public boolean loadRawFile() {
		_raw = FileAccessing.read("src/res/raw_graph.mxml");
		return _raw != null;
	}

	public void addBubbles(Map<String, ArrayList<Bubble>> changes) {
		if (changes == null) {
			return;
		}
		

	}

	public void addDataForBubbles(ArrayList<Bubble> bubbles) {
		ArrayCollection ac = new ArrayCollection();
		for(Bubble bubble : bubbles) {
			ac.add(bubble);
		}
		_arrayCollection.add(ac);
	}

	public List<ArrayCollection> getBubbleData() {
		return _arrayCollection;
	}

	private String format(float x) {
		StringBuilder sb = new StringBuilder();
		// Send all output to the Appendable object sb
		Formatter formatter = new Formatter(sb, Locale.US);
		formatter.format("%.2f", x);
		return sb.toString();
	}

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
