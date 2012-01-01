package ca.charland.bgm.graph;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.TreeMap;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
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
		assertNotNull(new Panel());
	}

	/**
	 * Test method for {@link ca.charland.bgm.graph.Panel#setJPanel()}.
	 */
	@Test
	public void testCreateDemoPanel() {
		Panel bc = new Panel();
		bc.setJPanel();
		assertNotNull(bc.getContentPane());
	}

	/**
	 * Test mouse clicked.
	 */
	@Test
	public void testChartMouseClickedWrongEventType() {
		// Setup
		Panel bc = new Panel();
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
	 * Test mouse clicked.
	 */
	@Test
	public void testChartMouseClicked() {
		// Setup
		Panel bc = new Panel();
		String link = "link";
		bc.setDataSet(getDataSet(link));
		bc.setJPanel();

		// Exercise
		bc.chartMouseClicked(getChartMouseEvent(link));

		// Verify
		assertTrue(bc.getChange().isEnabled());
	}

	/**
	 * Test mouse clicked.
	 */
	@Test
	public void testChartMouseClickedNullLink() {
		// Setup
		Panel bc = new Panel();
		String link = null;
		bc.setDataSet(getDataSet(link));
		bc.setJPanel();

		// Exercise
		bc.chartMouseClicked(getChartMouseEvent(link));

		// Verify
		assertFalse(bc.getChange().isEnabled());
	}

	/**
	 * Gets the ChartMouseEvent.
	 * 
	 * @param link
	 *            The link url.
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
	 * Gets the data set.
	 * 
	 * @param link
	 *            The http link.
	 * 
	 * @return the data set
	 */
	private static MyXYZDataset getDataSet(String link) {
		TreeMap<String, ArrayList<Bubble>> changes = new TreeMap<String, ArrayList<Bubble>>();
		ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
		bubbles.add(new Bubble(null, 0, 0, null, link));
		changes.put("key", bubbles);

		Chart chart = new Chart();
		chart.addBubbles(changes);
		return chart.getDataSet();
	}
}
