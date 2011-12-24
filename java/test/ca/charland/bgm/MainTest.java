package ca.charland.bgm;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Testing of the main class.
 * 
 * @author michael
 * 
 */
public class MainTest {

	/**
	 * Test parsing just the log file.
	 */
	@Test
	public void testParseArgsLogFile() {
		String[] args = { "sample.log" };

		// Exercise
		Main m = new Main(args);

		Assert.assertEquals("sample.log", m.getLogFile());
		Assert.assertNull(m.getTypes());
	}

	/**
	 * Test getting the log file.
	 */
	@Test
	public void testGetLogFile() {
		Assert.assertNull(new Main(new String[]{null}).getLogFile());
	}

	/**
	 * Test parsing the log file and one type.
	 */
	@Test
	public void testParseArgsLogFileAndOneType() {
		String[] args = { "sample.log", "java" };
		
		// Exercise
		Main m = new Main(args);

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
		String[] args = { "sample.log", "java", "cpp", "c", "cc" };
		
		// Exercise
		Main m = new Main(args);

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
		Assert.assertNull(new Main(new String[]{" "}).getTypes());
	}

}
