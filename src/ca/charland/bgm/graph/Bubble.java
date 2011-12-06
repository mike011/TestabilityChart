package ca.charland.bgm.graph;

/**
 * A pretty Bubble.
 * 
 * @author mcharland
 */
public class Bubble {

	/** The x. */
	private float x;
	
	/** The y. */
	private final float y;
	
	/** The size. */
	private float size;

	/**
	 * Instantiates a new bubble.
	 *
	 * @param date the date
	 * @param coverage the coverage
	 * @param rawSize the raw size
	 */
	Bubble(float date, float coverage, float rawSize) {
		this.x = date;
		this.y = coverage;
		this.size = rawSize;
	}

	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public float getX() {
		return x;
	}

	/**
	 * Normalize x.
	 *
	 * @param min the min
	 * @param max the max
	 */
	public void normalizeX(float min, float max) {
		x = (x - min) / max * 100;
	}

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public float getY() {
		return y;
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public float getSize() {
		return size;
	}

	/**
	 * Normalize size.
	 *
	 * @param min the min
	 * @param max the max
	 */
	public void normalizeSize(float min, float max) {
		size = (size - min) / max * 50 + 1;

	}
}
