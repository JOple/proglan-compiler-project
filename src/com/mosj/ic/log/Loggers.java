package com.mosj.ic.log;

public class Loggers {

	public static BasicLogger create(Class<?> clz) {
		return new BasicLogger(clz);
	}
}
