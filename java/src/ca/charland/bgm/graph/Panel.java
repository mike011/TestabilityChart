package ca.charland.bgm.graph;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYZDataset;
import org.jfree.ui.ApplicationFrame;

/**
 * The Panel
 * 
 * @author mcharland
 */
public class Panel extends ApplicationFrame {

	/**
     * Used to uniquely identify the class.
     */
    private static final long serialVersionUID = 2490289293680096781L;

	/**
	 * Instantiates a new bubble chart demo1.
	 */
	public Panel() {
		super("Testability Chart");
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	/**
	 * Creates the chart.
	 * 
	 * @param xyzdataset
	 *            the xyzdataset
	 * @return the j free chart
	 */
	private static JFreeChart createChart(XYZDataset xyzdataset) {
		JFreeChart jfreechart = ChartFactory.createBubbleChart("Bubble Chart Demo 1", "X", "Y", xyzdataset,
		        PlotOrientation.VERTICAL, true, true, false);
		XYPlot xyplot = (XYPlot) jfreechart.getPlot();
		xyplot.setForegroundAlpha(0.65F);
		xyplot.setDomainPannable(true);
		xyplot.setRangePannable(true);
		XYItemRenderer xyitemrenderer = xyplot.getRenderer();
		xyitemrenderer.setSeriesPaint(0, Color.blue);
		NumberAxis numberaxis = (NumberAxis) xyplot.getDomainAxis();
		numberaxis.setLowerMargin(0.14999999999999999D);
		numberaxis.setUpperMargin(0.14999999999999999D);
		NumberAxis numberaxis1 = (NumberAxis) xyplot.getRangeAxis();
		numberaxis1.setLowerMargin(0.14999999999999999D);
		numberaxis1.setUpperMargin(0.14999999999999999D);
		return jfreechart;
	}

	/**
	 * Creates the dataset.
	 * 
	 * @return the xYZ dataset
	 */
	public XYZDataset createDataset() {
		DefaultXYZDataset defaultxyzdataset = new DefaultXYZDataset();

		// date
		double x[] = { 5.7003213E8, 5.6990106E8, 5.09607936E8, 5.06068992E8, 3.54156544E8, 3.51535104E8, 3.4865152E8,
		        3.43277568E8, 3.40918272E8, 2.76955136E8, 2.69221888E8, 2.59260416E8, 1.8808832E8, 1.81796864E8,
		        1.57548544E8, 7.3007104E7, 6.5273856E7, 5.3215232E7, 3538944.0, 0.0 };

		// coverage
		double y[] = { 54, 68, 54, 76, 72, 41, 81, 40, 55, 8, 100, 40, 45, 57, 59, 55, 34, 33, 12, 53 };

		// size
		double z[] = { 50.0, 2.9733925, 2.097561, 1.9866962, 1.9977827, 1.654102, 2.7627494, 1.0332594, 5.490022, 2.053215,
		        1.0, 2.8736143, 5.9113083, 4.0266075, 2.8514411, 1.7982261, 2.7627494, 5.7117515, 1.4545455, 14.425721 };
		double ad3[][] = { x, y, z };
		defaultxyzdataset.addSeries("Series 1", ad3);
		return defaultxyzdataset;
	}

	/**
	 * Creates the demo panel.
	 * 
	 * @return the j panel
	 */
	public JPanel createDemoPanel() {
		JFreeChart jfreechart = createChart(createDataset());
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		chartpanel.setMouseWheelEnabled(true);
		chartpanel.setDomainZoomable(true);
		chartpanel.setRangeZoomable(true);
		return chartpanel;
	}
}
