package trico.eve.api.core;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

/**
 * @author nico.mainka
 */
public class EveApiDateTest {

	@Before
	public void setUp() throws Exception {}

	@Test
	public void convertToLocalTimeZone() throws Exception {
		EveApiDate eveApiDate = new EveApiDate("2012-11-08 22:00:39");
		Date localDate = eveApiDate.asLocal();
		System.out.println(localDate);
	}

}
