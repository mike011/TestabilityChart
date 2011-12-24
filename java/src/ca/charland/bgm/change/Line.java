package ca.charland.bgm.change;

import java.util.List;

/**
 * For files, holds one line of info from the change.
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
	 * @param types
	 *            the types
	 * @return the test diff
	 */
	public int getTestDiff(List<String> types) {
		int total = 0;
		if (!isSource(types)) {
			total = getDiff();
		}
		return total;
	}

	/**
	 * Gets the src diff.
	 * 
	 * @param types
	 *            the types
	 * @return the src diff
	 */
	public int getSrcDiff(List<String> types) {
		int total = 0;
		if (isSource(types)) {
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
	 * @param types
	 *            the types
	 * @return true, if is valid
	 */
	public boolean isValid(List<String> types) {

		boolean typeFound = false;
		for (String type : types) {
			if (file.trim().endsWith(type)) {
				typeFound = true;
				break;
			}
		}
		return typeFound && !(removed.equals("-") && added.equals("-"));
	}

	/**
	 * Checks if is source.
	 * 
	 * @param types
	 *            the types
	 * @return true, if is source
	 */
	public boolean isSource(List<String> types) {
		return isValid(types) && !file.contains("test");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return file;
	}
}
