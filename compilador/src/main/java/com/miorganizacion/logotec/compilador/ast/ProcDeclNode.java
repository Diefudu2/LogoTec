package com.miorganizacion.logotec.compilador.ast;
import java.util.List;
import java.util.Map;

public class ProcDeclNode implements ASTNode {
    private final String name;
    private final List<String> params;
    private final List<StmtNode> body; // 👈 cambia a StmtNode

    public ProcDeclNode(String name, List<String> params, List<StmtNode> body) {
        this.name = name;
        this.params = params;
        this.body = body;
    }

    @Override
    public Object execute(Map<String,Object> st) {
        st.put(name, this);
        return null;
    }

    public Object call(Map<String,Object> st, List<Object> args) {
        for (int i = 0; i < params.size(); i++) {
            st.put(params.get(i), args.get(i));
        }
        for (StmtNode stmt : body) {
            stmt.execute(st);
        }
        return null;
    }
}
