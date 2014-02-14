package ca.charland.bgm.change;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mcharland
 */
public class GitFileParser {

	public List<? extends Change> changes(List<String> fileContents, List<String> fileTypes) {
		List<GitChange> result = new ArrayList<GitChange>();

		String commit = null;
		String author = null;
		String date = null;
		String desc = null;
		List<Line> fileLines = new ArrayList<Line>();
		for (String line : fileContents) {
			if (line.length() > 0) {
				if (line.startsWith("commit")) {
					if (commit != null) {
						GitChange change = new GitChange(commit, author, date, desc, fileLines);
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
					if (lineObject.isValid(fileTypes)) {
						fileLines.add(lineObject);
					}
				}
			}
		}

		GitChange change = new GitChange(commit, author, date, desc, fileLines);
		if (change.isValid()) {
			result.add(change);
		}

		return result;
	}

	boolean isChangeValid(GitChange change, List<String> types) {
		return change.isValid();
	}

	Line line(String line) {
		String[] splits = line.split("\t");
		Line result = null;
		result = new Line(splits[0], splits[1], splits[2]);
		return result;
	}
}
