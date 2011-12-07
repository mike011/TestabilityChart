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

	/**
	 * Set the date range from 0 to 100.
	 */
	private static void normaliseX(Map<String, ArrayList<Bubble>> bubbles) {

		// First find mins and maxes
		ArrayList<Bubble> arrayList = bubbles.get(bubbles.keySet().toArray()[0]);
		Bubble bubble = arrayList.get(0);
		float min = bubble.getDate();
		float max = bubble.getDate();
		for (String key : bubbles.keySet()) {
			for (Bubble b : bubbles.get(key)) {
				min = Math.min(min, b.getDate());
				max = Math.max(max, b.getDate());
			}
		}

		// Normalising.
		for (String key : bubbles.keySet()) {
			for (Bubble b : bubbles.get(key)) {
				b.normaliseX(min, max);
			}
		}
	}

	/**
	 * Set the date range from 0 to 50.
	 */
	private static void normaliseSize(Map<String, ArrayList<Bubble>> bubbles) {

		// First find mins and maxes
		ArrayList<Bubble> arrayList = bubbles.get(bubbles.keySet().toArray()[0]);
		Bubble bubble = arrayList.get(0);
		float min = bubble.getSize();
		float max = bubble.getSize();
		for (String key : bubbles.keySet()) {
			for (Bubble b : bubbles.get(key)) {
				min = Math.min(min, b.getSize());
				max = Math.max(max, b.getSize());
			}
		}

		// Normalising.
		for (String s : bubbles.keySet()) {
			for (Bubble b : bubbles.get(s)) {
				b.normaliseSize(min, max);
			}
		}
	}

	/**
	 * Normalises the bubble data.
	 * 
	 * @param bubbles
	 *            the bubbles
	 */
	public static void normaliseBubbleData(Map<String, ArrayList<Bubble>> bubbles) {
		normaliseX(bubbles);
		normaliseSize(bubbles);
	}
}