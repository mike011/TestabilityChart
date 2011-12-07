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
	 * @return the coverage
	 */
	public float getCoverage() {

		float src = getSrc();

		float test = getTest();

		if ((src + test) == 0) {
			src = 1;
		}
		return test / (src + test);
	}

	/**
	 * Gets the test.
	 * 
	 * @return the test
	 */
	private int getTest() {
		int test = 0;
		for (Line line : lines) {
			test += line.getTestDiff();
		}
		return test;
	}

	/**
	 * Gets the src.
	 * 
	 * @return the src
	 */
	private int getSrc() {
		int src = 0;
		for (Line line : lines) {
			src += line.getSrcDiff();
		}
		return src;
	}

	/**
	 * Gets the lines covered.
	 * 
	 * @return the lines covered
	 */
	public int getLinesCovered() {
		return getSrc() + getTest();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		Change c2 = (Change) o;
		return this.commit.equals(c2.getCommit());
	}

	/**
	 * Gets the commit.
	 * 
	 * @return the commit
	 */
	private String getCommit() {
		return commit;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return commit;
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
	 * Gets the author.
	 * 
	 * @return the author
	 */
	public String getAuthor() {
		return author;
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
