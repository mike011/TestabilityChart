package ca.charland.bgm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

/**
 * Testing of the main class.
 * 
 * @author mcharland
 * 
 */
public class MainTest {

	/**
	 * Test getting the log file.
	 */
	@Test
	public void testGetLogFile() {
		assertNull(new Main().getLogFile());
	}

	/**
	 * Test parsing the log file that is null.
	 */
	@Test
	public void testParseArgsNull() {
		// Setup
		Main m = new Main();

		// Exercise
		m.setup();

		// Verify
		assertNull(m.getLogFile());
		assertNull(m.getTypes());
	}

	/**
	 * Test parsing the log file that has not been inited.
	 */
	@Test
	public void testParseArgsNotInited() {
		// Setup
		Main m = new Main();

		// Exercise
		m.setup();

		// Verify
		assertNull(m.getLogFile());
		assertNull(m.getTypes());
	}

	/**
	 * Test parsing the log file that is an empty string.
	 */
	@Test
	public void testParseArgsEmptyString() {
		// Setup
		Main m = new Main();

		// Exercise
		m.setup();

		// Verify
		assertNull(m.getLogFile());
		assertNull(m.getTypes());
	}

	/**
	 * Test getting types.
	 */
	@Test
	public void testGetTypes() {
		assertNull(new Main().getTypes());
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
		m.setup();

		// Verify
		assertEquals("dog.txt", m.getLogFile());
		List<String> types = m.getTypes();
		assertEquals(5, types.size());
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
		m.setup();

		// Verify
		assertEquals("sample.log", m.getLogFile());
		assertNull(m.getTypes());
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
		m.setup();

		// Verify
		assertNull(m.getLogFile());
		List<String> types = m.getTypes();
		assertEquals(4, types.size());
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
		m.setup();

		// Verify
		assertNull(m.getLogFile());
		assertNull(m.getTypes());
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
		m.setup();

		// Verify
		assertEquals("dog.txt", m.getLogFile());
		List<String> types = m.getTypes();
		assertEquals(5, types.size());
	}

	/**
	 * Test loading the link from the properties file.
	 */
	@Test
	public void testPropertiesFileLinkNotSet() {
		// Setup
		Main m = new Main();
		m.setPropertiesFile("test/res/log.properties");

		// Exercise
		m.setup();

		// Verify
		assertNull(m.getBaseURL());
	}

	/**
	 * Test loading the link from the properties file.
	 */
	@Test
	public void testPropertiesFileLink() {
		// Setup
		Main m = new Main();
		m.setPropertiesFile("test/res/common.properties");

		// Exercise
		m.setup();

		// Verify
		assertEquals("www.nba.com/", m.getBaseURL());
	}

	/**
	 * Test turning on debugging.
	 */
	@Test
	public void testPropertiesDebug() {
		// Setup
		Main m = new Main();
		m.setPropertiesFile("test/res/common.properties");

		// Exercise
		m.setup();

		// Verify
		assertTrue(m.isDebugging());
	}

	@Test
	public void testPropertiesTitle() {
		// Setup
		Main m = new Main();
		m.setPropertiesFile("test/res/common.properties");

		// Exercise
		m.setup();

		// Verify
		assertEquals("Testability", m.getProjectName());
	}
	
	@Test
	public void testPropertiesRepoTypeDefault() {
		// Setup
		Main m = new Main();
		m.setPropertiesFile("");

		// Exercise
		m.setup();

		// Verify
		assertEquals("git", m.getRepoType());
	}
	
	@Test
	public void testPropertiesRepoTypeSet() {
		// Setup
		Main m = new Main();
		m.setPropertiesFile("test/res/common.properties");

		// Exercise
		m.setup();

		// Verify
		assertEquals("svn", m.getRepoType());
	}
}
