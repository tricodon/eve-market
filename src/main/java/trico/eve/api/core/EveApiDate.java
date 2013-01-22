package trico.eve.api.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author nico.mainka
 */
public class EveApiDate extends Date {
	private static final long serialVersionUID = 1L;

	private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	public EveApiDate(String dateString) throws ParseException {
		super(new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).parse(dateString).getTime());
	}

	/** test... */
	public EveApiDate(long millis) {
		super(millis);
	}

	@Override
	public String toString() {
		return new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).format(this);
	}

	public Date asLocal() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		TimeZone utcZone = TimeZone.getTimeZone("UTC");
		simpleDateFormat.setTimeZone(utcZone);
		try {
			return simpleDateFormat.parse(toString());
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

}
