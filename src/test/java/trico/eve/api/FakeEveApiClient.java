package trico.eve.api;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.apache.commons.httpclient.HttpMethod;

/**
 * @author nico.mainka
 */
public class FakeEveApiClient extends EveApiHttpClient {

	private FakeResponseFile testResponseFile;

	public void respondWithError() {
		respondWith("error.xml");
	}

	public void respondeWithOrders() {
		respondWith("orders.xml");
	}

	private void respondWith(String file) {
		testResponseFile = new FakeResponseFile(file);
	}

	@Override
	protected String executeMethodWithErrorHandling(HttpMethod method) {
		return respond();
	}

	private String respond() {
		assertThat("testResponseFile", testResponseFile, is(notNullValue()));
		return testResponseFile.getContent();
	}

}
