package trico.eve.api;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author nico.mainka
 */
public class EveApiHttpClientTest {

	private EveApiHttpClient apiClient;

	@Before
	public void setUp() {
		apiClient = new EveApiHttpClient();
	}

	@Test
	@Ignore("live connection yet")
	public void testWebClient() {
		String result = apiClient.getMarketOrders();
		System.out.println(result);
	}

	@Test
	public void buildUrl() throws Exception {
		String url = EveApiHttpClient.buildUrl("test/method");
		String expectedUrl = "https://api.eveonline.com/" + //
				"test/method.xml.aspx?" + //
				"keyID=1234567&" + //
				"vCode=test_key_not_working";
		assertThat(url, is(expectedUrl));
	}

}
