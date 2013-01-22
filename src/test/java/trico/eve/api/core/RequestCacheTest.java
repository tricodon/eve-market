package trico.eve.api.core;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Before;
import org.junit.Test;

import trico.eve.api.orders.MarketOrders;

/**
 * @author nico.mainka
 */
public class RequestCacheTest {

	private RequestCache requestCache;

	@Before
	public void setUp() throws Exception {
		requestCache = new RequestCache();
	}

	@Test
	public void responseNotCached() {
		BasicResponse hasValidResponseFor = requestCache.getResponse(MarketOrders.class.getName());
		assertThat(hasValidResponseFor.isBeforeCachedUntil(new Date()), is(false));
	}

	@Test
	public void responseCachedUntilElapsed() throws Exception {
		EveApiDate eleapsedEveApiDate = new EveApiDate(DateUtils.addDays(new Date(), -1).getTime());
		requestCache.put(MarketOrders.class.getName(), new TestResponse(eleapsedEveApiDate.toString()));
		assertThat(requestCache.getResponse(MarketOrders.class.getName()).isBeforeCachedUntil(new Date()), is(false));
	}

	@Test
	public void responseCachedUntilNotElapsed() throws Exception {
		EveApiDate eleapsedEveApiDate = new EveApiDate(DateUtils.addDays(new Date(), +1).getTime());
		requestCache.put(MarketOrders.class.getName(), new TestResponse(eleapsedEveApiDate.toString()));
		assertThat(requestCache.getResponse(MarketOrders.class.getName()), is(notNullValue()));
	}

	class TestResponse extends BasicResponse {

		public TestResponse(String cachedUntil) {
			super(String.format("<eveApi>" + //
					"<currentTime>2012-10-10 12:36:34</currentTime>" + //
					"<cachedUntil>%s</cachedUntil>" + //
					"</eveApi>", cachedUntil));
		}

	}
}
