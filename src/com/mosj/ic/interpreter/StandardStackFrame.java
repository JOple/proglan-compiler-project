package com.mosj.ic.interpreter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;

import com.mosj.ic.value.Value;

public class StandardStackFrame implements StackFrame {

	private String name;
	private ProgramInformation info;
	private IOProvider io = StandardIOProvider.systemIO();
	private Stack<StackFrame> stackTrace = new Stack<>();
	private Map<Long, Value> values = new LinkedHashMap<>();
	private Map<String, Long> aliases = new LinkedHashMap<>();

	public StandardStackFrame() { }
	public StandardStackFrame(String name, ProgramInformation info, IOProvider io, Stack<StackFrame> stackTrace, Map<Long, Value> values, Map<String, Long> aliases) {
		this();
		this.name = name;
		this.io = io;
		this.info = info;
		this.stackTrace = stackTrace;
		this.values = values;
	}
	public StandardStackFrame(String name, ProgramInformation info, IOProvider io, Stack<StackFrame> stackTrace, Map<Long, Value> values) {
		this(name, info, io, stackTrace, values, new LinkedHashMap<>());
	}
	public StandardStackFrame(String name, ProgramInformation info, IOProvider io, Stack<StackFrame> stackTrace) {
		this(name, info, io, stackTrace, new LinkedHashMap<>());
	}
	public StandardStackFrame(String name, ProgramInformation info, Stack<StackFrame> stackTrace) {
		this(name, info, StandardIOProvider.systemIO(), stackTrace);
	}
	public StandardStackFrame(String name, StackFrame prev, Map<String, Long> aliases) {
		this(name, prev.getInfo(), prev.getIO(), prev.getStackTrace(), prev.getValues(), aliases);
	}
	public StandardStackFrame(String name, StackFrame prev) {
		this(name, prev, new LinkedHashMap<>());
	}

	@Override
	public String getFunctionName() {
		return name;
	}
	public void setFunctionName(String name) {
		this.name = name;
	}

	@Override
	public IOProvider getIO() {
		return io;
	}
	public void setIO(IOProvider io) {
		this.io = io;
	}

	@Override
	public ProgramInformation getInfo() {
		return info;
	}
	public void setInfo(ProgramInformation info) {
		this.info = info;
	}

	@Override
	public Stack<StackFrame> getStackTrace() {
		return stackTrace;
	}
	public void setStackTrace(Stack<StackFrame> stackTrace) {
		this.stackTrace = stackTrace;
	}

	@Override
	public Map<Long, Value> getValues() {
		return values;
	}
	public void setValues(Map<Long, Value> values) {
		this.values = values;
	}

	@Override
	public Map<String, Long> getAliases() {
		return aliases;
	}
	public void setAliases(Map<String, Long> aliases) {
		this.aliases = aliases;
	}
}
