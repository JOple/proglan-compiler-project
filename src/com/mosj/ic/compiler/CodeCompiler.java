package com.mosj.ic.compiler;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.mosj.ic.definition.*;
import com.mosj.ic.grammar.Co2pNParser.*;
import com.mosj.ic.interpreter.Reg;
import com.mosj.ic.interpreter.func.PrintFunction;
import com.mosj.ic.interpreter.func.ScanFunction;
import com.mosj.ic.symbols.*;
import com.mosj.ic.value.ValueType;

public class CodeCompiler implements Reg {

	public static CodeCompilation compile(String name, CompilationUnitContext ctx) {
		CodeCompiler c = new CodeCompiler(name, ctx);
		return new CodeCompilation(c.prog, c.log.logs());
	}

	private static final String MAIN_FUNCTION = "main";

	private ProgramDefinition prog;
	private CompilationUnitContext cpl;

	private LinkedHashMap<String, VariableDefinition> globals = new LinkedHashMap<>();
	private LinkedHashMap<String, StructDefinition> structs = new LinkedHashMap<>();
	private LinkedHashMap<String, FunctionDefinition> funcs = new LinkedHashMap<>();

	private CodeSymbolManager sym = CodeSymbolManager.create();
	private CodeLogger log = CodeLogger.create(CodeCompiler.class);

	private Codes x;
	private FunctionDefinition f;
	private boolean hasReturn;

	public CodeCompiler(String name, CompilationUnitContext ctx) {
		genProgramDef(name, ctx);
	}

	/* ************************************************
	 * Compilation Methods
	 * ************************************************/
	private ProgramDefinition genProgramDef(String name, CompilationUnitContext ctx) {
		this.prog = new ProgramDefinition(name, structs, globals, funcs);
		this.cpl = ctx;

		if(ctx.memberDeclaration() == null)
			return prog;

		sym.enterScope();

		preProcess();

		if(ctx.memberDeclaration() != null) {
			for(MemberDeclarationContext m : ctx.memberDeclaration()) {
				if(m.constDeclaration() != null) {
					VariableDefinition cons = genConstDef(m.constDeclaration());
					sym.createConst(cons);
					continue;
				}
				if(m.structDeclaration() != null) {
					genStructDef(m.structDeclaration());
					continue;
				}
				if(m.methodDeclaration() != null) {
					genFuncDef(m.methodDeclaration(), prog);
					continue;
				}

				log.debug("You should not reach this state");
			}
		}

		postProcess();
		sym.exitScope();

		return prog;
	}

	private VariableDefinition genConstDef(ConstDeclarationContext c) {
		VariableDefinition vd = new VariableDefinition();

		String name = c.Identifier().getText();
		vd.setName(name);

		if(globals.containsKey(name))
			log.dupEntity(c, "Variable", name);
		globals.put(name, vd);

		String value = c.literal().getText();
		vd.setValue(value);

		SymbolType type = Symbols.ofType(ValueType.OBJECT);
		if(c.literal().IntegerLiteral() != null)
			type = Symbols.ofType(ValueType.INT);
		else if(c.literal().FloatingPointLiteral() != null)
			type = Symbols.ofType(ValueType.FLOAT);
		else if(c.literal().CharacterLiteral() != null)
			type = Symbols.ofType(ValueType.CHAR);
		else if(c.literal().StringLiteral() != null)
			type = Symbols.ofType(ValueType.STRING);
		else if(c.literal().BooleanLiteral() != null)
			type = Symbols.ofType(ValueType.BOOL);
		vd.setType(type.toString());

		if(c.literal().NULL() != null)
			log.badDesign(c, String.format("The constant '%' is assigned with NULL", name));

		return vd;
	}

	private StructDefinition genStructDef(StructDeclarationContext c) {
		StructDefinition sd = new StructDefinition();

		String name = c.Identifier().getText();
		sd.setName(name);

		if(structs.containsKey(name))
			log.dupEntity(c, "Struct", name);
		structs.put(name, sd);

		if(c.structBody().structMemberDeclaration() == null) {
			log.badDesign(c, String.format("The struct '%s' has no content", name));
			return sd;
		}

		for(StructMemberDeclarationContext m : c.structBody().structMemberDeclaration()) {
			SymbolType mtype = findType(m.typeType());
			String mname = m.Identifier().getText();
			VariableDefinition v = new VariableDefinition(mname, mtype.toString(), null, false);
			sd.getMembers().add(v);
		}

		return sd;
	}

	private FunctionDefinition genFuncDef(MethodDeclarationContext c, ProgramDefinition p) {
		f = new FunctionDefinition();

		String name = c.Identifier().getText();
		f.setName(name);


		if(funcs.containsKey(name))
			log.dupEntity(c, "Function", name);
		funcs.put(name, f);

		if(c.formalParameters().formalParameterList() != null) {
			for(FormalParameterContext a : c.formalParameters().formalParameterList().formalParameter()) {
				SymbolType ptype = a.typeType() != null ? findType(a.typeType()) : Symbols.ofType(ValueType.OBJECT);
				String pname = a.Identifier().getText();
				VariableDefinition v = new VariableDefinition(pname, ptype.toString(), null, false);
				f.getParameters().add(v);

				if(ptype.isStruct() && !structs.containsKey(ptype.top()))
					log.missing(a, "Struct", ptype.top());
				if(ptype.isArray() && ptype.down().isStruct() && !structs.containsKey(ptype.down().top()))
					log.missing(a, "Struct", ptype.down().top());
			}
		}

		if(c.typeType() != null) {
			String returnType = findType(c.typeType()).toString();
			f.setReturnType(returnType);
		} else
			f.setReturnType(ValueType.VOID.getId());

		x = Codes.create();
		f.setOperations(x.getOps());
		hasReturn = false;

		sym.enterScope();
		funcInit(c);
		block(c.methodBody().block());
		funcFinal(c);
		sym.exitScope();

		return f;
	}


	/* ************************************************
	 * Support Methods
	 * ************************************************/

	private Map<String, FunctionDefinition> presetFunctions() {
		Map<String, FunctionDefinition> presetFunctions = new LinkedHashMap<>();
		presetFunctions.put(PrintFunction.INSTANCE.getName(), PrintFunction.INSTANCE);
		presetFunctions.put(ScanFunction.INSTANCE.getName(), ScanFunction.INSTANCE);
		return presetFunctions;
	}
	private SymbolType findType(TypeTypeContext c) {
		SymbolType t = Symbols.ofType(c.getText());
		if(t.isArray())
			t = t.meta(0);
		return t;
	}
	private boolean typeMatch(SymbolType a, SymbolType b) {
		if(a.isAny() || b.isAny())
			return true;
		if(a.isInt() && b.isFloat())
			return true;
		if(a.isFloat() && b.isFloat())
			return true;
		return a.equals(b);
	}

	private void preProcess() {
		funcs.putAll(presetFunctions());
	}
	private void postProcess() {
		if(!funcs.containsKey(MAIN_FUNCTION))
			log.badDesign(cpl, "There is no 'main' function");
	}

	private void funcInit(MethodDeclarationContext m) {
		List<VariableDefinition> params = f.getParameters();
		for(int i = 0; i < params.size(); i++) {
			VariableDefinition param = params.get(i);
			sym.create(param.getName(), param.getType());
			x.copy(x.var(Reg.ARRAY_R_AXX[i]), x.var(sym.get(param.getName()).getId()));
		}
		
		for(VariableDefinition cs : globals.values()) {
			x.store(x.var(sym.get(cs.getName()).getValue()), x.var(sym.get(cs.getName()).getId()));
		}

		if(MAIN_FUNCTION.equals(f.getName()) && params.size() > 0)
			log.badDesign(m, "The 'main' function must contain no arguments");
	}
	private void funcFinal(MethodDeclarationContext m) {
		if(!Symbols.ofType(f.getReturnType()).isVoid() && !hasReturn)
			log.missing(m, f.getReturnType(), "Return value");

		if(MAIN_FUNCTION.equals(f.getName()))
			x.exit();
		else
			x.ret(x.empty());
	}

	/* ************************************************
	 * Statement/Block Methods
	 * ************************************************/

	private void block(BlockContext b) {
		if(b.blockStatement() != null) {
			sym.enterScope();
			for(BlockStatementContext bs : b.blockStatement()) {
				if(bs.variableDeclaration() != null)
					variableDeclaration(bs.variableDeclaration());
				if(bs.statement() != null)
					statement(bs.statement());
			}
			sym.exitScope();
		}
	}
	private void variableDeclaration(VariableDeclarationContext v) {
		SymbolType type = findType(v.typeType());
		String n = v.Identifier().getText();
		ExpressionContext e = v.expression();

		if(sym.getCurrentTable().containsKey(n))
			log.dupEntity(v, "Variable", n);

		sym.create(n, type);

		x.decl(x.var(type.primitiveType().getId()), x.var(sym.get(n).getId()));
		if(e != null) {
			CodeDefinition exp = expression(e);
			SymbolType rtype = exp.getReturnType();
			if(!typeMatch(type, rtype))
				log.typeMismatch(v, type.toString(), rtype.toString());
			else
				sym.get(n).setType(exp.getReturnType());
			x.copy(exp, x.var(sym.get(n).getId()));
		} else
			x.copy(x.imm(null), x.var(sym.get(n).getId()));
	}
	private void statement(StatementContext s) {
		if(s.block() != null)
			block(s.block());
		if(s.statement() != null) {
			StatementContext a1 = s.statement(0);
			StatementContext a2 = s.statement().size() > 1 ? s.statement(1) : null;
			ExpressionContext e = s.parExpression() != null ? s.parExpression().expression() : null;
			if(s.IF() != null)
				conditional(e, a1, a2);
			if(s.FOR() != null) {
				ForControlContext fc = s.forControl();
				forLoop(fc,
						fc.forInit(),
						fc.expression(),
						fc.forUpdate() != null ? fc.forUpdate().expressionList().expression() : Collections.emptyList(),
								a1);
			}
			if(s.WHILE() != null) {
				if(s.DO() == null)
					whileLoop(e, a1);
				doWhileLoop(e, a1);
			}
		}
		if(s.RETURN() != null)
			retFunc(s.expression());
		if(s.statementExpression() != null)
			expression(s.statementExpression().expression());
	}

	private void conditional(ExpressionContext c, StatementContext t, StatementContext e) {
		CodeDefinition labelElse = x.rl(), labelEndif = x.rl();

		// Condition
		CodeDefinition cond = expression(c);

		if(!cond.getReturnType().isBool())
			log.typeMismatch(c, ValueType.BOOL.getId(), cond.getReturnType().toString());

		x.jf(cond, labelElse);

		// Then
		sym.enterScope();
		statement(t);
		sym.exitScope();

		x.j(labelEndif);

		// Else
		x.label(labelElse);
		if(e != null) {
			sym.enterScope();
			statement(e);
			sym.exitScope();
		}

		// End
		x.label(labelEndif);
	}
	private void forLoop(ForControlContext fc, ForInitContext i, ExpressionContext c, List<ExpressionContext> u, StatementContext b) {
		CodeDefinition labelStart = x.rl(), labelEnd = x.rl();

		sym.enterScope();

		// Initialization
		if(i != null) {
			String name = i.Identifier().getText();

			if(i.typeType() == null) {
				if(sym.get(name).isAbsent())
					log.missing(i, "Variable", name);
				else if(sym.get(name).isConstant())
					log.constRe(i, name);
			} else
				sym.create(name, findType(i.typeType()));

			CodeDefinition val = expression(i.expression());

			SymbolType t = sym.get(name).getType();
			SymbolType r = val.getReturnType();
			if(!typeMatch(t, r))
				log.typeMismatch(i, sym.get(name).getType().toString(), val.getReturnType().toString());

			x.copy(val, x.var(sym.get(name).getId()));
		}

		// Condition
		x.label(labelStart);

		if(c != null) {
			CodeDefinition cond = expression(c);

			if(!cond.getReturnType().isBool())
				log.typeMismatch(c, ValueType.BOOL.getId(), cond.getReturnType().toString());

			x.jf(cond, labelEnd);
		} else {
			log.badDesign(fc, "Condition in the for loop is not supplied");
			x.j(labelEnd);
		}

		// Body
		sym.enterScope();
		statement(b);
		sym.exitScope();

		// Update
		for(ExpressionContext e : u)
			expression(e);
		x.j(labelStart);

		// End
		x.label(labelEnd);
		sym.exitScope();
	}
	private void whileLoop(ExpressionContext c, StatementContext b) {
		CodeDefinition labelStart = x.rl(), labelEnd = x.rl();

		// Condition
		x.label(labelStart);

		CodeDefinition cond = expression(c);

		if(!cond.getReturnType().isBool())
			log.typeMismatch(c, ValueType.BOOL.getId(), cond.getReturnType().toString());

		x.jf(cond, labelEnd);

		// Body
		sym.enterScope();
		statement(b);
		sym.exitScope();

		x.j(labelStart);

		// End
		x.label(labelEnd);
	}
	private void doWhileLoop(ExpressionContext c, StatementContext b) {
		CodeDefinition labelStart = x.rl();

		// Body
		x.label(labelStart);

		sym.enterScope();
		statement(b);
		sym.exitScope();

		// Condition
		CodeDefinition cond = expression(c);

		if(!cond.getReturnType().isBool())
			log.typeMismatch(c, ValueType.BOOL.getId(), cond.getReturnType().toString());

		x.jt(cond, labelStart);

		// End
	}
	private void retFunc(ExpressionContext e) {
		hasReturn = true;

		if(Symbols.ofType(f.getReturnType()).isVoid()) {
			if(e != null)
				log.invalidOp(e, "The function should not return a value");

			x.ret(x.empty());
		}

		if(!Symbols.ofType(f.getReturnType()).isVoid()) {
			if(e == null) {
				log.invalidOp(e, "The function should return a value");
				x.ret(x.empty());
				return;
			}

			CodeDefinition retVal = expression(e);

			SymbolType t = Symbols.ofType(f.getReturnType());
			SymbolType r = retVal.getReturnType();

			if(!typeMatch(t, r))
				log.typeMismatch(e, t.toString(), r.toString());

			x.ret(x.decl(x.var(t.primitiveType().getId()), retVal));
		}
	}


	/* ************************************************
	 * Expression Methods
	 * ************************************************/
	private CodeDefinition expression(ExpressionContext e) {
		if(e.expression() == null || e.expression().size() == 0) {
			if(e.primary() != null) {
				if(e.primary().expression() != null)
					return expression(e.primary().expression());
				if(e.primary().literal() != null)
					return literal(e.primary().literal());
				if(e.primary().Identifier() != null)
					return variable(e.primary(), e.primary().Identifier().getText());
			}
			if(e.creator() != null)
				return create(e.creator(), findType(e.creator().typeType()), e.creator().arrayDim());

		}
		if(e.expression().size() == 1) {
			ExpressionContext v = e.expression(0);
			if(e.Identifier() != null)
				return memberAccess(v, e.Identifier().getText());
			if(e.LPAREN() != null) {
				if(e.typeType() == null) {
					if(e.expressionList() == null)
						return functionCall(v, Collections.emptyList());
					return functionCall(v, e.expressionList().expression());
				}
				return cast(findType(e.typeType()), v);
			}
			if(e.ADDR() != null)
				return addr(v);
			if(e.DEREF() != null)
				return deref(v);
			if(e.ADD() != null)
				return mathUnary("+", v);
			if(e.SUB() != null)
				return mathUnary("-", v);
			if(e.BANG() != null)
				return logicNot(v);
		}
		if(e.expression().size() == 2) {
			if(e.ASSIGN() != null) {
				ExpressionContext a2 = e.expression(1);
				ExpressionContext a1 = e.expression(0);
				return assign(a1, a2);
			}

			ExpressionContext a1 = e.expression(0);
			ExpressionContext a2 = e.expression(1);
			if(e.LBRACK() != null)
				return arrayIndex(a1, a2);
			if(e.MUL() != null)
				return mathBinary("*", a1, a2);
			if(e.DIV() != null)
				return mathBinary("/", a1, a2);
			if(e.MOD() != null)
				return mathBinary("%", a1, a2);
			if(e.ADD() != null)
				return mathBinary("+", a1, a2);
			if(e.SUB() != null)
				return mathBinary("-", a1, a2);
			if(e.LE() != null)
				return logicInequality("<=", a1, a2);
			if(e.GE() != null)
				return logicInequality(">=", a1, a2);
			if(e.GT() != null)
				return logicInequality(">", a1, a2);
			if(e.LT() != null)
				return logicInequality("<", a1, a2);
			if(e.EQUAL() != null)
				return logicEquality("==", a1, a2);
			if(e.NOTEQUAL() != null)
				return logicEquality("!=", a1, a2);
			if(e.AND() != null)
				return logicComplex("&&", a1, a2);
			if(e.OR() != null)
				return logicComplex("||", a1, a2);
		}

		log.debug("Code should not reach this place: " + e.getText());
		return x.empty();
	}

	private CodeDefinition literal(LiteralContext l) {
		CodeDefinition out = x.imm(l.getText());
		if(l.IntegerLiteral() != null)
			out.setReturnType(ValueType.INT);
		else if(l.FloatingPointLiteral() != null)
			out.setReturnType(ValueType.FLOAT);
		else if(l.CharacterLiteral() != null)
			out.setReturnType(ValueType.CHAR);
		else if(l.StringLiteral() != null)
			out.setReturnType(ValueType.STRING);
		else if(l.BooleanLiteral() != null)
			out.setReturnType(ValueType.BOOL);
		else
			out.setReturnType(ValueType.OBJECT);
		return out;
	}
	private CodeDefinition variable(PrimaryContext p, String v) {
		if(sym.get(v).isAbsent()) {
			log.missing(p, "Variable", v);
			return x.var(sym.get(v).getId())
					.setReturnType(ValueType.OBJECT);
		}
		return x.var(sym.get(v).getId())
				.setReturnType(sym.get(v).getType());
	}
	private CodeDefinition memberAccess(ExpressionContext e, String m) {
		CodeDefinition struct = expression(e);

		if(!struct.getReturnType().isStruct())
			log.invalidOp(e, "It is not a struct");
		else {
			String type = struct.getReturnType().top();
			StructDefinition def = structs.get(type);
			if(def == null)
				log.missing(e, "Struct", e.getText());
			else {
				List<VariableDefinition> ms = def.getMembers();
				int i = 0;
				for(; i < ms.size(); i++) {
					if(ms.get(i).getName().equals(m))
						break;
				}
				if(i == ms.size())
					log.missing(e, "Struct Member", m);
				else
					return x.index(struct, x.imm(i))
							.setReturnType(ms.get(i).getType());
			}
		}
		return struct;
	}
	private CodeDefinition arrayIndex(ExpressionContext b, ExpressionContext i) {
		CodeDefinition base = expression(b);
		CodeDefinition index = expression(i);

		if(!base.getReturnType().isArray()) {
			log.invalidOp(b, "It is not an array");
			return base;
		}
		if(!typeMatch(Symbols.ofType(ValueType.INT), index.getReturnType())) {
			log.typeMismatch(i, ValueType.INT.getId() + "|" + ValueType.FLOAT.getId(),
					index.getReturnType().toString());
			return base;
		}

		if(i.primary() != null && i.primary().literal() != null) {
			LiteralContext l = i.primary().literal();
			int n = 0;
			if(l.IntegerLiteral() != null) {
				try {
					n = Integer.parseInt(l.getText());
				} catch(NumberFormatException e) {
					log.debug("Parsing this integer literal should not result to an error: " + l.getText());
				}
			} else if(l.FloatingPointLiteral() != null) {
				try {
					n = (int) Float.parseFloat(l.getText());
				} catch(NumberFormatException e) {
					log.debug("Parsing this float literal should not result to an error: " + l.getText());
				}
			} else
				log.debug("It should not reach this state: " + l.getText());

			try {
				int size = (int) base.getReturnType().meta();
				if(size != -1 && n >= size)
					log.outBound(i, b.getText(), size + "", n + "");
			} catch(ClassCastException e) {
				log.debug("Meta of SymbolType must be of type int");
			}
		}

		return x.index(base, index)
				.setReturnType(base.getReturnType().down());
	}
	private CodeDefinition functionCall(ExpressionContext f, List<ExpressionContext> a) {
		if(f.primary() == null || f.primary().Identifier() == null) {
			log.invalidOp(f, "This is not a function name");
			return x.empty();
		}

		String func = f.primary().Identifier().getText();

		if(!funcs.containsKey(func)) {
			log.missing(f, "Function", func);
			return x.empty();
		}

		FunctionDefinition fd = funcs.get(func);
		List<VariableDefinition> params = fd.getParameters();

		if(params.size() != a.size()) {
			log.paramMismatch(f, fd, String.format("Requires %s params but provided with %s params", params.size(), a.size()));
			return x.empty();
		}

		CodeDefinition[] paramEval = new CodeDefinition[a.size()];

		for(int i = 0; i < a.size(); i++) {
			CodeDefinition arg = expression(a.get(i));
			paramEval[i] = arg;
		}

		SymbolType varType = Symbols.ofType(ValueType.OBJECT);

		for(int i = 0; i < paramEval.length; i++) {
			CodeDefinition arg = paramEval[i];
			VariableDefinition param = params.get(i);

			SymbolType pType = Symbols.ofType(param.getType());
			SymbolType rType = arg.getReturnType();

			if(!pType.equals(rType) && !pType.equals(varType) && !rType.equals(varType))
				log.paramMismatch(a.get(i), fd, String.format("Param %s requires '%s' but received '%s' instead",
						i, pType.toString(), rType));

			x.copy(x.decl(x.var(pType.primitiveType().getId()), arg), x.var(ARRAY_R_AXX[i]));
		}

		x.call(x.var(func));

		return x.copy(x.var(R_V0), x.av())
				.setReturnType(fd.getReturnType());
	}
	private CodeDefinition create(CreatorContext c, SymbolType t, ArrayDimContext a) {
		if(a == null) {
			if(!t.isStruct()) {
				if(t.isBool())
					return x.imm(false).setReturnType(ValueType.BOOL);
				if(t.isChar())
					return x.imm('\0').setReturnType(ValueType.CHAR);
				if(t.isInt())
					return x.imm(0).setReturnType(ValueType.INT);
				if(t.isFloat())
					return x.imm(0.0).setReturnType(ValueType.FLOAT);
				if(t.isString())
					return x.imm(null).setReturnType(ValueType.STRING);
				return x.imm(null).setReturnType(ValueType.OBJECT);
			}

			String name = t.top();

			if(!structs.containsKey(name)) {
				log.missing(c, "Struct", name);
				return x.imm(null).setReturnType(name);
			}
			if(structs.get(name).getMembers().size() == 0)
				return x.imm(null).setReturnType(name);

			List<VariableDefinition> members = structs.get(name).getMembers();

			CodeDefinition base = x.spc(x.imm(members.size()), x.decl(x.var(ValueType.POINTER.getId()), x.av()));

			for(int i = 0; i < members.size(); i++) {
				VariableDefinition member = members.get(i);
				String type = Symbols.ofType(member.getType()).primitiveType().getId();
				x.decl(x.var(type), x.deref(x.add(base, x.imm(i), x.av()), x.av()));
			}

			return base.setReturnType(name);
		}

		ExpressionContext exp = a.expression();
		
		CodeDefinition size = expression(exp);

		int metaSize = -1;
		if(exp.primary() != null && exp.primary().literal() != null) {
			LiteralContext num = exp.primary().literal();

			if(num.IntegerLiteral() != null) {
				try {
					metaSize = Integer.parseInt(num.IntegerLiteral().getText());
				} catch(NumberFormatException e) {
					log.debug("Parsing an integer literal should not result to an error");
				}
			}
			if(num.FloatingPointLiteral() != null) {
				try {
					metaSize = (int) Float.parseFloat(num.FloatingPointLiteral().getText());
				} catch(NumberFormatException e) {
					log.debug("Parsing a float literal should not result to an error");
				}
			}
			
			System.out.println("MetaSize: " + metaSize);
		}
			

		if(!typeMatch(Symbols.ofType(ValueType.INT), size.getReturnType()))
			log.typeMismatch(a.expression(),
					ValueType.INT.getId() + "|" + ValueType.FLOAT.getId(),
					size.getReturnType().toString());

		CodeDefinition base = x.spc(size, x.decl(x.var(ValueType.POINTER.getId()), x.av()));

		CodeDefinition labelStart = x.rl();
		
		// Initialize
		CodeDefinition intI = x.imm(0);
		
		// Start
		x.label(labelStart);
		
		// Body
		x.decl(x.var(t.primitiveType().getId()), x.deref(x.add(base, intI, x.av()), x.av()));
		x.add(intI, x.imm(1), intI);
		
		// Condition
		x.jt(x.lt(intI, size, x.av()), labelStart);

		return base.setReturnType(t.up(ValueType.ARRAY).meta(metaSize));
	}
	private CodeDefinition cast(SymbolType t, ExpressionContext v) {
		return x.store(expression(v), x.decl(x.var(t.primitiveType()), x.av())).setReturnType(t);
	}
	private CodeDefinition addr(ExpressionContext v) {
		CodeDefinition e = expression(v);
		return x.addr(e, x.av()).setReturnType(e.getReturnType().up(ValueType.POINTER));
	}
	private CodeDefinition deref(ExpressionContext v) {
		CodeDefinition e = expression(v);

		if(!e.getReturnType().isPointer()) {
			log.invalidArg(v, "Expression is not a pointer");
			return e;
		}

		return x.deref(e, x.av()).setReturnType(e.getReturnType().down());
	}
	private CodeDefinition mathUnary(String op, ExpressionContext v) {
		CodeDefinition e = expression(v);

		if(!e.getReturnType().isInt() && !e.getReturnType().isFloat())
			log.invalidArg(v, "Expression must be varint or varfp");

		switch (op) {
		case "+":
			return e;
		case "-":
			return x.mul(e, x.imm("-1"), x.av()).setReturnType(e.getReturnType());
		}

		log.debug("Code should not reach this place: " + op + v.getText());
		return x.empty();
	}
	private CodeDefinition mathBinary(String op, ExpressionContext a1, ExpressionContext a2) {
		CodeDefinition e1 = expression(a1), e2 = expression(a2);

		SymbolType outType = null;

		SymbolType r1 = e1.getReturnType(), r2 = e2.getReturnType();
		if((r1.isAny() || r1.isInt() || r1.isFloat()) && (r1.isAny() || r1.isInt() || r1.isFloat()))
			outType = Symbols.ofType(ValueType.FLOAT);
		if(r1.isInt() && r2.isInt())
			outType = Symbols.ofType(ValueType.INT);
		if(outType == null) {
			String te1 = ValueType.INT + "|" + ValueType.FLOAT;
			String te2 = te1 + "|" + Symbols.ofType(ValueType.OBJECT).up(ValueType.POINTER);

			switch(op) {
			case "+": case "-":
				if(r1.isString() || r2.isString()) {
					outType = Symbols.ofType(ValueType.STRING);
					break;
				}

				if(!r1.isAny() && !r1.isInt() && !r1.isFloat() && !r1.isPointer())
					log.typeMismatch(a1, te1, r1.toString());
				if(!r2.isAny() && !r2.isInt() && !r2.isFloat() && !r2.isPointer())
					log.typeMismatch(a2, te1, r2.toString());
				break;
			default:
				if(!r1.isAny() && !r1.isInt() && !r1.isFloat())
					log.typeMismatch(a1, te2, r1.toString());
				if(!r2.isAny() && !r2.isInt() && !r2.isFloat())
					log.typeMismatch(a2, te2, r2.toString());
				outType = Symbols.ofType(ValueType.OBJECT);
			}
		}

		CodeDefinition process = null;
		switch(op) {
		case "*":
			process = x.mul(e1, e2, x.av());
			break;
		case "/":
			process = x.div(e1, e2, x.av());
			break;
		case "%":
			process = x.mod(e1, e2, x.av());
			break;
		case "+":
			process = x.add(e1, e2, x.av());
			break;
		case "-":
			process = x.sub(e1, e2, x.av());
			break;
		default:
			log.debug("Code should not reach this place: " + a1.getText() + op + a2.getText());
			process = x.imm(0.0);
		}

		if(outType == null)
			log.debug("You shoul not reach this state");

		return process.setReturnType(outType);
	}
	private CodeDefinition logicNot(ExpressionContext v) {
		CodeDefinition e = expression(v);
		if(!e.getReturnType().isBool())
			log.typeMismatch(v, ValueType.BOOL.getId(), e.getReturnType().toString());
		return x.store(e, x.decl(x.var(ValueType.BOOL.getId()), x.av())).setReturnType(ValueType.BOOL);
	}
	private CodeDefinition logicInequality(String op, ExpressionContext a1, ExpressionContext a2) {
		CodeDefinition e1 = expression(a1), e2 = expression(a2);

		SymbolType r1 = e1.getReturnType(), r2 = e2.getReturnType();


		String te = ValueType.INT + "|" + ValueType.FLOAT + "|" + Symbols.ofType(ValueType.OBJECT).up(ValueType.POINTER);
		if(!(r1.isAny() || r1.isBool() || r1.isInt() || r1.isFloat()))
			log.typeMismatch(a1, te, r1.toString());
		if(!(r2.isAny() || r2.isBool() || r2.isInt() || r2.isFloat()))
			log.typeMismatch(a1, te, r2.toString());

		CodeDefinition process = null;
		switch(op) {
		case "<=":
			process = x.lte(e1, e2, x.av());
			break;
		case ">=":
			process = x.gte(e1, e2, x.av());
			break;
		case ">":
			process = x.gt(e1, e2, x.av());
			break;
		case "<":
			process = x.lt(e1, e2, x.av());
			break;
		default:
			log.debug("Code should not reach this place: " + a1.getText() + op + a2.getText());
			process = x.imm(false);
		}

		return process.setReturnType(ValueType.BOOL);
	}
	private CodeDefinition logicEquality(String op, ExpressionContext a1, ExpressionContext a2) {
		CodeDefinition e1 = expression(a1), e2 = expression(a2);

		CodeDefinition process = null;
		switch(op) {
		case "==":
			process = x.eq(e1, e2, x.av());
			break;
		case "!=":
			process = x.neq(e1, e2, x.av());
			break;
		default:
			log.debug("Code should not reach this place: " + a1.getText() + op + a2.getText());
			process = x.imm(false);
		}

		return process.setReturnType(ValueType.BOOL);
	}
	private CodeDefinition logicComplex(String op, ExpressionContext a1, ExpressionContext a2) {
		CodeDefinition e1 = expression(a1), e2 = expression(a2);

		SymbolType r1 = e1.getReturnType(), r2 = e2.getReturnType();

		if(!r1.isBool())
			log.typeMismatch(a1, ValueType.BOOL.getId(), r1.toString());
		if(!r2.isBool())
			log.typeMismatch(a1, ValueType.BOOL.getId(), r2.toString());

		CodeDefinition process = null;
		switch(op) {
		case "&&":
			process = x.and(e1, e2, x.av());
			break;
		case "||":
			process = x.or(e1, e2, x.av());
			break;
		default:
			log.debug("Code should not reach this place: " + a1.getText() + op + a2.getText());
			process = x.imm(false);
		}

		return process.setReturnType(ValueType.BOOL);
	}
	private CodeDefinition assign(ExpressionContext t, ExpressionContext v) {
		boolean isValid = false;
		if(t.primary() != null && t.primary().Identifier() != null)
			isValid = true;
		if(t.Identifier() != null)
			isValid = true;
		if(t.LBRACK() != null)
			isValid = true;
		if(t.DEREF() != null)
			isValid = true;

		if(!isValid)
			log.invalidOp(t, "Expects the left-hand sign of the expression to be a variable name, struct member access, array index, or dereference");
		if(t.primary() != null && t.primary().Identifier() != null) {
			Symbol s = sym.get(t.primary().Identifier().getText());
			if(s.isConstant())
				log.constRe(t, s.getName());
		}

		CodeDefinition e1 = expression(t), e2 = expression(v);

		if(!typeMatch(e1.getReturnType(), e2.getReturnType()))
			log.typeMismatch(t, e1.getReturnType().toString(), e2.getReturnType().toString());

		return x.copy(e2, e1).setReturnType(e2.getReturnType());
	}
}
