package com.miorganizacion.logotec.compilador.ast;

public final class Symbol {
	public final String name;
	public final ValueType type;
	public Object value;

	public Symbol(String n, ValueType t, Object v) {
		this.name = n;
		this.type = t;
		this.value = v;
	}
}
