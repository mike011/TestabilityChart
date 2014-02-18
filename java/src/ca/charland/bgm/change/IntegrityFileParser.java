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
		NodeList workItems = doc.getElementsByTagName("WorkItem");

		List<Change> changes = new ArrayList<Change>();
		int changeIndex = 0;
		for (Element workItem : getElements(workItems)) {
			List<? extends Line> files = getFiles(workItem);
			String date = getDate(workItem, changeIndex);
			if (date != null) {
				changes.add(new Change(getCommit(workItem), getAuthor(workItem), date, "", files));
			}
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

	private List<? extends Line> getFiles(Element workItem) {
		List<Line> files = new ArrayList<Line>();

		List<String> list = getList(workItem, "Item", "modelType", "si.ChangePackage.Entry", "id");
		int index = 0;
		for (String item : list) {
			String file = getFileName(item);
			files.add(new IntegrityLine(getLinesAdded(workItem, index), getLinesRemoved(workItem, index), file));
			++index;
		}
		return files;
	}

	private String getFileName(String item) {
		String file = item.substring(0, item.lastIndexOf(':'));
		file = file.substring(0, file.lastIndexOf(':'));
		return file;
	}

	private String getLinesRemoved(Element workItem, int fileIndex) {
		return get(workItem, "Field", "linesdeleted").get(fileIndex);
	}

	private String getLinesAdded(Element workItem, int fileIndex) {
		return get(workItem, "Field", "linesadded").get(fileIndex);
	}

	private String getAuthor(Element workItem) {
		List<String> list = getList(workItem, "Item", "modelType", "si.User", "id");
		return list.get(0);
	}

	private String getCommit(Element workItem) {
		return workItem.getAttribute("id");
	}

	private String getDate(Element workItem, int changeIndex) {
		List<String> list = get(workItem, "Field", "closeddate");
		if(list.isEmpty()) {
			// change was created another server
			return null;
		}
		String string = list.get(0);
		if (string == null) {
			return null;
		}
		return string.replace('T', ' ');
	}

	private List<String> get(Element element, String tagname, String name) {
		NodeList node = element.getElementsByTagName(tagname);

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

	private List<String> getList(Element workItem, String tagname, String name, String type, String id) {
		List<String> results = new ArrayList<String>();
		NodeList node = workItem.getElementsByTagName(tagname);

		for (int temp = 0; temp < node.getLength(); temp++) {
			Node nNode = node.item(temp);
			Element eElement = (Element) nNode;
			String attribute = eElement.getAttribute(name);
			if (attribute.equals(type)) {
				results.add(eElement.getAttribute(id));
			}

		}
		return results;
	}
}
