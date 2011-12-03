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
	 * @param lines the lines
	 * @return the list
	 */
	public static List<Change> changes(List<String> lines) {
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
						Change change = new Change(commit, author, date, desc,
								fileLines);
						result.add(change);
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
					fileLines.add(line(line));
				}
			}
		}

		Change change = new Change(commit, author, date, desc, fileLines);
		result.add(change);

		return result;
	}

	/**
	 * Line.
	 *
	 * @param line the line
	 * @return the line
	 */
	static Line line(String line) {
		String[] splits = line.split("\t");
		Line result = null;
			result = new Line(splits[0], splits[1], splits[2]);
		return result;
	}
}
