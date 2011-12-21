package ca.charland.bgm.graph;

import java.util.ArrayList;
import java.util.Map;

import org.jfree.data.xy.DefaultXYZDataset;

/**
 * The Class MyXYZDataset.
 * 
 * @author mcharland
 */
class MyXYZDataset extends DefaultXYZDataset {

	/** Serial Version UID. */
	private static final long serialVersionUID = 8377539346221547744L;

	/** The bubbles. */
	private Map<String, ArrayList<Bubble>> bubbles;

	/**
	 * Instantiates a new my xyz dataset.
	 */
	MyXYZDataset() {
	}

	/**
	 * Sets the bubbles.
	 * 
	 * @param changes
	 *            the changes
	 */
	public void setBubbles(Map<String, ArrayList<Bubble>> changes) {
		bubbles = changes;
	}

	/**
	 * Gets the bubble.
	 * 
	 * @param seriesKey
	 *            the series key
	 * @param item
	 *            the item
	 * @return the bubble
	 */
	public Bubble getBubble(String seriesKey, int item) {
		return bubbles.get(seriesKey).get(item);
	}
}