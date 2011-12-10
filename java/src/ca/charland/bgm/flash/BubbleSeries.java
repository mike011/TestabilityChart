package ca.charland.bgm.flash;

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
		String tabs = "\t\t\t\t";
		result.append(tabs).append("<mx:BubbleSeries\r\n");
		result.append(tabs).append('\t').append("dataProvider=\"{s");
		result.append(number);
		result.append("}\"\r\n");
		result.append(tabs).append('\t').append("displayName=\"");
		result.append(author);
		result.append("\"\r\n");
		result.append(tabs).append('\t').append("xField=\"Date\"\r\n");
		result.append(tabs).append('\t').append("yField=\"Coverage\"\r\n");
		result.append(tabs).append('\t').append("radiusField=\"Size\"\r\n");
		result.append(tabs).append("/>\r\n");
		return result.toString();
	}
}
