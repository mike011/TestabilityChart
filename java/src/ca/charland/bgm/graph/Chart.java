/*
 * 
 */
package ca.charland.bgm.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYZDataset;
import org.jfree.ui.RefineryUtilities;

/**
 * Represents the chart.
 * 
 * @author mcharland
 */
public class Chart {

	/** The panel. */
	private final Panel panel;

	/** The data to be displayed. */
	private DefaultXYZDataset defaultxyzdataset;

	/**
	 * Instantiates a new chart.
	 */
	public Chart() {
		panel = new Panel();
		defaultxyzdataset = new DefaultXYZDataset();
	}

	/**
	 * Adds the bubbles.
	 * 
	 * @param changes
	 *            the changes
	 */
	public void addBubbles(Map<String, ArrayList<Bubble>> changes) {
		if (changes == null) {
			return;
		}
		for (String key : changes.keySet()) {

			List<Double> xs = new ArrayList<Double>();
			List<Double> ys = new ArrayList<Double>();
			List<Double> zs = new ArrayList<Double>();
			for (Bubble b : changes.get(key)) {
				xs.add((double) b.getDate());
				ys.add((double) b.getCoverage());
				zs.add((double) b.getSize());
			}
			double[] xs2 = getPrimitiveArray(xs);
			double[] ys2 = getPrimitiveArray(ys);
			double[] zs2 = getPrimitiveArray(zs);
			double ad3[][] = { xs2, ys2, zs2 };
			addSeries(key, ad3);
		}

	}

	/**
	 * Gets the primitive array.
	 * 
	 * @param objectList
	 *            The object list to cast down.
	 * @return the primitive array
	 */
	private double[] getPrimitiveArray(List<Double> objectList) {
		Double[] da = objectList.toArray(new Double[0]);
		double[] d2 = new double[da.length];
		int a = 0;
		for (Double d : da) {
			d2[a] = d;
			++a;
		}
		return d2;
	}

	/**
	 * Gets the panel.
	 * 
	 * @return the panel
	 */
	Panel getPanel() {
		return panel;
	}

	/**
	 * Show the chart.
	 */
	public void show() {
		panel.setDataSet(defaultxyzdataset);
		panel.createJPanel();
		panel.pack();
		RefineryUtilities.centerFrameOnScreen(panel);
		panel.setVisible(true);

	}

	/**
	 * Adds a series to the data set.
	 * 
	 * @param name
	 *            The name of the series.
	 * @param data
	 *            The data to be added.
	 * 
	 * @return the XYZ data set
	 */
	XYZDataset addSeries(String name, double[][] data) {
		defaultxyzdataset.addSeries(name, data);
		return defaultxyzdataset;
	}

	/**
	 * Gets the data set which contains all the series.
	 * 
	 * @return the data set
	 */
	public XYZDataset getDataSet() {
		return defaultxyzdataset;
	}
}
