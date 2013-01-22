package trico.eve.api;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

/**
 * @author nico.mainka
 */
public class FakeResponseFile {

	private String input;

	public FakeResponseFile(String name) {
		try {
			input = FileUtils.readFileToString(new ClassPathResource(name).getFile());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String getContent() {
		return input;
	}

}
