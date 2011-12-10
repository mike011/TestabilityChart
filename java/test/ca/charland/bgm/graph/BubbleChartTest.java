package ca.charland.bgm.graph;

import static org.junit.Assert.*;

import javax.swing.JPanel;

import org.jfree.data.xy.XYZDataset;
import org.junit.Test;

/**
 * Testing BubbleChart.
 * 
 * @author mcharland
 *
 */
public class BubbleChartTest {

	/**
	 * Test method for {@link ca.charland.bgm.graph.BubbleChart#BubbleChart()}.
	 */
	@Test
	public final void testBubbleChart() {
		assertNotNull(new BubbleChart());
	}

	/**
	 * Test method for {@link ca.charland.bgm.graph.BubbleChart#createDataset()}.
	 */
	@Test
	public final void testCreateDataset() {
		BubbleChart bc = new BubbleChart();
		XYZDataset createDataset = bc.createDataset();
		assertNotNull(createDataset);
	}

	/**
	 * Test method for {@link ca.charland.bgm.graph.BubbleChart#createDemoPanel()}.
	 */
	@Test
	public final void testCreateDemoPanel() {
		BubbleChart bc = new BubbleChart();
		JPanel createDemoPanel = bc.createDemoPanel();
		assertNotNull(createDemoPanel);
	}

}
