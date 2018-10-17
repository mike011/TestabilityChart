package ca.charland.bgm.change;

import java.util.List;

public class GitLine extends Line {

	public GitLine(String added, String removed, String file) {
		super(added, removed, file);
	}
	
	@Override
	public boolean isValid(List<String> types) {
		if(file.startsWith("Pods")) {
			return false;
		}
		return super.isValid(types);
	}

}
