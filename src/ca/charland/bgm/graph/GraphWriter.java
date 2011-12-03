package ca.charland.bgm.graph;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import ca.charland.bgm.FileAccessing;

public class GraphWriter {

	private List<String> _raw;
	private List<String> _bubbleData;
	private List<String> _bubbleSeries;
	private StringBuffer _bubbles;
	private StringBuffer _out;

	public GraphWriter() {
		_bubbleData = new ArrayList<String>();
	}

	public boolean loadRawFile() {
		_raw = FileAccessing.read("src/res/raw_graph.mxml");
		return _raw != null;
	}

	public void addBubbles(Map<String, ArrayList<Bubble>> changes) {
		_bubbles = new StringBuffer();
		if (changes == null) {
			return;
		}

	}

	public void addDataForBubbles(ArrayList<Bubble> bubbles) {
		StringBuffer bubblesData = new StringBuffer();
		if (bubbles == null) {
			return;
		}
		for (int index = 0; index < bubbles.size(); index++) {
			Bubble bubble = bubbles.get(index);
			bubblesData.append("{Date:").append(format(bubble.getX()));
			bubblesData.append(", Coverage:").append(format(bubble.getY()));
			bubblesData.append(", Size:").append(format(bubble.getSize()));
			bubblesData.append("}");
			if (index + 1 < bubbles.size()) {
				bubblesData.append(",");
			}
			bubblesData.append("\r\n");
		}
		_bubbleData.add(bubblesData.toString());
	}

	public List<String> getBubbleData() {
		return _bubbleData;
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
			if (line.contains("{}")) {
				line = _bubbles.toString();
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
