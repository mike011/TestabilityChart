package ca.charland.bgm;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Testing of the main class.
 * 
 * @author mcharland
 * 
 */
public class MainTest {

	/**
	 * Test parsing just the log file.
	 */
	@Test
	public void testParseArgsLogFile() {
		// Setup
		String[] args = { "sample.log" };
		Main m = new Main();

		// Exercise
		m.setup(args);

		Assert.assertEquals("sample.log", m.getLogFile());
		Assert.assertNull(m.getTypes());
	}

	/**
	 * Test getting the log file.
	 */
	@Test
	public void testGetLogFile() {
		Assert.assertNull(new Main().getLogFile());
	}

	/**
	 * Test parsing the log file that is null.
	 */
	@Test
	public void testParseArgsNull() {
		// Setup
		Main m = new Main();

		// Exercise
		m.setup(null);

		Assert.assertNull(m.getLogFile());
		Assert.assertNull(m.getTypes());
	}
	
	/**
	 * Test parsing the log file that has not been inited.
	 */
	@Test
	public void testParseArgsNotInited() {
		// Setup
		Main m = new Main();

		// Exercise
		m.setup(new String[]{});

		Assert.assertNull(m.getLogFile());
		Assert.assertNull(m.getTypes());
	}
	
	/**
	 * Test parsing the log file that is an empty string.
	 */
	@Test
	public void testParseArgsEmptyString() {
		// Setup
		String[] args = { "" };
		Main m = new Main();

		// Exercise
		m.setup(args);

		Assert.assertNull(m.getLogFile());
		Assert.assertNull(m.getTypes());
	}

	/**
	 * Test parsing the log file and one type.
	 */
	@Test
	public void testParseArgsLogFileAndOneType() {
		// Setup
		String[] args = { "sample.log", "java" };
		Main m = new Main();

		// Exercise
		m.setup(args);

		// Verify
		Assert.assertEquals("sample.log", m.getLogFile());
		List<String> types = m.getTypes();
		Assert.assertEquals(1, types.size());
		Assert.assertEquals("java", types.get(0));
	}

	/**
	 * Test parsing the log file and one type.
	 */
	@Test
	public void testParseArgsLogFileAndManyType() {
		// Setup
		String[] args = { "sample.log", "java", "cpp", "c", "cc" };
		Main m = new Main();

		// Exercise
		m.setup(args);

		// Verify
		Assert.assertEquals("sample.log", m.getLogFile());
		List<String> types = m.getTypes();
		Assert.assertEquals(4, types.size());
		Assert.assertEquals("java", types.get(0));
		Assert.assertEquals("cpp", types.get(1));
		Assert.assertEquals("c", types.get(2));
		Assert.assertEquals("cc", types.get(3));
	}

	/**
	 * Test getting types.
	 */
	@Test
	public void testGetTypes() {
		Assert.assertNull(new Main().getTypes());
	}

	/**
	 * Test loading a valid properties file.
	 */
	@Test
	public void testPropertiesFile() {
		// Setup
		Main m = new Main();
		m.setPropertiesFile("test/res/common.properties");

		// Exercise
		m.setup(new String[] { "" });

		// Verify
		Assert.assertEquals("dog.txt", m.getLogFile());
		List<String> types = m.getTypes();
		Assert.assertEquals(5, types.size());
	}

	/**
	 * Test loading a valid properties file.
	 */
	@Test
	public void testPropertiesFileJustLogFile() {
		// Setup
		Main m = new Main();
		m.setPropertiesFile("test/res/log.properties");

		// Exercise
		m.setup(new String[] { "" });

		// Verify
		Assert.assertEquals("sample.log", m.getLogFile());
		Assert.assertNull(m.getTypes());
	}

	/**
	 * Test loading a valid properties file.
	 */
	@Test
	public void testPropertiesJustFileTypes() {
		// Setup
		Main m = new Main();
		m.setPropertiesFile("test/res/types.properties");

		// Exercise
		m.setup(new String[] { "" });

		// Verify
		Assert.assertNull(m.getLogFile());
		List<String> types = m.getTypes();
		Assert.assertEquals(4, types.size());
	}

	/**
	 * Test loading the properties when not found.
	 */
	@Test
	public void testPropertiesFilesFileNotFound() {
		// Setup
		Main m = new Main();
		m.setPropertiesFile("bad.file");

		// Exercise
		m.setup(new String[] { "" });

		// Verify
		Assert.assertNull(m.getLogFile());
		Assert.assertNull(m.getTypes());
	}

	/**
	 * Test loading the properties when you have args inputed and from properties file..
	 */
	@Test
	public void testPropertiesFilesFileMixedArgs() {
		// Setup
		Main m = new Main();
		m.setPropertiesFile("test/res/common.properties");

		// Exercise
		m.setup(new String[] { "" });

		// Verify
		Assert.assertEquals("dog.txt", m.getLogFile());
		List<String> types = m.getTypes();
		Assert.assertEquals(5, types.size());
	}
}
