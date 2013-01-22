package trico.eve.web;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import trico.eve.api.orders.MarketOrder;
import trico.eve.api.orders.MarketOrders;

/**
 * @author nico.mainka
 */
@ManagedBean
@SessionScoped
public class OrdersBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{eveDataRepository}")
	private EveDataRepository eveDataRepository;

	private Date cachedUntil;

	@PostConstruct
	public void init() {}

	public void setEveDataRepository(EveDataRepository eveDataRepository) {
		this.eveDataRepository = eveDataRepository;
	}

	public List<MarketOrder> getMarketOrders() {
		MarketOrders marketOrders = eveDataRepository.getMarketOrders();
		cachedUntil = marketOrders.getCachedUntil().asLocal();
		return marketOrders.getOrders();
	}

	public Date getCachedUntil() {
		return cachedUntil;
	}
}
