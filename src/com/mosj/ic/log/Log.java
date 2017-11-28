package com.mosj.ic.log;

public class Log {

	private LogType type;
	private String content;
	private Object payload;
	private LogTraceException trace = new LogTraceException();
	private int traceOffset = 1;
	
	public Log() { }
	public Log(LogType type, String content) {
		this();
		this.type = type;
		this.content = content;
	}
	public Log(LogType type, String content, Object payload) {
		this(type, content);
		this.payload = payload;
	}
	
	public LogType getType() {
		return type;
	}
	public void setType(LogType type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Object getPayload() {
		return payload;
	}
	public void setPayload(Object payload) {
		this.payload = payload;
	}

	public LogTraceException getTrace() {
		return trace;
	}
	public void setTrace(LogTraceException trace) {
		this.trace = trace;
	}
	
	public int getTraceOffset() {
		return traceOffset;
	}
	public void setTraceOffset(int traceOffset) {
		this.traceOffset = traceOffset;
	}

	public void trace() {
		trace = new LogTraceException();
	}

	@Override
	public String toString() {
		if(type != LogType.DEBUG || trace == null)
			return type + ": " + content;
		StackTraceElement s = trace.getStackTrace()[traceOffset];
		return String.format("%s.%s(%s:%s): %s: %s",
				s.getClassName(),
				s.getMethodName(),
				s.getFileName(),
				s.getLineNumber(),
				type,
				content);
	}
}
