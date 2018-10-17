package ca.charland.bgm.change;

import java.util.List;

/**
 * For files, holds one line of info from the change.
 * 
 * @author mcharland
 */
public class Line {

	private final String removed;
	private final String added;
	protected final String file;

	public Line(String added, String removed, String file) {
		this.added = added;
		this.removed = removed;
		String name = "";
		if (file != null) {
			name = file.trim();
		}
		this.file = name;
	}

	public int getTestDiff(List<String> types) {
		int total = 0;
		if (!isSource(types)) {
			total = getDifference();
		}
		return total;
	}

	public int getSourceDifference(List<String> types) {
		int total = 0;
		if (isSource(types)) {
			total = getDifference();
		}
		return total;
	}

	private int getDifference() {
		int total = 0;
		if (!removed.equals("-")) {
			total += Integer.parseInt(removed);
		}

		if (!added.equals("-")) {
			total += Integer.parseInt(added);
		}

		return total;
	}

	public boolean isValid(List<String> types) {

		boolean typeFound = false;
		for (String type : types) {
			if (file.endsWith(type)) {
				typeFound = true;
				break;
			}
		}
		return typeFound && !(removed.equals("-") && added.equals("-"));
	}

	public boolean isSource(List<String> types) {
		return isValid(types) && !file.toLowerCase().contains("test");
	}

	@Override
	public String toString() {
		return file;
	}
}
