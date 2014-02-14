package ca.charland.bgm;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class FileAccessingTest {
	
	@Test
	public void readXML() {
		assertNotNull(FileAccessing.readXML("test/res/one.xml"));
	}
}
