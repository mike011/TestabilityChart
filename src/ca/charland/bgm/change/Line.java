package ca.charland.bgm.change;

/**
 * For files holds one line of info from the change.
 * 
 * @author mcharland
 */
public class Line {

	/** The removed. */
	private final String removed;

	/** The added. */
	private final String added;

	/** The file. */
	private final String file;

	/**
	 * Instantiates a new line.
	 * 
	 * @param added
	 *            the added
	 * @param removed
	 *            the removed
	 * @param file
	 *            the file
	 */
	public Line(String added, String removed, String file) {
		this.added = added;
		this.removed = removed;
		this.file = file;

	}

	/**
	 * Gets the test diff.
	 * 
	 * @return the test diff
	 */
	public int getTestDiff() {
		int total = 0;
		if (!isSource()) {
			total = getDiff();
		}
		return total;
	}

	/**
	 * Gets the src diff.
	 * 
	 * @return the src diff
	 */
	public int getSrcDiff() {
		int total = 0;
		if (isSource()) {
			total = getDiff();
		}
		return total;
	}

	/**
	 * Gets the diff.
	 * 
	 * @return the diff
	 */
	private int getDiff() {
		int total = 0;
		if (!removed.equals("-")) {
			total += Integer.parseInt(removed);
		}

		if (!added.equals("-")) {
			total += Integer.parseInt(added);
		}

		return total;
	}

	/**
	 * Checks if is valid.
	 * 
	 * @return true, if is valid
	 */
	public boolean isValid() {
		return !(removed.equals("-") && added.equals("-"));
	}

	/**
	 * Checks if is source.
	 * 
	 * @return true, if is source
	 */
	public boolean isSource() {
		return isValid() && !file.contains("test");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return file;
	}
}
