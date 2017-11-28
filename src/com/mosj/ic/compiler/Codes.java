package com.mosj.ic.compiler;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.mosj.ic.definition.OperationDefinition;
import com.mosj.ic.interpreter.Reg;
import com.mosj.ic.interpreter.ins.InsType;
import com.mosj.ic.value.ValueType;

public class Codes {

	public static Codes create() {
		return new Codes();
	}
	
	private List<OperationDefinition> ops = new LinkedList<>();

	private String labelTemplate = "#L%s";
	private String autovarTemplate = "~t%s";
	
	private int labelCount = 0;
	private int autovarCount = 0;

	private Codes() { }

	/* ************************************************
	 * Core Methods
	 * ************************************************/
	/**
	 * Adds the operation to the list
	 * @param od Operation definition
	 * @return The same operation definition
	 */
	public OperationDefinition addOp(OperationDefinition od) {
		ops.add(od);
		return od;
	}
	/**
	 * Create operation definition
	 * @param op Operator
	 * @param args Arguments
	 * @return An operation definition
	 */
	public CodeDefinition op(InsType op, List<CodeDefinition> args) {
		CodeDefinition od = new CodeDefinition(op, args);
		addOp(od);
		return od;
	}
	/**
	 * Create operation definition
	 * @param op Operator
	 * @param args Arguments
	 * @return An operation definition
	 */
	public CodeDefinition op(InsType op, CodeDefinition... args) {
		return op(op, Arrays.asList(args));
	}
	
	/**
	 * Variable name
	 * @param n Name
	 * @return An operation definition
	 */
	public CodeDefinition var(Serializable n) {
		return new CodeDefinition(n + "");
	}
	
	/* ************************************************
	 * Support Methods
	 * ************************************************/
	/**
	 * Operation of operation definition
	 * @param od Operation definition
	 * @return Operation
	 */
	public String op(CodeDefinition od) {
		return od.getOp();
	}
	/**
	 * Argument 1 of operation definition
	 * @param od Operation definition
	 * @return Argument 1 (target)
	 */
	public String t(CodeDefinition od) {
		return od.getArgs().get(0);
	}
	/**
	 * Argument 2 of operation definition
	 * @param od Operation definition
	 * @return Argument 2 (actual arg1 of operation)
	 */
	public String a1(CodeDefinition od) {
		return od.getArgs().get(1);
	}
	/**
	 * Argument 3 of operation definition
	 * @param od Operation definition
	 * @return Argument 3 (actual arg2 of operation)
	 */
	public String a2(CodeDefinition od) {
		return od.getArgs().get(2);
	}

	/* ************************************************
	 * Convenience Methods
	 * ************************************************/
	/**
	 * Empty operation definition
	 * @return An operation definition
	 */
	public CodeDefinition empty() {
		return var(Reg.R_NULL).setReturnType(ValueType.VOID);
	}
	/**
	 * Reserve a label name
	 * @return An operation definition
	 */
	public CodeDefinition rl() {
		return var(String.format(labelTemplate, labelCount++));
	}
	/**
	 * Auto variable name
	 * @return An operation definition
	 */
	public CodeDefinition av() {
		return var(String.format(autovarTemplate, autovarCount++));
	}

	/* ************************************************
	 * Mixed Methods
	 * ************************************************/
	/**
	 * Immediate value, performs
	 * {@code store(var(val), av())}
	 * @param val Value
	 * @return An operation definition
	 */
	public CodeDefinition imm(Serializable val) {
		return store(var(val), av());
	}
	/**
	 * Array indexing, performs
	 * {@code deref(add(deref(b, av()), i, av()), av())}
	 * @param b Pointer
	 * @param i Index
	 * @return An operation definition
	 */
	public CodeDefinition index(CodeDefinition b, CodeDefinition i) {
//		return deref(add(deref(b, av()), i, av()), av());
		return deref(add(b, i, av()), av());
	}
	/**
	 * Stop execution using code 0, performs
	 * {@code exit(var(0))}
	 * @return An operation definition
	 */
	public CodeDefinition exit() {
		return exit(var(0));
	}
	
	/* ************************************************
	 * Operations
	 * ************************************************/
	/**
	 * Stop execution
	 * @param t Target (arg0)
	 * @return An operation definition
	 */
	public CodeDefinition exit(CodeDefinition t) {
		return op(InsType.EXIT, t);
	}
	/**
	 * Halt execution
	 * @return An operation definition
	 */
	public CodeDefinition trap() {
		return op(InsType.TRAP);
	}
	/**
	 * Mark address
	 * @param t Target (arg0)
	 * @return An operation definition
	 */
	public CodeDefinition label(CodeDefinition t) {
		return op(InsType.LABEL, t);
	}
	/**
	 * Retrieve address
	 * @param a Operand (actual arg1)
	 * @param t Target (actual arg0)
	 * @return An operation definition
	 */
	public CodeDefinition addr(CodeDefinition a, CodeDefinition t) {
		return op(InsType.ADDR, t, a);
	}
	/**
	 * Retrieve value from address
	 * @param a Operand (actual arg1)
	 * @param t Target (actual arg0)
	 * @return An operation definition
	 */
	public CodeDefinition deref(CodeDefinition a, CodeDefinition t) {
		return op(InsType.DEREF, t, a);
	}
	/**
	 * Declare value
	 * @param t Data type (actual arg1)
	 * @param a Alias (actual arg0)
	 * @return An operation definition
	 */
	public CodeDefinition decl(CodeDefinition t, CodeDefinition a) {
		return op(InsType.DECL, a, t);
	}
	/**
	 * Store value
	 * @param a Operand (actual arg1)
	 * @param t Target (actual arg0)
	 * @return An operation definition
	 */
	public CodeDefinition store(CodeDefinition a, CodeDefinition t) {
		return op(InsType.STORE, t, a);
	}
	/**
	 * Pass reference
	 * @param a Operand (actual arg1)
	 * @param t Target (actual arg0)
	 * @return An operation definition
	 */
	public CodeDefinition assign(CodeDefinition a, CodeDefinition t) {
		return op(InsType.ASSIGN, t, a);
	}
	/**
	 * Copy value
	 * @param a Operand (actual arg1)
	 * @param t Target (actual arg0)
	 * @return An operation definition
	 */
	public CodeDefinition copy(CodeDefinition a, CodeDefinition t) {
		return op(InsType.COPY, t, a);
	}
	/**
	 * Reserve space
	 * @param a Size (actual arg1)
	 * @param t Target (actual arg0)
	 * @return An operation definition
	 */
	public CodeDefinition spc(CodeDefinition a, CodeDefinition t) {
		return op(InsType.SPACE, t, a);
	}
	/**
	 * Unconditional Jump
	 * @param t Target label (arg0)
	 * @return An operation definition
	 */
	public CodeDefinition j(CodeDefinition t) {
		return op(InsType.JUMP, t);
	}
	/**
	 * Jump if true
	 * @param a Pointer to boolean (actual arg1)
	 * @param t Target label (actual arg0)
	 * @return An operation definition
	 */
	public CodeDefinition jt(CodeDefinition a, CodeDefinition t) {
		return op(InsType.JUMPT, t, a);
	}
	/**
	 * Jump if false
	 * @param a Pointer to boolean (actual arg1)
	 * @param t Target label (actual arg0)
	 * @return An operation definition
	 */
	public CodeDefinition jf(CodeDefinition a, CodeDefinition t) {
		return op(InsType.JUMPF, t, a);
	}
	/**
	 * Call function
	 * @param t Target (arg0)
	 * @return An operation definition
	 */
	public CodeDefinition call(CodeDefinition t) {
		return op(InsType.CALL, t);
	}
	/**
	 * Pop stackframe
	 * @return An operation definition
	 */
	public CodeDefinition ret(CodeDefinition t) {
		return op(InsType.RET, t);
	}
	/**
	 * Equals
	 * @param a1 Operand 1 (actual arg1)
	 * @param a2 Operand 2 (actual arg2)
	 * @param t Target (actual arg0)
	 * @return An operation definition
	 */
	public CodeDefinition eq(CodeDefinition a1, CodeDefinition a2, CodeDefinition t) {
		return op(InsType.EQ, t, a1, a2);
	}
	/**
	 * Not equals
	 * @param a1 Operand 1 (actual arg1)
	 * @param a2 Operand 2 (actual arg2)
	 * @param t Target (actual arg0)
	 * @return An operation definition
	 */
	public CodeDefinition neq(CodeDefinition a1, CodeDefinition a2, CodeDefinition t) {
		return op(InsType.NEQ, t, a1, a2);
	}
	/**
	 * Less than
	 * @param a1 Operand 1 (actual arg1)
	 * @param a2 Operand 2 (actual arg2)
	 * @param t Target (actual arg0)
	 * @return An operation definition
	 */
	public CodeDefinition lt(CodeDefinition a1, CodeDefinition a2, CodeDefinition t) {
		return op(InsType.LT, t, a1, a2);
	}
	/**
	 * Less than or equals
	 * @param a1 Operand 1 (actual arg1)
	 * @param a2 Operand 2 (actual arg2)
	 * @param t Target (actual arg0)
	 * @return An operation definition
	 */
	public CodeDefinition lte(CodeDefinition a1, CodeDefinition a2, CodeDefinition t) {
		return op(InsType.LTE, t, a1, a2);
	}
	/**
	 * Greater than
	 * @param a1 Operand 1 (actual arg1)
	 * @param a2 Operand 2 (actual arg2)
	 * @param t Target (actual arg0)
	 * @return An operation definition
	 */
	public CodeDefinition gt(CodeDefinition a1, CodeDefinition a2, CodeDefinition t) {
		return op(InsType.GT, t, a1, a2);
	}
	/**
	 * Greater than or equals
	 * @param a1 Operand 1 (actual arg1)
	 * @param a2 Operand 2 (actual arg2)
	 * @param t Target (actual arg0)
	 * @return An operation definition
	 */
	public CodeDefinition gte(CodeDefinition a1, CodeDefinition a2, CodeDefinition t) {
		return op(InsType.GTE, t, a1, a2);
	}
	/**
	 * Logical and
	 * @param a1 Operand 1 (actual arg1)
	 * @param a2 Operand 2 (actual arg2)
	 * @param t Target (actual arg0)
	 * @return An operation definition
	 */
	public CodeDefinition and(CodeDefinition a1, CodeDefinition a2, CodeDefinition t) {
		return op(InsType.AND, t, a1, a2);
	}
	/**
	 * Logical or
	 * @param a1 Operand 1 (actual arg1)
	 * @param a2 Operand 2 (actual arg2)
	 * @param t Target (actual arg0)
	 * @return An operation definition
	 */
	public CodeDefinition or(CodeDefinition a1, CodeDefinition a2, CodeDefinition t) {
		return op(InsType.OR, t, a1, a2);
	}
	/**
	 * LogicaL not
	 * @param a Operand (actual arg1)
	 * @param t Target (actual arg0)
	 * @return An operation definition
	 */
	public CodeDefinition not(CodeDefinition a, CodeDefinition t) {
		return op(InsType.NOT, t, a);
	}
	/**
	 * Add
	 * @param a1 Operand 1 (actual arg1)
	 * @param a2 Operand 2 (actual arg2)
	 * @param t Target (actual arg0)
	 * @return An operation definition
	 */
	public CodeDefinition add(CodeDefinition a1, CodeDefinition a2, CodeDefinition t) {
		return op(InsType.ADD, t, a1, a2);
	}
	/**
	 * Subtract
	 * @param a1 Operand 1 (actual arg1)
	 * @param a2 Operand 2 (actual arg2)
	 * @param t Target (actual arg0)
	 * @return An operation definition
	 */
	public CodeDefinition sub(CodeDefinition a1, CodeDefinition a2, CodeDefinition t) {
		return op(InsType.SUB, t, a1, a2);
	}
	/**
	 * Multiply
	 * @param a1 Operand 1 (actual arg1)
	 * @param a2 Operand 2 (actual arg2)
	 * @param t Target (actual arg0)
	 * @return An operation definition
	 */
	public CodeDefinition mul(CodeDefinition a1, CodeDefinition a2, CodeDefinition t) {
		return op(InsType.MUL, t, a1, a2);
	}
	/**
	 * Divide
	 * @param a1 Operand 1 (actual arg1)
	 * @param a2 Operand 2 (actual arg2)
	 * @param t Target (actual arg0)
	 * @return An operation definition
	 */
	public CodeDefinition div(CodeDefinition a1, CodeDefinition a2, CodeDefinition t) {
		return op(InsType.DIV, t, a1, a2);
	}
	/**
	 * Modulo
	 * @param a1 Operand 1 (actual arg1)
	 * @param a2 Operand 2 (actual arg2)
	 * @param t Target (actual arg0)
	 * @return An operation definition
	 */
	public CodeDefinition mod(CodeDefinition a1, CodeDefinition a2, CodeDefinition t) {
		return op(InsType.MOD, t, a1, a2);
	}
	/**
	 * Scan line
	 * @param t Target (arg0)
	 * @return An operation definition
	 */
	public CodeDefinition scan(CodeDefinition t) {
		return op(InsType.SCAN, t);
	}
	/**
	 * Print string
	 * @param t Target (arg0)
	 * @return An operation definition
	 */
	public CodeDefinition print(CodeDefinition t) {
		return op(InsType.PRINT, t);
	}

	/* ************************************************
	 * Getters and Setters
	 * ************************************************/
	public List<OperationDefinition> getOps() {
		return ops;
	}
	public Codes setOps(List<OperationDefinition> ops) {
		this.ops = ops == null ? this.ops = new LinkedList<>() : ops;
		return this;
	}
	public Codes setOps(OperationDefinition... ops) {
		return setOps(Arrays.asList(ops));
	}

	public String getLabelTemplate() {
		return labelTemplate;
	}
	public Codes setLabelTemplate(String labelTemplate) {
		this.labelTemplate = labelTemplate;
		return this;
	}

	public String getAutoVarTemplate() {
		return autovarTemplate;
	}
	public Codes setAutoVarTemplate(String autoVarTemplate) {
		this.autovarTemplate = autoVarTemplate;
		return this;
	}

	public int getLabelCount() {
		return labelCount;
	}
	public Codes setLabelCount(int labelCount) {
		this.labelCount = labelCount;
		return this;
	}

	public int getAutovarCount() {
		return autovarCount;
	}
	public Codes setAutovarCount(int autovarCount) {
		this.autovarCount = autovarCount;
		return this;
	}
}
