package com.mosj.ic.log;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BasicLogger implements StandardLogger {

	private List<Log> logs = new LinkedList<>();
	private Class<?> basis;
	
	public BasicLogger(Class<?> basis) {
		this.basis = basis;
	}

	@Override
	public void log(Log log) {
		int i = 0;
		String clzName = basis.getName();
		while(!log.getTrace().getStackTrace()[i].getClassName().equals(clzName))
			i++;
		log.setTraceOffset(i);
		logs.add(log);
	}

	public List<Log> logs() {
		return Collections.unmodifiableList(logs);
	}
}
