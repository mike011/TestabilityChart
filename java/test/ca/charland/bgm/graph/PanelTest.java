package ca.charland.bgm.graph;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * Testing Panel.
 * 
 * @author mcharland
 * 
 */
public class PanelTest {

	/**
	 * Test the constructor.
	 */
	@Test
	public final void testConstructor() {
		assertNotNull(new Panel());
	}

	/**
	 * Test method for {@link ca.charland.bgm.graph.Panel#setJPanel()}.
	 */
	@Test
	public final void testCreateDemoPanel() {
		Panel bc = new Panel();
		bc.setJPanel();
		assertNotNull(bc.getContentPane());
	}
}
