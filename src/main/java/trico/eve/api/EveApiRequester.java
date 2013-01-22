package trico.eve.api;

import java.util.Date;

import trico.eve.api.core.BasicResponse;
import trico.eve.api.core.EveApiDate;
import trico.eve.api.core.RequestCache;
import trico.eve.api.orders.MarketOrder;
import trico.eve.api.orders.MarketOrders;
import trico.eve.api.type.EveTypeNames;
import trico.eve.api.type.TypeName;
import trico.log.Log;

/**
 * @author nico.mainka
 */
public class EveApiRequester {

	private static final Log log = Log.getLog(EveApiRequester.class);

	private EveApiHttpClient apiHttpClient = new EveApiHttpClient();

	private RequestCache requestCache = new RequestCache();

	public void setApiHttpClient(EveApiHttpClient apiHttpClient) {
		this.apiHttpClient = apiHttpClient;
	}

	public void setRequestCache(RequestCache requestCache) {
		this.requestCache = requestCache;
	}

	public MarketOrders getMarketOrders() {
		log.debug("requesting marketOrders...");

		BasicResponse response = requestCache.getResponse(MarketOrders.class.getName());
		EveApiDate cachedUntil = response.getCachedUntil();

		if (response.isBeforeCachedUntil(new Date())) {
			log.debug("returning cached marketOrders cachedUntil=%s", cachedUntil.asLocal());
			return (MarketOrders) response;
		}

		MarketOrders marketOrders = new MarketOrders(apiHttpClient.getMarketOrders());
		for (MarketOrder order : marketOrders.getOrders()) {
			order.setTypeName(getTypeName(order.getTypeId()));
		}
		requestCache.put(MarketOrders.class.getName(), marketOrders);

		log.debug("returning new marketOrders cachedUntil%s", marketOrders.getCachedUntil().asLocal());
		return marketOrders;
	}

	public String getTypeName(Integer id) {
		log.debug("requesting typeName id=%s", id);

		String key = TypeName.class.getName() + "#" + id;
		BasicResponse response = requestCache.getResponse(key);
		EveApiDate cachedUntil = response.getCachedUntil();
		if (response.isBeforeCachedUntil(new Date())) {
			log.debug("returning cached typeName cachedUntil=%s", cachedUntil.asLocal());
			EveTypeNames typeNames = (EveTypeNames) response;
			return typeNames.get(id);
		}
		EveTypeNames eveTypeNames = new EveTypeNames(apiHttpClient.getEveTypeNames(id));
		requestCache.put(key, eveTypeNames);

		log.debug("returning new typeName cachedUntil%s", eveTypeNames.getCachedUntil().asLocal());
		return eveTypeNames.get(id);
	}
}
