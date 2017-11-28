package com.mosj.ic.log;

public interface StandardLogger extends Logger<Log> {

	default void log(LogType t, String c, Object p) {
		log(new Log(t, c, p));
	}
	default void log(LogType t, String c) {
		log(t, c, null);
	}
	
	default void debug(String c) {
		log(LogType.DEBUG, c);
	}
	default void msg(String c) {
		log(LogType.MESSAGE, c);
	}
	default void warn(String c) {
		log(LogType.WARNING, c);
	}
	default void error(String c) {
		log(LogType.ERROR, c);
	}
	default void fatal(String c) {
		log(LogType.FATAL_ERROR, c);
	}

	default void error(String c, Exception e) {
		log(LogType.ERROR, c, e);
	}
	default void fatal(String c, Exception e) {
		log(LogType.FATAL_ERROR, c, e);
	}
}
