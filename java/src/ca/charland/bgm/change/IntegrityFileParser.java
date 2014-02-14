package ca.charland.bgm.change;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ca.charland.bgm.FileAccessing;

public class IntegrityFileParser {

	private String xml;
	private Document doc;

	public IntegrityFileParser(String xml) {
		this.xml = xml;
	}

	public void load() {
		doc = FileAccessing.readXML(xml);
	}

	public List<Change> parse() {
		NodeList nList = doc.getElementsByTagName("WorkItem");

		List<Change> changes = new ArrayList<Change>();
		int changeIndex = 0;
		for (Element element : getElements(nList)) {
			List<Line> files = getFiles(element);
			changes.add(new Change(getCommit(changeIndex), getAuthor(), getDate(changeIndex), "", files));
			++changeIndex;
		}
		return changes;
	}
	

	private List<Element> getElements(NodeList nodeList) {
		List<Element> elements = new ArrayList<Element>();
		for (int index = 0; index < nodeList.getLength(); index++) {

			Node nNode = nodeList.item(index);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				elements.add(eElement);
			}
		}
		return elements;
	}

	private List<Line> getFiles(Element line) {
		List<Line> files = new ArrayList<Line>();
		
		List<String> list = get("Item", "id");
		int index = 0;
		for(String item : list) {
			String file = extracted(item);
			files.add(new Line(getLinesAdded(index), getLinesRemoved(index), file));
			++index;
		}
		return files;
	}

	private String extracted(String item) {
	    String file = item.substring(0, item.lastIndexOf(':'));
	    file = file.substring(0, file.lastIndexOf(':'));
	    return file;
    }


	private String getLinesRemoved(int fileIndex) {
		return get("Field", "linesdeleted", "TokenValue", 0).get(fileIndex);
	}

	private String getLinesAdded(int fileIndex) {
		return get("Field", "linesadded", "TokenValue", 0).get(fileIndex);
	}

	private String getAuthor() {
		return get("App-Connection", "userID").get(0);
	}

	private String getCommit(int changeIndex) {
		return get("WorkItem", "id").get(changeIndex);
	}

	private String getDate(int changeIndex) {
		return get("Field", "closeddate", "TokenValue", changeIndex).get(0).replace('T', ' ');
	}

	private List<String> get(String tagname, String name, String secondTag, int index) {
		NodeList node = doc.getElementsByTagName(tagname);

		List<String> results = new ArrayList<String>();
		for (int temp = 0; temp < node.getLength(); temp++) {
			Node nNode = node.item(temp);
			Element eElement = (Element) nNode;
			String fieldName = eElement.getAttribute("name");
			if (fieldName.equals(name)) {
				results.add(getLines(eElement));
			}
		}
		return results;
	}

	private String getLines(Element line) {
		Node item = line.getElementsByTagName("TokenValue").item(0);
		if (item != null) {
			String element = item.getTextContent();
			return element;
		}
		return null;
	}

	private List<String> get(String tagname, String name) {
		List<String> results = new ArrayList<String>();
		NodeList node = doc.getElementsByTagName(tagname);

		for (int temp = 0; temp < node.getLength(); temp++) {
			Node nNode = node.item(temp);
			Element eElement = (Element) nNode;
			results.add(eElement.getAttribute(name));
		}
		return results;
	}
}
