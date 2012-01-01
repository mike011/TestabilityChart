package ca.charland.bgm.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	private MyXYZDataset dataset;

	/**
	 * Instantiates a new chart.
	 */
	public Chart() {
		panel = new Panel();
		dataset = new MyXYZDataset();
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
		dataset.setBubbles(changes);
		for (String key : changes.keySet()) {

			List<Double> xs = new ArrayList<Double>();
			List<Double> ys = new ArrayList<Double>();
			List<Double> zs = new ArrayList<Double>();
			for (Bubble b : changes.get(key)) {
				xs.add((double) b.getDate());
				ys.add((double) b.getCoverage() / 100);
				zs.add((double) b.getSize() / 100);
			}
			double[] xs2 = getPrimitiveArray(xs);
			double[] ys2 = getPrimitiveArray(ys);
			double[] zs2 = getPrimitiveArray(zs);
			double data[][] = { xs2, ys2, zs2 };
			dataset.addSeries(key, data);
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
		panel.setDataSet(dataset);
		panel.setJPanel();
		panel.pack();
		RefineryUtilities.centerFrameOnScreen(panel);
		panel.setVisible(true);

	}

	/**
	 * Gets the data set which contains all the series.
	 * 
	 * @return the data set
	 */
	public MyXYZDataset getDataSet() {
		return dataset;
	}
}
