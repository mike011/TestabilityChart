package ca.charland.bgm.change;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Change {

	private final String commit;
	private final String author;
	private final String dateString;
	private final String description;
	private final List<Line> lines;

	public Change(String commit, String author, String date,
			String description, List<Line> lines) {
		this.commit = commit;
		this.author = author;
		this.dateString = date;
		this.description = description;
		this.lines = lines;

	}

	public float getCoverage() {

		float src = getSrc();

		float test = getTest();

		if ((src + test) == 0) {
			src = 1;
		}
		return test / (src + test);
	}

	private int getTest() {
		int test = 0;
		for (Line line : lines) {
			test += line.getTestDiff();
		}
		return test;
	}

	private int getSrc() {
		int src = 0;
		for (Line line : lines) {
			src += line.getSrcDiff();
		}
		return src;
	}

	public int getLinesCovered() {
		return getSrc() + getTest();
	}

	public boolean equals(Object o) {
		Change c2 = (Change) o;
		return this.commit.equals(c2.getCommit());
	}

	private String getCommit() {
		return commit;
	}

	public String toString() {
		return commit;
	}

	public Date getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"E MMM dd HH:mm:ss yyyy Z");

		String sub = dateString.substring(
				dateString.indexOf("Date:") + "Date:".length()).trim();
		Date date = null;
		try {
			date = (Date) formatter.parse(sub);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public String getAuthor() {
		return author;
	}
}
