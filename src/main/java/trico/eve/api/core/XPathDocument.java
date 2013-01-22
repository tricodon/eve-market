package trico.eve.api.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author nico.mainka
 */
public class XPathDocument {

	private final Object doc;

	private final XPath xPath;

	private final String input;

	public XPathDocument(String input) {
		this.input = input;
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true);
		try {
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			doc = builder.parse(IOUtils.toInputStream(input));
			xPath = XPathFactory.newInstance().newXPath();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public NodeList nodes(String expession) {
		try {
			XPathExpression xPathExpression = xPath.compile(expession);
			Object object = xPathExpression.evaluate(doc, XPathConstants.NODESET);
			NodeList nodeList = (NodeList) object;
			return nodeList;
		} catch (XPathExpressionException e) {
			throw new RuntimeException(expession, e);
		}
	}

	public Node firstNode(String expession) {
		return nodes(expession).item(0);
	}

	public String getTextContent(String expression) {
		Node node = firstNode(expression);
		if (node == null) {
			throw new RuntimeException("node not found for: " + expression + " in: " + input);
		}
		String textContent = node.getTextContent();
		return textContent;
	}

	public String getAttributeValue(Node node, String name) {
		return node.getAttributes().getNamedItem(name).getNodeValue();
	}

	public Map<String, String> getAttributes(Node node) {
		NamedNodeMap attributes = node.getAttributes();
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < attributes.getLength(); i++) {
			Node item = attributes.item(i);
			map.put(item.getNodeName(), item.getNodeValue());
		}
		return map;
	}

	public List<Map<String, String>> list(String expression) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		NodeList nodes = nodes(expression);
		for (int i = 0; i < nodes.getLength(); i++) {
			list.add(getAttributes(nodes.item(i)));
		}
		return list;
	}

	public boolean hasNode(String expression) {
		return nodes(expression).getLength() > 0;
	}
}
