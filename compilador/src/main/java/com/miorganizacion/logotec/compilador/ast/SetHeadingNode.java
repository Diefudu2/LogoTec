package com.miorganizacion.logotec.compilador.ast;
import java.util.Map;

/* Rumbo */
public class SetHeadingNode implements StmtNode {
    private final ExprNode angle;
    public SetHeadingNode(ExprNode angle) { this.angle = angle; }
    @Override public Object execute(Map<String,Object> st) {
        Object val = angle.execute(st);
        if (!(val instanceof Number)) throw new RuntimeException("Rumbo requiere número");
        System.out.println("SetHeading " + val);
        return null;
    }
}
