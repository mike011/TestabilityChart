package ca.charland.bgm.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents the Bubble Graph mxml.
 * 
 * @author mcharland
 */
public class Graph {

	/** The _bubble series. */
	private List<BubbleSeries> _bubbleSeries;

	/** The _array collection. */
	private List<ArrayCollection> _arrayCollection;

	/**
	 * Instantiates a new graph.
	 * 
	 * @param bubbles
	 *            the bubbles
	 */
	public Graph() {
		_arrayCollection = new ArrayList<ArrayCollection>();
		_bubbleSeries = new ArrayList<BubbleSeries>();
	}

	// /**
	// * Set the date range from 0 to 100.
	// */
	// private void normalizeX() {
	//
	// // First find mins and maxes
	// float min = bubbles.get(0).getX();
	// float max = bubbles.get(0).getX();
	// for (Bubble b : bubbles) {
	// min = Math.min(min, b.getX());
	// max = Math.max(max, b.getX());
	// }
	//
	// // Normalizing.
	// for (Bubble b : bubbles) {
	// b.normalizeX(min, max);
	// }
	// }
	//
	// /**
	// * Set the date range from 0 to 50.
	// */
	// private void normalizeSize() {
	//
	// // First find mins and maxes
	// float min = bubbles.get(0).getSize();
	// float max = bubbles.get(0).getSize();
	// for (Bubble b : bubbles) {
	// min = Math.min(min, b.getSize());
	// max = Math.max(max, b.getSize());
	// }
	//
	// // Normalizing.
	// for (Bubble b : bubbles) {
	// b.normalizeSize(min, max);
	// }
	// }

	/**
	 * Normalizes the bubble data.
	 */
	public void normalizeBubbleData() {
		// normalizeX();
		// normalizeSize();
	}

	/**
	 * Adds the data for bubbles.
	 * 
	 * @param i
	 *            The index to identify the element.
	 * 
	 * @param bubbles
	 *            the bubbles
	 */
	void addDataForBubbles(int i, ArrayList<Bubble> bubbles) {
		ArrayCollection ac = new ArrayCollection(i);
		for (Bubble bubble : bubbles) {
			ac.add(bubble);
		}
		_arrayCollection.add(ac);
	}

	/**
	 * Gets all the bubble data.
	 * 
	 * @return the bubble data
	 */
	public List<ArrayCollection> getAllBubbleData() {
		return _arrayCollection;
	}

	/**
	 * Adds the bubbles.
	 * 
	 * @param changes
	 *            the changes
	 */
	public void addBubbles(Map<String, ArrayList<Bubble>> changes) {
		if (changes == null) {
			return;
		}
		int x = 0;
		for (String key : changes.keySet()) {
			addDataForBubbles(x, changes.get(key));
			_bubbleSeries.add(new BubbleSeries(x));
			++x;
		}
	}

	/**
	 * Gets all bubble series.
	 * 
	 * @return All bubble series
	 */
	public List<BubbleSeries> getAllBubbleSeries() {
		return _bubbleSeries;
	}

}
