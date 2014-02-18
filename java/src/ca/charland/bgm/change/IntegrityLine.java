package ca.charland.bgm.change;

import java.util.List;

public class IntegrityLine extends Line{

	public IntegrityLine(String added, String removed, String file) {
	    super(added, removed, file);
    }
	
	@Override
	public boolean isSource(List<String> types) {
		return super.isSource(types) && !super.toString().contains("itf");
	}

}
