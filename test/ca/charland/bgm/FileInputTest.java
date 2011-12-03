/**
 * 
 */
package ca.charland.bgm;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import ca.charland.bgm.FileAccessing;

/**
 * @author mcharland
 *
 */
public class FileInputTest {

	/**
	 * Test method for {@link ca.charland.bgm.FileAccessing#read()}.
	 */
	@Test
	public void testReadTxt() {
		List<String> read = FileAccessing.read("test/res/simple.txt");
		Assert.assertNotNull(read);
		Assert.assertTrue(read.size() > 0);
	}
	
	/**
	 * Test method for {@link ca.charland.bgm.FileAccessing#read()}.
	 */
	@Test
	public void testReadAS() {
		List<String> read = FileAccessing.read("src/res/raw_graph.mxml");
		Assert.assertNotNull(read);
		Assert.assertTrue(read.size() > 0);
	}
}
