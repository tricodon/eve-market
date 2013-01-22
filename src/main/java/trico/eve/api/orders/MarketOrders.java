package trico.eve.api.orders;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import trico.eve.api.core.BasicResponse;

/**
 * @author nico.mainka
 */
public class MarketOrders extends BasicResponse {

	private List<MarketOrder> orders;

	public MarketOrders(String content) {
		super(content);
	}

	@Override
	protected void parse(String input) {
		super.parse(input);
		orders = new ArrayList<MarketOrder>();
		for (Map<String, String> attributes : rows()) {
			orders.add(new MarketOrder(attributes));
		}
	}

	public List<MarketOrder> getOrders() {
		return orders;
	}
}
