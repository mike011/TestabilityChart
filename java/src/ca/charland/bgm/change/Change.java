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

	protected final List<Line> lines;

	public Change(String commit, String author, String date, String description, List<Line> lines) {
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

	public List<Line> getFiles() {
	    return lines;
    }

	public float getCoverage(List<String> types) {
	    // TODO Auto-generated method stub
	    return 0;
    }

	public float getLinesCovered(List<String> types) {
	    // TODO Auto-generated method stub
	    return 0;
    }
}
