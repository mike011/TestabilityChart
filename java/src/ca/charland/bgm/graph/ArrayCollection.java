package ca.charland.bgm.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Data that will be printed to the MXML.
 * 
 * @author mcharland
 */
public class ArrayCollection {

	/** The elements. */
	private final List<Bubble> _elements;

	/**
	 * Instantiates a new array collection.
	 */
	ArrayCollection(int number) {
		_elements = new ArrayList<Bubble>();
	}

	/**
	 * Adds the.
	 * 
	 * @param bubble
	 *            the bubble
	 */
	public void add(Bubble bubble) {
		_elements.add(bubble);
	}

	/**
	 * Gets the elements.
	 * 
	 * @return the elements
	 */
	public List<Bubble> getElements() {
		return _elements;
	}
}
