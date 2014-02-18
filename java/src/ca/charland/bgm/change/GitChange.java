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
public class GitChange extends Change {

	public GitChange(String commit, String author, String date, String description, List<Line> lines) {
		super(commit, author, date, description, lines);

	}

	@Override
	public boolean equals(Object o) {
		GitChange c2 = (GitChange) o;
		return getCommit().equals(c2.getCommit());
	}

	public String getCommit() {
		return commit.substring("commit ".length()).trim();
	}

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

	public String getAuthor() {
		int start = "Author:".length();
		int end = author.indexOf('<');
		String parsed = author.substring(start, end).trim();
		return parsed;
	}

	public boolean isValid() {
		return !lines.isEmpty();
	}
}
