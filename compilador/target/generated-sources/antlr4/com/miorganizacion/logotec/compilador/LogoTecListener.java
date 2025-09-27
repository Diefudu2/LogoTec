// Generated from LogoTec.g4 by ANTLR 4.4
package com.miorganizacion.logotec.compilador;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LogoTecParser}.
 */
public interface LogoTecListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LogoTecParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(@NotNull LogoTecParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoTecParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(@NotNull LogoTecParser.StartContext ctx);
}