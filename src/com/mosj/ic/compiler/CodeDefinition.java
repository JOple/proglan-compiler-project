package com.mosj.ic.compiler;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.ParserRuleContext;

import com.mosj.ic.definition.OperationDefinition;
import com.mosj.ic.interpreter.ins.InsType;
import com.mosj.ic.symbols.SymbolType;
import com.mosj.ic.symbols.Symbols;
import com.mosj.ic.value.ValueType;

public class CodeDefinition extends OperationDefinition {

	private static final long serialVersionUID = -1231506824291787430L;

	private transient List<CodeDefinition> contexts = new LinkedList<>();
	private transient List<ParserRuleContext> sources = new LinkedList<>();
	private transient SymbolType returnType;
	
	public CodeDefinition(InsType op, List<CodeDefinition> args) {
		super(op.getName(), args.stream().map(a -> a.getArgs().get(0)).collect(Collectors.toList()));
		this.contexts = args;
		this.returnType = Symbols.ofType(ValueType.VOID);
	}
	public CodeDefinition(String alias) {
		super(null, alias);
		this.returnType = Symbols.ofType(ValueType.VOID);
	}
	
	public String getFirst() {
		return getArgs().size() == 0 ? null : getArgs().get(0);
	}
	
	public List<CodeDefinition> getContexts() {
		return contexts;
	}
	public CodeDefinition setContexts(List<CodeDefinition> stmts) {
		this.contexts = stmts;
		return this;
	}
	public CodeDefinition setContexts(CodeDefinition... stmts) {
		return setContexts(Arrays.asList(stmts));
	}
	
	public List<ParserRuleContext> getSources() {
		return sources;
	}
	public ParserRuleContext getSource() {
		if(sources.size() == 0)
			return null;
		return sources.get(0);
	}
	public CodeDefinition setSources(List<ParserRuleContext> contexts) {
		this.sources = contexts;
		return this;
	}
	public CodeDefinition setSources(ParserRuleContext... contexts) {
		return setSources(Arrays.asList(contexts));
	}
	
	public SymbolType getReturnType() {
		return returnType;
	}
	public CodeDefinition setReturnType(SymbolType returnType) {
		this.returnType = returnType;
		return this;
	}
	public CodeDefinition setReturnType(ValueType returnType) {
		return setReturnType(Symbols.ofType(returnType));
	}
	public CodeDefinition setReturnType(String returnType) {
		return setReturnType(Symbols.ofType(returnType));
	}
}
