package trico.eve.api.orders;

/**
 * @author nico.mainka
 */
public enum OrderState {

	ACTIVE(0), CLOSED(1), EXPIRED(2), CANCELLED(3), PENDING(4), CHAR_DELETED(5);

	private long id;

	private OrderState(long id) {
		this.id = id;
	}

	static OrderState find(long id) {
		for (OrderState orderState : values()) {
			if (orderState.id == id) {
				return orderState;
			}
		}
		throw new RuntimeException("orderState not found for: " + id);
	}
}
