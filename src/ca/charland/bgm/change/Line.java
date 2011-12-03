package ca.charland.bgm.change;

public class Line {
	
	private final String removed;
	private final String added;
	private final String file;

	public Line(String added, String removed, String file) {
		this.added = added;
		this.removed = removed;
		this.file = file;
		
	}
	
	public int getTestDiff() {
		int total = 0;
		if(!isSource()) {
			total = getDiff();
		}
		return total;
	}
	
	public int getSrcDiff() {
		int total = 0;
		if(isSource()) {
			total = getDiff();
		}
		return total;
	}
	
	private int getDiff() {
		int total = 0;
		if(!removed.equals("-")) {
			total += Integer.parseInt(removed);
		}
		
		if(!added.equals("-")) {
			total += Integer.parseInt(added);
		}
			
		return total;
	}

	public boolean isValid() {		
		return !(removed.equals("-") && added.equals("-"));
	}
	
	public boolean isSource() {
		return  isValid() && !file.contains("test");
	}

	public String toString() {
		return file;
	}
}
