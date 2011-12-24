package ca.charland.bgm.change;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Holds one change which is a commit into the repository.
 * 
 * @author mcharland
 */
public class Change {

	/** The commit. */
	private final String commit;

	/** The author. */
	private final String author;

	/** The date string. */
	private final String dateString;

	/** The description. */
	private final String description;

	/** The lines. */
	private final List<Line> lines;

	/**
	 * Instantiates a new change.
	 * 
	 * @param commit
	 *            the commit
	 * @param author
	 *            the author
	 * @param date
	 *            the date
	 * @param description
	 *            the description
	 * @param lines
	 *            the lines
	 */
	public Change(String commit, String author, String date, String description, List<Line> lines) {
		this.commit = commit;
		this.author = author;
		this.dateString = date;
		this.description = description;
		this.lines = lines;

	}

	/**
	 * Gets the coverage.
	 * 
	 * @param types
	 *            the types
	 * @return the coverage
	 */
	public float getCoverage(List<String> types) {

		float src = getSrc(types);

		float test = getTest(types);

		// Prevent a divide by zero.
		if ((src + test) == 0) {
			src = 1;
		}
		return test / (src + test);
	}

	/**
	 * Gets the test.
	 * 
	 * @param types
	 *            the types
	 * @return the test
	 */
	private int getTest(List<String> types) {
		int test = 0;
		for (Line line : lines) {
			test += line.getTestDiff(types);
		}
		return test;
	}

	/**
	 * Gets the src.
	 * 
	 * @param types
	 *            the types
	 * @return the src
	 */
	private int getSrc(List<String> types) {
		int src = 0;
		for (Line line : lines) {
			src += line.getSrcDiff(types);
		}
		return src;
	}

	/**
	 * Gets the lines covered.
	 * 
	 * @param types
	 *            the types
	 * @return the lines covered
	 */
	public int getLinesCovered(List<String> types) {
		return getSrc(types) + getTest(types);
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(Object o) {
		Change c2 = (Change) o;
		return getCommit().equals(c2.getCommit());
	}

	/**
	 * Gets the commit.
	 * 
	 * @return the commit
	 */
	public String getCommit() {
		return commit.substring("commit ".length()).trim();
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return getCommit();
	}

	/**
	 * Gets the date.
	 * 
	 * @return the date
	 */
	public Date getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss yyyy Z");

		String sub = dateString.substring(dateString.indexOf("Date:") + "Date:".length()).trim();
		Date date = null;
		try {
			date = (Date) formatter.parse(sub);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * Gets the parsed author.
	 * 
	 * @return the author
	 */
	public String getAuthor() {
		int start = "Author:".length();
		int end = author.indexOf('<');
		String parsed = author.substring(start, end).trim();
		return parsed;
	}

	/**
	 * Checks if the Change is valid meaning that it containing any lines.
	 * 
	 * @return If the Change is valid.
	 */
	public boolean isValid() {
		return !lines.isEmpty();
	}
}
