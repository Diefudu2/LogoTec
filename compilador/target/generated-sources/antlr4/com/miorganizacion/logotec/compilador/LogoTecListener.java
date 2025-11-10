// Generated from com\miorganizacion\logotec\compilador\LogoTec.g4 by ANTLR 4.5.1
package com.miorganizacion.logotec.compilador;

    import java.util.*;
    import com.miorganizacion.logotec.compilador.ast.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LogoTecParser}.
 */
public interface LogoTecListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(LogoTecParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(LogoTecParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#proceduresBlock}.
	 * @param ctx the parse tree
	 */
	void enterProceduresBlock(LogoTecParser.ProceduresBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#proceduresBlock}.
	 * @param ctx the parse tree
	 */
	void exitProceduresBlock(LogoTecParser.ProceduresBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#procedureDecl}.
	 * @param ctx the parse tree
	 */
	void enterProcedureDecl(LogoTecParser.ProcedureDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#procedureDecl}.
	 * @param ctx the parse tree
	 */
	void exitProcedureDecl(LogoTecParser.ProcedureDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#sentence}.
	 * @param ctx the parse tree
	 */
	void enterSentence(LogoTecParser.SentenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#sentence}.
	 * @param ctx the parse tree
	 */
	void exitSentence(LogoTecParser.SentenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(LogoTecParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(LogoTecParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#varInit}.
	 * @param ctx the parse tree
	 */
	void enterVarInit(LogoTecParser.VarInitContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#varInit}.
	 * @param ctx the parse tree
	 */
	void exitVarInit(LogoTecParser.VarInitContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#callProc}.
	 * @param ctx the parse tree
	 */
	void enterCallProc(LogoTecParser.CallProcContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#callProc}.
	 * @param ctx the parse tree
	 */
	void exitCallProc(LogoTecParser.CallProcContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#primaryArg}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryArg(LogoTecParser.PrimaryArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#primaryArg}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryArg(LogoTecParser.PrimaryArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#expressionSeries}.
	 * @param ctx the parse tree
	 */
	void enterExpressionSeries(LogoTecParser.ExpressionSeriesContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#expressionSeries}.
	 * @param ctx the parse tree
	 */
	void exitExpressionSeries(LogoTecParser.ExpressionSeriesContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#execBlock}.
	 * @param ctx the parse tree
	 */
	void enterExecBlock(LogoTecParser.ExecBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#execBlock}.
	 * @param ctx the parse tree
	 */
	void exitExecBlock(LogoTecParser.ExecBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#repiteBlock}.
	 * @param ctx the parse tree
	 */
	void enterRepiteBlock(LogoTecParser.RepiteBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#repiteBlock}.
	 * @param ctx the parse tree
	 */
	void exitRepiteBlock(LogoTecParser.RepiteBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#siStmt}.
	 * @param ctx the parse tree
	 */
	void enterSiStmt(LogoTecParser.SiStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#siStmt}.
	 * @param ctx the parse tree
	 */
	void exitSiStmt(LogoTecParser.SiStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#hazDoStmt}.
	 * @param ctx the parse tree
	 */
	void enterHazDoStmt(LogoTecParser.HazDoStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#hazDoStmt}.
	 * @param ctx the parse tree
	 */
	void exitHazDoStmt(LogoTecParser.HazDoStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#mientrasStmt}.
	 * @param ctx the parse tree
	 */
	void enterMientrasStmt(LogoTecParser.MientrasStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#mientrasStmt}.
	 * @param ctx the parse tree
	 */
	void exitMientrasStmt(LogoTecParser.MientrasStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#hastaStmt}.
	 * @param ctx the parse tree
	 */
	void enterHastaStmt(LogoTecParser.HastaStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#hastaStmt}.
	 * @param ctx the parse tree
	 */
	void exitHastaStmt(LogoTecParser.HastaStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#turtleCmd}.
	 * @param ctx the parse tree
	 */
	void enterTurtleCmd(LogoTecParser.TurtleCmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#turtleCmd}.
	 * @param ctx the parse tree
	 */
	void exitTurtleCmd(LogoTecParser.TurtleCmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#colorName}.
	 * @param ctx the parse tree
	 */
	void enterColorName(LogoTecParser.ColorNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#colorName}.
	 * @param ctx the parse tree
	 */
	void exitColorName(LogoTecParser.ColorNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(LogoTecParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(LogoTecParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#logicTerm}.
	 * @param ctx the parse tree
	 */
	void enterLogicTerm(LogoTecParser.LogicTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#logicTerm}.
	 * @param ctx the parse tree
	 */
	void exitLogicTerm(LogoTecParser.LogicTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#logicFactor}.
	 * @param ctx the parse tree
	 */
	void enterLogicFactor(LogoTecParser.LogicFactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#logicFactor}.
	 * @param ctx the parse tree
	 */
	void exitLogicFactor(LogoTecParser.LogicFactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#relational}.
	 * @param ctx the parse tree
	 */
	void enterRelational(LogoTecParser.RelationalContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#relational}.
	 * @param ctx the parse tree
	 */
	void exitRelational(LogoTecParser.RelationalContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#arithExpr}.
	 * @param ctx the parse tree
	 */
	void enterArithExpr(LogoTecParser.ArithExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#arithExpr}.
	 * @param ctx the parse tree
	 */
	void exitArithExpr(LogoTecParser.ArithExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(LogoTecParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(LogoTecParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(LogoTecParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(LogoTecParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#exponent}.
	 * @param ctx the parse tree
	 */
	void enterExponent(LogoTecParser.ExponentContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#exponent}.
	 * @param ctx the parse tree
	 */
	void exitExponent(LogoTecParser.ExponentContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#unary}.
	 * @param ctx the parse tree
	 */
	void enterUnary(LogoTecParser.UnaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#unary}.
	 * @param ctx the parse tree
	 */
	void exitUnary(LogoTecParser.UnaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void enterFuncCall(LogoTecParser.FuncCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void exitFuncCall(LogoTecParser.FuncCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(LogoTecParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(LogoTecParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#literalOrString}.
	 * @param ctx the parse tree
	 */
	void enterLiteralOrString(LogoTecParser.LiteralOrStringContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#literalOrString}.
	 * @param ctx the parse tree
	 */
	void exitLiteralOrString(LogoTecParser.LiteralOrStringContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#cmtFirstLine}.
	 * @param ctx the parse tree
	 */
	void enterCmtFirstLine(LogoTecParser.CmtFirstLineContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#cmtFirstLine}.
	 * @param ctx the parse tree
	 */
	void exitCmtFirstLine(LogoTecParser.CmtFirstLineContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#invalidComment}.
	 * @param ctx the parse tree
	 */
	void enterInvalidComment(LogoTecParser.InvalidCommentContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#invalidComment}.
	 * @param ctx the parse tree
	 */
	void exitInvalidComment(LogoTecParser.InvalidCommentContext ctx);
}