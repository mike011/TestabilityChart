package ca.charland.bgm.graph;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

/**
 * Data that will be printed to the mxml.
 * 
 * @author mcharland
 */
public class ArrayCollection {

	/** The elements. */
	List<String> elements;
	
	/** The Constant NEW_LINE. */
	private static final String NEW_LINE = "\r\n";

	/**
	 * Instantiates a new array collection.
	 */
	ArrayCollection() {
		elements = new ArrayList<String>();
	}

	/**
	 * Adds the.
	 *
	 * @param bubble the bubble
	 */
	public void add(Bubble bubble) {
		StringBuffer bubblesData = new StringBuffer();
		bubblesData.append("{\"Date\":").append(format(bubble.getX()));
		bubblesData.append(", \"Coverage\":").append(format(bubble.getY()));
		bubblesData.append(", \"Size\":").append(format(bubble.getSize()));
		bubblesData.append("}");
		elements.add(bubblesData.toString());
	}

	/**
	 * Format.
	 *
	 * @param x the x
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
		return elements;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer expected = new StringBuffer();
		expected.append("[Bindable]").append(NEW_LINE);
		expected.append("private var s1:ArrayCollection = new ArrayCollection( [\r\n");
		for(String s : elements) {
			expected.append(s).append(NEW_LINE);
		}
		expected.append("]);").append(NEW_LINE);
		return expected.toString();
	}
}
