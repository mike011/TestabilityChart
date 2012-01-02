package ca.charland.bgm;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

/**
 * Tests for FileInput.
 * 
 * @author mcharland
 */
public class FileInputTest {

	/**
	 * Test method for {@link ca.charland.bgm.FileAccessing#read()}.
	 */
	@Test
	public void testReadTxt() {
		// Setup & Exercise
		List<String> read = FileAccessing.read("test/res/one.txt");
		
		// Verify
		assertNotNull(read);
		assertTrue(read.size() > 0);
	}
}
