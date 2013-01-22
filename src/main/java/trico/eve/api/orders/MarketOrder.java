package trico.eve.api.orders;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import trico.eve.api.core.EveApiDate;
import trico.eve.api.core.EveApiObject;

/**
 * @author nico.mainka
 */
public class MarketOrder extends EveApiObject {

	public MarketOrder(Map<String, String> attributes) {
		orderId = Long.parseLong(attributes.get("orderID"));
		characterId = Long.parseLong(attributes.get("charID"));
		stationId = Long.parseLong(attributes.get("stationID"));
		volEntered = Long.parseLong(attributes.get("volEntered"));
		volRemaining = Long.parseLong(attributes.get("volRemaining"));
		minVolume = Long.parseLong(attributes.get("minVolume"));
		orderState = Integer.parseInt(attributes.get("orderState"));
		typeId = Integer.parseInt(attributes.get("typeID"));
		range = Integer.parseInt(attributes.get("range"));
		accountKey = Integer.parseInt(attributes.get("accountKey"));
		duration = Integer.parseInt(attributes.get("duration"));
		escrow = new BigDecimal(attributes.get("escrow"));
		price = new BigDecimal(attributes.get("price"));
		bid = StringUtils.containsIgnoreCase("1", attributes.get("bid")) ? true : false;
		try {
			issued = new EveApiDate(attributes.get("issued"));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	private final Long orderId;

	private final Long characterId;

	private final Long stationId;

	private final Long volEntered;

	private final Long volRemaining;

	private final Long minVolume;

	private final Integer orderState;

	private final Integer typeId;

	private final Integer range;

	private final Integer accountKey;

	private final Integer duration;

	private final BigDecimal escrow;

	private final BigDecimal price;

	private final boolean bid;

	private final Date issued;

	private String typeName;

	public Long getOrderId() {
		return orderId;
	}

	public Long getCharacterId() {
		return characterId;
	}

	public Long getStationId() {
		return stationId;
	}

	public Long getVolEntered() {
		return volEntered;
	}

	public Long getVolRemaining() {
		return volRemaining;
	}

	public Long getMinVolume() {
		return minVolume;
	}

	public OrderState getOrderState() {
		return OrderState.find(orderState);
	}

	public Integer getTypeId() {
		return typeId;
	}

	public String getRange() {
		if (!bid) {
			return "Sell";
		}
		String value = "Buy";
		if (range == -1) {
			value = "Station";
		} else if (range == 0) {
			value = "Solar System";
		} else if (range > 0 && range < 32767) {
			value = range + " Jumps";
		}
		return value;
	}

	public Integer getAccountKey() {
		return accountKey;
	}

	public Integer getDuration() {
		return duration;
	}

	public BigDecimal getEscrow() {
		return escrow;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public boolean isBid() {
		return bid;
	}

	public Date getIssued() {
		return issued;
	}

	@Override
	public String toString() {
		return "\n  " + super.toString();
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeName() {
		return typeName != null ? typeName : typeId.toString();
	}

}
