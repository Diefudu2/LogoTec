// Generated from LogoTec.g4 by ANTLR 4.4
package com.miorganizacion.logotec.compilador;

    import java.util.*;
    import com.miorganizacion.logotec.compilador.ast.*;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LogoTecParser}.
 */
public interface LogoTecListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#logicTerm}.
	 * @param ctx the parse tree
	 */
	void enterLogicTerm(@NotNull LogoTecParser.LogicTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#logicTerm}.
	 * @param ctx the parse tree
	 */
	void exitLogicTerm(@NotNull LogoTecParser.LogicTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#colorName}.
	 * @param ctx the parse tree
	 */
	void enterColorName(@NotNull LogoTecParser.ColorNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#colorName}.
	 * @param ctx the parse tree
	 */
	void exitColorName(@NotNull LogoTecParser.ColorNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#invalidComment}.
	 * @param ctx the parse tree
	 */
	void enterInvalidComment(@NotNull LogoTecParser.InvalidCommentContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#invalidComment}.
	 * @param ctx the parse tree
	 */
	void exitInvalidComment(@NotNull LogoTecParser.InvalidCommentContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(@NotNull LogoTecParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(@NotNull LogoTecParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#unary}.
	 * @param ctx the parse tree
	 */
	void enterUnary(@NotNull LogoTecParser.UnaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#unary}.
	 * @param ctx the parse tree
	 */
	void exitUnary(@NotNull LogoTecParser.UnaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#proceduresBlock}.
	 * @param ctx the parse tree
	 */
	void enterProceduresBlock(@NotNull LogoTecParser.ProceduresBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#proceduresBlock}.
	 * @param ctx the parse tree
	 */
	void exitProceduresBlock(@NotNull LogoTecParser.ProceduresBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#arithExpr}.
	 * @param ctx the parse tree
	 */
	void enterArithExpr(@NotNull LogoTecParser.ArithExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#arithExpr}.
	 * @param ctx the parse tree
	 */
	void exitArithExpr(@NotNull LogoTecParser.ArithExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#expressionSeries}.
	 * @param ctx the parse tree
	 */
	void enterExpressionSeries(@NotNull LogoTecParser.ExpressionSeriesContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#expressionSeries}.
	 * @param ctx the parse tree
	 */
	void exitExpressionSeries(@NotNull LogoTecParser.ExpressionSeriesContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#hazHastaStmt}.
	 * @param ctx the parse tree
	 */
	void enterHazHastaStmt(@NotNull LogoTecParser.HazHastaStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#hazHastaStmt}.
	 * @param ctx the parse tree
	 */
	void exitHazHastaStmt(@NotNull LogoTecParser.HazHastaStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#siStmt}.
	 * @param ctx the parse tree
	 */
	void enterSiStmt(@NotNull LogoTecParser.SiStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#siStmt}.
	 * @param ctx the parse tree
	 */
	void exitSiStmt(@NotNull LogoTecParser.SiStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#hastaStmt}.
	 * @param ctx the parse tree
	 */
	void enterHastaStmt(@NotNull LogoTecParser.HastaStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#hastaStmt}.
	 * @param ctx the parse tree
	 */
	void exitHastaStmt(@NotNull LogoTecParser.HastaStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#hazMientrasStmt}.
	 * @param ctx the parse tree
	 */
	void enterHazMientrasStmt(@NotNull LogoTecParser.HazMientrasStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#hazMientrasStmt}.
	 * @param ctx the parse tree
	 */
	void exitHazMientrasStmt(@NotNull LogoTecParser.HazMientrasStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#varInit}.
	 * @param ctx the parse tree
	 */
	void enterVarInit(@NotNull LogoTecParser.VarInitContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#varInit}.
	 * @param ctx the parse tree
	 */
	void exitVarInit(@NotNull LogoTecParser.VarInitContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#execBlock}.
	 * @param ctx the parse tree
	 */
	void enterExecBlock(@NotNull LogoTecParser.ExecBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#execBlock}.
	 * @param ctx the parse tree
	 */
	void exitExecBlock(@NotNull LogoTecParser.ExecBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(@NotNull LogoTecParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(@NotNull LogoTecParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#turtleCmd}.
	 * @param ctx the parse tree
	 */
	void enterTurtleCmd(@NotNull LogoTecParser.TurtleCmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#turtleCmd}.
	 * @param ctx the parse tree
	 */
	void exitTurtleCmd(@NotNull LogoTecParser.TurtleCmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(@NotNull LogoTecParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(@NotNull LogoTecParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#exponent}.
	 * @param ctx the parse tree
	 */
	void enterExponent(@NotNull LogoTecParser.ExponentContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#exponent}.
	 * @param ctx the parse tree
	 */
	void exitExponent(@NotNull LogoTecParser.ExponentContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#sentence}.
	 * @param ctx the parse tree
	 */
	void enterSentence(@NotNull LogoTecParser.SentenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#sentence}.
	 * @param ctx the parse tree
	 */
	void exitSentence(@NotNull LogoTecParser.SentenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#callProc}.
	 * @param ctx the parse tree
	 */
	void enterCallProc(@NotNull LogoTecParser.CallProcContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#callProc}.
	 * @param ctx the parse tree
	 */
	void exitCallProc(@NotNull LogoTecParser.CallProcContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull LogoTecParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull LogoTecParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#literalOrString}.
	 * @param ctx the parse tree
	 */
	void enterLiteralOrString(@NotNull LogoTecParser.LiteralOrStringContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#literalOrString}.
	 * @param ctx the parse tree
	 */
	void exitLiteralOrString(@NotNull LogoTecParser.LiteralOrStringContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#cmtFirstLine}.
	 * @param ctx the parse tree
	 */
	void enterCmtFirstLine(@NotNull LogoTecParser.CmtFirstLineContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#cmtFirstLine}.
	 * @param ctx the parse tree
	 */
	void exitCmtFirstLine(@NotNull LogoTecParser.CmtFirstLineContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#repiteBlock}.
	 * @param ctx the parse tree
	 */
	void enterRepiteBlock(@NotNull LogoTecParser.RepiteBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#repiteBlock}.
	 * @param ctx the parse tree
	 */
	void exitRepiteBlock(@NotNull LogoTecParser.RepiteBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#logicFactor}.
	 * @param ctx the parse tree
	 */
	void enterLogicFactor(@NotNull LogoTecParser.LogicFactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#logicFactor}.
	 * @param ctx the parse tree
	 */
	void exitLogicFactor(@NotNull LogoTecParser.LogicFactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#primaryArg}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryArg(@NotNull LogoTecParser.PrimaryArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#primaryArg}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryArg(@NotNull LogoTecParser.PrimaryArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#procedureDecl}.
	 * @param ctx the parse tree
	 */
	void enterProcedureDecl(@NotNull LogoTecParser.ProcedureDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#procedureDecl}.
	 * @param ctx the parse tree
	 */
	void exitProcedureDecl(@NotNull LogoTecParser.ProcedureDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#relational}.
	 * @param ctx the parse tree
	 */
	void enterRelational(@NotNull LogoTecParser.RelationalContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#relational}.
	 * @param ctx the parse tree
	 */
	void exitRelational(@NotNull LogoTecParser.RelationalContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void enterFuncCall(@NotNull LogoTecParser.FuncCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void exitFuncCall(@NotNull LogoTecParser.FuncCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(@NotNull LogoTecParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(@NotNull LogoTecParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#mientrasStmt}.
	 * @param ctx the parse tree
	 */
	void enterMientrasStmt(@NotNull LogoTecParser.MientrasStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#mientrasStmt}.
	 * @param ctx the parse tree
	 */
	void exitMientrasStmt(@NotNull LogoTecParser.MientrasStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(@NotNull LogoTecParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(@NotNull LogoTecParser.PrimaryContext ctx);
}