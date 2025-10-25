package com.miorganizacion.logotec.compilador.ast;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

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
        if (args.size() != params.size()) {
            throw new RuntimeException("Procedimiento '" + name + "' espera " + params.size()
                                       + " argumento(s), recibió " + args.size());
        }

        Map<String,Object> previous = new HashMap<>();
        Set<String> newParams = new HashSet<>();

        for (int i = 0; i < params.size(); i++) {
            String paramName = params.get(i);
            if (st.containsKey(paramName)) {
                previous.put(paramName, st.get(paramName));
            } else {
                newParams.add(paramName);
            }
            st.put(paramName, args.get(i));
        }

        try {
            for (StmtNode stmt : body) {
                if (stmt != null) {
                    stmt.execute(st);
                }
            }
        } finally {
            for (String paramName : params) {
                if (previous.containsKey(paramName)) {
                    st.put(paramName, previous.get(paramName));
                } else if (newParams.contains(paramName)) {
                    st.remove(paramName);
                }
            }
        }
        return null;
    }
}
