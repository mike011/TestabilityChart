package ca.charland.bgm.graph;

import static org.junit.Assert.*;

import javax.swing.JPanel;

import org.jfree.data.xy.XYZDataset;
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
	 * Test method for {@link ca.charland.bgm.graph.Panel#createDataset()}.
	 */
	@Test
	public final void testCreateDataset() {
		Panel bc = new Panel();
		XYZDataset createDataset = bc.createDataset();
		assertNotNull(createDataset);
	}

	/**
	 * Test method for {@link ca.charland.bgm.graph.Panel#createDemoPanel()}.
	 */
	@Test
	public final void testCreateDemoPanel() {
		Panel bc = new Panel();
		JPanel createDemoPanel = bc.createDemoPanel();
		assertNotNull(createDemoPanel);
	}
}
