// Generated from com\miorganizacion\logotec\compilador\LogoTec.g4 by ANTLR 4.5.1
package com.miorganizacion.logotec.compilador;
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
	 * Visit a parse tree produced by {@link LogoTecParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(LogoTecParser.StartContext ctx);
}