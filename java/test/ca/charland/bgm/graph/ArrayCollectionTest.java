package ca.charland.bgm.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import ca.charland.bgm.graph.ArrayCollection;
import ca.charland.bgm.graph.Bubble;

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
		assertNotNull(new ArrayCollection(0));
	}

	/**
	 * Test add.
	 */
	@Test
	public void testAdd() {
		ArrayCollection ac = new ArrayCollection(2);
		Bubble b = new Bubble(null, 0, 0, "");

		ac.add(b);

		List<Bubble> element = ac.getElements();

		assertNotNull(element);
		assertEquals(1, element.size());
	}
}
