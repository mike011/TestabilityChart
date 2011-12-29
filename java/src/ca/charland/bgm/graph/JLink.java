package ca.charland.bgm.graph;

import javax.swing.JButton;

/**
 * The Class JLink which wraps a button and a link.
 */
public class JLink extends JButton {

	/**
	 * A generated URL.
	 */
	private static final long serialVersionUID = 8308684837097519831L;

	/** The URL. */
	private String _url;

	/**
	 * Sets the URL.
	 * 
	 * @param string
	 *            the new URL
	 */
	public void setURL(String string) {
		_url = string;
	}

	/**
	 * Gets the URL.
	 * 
	 * @return the URL
	 */
	public String getURL() {
		return _url;
	}

}
