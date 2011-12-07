package ca.charland.bgm.graph;

import java.util.Formatter;
import java.util.Locale;

/**
 * A pretty Bubble.
 * 
 * @author mcharland
 */
public class Bubble {

	/** The x. */
	private float date;

	/** The y. */
	private final float coverage;

	/** The size. */
	private float size;

	/**
	 * Instantiates a new bubble.
	 * 
	 * @param date
	 *            the date
	 * @param coverage
	 *            the coverage
	 * @param linesCovered
	 *            The amount of lines covered.
	 */
	Bubble(float date, float coverage, float linesCovered) {
		this.date = date;
		this.coverage = coverage;
		this.size = linesCovered;
	}

	/**
	 * Gets the x float.
	 * 
	 * @return the x
	 */
	public float getDate() {
		return date;
	}

	/**
	 * Normalise x.
	 * 
	 * @param min
	 *            the min
	 * @param max
	 *            the max
	 */
	public void normaliseX(float min, float max) {
		date = (date - min) / max * 100;
	}

	/**
	 * Gets the y.
	 * 
	 * @return the y
	 */
	public float getCoverage() {
		return coverage * 100;
	}

	/**
	 * Gets the y.
	 * 
	 * @return the y
	 */
	public String getCoverageString() {
		StringBuffer out = new StringBuffer();
		Formatter formatter = new Formatter(out, Locale.US);
		formatter.format("%.0f", getCoverage());
		out.append('%');
		return out.toString();
	}

	/**
	 * Gets the size.
	 * 
	 * @return the size
	 */
	public float getSize() {
		return size;
	}

	/**
	 * Normalise size.
	 * 
	 * @param min
	 *            the min
	 * @param max
	 *            the max
	 */
	public void normaliseSize(float min, float max) {
		int MAX_BUBBLE_SIZE = 50;
		size = Math.min(MAX_BUBBLE_SIZE, (size - min) / max * MAX_BUBBLE_SIZE + 1);
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

	/** {@inheritDoc}  */
	@Override
	public String toString() {
		StringBuffer bubblesData = new StringBuffer();
		bubblesData.append("{\"Date\":\"").append(format(getDate())).append("\"");
		bubblesData.append(", \"Coverage\":\"").append(getCoverageString()).append("\"");
		bubblesData.append(", \"Size\":\"").append(format(getSize())).append("\"");
		bubblesData.append("}");
		return bubblesData.toString();
	}
}
