package ca.charland.bgm.graph;

/**
 * Holds the MXML representation of a BubbleSeries.
 * 
 * @author mcharland
 * 
 */
public class BubbleSeries {

	/** The number. */
	private int number;
	
	/** The author. */
	private final String author;

	/**
	 * Instantiates a new bubble series.
	 * 
	 * @param number
	 *            The number that ties it into the array collection.
	 * @param author
	 *            The author of the change.
	 */
	BubbleSeries(int number, String author) {
		this.number = number;
		this.author = author;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("<mx:BubbleSeries\r\n");
		result.append("dataProvider=\"{s");
		result.append(number);
		result.append("}\"\r\n");
		result.append("displayName=\"");
		result.append(author);
		result.append("\"\r\n");
		result.append("xField=\"Date\"\r\n");
		result.append("yField=\"Coverage\"\r\n");
		result.append("radiusField=\"Size\"\r\n");
		result.append("/>\r\n");
		return result.toString();
	}
}
