package trico.eve.api.orders;

/**
 * @author nico.mainka
 */
public enum OrderRange {
	SELL(32767);
	private int id;

	private OrderRange(int id) {
		this.id = id;
	}

}
