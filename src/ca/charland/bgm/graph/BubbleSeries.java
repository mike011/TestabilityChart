package ca.charland.bgm.graph;

/**
 * Holds the mxml representation of a BubbleSeries.
 * 
 * @author mcharland
 * 
 */
public class BubbleSeries {
	
	/** The _number. */
	private int _number;

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
		result.append("displayName=\"series1\"\r\n");
		result.append("xField=\"x\"\r\n");
		result.append("yField=\"y\"\r\n");
		result.append("radiusField=\"r\"\r\n");
		result.append("/>\r\n");
		return result.toString();
	}
}
