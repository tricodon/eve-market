package trico.eve.api.orders;

import org.junit.Before;
import org.junit.Test;

import trico.eve.api.FakeResponseFile;

/**
 * @author nico.mainka
 */
public class MarketOrdersXPathTest {

	private MarketOrders marketOrders;

	@Before
	public void setUp() throws Exception {
		FakeResponseFile responseFile = new FakeResponseFile("orders.xml");
		marketOrders = new MarketOrders(responseFile.getContent());
	}

	@Test
	public void test() throws Exception {
		System.out.println(marketOrders);
	}
}
