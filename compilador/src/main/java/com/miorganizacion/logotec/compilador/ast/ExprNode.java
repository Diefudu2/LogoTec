package com.miorganizacion.logotec.compilador.ast;

import java.util.Map;

public interface ExprNode extends ASTNode {
    @Override
    Object execute(Map<String, Object> symbolTable);
}
