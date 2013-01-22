package trico.eve.api;

import static junit.framework.Assert.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import trico.eve.api.orders.MarketOrders;

/**
 * @author nico.mainka
 */
public class EveApiRequesterTest {

	private EveApiRequester eveApiRequester;

	private FakeEveApiClient testEveApiClient;

	@Before
	public void setUp() {
		testEveApiClient = new FakeEveApiClient();
		eveApiRequester = new EveApiRequester();
		eveApiRequester.setApiHttpClient(testEveApiClient);
	}

	@Test
	public void error() throws Exception {
		try {
			testEveApiClient.respondWithError();
			eveApiRequester.getMarketOrders();
			fail("expected exception");
		} catch (Exception expected) {}
	}

	@Test
	public void marketOrders() {
		testEveApiClient.respondeWithOrders();
		MarketOrders marketOrders = eveApiRequester.getMarketOrders();
		assertThat(marketOrders, is(notNullValue()));
		assertThat(marketOrders.getOrders().size(), is(59));
		System.out.println(marketOrders);
	}

}
