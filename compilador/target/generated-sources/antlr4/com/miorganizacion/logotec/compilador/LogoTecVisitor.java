// Generated from com\miorganizacion\logotec\compilador\LogoTec.g4 by ANTLR 4.5.1
package com.miorganizacion.logotec.compilador;

    import java.util.*;
    import com.miorganizacion.logotec.compilador.ast.*;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LogoTecParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LogoTecVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LogoTecParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(LogoTecParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoTecParser#proceduresBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProceduresBlock(LogoTecParser.ProceduresBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoTecParser#procedureDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureDecl(LogoTecParser.ProcedureDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoTecParser#sentence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentence(LogoTecParser.SentenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoTecParser#varDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(LogoTecParser.VarDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoTecParser#varInit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarInit(LogoTecParser.VarInitContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoTecParser#callProc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallProc(LogoTecParser.CallProcContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoTecParser#expressionSeries}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionSeries(LogoTecParser.ExpressionSeriesContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoTecParser#execBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExecBlock(LogoTecParser.ExecBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoTecParser#repiteBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepiteBlock(LogoTecParser.RepiteBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoTecParser#siStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSiStmt(LogoTecParser.SiStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoTecParser#hazHastaStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHazHastaStmt(LogoTecParser.HazHastaStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoTecParser#hazMientrasStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHazMientrasStmt(LogoTecParser.HazMientrasStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoTecParser#mientrasStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMientrasStmt(LogoTecParser.MientrasStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoTecParser#hastaStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHastaStmt(LogoTecParser.HastaStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoTecParser#turtleCmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTurtleCmd(LogoTecParser.TurtleCmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoTecParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(LogoTecParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoTecParser#logicTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicTerm(LogoTecParser.LogicTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoTecParser#logicFactor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicFactor(LogoTecParser.LogicFactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoTecParser#relational}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelational(LogoTecParser.RelationalContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoTecParser#arithExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithExpr(LogoTecParser.ArithExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoTecParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(LogoTecParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoTecParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(LogoTecParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoTecParser#exponent}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExponent(LogoTecParser.ExponentContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoTecParser#unary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary(LogoTecParser.UnaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoTecParser#funcCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncCall(LogoTecParser.FuncCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoTecParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary(LogoTecParser.PrimaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoTecParser#literalOrString}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralOrString(LogoTecParser.LiteralOrStringContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoTecParser#cmtFirstLine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmtFirstLine(LogoTecParser.CmtFirstLineContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoTecParser#colorName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColorName(LogoTecParser.ColorNameContext ctx);
}