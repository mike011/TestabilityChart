package ca.charland.bgm.graph;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ca.charland.bgm.change.Change;

public class GraphParser {

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

}
