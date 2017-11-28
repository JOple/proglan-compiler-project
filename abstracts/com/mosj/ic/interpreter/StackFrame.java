package com.mosj.ic.interpreter;

import java.util.Map;
import java.util.Stack;

import com.mosj.ic.value.Value;

public interface StackFrame {

	String getFunctionName();
	ProgramInformation getInfo();
	IOProvider getIO();
	
	Stack<StackFrame> getStackTrace();
	Map<Long, Value> getValues();
	Map<String, Long> getAliases();

	/*Integer[] malloc(List<Value> val);
	default Integer[] malloc(Value... val) {
		return malloc(Arrays.asList(val));
	}
	default Integer malloc(Value val) {
		return malloc(new Value[] {val})[0];
	}

	default void mfree(List<Integer> addr) {
		for(Integer i : addr)
			delValue(i);
	}
	default void mfree(Integer... addr) {
		mfree(Arrays.asList(addr));
	}

	default Value value(Integer addr) {
		return getValues().get(addr);
	}
	default void value(Integer addr, Value val) {
		if(val == null)
			delValue(addr);
		else
			getValues().put(addr, val);
	}
	default void delValue(Integer... addr) {
		for(Integer i : addr)
			getValues().remove(i);
	}

	default Integer alias(String name) {
		return getAliases().get(name);
	}
	default void alias(String name, Integer addr) {
		if(addr == null)
			delAlias(name);
		else
			getAliases().put(name, addr);
	}
	default void delAlias(String... name) {
		for(String n : name)
			getAliases().remove(n);
	}

	default Value load(String alias) {
		Integer addr = alias(alias);
		if(addr == null)
			throw new IllegalStateException("Variable does not exists: " + alias);
		Value val = value(addr);
		if(val == null)
			throw new IllegalStateException("Address does not exists: " + addr);
		return val;
	}
	default Integer store(String alias, Value val) {
		Integer addr = alias(alias);
		if(addr == null)
			addr = malloc(val);
		alias(alias, addr);
		return addr;
	}*/
}
