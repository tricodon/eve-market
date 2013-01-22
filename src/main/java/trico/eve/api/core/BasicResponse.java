package trico.eve.api.core;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class BasicResponse extends EveApiObject {

	private EveApiDate currentTime;

	private EveApiDate cachedUntil;

	protected XPathDocument xPathDocument;

	public BasicResponse(String content) {
		parse(content);
	}

	public EveApiDate getCurrentTime() {
		return currentTime;
	}

	public EveApiDate getCachedUntil() {
		return cachedUntil;
	}

	protected void parse(String input) {
		xPathDocument = new XPathDocument(input);
		try {
			currentTime = new EveApiDate(xPathDocument.getTextContent("//currentTime"));
			cachedUntil = new EveApiDate(xPathDocument.getTextContent("//cachedUntil"));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean isBeforeCachedUntil(Date currentLocalDate) {
		return cachedUntil.asLocal().after(currentLocalDate);
	}

	protected List<Map<String, String>> rows() {
		return xPathDocument.list("//row");
	}

}
