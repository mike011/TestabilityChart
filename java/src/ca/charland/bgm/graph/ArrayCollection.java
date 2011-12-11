package ca.charland.bgm.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Data that will be printed to the MXML.
 * 
 * @author mcharland
 */
public class ArrayCollection {

	/** The number. */
	private final int _number;

	/** The elements. */
	private final List<Bubble> _elements;

	/**
	 * Instantiates a new array collection.
	 */
	ArrayCollection(int number) {
		_number = number;
		_elements = new ArrayList<Bubble>();
	}

	/**
	 * Adds the.
	 * 
	 * @param bubble
	 *            the bubble
	 */
	public void add(Bubble bubble) {
		_elements.add(bubble);
	}

	/**
	 * Gets the elements.
	 * 
	 * @return the elements
	 */
	public List<Bubble> getElements() {
		return _elements;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		StringBuffer expected = new StringBuffer();
		String tabs = "\t\t\t";
		expected.append(tabs).append("[Bindable]").append(Graph.NEW_LINE);
		expected.append(tabs).append("private var s");
		expected.append(_number);
		expected.append(":ArrayCollection = new ArrayCollection( [").append(Graph.NEW_LINE);
		int current = 0;
		for (Bubble element : _elements) {
			expected.append(tabs).append('\t').append(element);
			++current;
			if (current < _elements.size()) {
				expected.append(',');
			}
			expected.append(Graph.NEW_LINE);
		}
		expected.append(tabs).append("]);").append(Graph.NEW_LINE);
		return expected.toString();
	}
}
