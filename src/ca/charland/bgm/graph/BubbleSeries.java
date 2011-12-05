package ca.charland.bgm.graph;

/**
 * Holds the MXML representation of a BubbleSeries.
 * 
 * @author mcharland
 * 
 */
public class BubbleSeries {

	/** The number. */
	private int _number;

	/**
	 * Instantiates a new bubble series.
	 * 
	 * @param number
	 *            the number
	 */
	BubbleSeries(int number) {
		_number = number;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("<mx:BubbleSeries\r\n");
		result.append("dataProvider=\"{s");
		result.append(_number);
		result.append("}\"\r\n");
		result.append("displayName=\"series");
		result.append(_number);
		result.append("\"\r\n");
		result.append("xField=\"Date\"\r\n");
		result.append("yField=\"Coverage\"\r\n");
		result.append("radiusField=\"Size\"\r\n");
		result.append("/>\r\n");
		return result.toString();
	}
}
