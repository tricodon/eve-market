package trico.log;

/**
 * @author nico.mainka
 */
public interface LogAdapter {

	public void trace(Object message);

	public void debug(Object message);

	public void info(Object message);

	public void warn(Object message);

	public void error(Object message);

	public void error(Object message, Throwable throwable);
}
