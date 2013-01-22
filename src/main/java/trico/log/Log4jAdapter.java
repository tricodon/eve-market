package trico.log;

import org.apache.log4j.Logger;

/**
 * @author nico.mainka
 * @param <T>
 */
public class Log4jAdapter<T> implements LogAdapter {

	private final Logger logger;

	public Log4jAdapter(Class<T> loggerClass) {
		logger = Logger.getLogger(loggerClass);
	}

	@Override
	public void trace(Object message) {
		logger.trace(message);
	}

	@Override
	public void debug(Object message) {
		logger.debug(message);
	}

	@Override
	public void info(Object message) {
		logger.info(message);
	}

	@Override
	public void warn(Object message) {
		logger.warn(message);
	}

	@Override
	public void error(Object message) {
		logger.error(message);
	}

	@Override
	public void error(Object message, Throwable throwable) {
		logger.error(message, throwable);
	}

}
