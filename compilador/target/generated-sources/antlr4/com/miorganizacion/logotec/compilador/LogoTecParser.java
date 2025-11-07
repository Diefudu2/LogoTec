// Generated from com\miorganizacion\logotec\compilador\LogoTec.g4 by ANTLR 4.5.1
package com.miorganizacion.logotec.compilador;

    import java.util.*;
    import com.miorganizacion.logotec.compilador.ast.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LogoTecParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PARA=1, HAZ=2, FIN=3, EJECUTA=4, REPITE=5, APARECETORTUGA=6, AT=7, PONPOS=8, 
		PONXY=9, PONRUMBO=10, RUMBO=11, PONX=12, PONY=13, BAJALAPIZ=14, BL=15, 
		SUBELAPIZ=16, SB=17, CENTRO=18, ESPERA=19, INC=20, SI=21, HASTA=22, MIENTRAS=23, 
		INIC=24, AVANZA=25, AV=26, RETROCEDE=27, RE=28, ATRAS=29, GIRADERECHA=30, 
		GD=31, GIRAIZQUIERDA=32, GI=33, OCULTATORTUGA=34, OT=35, PONCOLORLAPIZ=36, 
		PONCL_ALIAS=37, PONCL=38, IGUALESQ=39, YFUNC=40, OFUNC=41, MAYORQ=42, 
		MENORQ=43, DIFERENCIA=44, AZAR=45, PRODUCTO=46, POTENCIA=47, DIVISION=48, 
		SUMA=49, PLUS=50, MINUS=51, MULT=52, DIV=53, EXP=54, AND=55, OR=56, NOT=57, 
		GT=58, LT=59, GEQ=60, LEQ=61, EQ=62, NEQ=63, ASSIGN=64, BRACKET_OPEN=65, 
		BRACKET_CLOSE=66, PAR_OPEN=67, PAR_CLOSE=68, SEMICOLON=69, COMMA=70, DOT=71, 
		BOOLEAN=72, DECIMAL=73, NUMBER=74, STRING=75, ID=76, FIRSTLINE_COMMENT=77, 
		COMMENT_LINE=78, INVALID_COMMENT=79, WS=80, COLOR=81;
	public static final int
		RULE_program = 0, RULE_proceduresBlock = 1, RULE_procedureDecl = 2, RULE_sentence = 3, 
		RULE_varDecl = 4, RULE_varInit = 5, RULE_callProc = 6, RULE_primaryArg = 7, 
		RULE_expressionSeries = 8, RULE_execBlock = 9, RULE_repiteBlock = 10, 
		RULE_siStmt = 11, RULE_hazDoStmt = 12, RULE_mientrasStmt = 13, RULE_hastaStmt = 14, 
		RULE_turtleCmd = 15, RULE_colorName = 16, RULE_expression = 17, RULE_logicTerm = 18, 
		RULE_logicFactor = 19, RULE_relational = 20, RULE_arithExpr = 21, RULE_term = 22, 
		RULE_factor = 23, RULE_exponent = 24, RULE_unary = 25, RULE_funcCall = 26, 
		RULE_primary = 27, RULE_literalOrString = 28, RULE_cmtFirstLine = 29, 
		RULE_invalidComment = 30;
	public static final String[] ruleNames = {
		"program", "proceduresBlock", "procedureDecl", "sentence", "varDecl", 
		"varInit", "callProc", "primaryArg", "expressionSeries", "execBlock", 
		"repiteBlock", "siStmt", "hazDoStmt", "mientrasStmt", "hastaStmt", "turtleCmd", 
		"colorName", "expression", "logicTerm", "logicFactor", "relational", "arithExpr", 
		"term", "factor", "exponent", "unary", "funcCall", "primary", "literalOrString", 
		"cmtFirstLine", "invalidComment"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, "'SI'", "'HASTA'", 
		"'MIENTRAS'", "'INIC'", null, null, null, null, "'atras'", null, null, 
		null, null, "'ocultatortuga'", "'ot'", null, "'ponCL'", null, null, "'Y'", 
		"'O'", null, null, null, null, null, null, null, null, "'+'", "'-'", "'*'", 
		"'/'", "'^'", "'&&'", "'||'", "'!'", "'>'", "'<'", "'>='", "'<='", "'=='", 
		"'!='", "'='", null, null, "'('", "')'", "';'", null, "'.'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "PARA", "HAZ", "FIN", "EJECUTA", "REPITE", "APARECETORTUGA", "AT", 
		"PONPOS", "PONXY", "PONRUMBO", "RUMBO", "PONX", "PONY", "BAJALAPIZ", "BL", 
		"SUBELAPIZ", "SB", "CENTRO", "ESPERA", "INC", "SI", "HASTA", "MIENTRAS", 
		"INIC", "AVANZA", "AV", "RETROCEDE", "RE", "ATRAS", "GIRADERECHA", "GD", 
		"GIRAIZQUIERDA", "GI", "OCULTATORTUGA", "OT", "PONCOLORLAPIZ", "PONCL_ALIAS", 
		"PONCL", "IGUALESQ", "YFUNC", "OFUNC", "MAYORQ", "MENORQ", "DIFERENCIA", 
		"AZAR", "PRODUCTO", "POTENCIA", "DIVISION", "SUMA", "PLUS", "MINUS", "MULT", 
		"DIV", "EXP", "AND", "OR", "NOT", "GT", "LT", "GEQ", "LEQ", "EQ", "NEQ", 
		"ASSIGN", "BRACKET_OPEN", "BRACKET_CLOSE", "PAR_OPEN", "PAR_CLOSE", "SEMICOLON", 
		"COMMA", "DOT", "BOOLEAN", "DECIMAL", "NUMBER", "STRING", "ID", "FIRSTLINE_COMMENT", 
		"COMMENT_LINE", "INVALID_COMMENT", "WS", "COLOR"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "LogoTec.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	/* 2 ------------------- Variables de estado ------------------- */
	    Map<String, Symbol> symbols = new HashMap<>();
	    List<String> errors = new ArrayList<>();
	    boolean firstLineHasComment = false;
	    boolean atLeastOneVariable = false;

	/* 3 ------------------- Utilidad para línea actual ------------------- */
	    int currentLine() {
	        return getCurrentToken() != null ? getCurrentToken().getLine() : -1;
	    }

	/* 4 ------------------- Manejo de llamadas pendientes ------------------- */
	    static class PendingCall {
	        final String name;
	        final int argCount;
	        final int line;
	        PendingCall(String name, int argCount, int line) {
	            this.name = name;
	            this.argCount = argCount;
	            this.line = line;
	        }
	    }
	    List<PendingCall> pendingCalls = new ArrayList<>();

	/* 5 ------------------- Validación de restricciones del programa ------------------- */
	    void ensureProgramConstraints() {
	        if (!firstLineHasComment) {
	            errors.add("Error en línea 1: el programa debe iniciar con un comentario (// ...).");
	        }
	        if (!atLeastOneVariable) {
	            errors.add("Error: el programa debe definir al menos una variable con 'Haz' o 'INIC'.");
	        }
	        for (PendingCall pc : pendingCalls) {
	            Symbol s = symbols.get(pc.name);
	            if (s == null) {
	                errors.add("Error en línea " + pc.line + ": procedimiento '" + pc.name + "' no está definido.");
	            } else if (s.type != ValueType.PROCEDURE) {
	                errors.add("Error en línea " + pc.line + ": '" + pc.name + "' no es un procedimiento.");
	            } else {
	                int expectedParams = (Integer) s.value;
	                if (pc.argCount != expectedParams) {
	                    errors.add("Error en línea " + pc.line + ": procedimiento '" + pc.name + "' espera " + expectedParams +
	                               " parámetros, pero se llamó con " + pc.argCount + ".");
	                }
	            }
	        }
	        pendingCalls.clear();
	        if (!errors.isEmpty()) {
	            throw new RuntimeException(String.join("\n", errors));
	        }
	    }

	/* 6 ------------------- Validación de nombres de variables ------------------- */
	    boolean isValidVarName(String id) {
	        if (id.length() == 0 || id.length() > 10) return false;
	        if (!Character.isLowerCase(id.charAt(0))) return false;
	        for (char c : id.toCharArray()) {
	            if (!(Character.isLetterOrDigit(c) || c=='_' || c=='&' || c=='@')) return false;
	        }
	        return true;
	    }
	    
	    /**
	     * Valida longitud de identificador en cualquier contexto.
	     * Restricción #3
	     */
	    void validateIdentifierLength(String id, int line) {
	        if (id.length() > 10) {
	            errors.add("Error en línea " + line + 
	                       ": identificador '" + id + "' excede el límite de 10 caracteres (tiene " + id.length() + ").");
	        }
	    }

	/* 7 ------------------- Declaración y asignación de variables ------------------- */
	    void declareOrAssign(String name, ValueType type, Object value) {
	        if (!isValidVarName(name)) {
	            errors.add("Error en línea " + currentLine() + ": identificador inválido '" + name + "'.");
	            return;
	        }
	        Symbol s = symbols.get(name);
	        if (s == null) {
	            symbols.put(name, new Symbol(name, type, value));
	            atLeastOneVariable = true;
	        } else {
	            if (s.type != type) {
	                errors.add("Error en línea " + currentLine() + ": intento de asignar " + type +
	                           " a variable '" + name + "' de tipo " + s.type + ".");
	            } else {
	                s.value = value;
	            }
	        }
	    }

	/* 8 ------------------- GRUPO 2: INIC solo inicializa ------------------- */
	    void assignInitOnlyIfDeclared(String name, ValueType valueType, ExprNode exprNode) {
	        int line = currentLine();
	        if (!isValidVarName(name)) {
	            errors.add("Error en línea " + line + ": identificador inválido '" + name + "'.");
	            return;
	        }
	        Symbol s = symbols.get(name);
	        if (s == null) {
	            errors.add("Error en línea " + line +
	                       ": variable '" + name + "' no ha sido declarada antes de inicializar.");
	            return;
	        }
	        if (s.type != valueType) {
	            errors.add("Error en línea " + line +
	                       ": intento de asignar " + valueType + " a variable '" + name +
	                       "' de tipo " + s.type + ".");
	            return;
	        }
	        s.value = exprNode;
	    }

	/* 9 ------------------- Declaración de procedimientos ------------------- */
	    void predeclareProcedure(String name) {
	        Symbol s = symbols.get(name);
	        if (s == null) {
	            symbols.put(name, new Symbol(name, ValueType.PROCEDURE, null));
	        } else if (s.type == ValueType.PROCEDURE && s.value == null) {
	            errors.add("Error en línea " + currentLine() + ": procedimiento '" + name + "' ya está en proceso de definición.");
	        } else {
	            errors.add("Error en línea " + currentLine() + ": símbolo '" + name + "' ya está definido y no puede declararse como procedimiento.");
	        }
	    }

	    void declareProcedure(String name, int paramCount) {
	        Symbol s = symbols.get(name);
	        if (s == null) {
	            symbols.put(name, new Symbol(name, ValueType.PROCEDURE, paramCount));
	        } else if (s.type != ValueType.PROCEDURE) {
	            errors.add("Error en línea " + currentLine() + ": símbolo '" + name + "' ya está definido y no es un procedimiento.");
	        } else if (s.value == null) {
	            s.value = paramCount;
	        } else {
	            int expectedParams = (Integer) s.value;
	            if (expectedParams != paramCount) {
	                errors.add("Error en línea " + currentLine() + ": procedimiento '" + name +
	                           "' ya está definido con " + expectedParams + " parámetros.");
	            }
	        }
	    }

	/* 10 ------------------- Validación de llamadas a procedimientos ------------------- */
	    void validateProcedureCall(String name, int argCount) {
	        Symbol s = symbols.get(name);
	        int line = currentLine();
	        if (s == null || (s.type == ValueType.PROCEDURE && s.value == null)) {
	            pendingCalls.add(new PendingCall(name, argCount, line));
	            return;
	        }
	        if (s.type != ValueType.PROCEDURE) {
	            errors.add("Error en línea " + line + ": '" + name + "' no es un procedimiento.");
	        } else {
	            int expectedParams = (Integer) s.value;
	            if (argCount != expectedParams) {
	                errors.add("Error en línea " + line + ": procedimiento '" + name + "' espera " +
	                           expectedParams + " parámetros, pero se llamó con " + argCount + ".");
	            }
	        }
	    }

	/* 11 ------------------- GRUPO 1: Validación de tipos en expresiones ------------------- */
	    void requireNumericType(ExprNode expr, String commandName) {
	        ValueType t = ValueType.infer(expr, symbols);
	        if (t != ValueType.INT && t != ValueType.UNKNOWN) {
	            errors.add("Error en línea " + currentLine() + 
	                       ": comando '" + commandName + "' requiere argumento numérico, recibió " + t + ".");
	        }
	    }

	    void requireBooleanType(ExprNode expr, String context) {
	        ValueType t = ValueType.infer(expr, symbols);
	        if (t != ValueType.BOOL && t != ValueType.UNKNOWN) {
	            errors.add("Error en línea " + currentLine() + 
	                       ": " + context + " requiere expresión booleana, recibió " + t + ".");
	        }
	    }

	    void validateIncrement(String varName, ExprNode incrementExpr) {
	        int line = currentLine();
	        Symbol s = symbols.get(varName);
	        if (s == null) {
	            errors.add("Error en línea " + line + 
	                       ": variable '" + varName + "' no declarada.");
	            return;
	        }
	        if (s.type != ValueType.INT) {
	            errors.add("Error en línea " + line + 
	                       ": INC solo puede incrementar variables numéricas, '" + varName + 
	                       "' es de tipo " + s.type + ".");
	        }
	        ValueType incType = ValueType.infer(incrementExpr, symbols);
	        if (incType != ValueType.INT && incType != ValueType.UNKNOWN) {
	            errors.add("Error en línea " + line + 
	                       ": INC requiere incremento numérico, recibió " + incType + ".");
	        }
	    }

	    void requireNumericArgs(ExprNode arg1, ExprNode arg2, String funcName) {
	        int line = currentLine();
	        ValueType t1 = ValueType.infer(arg1, symbols);
	        ValueType t2 = arg2 != null ? ValueType.infer(arg2, symbols) : ValueType.INT;
	        if (t1 != ValueType.INT && t1 != ValueType.UNKNOWN) {
	            errors.add("Error en línea " + line + 
	                       ": función '" + funcName + "' requiere argumentos numéricos, primer argumento es " + t1 + ".");
	        }
	        if (arg2 != null && t2 != ValueType.INT && t2 != ValueType.UNKNOWN) {
	            errors.add("Error en línea " + line + 
	                       ": función '" + funcName + "' requiere argumentos numéricos, segundo argumento es " + t2 + ".");
	        }
	    }
	    
	/* GRUPO 3: Validación de bloques de control de flujo */
	    
	    /**
	     * Valida que un bloque de sentencias no esté vacío.
	     * Restricciones #37, #39
	     */
	    void requireNonEmptyBlock(List<StmtNode> body, String blockType) {
	        if (body == null || body.isEmpty()) {
	            errors.add("Error en línea " + currentLine() + 
	                       ": bloque de '" + blockType + "' no puede estar vacío.");
	        }
	    }

	    /**
	     * Valida que un SI solo tenga un bloque ELSE (máximo).
	     * Restricción #36
	     */
	    void validateSingleElse(int elseBlockCount, String context) {
	        if (elseBlockCount > 1) {
	            errors.add("Error en línea " + currentLine() + 
	                       ": " + context + " solo puede tener un bloque ELSE, se encontraron " + elseBlockCount + ".");
	        }
	    }

	    /**
	     * Valida que HAZ.HASTA/HAZ.MIENTRAS tenga condición.
	     * Restricción #38, #40
	     */
	    void requireCondition(ExprNode condition, String loopType) {
	        if (condition == null) {
	            errors.add("Error en línea " + currentLine() + 
	                       ": '" + loopType + "' requiere una condición entre paréntesis.");
	        }
	    }

	    /**
	     * Valida que funciones binarias tengan exactamente 2 argumentos.
	     * Restricción #44
	     */
	    void requireTwoArguments(int argCount, String funcName, int line) {
	        if (argCount != 2) {
	            errors.add("Error en línea " + line + 
	                       ": función '" + funcName + "' requiere exactamente 2 argumentos, recibió " + argCount + ".");
	        }
	    }

	    /**
	     * Valida que funciones unarias tengan exactamente 1 argumento.
	     */
	    void requireOneArgument(int argCount, String funcName, int line) {
	        if (argCount != 1) {
	            errors.add("Error en línea " + line + 
	                       ": función '" + funcName + "' requiere exactamente 1 argumento, recibió " + argCount + ".");
	        }
	    }

	public LogoTecParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public ProgramNode node;
		public ProceduresBlockContext p;
		public TerminalNode EOF() { return getToken(LogoTecParser.EOF, 0); }
		public ProceduresBlockContext proceduresBlock() {
			return getRuleContext(ProceduresBlockContext.class,0);
		}
		public CmtFirstLineContext cmtFirstLine() {
			return getRuleContext(CmtFirstLineContext.class,0);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogoTecVisitor ) return ((LogoTecVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			_la = _input.LA(1);
			if (_la==FIRSTLINE_COMMENT) {
				{
				setState(62);
				cmtFirstLine();
				}
			}

			setState(65);
			((ProgramContext)_localctx).p = proceduresBlock();
			setState(66);
			match(EOF);

			        ensureProgramConstraints();
			        ((ProgramContext)_localctx).node =  ((ProgramContext)_localctx).p.node;
			      
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProceduresBlockContext extends ParserRuleContext {
		public ProgramNode node;
		public ProcedureDeclContext procedureDecl;
		public SentenceContext sentence;
		public List<ProcedureDeclContext> procedureDecl() {
			return getRuleContexts(ProcedureDeclContext.class);
		}
		public ProcedureDeclContext procedureDecl(int i) {
			return getRuleContext(ProcedureDeclContext.class,i);
		}
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public List<InvalidCommentContext> invalidComment() {
			return getRuleContexts(InvalidCommentContext.class);
		}
		public InvalidCommentContext invalidComment(int i) {
			return getRuleContext(InvalidCommentContext.class,i);
		}
		public ProceduresBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_proceduresBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterProceduresBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitProceduresBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogoTecVisitor ) return ((LogoTecVisitor<? extends T>)visitor).visitProceduresBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProceduresBlockContext proceduresBlock() throws RecognitionException {
		ProceduresBlockContext _localctx = new ProceduresBlockContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_proceduresBlock);

		        List<ProcDeclNode> decls = new ArrayList<>();
		        List<StmtNode> mainBody = new ArrayList<>();
		    
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PARA) | (1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONCOLORLAPIZ) | (1L << PONCL))) != 0) || _la==ID || _la==INVALID_COMMENT) {
				{
				setState(76);
				switch (_input.LA(1)) {
				case PARA:
					{
					setState(69);
					((ProceduresBlockContext)_localctx).procedureDecl = procedureDecl();
					 decls.add(((ProceduresBlockContext)_localctx).procedureDecl.node); 
					}
					break;
				case HAZ:
				case EJECUTA:
				case REPITE:
				case APARECETORTUGA:
				case AT:
				case PONPOS:
				case PONXY:
				case PONRUMBO:
				case RUMBO:
				case PONX:
				case PONY:
				case BAJALAPIZ:
				case BL:
				case SUBELAPIZ:
				case SB:
				case CENTRO:
				case ESPERA:
				case INC:
				case SI:
				case HASTA:
				case MIENTRAS:
				case INIC:
				case AVANZA:
				case AV:
				case RETROCEDE:
				case RE:
				case GIRADERECHA:
				case GD:
				case GIRAIZQUIERDA:
				case GI:
				case OCULTATORTUGA:
				case OT:
				case PONCOLORLAPIZ:
				case PONCL:
				case ID:
					{
					setState(72);
					((ProceduresBlockContext)_localctx).sentence = sentence();
					 mainBody.add(((ProceduresBlockContext)_localctx).sentence.node); 
					}
					break;
				case INVALID_COMMENT:
					{
					setState(75);
					invalidComment();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(80);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			 ((ProceduresBlockContext)_localctx).node =  new ProgramNode(decls, mainBody); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProcedureDeclContext extends ParserRuleContext {
		public ProcDeclNode node;
		public Token procName;
		public Token param;
		public SentenceContext sentence;
		public TerminalNode PARA() { return getToken(LogoTecParser.PARA, 0); }
		public List<TerminalNode> BRACKET_OPEN() { return getTokens(LogoTecParser.BRACKET_OPEN); }
		public TerminalNode BRACKET_OPEN(int i) {
			return getToken(LogoTecParser.BRACKET_OPEN, i);
		}
		public List<TerminalNode> BRACKET_CLOSE() { return getTokens(LogoTecParser.BRACKET_CLOSE); }
		public TerminalNode BRACKET_CLOSE(int i) {
			return getToken(LogoTecParser.BRACKET_CLOSE, i);
		}
		public TerminalNode FIN() { return getToken(LogoTecParser.FIN, 0); }
		public List<TerminalNode> ID() { return getTokens(LogoTecParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(LogoTecParser.ID, i);
		}
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public ProcedureDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_procedureDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterProcedureDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitProcedureDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogoTecVisitor ) return ((LogoTecVisitor<? extends T>)visitor).visitProcedureDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProcedureDeclContext procedureDecl() throws RecognitionException {
		ProcedureDeclContext _localctx = new ProcedureDeclContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_procedureDecl);
		 
		        List<String> params = new ArrayList<>(); 
		        List<StmtNode> body = new ArrayList<>(); 
		    
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(PARA);
			setState(84);
			((ProcedureDeclContext)_localctx).procName = match(ID);
			 
			        validateIdentifierLength((((ProcedureDeclContext)_localctx).procName!=null?((ProcedureDeclContext)_localctx).procName.getText():null), (((ProcedureDeclContext)_localctx).procName!=null?((ProcedureDeclContext)_localctx).procName.getLine():0));
			        predeclareProcedure((((ProcedureDeclContext)_localctx).procName!=null?((ProcedureDeclContext)_localctx).procName.getText():null)); 
			      
			setState(86);
			match(BRACKET_OPEN);
			setState(91);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(87);
				((ProcedureDeclContext)_localctx).param = match(ID);
				 
				          validateIdentifierLength((((ProcedureDeclContext)_localctx).param!=null?((ProcedureDeclContext)_localctx).param.getText():null), (((ProcedureDeclContext)_localctx).param!=null?((ProcedureDeclContext)_localctx).param.getLine():0));
				          params.add(((ProcedureDeclContext)_localctx).param.getText()); 
				          declareOrAssign((((ProcedureDeclContext)_localctx).param!=null?((ProcedureDeclContext)_localctx).param.getText():null), ValueType.UNKNOWN, null);
				        
				}
				}
				setState(93);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(94);
			match(BRACKET_CLOSE);
			 declareProcedure((((ProcedureDeclContext)_localctx).procName!=null?((ProcedureDeclContext)_localctx).procName.getText():null), params.size()); 
			setState(96);
			match(BRACKET_OPEN);
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONCOLORLAPIZ) | (1L << PONCL))) != 0) || _la==ID) {
				{
				{
				setState(97);
				((ProcedureDeclContext)_localctx).sentence = sentence();
				body.add(((ProcedureDeclContext)_localctx).sentence.node);
				}
				}
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(105);
			match(BRACKET_CLOSE);
			setState(106);
			match(FIN);

			        if (!params.isEmpty()) atLeastOneVariable = true;
			        ((ProcedureDeclContext)_localctx).node =  new ProcDeclNode((((ProcedureDeclContext)_localctx).procName!=null?((ProcedureDeclContext)_localctx).procName.getText():null), params, body);
			      
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SentenceContext extends ParserRuleContext {
		public StmtNode node;
		public VarDeclContext varDecl;
		public VarInitContext varInit;
		public HazDoStmtContext hazDoStmt;
		public SiStmtContext siStmt;
		public HastaStmtContext hastaStmt;
		public MientrasStmtContext mientrasStmt;
		public RepiteBlockContext repiteBlock;
		public ExecBlockContext execBlock;
		public TurtleCmdContext turtleCmd;
		public CallProcContext callProc;
		public VarDeclContext varDecl() {
			return getRuleContext(VarDeclContext.class,0);
		}
		public VarInitContext varInit() {
			return getRuleContext(VarInitContext.class,0);
		}
		public HazDoStmtContext hazDoStmt() {
			return getRuleContext(HazDoStmtContext.class,0);
		}
		public SiStmtContext siStmt() {
			return getRuleContext(SiStmtContext.class,0);
		}
		public HastaStmtContext hastaStmt() {
			return getRuleContext(HastaStmtContext.class,0);
		}
		public MientrasStmtContext mientrasStmt() {
			return getRuleContext(MientrasStmtContext.class,0);
		}
		public RepiteBlockContext repiteBlock() {
			return getRuleContext(RepiteBlockContext.class,0);
		}
		public ExecBlockContext execBlock() {
			return getRuleContext(ExecBlockContext.class,0);
		}
		public TurtleCmdContext turtleCmd() {
			return getRuleContext(TurtleCmdContext.class,0);
		}
		public CallProcContext callProc() {
			return getRuleContext(CallProcContext.class,0);
		}
		public SentenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sentence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterSentence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitSentence(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogoTecVisitor ) return ((LogoTecVisitor<? extends T>)visitor).visitSentence(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SentenceContext sentence() throws RecognitionException {
		SentenceContext _localctx = new SentenceContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_sentence);
		try {
			setState(139);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(109);
				((SentenceContext)_localctx).varDecl = varDecl();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).varDecl.node; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(112);
				((SentenceContext)_localctx).varInit = varInit();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).varInit.node; 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(115);
				((SentenceContext)_localctx).hazDoStmt = hazDoStmt();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).hazDoStmt.node; 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(118);
				((SentenceContext)_localctx).siStmt = siStmt();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).siStmt.node; 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(121);
				((SentenceContext)_localctx).hastaStmt = hastaStmt();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).hastaStmt.node; 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(124);
				((SentenceContext)_localctx).mientrasStmt = mientrasStmt();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).mientrasStmt.node; 
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(127);
				((SentenceContext)_localctx).repiteBlock = repiteBlock();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).repiteBlock.node; 
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(130);
				((SentenceContext)_localctx).execBlock = execBlock();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).execBlock.node; 
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(133);
				((SentenceContext)_localctx).turtleCmd = turtleCmd();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).turtleCmd.node; 
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(136);
				((SentenceContext)_localctx).callProc = callProc();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).callProc.node; 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDeclContext extends ParserRuleContext {
		public StmtNode node;
		public Token name;
		public LiteralOrStringContext value;
		public TerminalNode HAZ() { return getToken(LogoTecParser.HAZ, 0); }
		public TerminalNode ID() { return getToken(LogoTecParser.ID, 0); }
		public LiteralOrStringContext literalOrString() {
			return getRuleContext(LiteralOrStringContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(LogoTecParser.SEMICOLON, 0); }
		public VarDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterVarDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitVarDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogoTecVisitor ) return ((LogoTecVisitor<? extends T>)visitor).visitVarDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclContext varDecl() throws RecognitionException {
		VarDeclContext _localctx = new VarDeclContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_varDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			match(HAZ);
			setState(142);
			((VarDeclContext)_localctx).name = match(ID);
			setState(143);
			((VarDeclContext)_localctx).value = literalOrString();
			setState(145);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(144);
				match(SEMICOLON);
				}
			}


			        validateIdentifierLength((((VarDeclContext)_localctx).name!=null?((VarDeclContext)_localctx).name.getText():null), (((VarDeclContext)_localctx).name!=null?((VarDeclContext)_localctx).name.getLine():0));
			        declareOrAssign((((VarDeclContext)_localctx).name!=null?((VarDeclContext)_localctx).name.getText():null), ValueType.infer(((VarDeclContext)_localctx).value.node), ((VarDeclContext)_localctx).value.jval);
			        ((VarDeclContext)_localctx).node =  new VarDeclNode((((VarDeclContext)_localctx).name!=null?((VarDeclContext)_localctx).name.getText():null), ((VarDeclContext)_localctx).value.node);
			      
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarInitContext extends ParserRuleContext {
		public StmtNode node;
		public Token name;
		public ExpressionContext expression;
		public TerminalNode INIC() { return getToken(LogoTecParser.INIC, 0); }
		public TerminalNode ASSIGN() { return getToken(LogoTecParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(LogoTecParser.SEMICOLON, 0); }
		public TerminalNode ID() { return getToken(LogoTecParser.ID, 0); }
		public VarInitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varInit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterVarInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitVarInit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogoTecVisitor ) return ((LogoTecVisitor<? extends T>)visitor).visitVarInit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarInitContext varInit() throws RecognitionException {
		VarInitContext _localctx = new VarInitContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_varInit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			match(INIC);
			setState(150);
			((VarInitContext)_localctx).name = match(ID);
			setState(151);
			match(ASSIGN);
			setState(152);
			((VarInitContext)_localctx).expression = expression();
			setState(153);
			match(SEMICOLON);

			        validateIdentifierLength((((VarInitContext)_localctx).name!=null?((VarInitContext)_localctx).name.getText():null), (((VarInitContext)_localctx).name!=null?((VarInitContext)_localctx).name.getLine():0));
			        ValueType t = ValueType.infer(((VarInitContext)_localctx).expression.node, symbols);
			        assignInitOnlyIfDeclared((((VarInitContext)_localctx).name!=null?((VarInitContext)_localctx).name.getText():null), t, ((VarInitContext)_localctx).expression.node);
			        ((VarInitContext)_localctx).node =  new VarAssignNode((((VarInitContext)_localctx).name!=null?((VarInitContext)_localctx).name.getText():null), ((VarInitContext)_localctx).expression.node);
			      
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CallProcContext extends ParserRuleContext {
		public StmtNode node;
		public Token proc;
		public ExpressionContext expression;
		public PrimaryArgContext primaryArg;
		public TerminalNode ID() { return getToken(LogoTecParser.ID, 0); }
		public TerminalNode BRACKET_OPEN() { return getToken(LogoTecParser.BRACKET_OPEN, 0); }
		public TerminalNode BRACKET_CLOSE() { return getToken(LogoTecParser.BRACKET_CLOSE, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<PrimaryArgContext> primaryArg() {
			return getRuleContexts(PrimaryArgContext.class);
		}
		public PrimaryArgContext primaryArg(int i) {
			return getRuleContext(PrimaryArgContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LogoTecParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LogoTecParser.COMMA, i);
		}
		public CallProcContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callProc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterCallProc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitCallProc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogoTecVisitor ) return ((LogoTecVisitor<? extends T>)visitor).visitCallProc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallProcContext callProc() throws RecognitionException {
		CallProcContext _localctx = new CallProcContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_callProc);
		 List<ExprNode> args = new ArrayList<>(); 
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			((CallProcContext)_localctx).proc = match(ID);

			        validateIdentifierLength((((CallProcContext)_localctx).proc!=null?((CallProcContext)_localctx).proc.getText():null), (((CallProcContext)_localctx).proc!=null?((CallProcContext)_localctx).proc.getLine():0));
			      
			setState(183);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(158);
				match(BRACKET_OPEN);
				setState(172);
				_la = _input.LA(1);
				if (((((_la - 39)) & ~0x3f) == 0 && ((1L << (_la - 39)) & ((1L << (IGUALESQ - 39)) | (1L << (YFUNC - 39)) | (1L << (OFUNC - 39)) | (1L << (MAYORQ - 39)) | (1L << (MENORQ - 39)) | (1L << (DIFERENCIA - 39)) | (1L << (AZAR - 39)) | (1L << (PRODUCTO - 39)) | (1L << (POTENCIA - 39)) | (1L << (DIVISION - 39)) | (1L << (SUMA - 39)) | (1L << (PLUS - 39)) | (1L << (MINUS - 39)) | (1L << (NOT - 39)) | (1L << (PAR_OPEN - 39)) | (1L << (BOOLEAN - 39)) | (1L << (DECIMAL - 39)) | (1L << (NUMBER - 39)) | (1L << (STRING - 39)) | (1L << (ID - 39)))) != 0)) {
					{
					setState(159);
					((CallProcContext)_localctx).expression = expression();
					 args.add(((CallProcContext)_localctx).expression.node); 
					setState(169);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (((((_la - 39)) & ~0x3f) == 0 && ((1L << (_la - 39)) & ((1L << (IGUALESQ - 39)) | (1L << (YFUNC - 39)) | (1L << (OFUNC - 39)) | (1L << (MAYORQ - 39)) | (1L << (MENORQ - 39)) | (1L << (DIFERENCIA - 39)) | (1L << (AZAR - 39)) | (1L << (PRODUCTO - 39)) | (1L << (POTENCIA - 39)) | (1L << (DIVISION - 39)) | (1L << (SUMA - 39)) | (1L << (PLUS - 39)) | (1L << (MINUS - 39)) | (1L << (NOT - 39)) | (1L << (PAR_OPEN - 39)) | (1L << (COMMA - 39)) | (1L << (BOOLEAN - 39)) | (1L << (DECIMAL - 39)) | (1L << (NUMBER - 39)) | (1L << (STRING - 39)) | (1L << (ID - 39)))) != 0)) {
						{
						{
						setState(162);
						_la = _input.LA(1);
						if (_la==COMMA) {
							{
							setState(161);
							match(COMMA);
							}
						}

						setState(164);
						((CallProcContext)_localctx).expression = expression();
						 args.add(((CallProcContext)_localctx).expression.node); 
						}
						}
						setState(171);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(174);
				match(BRACKET_CLOSE);
				}
				break;
			case 2:
				{
				setState(175);
				if (!( _input.LA(1) != BRACKET_OPEN && 
				          (_input.LA(1) == NUMBER || _input.LA(1) == ID || _input.LA(1) == PAR_OPEN) )) throw new FailedPredicateException(this, " _input.LA(1) != BRACKET_OPEN && \r\n          (_input.LA(1) == NUMBER || _input.LA(1) == ID || _input.LA(1) == PAR_OPEN) ");
				setState(179); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(176);
						((CallProcContext)_localctx).primaryArg = primaryArg();
						 args.add(((CallProcContext)_localctx).primaryArg.node); 
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(181); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			}

			        validateProcedureCall((((CallProcContext)_localctx).proc!=null?((CallProcContext)_localctx).proc.getText():null), args.size());
			        ((CallProcContext)_localctx).node =  new ProcCallNode((((CallProcContext)_localctx).proc!=null?((CallProcContext)_localctx).proc.getText():null), args);
			      
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimaryArgContext extends ParserRuleContext {
		public ExprNode node;
		public Token NUMBER;
		public Token ID;
		public ExpressionContext expression;
		public TerminalNode NUMBER() { return getToken(LogoTecParser.NUMBER, 0); }
		public TerminalNode ID() { return getToken(LogoTecParser.ID, 0); }
		public TerminalNode PAR_OPEN() { return getToken(LogoTecParser.PAR_OPEN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PAR_CLOSE() { return getToken(LogoTecParser.PAR_CLOSE, 0); }
		public PrimaryArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryArg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterPrimaryArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitPrimaryArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogoTecVisitor ) return ((LogoTecVisitor<? extends T>)visitor).visitPrimaryArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryArgContext primaryArg() throws RecognitionException {
		PrimaryArgContext _localctx = new PrimaryArgContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_primaryArg);
		try {
			setState(196);
			switch (_input.LA(1)) {
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(187);
				((PrimaryArgContext)_localctx).NUMBER = match(NUMBER);
				 ((PrimaryArgContext)_localctx).node =  new ConstNode(Integer.parseInt((((PrimaryArgContext)_localctx).NUMBER!=null?((PrimaryArgContext)_localctx).NUMBER.getText():null))); 
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(189);
				((PrimaryArgContext)_localctx).ID = match(ID);
				 ((PrimaryArgContext)_localctx).node =  new VarRefNode((((PrimaryArgContext)_localctx).ID!=null?((PrimaryArgContext)_localctx).ID.getText():null)); 
				}
				break;
			case PAR_OPEN:
				enterOuterAlt(_localctx, 3);
				{
				setState(191);
				match(PAR_OPEN);
				setState(192);
				((PrimaryArgContext)_localctx).expression = expression();
				setState(193);
				match(PAR_CLOSE);
				 ((PrimaryArgContext)_localctx).node =  ((PrimaryArgContext)_localctx).expression.node; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionSeriesContext extends ParserRuleContext {
		public List<ExprNode> list;
		public ExpressionContext expression;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LogoTecParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LogoTecParser.COMMA, i);
		}
		public ExpressionSeriesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionSeries; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterExpressionSeries(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitExpressionSeries(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogoTecVisitor ) return ((LogoTecVisitor<? extends T>)visitor).visitExpressionSeries(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionSeriesContext expressionSeries() throws RecognitionException {
		ExpressionSeriesContext _localctx = new ExpressionSeriesContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_expressionSeries);
		 ((ExpressionSeriesContext)_localctx).list =  new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
			_la = _input.LA(1);
			if (((((_la - 39)) & ~0x3f) == 0 && ((1L << (_la - 39)) & ((1L << (IGUALESQ - 39)) | (1L << (YFUNC - 39)) | (1L << (OFUNC - 39)) | (1L << (MAYORQ - 39)) | (1L << (MENORQ - 39)) | (1L << (DIFERENCIA - 39)) | (1L << (AZAR - 39)) | (1L << (PRODUCTO - 39)) | (1L << (POTENCIA - 39)) | (1L << (DIVISION - 39)) | (1L << (SUMA - 39)) | (1L << (PLUS - 39)) | (1L << (MINUS - 39)) | (1L << (NOT - 39)) | (1L << (PAR_OPEN - 39)) | (1L << (BOOLEAN - 39)) | (1L << (DECIMAL - 39)) | (1L << (NUMBER - 39)) | (1L << (STRING - 39)) | (1L << (ID - 39)))) != 0)) {
				{
				setState(198);
				((ExpressionSeriesContext)_localctx).expression = expression();
				 _localctx.list.add(((ExpressionSeriesContext)_localctx).expression.node); 
				setState(208);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 39)) & ~0x3f) == 0 && ((1L << (_la - 39)) & ((1L << (IGUALESQ - 39)) | (1L << (YFUNC - 39)) | (1L << (OFUNC - 39)) | (1L << (MAYORQ - 39)) | (1L << (MENORQ - 39)) | (1L << (DIFERENCIA - 39)) | (1L << (AZAR - 39)) | (1L << (PRODUCTO - 39)) | (1L << (POTENCIA - 39)) | (1L << (DIVISION - 39)) | (1L << (SUMA - 39)) | (1L << (PLUS - 39)) | (1L << (MINUS - 39)) | (1L << (NOT - 39)) | (1L << (PAR_OPEN - 39)) | (1L << (COMMA - 39)) | (1L << (BOOLEAN - 39)) | (1L << (DECIMAL - 39)) | (1L << (NUMBER - 39)) | (1L << (STRING - 39)) | (1L << (ID - 39)))) != 0)) {
					{
					{
					setState(201);
					_la = _input.LA(1);
					if (_la==COMMA) {
						{
						setState(200);
						match(COMMA);
						}
					}

					setState(203);
					((ExpressionSeriesContext)_localctx).expression = expression();
					 _localctx.list.add(((ExpressionSeriesContext)_localctx).expression.node); 
					}
					}
					setState(210);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExecBlockContext extends ParserRuleContext {
		public StmtNode node;
		public SentenceContext sentence;
		public TerminalNode EJECUTA() { return getToken(LogoTecParser.EJECUTA, 0); }
		public TerminalNode BRACKET_OPEN() { return getToken(LogoTecParser.BRACKET_OPEN, 0); }
		public TerminalNode BRACKET_CLOSE() { return getToken(LogoTecParser.BRACKET_CLOSE, 0); }
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public ExecBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_execBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterExecBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitExecBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogoTecVisitor ) return ((LogoTecVisitor<? extends T>)visitor).visitExecBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExecBlockContext execBlock() throws RecognitionException {
		ExecBlockContext _localctx = new ExecBlockContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_execBlock);
		 List<StmtNode> body = new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(213);
			match(EJECUTA);
			setState(214);
			match(BRACKET_OPEN);
			setState(220);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONCOLORLAPIZ) | (1L << PONCL))) != 0) || _la==ID) {
				{
				{
				setState(215);
				((ExecBlockContext)_localctx).sentence = sentence();
				body.add(((ExecBlockContext)_localctx).sentence.node);
				}
				}
				setState(222);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(223);
			match(BRACKET_CLOSE);
			 ((ExecBlockContext)_localctx).node =  new ExecBlockNode(body); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RepiteBlockContext extends ParserRuleContext {
		public StmtNode node;
		public ExpressionContext times;
		public SentenceContext sentence;
		public TerminalNode REPITE() { return getToken(LogoTecParser.REPITE, 0); }
		public TerminalNode BRACKET_OPEN() { return getToken(LogoTecParser.BRACKET_OPEN, 0); }
		public TerminalNode BRACKET_CLOSE() { return getToken(LogoTecParser.BRACKET_CLOSE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public RepiteBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repiteBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterRepiteBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitRepiteBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogoTecVisitor ) return ((LogoTecVisitor<? extends T>)visitor).visitRepiteBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RepiteBlockContext repiteBlock() throws RecognitionException {
		RepiteBlockContext _localctx = new RepiteBlockContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_repiteBlock);
		 List<StmtNode> body = new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
			match(REPITE);
			setState(227);
			((RepiteBlockContext)_localctx).times = expression();

			        requireNumericType(((RepiteBlockContext)_localctx).times.node, "REPITE");
			      
			setState(229);
			match(BRACKET_OPEN);
			setState(235);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONCOLORLAPIZ) | (1L << PONCL))) != 0) || _la==ID) {
				{
				{
				setState(230);
				((RepiteBlockContext)_localctx).sentence = sentence();
				body.add(((RepiteBlockContext)_localctx).sentence.node);
				}
				}
				setState(237);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(238);
			match(BRACKET_CLOSE);
			 ((RepiteBlockContext)_localctx).node =  new RepeatNode(((RepiteBlockContext)_localctx).times.node, body); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SiStmtContext extends ParserRuleContext {
		public StmtNode node;
		public ExpressionContext cond;
		public SentenceContext sentence;
		public TerminalNode SI() { return getToken(LogoTecParser.SI, 0); }
		public TerminalNode PAR_OPEN() { return getToken(LogoTecParser.PAR_OPEN, 0); }
		public TerminalNode PAR_CLOSE() { return getToken(LogoTecParser.PAR_CLOSE, 0); }
		public List<TerminalNode> BRACKET_OPEN() { return getTokens(LogoTecParser.BRACKET_OPEN); }
		public TerminalNode BRACKET_OPEN(int i) {
			return getToken(LogoTecParser.BRACKET_OPEN, i);
		}
		public List<TerminalNode> BRACKET_CLOSE() { return getTokens(LogoTecParser.BRACKET_CLOSE); }
		public TerminalNode BRACKET_CLOSE(int i) {
			return getToken(LogoTecParser.BRACKET_CLOSE, i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public SiStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_siStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterSiStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitSiStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogoTecVisitor ) return ((LogoTecVisitor<? extends T>)visitor).visitSiStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SiStmtContext siStmt() throws RecognitionException {
		SiStmtContext _localctx = new SiStmtContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_siStmt);
		 
		        List<StmtNode> thenBody = new ArrayList<>(); 
		        List<StmtNode> elseBody = new ArrayList<>();
		        int elseCount = 0;
		    
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(241);
			match(SI);
			setState(242);
			match(PAR_OPEN);
			setState(243);
			((SiStmtContext)_localctx).cond = expression();
			setState(244);
			match(PAR_CLOSE);

			        requireBooleanType(((SiStmtContext)_localctx).cond.node, "condición de SI");
			      
			setState(246);
			match(BRACKET_OPEN);
			setState(252);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONCOLORLAPIZ) | (1L << PONCL))) != 0) || _la==ID) {
				{
				{
				setState(247);
				((SiStmtContext)_localctx).sentence = sentence();
				thenBody.add(((SiStmtContext)_localctx).sentence.node);
				}
				}
				setState(254);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(255);
			match(BRACKET_CLOSE);
			setState(269);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BRACKET_OPEN) {
				{
				{
				setState(256);
				match(BRACKET_OPEN);
				 elseCount++; 
				setState(263);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONCOLORLAPIZ) | (1L << PONCL))) != 0) || _la==ID) {
					{
					{
					setState(258);
					((SiStmtContext)_localctx).sentence = sentence();
					elseBody.add(((SiStmtContext)_localctx).sentence.node);
					}
					}
					setState(265);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(266);
				match(BRACKET_CLOSE);
				}
				}
				setState(271);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}

			        validateSingleElse(elseCount, "SI");
			        ((SiStmtContext)_localctx).node =  new IfNode(((SiStmtContext)_localctx).cond.node, thenBody, elseBody.isEmpty()? null : elseBody);
			      
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HazDoStmtContext extends ParserRuleContext {
		public StmtNode node;
		public SentenceContext sentence;
		public ExpressionContext cond;
		public TerminalNode HAZ() { return getToken(LogoTecParser.HAZ, 0); }
		public TerminalNode DOT() { return getToken(LogoTecParser.DOT, 0); }
		public TerminalNode BRACKET_OPEN() { return getToken(LogoTecParser.BRACKET_OPEN, 0); }
		public TerminalNode BRACKET_CLOSE() { return getToken(LogoTecParser.BRACKET_CLOSE, 0); }
		public TerminalNode HASTA() { return getToken(LogoTecParser.HASTA, 0); }
		public TerminalNode PAR_OPEN() { return getToken(LogoTecParser.PAR_OPEN, 0); }
		public TerminalNode PAR_CLOSE() { return getToken(LogoTecParser.PAR_CLOSE, 0); }
		public TerminalNode MIENTRAS() { return getToken(LogoTecParser.MIENTRAS, 0); }
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public HazDoStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hazDoStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterHazDoStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitHazDoStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogoTecVisitor ) return ((LogoTecVisitor<? extends T>)visitor).visitHazDoStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HazDoStmtContext hazDoStmt() throws RecognitionException {
		HazDoStmtContext _localctx = new HazDoStmtContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_hazDoStmt);
		 
		        List<StmtNode> body = new ArrayList<>(); 
		        ExprNode condition = null;
		        boolean isUntil = false;
		        boolean isWhile = false;
		    
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(274);
			match(HAZ);
			setState(275);
			match(DOT);
			setState(276);
			match(BRACKET_OPEN);
			setState(282);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONCOLORLAPIZ) | (1L << PONCL))) != 0) || _la==ID) {
				{
				{
				setState(277);
				((HazDoStmtContext)_localctx).sentence = sentence();
				body.add(((HazDoStmtContext)_localctx).sentence.node);
				}
				}
				setState(284);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(285);
			match(BRACKET_CLOSE);
			setState(298);
			switch (_input.LA(1)) {
			case HASTA:
				{
				setState(286);
				match(HASTA);
				setState(287);
				match(PAR_OPEN);
				setState(288);
				((HazDoStmtContext)_localctx).cond = expression();
				setState(289);
				match(PAR_CLOSE);
				 
				          condition = ((HazDoStmtContext)_localctx).cond.node; 
				          isUntil = true; 
				        
				}
				break;
			case MIENTRAS:
				{
				setState(292);
				match(MIENTRAS);
				setState(293);
				match(PAR_OPEN);
				setState(294);
				((HazDoStmtContext)_localctx).cond = expression();
				setState(295);
				match(PAR_CLOSE);
				 
				          condition = ((HazDoStmtContext)_localctx).cond.node; 
				          isWhile = true; 
				        
				}
				break;
			default:
				throw new NoViableAltException(this);
			}

			        if (condition == null) {
			            errors.add("Error en línea " + currentLine() + 
			                       ": HAZ requiere HASTA o MIENTRAS con condición.");
			        } else {
			            requireNonEmptyBlock(body, isUntil ? "HAZ.HASTA" : "HAZ.MIENTRAS");
			            requireBooleanType(condition, isUntil ? "condición de HAZ.HASTA" : "condición de HAZ.MIENTRAS");
			            
			            if (isUntil) {
			                ((HazDoStmtContext)_localctx).node =  new DoUntilNode(body, condition);
			            } else {
			                ((HazDoStmtContext)_localctx).node =  new DoWhileNode(body, condition);
			            }
			        }
			      
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MientrasStmtContext extends ParserRuleContext {
		public StmtNode node;
		public ExpressionContext cond;
		public SentenceContext sentence;
		public TerminalNode MIENTRAS() { return getToken(LogoTecParser.MIENTRAS, 0); }
		public TerminalNode PAR_OPEN() { return getToken(LogoTecParser.PAR_OPEN, 0); }
		public TerminalNode PAR_CLOSE() { return getToken(LogoTecParser.PAR_CLOSE, 0); }
		public TerminalNode BRACKET_OPEN() { return getToken(LogoTecParser.BRACKET_OPEN, 0); }
		public TerminalNode BRACKET_CLOSE() { return getToken(LogoTecParser.BRACKET_CLOSE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public MientrasStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mientrasStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterMientrasStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitMientrasStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogoTecVisitor ) return ((LogoTecVisitor<? extends T>)visitor).visitMientrasStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MientrasStmtContext mientrasStmt() throws RecognitionException {
		MientrasStmtContext _localctx = new MientrasStmtContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_mientrasStmt);
		 List<StmtNode> body = new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(302);
			match(MIENTRAS);
			setState(303);
			match(PAR_OPEN);
			setState(304);
			((MientrasStmtContext)_localctx).cond = expression();
			setState(305);
			match(PAR_CLOSE);

			        requireBooleanType(((MientrasStmtContext)_localctx).cond.node, "condición de MIENTRAS");
			      
			setState(307);
			match(BRACKET_OPEN);
			setState(313);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONCOLORLAPIZ) | (1L << PONCL))) != 0) || _la==ID) {
				{
				{
				setState(308);
				((MientrasStmtContext)_localctx).sentence = sentence();
				body.add(((MientrasStmtContext)_localctx).sentence.node);
				}
				}
				setState(315);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(316);
			match(BRACKET_CLOSE);

			        requireNonEmptyBlock(body, "MIENTRAS");
			        ((MientrasStmtContext)_localctx).node =  new WhileNode(((MientrasStmtContext)_localctx).cond.node, body);
			      
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HastaStmtContext extends ParserRuleContext {
		public StmtNode node;
		public ExpressionContext cond;
		public SentenceContext sentence;
		public TerminalNode HASTA() { return getToken(LogoTecParser.HASTA, 0); }
		public TerminalNode PAR_OPEN() { return getToken(LogoTecParser.PAR_OPEN, 0); }
		public TerminalNode PAR_CLOSE() { return getToken(LogoTecParser.PAR_CLOSE, 0); }
		public TerminalNode BRACKET_OPEN() { return getToken(LogoTecParser.BRACKET_OPEN, 0); }
		public TerminalNode BRACKET_CLOSE() { return getToken(LogoTecParser.BRACKET_CLOSE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public HastaStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hastaStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterHastaStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitHastaStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogoTecVisitor ) return ((LogoTecVisitor<? extends T>)visitor).visitHastaStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HastaStmtContext hastaStmt() throws RecognitionException {
		HastaStmtContext _localctx = new HastaStmtContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_hastaStmt);
		 List<StmtNode> body = new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(319);
			match(HASTA);
			setState(320);
			match(PAR_OPEN);
			setState(321);
			((HastaStmtContext)_localctx).cond = expression();
			setState(322);
			match(PAR_CLOSE);

			        requireBooleanType(((HastaStmtContext)_localctx).cond.node, "condición de HASTA");
			      
			setState(324);
			match(BRACKET_OPEN);
			setState(330);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONCOLORLAPIZ) | (1L << PONCL))) != 0) || _la==ID) {
				{
				{
				setState(325);
				((HastaStmtContext)_localctx).sentence = sentence();
				body.add(((HastaStmtContext)_localctx).sentence.node);
				}
				}
				setState(332);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(333);
			match(BRACKET_CLOSE);

			        requireNonEmptyBlock(body, "HASTA");
			        ((HastaStmtContext)_localctx).node =  new UntilNode(((HastaStmtContext)_localctx).cond.node, body);
			      
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TurtleCmdContext extends ParserRuleContext {
		public StmtNode node;
		public ExpressionContext e;
		public ExpressionSeriesContext coords;
		public ExpressionContext x;
		public ExpressionContext y;
		public Token id;
		public ExpressionContext n;
		public ColorNameContext c;
		public TerminalNode AVANZA() { return getToken(LogoTecParser.AVANZA, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode AV() { return getToken(LogoTecParser.AV, 0); }
		public TerminalNode RETROCEDE() { return getToken(LogoTecParser.RETROCEDE, 0); }
		public TerminalNode RE() { return getToken(LogoTecParser.RE, 0); }
		public TerminalNode GIRADERECHA() { return getToken(LogoTecParser.GIRADERECHA, 0); }
		public TerminalNode GD() { return getToken(LogoTecParser.GD, 0); }
		public TerminalNode GIRAIZQUIERDA() { return getToken(LogoTecParser.GIRAIZQUIERDA, 0); }
		public TerminalNode GI() { return getToken(LogoTecParser.GI, 0); }
		public TerminalNode OCULTATORTUGA() { return getToken(LogoTecParser.OCULTATORTUGA, 0); }
		public TerminalNode OT() { return getToken(LogoTecParser.OT, 0); }
		public TerminalNode APARECETORTUGA() { return getToken(LogoTecParser.APARECETORTUGA, 0); }
		public TerminalNode AT() { return getToken(LogoTecParser.AT, 0); }
		public TerminalNode PONPOS() { return getToken(LogoTecParser.PONPOS, 0); }
		public TerminalNode BRACKET_OPEN() { return getToken(LogoTecParser.BRACKET_OPEN, 0); }
		public TerminalNode BRACKET_CLOSE() { return getToken(LogoTecParser.BRACKET_CLOSE, 0); }
		public ExpressionSeriesContext expressionSeries() {
			return getRuleContext(ExpressionSeriesContext.class,0);
		}
		public TerminalNode PONXY() { return getToken(LogoTecParser.PONXY, 0); }
		public TerminalNode PONRUMBO() { return getToken(LogoTecParser.PONRUMBO, 0); }
		public TerminalNode RUMBO() { return getToken(LogoTecParser.RUMBO, 0); }
		public TerminalNode PONX() { return getToken(LogoTecParser.PONX, 0); }
		public TerminalNode PONY() { return getToken(LogoTecParser.PONY, 0); }
		public TerminalNode BAJALAPIZ() { return getToken(LogoTecParser.BAJALAPIZ, 0); }
		public TerminalNode BL() { return getToken(LogoTecParser.BL, 0); }
		public TerminalNode SUBELAPIZ() { return getToken(LogoTecParser.SUBELAPIZ, 0); }
		public TerminalNode SB() { return getToken(LogoTecParser.SB, 0); }
		public TerminalNode CENTRO() { return getToken(LogoTecParser.CENTRO, 0); }
		public TerminalNode ESPERA() { return getToken(LogoTecParser.ESPERA, 0); }
		public TerminalNode INC() { return getToken(LogoTecParser.INC, 0); }
		public TerminalNode ID() { return getToken(LogoTecParser.ID, 0); }
		public TerminalNode PONCOLORLAPIZ() { return getToken(LogoTecParser.PONCOLORLAPIZ, 0); }
		public TerminalNode PONCL() { return getToken(LogoTecParser.PONCL, 0); }
		public ColorNameContext colorName() {
			return getRuleContext(ColorNameContext.class,0);
		}
		public TurtleCmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_turtleCmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterTurtleCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitTurtleCmd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogoTecVisitor ) return ((LogoTecVisitor<? extends T>)visitor).visitTurtleCmd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TurtleCmdContext turtleCmd() throws RecognitionException {
		TurtleCmdContext _localctx = new TurtleCmdContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_turtleCmd);
		try {
			setState(447);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(336);
				match(AVANZA);
				setState(337);
				((TurtleCmdContext)_localctx).e = expression();
				 
				        requireNumericType(((TurtleCmdContext)_localctx).e.node, "AVANZA");
				        ((TurtleCmdContext)_localctx).node =  new ForwardNode(((TurtleCmdContext)_localctx).e.node); 
				      
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(340);
				match(AV);
				setState(341);
				((TurtleCmdContext)_localctx).e = expression();
				 
				        requireNumericType(((TurtleCmdContext)_localctx).e.node, "AV");
				        ((TurtleCmdContext)_localctx).node =  new ForwardNode(((TurtleCmdContext)_localctx).e.node); 
				      
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(344);
				match(RETROCEDE);
				setState(345);
				((TurtleCmdContext)_localctx).e = expression();
				 
				        requireNumericType(((TurtleCmdContext)_localctx).e.node, "RETROCEDE");
				        ((TurtleCmdContext)_localctx).node =  new BackwardNode(((TurtleCmdContext)_localctx).e.node); 
				      
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(348);
				match(RE);
				setState(349);
				((TurtleCmdContext)_localctx).e = expression();
				 
				        requireNumericType(((TurtleCmdContext)_localctx).e.node, "RE");
				        ((TurtleCmdContext)_localctx).node =  new BackwardNode(((TurtleCmdContext)_localctx).e.node); 
				      
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(352);
				match(GIRADERECHA);
				setState(353);
				((TurtleCmdContext)_localctx).e = expression();
				 
				        requireNumericType(((TurtleCmdContext)_localctx).e.node, "GIRADERECHA");
				        ((TurtleCmdContext)_localctx).node =  new TurnRightNode(((TurtleCmdContext)_localctx).e.node); 
				      
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(356);
				match(GD);
				setState(357);
				((TurtleCmdContext)_localctx).e = expression();
				 
				        requireNumericType(((TurtleCmdContext)_localctx).e.node, "GD");
				        ((TurtleCmdContext)_localctx).node =  new TurnRightNode(((TurtleCmdContext)_localctx).e.node); 
				      
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(360);
				match(GIRAIZQUIERDA);
				setState(361);
				((TurtleCmdContext)_localctx).e = expression();
				 
				        requireNumericType(((TurtleCmdContext)_localctx).e.node, "GIRAIZQUIERDA");
				        ((TurtleCmdContext)_localctx).node =  new TurnLeftNode(((TurtleCmdContext)_localctx).e.node); 
				      
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(364);
				match(GI);
				setState(365);
				((TurtleCmdContext)_localctx).e = expression();
				 
				        requireNumericType(((TurtleCmdContext)_localctx).e.node, "GI");
				        ((TurtleCmdContext)_localctx).node =  new TurnLeftNode(((TurtleCmdContext)_localctx).e.node); 
				      
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(368);
				match(OCULTATORTUGA);
				 ((TurtleCmdContext)_localctx).node =  new HideTurtleNode(); 
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(370);
				match(OT);
				 ((TurtleCmdContext)_localctx).node =  new HideTurtleNode(); 
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(372);
				match(APARECETORTUGA);
				 ((TurtleCmdContext)_localctx).node =  new ShowTurtleNode(); 
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(374);
				match(AT);
				 ((TurtleCmdContext)_localctx).node =  new ShowTurtleNode(); 
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(376);
				match(PONPOS);
				setState(377);
				match(BRACKET_OPEN);
				setState(378);
				((TurtleCmdContext)_localctx).coords = expressionSeries();
				setState(379);
				match(BRACKET_CLOSE);

				        List<ExprNode> coordsList = ((TurtleCmdContext)_localctx).coords.list;
				        if (coordsList.size() != 2) {
				            errors.add("Error en línea " + currentLine() + ": 'PONPOS' requiere exactamente dos expresiones para X e Y.");
				        }
				        ExprNode xNode = coordsList.size() > 0 ? coordsList.get(0) : new ConstNode(0);
				        ExprNode yNode = coordsList.size() > 1 ? coordsList.get(1) : new ConstNode(0);
				        requireNumericType(xNode, "PONPOS (coordenada X)");
				        requireNumericType(yNode, "PONPOS (coordenada Y)");
				        ((TurtleCmdContext)_localctx).node =  new SetPosNode(xNode, yNode, true);
				      
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(382);
				match(PONXY);
				setState(383);
				((TurtleCmdContext)_localctx).x = expression();
				setState(384);
				((TurtleCmdContext)_localctx).y = expression();
				 
				        requireNumericType(((TurtleCmdContext)_localctx).x.node, "PONXY (coordenada X)");
				        requireNumericType(((TurtleCmdContext)_localctx).y.node, "PONXY (coordenada Y)");
				        ((TurtleCmdContext)_localctx).node =  new SetPosNode(((TurtleCmdContext)_localctx).x.node, ((TurtleCmdContext)_localctx).y.node, false); 
				      
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(387);
				match(PONRUMBO);
				setState(388);
				((TurtleCmdContext)_localctx).e = expression();
				 
				        requireNumericType(((TurtleCmdContext)_localctx).e.node, "PONRUMBO");
				        ((TurtleCmdContext)_localctx).node =  new SetHeadingNode(((TurtleCmdContext)_localctx).e.node); 
				      
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(391);
				match(RUMBO);
				 ((TurtleCmdContext)_localctx).node =  new ShowHeadingNode(); 
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(393);
				match(PONX);
				setState(394);
				((TurtleCmdContext)_localctx).e = expression();
				 
				        requireNumericType(((TurtleCmdContext)_localctx).e.node, "PONX");
				        ((TurtleCmdContext)_localctx).node =  new SetXNode(((TurtleCmdContext)_localctx).e.node); 
				      
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(397);
				match(PONY);
				setState(398);
				((TurtleCmdContext)_localctx).e = expression();
				 
				        requireNumericType(((TurtleCmdContext)_localctx).e.node, "PONY");
				        ((TurtleCmdContext)_localctx).node =  new SetYNode(((TurtleCmdContext)_localctx).e.node); 
				      
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(401);
				match(BAJALAPIZ);
				 ((TurtleCmdContext)_localctx).node =  new PenDownNode(); 
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(403);
				match(BL);
				 ((TurtleCmdContext)_localctx).node =  new PenDownNode(); 
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 21);
				{
				setState(405);
				match(SUBELAPIZ);
				 ((TurtleCmdContext)_localctx).node =  new PenUpNode(); 
				}
				break;
			case 22:
				enterOuterAlt(_localctx, 22);
				{
				setState(407);
				match(SB);
				 ((TurtleCmdContext)_localctx).node =  new PenUpNode(); 
				}
				break;
			case 23:
				enterOuterAlt(_localctx, 23);
				{
				setState(409);
				match(CENTRO);
				 ((TurtleCmdContext)_localctx).node =  new CenterNode(); 
				}
				break;
			case 24:
				enterOuterAlt(_localctx, 24);
				{
				setState(411);
				match(ESPERA);
				setState(412);
				((TurtleCmdContext)_localctx).e = expression();
				 
				        requireNumericType(((TurtleCmdContext)_localctx).e.node, "ESPERA");
				        ((TurtleCmdContext)_localctx).node =  new WaitNode(((TurtleCmdContext)_localctx).e.node); 
				      
				}
				break;
			case 25:
				enterOuterAlt(_localctx, 25);
				{
				setState(415);
				match(INC);
				setState(416);
				match(BRACKET_OPEN);
				setState(417);
				((TurtleCmdContext)_localctx).id = match(ID);
				setState(418);
				match(BRACKET_CLOSE);
				 
				        validateIncrement((((TurtleCmdContext)_localctx).id!=null?((TurtleCmdContext)_localctx).id.getText():null), new ConstNode(1));
				        ((TurtleCmdContext)_localctx).node =  new IncNode(new VarRefNode((((TurtleCmdContext)_localctx).id!=null?((TurtleCmdContext)_localctx).id.getText():null)), new ConstNode(1)); 
				      
				}
				break;
			case 26:
				enterOuterAlt(_localctx, 26);
				{
				setState(420);
				match(INC);
				setState(421);
				match(BRACKET_OPEN);
				setState(422);
				((TurtleCmdContext)_localctx).id = match(ID);
				setState(423);
				((TurtleCmdContext)_localctx).n = expression();
				setState(424);
				match(BRACKET_CLOSE);
				 
				        validateIncrement((((TurtleCmdContext)_localctx).id!=null?((TurtleCmdContext)_localctx).id.getText():null), ((TurtleCmdContext)_localctx).n.node);
				        ((TurtleCmdContext)_localctx).node =  new IncNode(new VarRefNode((((TurtleCmdContext)_localctx).id!=null?((TurtleCmdContext)_localctx).id.getText():null)), ((TurtleCmdContext)_localctx).n.node); 
				      
				}
				break;
			case 27:
				enterOuterAlt(_localctx, 27);
				{
				setState(427);
				match(PONCOLORLAPIZ);
				setState(428);
				match(BRACKET_OPEN);
				setState(429);
				((TurtleCmdContext)_localctx).coords = expressionSeries();
				setState(430);
				match(BRACKET_CLOSE);

				        List<ExprNode> colorList = ((TurtleCmdContext)_localctx).coords.list;
				        if (colorList.size() != 3) {
				            errors.add("Error en línea " + currentLine() + ": 'PONCOLORLAPIZ' requiere exactamente tres valores RGB.");
				        }
				        ExprNode rNode = colorList.size() > 0 ? colorList.get(0) : new ConstNode(0);
				        ExprNode gNode = colorList.size() > 1 ? colorList.get(1) : new ConstNode(0);
				        ExprNode bNode = colorList.size() > 2 ? colorList.get(2) : new ConstNode(0);
				        requireNumericType(rNode, "PONCOLORLAPIZ (valor R)");
				        requireNumericType(gNode, "PONCOLORLAPIZ (valor G)");
				        requireNumericType(bNode, "PONCOLORLAPIZ (valor B)");
				        ((TurtleCmdContext)_localctx).node =  new SetColorNode(rNode, gNode, bNode);
				      
				}
				break;
			case 28:
				enterOuterAlt(_localctx, 28);
				{
				setState(433);
				match(PONCL);
				setState(434);
				match(BRACKET_OPEN);
				setState(435);
				((TurtleCmdContext)_localctx).coords = expressionSeries();
				setState(436);
				match(BRACKET_CLOSE);

				        List<ExprNode> colorList = ((TurtleCmdContext)_localctx).coords.list;
				        if (colorList.size() != 3) {
				            errors.add("Error en línea " + currentLine() + ": 'PONCL' requiere exactamente tres valores RGB.");
				        }
				        ExprNode rNode = colorList.size() > 0 ? colorList.get(0) : new ConstNode(0);
				        ExprNode gNode = colorList.size() > 1 ? colorList.get(1) : new ConstNode(0);
				        ExprNode bNode = colorList.size() > 2 ? colorList.get(2) : new ConstNode(0);
				        requireNumericType(rNode, "PONCL (valor R)");
				        requireNumericType(gNode, "PONCL (valor G)");
				        requireNumericType(bNode, "PONCL (valor B)");
				        ((TurtleCmdContext)_localctx).node =  new SetColorNode(rNode, gNode, bNode);
				      
				}
				break;
			case 29:
				enterOuterAlt(_localctx, 29);
				{
				setState(439);
				match(PONCOLORLAPIZ);
				setState(440);
				((TurtleCmdContext)_localctx).c = colorName();
				 ((TurtleCmdContext)_localctx).node =  new SetPenColorNode(((TurtleCmdContext)_localctx).c.value); 
				}
				break;
			case 30:
				enterOuterAlt(_localctx, 30);
				{
				setState(443);
				match(PONCL);
				setState(444);
				((TurtleCmdContext)_localctx).c = colorName();
				 ((TurtleCmdContext)_localctx).node =  new SetPenColorNode(((TurtleCmdContext)_localctx).c.value); 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ColorNameContext extends ParserRuleContext {
		public String value;
		public Token c;
		public Token id;
		public TerminalNode COLOR() { return getToken(LogoTecParser.COLOR, 0); }
		public TerminalNode ID() { return getToken(LogoTecParser.ID, 0); }
		public ColorNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_colorName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterColorName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitColorName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogoTecVisitor ) return ((LogoTecVisitor<? extends T>)visitor).visitColorName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColorNameContext colorName() throws RecognitionException {
		ColorNameContext _localctx = new ColorNameContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_colorName);
		try {
			setState(453);
			switch (_input.LA(1)) {
			case COLOR:
				enterOuterAlt(_localctx, 1);
				{
				setState(449);
				((ColorNameContext)_localctx).c = match(COLOR);
				 ((ColorNameContext)_localctx).value =  ((ColorNameContext)_localctx).c.getText().toLowerCase(); 
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(451);
				((ColorNameContext)_localctx).id = match(ID);
				 ((ColorNameContext)_localctx).value =  ((ColorNameContext)_localctx).id.getText().toLowerCase(); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExprNode node;
		public Value val;
		public LogicTermContext t1;
		public LogicTermContext t2;
		public List<LogicTermContext> logicTerm() {
			return getRuleContexts(LogicTermContext.class);
		}
		public LogicTermContext logicTerm(int i) {
			return getRuleContext(LogicTermContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(LogoTecParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(LogoTecParser.OR, i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogoTecVisitor ) return ((LogoTecVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(455);
			((ExpressionContext)_localctx).t1 = logicTerm();
			 ((ExpressionContext)_localctx).node =  ((ExpressionContext)_localctx).t1.node; ((ExpressionContext)_localctx).val =  ((ExpressionContext)_localctx).t1.val; 
			setState(463);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(457);
				match(OR);
				setState(458);
				((ExpressionContext)_localctx).t2 = logicTerm();
				 ((ExpressionContext)_localctx).node =  new LogicalOrNode(_localctx.node, ((ExpressionContext)_localctx).t2.node); 
				}
				}
				setState(465);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LogicTermContext extends ParserRuleContext {
		public ExprNode node;
		public Value val;
		public LogicFactorContext f1;
		public LogicFactorContext f2;
		public List<LogicFactorContext> logicFactor() {
			return getRuleContexts(LogicFactorContext.class);
		}
		public LogicFactorContext logicFactor(int i) {
			return getRuleContext(LogicFactorContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(LogoTecParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(LogoTecParser.AND, i);
		}
		public LogicTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterLogicTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitLogicTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogoTecVisitor ) return ((LogoTecVisitor<? extends T>)visitor).visitLogicTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicTermContext logicTerm() throws RecognitionException {
		LogicTermContext _localctx = new LogicTermContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_logicTerm);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(466);
			((LogicTermContext)_localctx).f1 = logicFactor();
			 ((LogicTermContext)_localctx).node =  ((LogicTermContext)_localctx).f1.node; ((LogicTermContext)_localctx).val =  ((LogicTermContext)_localctx).f1.val; 
			setState(474);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(468);
				match(AND);
				setState(469);
				((LogicTermContext)_localctx).f2 = logicFactor();
				 ((LogicTermContext)_localctx).node =  new LogicalAndNode(_localctx.node, ((LogicTermContext)_localctx).f2.node); 
				}
				}
				setState(476);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LogicFactorContext extends ParserRuleContext {
		public ExprNode node;
		public Value val;
		public LogicFactorContext lf;
		public RelationalContext r;
		public TerminalNode NOT() { return getToken(LogoTecParser.NOT, 0); }
		public LogicFactorContext logicFactor() {
			return getRuleContext(LogicFactorContext.class,0);
		}
		public RelationalContext relational() {
			return getRuleContext(RelationalContext.class,0);
		}
		public LogicFactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicFactor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterLogicFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitLogicFactor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogoTecVisitor ) return ((LogoTecVisitor<? extends T>)visitor).visitLogicFactor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicFactorContext logicFactor() throws RecognitionException {
		LogicFactorContext _localctx = new LogicFactorContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_logicFactor);
		try {
			setState(484);
			switch (_input.LA(1)) {
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(477);
				match(NOT);
				setState(478);
				((LogicFactorContext)_localctx).lf = logicFactor();
				 ((LogicFactorContext)_localctx).node =  new LogicalNotNode(((LogicFactorContext)_localctx).lf.node);
				}
				break;
			case IGUALESQ:
			case YFUNC:
			case OFUNC:
			case MAYORQ:
			case MENORQ:
			case DIFERENCIA:
			case AZAR:
			case PRODUCTO:
			case POTENCIA:
			case DIVISION:
			case SUMA:
			case PLUS:
			case MINUS:
			case PAR_OPEN:
			case BOOLEAN:
			case DECIMAL:
			case NUMBER:
			case STRING:
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(481);
				((LogicFactorContext)_localctx).r = relational();
				 ((LogicFactorContext)_localctx).node =  ((LogicFactorContext)_localctx).r.node; ((LogicFactorContext)_localctx).val =  ((LogicFactorContext)_localctx).r.val; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationalContext extends ParserRuleContext {
		public ExprNode node;
		public Value val;
		public ArithExprContext a1;
		public ArithExprContext a2;
		public List<ArithExprContext> arithExpr() {
			return getRuleContexts(ArithExprContext.class);
		}
		public ArithExprContext arithExpr(int i) {
			return getRuleContext(ArithExprContext.class,i);
		}
		public List<TerminalNode> GT() { return getTokens(LogoTecParser.GT); }
		public TerminalNode GT(int i) {
			return getToken(LogoTecParser.GT, i);
		}
		public List<TerminalNode> LT() { return getTokens(LogoTecParser.LT); }
		public TerminalNode LT(int i) {
			return getToken(LogoTecParser.LT, i);
		}
		public List<TerminalNode> GEQ() { return getTokens(LogoTecParser.GEQ); }
		public TerminalNode GEQ(int i) {
			return getToken(LogoTecParser.GEQ, i);
		}
		public List<TerminalNode> LEQ() { return getTokens(LogoTecParser.LEQ); }
		public TerminalNode LEQ(int i) {
			return getToken(LogoTecParser.LEQ, i);
		}
		public List<TerminalNode> EQ() { return getTokens(LogoTecParser.EQ); }
		public TerminalNode EQ(int i) {
			return getToken(LogoTecParser.EQ, i);
		}
		public List<TerminalNode> NEQ() { return getTokens(LogoTecParser.NEQ); }
		public TerminalNode NEQ(int i) {
			return getToken(LogoTecParser.NEQ, i);
		}
		public RelationalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relational; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterRelational(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitRelational(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogoTecVisitor ) return ((LogoTecVisitor<? extends T>)visitor).visitRelational(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationalContext relational() throws RecognitionException {
		RelationalContext _localctx = new RelationalContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_relational);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(486);
			((RelationalContext)_localctx).a1 = arithExpr();
			 ((RelationalContext)_localctx).node =  ((RelationalContext)_localctx).a1.node; ((RelationalContext)_localctx).val =  ((RelationalContext)_localctx).a1.val; 
			setState(514);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GT) | (1L << LT) | (1L << GEQ) | (1L << LEQ) | (1L << EQ) | (1L << NEQ))) != 0)) {
				{
				setState(512);
				switch (_input.LA(1)) {
				case GT:
					{
					setState(488);
					match(GT);
					setState(489);
					((RelationalContext)_localctx).a2 = arithExpr();
					 ((RelationalContext)_localctx).node =  new GreaterThanNode(_localctx.node, ((RelationalContext)_localctx).a2.node); 
					}
					break;
				case LT:
					{
					setState(492);
					match(LT);
					setState(493);
					((RelationalContext)_localctx).a2 = arithExpr();
					 ((RelationalContext)_localctx).node =  new LessThanNode(_localctx.node, ((RelationalContext)_localctx).a2.node); 
					}
					break;
				case GEQ:
					{
					setState(496);
					match(GEQ);
					setState(497);
					((RelationalContext)_localctx).a2 = arithExpr();
					 ((RelationalContext)_localctx).node =  new GreaterEqualNode(_localctx.node, ((RelationalContext)_localctx).a2.node); 
					}
					break;
				case LEQ:
					{
					setState(500);
					match(LEQ);
					setState(501);
					((RelationalContext)_localctx).a2 = arithExpr();
					 ((RelationalContext)_localctx).node =  new LessEqualNode(_localctx.node, ((RelationalContext)_localctx).a2.node); 
					}
					break;
				case EQ:
					{
					setState(504);
					match(EQ);
					setState(505);
					((RelationalContext)_localctx).a2 = arithExpr();
					 ((RelationalContext)_localctx).node =  new EqualsNode(_localctx.node, ((RelationalContext)_localctx).a2.node); 
					}
					break;
				case NEQ:
					{
					setState(508);
					match(NEQ);
					setState(509);
					((RelationalContext)_localctx).a2 = arithExpr();
					 ((RelationalContext)_localctx).node =  new NotEqualsNode(_localctx.node, ((RelationalContext)_localctx).a2.node); 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(516);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArithExprContext extends ParserRuleContext {
		public ExprNode node;
		public Value val;
		public TermContext t1;
		public TermContext t2;
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<TerminalNode> PLUS() { return getTokens(LogoTecParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(LogoTecParser.PLUS, i);
		}
		public List<TerminalNode> MINUS() { return getTokens(LogoTecParser.MINUS); }
		public TerminalNode MINUS(int i) {
			return getToken(LogoTecParser.MINUS, i);
		}
		public ArithExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterArithExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitArithExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogoTecVisitor ) return ((LogoTecVisitor<? extends T>)visitor).visitArithExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArithExprContext arithExpr() throws RecognitionException {
		ArithExprContext _localctx = new ArithExprContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_arithExpr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(517);
			((ArithExprContext)_localctx).t1 = term();
			 ((ArithExprContext)_localctx).node =  ((ArithExprContext)_localctx).t1.node; ((ArithExprContext)_localctx).val =  ((ArithExprContext)_localctx).t1.val; 
			setState(529);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(527);
					switch (_input.LA(1)) {
					case PLUS:
						{
						setState(519);
						match(PLUS);
						setState(520);
						((ArithExprContext)_localctx).t2 = term();
						 ((ArithExprContext)_localctx).node =  new AdditionNode(_localctx.node, ((ArithExprContext)_localctx).t2.node); 
						}
						break;
					case MINUS:
						{
						setState(523);
						match(MINUS);
						setState(524);
						((ArithExprContext)_localctx).t2 = term();
						 ((ArithExprContext)_localctx).node =  new SubtractionNode(_localctx.node, ((ArithExprContext)_localctx).t2.node);  
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(531);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public ExprNode node;
		public Value val;
		public FactorContext f1;
		public FactorContext f2;
		public List<FactorContext> factor() {
			return getRuleContexts(FactorContext.class);
		}
		public FactorContext factor(int i) {
			return getRuleContext(FactorContext.class,i);
		}
		public List<TerminalNode> MULT() { return getTokens(LogoTecParser.MULT); }
		public TerminalNode MULT(int i) {
			return getToken(LogoTecParser.MULT, i);
		}
		public List<TerminalNode> DIV() { return getTokens(LogoTecParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(LogoTecParser.DIV, i);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogoTecVisitor ) return ((LogoTecVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(532);
			((TermContext)_localctx).f1 = factor();
			 ((TermContext)_localctx).node =  ((TermContext)_localctx).f1.node; ((TermContext)_localctx).val =  ((TermContext)_localctx).f1.val; 
			setState(544);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==MULT || _la==DIV) {
				{
				setState(542);
				switch (_input.LA(1)) {
				case MULT:
					{
					setState(534);
					match(MULT);
					setState(535);
					((TermContext)_localctx).f2 = factor();
					 ((TermContext)_localctx).node =  new MultiplicationNode(_localctx.node, ((TermContext)_localctx).f2.node);  
					}
					break;
				case DIV:
					{
					setState(538);
					match(DIV);
					setState(539);
					((TermContext)_localctx).f2 = factor();
					 ((TermContext)_localctx).node =  new DivisionNode(_localctx.node, ((TermContext)_localctx).f2.node); 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(546);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FactorContext extends ParserRuleContext {
		public ExprNode node;
		public Value val;
		public ExponentContext e1;
		public ExponentContext e2;
		public List<ExponentContext> exponent() {
			return getRuleContexts(ExponentContext.class);
		}
		public ExponentContext exponent(int i) {
			return getRuleContext(ExponentContext.class,i);
		}
		public List<TerminalNode> EXP() { return getTokens(LogoTecParser.EXP); }
		public TerminalNode EXP(int i) {
			return getToken(LogoTecParser.EXP, i);
		}
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitFactor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogoTecVisitor ) return ((LogoTecVisitor<? extends T>)visitor).visitFactor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_factor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(547);
			((FactorContext)_localctx).e1 = exponent();
			 ((FactorContext)_localctx).node =  ((FactorContext)_localctx).e1.node; ((FactorContext)_localctx).val =  ((FactorContext)_localctx).e1.val; 
			setState(555);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EXP) {
				{
				{
				setState(549);
				match(EXP);
				setState(550);
				((FactorContext)_localctx).e2 = exponent();
				 ((FactorContext)_localctx).node =  new ExponentiationNode(_localctx.node, ((FactorContext)_localctx).e2.node);  
				}
				}
				setState(557);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExponentContext extends ParserRuleContext {
		public ExprNode node;
		public Value val;
		public UnaryContext unary;
		public UnaryContext unary() {
			return getRuleContext(UnaryContext.class,0);
		}
		public ExponentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exponent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterExponent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitExponent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogoTecVisitor ) return ((LogoTecVisitor<? extends T>)visitor).visitExponent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExponentContext exponent() throws RecognitionException {
		ExponentContext _localctx = new ExponentContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_exponent);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(558);
			((ExponentContext)_localctx).unary = unary();
			 ((ExponentContext)_localctx).node =  ((ExponentContext)_localctx).unary.node; ((ExponentContext)_localctx).val =  ((ExponentContext)_localctx).unary.val; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnaryContext extends ParserRuleContext {
		public ExprNode node;
		public Value val;
		public UnaryContext u;
		public FuncCallContext funcCall;
		public PrimaryContext primary;
		public TerminalNode MINUS() { return getToken(LogoTecParser.MINUS, 0); }
		public UnaryContext unary() {
			return getRuleContext(UnaryContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(LogoTecParser.PLUS, 0); }
		public FuncCallContext funcCall() {
			return getRuleContext(FuncCallContext.class,0);
		}
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public UnaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterUnary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitUnary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogoTecVisitor ) return ((LogoTecVisitor<? extends T>)visitor).visitUnary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryContext unary() throws RecognitionException {
		UnaryContext _localctx = new UnaryContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_unary);
		try {
			setState(575);
			switch (_input.LA(1)) {
			case MINUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(561);
				match(MINUS);
				setState(562);
				((UnaryContext)_localctx).u = unary();
				 ((UnaryContext)_localctx).node =  new SubtractionNode(new ConstNode(0), ((UnaryContext)_localctx).u.node); ((UnaryContext)_localctx).val =  Value.unknown(); 
				}
				break;
			case PLUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(565);
				match(PLUS);
				setState(566);
				((UnaryContext)_localctx).u = unary();
				 ((UnaryContext)_localctx).node =  ((UnaryContext)_localctx).u.node; ((UnaryContext)_localctx).val =  ((UnaryContext)_localctx).u.val; 
				}
				break;
			case IGUALESQ:
			case YFUNC:
			case OFUNC:
			case MAYORQ:
			case MENORQ:
			case DIFERENCIA:
			case AZAR:
			case PRODUCTO:
			case POTENCIA:
			case DIVISION:
			case SUMA:
				enterOuterAlt(_localctx, 3);
				{
				setState(569);
				((UnaryContext)_localctx).funcCall = funcCall();
				 ((UnaryContext)_localctx).node =  ((UnaryContext)_localctx).funcCall.node; ((UnaryContext)_localctx).val =  ((UnaryContext)_localctx).funcCall.val; 
				}
				break;
			case PAR_OPEN:
			case BOOLEAN:
			case DECIMAL:
			case NUMBER:
			case STRING:
			case ID:
				enterOuterAlt(_localctx, 4);
				{
				setState(572);
				((UnaryContext)_localctx).primary = primary();
				 ((UnaryContext)_localctx).node =  ((UnaryContext)_localctx).primary.node; ((UnaryContext)_localctx).val =  ((UnaryContext)_localctx).primary.val; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncCallContext extends ParserRuleContext {
		public ExprNode node;
		public Value val;
		public ExpressionSeriesContext args;
		public ExpressionContext e1;
		public ExpressionContext expression;
		public List<ExpressionContext> rest = new ArrayList<ExpressionContext>();
		public TerminalNode IGUALESQ() { return getToken(LogoTecParser.IGUALESQ, 0); }
		public TerminalNode PAR_OPEN() { return getToken(LogoTecParser.PAR_OPEN, 0); }
		public TerminalNode PAR_CLOSE() { return getToken(LogoTecParser.PAR_CLOSE, 0); }
		public ExpressionSeriesContext expressionSeries() {
			return getRuleContext(ExpressionSeriesContext.class,0);
		}
		public TerminalNode YFUNC() { return getToken(LogoTecParser.YFUNC, 0); }
		public TerminalNode OFUNC() { return getToken(LogoTecParser.OFUNC, 0); }
		public TerminalNode MAYORQ() { return getToken(LogoTecParser.MAYORQ, 0); }
		public TerminalNode MENORQ() { return getToken(LogoTecParser.MENORQ, 0); }
		public TerminalNode AZAR() { return getToken(LogoTecParser.AZAR, 0); }
		public TerminalNode PRODUCTO() { return getToken(LogoTecParser.PRODUCTO, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LogoTecParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LogoTecParser.COMMA, i);
		}
		public TerminalNode POTENCIA() { return getToken(LogoTecParser.POTENCIA, 0); }
		public TerminalNode DIVISION() { return getToken(LogoTecParser.DIVISION, 0); }
		public TerminalNode SUMA() { return getToken(LogoTecParser.SUMA, 0); }
		public TerminalNode DIFERENCIA() { return getToken(LogoTecParser.DIFERENCIA, 0); }
		public FuncCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterFuncCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitFuncCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogoTecVisitor ) return ((LogoTecVisitor<? extends T>)visitor).visitFuncCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncCallContext funcCall() throws RecognitionException {
		FuncCallContext _localctx = new FuncCallContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_funcCall);
		int _la;
		try {
			setState(664);
			switch (_input.LA(1)) {
			case IGUALESQ:
				enterOuterAlt(_localctx, 1);
				{
				setState(577);
				match(IGUALESQ);
				setState(578);
				match(PAR_OPEN);
				setState(579);
				((FuncCallContext)_localctx).args = expressionSeries();
				setState(580);
				match(PAR_CLOSE);
				 
				        requireTwoArguments(((FuncCallContext)_localctx).args.list.size(), "Iguales?", currentLine());
				        ExprNode e1 = ((FuncCallContext)_localctx).args.list.size() > 0 ? ((FuncCallContext)_localctx).args.list.get(0) : new ConstNode(0);
				        ExprNode e2 = ((FuncCallContext)_localctx).args.list.size() > 1 ? ((FuncCallContext)_localctx).args.list.get(1) : new ConstNode(0);
				        ((FuncCallContext)_localctx).node =  new EqualsFuncNode(e1, e2); 
				      
				}
				break;
			case YFUNC:
				enterOuterAlt(_localctx, 2);
				{
				setState(583);
				match(YFUNC);
				setState(584);
				match(PAR_OPEN);
				setState(585);
				((FuncCallContext)_localctx).args = expressionSeries();
				setState(586);
				match(PAR_CLOSE);
				 
				        requireTwoArguments(((FuncCallContext)_localctx).args.list.size(), "Y", currentLine());
				        ExprNode e1 = ((FuncCallContext)_localctx).args.list.size() > 0 ? ((FuncCallContext)_localctx).args.list.get(0) : new ConstNode(false);
				        ExprNode e2 = ((FuncCallContext)_localctx).args.list.size() > 1 ? ((FuncCallContext)_localctx).args.list.get(1) : new ConstNode(false);
				        ((FuncCallContext)_localctx).node =  new LogicalAndNode(e1, e2);
				      
				}
				break;
			case OFUNC:
				enterOuterAlt(_localctx, 3);
				{
				setState(589);
				match(OFUNC);
				setState(590);
				match(PAR_OPEN);
				setState(591);
				((FuncCallContext)_localctx).args = expressionSeries();
				setState(592);
				match(PAR_CLOSE);
				 
				        requireTwoArguments(((FuncCallContext)_localctx).args.list.size(), "O", currentLine());
				        ExprNode e1 = ((FuncCallContext)_localctx).args.list.size() > 0 ? ((FuncCallContext)_localctx).args.list.get(0) : new ConstNode(false);
				        ExprNode e2 = ((FuncCallContext)_localctx).args.list.size() > 1 ? ((FuncCallContext)_localctx).args.list.get(1) : new ConstNode(false);
				        ((FuncCallContext)_localctx).node =  new LogicalOrNode(e1, e2);
				      
				}
				break;
			case MAYORQ:
				enterOuterAlt(_localctx, 4);
				{
				setState(595);
				match(MAYORQ);
				setState(596);
				match(PAR_OPEN);
				setState(597);
				((FuncCallContext)_localctx).args = expressionSeries();
				setState(598);
				match(PAR_CLOSE);
				 
				        requireTwoArguments(((FuncCallContext)_localctx).args.list.size(), "MayorQue?", currentLine());
				        ExprNode e1 = ((FuncCallContext)_localctx).args.list.size() > 0 ? ((FuncCallContext)_localctx).args.list.get(0) : new ConstNode(0);
				        ExprNode e2 = ((FuncCallContext)_localctx).args.list.size() > 1 ? ((FuncCallContext)_localctx).args.list.get(1) : new ConstNode(0);
				        requireNumericArgs(e1, e2, "MayorQue?");
				        ((FuncCallContext)_localctx).node =  new GreaterThanNode(e1, e2);  
				      
				}
				break;
			case MENORQ:
				enterOuterAlt(_localctx, 5);
				{
				setState(601);
				match(MENORQ);
				setState(602);
				match(PAR_OPEN);
				setState(603);
				((FuncCallContext)_localctx).args = expressionSeries();
				setState(604);
				match(PAR_CLOSE);
				 
				        requireTwoArguments(((FuncCallContext)_localctx).args.list.size(), "MenorQue?", currentLine());
				        ExprNode e1 = ((FuncCallContext)_localctx).args.list.size() > 0 ? ((FuncCallContext)_localctx).args.list.get(0) : new ConstNode(0);
				        ExprNode e2 = ((FuncCallContext)_localctx).args.list.size() > 1 ? ((FuncCallContext)_localctx).args.list.get(1) : new ConstNode(0);
				        requireNumericArgs(e1, e2, "MenorQue?");
				        ((FuncCallContext)_localctx).node =  new LessThanNode(e1, e2);  
				      
				}
				break;
			case AZAR:
				enterOuterAlt(_localctx, 6);
				{
				setState(607);
				match(AZAR);
				setState(608);
				match(PAR_OPEN);
				setState(609);
				((FuncCallContext)_localctx).args = expressionSeries();
				setState(610);
				match(PAR_CLOSE);
				 
				        requireOneArgument(((FuncCallContext)_localctx).args.list.size(), "AZAR", currentLine());
				        ExprNode e = ((FuncCallContext)_localctx).args.list.size() > 0 ? ((FuncCallContext)_localctx).args.list.get(0) : new ConstNode(1);
				        requireNumericType(e, "AZAR");
				        ((FuncCallContext)_localctx).node =  new RandomNode(e); 
				      
				}
				break;
			case PRODUCTO:
				enterOuterAlt(_localctx, 7);
				{
				setState(613);
				match(PRODUCTO);
				setState(614);
				match(PAR_OPEN);
				setState(615);
				((FuncCallContext)_localctx).e1 = expression();
				setState(620);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(616);
					match(COMMA);
					setState(617);
					((FuncCallContext)_localctx).expression = expression();
					((FuncCallContext)_localctx).rest.add(((FuncCallContext)_localctx).expression);
					}
					}
					setState(622);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(623);
				match(PAR_CLOSE);

				        requireNumericType(((FuncCallContext)_localctx).e1.node, "PRODUCTO");
				        List<ExprNode> restNodes = new ArrayList<>();
				        if (((FuncCallContext)_localctx).rest != null) {
				            for (ExpressionContext ctx : ((FuncCallContext)_localctx).rest) {
				                requireNumericType(ctx.node, "PRODUCTO");
				                restNodes.add(ctx.node);
				            }
				        }
				        ((FuncCallContext)_localctx).node =  new ProductNode(((FuncCallContext)_localctx).e1.node, restNodes);
				      
				}
				break;
			case POTENCIA:
				enterOuterAlt(_localctx, 8);
				{
				setState(626);
				match(POTENCIA);
				setState(627);
				match(PAR_OPEN);
				setState(628);
				((FuncCallContext)_localctx).args = expressionSeries();
				setState(629);
				match(PAR_CLOSE);
				 
				        requireTwoArguments(((FuncCallContext)_localctx).args.list.size(), "POTENCIA", currentLine());
				        ExprNode e1 = ((FuncCallContext)_localctx).args.list.size() > 0 ? ((FuncCallContext)_localctx).args.list.get(0) : new ConstNode(0);
				        ExprNode e2 = ((FuncCallContext)_localctx).args.list.size() > 1 ? ((FuncCallContext)_localctx).args.list.get(1) : new ConstNode(0);
				        requireNumericArgs(e1, e2, "POTENCIA");
				        ((FuncCallContext)_localctx).node =  new ExponentiationNode(e1, e2); 
				      
				}
				break;
			case DIVISION:
				enterOuterAlt(_localctx, 9);
				{
				setState(632);
				match(DIVISION);
				setState(633);
				match(PAR_OPEN);
				setState(634);
				((FuncCallContext)_localctx).args = expressionSeries();
				setState(635);
				match(PAR_CLOSE);
				 
				        requireTwoArguments(((FuncCallContext)_localctx).args.list.size(), "DIVISION", currentLine());
				        ExprNode e1 = ((FuncCallContext)_localctx).args.list.size() > 0 ? ((FuncCallContext)_localctx).args.list.get(0) : new ConstNode(0);
				        ExprNode e2 = ((FuncCallContext)_localctx).args.list.size() > 1 ? ((FuncCallContext)_localctx).args.list.get(1) : new ConstNode(1);
				        requireNumericArgs(e1, e2, "DIVISION");
				        ((FuncCallContext)_localctx).node =  new DivisionNode(e1, e2); 
				      
				}
				break;
			case SUMA:
				enterOuterAlt(_localctx, 10);
				{
				setState(638);
				match(SUMA);
				setState(639);
				match(PAR_OPEN);
				setState(640);
				((FuncCallContext)_localctx).e1 = expression();
				setState(645);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(641);
					match(COMMA);
					setState(642);
					((FuncCallContext)_localctx).expression = expression();
					((FuncCallContext)_localctx).rest.add(((FuncCallContext)_localctx).expression);
					}
					}
					setState(647);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(648);
				match(PAR_CLOSE);

				        requireNumericType(((FuncCallContext)_localctx).e1.node, "SUMA");
				        List<ExprNode> restNodes = new ArrayList<>();
				        if (((FuncCallContext)_localctx).rest != null) {
				            for (ExpressionContext ctx : ((FuncCallContext)_localctx).rest) {
				                requireNumericType(ctx.node, "SUMA");
				                restNodes.add(ctx.node);
				            }
				        }
				        ((FuncCallContext)_localctx).node =  new SumNode(((FuncCallContext)_localctx).e1.node, restNodes);
				      
				}
				break;
			case DIFERENCIA:
				enterOuterAlt(_localctx, 11);
				{
				setState(651);
				match(DIFERENCIA);
				setState(652);
				match(PAR_OPEN);
				setState(653);
				((FuncCallContext)_localctx).e1 = expression();
				setState(658);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(654);
					match(COMMA);
					setState(655);
					((FuncCallContext)_localctx).expression = expression();
					((FuncCallContext)_localctx).rest.add(((FuncCallContext)_localctx).expression);
					}
					}
					setState(660);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(661);
				match(PAR_CLOSE);

				        requireNumericType(((FuncCallContext)_localctx).e1.node, "DIFERENCIA");
				        List<ExprNode> restNodes = new ArrayList<>();
				        if (((FuncCallContext)_localctx).rest != null) {
				            for (ExpressionContext ctx : ((FuncCallContext)_localctx).rest) {
				                requireNumericType(ctx.node, "DIFERENCIA");
				                restNodes.add(ctx.node);
				            }
				        }
				        ((FuncCallContext)_localctx).node =  new DifferenceNode(((FuncCallContext)_localctx).e1.node, restNodes);
				      
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimaryContext extends ParserRuleContext {
		public ExprNode node;
		public Value val;
		public Token DECIMAL;
		public Token NUMBER;
		public Token BOOLEAN;
		public Token ID;
		public Token STRING;
		public ExpressionContext expression;
		public TerminalNode DECIMAL() { return getToken(LogoTecParser.DECIMAL, 0); }
		public TerminalNode NUMBER() { return getToken(LogoTecParser.NUMBER, 0); }
		public TerminalNode BOOLEAN() { return getToken(LogoTecParser.BOOLEAN, 0); }
		public TerminalNode ID() { return getToken(LogoTecParser.ID, 0); }
		public TerminalNode STRING() { return getToken(LogoTecParser.STRING, 0); }
		public TerminalNode PAR_OPEN() { return getToken(LogoTecParser.PAR_OPEN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PAR_CLOSE() { return getToken(LogoTecParser.PAR_CLOSE, 0); }
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitPrimary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogoTecVisitor ) return ((LogoTecVisitor<? extends T>)visitor).visitPrimary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_primary);
		try {
			setState(681);
			switch (_input.LA(1)) {
			case DECIMAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(666);
				((PrimaryContext)_localctx).DECIMAL = match(DECIMAL);

				        double v = Double.parseDouble((((PrimaryContext)_localctx).DECIMAL!=null?((PrimaryContext)_localctx).DECIMAL.getText():null).replace(',', '.'));
				        ((PrimaryContext)_localctx).node =  new ConstNode(v);
				        ((PrimaryContext)_localctx).val =  Value.unknown();
				      
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(668);
				((PrimaryContext)_localctx).NUMBER = match(NUMBER);
				 
				        int v = Integer.parseInt((((PrimaryContext)_localctx).NUMBER!=null?((PrimaryContext)_localctx).NUMBER.getText():null));
				        ((PrimaryContext)_localctx).node =  new ConstNode(v);
				        ((PrimaryContext)_localctx).val =  Value.integer(v);
				      
				}
				break;
			case BOOLEAN:
				enterOuterAlt(_localctx, 3);
				{
				setState(670);
				((PrimaryContext)_localctx).BOOLEAN = match(BOOLEAN);
				 
				        boolean b = Boolean.parseBoolean((((PrimaryContext)_localctx).BOOLEAN!=null?((PrimaryContext)_localctx).BOOLEAN.getText():null));
				        ((PrimaryContext)_localctx).node =  new ConstNode(b);
				        ((PrimaryContext)_localctx).val =  Value.bool(b);
				      
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 4);
				{
				setState(672);
				((PrimaryContext)_localctx).ID = match(ID);
				 
				        validateIdentifierLength((((PrimaryContext)_localctx).ID!=null?((PrimaryContext)_localctx).ID.getText():null), (((PrimaryContext)_localctx).ID!=null?((PrimaryContext)_localctx).ID.getLine():0));
				        ((PrimaryContext)_localctx).node =  new VarRefNode((((PrimaryContext)_localctx).ID!=null?((PrimaryContext)_localctx).ID.getText():null));
				        ((PrimaryContext)_localctx).val =  Value.unknown();
				      
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 5);
				{
				setState(674);
				((PrimaryContext)_localctx).STRING = match(STRING);

				        String s = (((PrimaryContext)_localctx).STRING!=null?((PrimaryContext)_localctx).STRING.getText():null).substring(1,(((PrimaryContext)_localctx).STRING!=null?((PrimaryContext)_localctx).STRING.getText():null).length()-1);
				        ((PrimaryContext)_localctx).node =  new ConstNode(s);
				        ((PrimaryContext)_localctx).val =  Value.string(s);
				      
				}
				break;
			case PAR_OPEN:
				enterOuterAlt(_localctx, 6);
				{
				setState(676);
				match(PAR_OPEN);
				setState(677);
				((PrimaryContext)_localctx).expression = expression();
				setState(678);
				match(PAR_CLOSE);
				 
				        ((PrimaryContext)_localctx).node =  ((PrimaryContext)_localctx).expression.node; 
				        ((PrimaryContext)_localctx).val =  ((PrimaryContext)_localctx).expression.val; 
				      
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralOrStringContext extends ParserRuleContext {
		public ExprNode node;
		public Object jval;
		public Token DECIMAL;
		public Token NUMBER;
		public Token BOOLEAN;
		public Token STRING;
		public TerminalNode MINUS() { return getToken(LogoTecParser.MINUS, 0); }
		public TerminalNode DECIMAL() { return getToken(LogoTecParser.DECIMAL, 0); }
		public TerminalNode NUMBER() { return getToken(LogoTecParser.NUMBER, 0); }
		public TerminalNode BOOLEAN() { return getToken(LogoTecParser.BOOLEAN, 0); }
		public TerminalNode STRING() { return getToken(LogoTecParser.STRING, 0); }
		public LiteralOrStringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literalOrString; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterLiteralOrString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitLiteralOrString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogoTecVisitor ) return ((LogoTecVisitor<? extends T>)visitor).visitLiteralOrString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralOrStringContext literalOrString() throws RecognitionException {
		LiteralOrStringContext _localctx = new LiteralOrStringContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_literalOrString);
		try {
			setState(697);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(683);
				match(MINUS);
				setState(684);
				((LiteralOrStringContext)_localctx).DECIMAL = match(DECIMAL);

				        double v = -Double.parseDouble((((LiteralOrStringContext)_localctx).DECIMAL!=null?((LiteralOrStringContext)_localctx).DECIMAL.getText():null).replace(',', '.'));
				        ((LiteralOrStringContext)_localctx).node =  new ConstNode(v);
				        ((LiteralOrStringContext)_localctx).jval =  Double.valueOf(v);
				      
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(686);
				((LiteralOrStringContext)_localctx).DECIMAL = match(DECIMAL);

				        double v = Double.parseDouble((((LiteralOrStringContext)_localctx).DECIMAL!=null?((LiteralOrStringContext)_localctx).DECIMAL.getText():null).replace(',', '.'));
				        ((LiteralOrStringContext)_localctx).node =  new ConstNode(v);
				        ((LiteralOrStringContext)_localctx).jval =  Double.valueOf(v);
				      
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(688);
				match(MINUS);
				setState(689);
				((LiteralOrStringContext)_localctx).NUMBER = match(NUMBER);

				        int v = -Integer.parseInt((((LiteralOrStringContext)_localctx).NUMBER!=null?((LiteralOrStringContext)_localctx).NUMBER.getText():null));
				        ((LiteralOrStringContext)_localctx).node =  new ConstNode(v);
				        ((LiteralOrStringContext)_localctx).jval =  Integer.valueOf(v);
				      
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(691);
				((LiteralOrStringContext)_localctx).NUMBER = match(NUMBER);

				        int v = Integer.parseInt((((LiteralOrStringContext)_localctx).NUMBER!=null?((LiteralOrStringContext)_localctx).NUMBER.getText():null));
				        ((LiteralOrStringContext)_localctx).node =  new ConstNode(v);
				        ((LiteralOrStringContext)_localctx).jval =  Integer.valueOf(v);
				      
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(693);
				((LiteralOrStringContext)_localctx).BOOLEAN = match(BOOLEAN);

				        boolean v = Boolean.parseBoolean((((LiteralOrStringContext)_localctx).BOOLEAN!=null?((LiteralOrStringContext)_localctx).BOOLEAN.getText():null));
				        ((LiteralOrStringContext)_localctx).node =  new ConstNode(v);
				        ((LiteralOrStringContext)_localctx).jval =  Boolean.valueOf(v);
				      
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(695);
				((LiteralOrStringContext)_localctx).STRING = match(STRING);

				        String s = (((LiteralOrStringContext)_localctx).STRING!=null?((LiteralOrStringContext)_localctx).STRING.getText():null).substring(1, (((LiteralOrStringContext)_localctx).STRING!=null?((LiteralOrStringContext)_localctx).STRING.getText():null).length()-1);
				        ((LiteralOrStringContext)_localctx).node =  new ConstNode(s);
				        ((LiteralOrStringContext)_localctx).jval =  s;
				      
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmtFirstLineContext extends ParserRuleContext {
		public TerminalNode FIRSTLINE_COMMENT() { return getToken(LogoTecParser.FIRSTLINE_COMMENT, 0); }
		public CmtFirstLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmtFirstLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterCmtFirstLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitCmtFirstLine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogoTecVisitor ) return ((LogoTecVisitor<? extends T>)visitor).visitCmtFirstLine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CmtFirstLineContext cmtFirstLine() throws RecognitionException {
		CmtFirstLineContext _localctx = new CmtFirstLineContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_cmtFirstLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(699);
			match(FIRSTLINE_COMMENT);
			 firstLineHasComment = true; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InvalidCommentContext extends ParserRuleContext {
		public TerminalNode INVALID_COMMENT() { return getToken(LogoTecParser.INVALID_COMMENT, 0); }
		public InvalidCommentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_invalidComment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterInvalidComment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitInvalidComment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogoTecVisitor ) return ((LogoTecVisitor<? extends T>)visitor).visitInvalidComment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InvalidCommentContext invalidComment() throws RecognitionException {
		InvalidCommentContext _localctx = new InvalidCommentContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_invalidComment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(702);
			match(INVALID_COMMENT);

			        errors.add("Error en línea " + currentLine() + ": los comentarios deben empezar con //");
			        //throw new RuntimeException("Error en línea " + currentLine() + ": los comentarios deben empezar con //");
			      
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 6:
			return callProc_sempred((CallProcContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean callProc_sempred(CallProcContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return  _input.LA(1) != BRACKET_OPEN && 
		          (_input.LA(1) == NUMBER || _input.LA(1) == ID || _input.LA(1) == PAR_OPEN) ;
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3S\u02c4\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \3\2"+
		"\5\2B\n\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3O\n\3\f\3\16"+
		"\3R\13\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\7\4\\\n\4\f\4\16\4_\13\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\7\4g\n\4\f\4\16\4j\13\4\3\4\3\4\3\4\3\4\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u008e\n\5\3\6\3\6\3\6\3\6"+
		"\5\6\u0094\n\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\5\b\u00a5\n\b\3\b\3\b\3\b\7\b\u00aa\n\b\f\b\16\b\u00ad\13\b\5\b\u00af"+
		"\n\b\3\b\3\b\3\b\3\b\3\b\6\b\u00b6\n\b\r\b\16\b\u00b7\5\b\u00ba\n\b\3"+
		"\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00c7\n\t\3\n\3\n\3\n\5"+
		"\n\u00cc\n\n\3\n\3\n\3\n\7\n\u00d1\n\n\f\n\16\n\u00d4\13\n\5\n\u00d6\n"+
		"\n\3\13\3\13\3\13\3\13\3\13\7\13\u00dd\n\13\f\13\16\13\u00e0\13\13\3\13"+
		"\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00ec\n\f\f\f\16\f\u00ef\13"+
		"\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u00fd\n\r\f\r\16"+
		"\r\u0100\13\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u0108\n\r\f\r\16\r\u010b\13"+
		"\r\3\r\7\r\u010e\n\r\f\r\16\r\u0111\13\r\3\r\3\r\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\7\16\u011b\n\16\f\16\16\16\u011e\13\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u012d\n\16\3\16\3\16"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\7\17\u013a\n\17\f\17\16"+
		"\17\u013d\13\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\7\20\u014b\n\20\f\20\16\20\u014e\13\20\3\20\3\20\3\20\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u01c2\n\21"+
		"\3\22\3\22\3\22\3\22\5\22\u01c8\n\22\3\23\3\23\3\23\3\23\3\23\3\23\7\23"+
		"\u01d0\n\23\f\23\16\23\u01d3\13\23\3\24\3\24\3\24\3\24\3\24\3\24\7\24"+
		"\u01db\n\24\f\24\16\24\u01de\13\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\5\25\u01e7\n\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\7\26\u0203\n\26\f\26\16\26\u0206\13\26\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\7\27\u0212\n\27\f\27\16\27\u0215\13\27\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\7\30\u0221\n\30\f\30\16"+
		"\30\u0224\13\30\3\31\3\31\3\31\3\31\3\31\3\31\7\31\u022c\n\31\f\31\16"+
		"\31\u022f\13\31\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\5\33\u0242\n\33\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\7\34\u026d\n\34\f\34\16\34\u0270"+
		"\13\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\34\3\34\7\34\u0286\n\34\f\34\16\34\u0289\13"+
		"\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\7\34\u0293\n\34\f\34\16\34"+
		"\u0296\13\34\3\34\3\34\3\34\5\34\u029b\n\34\3\35\3\35\3\35\3\35\3\35\3"+
		"\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\5\35\u02ac\n\35\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\5\36"+
		"\u02bc\n\36\3\37\3\37\3\37\3 \3 \3 \3 \2\2!\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\32\34\36 \"$&(*,.\60\62\64\668:<>\2\2\u030e\2A\3\2\2\2\4P\3\2\2\2"+
		"\6U\3\2\2\2\b\u008d\3\2\2\2\n\u008f\3\2\2\2\f\u0097\3\2\2\2\16\u009e\3"+
		"\2\2\2\20\u00c6\3\2\2\2\22\u00d5\3\2\2\2\24\u00d7\3\2\2\2\26\u00e4\3\2"+
		"\2\2\30\u00f3\3\2\2\2\32\u0114\3\2\2\2\34\u0130\3\2\2\2\36\u0141\3\2\2"+
		"\2 \u01c1\3\2\2\2\"\u01c7\3\2\2\2$\u01c9\3\2\2\2&\u01d4\3\2\2\2(\u01e6"+
		"\3\2\2\2*\u01e8\3\2\2\2,\u0207\3\2\2\2.\u0216\3\2\2\2\60\u0225\3\2\2\2"+
		"\62\u0230\3\2\2\2\64\u0241\3\2\2\2\66\u029a\3\2\2\28\u02ab\3\2\2\2:\u02bb"+
		"\3\2\2\2<\u02bd\3\2\2\2>\u02c0\3\2\2\2@B\5<\37\2A@\3\2\2\2AB\3\2\2\2B"+
		"C\3\2\2\2CD\5\4\3\2DE\7\2\2\3EF\b\2\1\2F\3\3\2\2\2GH\5\6\4\2HI\b\3\1\2"+
		"IO\3\2\2\2JK\5\b\5\2KL\b\3\1\2LO\3\2\2\2MO\5> \2NG\3\2\2\2NJ\3\2\2\2N"+
		"M\3\2\2\2OR\3\2\2\2PN\3\2\2\2PQ\3\2\2\2QS\3\2\2\2RP\3\2\2\2ST\b\3\1\2"+
		"T\5\3\2\2\2UV\7\3\2\2VW\7N\2\2WX\b\4\1\2X]\7C\2\2YZ\7N\2\2Z\\\b\4\1\2"+
		"[Y\3\2\2\2\\_\3\2\2\2][\3\2\2\2]^\3\2\2\2^`\3\2\2\2_]\3\2\2\2`a\7D\2\2"+
		"ab\b\4\1\2bh\7C\2\2cd\5\b\5\2de\b\4\1\2eg\3\2\2\2fc\3\2\2\2gj\3\2\2\2"+
		"hf\3\2\2\2hi\3\2\2\2ik\3\2\2\2jh\3\2\2\2kl\7D\2\2lm\7\5\2\2mn\b\4\1\2"+
		"n\7\3\2\2\2op\5\n\6\2pq\b\5\1\2q\u008e\3\2\2\2rs\5\f\7\2st\b\5\1\2t\u008e"+
		"\3\2\2\2uv\5\32\16\2vw\b\5\1\2w\u008e\3\2\2\2xy\5\30\r\2yz\b\5\1\2z\u008e"+
		"\3\2\2\2{|\5\36\20\2|}\b\5\1\2}\u008e\3\2\2\2~\177\5\34\17\2\177\u0080"+
		"\b\5\1\2\u0080\u008e\3\2\2\2\u0081\u0082\5\26\f\2\u0082\u0083\b\5\1\2"+
		"\u0083\u008e\3\2\2\2\u0084\u0085\5\24\13\2\u0085\u0086\b\5\1\2\u0086\u008e"+
		"\3\2\2\2\u0087\u0088\5 \21\2\u0088\u0089\b\5\1\2\u0089\u008e\3\2\2\2\u008a"+
		"\u008b\5\16\b\2\u008b\u008c\b\5\1\2\u008c\u008e\3\2\2\2\u008do\3\2\2\2"+
		"\u008dr\3\2\2\2\u008du\3\2\2\2\u008dx\3\2\2\2\u008d{\3\2\2\2\u008d~\3"+
		"\2\2\2\u008d\u0081\3\2\2\2\u008d\u0084\3\2\2\2\u008d\u0087\3\2\2\2\u008d"+
		"\u008a\3\2\2\2\u008e\t\3\2\2\2\u008f\u0090\7\4\2\2\u0090\u0091\7N\2\2"+
		"\u0091\u0093\5:\36\2\u0092\u0094\7G\2\2\u0093\u0092\3\2\2\2\u0093\u0094"+
		"\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0096\b\6\1\2\u0096\13\3\2\2\2\u0097"+
		"\u0098\7\32\2\2\u0098\u0099\7N\2\2\u0099\u009a\7B\2\2\u009a\u009b\5$\23"+
		"\2\u009b\u009c\7G\2\2\u009c\u009d\b\7\1\2\u009d\r\3\2\2\2\u009e\u009f"+
		"\7N\2\2\u009f\u00b9\b\b\1\2\u00a0\u00ae\7C\2\2\u00a1\u00a2\5$\23\2\u00a2"+
		"\u00ab\b\b\1\2\u00a3\u00a5\7H\2\2\u00a4\u00a3\3\2\2\2\u00a4\u00a5\3\2"+
		"\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a7\5$\23\2\u00a7\u00a8\b\b\1\2\u00a8"+
		"\u00aa\3\2\2\2\u00a9\u00a4\3\2\2\2\u00aa\u00ad\3\2\2\2\u00ab\u00a9\3\2"+
		"\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00af\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ae"+
		"\u00a1\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00ba\7D"+
		"\2\2\u00b1\u00b5\6\b\2\2\u00b2\u00b3\5\20\t\2\u00b3\u00b4\b\b\1\2\u00b4"+
		"\u00b6\3\2\2\2\u00b5\u00b2\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b5\3\2"+
		"\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00ba\3\2\2\2\u00b9\u00a0\3\2\2\2\u00b9"+
		"\u00b1\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00bc\b\b"+
		"\1\2\u00bc\17\3\2\2\2\u00bd\u00be\7L\2\2\u00be\u00c7\b\t\1\2\u00bf\u00c0"+
		"\7N\2\2\u00c0\u00c7\b\t\1\2\u00c1\u00c2\7E\2\2\u00c2\u00c3\5$\23\2\u00c3"+
		"\u00c4\7F\2\2\u00c4\u00c5\b\t\1\2\u00c5\u00c7\3\2\2\2\u00c6\u00bd\3\2"+
		"\2\2\u00c6\u00bf\3\2\2\2\u00c6\u00c1\3\2\2\2\u00c7\21\3\2\2\2\u00c8\u00c9"+
		"\5$\23\2\u00c9\u00d2\b\n\1\2\u00ca\u00cc\7H\2\2\u00cb\u00ca\3\2\2\2\u00cb"+
		"\u00cc\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00ce\5$\23\2\u00ce\u00cf\b\n"+
		"\1\2\u00cf\u00d1\3\2\2\2\u00d0\u00cb\3\2\2\2\u00d1\u00d4\3\2\2\2\u00d2"+
		"\u00d0\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d6\3\2\2\2\u00d4\u00d2\3\2"+
		"\2\2\u00d5\u00c8\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\23\3\2\2\2\u00d7\u00d8"+
		"\7\6\2\2\u00d8\u00de\7C\2\2\u00d9\u00da\5\b\5\2\u00da\u00db\b\13\1\2\u00db"+
		"\u00dd\3\2\2\2\u00dc\u00d9\3\2\2\2\u00dd\u00e0\3\2\2\2\u00de\u00dc\3\2"+
		"\2\2\u00de\u00df\3\2\2\2\u00df\u00e1\3\2\2\2\u00e0\u00de\3\2\2\2\u00e1"+
		"\u00e2\7D\2\2\u00e2\u00e3\b\13\1\2\u00e3\25\3\2\2\2\u00e4\u00e5\7\7\2"+
		"\2\u00e5\u00e6\5$\23\2\u00e6\u00e7\b\f\1\2\u00e7\u00ed\7C\2\2\u00e8\u00e9"+
		"\5\b\5\2\u00e9\u00ea\b\f\1\2\u00ea\u00ec\3\2\2\2\u00eb\u00e8\3\2\2\2\u00ec"+
		"\u00ef\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00f0\3\2"+
		"\2\2\u00ef\u00ed\3\2\2\2\u00f0\u00f1\7D\2\2\u00f1\u00f2\b\f\1\2\u00f2"+
		"\27\3\2\2\2\u00f3\u00f4\7\27\2\2\u00f4\u00f5\7E\2\2\u00f5\u00f6\5$\23"+
		"\2\u00f6\u00f7\7F\2\2\u00f7\u00f8\b\r\1\2\u00f8\u00fe\7C\2\2\u00f9\u00fa"+
		"\5\b\5\2\u00fa\u00fb\b\r\1\2\u00fb\u00fd\3\2\2\2\u00fc\u00f9\3\2\2\2\u00fd"+
		"\u0100\3\2\2\2\u00fe\u00fc\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0101\3\2"+
		"\2\2\u0100\u00fe\3\2\2\2\u0101\u010f\7D\2\2\u0102\u0103\7C\2\2\u0103\u0109"+
		"\b\r\1\2\u0104\u0105\5\b\5\2\u0105\u0106\b\r\1\2\u0106\u0108\3\2\2\2\u0107"+
		"\u0104\3\2\2\2\u0108\u010b\3\2\2\2\u0109\u0107\3\2\2\2\u0109\u010a\3\2"+
		"\2\2\u010a\u010c\3\2\2\2\u010b\u0109\3\2\2\2\u010c\u010e\7D\2\2\u010d"+
		"\u0102\3\2\2\2\u010e\u0111\3\2\2\2\u010f\u010d\3\2\2\2\u010f\u0110\3\2"+
		"\2\2\u0110\u0112\3\2\2\2\u0111\u010f\3\2\2\2\u0112\u0113\b\r\1\2\u0113"+
		"\31\3\2\2\2\u0114\u0115\7\4\2\2\u0115\u0116\7I\2\2\u0116\u011c\7C\2\2"+
		"\u0117\u0118\5\b\5\2\u0118\u0119\b\16\1\2\u0119\u011b\3\2\2\2\u011a\u0117"+
		"\3\2\2\2\u011b\u011e\3\2\2\2\u011c\u011a\3\2\2\2\u011c\u011d\3\2\2\2\u011d"+
		"\u011f\3\2\2\2\u011e\u011c\3\2\2\2\u011f\u012c\7D\2\2\u0120\u0121\7\30"+
		"\2\2\u0121\u0122\7E\2\2\u0122\u0123\5$\23\2\u0123\u0124\7F\2\2\u0124\u0125"+
		"\b\16\1\2\u0125\u012d\3\2\2\2\u0126\u0127\7\31\2\2\u0127\u0128\7E\2\2"+
		"\u0128\u0129\5$\23\2\u0129\u012a\7F\2\2\u012a\u012b\b\16\1\2\u012b\u012d"+
		"\3\2\2\2\u012c\u0120\3\2\2\2\u012c\u0126\3\2\2\2\u012d\u012e\3\2\2\2\u012e"+
		"\u012f\b\16\1\2\u012f\33\3\2\2\2\u0130\u0131\7\31\2\2\u0131\u0132\7E\2"+
		"\2\u0132\u0133\5$\23\2\u0133\u0134\7F\2\2\u0134\u0135\b\17\1\2\u0135\u013b"+
		"\7C\2\2\u0136\u0137\5\b\5\2\u0137\u0138\b\17\1\2\u0138\u013a\3\2\2\2\u0139"+
		"\u0136\3\2\2\2\u013a\u013d\3\2\2\2\u013b\u0139\3\2\2\2\u013b\u013c\3\2"+
		"\2\2\u013c\u013e\3\2\2\2\u013d\u013b\3\2\2\2\u013e\u013f\7D\2\2\u013f"+
		"\u0140\b\17\1\2\u0140\35\3\2\2\2\u0141\u0142\7\30\2\2\u0142\u0143\7E\2"+
		"\2\u0143\u0144\5$\23\2\u0144\u0145\7F\2\2\u0145\u0146\b\20\1\2\u0146\u014c"+
		"\7C\2\2\u0147\u0148\5\b\5\2\u0148\u0149\b\20\1\2\u0149\u014b\3\2\2\2\u014a"+
		"\u0147\3\2\2\2\u014b\u014e\3\2\2\2\u014c\u014a\3\2\2\2\u014c\u014d\3\2"+
		"\2\2\u014d\u014f\3\2\2\2\u014e\u014c\3\2\2\2\u014f\u0150\7D\2\2\u0150"+
		"\u0151\b\20\1\2\u0151\37\3\2\2\2\u0152\u0153\7\33\2\2\u0153\u0154\5$\23"+
		"\2\u0154\u0155\b\21\1\2\u0155\u01c2\3\2\2\2\u0156\u0157\7\34\2\2\u0157"+
		"\u0158\5$\23\2\u0158\u0159\b\21\1\2\u0159\u01c2\3\2\2\2\u015a\u015b\7"+
		"\35\2\2\u015b\u015c\5$\23\2\u015c\u015d\b\21\1\2\u015d\u01c2\3\2\2\2\u015e"+
		"\u015f\7\36\2\2\u015f\u0160\5$\23\2\u0160\u0161\b\21\1\2\u0161\u01c2\3"+
		"\2\2\2\u0162\u0163\7 \2\2\u0163\u0164\5$\23\2\u0164\u0165\b\21\1\2\u0165"+
		"\u01c2\3\2\2\2\u0166\u0167\7!\2\2\u0167\u0168\5$\23\2\u0168\u0169\b\21"+
		"\1\2\u0169\u01c2\3\2\2\2\u016a\u016b\7\"\2\2\u016b\u016c\5$\23\2\u016c"+
		"\u016d\b\21\1\2\u016d\u01c2\3\2\2\2\u016e\u016f\7#\2\2\u016f\u0170\5$"+
		"\23\2\u0170\u0171\b\21\1\2\u0171\u01c2\3\2\2\2\u0172\u0173\7$\2\2\u0173"+
		"\u01c2\b\21\1\2\u0174\u0175\7%\2\2\u0175\u01c2\b\21\1\2\u0176\u0177\7"+
		"\b\2\2\u0177\u01c2\b\21\1\2\u0178\u0179\7\t\2\2\u0179\u01c2\b\21\1\2\u017a"+
		"\u017b\7\n\2\2\u017b\u017c\7C\2\2\u017c\u017d\5\22\n\2\u017d\u017e\7D"+
		"\2\2\u017e\u017f\b\21\1\2\u017f\u01c2\3\2\2\2\u0180\u0181\7\13\2\2\u0181"+
		"\u0182\5$\23\2\u0182\u0183\5$\23\2\u0183\u0184\b\21\1\2\u0184\u01c2\3"+
		"\2\2\2\u0185\u0186\7\f\2\2\u0186\u0187\5$\23\2\u0187\u0188\b\21\1\2\u0188"+
		"\u01c2\3\2\2\2\u0189\u018a\7\r\2\2\u018a\u01c2\b\21\1\2\u018b\u018c\7"+
		"\16\2\2\u018c\u018d\5$\23\2\u018d\u018e\b\21\1\2\u018e\u01c2\3\2\2\2\u018f"+
		"\u0190\7\17\2\2\u0190\u0191\5$\23\2\u0191\u0192\b\21\1\2\u0192\u01c2\3"+
		"\2\2\2\u0193\u0194\7\20\2\2\u0194\u01c2\b\21\1\2\u0195\u0196\7\21\2\2"+
		"\u0196\u01c2\b\21\1\2\u0197\u0198\7\22\2\2\u0198\u01c2\b\21\1\2\u0199"+
		"\u019a\7\23\2\2\u019a\u01c2\b\21\1\2\u019b\u019c\7\24\2\2\u019c\u01c2"+
		"\b\21\1\2\u019d\u019e\7\25\2\2\u019e\u019f\5$\23\2\u019f\u01a0\b\21\1"+
		"\2\u01a0\u01c2\3\2\2\2\u01a1\u01a2\7\26\2\2\u01a2\u01a3\7C\2\2\u01a3\u01a4"+
		"\7N\2\2\u01a4\u01a5\7D\2\2\u01a5\u01c2\b\21\1\2\u01a6\u01a7\7\26\2\2\u01a7"+
		"\u01a8\7C\2\2\u01a8\u01a9\7N\2\2\u01a9\u01aa\5$\23\2\u01aa\u01ab\7D\2"+
		"\2\u01ab\u01ac\b\21\1\2\u01ac\u01c2\3\2\2\2\u01ad\u01ae\7&\2\2\u01ae\u01af"+
		"\7C\2\2\u01af\u01b0\5\22\n\2\u01b0\u01b1\7D\2\2\u01b1\u01b2\b\21\1\2\u01b2"+
		"\u01c2\3\2\2\2\u01b3\u01b4\7(\2\2\u01b4\u01b5\7C\2\2\u01b5\u01b6\5\22"+
		"\n\2\u01b6\u01b7\7D\2\2\u01b7\u01b8\b\21\1\2\u01b8\u01c2\3\2\2\2\u01b9"+
		"\u01ba\7&\2\2\u01ba\u01bb\5\"\22\2\u01bb\u01bc\b\21\1\2\u01bc\u01c2\3"+
		"\2\2\2\u01bd\u01be\7(\2\2\u01be\u01bf\5\"\22\2\u01bf\u01c0\b\21\1\2\u01c0"+
		"\u01c2\3\2\2\2\u01c1\u0152\3\2\2\2\u01c1\u0156\3\2\2\2\u01c1\u015a\3\2"+
		"\2\2\u01c1\u015e\3\2\2\2\u01c1\u0162\3\2\2\2\u01c1\u0166\3\2\2\2\u01c1"+
		"\u016a\3\2\2\2\u01c1\u016e\3\2\2\2\u01c1\u0172\3\2\2\2\u01c1\u0174\3\2"+
		"\2\2\u01c1\u0176\3\2\2\2\u01c1\u0178\3\2\2\2\u01c1\u017a\3\2\2\2\u01c1"+
		"\u0180\3\2\2\2\u01c1\u0185\3\2\2\2\u01c1\u0189\3\2\2\2\u01c1\u018b\3\2"+
		"\2\2\u01c1\u018f\3\2\2\2\u01c1\u0193\3\2\2\2\u01c1\u0195\3\2\2\2\u01c1"+
		"\u0197\3\2\2\2\u01c1\u0199\3\2\2\2\u01c1\u019b\3\2\2\2\u01c1\u019d\3\2"+
		"\2\2\u01c1\u01a1\3\2\2\2\u01c1\u01a6\3\2\2\2\u01c1\u01ad\3\2\2\2\u01c1"+
		"\u01b3\3\2\2\2\u01c1\u01b9\3\2\2\2\u01c1\u01bd\3\2\2\2\u01c2!\3\2\2\2"+
		"\u01c3\u01c4\7S\2\2\u01c4\u01c8\b\22\1\2\u01c5\u01c6\7N\2\2\u01c6\u01c8"+
		"\b\22\1\2\u01c7\u01c3\3\2\2\2\u01c7\u01c5\3\2\2\2\u01c8#\3\2\2\2\u01c9"+
		"\u01ca\5&\24\2\u01ca\u01d1\b\23\1\2\u01cb\u01cc\7:\2\2\u01cc\u01cd\5&"+
		"\24\2\u01cd\u01ce\b\23\1\2\u01ce\u01d0\3\2\2\2\u01cf\u01cb\3\2\2\2\u01d0"+
		"\u01d3\3\2\2\2\u01d1\u01cf\3\2\2\2\u01d1\u01d2\3\2\2\2\u01d2%\3\2\2\2"+
		"\u01d3\u01d1\3\2\2\2\u01d4\u01d5\5(\25\2\u01d5\u01dc\b\24\1\2\u01d6\u01d7"+
		"\79\2\2\u01d7\u01d8\5(\25\2\u01d8\u01d9\b\24\1\2\u01d9\u01db\3\2\2\2\u01da"+
		"\u01d6\3\2\2\2\u01db\u01de\3\2\2\2\u01dc\u01da\3\2\2\2\u01dc\u01dd\3\2"+
		"\2\2\u01dd\'\3\2\2\2\u01de\u01dc\3\2\2\2\u01df\u01e0\7;\2\2\u01e0\u01e1"+
		"\5(\25\2\u01e1\u01e2\b\25\1\2\u01e2\u01e7\3\2\2\2\u01e3\u01e4\5*\26\2"+
		"\u01e4\u01e5\b\25\1\2\u01e5\u01e7\3\2\2\2\u01e6\u01df\3\2\2\2\u01e6\u01e3"+
		"\3\2\2\2\u01e7)\3\2\2\2\u01e8\u01e9\5,\27\2\u01e9\u0204\b\26\1\2\u01ea"+
		"\u01eb\7<\2\2\u01eb\u01ec\5,\27\2\u01ec\u01ed\b\26\1\2\u01ed\u0203\3\2"+
		"\2\2\u01ee\u01ef\7=\2\2\u01ef\u01f0\5,\27\2\u01f0\u01f1\b\26\1\2\u01f1"+
		"\u0203\3\2\2\2\u01f2\u01f3\7>\2\2\u01f3\u01f4\5,\27\2\u01f4\u01f5\b\26"+
		"\1\2\u01f5\u0203\3\2\2\2\u01f6\u01f7\7?\2\2\u01f7\u01f8\5,\27\2\u01f8"+
		"\u01f9\b\26\1\2\u01f9\u0203\3\2\2\2\u01fa\u01fb\7@\2\2\u01fb\u01fc\5,"+
		"\27\2\u01fc\u01fd\b\26\1\2\u01fd\u0203\3\2\2\2\u01fe\u01ff\7A\2\2\u01ff"+
		"\u0200\5,\27\2\u0200\u0201\b\26\1\2\u0201\u0203\3\2\2\2\u0202\u01ea\3"+
		"\2\2\2\u0202\u01ee\3\2\2\2\u0202\u01f2\3\2\2\2\u0202\u01f6\3\2\2\2\u0202"+
		"\u01fa\3\2\2\2\u0202\u01fe\3\2\2\2\u0203\u0206\3\2\2\2\u0204\u0202\3\2"+
		"\2\2\u0204\u0205\3\2\2\2\u0205+\3\2\2\2\u0206\u0204\3\2\2\2\u0207\u0208"+
		"\5.\30\2\u0208\u0213\b\27\1\2\u0209\u020a\7\64\2\2\u020a\u020b\5.\30\2"+
		"\u020b\u020c\b\27\1\2\u020c\u0212\3\2\2\2\u020d\u020e\7\65\2\2\u020e\u020f"+
		"\5.\30\2\u020f\u0210\b\27\1\2\u0210\u0212\3\2\2\2\u0211\u0209\3\2\2\2"+
		"\u0211\u020d\3\2\2\2\u0212\u0215\3\2\2\2\u0213\u0211\3\2\2\2\u0213\u0214"+
		"\3\2\2\2\u0214-\3\2\2\2\u0215\u0213\3\2\2\2\u0216\u0217\5\60\31\2\u0217"+
		"\u0222\b\30\1\2\u0218\u0219\7\66\2\2\u0219\u021a\5\60\31\2\u021a\u021b"+
		"\b\30\1\2\u021b\u0221\3\2\2\2\u021c\u021d\7\67\2\2\u021d\u021e\5\60\31"+
		"\2\u021e\u021f\b\30\1\2\u021f\u0221\3\2\2\2\u0220\u0218\3\2\2\2\u0220"+
		"\u021c\3\2\2\2\u0221\u0224\3\2\2\2\u0222\u0220\3\2\2\2\u0222\u0223\3\2"+
		"\2\2\u0223/\3\2\2\2\u0224\u0222\3\2\2\2\u0225\u0226\5\62\32\2\u0226\u022d"+
		"\b\31\1\2\u0227\u0228\78\2\2\u0228\u0229\5\62\32\2\u0229\u022a\b\31\1"+
		"\2\u022a\u022c\3\2\2\2\u022b\u0227\3\2\2\2\u022c\u022f\3\2\2\2\u022d\u022b"+
		"\3\2\2\2\u022d\u022e\3\2\2\2\u022e\61\3\2\2\2\u022f\u022d\3\2\2\2\u0230"+
		"\u0231\5\64\33\2\u0231\u0232\b\32\1\2\u0232\63\3\2\2\2\u0233\u0234\7\65"+
		"\2\2\u0234\u0235\5\64\33\2\u0235\u0236\b\33\1\2\u0236\u0242\3\2\2\2\u0237"+
		"\u0238\7\64\2\2\u0238\u0239\5\64\33\2\u0239\u023a\b\33\1\2\u023a\u0242"+
		"\3\2\2\2\u023b\u023c\5\66\34\2\u023c\u023d\b\33\1\2\u023d\u0242\3\2\2"+
		"\2\u023e\u023f\58\35\2\u023f\u0240\b\33\1\2\u0240\u0242\3\2\2\2\u0241"+
		"\u0233\3\2\2\2\u0241\u0237\3\2\2\2\u0241\u023b\3\2\2\2\u0241\u023e\3\2"+
		"\2\2\u0242\65\3\2\2\2\u0243\u0244\7)\2\2\u0244\u0245\7E\2\2\u0245\u0246"+
		"\5\22\n\2\u0246\u0247\7F\2\2\u0247\u0248\b\34\1\2\u0248\u029b\3\2\2\2"+
		"\u0249\u024a\7*\2\2\u024a\u024b\7E\2\2\u024b\u024c\5\22\n\2\u024c\u024d"+
		"\7F\2\2\u024d\u024e\b\34\1\2\u024e\u029b\3\2\2\2\u024f\u0250\7+\2\2\u0250"+
		"\u0251\7E\2\2\u0251\u0252\5\22\n\2\u0252\u0253\7F\2\2\u0253\u0254\b\34"+
		"\1\2\u0254\u029b\3\2\2\2\u0255\u0256\7,\2\2\u0256\u0257\7E\2\2\u0257\u0258"+
		"\5\22\n\2\u0258\u0259\7F\2\2\u0259\u025a\b\34\1\2\u025a\u029b\3\2\2\2"+
		"\u025b\u025c\7-\2\2\u025c\u025d\7E\2\2\u025d\u025e\5\22\n\2\u025e\u025f"+
		"\7F\2\2\u025f\u0260\b\34\1\2\u0260\u029b\3\2\2\2\u0261\u0262\7/\2\2\u0262"+
		"\u0263\7E\2\2\u0263\u0264\5\22\n\2\u0264\u0265\7F\2\2\u0265\u0266\b\34"+
		"\1\2\u0266\u029b\3\2\2\2\u0267\u0268\7\60\2\2\u0268\u0269\7E\2\2\u0269"+
		"\u026e\5$\23\2\u026a\u026b\7H\2\2\u026b\u026d\5$\23\2\u026c\u026a\3\2"+
		"\2\2\u026d\u0270\3\2\2\2\u026e\u026c\3\2\2\2\u026e\u026f\3\2\2\2\u026f"+
		"\u0271\3\2\2\2\u0270\u026e\3\2\2\2\u0271\u0272\7F\2\2\u0272\u0273\b\34"+
		"\1\2\u0273\u029b\3\2\2\2\u0274\u0275\7\61\2\2\u0275\u0276\7E\2\2\u0276"+
		"\u0277\5\22\n\2\u0277\u0278\7F\2\2\u0278\u0279\b\34\1\2\u0279\u029b\3"+
		"\2\2\2\u027a\u027b\7\62\2\2\u027b\u027c\7E\2\2\u027c\u027d\5\22\n\2\u027d"+
		"\u027e\7F\2\2\u027e\u027f\b\34\1\2\u027f\u029b\3\2\2\2\u0280\u0281\7\63"+
		"\2\2\u0281\u0282\7E\2\2\u0282\u0287\5$\23\2\u0283\u0284\7H\2\2\u0284\u0286"+
		"\5$\23\2\u0285\u0283\3\2\2\2\u0286\u0289\3\2\2\2\u0287\u0285\3\2\2\2\u0287"+
		"\u0288\3\2\2\2\u0288\u028a\3\2\2\2\u0289\u0287\3\2\2\2\u028a\u028b\7F"+
		"\2\2\u028b\u028c\b\34\1\2\u028c\u029b\3\2\2\2\u028d\u028e\7.\2\2\u028e"+
		"\u028f\7E\2\2\u028f\u0294\5$\23\2\u0290\u0291\7H\2\2\u0291\u0293\5$\23"+
		"\2\u0292\u0290\3\2\2\2\u0293\u0296\3\2\2\2\u0294\u0292\3\2\2\2\u0294\u0295"+
		"\3\2\2\2\u0295\u0297\3\2\2\2\u0296\u0294\3\2\2\2\u0297\u0298\7F\2\2\u0298"+
		"\u0299\b\34\1\2\u0299\u029b\3\2\2\2\u029a\u0243\3\2\2\2\u029a\u0249\3"+
		"\2\2\2\u029a\u024f\3\2\2\2\u029a\u0255\3\2\2\2\u029a\u025b\3\2\2\2\u029a"+
		"\u0261\3\2\2\2\u029a\u0267\3\2\2\2\u029a\u0274\3\2\2\2\u029a\u027a\3\2"+
		"\2\2\u029a\u0280\3\2\2\2\u029a\u028d\3\2\2\2\u029b\67\3\2\2\2\u029c\u029d"+
		"\7K\2\2\u029d\u02ac\b\35\1\2\u029e\u029f\7L\2\2\u029f\u02ac\b\35\1\2\u02a0"+
		"\u02a1\7J\2\2\u02a1\u02ac\b\35\1\2\u02a2\u02a3\7N\2\2\u02a3\u02ac\b\35"+
		"\1\2\u02a4\u02a5\7M\2\2\u02a5\u02ac\b\35\1\2\u02a6\u02a7\7E\2\2\u02a7"+
		"\u02a8\5$\23\2\u02a8\u02a9\7F\2\2\u02a9\u02aa\b\35\1\2\u02aa\u02ac\3\2"+
		"\2\2\u02ab\u029c\3\2\2\2\u02ab\u029e\3\2\2\2\u02ab\u02a0\3\2\2\2\u02ab"+
		"\u02a2\3\2\2\2\u02ab\u02a4\3\2\2\2\u02ab\u02a6\3\2\2\2\u02ac9\3\2\2\2"+
		"\u02ad\u02ae\7\65\2\2\u02ae\u02af\7K\2\2\u02af\u02bc\b\36\1\2\u02b0\u02b1"+
		"\7K\2\2\u02b1\u02bc\b\36\1\2\u02b2\u02b3\7\65\2\2\u02b3\u02b4\7L\2\2\u02b4"+
		"\u02bc\b\36\1\2\u02b5\u02b6\7L\2\2\u02b6\u02bc\b\36\1\2\u02b7\u02b8\7"+
		"J\2\2\u02b8\u02bc\b\36\1\2\u02b9\u02ba\7M\2\2\u02ba\u02bc\b\36\1\2\u02bb"+
		"\u02ad\3\2\2\2\u02bb\u02b0\3\2\2\2\u02bb\u02b2\3\2\2\2\u02bb\u02b5\3\2"+
		"\2\2\u02bb\u02b7\3\2\2\2\u02bb\u02b9\3\2\2\2\u02bc;\3\2\2\2\u02bd\u02be"+
		"\7O\2\2\u02be\u02bf\b\37\1\2\u02bf=\3\2\2\2\u02c0\u02c1\7Q\2\2\u02c1\u02c2"+
		"\b \1\2\u02c2?\3\2\2\2.ANP]h\u008d\u0093\u00a4\u00ab\u00ae\u00b7\u00b9"+
		"\u00c6\u00cb\u00d2\u00d5\u00de\u00ed\u00fe\u0109\u010f\u011c\u012c\u013b"+
		"\u014c\u01c1\u01c7\u01d1\u01dc\u01e6\u0202\u0204\u0211\u0213\u0220\u0222"+
		"\u022d\u0241\u026e\u0287\u0294\u029a\u02ab\u02bb";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}