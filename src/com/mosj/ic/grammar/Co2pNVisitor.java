package com.mosj.ic.grammar;
// Generated from Co2pN.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link Co2pNParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface Co2pNVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link Co2pNParser#compilationUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompilationUnit(Co2pNParser.CompilationUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link Co2pNParser#memberDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberDeclaration(Co2pNParser.MemberDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Co2pNParser#methodDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodDeclaration(Co2pNParser.MethodDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Co2pNParser#formalParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParameters(Co2pNParser.FormalParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link Co2pNParser#formalParameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParameterList(Co2pNParser.FormalParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link Co2pNParser#formalParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParameter(Co2pNParser.FormalParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link Co2pNParser#methodBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodBody(Co2pNParser.MethodBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link Co2pNParser#constDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstDeclaration(Co2pNParser.ConstDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Co2pNParser#structDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructDeclaration(Co2pNParser.StructDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Co2pNParser#structBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructBody(Co2pNParser.StructBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link Co2pNParser#structMemberDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructMemberDeclaration(Co2pNParser.StructMemberDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Co2pNParser#typeType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeType(Co2pNParser.TypeTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Co2pNParser#varType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarType(Co2pNParser.VarTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Co2pNParser#primitiveType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveType(Co2pNParser.PrimitiveTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Co2pNParser#structType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructType(Co2pNParser.StructTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Co2pNParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(Co2pNParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link Co2pNParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(Co2pNParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link Co2pNParser#blockStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStatement(Co2pNParser.BlockStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Co2pNParser#variableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaration(Co2pNParser.VariableDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Co2pNParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(Co2pNParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Co2pNParser#forControl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForControl(Co2pNParser.ForControlContext ctx);
	/**
	 * Visit a parse tree produced by {@link Co2pNParser#forInit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForInit(Co2pNParser.ForInitContext ctx);
	/**
	 * Visit a parse tree produced by {@link Co2pNParser#forUpdate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForUpdate(Co2pNParser.ForUpdateContext ctx);
	/**
	 * Visit a parse tree produced by {@link Co2pNParser#parExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParExpression(Co2pNParser.ParExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Co2pNParser#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionList(Co2pNParser.ExpressionListContext ctx);
	/**
	 * Visit a parse tree produced by {@link Co2pNParser#statementExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementExpression(Co2pNParser.StatementExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Co2pNParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(Co2pNParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Co2pNParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary(Co2pNParser.PrimaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link Co2pNParser#creator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreator(Co2pNParser.CreatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link Co2pNParser#arrayDim}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayDim(Co2pNParser.ArrayDimContext ctx);
}
