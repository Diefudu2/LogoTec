package com.miorganizacion.logotec.compilador.opt;

import java.util.*;
import com.miorganizacion.logotec.compilador.ast.*;

public class DeadCodeEliminator {

    public void rewriteStmtInto(StmtNode s, List<StmtNode> out) {
        if (s == null) return;

        // Flatten de bloques vacíos
        if (s instanceof ExecBlockNode b) {
            for (StmtNode i : b.getBody()) rewriteStmtInto(i, out);
            return;
        }

        // if con ramas vacías removible si no tiene efectos (conservador: mantenemos)
        // while(true) etc. no se elimina aquí para no cambiar semántica.

        // Eliminar "INC var 0"
        if (s instanceof IncNode n) {
            if (n.getDelta() instanceof ConstNode c && c.getValue() instanceof Integer v && v == 0) {
                return;
            }
        }

        out.add(s);
    }
}
