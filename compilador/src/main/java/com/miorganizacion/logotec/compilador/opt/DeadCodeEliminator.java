package com.miorganizacion.logotec.compilador.opt;

import java.util.*;
import com.miorganizacion.logotec.compilador.ast.*;

/**
 * Eliminador de código muerto.
 * Compatible con Java 8+.
 */
public class DeadCodeEliminator {

    public void rewriteStmtInto(StmtNode s, List<StmtNode> out) {
        if (s == null) return;

        // Flatten de bloques vacíos
        if (s instanceof ExecBlockNode) {
            ExecBlockNode b = (ExecBlockNode) s;
            for (StmtNode i : b.getBody()) {
                rewriteStmtInto(i, out);
            }
            return;
        }

        // Eliminar "INC var 0"
        if (s instanceof IncNode) {
            IncNode n = (IncNode) s;
            ExprNode delta = n.getDelta();
            if (delta instanceof ConstNode) {
                ConstNode c = (ConstNode) delta;
                Object val = c.getValue();
                if (val instanceof Integer && ((Integer) val) == 0) {
                    return; // Eliminar incremento de 0
                }
            }
        }

        out.add(s);
    }
}
