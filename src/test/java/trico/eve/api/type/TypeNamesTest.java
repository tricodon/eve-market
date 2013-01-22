package trico.eve.api.type;

import org.junit.Before;
import org.junit.Test;

import trico.eve.api.FakeResponseFile;

/**
 * @author nico.mainka
 */
public class TypeNamesTest {

	private EveTypeNames typeNames;

	@Before
	public void setUp() throws Exception {
		FakeResponseFile responseFile = new FakeResponseFile("typeNames.xml");
		typeNames = new EveTypeNames(responseFile.getContent());
	}

	@Test
	public void test() throws Exception {
		System.out.println(typeNames);
	}
}
