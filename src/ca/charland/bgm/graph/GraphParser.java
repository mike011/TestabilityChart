package ca.charland.bgm.graph;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ca.charland.bgm.change.Change;

/**
 * Parses data into context for the graph.
 * 
 * @author mcharland
 */
public class GraphParser {

	/**
	 * Gets the Bubbles from the changes.
	 * 
	 * @param changes
	 *            the changes
	 * @return the map
	 */
	public static Map<String, ArrayList<Bubble>> bubbles(List<Change> changes) {

		Map<String, ArrayList<Bubble>> series = new TreeMap<String, ArrayList<Bubble>>();

		for (Change change : changes) {
			float coverage = change.getCoverage();
			Date date = change.getDate();
			float linesCovered = change.getLinesCovered();
			Bubble bubble = new Bubble(date.getTime(), coverage, linesCovered);

			String author = change.getAuthor();
			if (!series.containsKey(author)) {
				series.put(author, new ArrayList<Bubble>());
			}
			series.get(author).add(bubble);
		}
		return series;
	}

	// /**
	// * Set the date range from 0 to 100.
	// */
	// private void normalizeX(Map<String, ArrayList<Bubble>> bubbles) {
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
	/**
	 * Set the date range from 0 to 50.
	 */
//	private void normalizeSize(Map<String, ArrayList<Bubble>> bubbles) {
//
//		// First find mins and maxes
//		float min = _arrayCollection.get(0).getDate();
//		float max = _arrayCollection.get(0).getDate();
//		for (Bubble b : bubbles) {
//			min = Math.min(min, b.getSize());
//			max = Math.max(max, b.getSize());
//		}
//
//		// Normalizing.
//		for (Bubble b : _arrayCollection) {
//			b.normalizeSize(min, max);
//		}
//	}

	/**
	 * Normalizes the bubble data.
	 * 
	 * @param bubbles
	 *            the bubbles
	 */
	public static void normalizeBubbleData(
			Map<String, ArrayList<Bubble>> bubbles) {
		// // normalizeX();
//		normalizeSize();
	}

}
