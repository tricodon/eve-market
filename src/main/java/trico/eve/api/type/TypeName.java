package trico.eve.api.type;

import java.util.Map;

import trico.eve.api.core.EveApiObject;

/**
 * @author nico.mainka
 */
public class TypeName extends EveApiObject {

	private final long id;

	private final String name;

	public TypeName(Map<String, String> attributes) {
		id = Long.parseLong(attributes.get("typeID"));
		name = attributes.get("typeName");
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
