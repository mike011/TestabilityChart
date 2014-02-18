package ca.charland.bgm.change;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Change {

	protected final String commit;
	protected final String author;
	protected final String dateString;
	protected final List<? extends Line> lines;

	public Change(String commit, String author, String date, String description, List<? extends Line> lines) {
		this.commit = commit;
		this.author = author;
		this.dateString = date;
		this.lines = lines;
	}

	public String getAuthor() {
	    return author;
    }

	public String getCommit() {
	    return commit;
    }

	public Date getDate() {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		try {
	        return (Date)formatter.parse(dateString);
        } catch (ParseException e) {
	        e.printStackTrace();
        }
		return null;
    }

	public List<? extends Line> getFiles() {
	    return lines;
    }

	public float getCoverage(List<String> types) {
		float src = getSrc(types);

		float test = getTest(types);

		// Prevent a divide by zero.
		if ((src + test) == 0) {
			src = 1;
		}
		return test / (src + test);
    }
	
	private int getSrc(List<String> types) {
		int src = 0;
		for (Line line : lines) {
			src += line.getSourceDifference(types);
		}
		return src;
	}
	
	private int getTest(List<String> types) {
		int test = 0;
		for (Line line : lines) {
			test += line.getTestDiff(types);
		}
		return test;
	}

	public float getLinesCovered(List<String> types) {
		return getSrc(types) + getTest(types);
    }
	
	@Override
	public String toString() {
		return getCommit();
	}
}
