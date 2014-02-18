package ca.charland.bgm.change;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class IntegrityFileParserTest {

	@Test
	public void noChanges() {
		IntegrityFileParser ifp = new IntegrityFileParser("test/res/empty.xml");
		ifp.load();
		List<Change> changes = ifp.parse();
		assertTrue(changes.isEmpty());
	}

	@Test
	public void oneFilesInOneChange() {
		IntegrityFileParser ifp = new IntegrityFileParser("test/res/one.xml");
		ifp.load();
		List<Change> changes = ifp.parse();
		assertFalse(changes.isEmpty());
		assertEquals(1, changes.size());

		Change change = changes.get(0);
		assertEquals("781199:1", change.getCommit());
		assertEquals("mcharland", change.getAuthor());
		assertEquals("Wed Aug 15 20:06:15 EDT 2012", change.getDate().toString());
		assertEquals(1, change.getFiles().size());
		List<? extends Line> files = change.getFiles();
		assertFirstFileFromChange(files.get(0));
	}

	@Test
	public void twoFilesInOneChange() {
		IntegrityFileParser ifp = new IntegrityFileParser("test/res/two.xml");
		ifp.load();
		List<Change> changes = ifp.parse();
		assertFalse(changes.isEmpty());
		assertEquals(1, changes.size());

		Change change = changes.get(0);
		assertEquals("781199:1", change.getCommit());
		assertEquals("mcharland", change.getAuthor());
		assertEquals("Wed Aug 15 20:06:15 EDT 2012", change.getDate().toString());
		assertEquals(2, change.getFiles().size());
		List<? extends Line> files = change.getFiles();
		assertFirstFileFromChange(files.get(0));
		assertSecondFileFromChange(files.get(1));
	}

	private void assertFirstFileFromChange(Line line) {
		assertEquals(
		        "hqrdsi.mks.com\\:7030:/rds/src/itf/java/mks/itf/framework/mksapi/im/project.pj:mainline:FieldAndValuePair.java",
		        line.toString());

		List<String> types = new ArrayList<String>();
		types.add("java");
		assertEquals(28, line.getTestDiff(types));
		assertEquals(0, line.getSourceDifference(types));
	}

	private void assertSecondFileFromChange(Line line) {
		assertEquals("hqrdsi.mks.com\\:7030:/rds/src/itf/java/mks/itf/framework/mksapi/im/project.pj:mainline:Item.java",
		        line.toString());

		List<String> types = new ArrayList<String>();
		types.add("java");
		assertEquals(26, line.getTestDiff(types));
		assertEquals(0, line.getSourceDifference(types));
	}

	@Test
	public void twoChanges() {
		IntegrityFileParser ifp = new IntegrityFileParser("test/res/twoChanges.xml");
		ifp.load();
		List<Change> changes = ifp.parse();
		assertEquals(2, changes.size());
		assertEquals("769541:1", changes.get(0).getCommit());
		assertEquals("781199:1", changes.get(1).getCommit());
		
		List<? extends Line> files = changes.get(1).getFiles();
		Line line = files.get(0);
		assertEquals("hqrdsi.mks.com\\:7030:/rds/src/itf/java/mks/itf/framework/mksapi/im/project.pj:mainline:FieldAndValuePair.java",
		        line.toString());

		List<String> types = new ArrayList<String>();
		types.add("java");
		assertEquals(28, line.getTestDiff(types));
		assertEquals(0, line.getSourceDifference(types));
		assertEquals("jeshepherd", changes.get(1).getAuthor());

	}
}
