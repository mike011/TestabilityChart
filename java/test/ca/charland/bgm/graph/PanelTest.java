package ca.charland.bgm.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.Container;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.TreeMap;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.XYItemEntity;
import org.jfree.chart.plot.PlotOrientation;
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
	public void testConstructor() {
		// Setup & Exercise
		Panel panel = new Panel(null);
		
		// Verify
		assertNotNull(panel);
		assertEquals("Testability Chart 0.15", panel.getTitle());
	}

	/**
	 * Test method for {@link ca.charland.bgm.graph.Panel#setJPanel()}.
	 */
	@Test
	public void testCreateDemoPanel() {
		// Setup
		Panel bc = new Panel(null);
		
		// Exercise
		bc.setJPanel();
		
		// Verify
		assertNotNull(bc.getContentPane());
	}

	/**
	 * Test mouse clicked, but it's the wrong event type.
	 */
	@Test
	public void testChartMouseClickedWrongEventType() {
		// Setup
		Panel bc = new Panel(null);
		bc.setJPanel();
		JFreeChart createBubbleChart = ChartFactory.createBubbleChart("", "", "", new MyXYZDataset(),
		        PlotOrientation.VERTICAL, true, true, false);
		ChartMouseEvent cme = new ChartMouseEvent(createBubbleChart, null, null);

		// Exercise
		bc.chartMouseClicked(cme);

		// Verify
		assertFalse(bc.getChange().isEnabled());
	}

	/**
	 * Test mouse clicked, with a valid event type.
	 */
	@Test
	public void testChartMouseClicked() {
		// Setup
		Panel bc = new Panel(null);
		String link = "link";
		bc.setDataSet(getDataSet(link));
		bc.setJPanel();

		// Exercise
		bc.chartMouseClicked(getChartMouseEvent(link));

		// Verify
		JLink change = bc.getChange();
		assertTrue(change.isEnabled());
		assertEquals("change by key", change.getText());
	}

	/**
	 * Test mouse clicked, but the link is null.
	 */
	@Test
	public void testChartMouseClickedNullLink() {
		// Setup
		Panel bc = new Panel(null);
		String link = null;
		bc.setDataSet(getDataSet(link));
		bc.setJPanel();

		// Exercise
		bc.chartMouseClicked(getChartMouseEvent(link));

		// Verify
		assertFalse(bc.getChange().isEnabled());
	}

	/**
	 * Helper method that gets the ChartMouseEvent.
	 * 
	 * @param link
	 *            The link URL.
	 * 
	 * @return the ChartMouseEvent
	 */
	private static ChartMouseEvent getChartMouseEvent(String link) {
		JFreeChart createBubbleChart = ChartFactory.createBubbleChart("", "", "", new MyXYZDataset(),
		        PlotOrientation.VERTICAL, true, true, false);

		XYItemEntity entity = new XYItemEntity(new Area(), getDataSet(link), 0, 0, null, null);
		ChartMouseEvent cme = new ChartMouseEvent(createBubbleChart, null, entity);

		return cme;
	}

	/**
	 * Helper method that gets the data set.
	 * 
	 * @param link
	 *            The HTTP link.
	 * 
	 * @return the data set
	 */
	private static MyXYZDataset getDataSet(String link) {
		TreeMap<String, ArrayList<Bubble>> changes = new TreeMap<String, ArrayList<Bubble>>();
		ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
		bubbles.add(new Bubble(null, 0, 0, "change", link));
		changes.put("key", bubbles);

		Chart chart = new Chart(null);
		chart.addBubbles(changes);
		return chart.getDataSet();
	}
	
	/**
	 * Test that the panel title is not set.
	 */
	@Test
	public void testPanelTitleNull() {
		// Setup
		Panel bc = new Panel(null);
		String link = null;
		bc.setDataSet(getDataSet(link));
		
		// Exercise
		bc.setJPanel();
		
		// Verify
		Container contentPane = bc.getContentPane();
		ChartPanel chart = (ChartPanel)contentPane.getComponents()[0];
		JFreeChart jfc = chart.getChart();
		assertNull(jfc.getTitle());
	}
	
	/**
	 * Test that the panel title is correctly set.
	 */
	@Test
	public void testPanelTitle() {
		// Setup
		Panel bc = new Panel("Frank");
		String link = null;
		bc.setDataSet(getDataSet(link));
		
		// Exercise
		bc.setJPanel();
		
		// Verify
		Container contentPane = bc.getContentPane();
		ChartPanel chart = (ChartPanel)contentPane.getComponents()[0];
		JFreeChart jfc = chart.getChart();
		assertEquals("Project: Frank", jfc.getTitle().getText());
	}
}
