package com.mosj.ic.log;

public class LogTraceException extends RuntimeException {

	private static final long serialVersionUID = -3168249856522865637L;

	public LogTraceException() {
		super();
	}
	protected LogTraceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public LogTraceException(String message, Throwable cause) {
		super(message, cause);
	}
	public LogTraceException(String message) {
		super(message);
	}
	public LogTraceException(Throwable cause) {
		super(cause);
	}
}
