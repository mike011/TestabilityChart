package ca.charland.bgm.change;

import java.util.ArrayList;
import java.util.List;

/**
 * Parses the contents from the file into changes.
 * 
 * @author mcharland
 */
public class FileParser {

	/**
	 * Changes.
	 * 
	 * @param lines
	 *            the lines
	 * @param types
	 *            The types of valid files to accept.
	 * @return the list
	 */
	public static List<Change> changes(List<String> lines, List<String> types) {
		List<Change> result = new ArrayList<Change>();

		String commit = null;
		String author = null;
		String date = null;
		String desc = null;
		List<Line> fileLines = new ArrayList<Line>();
		for (String line : lines) {
			if (line.length() > 0) {
				if (line.startsWith("commit")) {
					if (commit != null) {
						Change change = new Change(commit, author, date, desc, fileLines);
						if (change.isValid()) {
							result.add(change);
						}
						fileLines = new ArrayList<Line>();
					}
					commit = line;
				} else if (line.startsWith("Author:")) {
					author = line;
				} else if (line.startsWith("Date:")) {
					date = line;
				} else if (line.startsWith("    ")) {
					desc = line;
				} else if (line.startsWith("commit")) {
					commit = line;
				} else if (line.startsWith("Merge")) {
					// ignored.
				} else {
					Line lineObject = line(line);
					if (lineObject.isValid(types)) {
						fileLines.add(lineObject);
					}
				}
			}
		}

		Change change = new Change(commit, author, date, desc, fileLines);
		if (change.isValid()) {
			result.add(change);
		}

		return result;
	}

	/**
	 * Checks if the change is valid.
	 * 
	 * @param change
	 *            the change
	 * @param types
	 *            The valid file types.
	 * @return true, if is valid
	 */
	static boolean isChangeValid(Change change, List<String> types) {
		return change.isValid();
	}

	/**
	 * Parses the string into the object Line.
	 * 
	 * @param line
	 *            the string
	 * @return the Line object.
	 */
	static Line line(String line) {
		String[] splits = line.split("\t");
		Line result = null;
		result = new Line(splits[0], splits[1], splits[2]);
		return result;
	}
}
