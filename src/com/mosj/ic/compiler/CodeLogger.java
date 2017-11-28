package com.mosj.ic.compiler;

import org.antlr.v4.runtime.ParserRuleContext;

import com.mosj.ic.definition.FunctionDefinition;
import com.mosj.ic.log.BasicLogger;

public class CodeLogger extends BasicLogger {

	public static CodeLogger create(Class<?> basis) {
		return new CodeLogger(basis);
	}
	
	public CodeLogger(Class<?> basis) {
		super(basis);
	}

	public void ambiguity(int start, int end, String msg) {
		error(String.format("[index %s:%s][%s]:: %s",
				start,
				end,
				"Ambiguity Detected",
				msg));
	}
	public void sslConflic(int start, int end, String msg) {
		error(String.format("[index %s:%s][%s]:: %s",
				start,
				end,
				"SSL Conflict",
				msg));
	}
	public void ctxSensitivity(int start, int end, String msg) {
		error(String.format("[index %s:%s][%s]:: %s",
				start,
				end,
				"Context Sensitivity",
				msg));
	}
	public void syntaxErr(int line, int charPos, String msg) {
		error(String.format("[line %s:%s][%s]:: %s",
				line,
				charPos,
				"Syntax Error",
				msg));
	}

	public void unknownToken(ParserRuleContext p) {
		error(String.format("[line %s:%s][%s]:: The token '%s' is not recognized",
				p.start.getLine(),
				p.start.getCharPositionInLine(),
				"Unknown Token",
				p.getText()));
	}

	public void badDesign(ParserRuleContext p, String msg) {
		error(String.format("[line %s:%s][%s]:: %s",
				p.start.getLine(),
				p.start.getCharPositionInLine(),
				"Bad Design",
				msg));
	}
	public void invalidOp(ParserRuleContext p, String msg) {
		error(String.format("[line %s:%s][%s]:: %s",
				p.start.getLine(),
				p.start.getCharPositionInLine(),
				"Invalid Operation",
				msg));
	}
	public void invalidArg(ParserRuleContext p, String msg) {
		error(String.format("[line %s:%s][%s]:: %s",
				p.start.getLine(),
				p.start.getCharPositionInLine(),
				"Invalid Operation",
				msg));
	}
	
	
	
	public void typeMismatch(ParserRuleContext p, String typeExpected, String typeAccepted) {
		error(String.format("[line %s:%s][%s]:: Expects a '%s' but got '%s' instead",
				p.start.getLine(),
				p.start.getCharPositionInLine(),
				"Type Mismatch",
				typeExpected,
				typeAccepted));
	}
	
	public void missing(ParserRuleContext p, String type, String name) {
		error(String.format("[line %s:%s][%s]::  %s '%s' is not declared",
				p.start.getLine(),
				p.start.getCharPositionInLine(),
				"Missing Entity",
				type,
				name));
	}

	public void dupEntity(ParserRuleContext p, String type, String name) {
		error(String.format("[line %s:%s][%s]::  %s '%s' is already declared",
				p.start.getLine(),
				p.start.getCharPositionInLine(),
				"Duplicate Variable",
				type,
				name));
	}
	
	public void constRe(ParserRuleContext p, String constName) {
		error(String.format("[line %s:%s][%s]:: The value of '%s' cannot be reassigned",
				p.start.getLine(),
				p.start.getCharPositionInLine(),
				"Const Reassign",
				constName));
	}

	public void paramMismatch(ParserRuleContext p, FunctionDefinition f, String msg) {
		error(String.format("[line %s:%s][%s]:: On calling function '%s': %s",
				p.start.getLine(),
				p.start.getCharPositionInLine(),
				"Param Mismatch",
				f.getName(),
				msg));
	}
	
	public void outBound(ParserRuleContext p, String array, String size, String index) {
		error(String.format("[line %s:%s][%s]:: Array '%s' has size of %s but assigned with %s",
				p.start.getLine(),
				p.start.getCharPositionInLine(),
				"Out of Bounds",
				array,
				size,
				index));
	}
}
