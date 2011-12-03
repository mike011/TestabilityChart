package ca.charland.bgm.graph;

public class Bubble {

	private float x;
	private final float y;
	private float size;

	Bubble(float date, float coverage, float rawSize) {
		this.x = date;
		this.y = coverage;
		this.size = rawSize;
	}

	public float getX() {
		return x;
	}

	public void normalizeX(float min, float max) {
		x = (x - min) / max * 100000;
	}

	public float getY() {
		return y;
	}

	public float getSize() {
		return size;
	}

	public void normalizeSize(float min, float max) {
		size = (size - min) / max * 50 + 1;

	}
}
