// Generated from com\miorganizacion\logotec\compilador\LogoTec.g4 by ANTLR 4.5.1
package com.miorganizacion.logotec.compilador;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link LogoTecVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public class LogoTecBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements LogoTecVisitor<T> {
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitStart(LogoTecParser.StartContext ctx) { return visitChildren(ctx); }
}