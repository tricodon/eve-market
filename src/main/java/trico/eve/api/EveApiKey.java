package trico.eve.api;

/**
 * @author nico.mainka
 */
public class EveApiKey {

	private final String id;

	private final String code;

	public EveApiKey(String id, String code) {
		this.id = id;
		this.code = code;
	}

	public String asUrlParams() {
		return String.format("keyID=%s&vCode=%s", id, code);
	}
}
