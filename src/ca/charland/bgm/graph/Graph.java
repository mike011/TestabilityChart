package ca.charland.bgm.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Graph {

	private final Map<String, ArrayList<Bubble>> bubbles;

	public Graph(Map<String, ArrayList<Bubble>> bubbles) {
		this.bubbles = bubbles;
	}

//	/**
//	 * Set the date range from 0 to 100.
//	 */
//	private void normalizeX() {
//
//		// First find mins and maxes
//		float min = bubbles.get(0).getX();
//		float max = bubbles.get(0).getX();
//		for (Bubble b : bubbles) {
//			min = Math.min(min, b.getX());
//			max = Math.max(max, b.getX());
//		}
//		
//		// Normalizing.
//		for (Bubble b : bubbles) {
//			b.normalizeX(min, max);
//		}
//	}
//	
//	/**
//	 * Set the date range from 0 to 50.
//	 */
//	private void normalizeSize() {
//
//		// First find mins and maxes
//		float min = bubbles.get(0).getSize();
//		float max = bubbles.get(0).getSize();
//		for (Bubble b : bubbles) {
//			min = Math.min(min, b.getSize());
//			max = Math.max(max, b.getSize());
//		}
//		
//		// Normalizing.
//		for (Bubble b : bubbles) {
//			b.normalizeSize(min, max);
//		}
//	}

	public Map<String, ArrayList<Bubble>> getNormalizedBubbles() {
		//normalizeX();
		//normalizeSize();
		return bubbles;
	}

}
