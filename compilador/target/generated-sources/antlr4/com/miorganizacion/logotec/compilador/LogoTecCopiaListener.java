// Generated from LogoTecCopia.g4 by ANTLR 4.4
package com.miorganizacion.logotec.compilador;

    import java.util.*;
    import com.miorganizacion.logotec.compilador.ast.*;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LogoTecCopiaParser}.
 */
public interface LogoTecCopiaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LogoTecCopiaParser#logicTerm}.
	 * @param ctx the parse tree
	 */
	void enterLogicTerm(@NotNull LogoTecCopiaParser.LogicTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecCopiaParser#logicTerm}.
	 * @param ctx the parse tree
	 */
	void exitLogicTerm(@NotNull LogoTecCopiaParser.LogicTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecCopiaParser#colorName}.
	 * @param ctx the parse tree
	 */
	void enterColorName(@NotNull LogoTecCopiaParser.ColorNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecCopiaParser#colorName}.
	 * @param ctx the parse tree
	 */
	void exitColorName(@NotNull LogoTecCopiaParser.ColorNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecCopiaParser#invalidComment}.
	 * @param ctx the parse tree
	 */
	void enterInvalidComment(@NotNull LogoTecCopiaParser.InvalidCommentContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecCopiaParser#invalidComment}.
	 * @param ctx the parse tree
	 */
	void exitInvalidComment(@NotNull LogoTecCopiaParser.InvalidCommentContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecCopiaParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(@NotNull LogoTecCopiaParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecCopiaParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(@NotNull LogoTecCopiaParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecCopiaParser#unary}.
	 * @param ctx the parse tree
	 */
	void enterUnary(@NotNull LogoTecCopiaParser.UnaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecCopiaParser#unary}.
	 * @param ctx the parse tree
	 */
	void exitUnary(@NotNull LogoTecCopiaParser.UnaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecCopiaParser#proceduresBlock}.
	 * @param ctx the parse tree
	 */
	void enterProceduresBlock(@NotNull LogoTecCopiaParser.ProceduresBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecCopiaParser#proceduresBlock}.
	 * @param ctx the parse tree
	 */
	void exitProceduresBlock(@NotNull LogoTecCopiaParser.ProceduresBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecCopiaParser#arithExpr}.
	 * @param ctx the parse tree
	 */
	void enterArithExpr(@NotNull LogoTecCopiaParser.ArithExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecCopiaParser#arithExpr}.
	 * @param ctx the parse tree
	 */
	void exitArithExpr(@NotNull LogoTecCopiaParser.ArithExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecCopiaParser#expressionSeries}.
	 * @param ctx the parse tree
	 */
	void enterExpressionSeries(@NotNull LogoTecCopiaParser.ExpressionSeriesContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecCopiaParser#expressionSeries}.
	 * @param ctx the parse tree
	 */
	void exitExpressionSeries(@NotNull LogoTecCopiaParser.ExpressionSeriesContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecCopiaParser#hazHastaStmt}.
	 * @param ctx the parse tree
	 */
	void enterHazHastaStmt(@NotNull LogoTecCopiaParser.HazHastaStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecCopiaParser#hazHastaStmt}.
	 * @param ctx the parse tree
	 */
	void exitHazHastaStmt(@NotNull LogoTecCopiaParser.HazHastaStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecCopiaParser#siStmt}.
	 * @param ctx the parse tree
	 */
	void enterSiStmt(@NotNull LogoTecCopiaParser.SiStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecCopiaParser#siStmt}.
	 * @param ctx the parse tree
	 */
	void exitSiStmt(@NotNull LogoTecCopiaParser.SiStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecCopiaParser#hastaStmt}.
	 * @param ctx the parse tree
	 */
	void enterHastaStmt(@NotNull LogoTecCopiaParser.HastaStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecCopiaParser#hastaStmt}.
	 * @param ctx the parse tree
	 */
	void exitHastaStmt(@NotNull LogoTecCopiaParser.HastaStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecCopiaParser#hazMientrasStmt}.
	 * @param ctx the parse tree
	 */
	void enterHazMientrasStmt(@NotNull LogoTecCopiaParser.HazMientrasStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecCopiaParser#hazMientrasStmt}.
	 * @param ctx the parse tree
	 */
	void exitHazMientrasStmt(@NotNull LogoTecCopiaParser.HazMientrasStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecCopiaParser#varInit}.
	 * @param ctx the parse tree
	 */
	void enterVarInit(@NotNull LogoTecCopiaParser.VarInitContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecCopiaParser#varInit}.
	 * @param ctx the parse tree
	 */
	void exitVarInit(@NotNull LogoTecCopiaParser.VarInitContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecCopiaParser#execBlock}.
	 * @param ctx the parse tree
	 */
	void enterExecBlock(@NotNull LogoTecCopiaParser.ExecBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecCopiaParser#execBlock}.
	 * @param ctx the parse tree
	 */
	void exitExecBlock(@NotNull LogoTecCopiaParser.ExecBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecCopiaParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(@NotNull LogoTecCopiaParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecCopiaParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(@NotNull LogoTecCopiaParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecCopiaParser#turtleCmd}.
	 * @param ctx the parse tree
	 */
	void enterTurtleCmd(@NotNull LogoTecCopiaParser.TurtleCmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecCopiaParser#turtleCmd}.
	 * @param ctx the parse tree
	 */
	void exitTurtleCmd(@NotNull LogoTecCopiaParser.TurtleCmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecCopiaParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(@NotNull LogoTecCopiaParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecCopiaParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(@NotNull LogoTecCopiaParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecCopiaParser#exponent}.
	 * @param ctx the parse tree
	 */
	void enterExponent(@NotNull LogoTecCopiaParser.ExponentContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecCopiaParser#exponent}.
	 * @param ctx the parse tree
	 */
	void exitExponent(@NotNull LogoTecCopiaParser.ExponentContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecCopiaParser#sentence}.
	 * @param ctx the parse tree
	 */
	void enterSentence(@NotNull LogoTecCopiaParser.SentenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecCopiaParser#sentence}.
	 * @param ctx the parse tree
	 */
	void exitSentence(@NotNull LogoTecCopiaParser.SentenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecCopiaParser#callProc}.
	 * @param ctx the parse tree
	 */
	void enterCallProc(@NotNull LogoTecCopiaParser.CallProcContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecCopiaParser#callProc}.
	 * @param ctx the parse tree
	 */
	void exitCallProc(@NotNull LogoTecCopiaParser.CallProcContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecCopiaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull LogoTecCopiaParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecCopiaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull LogoTecCopiaParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecCopiaParser#literalOrString}.
	 * @param ctx the parse tree
	 */
	void enterLiteralOrString(@NotNull LogoTecCopiaParser.LiteralOrStringContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecCopiaParser#literalOrString}.
	 * @param ctx the parse tree
	 */
	void exitLiteralOrString(@NotNull LogoTecCopiaParser.LiteralOrStringContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecCopiaParser#cmtFirstLine}.
	 * @param ctx the parse tree
	 */
	void enterCmtFirstLine(@NotNull LogoTecCopiaParser.CmtFirstLineContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecCopiaParser#cmtFirstLine}.
	 * @param ctx the parse tree
	 */
	void exitCmtFirstLine(@NotNull LogoTecCopiaParser.CmtFirstLineContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecCopiaParser#repiteBlock}.
	 * @param ctx the parse tree
	 */
	void enterRepiteBlock(@NotNull LogoTecCopiaParser.RepiteBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecCopiaParser#repiteBlock}.
	 * @param ctx the parse tree
	 */
	void exitRepiteBlock(@NotNull LogoTecCopiaParser.RepiteBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecCopiaParser#logicFactor}.
	 * @param ctx the parse tree
	 */
	void enterLogicFactor(@NotNull LogoTecCopiaParser.LogicFactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecCopiaParser#logicFactor}.
	 * @param ctx the parse tree
	 */
	void exitLogicFactor(@NotNull LogoTecCopiaParser.LogicFactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecCopiaParser#primaryArg}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryArg(@NotNull LogoTecCopiaParser.PrimaryArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecCopiaParser#primaryArg}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryArg(@NotNull LogoTecCopiaParser.PrimaryArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecCopiaParser#procedureDecl}.
	 * @param ctx the parse tree
	 */
	void enterProcedureDecl(@NotNull LogoTecCopiaParser.ProcedureDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecCopiaParser#procedureDecl}.
	 * @param ctx the parse tree
	 */
	void exitProcedureDecl(@NotNull LogoTecCopiaParser.ProcedureDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecCopiaParser#relational}.
	 * @param ctx the parse tree
	 */
	void enterRelational(@NotNull LogoTecCopiaParser.RelationalContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecCopiaParser#relational}.
	 * @param ctx the parse tree
	 */
	void exitRelational(@NotNull LogoTecCopiaParser.RelationalContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecCopiaParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void enterFuncCall(@NotNull LogoTecCopiaParser.FuncCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecCopiaParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void exitFuncCall(@NotNull LogoTecCopiaParser.FuncCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecCopiaParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(@NotNull LogoTecCopiaParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecCopiaParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(@NotNull LogoTecCopiaParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecCopiaParser#mientrasStmt}.
	 * @param ctx the parse tree
	 */
	void enterMientrasStmt(@NotNull LogoTecCopiaParser.MientrasStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecCopiaParser#mientrasStmt}.
	 * @param ctx the parse tree
	 */
	void exitMientrasStmt(@NotNull LogoTecCopiaParser.MientrasStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoTecCopiaParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(@NotNull LogoTecCopiaParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecCopiaParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(@NotNull LogoTecCopiaParser.PrimaryContext ctx);
}