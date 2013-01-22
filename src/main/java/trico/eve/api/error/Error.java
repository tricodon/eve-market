package trico.eve.api.error;

import org.w3c.dom.Node;

import trico.eve.api.core.BasicResponse;
import trico.eve.api.core.XPathDocument;

/**
 * @author nico.mainka
 */
public class Error extends BasicResponse {

	private Integer code;

	private String message;

	public Error(String content) {
		super(content);
	}

	@Override
	protected void parse(String input) {
		super.parse(input);
		Node node = xPathDocument.firstNode("//error");
		code = Integer.parseInt(xPathDocument.getAttributeValue(node, "code"));
		message = node.getTextContent();
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public static boolean isError(String content) {
		XPathDocument pathDocument = new XPathDocument(content);
		return pathDocument.hasNode("//error");
	}

}
