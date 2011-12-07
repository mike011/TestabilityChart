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
			_bubbleSeries.add(new BubbleSeries(x, key));
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
