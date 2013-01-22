package trico.log;

/**
 * @author nico.mainka
 */
public class Log {

	public static <T> Log getLog(Class<T> clazz) {
		return new Log(clazz);
	}

	private final LogAdapter logAdapter;

	private <T> Log(Class<T> clazz) {
		logAdapter = new Log4jAdapter<T>(clazz);
	}

	public void trace(String format, Object args) {
		logAdapter.trace(String.format(format, args));
	}

	public void debug(String format, Object... args) {
		logAdapter.debug(format(format, args));
	}

	private String format(String format, Object... args) {
		return String.format(format, args);
	}

	public void info(String format, Object... args) {
		logAdapter.info(format(format, args));
	}

	public void warn(String format, Object... args) {
		logAdapter.warn(format(format, args));
	}

	public void error(String format, Object... args) {
		logAdapter.error(format(format, args));
	}

	public void error(String format, Throwable throwable, Object... args) {
		logAdapter.error(format(format, args), throwable);
	}

}
