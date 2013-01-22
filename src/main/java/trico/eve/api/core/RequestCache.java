package trico.eve.api.core;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author nico.mainka
 */
public class RequestCache {

	private final Map<String, BasicResponse> cache = new HashMap<String, BasicResponse>();

	public void put(String key, BasicResponse response) {
		cache.put(key, response);
	}

	public <T extends BasicResponse> BasicResponse getResponse(String key) {
		BasicResponse response = cache.get(key);
		if (response == null) {
			return new NotCached("");
		}
		return response;
	}

	class NotCached extends BasicResponse {

		public NotCached(String content) {
			super(content);
		}

		@Override
		public boolean isBeforeCachedUntil(Date currentLocalDate) {
			return false;
		}

		@Override
		protected void parse(String input) {
			// do not parse
		}
	}

}
