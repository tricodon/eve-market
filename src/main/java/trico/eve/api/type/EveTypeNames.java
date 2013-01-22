package trico.eve.api.type;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import trico.eve.api.core.BasicResponse;

/**
 * @author nico.mainka
 */
public class EveTypeNames extends BasicResponse {

	private List<TypeName> typeNames;

	public EveTypeNames(String content) {
		super(content);
	}

	@Override
	protected void parse(String input) {
		super.parse(input);
		typeNames = new ArrayList<TypeName>();
		for (Map<String, String> attributes : rows()) {
			typeNames.add(new TypeName(attributes));
		}
	}

	public String get(Integer id) {
		for (TypeName typeName : typeNames) {
			if (typeName.getId() == id) {
				return typeName.getName();
			}
		}
		return String.valueOf(id);
	}

	public List<TypeName> getTypeNames() {
		return typeNames;
	}
}
