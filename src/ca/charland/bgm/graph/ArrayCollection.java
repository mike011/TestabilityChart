package ca.charland.bgm.graph;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

/**
 * Data that will be printed to the MXML.
 * 
 * @author mcharland
 */
public class ArrayCollection {

	/** The number. */
	private final int _number;

	/** The elements. */
	private final List<String> _elements;

	/**
	 * Instantiates a new array collection.
	 */
	ArrayCollection(int number) {
		_number = number;
		_elements = new ArrayList<String>();
	}

	/**
	 * Adds the.
	 * 
	 * @param bubble
	 *            the bubble
	 */
	public void add(Bubble bubble) {
		StringBuffer bubblesData = new StringBuffer();
		bubblesData.append("{\"Date\":").append(format(bubble.getX()));
		bubblesData.append(", \"Coverage\":").append(format(bubble.getY()));
		bubblesData.append(", \"Size\":").append(format(bubble.getSize()));
		bubblesData.append("}");
		_elements.add(bubblesData.toString());
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
	 * Gets the elements.
	 * 
	 * @return the elements
	 */
	public List<String> getElements() {
		return _elements;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		StringBuffer expected = new StringBuffer();
		expected.append("[Bindable]").append(GraphWriter.NEW_LINE);
		expected.append("private var s");
		expected.append(_number);
		expected.append(":ArrayCollection = new ArrayCollection( [").append(GraphWriter.NEW_LINE);
		int current = 0;
		for (String element : _elements) {
			expected.append(element);
			++current;
			if (current < _elements.size()) {
				expected.append(',');
			}
			expected.append(GraphWriter.NEW_LINE);
		}
		expected.append("]);").append(GraphWriter.NEW_LINE);
		return expected.toString();
	}
}
