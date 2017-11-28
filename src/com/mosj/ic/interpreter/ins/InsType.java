package com.mosj.ic.interpreter.ins;

import java.util.function.Supplier;

import com.mosj.ic.interpreter.Instruction;
import com.mosj.ic.interpreter.ins.bool.*;
import com.mosj.ic.interpreter.ins.ctrl.*;
import com.mosj.ic.interpreter.ins.func.*;
import com.mosj.ic.interpreter.ins.io.*;
import com.mosj.ic.interpreter.ins.jump.*;
import com.mosj.ic.interpreter.ins.math.*;
import com.mosj.ic.interpreter.ins.val.*;

public enum InsType {

	// CONTROL INSTRUCTIONS
	EXIT("exit", "ctrl", "exit", "Stop execution", 1, () -> new ExitIns()),
	TRAP("trap", "ctrl", "trap", "Halt execution", 0, () -> new TrapIns()),
	LABEL("label", "ctrl", "label", "Mark address", 1, () -> new LabelIns()),
	// VALUE INSTRUCTIONS
	ADDR("addr", "val", "addr", "Retrieve address", 2, () -> new AddrIns()),
	DEREF("deref", "val", "deref", "Retrieve value from address", 2, () -> new DerefIns()),
	DECL("decl", "val", "=>", "Declare value", 2, () -> new DeclIns()),
	STORE("store", "val", "<-", "Store value", 2, () -> new StoreIns()),
	ASSIGN("assign", "val", "=", "Pass reference", 2, () -> new AssignIns()),
	COPY("copy", "val", "<<", "Copy value", 2, () -> new CopyIns()),
	SPACE("spc", "val", "__", "Reserve space", 2, () -> new SpaceIns()),
	// JUMP INSTRUCTIONSS
	JUMP("j", "jump", "j", "Unconditional Jump", 1, () -> new JumpIns()),
	JUMPT("jt", "jump", "jt", "Jump if true", 2, () -> new JumptIns()),
	JUMPF("jf", "jump", "jf", "Jump if false", 2, () -> new JumpfIns()),
	// FUNCTION INSTRUCTIONS
	CALL("call", "func", "call", "Call function", 1, () -> new CallIns()),
	RET("ret", "func", "ret", "Pop stackframe", 1, () -> new RetIns()),
	//BOOLEAN INSTRUCTONS
	EQ("eq", "bool", "==", "Equals", 3, () -> new EqIns()),
	NEQ("neq", "bool", "!=", "Not equals", 3, () -> new NeqIns()),
	LT("lt", "bool", "<", "Less than", 3, () -> new LtIns()),
	LTE("lte", "bool", "<=", "Less than or equals", 3, () -> new LteIns()),
	GT("gt", "bool", ">", "Greater than", 3, () -> new GtIns()),
	GTE("gte", "bool", ">=", "Greater than or equals", 3, () -> new GteIns()),
	AND("and", "bool", "&&", "Logical and", 3, () -> new AndIns()),
	OR("or", "bool", "||", "Logical or", 3, () -> new OrIns()),
	NOT("not", "bool", "!", "LogicaL not", 2, () -> new NotIns()),
	// MATH INSTRUCTIONS
	ADD("add", "math", "+", "Add", 3, () -> new AddIns()),
	SUB("sub", "math", "-", "Subtract", 3, () -> new SubIns()),
	MUL("mul", "math", "*", "Multiply", 3, () -> new MulIns()),
	DIV("div", "math", "/", "Divide", 3, () -> new DivIns()),
	MOD("mod", "math", "%", "Modulo", 3, () -> new ModIns()),
	// IO INSTRUCTIONS
	SCAN("scan", "io", "in", "Scan line", 1, () -> new ScanIns()),
	PRINT("print", "io", "out", "Print string", 1, () -> new PrintIns());
	
	private String name;
	private String type;
	private String symbol;
	private String description;
	private int paramCount;
	private Supplier<Instruction> insProvider;

	private InsType(String name, String type, String symbol, String description, int paramCount, Supplier<Instruction> insProvider) {
		this.name = name;
		this.type = type;
		this.symbol = symbol;
		this.description = description;
		this.insProvider = insProvider;
		this.paramCount = paramCount;
	}
	
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public String getSymbol() {
		return symbol;
	}
	public String getDescription() {
		return description;
	}
	public int getParamCount() {
		return paramCount;
	}
	public Instruction getInstruction() {
		return insProvider.get();
	}
}
