package com.miorganizacion.logotec.compilador.ast;
import java.util.Map;
import java.util.Scanner;

public class InputNode implements ASTNode {
    private String prompt;
    public InputNode(String prompt) { this.prompt = prompt; }
    @Override public Object execute(Map<String,Object> st) {
        System.out.print(prompt + ": ");
        @SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
