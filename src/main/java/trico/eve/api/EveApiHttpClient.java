package trico.eve.api;

import java.io.StringWriter;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import trico.eve.api.error.Error;

/**
 * @author nico.mainka
 */
public class EveApiHttpClient {

	private static final Logger log = Logger.getLogger(EveApiHttpClient.class);

	private static final String BASE_URL = "https://api.eveonline.com/";

	private final HttpClient httpClient = new HttpClient();

	public String getAPIKeyInfo() {
		return doGet(buildUrl("account/APIKeyInfo"));
	}

	public String getMarketOrders() {
		return doGet(buildUrl("char/MarketOrders"));
	}

	public String getEveTypeNames(Integer id) {
		return doGet(buildUrl("eve/TypeName", new Param("ids", id.toString())));
	}

	static String buildUrl(String method, Param... params) {
		EveApiKey apiKey = new EveApiKey("1234567", "test_key_not_working");
		StringBuilder url = new StringBuilder(BASE_URL).append(method).append(".xml.aspx?");
		url.append(apiKey.asUrlParams());
		for (Param param : params) {
			url.append("&").append(param.name).append("=").append(param.value);
		}
		String string = url.toString();
		return string;
	}

	protected String doGet(String uri) {
		log.debug("url: " + uri);
		String content = executeMethodWithErrorHandling(new GetMethod(uri));
		if (Error.isError(content)) {
			Error error = new Error(content);
			throw new RuntimeException(error.toString());
		}
		return content;

	}

	protected String executeMethodWithErrorHandling(HttpMethod method) {
		method.addRequestHeader(HttpMethodParams.USER_AGENT, "trico.eve.dev");
		return read(method, exec(method));
	}

	private String read(HttpMethod method, int responseCode) {
		try {
			StringWriter stringWriter = new StringWriter();
			IOUtils.copy(method.getResponseBodyAsStream(), stringWriter, "UTF-8");
			if (responseCode != HttpStatus.SC_OK) {
				throw new RuntimeException(method.getResponseBodyAsString());
			}
			return stringWriter.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private int exec(HttpMethod method) {
		try {
			return httpClient.executeMethod(method);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	class Param {
		String name;

		String value;

		private Param(String name, String value) {
			this.name = name;
			this.value = value;
		}

	}

}
