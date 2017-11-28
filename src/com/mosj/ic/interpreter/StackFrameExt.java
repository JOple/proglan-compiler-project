package com.mosj.ic.interpreter;

import java.io.Serializable;
import java.util.Map;
import java.util.Stack;
import java.util.UUID;

import com.mosj.ic.interpreter.IOProvider;
import com.mosj.ic.interpreter.ProgramInformation;
import com.mosj.ic.interpreter.StackFrame;
import com.mosj.ic.value.Value;
import com.mosj.ic.value.ValueType;
import com.mosj.ic.value.Values;

public class StackFrameExt implements StackFrame {
	
	public static StackFrameExt of(StackFrame sf) {
		return new StackFrameExt(sf);
	}
	
	private static long COUNT = System.currentTimeMillis();
	
	private StackFrame basis;

	public StackFrameExt(StackFrame s) {
		this.basis = s;
	}

	public StackFrameExt setAddr(String alias, Long addr) {
		getAliases().put(alias, addr);
		return this;
	}
	
	public StackFrameExt setType(Long addr, Value val) {
		if(getValues().containsKey(addr))
			val.set(getValues().get(addr).get());
		getValues().put(addr, val);
		return this;
	}
	public StackFrameExt setType(Long addr, ValueType dt) {
		setType(addr, dt.gen());
		return this;
	}
	public StackFrameExt setType(String alias, Value val) {
		setType(getAddr(alias), val);
		return this;
	}
	public StackFrameExt setType(String alias, ValueType dt) {
		setType(alias, dt.gen());
		return this;
	}
	
	public StackFrameExt setVal(Long addr, Value val) {
		getVal(addr).set(val.get());
		return this;
	}
	public StackFrameExt setVal(Long addr, Serializable val) {
		setVal(addr, Values.ofAny(val));
		return this;
	}
	public StackFrameExt setVal(String alias, Value val) {
		setVal(getAddr(alias), val);
		return this;
	}
	public StackFrameExt setVal(String alias, Serializable val) {
		setVal(alias, Values.ofAny(val));
		return this;
	}

	public Long getAddr(String alias) {
		if(!getAliases().containsKey(alias))
			getAliases().put(alias, COUNT++);
		return getAliases().get(alias);
	}
	public Long getAddr() {
		String alias = UUID.randomUUID().toString();
		getAddr(alias);
		return getAliases().remove(alias);
	}
	public Value getVal(Long addr) {
		if(!getValues().containsKey(addr))
			setType(addr, ValueType.OBJECT);
		return getValues().get(addr);
	}
	public Value getVal(String alias) {
		return getVal(getAddr(alias));
	}
	
	public StackFrame getBasis() {
		return basis;
	}

	@Override
	public String getFunctionName() {
		return basis.getFunctionName();
	}
	@Override
	public ProgramInformation getInfo() {
		return basis.getInfo();
	}
	@Override
	public IOProvider getIO() {
		return basis.getIO();
	}
	@Override
	public Stack<StackFrame> getStackTrace() {
		return basis.getStackTrace();
	}
	@Override
	public Map<Long, Value> getValues() {
		return basis.getValues();
	}
	@Override
	public Map<String, Long> getAliases() {
		return basis.getAliases();
	}
}
