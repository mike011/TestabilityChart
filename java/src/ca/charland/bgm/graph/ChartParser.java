package ca.charland.bgm.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ca.charland.bgm.change.Change;

/**
 * Parses data into context for the graph.
 * 
 * @author mcharland
 */
public class ChartParser {

	/**
	 * Gets the Bubbles from the changes.
	 * 
	 * @param changes
	 *            the changes
	 * @param types
	 *            the types
	 * @param url The URL to link.
	 * @return The map which contains keys of authors and lists of there bubbles.
	 */
	public static Map<String, ArrayList<Bubble>> bubbles(List<? extends Change> changes, List<String> types, String url) {

		Map<String, ArrayList<Bubble>> series = new TreeMap<String, ArrayList<Bubble>>();

		for (Change change : changes) {
			float coverage = change.getCoverage(types);
			float linesCovered = change.getLinesCovered(types);
			Bubble bubble = new Bubble(change.getDate(), coverage, linesCovered, change.getCommit(), url);

			String author = change.getAuthor();
			if (!series.containsKey(author)) {
				series.put(author, new ArrayList<Bubble>());
			}
			series.get(author).add(bubble);
		}
		return series;
	}

	/**
	 * Set the date range from 0 to 50.
	 * 
	 * @param bubbles
	 *            The bubbles to normalise.
	 */
	private static void normaliseSize(Map<String, ArrayList<Bubble>> bubbles) {

		// First find minimums and maximums
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
		normaliseSize(bubbles);
	}
}