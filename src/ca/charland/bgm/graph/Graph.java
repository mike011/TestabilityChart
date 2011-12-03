package ca.charland.bgm.graph;

import java.util.ArrayList;
import java.util.Map;

/**
 * Represents the Bubble Graph mxml.
 * 
 * @author mcharland
 */
public class Graph {

	/** The bubbles. */
	private final Map<String, ArrayList<Bubble>> bubbles;

	/**
	 * Instantiates a new graph.
	 *
	 * @param bubbles the bubbles
	 */
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

	/**
 * Gets the normalized bubbles.
 *
 * @return the normalized bubbles
 */
public Map<String, ArrayList<Bubble>> getNormalizedBubbles() {
		//normalizeX();
		//normalizeSize();
		return bubbles;
	}

}
