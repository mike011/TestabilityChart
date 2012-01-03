package ca.charland.bgm.graph;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.XYItemEntity;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYZDataset;
import org.jfree.ui.ApplicationFrame;

import ca.charland.bgm.Main;

/**
 * The Panel which is what is showing the chart.
 * 
 * @author mcharland
 */
public class Panel extends ApplicationFrame implements ChartMouseListener, ActionListener {

	/**
	 * Used to uniquely identify the class.
	 */
	private static final long serialVersionUID = 2490289293680096781L;

	/**
	 * The title.
	 */
	private static final String TITLE = "Testability Chart " + Main.VERSION;

	/**
	 * The data set.
	 */
	private MyXYZDataset dataSet;

	/** The change. */
	private JLink change;

	/** The title of the project. */
	private final String title;

	/**
	 * Instantiates a new bubble chart demo.
	 * 
	 * @param title
	 *            The title of the project.
	 */
	public Panel(String title) {
		super(TITLE);
		this.title = title;
	}

	/**
	 * Sets the j panel.
	 */
	void setJPanel() {
		JFreeChart jfreechart = createChart(dataSet, title);
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		chartpanel.setMouseWheelEnabled(true);
		chartpanel.setDomainZoomable(true);
		chartpanel.setRangeZoomable(true);
		chartpanel.addChartMouseListener(this);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(chartpanel);

		JPanel label = new JPanel(new FlowLayout());
		label.add(new JLabel("Commit:"));
		change = new JLink();
		change.setEnabled(false);
		change.addActionListener(this);
		label.add(change);
		panel.add(label);

		setContentPane(panel);
	}

	/**
	 * Sets the data set.
	 * 
	 * @param dataSet
	 *            the new data set
	 */
	public void setDataSet(MyXYZDataset dataSet) {
		this.dataSet = dataSet;
	}

	/**
	 * Creates the chart.
	 * 
	 * @param xyzdataset
	 *            the x y z data set
	 * @param title
	 *            The title to set.
	 * @return the j free chart
	 */
	private static JFreeChart createChart(XYZDataset xyzdataset, String title) {
		final String x = "date";
		final String y = "test / (test + source)";
		if (title != null) {
			title = "Project: " + title;
		}
		JFreeChart jfreechart = ChartFactory.createBubbleChart(title, x, y, xyzdataset, PlotOrientation.VERTICAL, true,
		        true, false);
		XYPlot xyplot = (XYPlot) jfreechart.getPlot();
		xyplot.setForegroundAlpha(0.65F);
		xyplot.setDomainPannable(true);
		xyplot.setRangePannable(true);
		XYItemRenderer xyitemrenderer = xyplot.getRenderer();
		xyitemrenderer.setSeriesPaint(0, Color.blue);

		NumberAxis xAxis = (NumberAxis) xyplot.getDomainAxis();
		xAxis.setLowerMargin(0.14999999999999999D);
		xAxis.setUpperMargin(0.14999999999999999D);
		xAxis.setTickUnit(new NumberTickUnit(500000000, new DecimalFormat("##%")));

		// Switch the domain set to the date axis.
		xyplot.setDomainAxis(new DateAxis());

		NumberAxis yAxis = (NumberAxis) xyplot.getRangeAxis();
		yAxis.setLowerMargin(0.14999999999999999D);
		yAxis.setUpperMargin(0.14999999999999999D);
		yAxis.setTickUnit(new NumberTickUnit(.25, NumberFormat.getPercentInstance()));

		if (xyzdataset != null) {
			int seriesCount = xyzdataset.getSeriesCount();
			if (seriesCount > 10) {
				jfreechart.removeLegend();
			}
		}
		return jfreechart;
	}

	/** {@inheritDoc} */
	@Override
	public void chartMouseClicked(ChartMouseEvent event) {
		ChartEntity entity = event.getEntity();
		if (entity instanceof XYItemEntity) {
			XYItemEntity cie = (XYItemEntity) entity;
			DefaultXYZDataset ds = (DefaultXYZDataset) cie.getDataset();
			String seriesKey = ds.getSeriesKey(cie.getSeriesIndex()).toString();
			Bubble bubble = dataSet.getBubble(seriesKey, cie.getItem());
			change.setText(bubble.getChange() + " by " + seriesKey);
			String link = bubble.getLink();
			if (link != null) {
				change.setURL(link);
				change.setEnabled(true);
			}
		}
	}

	/** {@inheritDoc} */
	@Override
	public void chartMouseMoved(ChartMouseEvent event) {
		// not used
	}

	/** {@inheritDoc} */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			Desktop.getDesktop().browse(new URI(change.getURL()));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the change.
	 * 
	 * @return the change
	 */
	JLink getChange() {
		return change;
	}
}