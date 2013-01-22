package trico.eve.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import trico.eve.api.EveApiRequester;
import trico.eve.api.orders.MarketOrders;

/**
 * @author nico.mainka
 */
@Repository
public class EveDataRepository {

	@Autowired
	private EveApiRequester eveApiRequester;

	public MarketOrders getMarketOrders() {
		return eveApiRequester.getMarketOrders();
	}
}
