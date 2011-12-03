package ca.charland.bgm.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

/**
 * Tests for ArrayCollection.
 *
 * @author mcharland
 */
public class ArrayCollectionTest {

	/**
	 * Test.
	 */
	@Test
	public void test() {
		assertNotNull(new ArrayCollection());
	}

	/**
	 * Test add.
	 */
	@Test
	public void testAdd() {
		ArrayCollection ac = new ArrayCollection();
		Bubble b = new Bubble(0, 0, 0);

		ac.add(b);

		List<String> element = ac.getElements();

		assertNotNull(element);
		assertEquals(1, element.size());
		assertEquals("{\"Date\":0.00, \"Coverage\":0.00, \"Size\":0.00}",
				element.get(0));
	}

	/**
	 * Test to string.
	 */
	@Test
	public void testToString() {
		ArrayCollection ac = new ArrayCollection();
		Bubble b = new Bubble(0, 0, 0);
		ac.add(b);
		
		String out = ac.toString();
		
		StringBuffer expected = new StringBuffer();
		expected.append("[Bindable]\r\n");
		expected.append("private var s1:ArrayCollection = new ArrayCollection( [\r\n");
		expected.append("{\"Date\":0.00, \"Coverage\":0.00, \"Size\":0.00}\r\n");
		expected.append("]);\r\n");
		assertEquals(expected.toString(), out);
	}

}
