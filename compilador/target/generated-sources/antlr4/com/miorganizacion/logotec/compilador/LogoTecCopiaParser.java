// Generated from LogoTecCopia.g4 by ANTLR 4.4
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
public class LogoTecCopiaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PARA=1, HAZ=2, FIN=3, EJECUTA=4, REPITE=5, APARECETORTUGA=6, AT=7, PONPOS=8, 
		PONXY=9, PONRUMBO=10, RUMBO=11, PONX=12, PONY=13, BAJALAPIZ=14, BL=15, 
		SUBELAPIZ=16, SB=17, CENTRO=18, ESPERA=19, INC=20, SI=21, HASTA=22, MIENTRAS=23, 
		INIC=24, AVANZA=25, AV=26, RETROCEDE=27, RE=28, ATRAS=29, GIRADERECHA=30, 
		GD=31, GIRAIZQUIERDA=32, GI=33, OCULTATORTUGA=34, OT=35, PONCOLORLAPIZ=36, 
		PONCL=37, IGUALESQ=38, YFUNC=39, OFUNC=40, MAYORQ=41, MENORQ=42, DIFERENCIA=43, 
		AZAR=44, PRODUCTO=45, POTENCIA=46, DIVISION=47, SUMA=48, PLUS=49, MINUS=50, 
		MULT=51, DIV=52, EXP=53, AND=54, OR=55, NOT=56, GT=57, LT=58, GEQ=59, 
		LEQ=60, EQ=61, NEQ=62, ASSIGN=63, BRACKET_OPEN=64, BRACKET_CLOSE=65, PAR_OPEN=66, 
		PAR_CLOSE=67, SEMICOLON=68, COMMA=69, DOT=70, BOOLEAN=71, DECIMAL=72, 
		NUMBER=73, STRING=74, ID=75, FIRSTLINE_COMMENT=76, COMMENT_LINE=77, INVALID_COMMENT=78, 
		WS=79, COLOR=80;
	public static final String[] tokenNames = {
		"<INVALID>", "PARA", "HAZ", "FIN", "EJECUTA", "REPITE", "APARECETORTUGA", 
		"AT", "PONPOS", "PONXY", "PONRUMBO", "RUMBO", "PONX", "PONY", "BAJALAPIZ", 
		"BL", "SUBELAPIZ", "SB", "CENTRO", "ESPERA", "INC", "'SI'", "'HASTA'", 
		"'MIENTRAS'", "'INIC'", "'avanza'", "'av'", "'retrocede'", "'re'", "'atras'", 
		"'giraderecha'", "'gd'", "'giraizquierda'", "'gi'", "'ocultatortuga'", 
		"'ot'", "PONCOLORLAPIZ", "PONCL", "IGUALESQ", "'Y'", "'O'", "MAYORQ", 
		"MENORQ", "DIFERENCIA", "AZAR", "PRODUCTO", "POTENCIA", "DIVISION", "SUMA", 
		"'+'", "'-'", "'*'", "'/'", "'^'", "'&&'", "'||'", "'!'", "'>'", "'<'", 
		"'>='", "'<='", "'=='", "'!='", "'='", "BRACKET_OPEN", "BRACKET_CLOSE", 
		"'('", "')'", "';'", "COMMA", "'.'", "BOOLEAN", "DECIMAL", "NUMBER", "STRING", 
		"ID", "FIRSTLINE_COMMENT", "COMMENT_LINE", "INVALID_COMMENT", "WS", "COLOR"
	};
	public static final int
		RULE_program = 0, RULE_proceduresBlock = 1, RULE_procedureDecl = 2, RULE_sentence = 3, 
		RULE_varDecl = 4, RULE_varInit = 5, RULE_callProc = 6, RULE_primaryArg = 7, 
		RULE_expressionSeries = 8, RULE_execBlock = 9, RULE_repiteBlock = 10, 
		RULE_siStmt = 11, RULE_hazHastaStmt = 12, RULE_hazMientrasStmt = 13, RULE_mientrasStmt = 14, 
		RULE_hastaStmt = 15, RULE_turtleCmd = 16, RULE_colorName = 17, RULE_expression = 18, 
		RULE_logicTerm = 19, RULE_logicFactor = 20, RULE_relational = 21, RULE_arithExpr = 22, 
		RULE_term = 23, RULE_factor = 24, RULE_exponent = 25, RULE_unary = 26, 
		RULE_funcCall = 27, RULE_primary = 28, RULE_literalOrString = 29, RULE_cmtFirstLine = 30, 
		RULE_invalidComment = 31;
	public static final String[] ruleNames = {
		"program", "proceduresBlock", "procedureDecl", "sentence", "varDecl", 
		"varInit", "callProc", "primaryArg", "expressionSeries", "execBlock", 
		"repiteBlock", "siStmt", "hazHastaStmt", "hazMientrasStmt", "mientrasStmt", 
		"hastaStmt", "turtleCmd", "colorName", "expression", "logicTerm", "logicFactor", 
		"relational", "arithExpr", "term", "factor", "exponent", "unary", "funcCall", 
		"primary", "literalOrString", "cmtFirstLine", "invalidComment"
	};

	@Override
	public String getGrammarFileName() { return "LogoTecCopia.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

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

	public LogoTecCopiaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public ProgramNode node;
		public ProceduresBlockContext p;
		public ProceduresBlockContext proceduresBlock() {
			return getRuleContext(ProceduresBlockContext.class,0);
		}
		public TerminalNode EOF() { return getToken(LogoTecCopiaParser.EOF, 0); }
		public CmtFirstLineContext cmtFirstLine() {
			return getRuleContext(CmtFirstLineContext.class,0);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			_la = _input.LA(1);
			if (_la==FIRSTLINE_COMMENT) {
				{
				setState(64); cmtFirstLine();
				}
			}

			setState(67); ((ProgramContext)_localctx).p = proceduresBlock();
			setState(68); match(EOF);

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
		public InvalidCommentContext invalidComment(int i) {
			return getRuleContext(InvalidCommentContext.class,i);
		}
		public List<ProcedureDeclContext> procedureDecl() {
			return getRuleContexts(ProcedureDeclContext.class);
		}
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public List<InvalidCommentContext> invalidComment() {
			return getRuleContexts(InvalidCommentContext.class);
		}
		public ProcedureDeclContext procedureDecl(int i) {
			return getRuleContext(ProcedureDeclContext.class,i);
		}
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public ProceduresBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_proceduresBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).enterProceduresBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).exitProceduresBlock(this);
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
			setState(80);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PARA) | (1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONCOLORLAPIZ) | (1L << PONCL))) != 0) || _la==ID || _la==INVALID_COMMENT) {
				{
				setState(78);
				switch (_input.LA(1)) {
				case PARA:
					{
					setState(71); ((ProceduresBlockContext)_localctx).procedureDecl = procedureDecl();
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
					setState(74); ((ProceduresBlockContext)_localctx).sentence = sentence();
					 mainBody.add(((ProceduresBlockContext)_localctx).sentence.node); 
					}
					break;
				case INVALID_COMMENT:
					{
					setState(77); invalidComment();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(82);
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
		public List<TerminalNode> ID() { return getTokens(LogoTecCopiaParser.ID); }
		public TerminalNode PARA() { return getToken(LogoTecCopiaParser.PARA, 0); }
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public List<TerminalNode> BRACKET_CLOSE() { return getTokens(LogoTecCopiaParser.BRACKET_CLOSE); }
		public TerminalNode ID(int i) {
			return getToken(LogoTecCopiaParser.ID, i);
		}
		public List<TerminalNode> BRACKET_OPEN() { return getTokens(LogoTecCopiaParser.BRACKET_OPEN); }
		public TerminalNode BRACKET_OPEN(int i) {
			return getToken(LogoTecCopiaParser.BRACKET_OPEN, i);
		}
		public TerminalNode BRACKET_CLOSE(int i) {
			return getToken(LogoTecCopiaParser.BRACKET_CLOSE, i);
		}
		public TerminalNode FIN() { return getToken(LogoTecCopiaParser.FIN, 0); }
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public ProcedureDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_procedureDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).enterProcedureDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).exitProcedureDecl(this);
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
			setState(85); match(PARA);
			setState(86); ((ProcedureDeclContext)_localctx).procName = match(ID);
			 predeclareProcedure((((ProcedureDeclContext)_localctx).procName!=null?((ProcedureDeclContext)_localctx).procName.getText():null)); 
			setState(88); match(BRACKET_OPEN);
			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(89); ((ProcedureDeclContext)_localctx).param = match(ID);
				 
				          params.add(((ProcedureDeclContext)_localctx).param.getText()); 
				          declareOrAssign((((ProcedureDeclContext)_localctx).param!=null?((ProcedureDeclContext)_localctx).param.getText():null), ValueType.UNKNOWN, null);
				      
				}
				}
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(96); match(BRACKET_CLOSE);
			 declareProcedure((((ProcedureDeclContext)_localctx).procName!=null?((ProcedureDeclContext)_localctx).procName.getText():null), params.size()); 
			setState(98); match(BRACKET_OPEN);
			setState(104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONCOLORLAPIZ) | (1L << PONCL))) != 0) || _la==ID) {
				{
				{
				setState(99); ((ProcedureDeclContext)_localctx).sentence = sentence();
				body.add(((ProcedureDeclContext)_localctx).sentence.node);
				}
				}
				setState(106);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(107); match(BRACKET_CLOSE);
			setState(108); match(FIN);

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
		public HazHastaStmtContext hazHastaStmt;
		public HazMientrasStmtContext hazMientrasStmt;
		public VarDeclContext varDecl;
		public VarInitContext varInit;
		public CallProcContext callProc;
		public TurtleCmdContext turtleCmd;
		public SiStmtContext siStmt;
		public HastaStmtContext hastaStmt;
		public MientrasStmtContext mientrasStmt;
		public RepiteBlockContext repiteBlock;
		public ExecBlockContext execBlock;
		public HazHastaStmtContext hazHastaStmt() {
			return getRuleContext(HazHastaStmtContext.class,0);
		}
		public HastaStmtContext hastaStmt() {
			return getRuleContext(HastaStmtContext.class,0);
		}
		public SiStmtContext siStmt() {
			return getRuleContext(SiStmtContext.class,0);
		}
		public VarInitContext varInit() {
			return getRuleContext(VarInitContext.class,0);
		}
		public HazMientrasStmtContext hazMientrasStmt() {
			return getRuleContext(HazMientrasStmtContext.class,0);
		}
		public VarDeclContext varDecl() {
			return getRuleContext(VarDeclContext.class,0);
		}
		public TurtleCmdContext turtleCmd() {
			return getRuleContext(TurtleCmdContext.class,0);
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
		public CallProcContext callProc() {
			return getRuleContext(CallProcContext.class,0);
		}
		public SentenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sentence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).enterSentence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).exitSentence(this);
		}
	}

	public final SentenceContext sentence() throws RecognitionException {
		SentenceContext _localctx = new SentenceContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_sentence);
		try {
			setState(144);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(111); ((SentenceContext)_localctx).hazHastaStmt = hazHastaStmt();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).hazHastaStmt.node; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(114); ((SentenceContext)_localctx).hazMientrasStmt = hazMientrasStmt();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).hazMientrasStmt.node; 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(117); ((SentenceContext)_localctx).varDecl = varDecl();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).varDecl.node; 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(120); ((SentenceContext)_localctx).varInit = varInit();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).varInit.node; 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(123); ((SentenceContext)_localctx).callProc = callProc();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).callProc.node; 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(126); ((SentenceContext)_localctx).turtleCmd = turtleCmd();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).turtleCmd.node; 
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(129); ((SentenceContext)_localctx).siStmt = siStmt();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).siStmt.node; 
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(132); ((SentenceContext)_localctx).hastaStmt = hastaStmt();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).hastaStmt.node; 
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(135); ((SentenceContext)_localctx).mientrasStmt = mientrasStmt();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).mientrasStmt.node; 
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(138); ((SentenceContext)_localctx).repiteBlock = repiteBlock();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).repiteBlock.node; 
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(141); ((SentenceContext)_localctx).execBlock = execBlock();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).execBlock.node; 
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
		public TerminalNode ID() { return getToken(LogoTecCopiaParser.ID, 0); }
		public LiteralOrStringContext literalOrString() {
			return getRuleContext(LiteralOrStringContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(LogoTecCopiaParser.SEMICOLON, 0); }
		public TerminalNode HAZ() { return getToken(LogoTecCopiaParser.HAZ, 0); }
		public VarDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).enterVarDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).exitVarDecl(this);
		}
	}

	public final VarDeclContext varDecl() throws RecognitionException {
		VarDeclContext _localctx = new VarDeclContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_varDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146); match(HAZ);
			setState(147); ((VarDeclContext)_localctx).name = match(ID);
			setState(148); ((VarDeclContext)_localctx).value = literalOrString();
			setState(150);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(149); match(SEMICOLON);
				}
			}


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
		public TerminalNode ASSIGN() { return getToken(LogoTecCopiaParser.ASSIGN, 0); }
		public TerminalNode SEMICOLON() { return getToken(LogoTecCopiaParser.SEMICOLON, 0); }
		public TerminalNode ID() { return getToken(LogoTecCopiaParser.ID, 0); }
		public TerminalNode INIC() { return getToken(LogoTecCopiaParser.INIC, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VarInitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varInit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).enterVarInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).exitVarInit(this);
		}
	}

	public final VarInitContext varInit() throws RecognitionException {
		VarInitContext _localctx = new VarInitContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_varInit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154); match(INIC);
			setState(155); ((VarInitContext)_localctx).name = match(ID);
			setState(156); match(ASSIGN);
			setState(157); ((VarInitContext)_localctx).expression = expression();
			setState(158); match(SEMICOLON);

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
		public TerminalNode ID() { return getToken(LogoTecCopiaParser.ID, 0); }
		public List<PrimaryArgContext> primaryArg() {
			return getRuleContexts(PrimaryArgContext.class);
		}
		public PrimaryArgContext primaryArg(int i) {
			return getRuleContext(PrimaryArgContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LogoTecCopiaParser.COMMA); }
		public TerminalNode BRACKET_CLOSE() { return getToken(LogoTecCopiaParser.BRACKET_CLOSE, 0); }
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode BRACKET_OPEN() { return getToken(LogoTecCopiaParser.BRACKET_OPEN, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public TerminalNode COMMA(int i) {
			return getToken(LogoTecCopiaParser.COMMA, i);
		}
		public CallProcContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callProc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).enterCallProc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).exitCallProc(this);
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
			setState(161); ((CallProcContext)_localctx).proc = match(ID);
			setState(187);
			switch (_input.LA(1)) {
			case BRACKET_OPEN:
				{
				setState(162); match(BRACKET_OPEN);
				setState(176);
				_la = _input.LA(1);
				if (((((_la - 38)) & ~0x3f) == 0 && ((1L << (_la - 38)) & ((1L << (IGUALESQ - 38)) | (1L << (YFUNC - 38)) | (1L << (OFUNC - 38)) | (1L << (MAYORQ - 38)) | (1L << (MENORQ - 38)) | (1L << (DIFERENCIA - 38)) | (1L << (AZAR - 38)) | (1L << (PRODUCTO - 38)) | (1L << (POTENCIA - 38)) | (1L << (DIVISION - 38)) | (1L << (SUMA - 38)) | (1L << (PLUS - 38)) | (1L << (MINUS - 38)) | (1L << (NOT - 38)) | (1L << (PAR_OPEN - 38)) | (1L << (BOOLEAN - 38)) | (1L << (DECIMAL - 38)) | (1L << (NUMBER - 38)) | (1L << (STRING - 38)) | (1L << (ID - 38)))) != 0)) {
					{
					setState(163); ((CallProcContext)_localctx).expression = expression();
					 args.add(((CallProcContext)_localctx).expression.node); 
					setState(173);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (((((_la - 38)) & ~0x3f) == 0 && ((1L << (_la - 38)) & ((1L << (IGUALESQ - 38)) | (1L << (YFUNC - 38)) | (1L << (OFUNC - 38)) | (1L << (MAYORQ - 38)) | (1L << (MENORQ - 38)) | (1L << (DIFERENCIA - 38)) | (1L << (AZAR - 38)) | (1L << (PRODUCTO - 38)) | (1L << (POTENCIA - 38)) | (1L << (DIVISION - 38)) | (1L << (SUMA - 38)) | (1L << (PLUS - 38)) | (1L << (MINUS - 38)) | (1L << (NOT - 38)) | (1L << (PAR_OPEN - 38)) | (1L << (COMMA - 38)) | (1L << (BOOLEAN - 38)) | (1L << (DECIMAL - 38)) | (1L << (NUMBER - 38)) | (1L << (STRING - 38)) | (1L << (ID - 38)))) != 0)) {
						{
						{
						setState(166);
						_la = _input.LA(1);
						if (_la==COMMA) {
							{
							setState(165); match(COMMA);
							}
						}

						setState(168); ((CallProcContext)_localctx).expression = expression();
						 args.add(((CallProcContext)_localctx).expression.node); 
						}
						}
						setState(175);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(178); match(BRACKET_CLOSE);
				}
				break;
			case EOF:
			case PARA:
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
			case BRACKET_CLOSE:
			case PAR_OPEN:
			case NUMBER:
			case ID:
			case INVALID_COMMENT:
				{
				setState(184);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(179); ((CallProcContext)_localctx).primaryArg = primaryArg();
						 args.add(((CallProcContext)_localctx).primaryArg.node); 
						}
						} 
					}
					setState(186);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
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
		public TerminalNode ID() { return getToken(LogoTecCopiaParser.ID, 0); }
		public TerminalNode PAR_CLOSE() { return getToken(LogoTecCopiaParser.PAR_CLOSE, 0); }
		public TerminalNode PAR_OPEN() { return getToken(LogoTecCopiaParser.PAR_OPEN, 0); }
		public TerminalNode NUMBER() { return getToken(LogoTecCopiaParser.NUMBER, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PrimaryArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryArg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).enterPrimaryArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).exitPrimaryArg(this);
		}
	}

	public final PrimaryArgContext primaryArg() throws RecognitionException {
		PrimaryArgContext _localctx = new PrimaryArgContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_primaryArg);
		try {
			setState(200);
			switch (_input.LA(1)) {
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(191); ((PrimaryArgContext)_localctx).NUMBER = match(NUMBER);
				 ((PrimaryArgContext)_localctx).node =  new ConstNode(Integer.parseInt((((PrimaryArgContext)_localctx).NUMBER!=null?((PrimaryArgContext)_localctx).NUMBER.getText():null))); 
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(193); ((PrimaryArgContext)_localctx).ID = match(ID);
				 ((PrimaryArgContext)_localctx).node =  new VarRefNode((((PrimaryArgContext)_localctx).ID!=null?((PrimaryArgContext)_localctx).ID.getText():null)); 
				}
				break;
			case PAR_OPEN:
				enterOuterAlt(_localctx, 3);
				{
				setState(195); match(PAR_OPEN);
				setState(196); ((PrimaryArgContext)_localctx).expression = expression();
				setState(197); match(PAR_CLOSE);
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
		public List<TerminalNode> COMMA() { return getTokens(LogoTecCopiaParser.COMMA); }
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public TerminalNode COMMA(int i) {
			return getToken(LogoTecCopiaParser.COMMA, i);
		}
		public ExpressionSeriesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionSeries; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).enterExpressionSeries(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).exitExpressionSeries(this);
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
			setState(215);
			_la = _input.LA(1);
			if (((((_la - 38)) & ~0x3f) == 0 && ((1L << (_la - 38)) & ((1L << (IGUALESQ - 38)) | (1L << (YFUNC - 38)) | (1L << (OFUNC - 38)) | (1L << (MAYORQ - 38)) | (1L << (MENORQ - 38)) | (1L << (DIFERENCIA - 38)) | (1L << (AZAR - 38)) | (1L << (PRODUCTO - 38)) | (1L << (POTENCIA - 38)) | (1L << (DIVISION - 38)) | (1L << (SUMA - 38)) | (1L << (PLUS - 38)) | (1L << (MINUS - 38)) | (1L << (NOT - 38)) | (1L << (PAR_OPEN - 38)) | (1L << (BOOLEAN - 38)) | (1L << (DECIMAL - 38)) | (1L << (NUMBER - 38)) | (1L << (STRING - 38)) | (1L << (ID - 38)))) != 0)) {
				{
				setState(202); ((ExpressionSeriesContext)_localctx).expression = expression();
				 _localctx.list.add(((ExpressionSeriesContext)_localctx).expression.node); 
				setState(212);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 38)) & ~0x3f) == 0 && ((1L << (_la - 38)) & ((1L << (IGUALESQ - 38)) | (1L << (YFUNC - 38)) | (1L << (OFUNC - 38)) | (1L << (MAYORQ - 38)) | (1L << (MENORQ - 38)) | (1L << (DIFERENCIA - 38)) | (1L << (AZAR - 38)) | (1L << (PRODUCTO - 38)) | (1L << (POTENCIA - 38)) | (1L << (DIVISION - 38)) | (1L << (SUMA - 38)) | (1L << (PLUS - 38)) | (1L << (MINUS - 38)) | (1L << (NOT - 38)) | (1L << (PAR_OPEN - 38)) | (1L << (COMMA - 38)) | (1L << (BOOLEAN - 38)) | (1L << (DECIMAL - 38)) | (1L << (NUMBER - 38)) | (1L << (STRING - 38)) | (1L << (ID - 38)))) != 0)) {
					{
					{
					setState(205);
					_la = _input.LA(1);
					if (_la==COMMA) {
						{
						setState(204); match(COMMA);
						}
					}

					setState(207); ((ExpressionSeriesContext)_localctx).expression = expression();
					 _localctx.list.add(((ExpressionSeriesContext)_localctx).expression.node); 
					}
					}
					setState(214);
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
		public TerminalNode EJECUTA() { return getToken(LogoTecCopiaParser.EJECUTA, 0); }
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public TerminalNode BRACKET_CLOSE() { return getToken(LogoTecCopiaParser.BRACKET_CLOSE, 0); }
		public TerminalNode BRACKET_OPEN() { return getToken(LogoTecCopiaParser.BRACKET_OPEN, 0); }
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public ExecBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_execBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).enterExecBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).exitExecBlock(this);
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
			setState(217); match(EJECUTA);
			setState(218); match(BRACKET_OPEN);
			setState(224);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONCOLORLAPIZ) | (1L << PONCL))) != 0) || _la==ID) {
				{
				{
				setState(219); ((ExecBlockContext)_localctx).sentence = sentence();
				body.add(((ExecBlockContext)_localctx).sentence.node);
				}
				}
				setState(226);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(227); match(BRACKET_CLOSE);
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
		public TerminalNode REPITE() { return getToken(LogoTecCopiaParser.REPITE, 0); }
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public TerminalNode BRACKET_CLOSE() { return getToken(LogoTecCopiaParser.BRACKET_CLOSE, 0); }
		public TerminalNode BRACKET_OPEN() { return getToken(LogoTecCopiaParser.BRACKET_OPEN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public RepiteBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repiteBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).enterRepiteBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).exitRepiteBlock(this);
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
			setState(230); match(REPITE);
			setState(231); ((RepiteBlockContext)_localctx).times = expression();

			        requireNumericType(((RepiteBlockContext)_localctx).times.node, "REPITE");
			      
			setState(233); match(BRACKET_OPEN);
			setState(239);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONCOLORLAPIZ) | (1L << PONCL))) != 0) || _la==ID) {
				{
				{
				setState(234); ((RepiteBlockContext)_localctx).sentence = sentence();
				body.add(((RepiteBlockContext)_localctx).sentence.node);
				}
				}
				setState(241);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(242); match(BRACKET_CLOSE);
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
		public TerminalNode PAR_CLOSE() { return getToken(LogoTecCopiaParser.PAR_CLOSE, 0); }
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public List<TerminalNode> BRACKET_CLOSE() { return getTokens(LogoTecCopiaParser.BRACKET_CLOSE); }
		public TerminalNode PAR_OPEN() { return getToken(LogoTecCopiaParser.PAR_OPEN, 0); }
		public TerminalNode SI() { return getToken(LogoTecCopiaParser.SI, 0); }
		public List<TerminalNode> BRACKET_OPEN() { return getTokens(LogoTecCopiaParser.BRACKET_OPEN); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode BRACKET_OPEN(int i) {
			return getToken(LogoTecCopiaParser.BRACKET_OPEN, i);
		}
		public TerminalNode BRACKET_CLOSE(int i) {
			return getToken(LogoTecCopiaParser.BRACKET_CLOSE, i);
		}
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public SiStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_siStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).enterSiStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).exitSiStmt(this);
		}
	}

	public final SiStmtContext siStmt() throws RecognitionException {
		SiStmtContext _localctx = new SiStmtContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_siStmt);
		 List<StmtNode> thenBody = new ArrayList<>(); List<StmtNode> elseBody = new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245); match(SI);
			setState(246); match(PAR_OPEN);
			setState(247); ((SiStmtContext)_localctx).cond = expression();
			setState(248); match(PAR_CLOSE);

			        requireBooleanType(((SiStmtContext)_localctx).cond.node, "condición de SI");
			      
			setState(250); match(BRACKET_OPEN);
			setState(256);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONCOLORLAPIZ) | (1L << PONCL))) != 0) || _la==ID) {
				{
				{
				setState(251); ((SiStmtContext)_localctx).sentence = sentence();
				thenBody.add(((SiStmtContext)_localctx).sentence.node);
				}
				}
				setState(258);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(259); match(BRACKET_CLOSE);
			setState(270);
			_la = _input.LA(1);
			if (_la==BRACKET_OPEN) {
				{
				setState(260); match(BRACKET_OPEN);
				setState(266);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONCOLORLAPIZ) | (1L << PONCL))) != 0) || _la==ID) {
					{
					{
					setState(261); ((SiStmtContext)_localctx).sentence = sentence();
					elseBody.add(((SiStmtContext)_localctx).sentence.node);
					}
					}
					setState(268);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(269); match(BRACKET_CLOSE);
				}
			}


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

	public static class HazHastaStmtContext extends ParserRuleContext {
		public StmtNode node;
		public SentenceContext sentence;
		public ExpressionContext cond;
		public TerminalNode DOT() { return getToken(LogoTecCopiaParser.DOT, 0); }
		public TerminalNode PAR_CLOSE() { return getToken(LogoTecCopiaParser.PAR_CLOSE, 0); }
		public TerminalNode HAZ() { return getToken(LogoTecCopiaParser.HAZ, 0); }
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public TerminalNode BRACKET_CLOSE() { return getToken(LogoTecCopiaParser.BRACKET_CLOSE, 0); }
		public TerminalNode PAR_OPEN() { return getToken(LogoTecCopiaParser.PAR_OPEN, 0); }
		public TerminalNode HASTA() { return getToken(LogoTecCopiaParser.HASTA, 0); }
		public TerminalNode BRACKET_OPEN() { return getToken(LogoTecCopiaParser.BRACKET_OPEN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public HazHastaStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hazHastaStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).enterHazHastaStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).exitHazHastaStmt(this);
		}
	}

	public final HazHastaStmtContext hazHastaStmt() throws RecognitionException {
		HazHastaStmtContext _localctx = new HazHastaStmtContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_hazHastaStmt);
		 List<StmtNode> body = new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(274); match(HAZ);
			setState(276);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(275); match(DOT);
				}
			}

			setState(278); match(BRACKET_OPEN);
			setState(284);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONCOLORLAPIZ) | (1L << PONCL))) != 0) || _la==ID) {
				{
				{
				setState(279); ((HazHastaStmtContext)_localctx).sentence = sentence();
				body.add(((HazHastaStmtContext)_localctx).sentence.node);
				}
				}
				setState(286);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(287); match(BRACKET_CLOSE);
			setState(288); match(HASTA);
			setState(289); match(PAR_OPEN);
			setState(290); ((HazHastaStmtContext)_localctx).cond = expression();
			setState(291); match(PAR_CLOSE);

			        requireBooleanType(((HazHastaStmtContext)_localctx).cond.node, "condición de HAZ.HASTA");
			        ((HazHastaStmtContext)_localctx).node =  new DoUntilNode(body, ((HazHastaStmtContext)_localctx).cond.node);
			      
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

	public static class HazMientrasStmtContext extends ParserRuleContext {
		public StmtNode node;
		public SentenceContext sentence;
		public ExpressionContext cond;
		public TerminalNode DOT() { return getToken(LogoTecCopiaParser.DOT, 0); }
		public TerminalNode MIENTRAS() { return getToken(LogoTecCopiaParser.MIENTRAS, 0); }
		public TerminalNode PAR_CLOSE() { return getToken(LogoTecCopiaParser.PAR_CLOSE, 0); }
		public TerminalNode HAZ() { return getToken(LogoTecCopiaParser.HAZ, 0); }
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public TerminalNode BRACKET_CLOSE() { return getToken(LogoTecCopiaParser.BRACKET_CLOSE, 0); }
		public TerminalNode PAR_OPEN() { return getToken(LogoTecCopiaParser.PAR_OPEN, 0); }
		public TerminalNode BRACKET_OPEN() { return getToken(LogoTecCopiaParser.BRACKET_OPEN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public HazMientrasStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hazMientrasStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).enterHazMientrasStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).exitHazMientrasStmt(this);
		}
	}

	public final HazMientrasStmtContext hazMientrasStmt() throws RecognitionException {
		HazMientrasStmtContext _localctx = new HazMientrasStmtContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_hazMientrasStmt);
		 List<StmtNode> body = new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(294); match(HAZ);
			setState(296);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(295); match(DOT);
				}
			}

			setState(298); match(BRACKET_OPEN);
			setState(304);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONCOLORLAPIZ) | (1L << PONCL))) != 0) || _la==ID) {
				{
				{
				setState(299); ((HazMientrasStmtContext)_localctx).sentence = sentence();
				body.add(((HazMientrasStmtContext)_localctx).sentence.node);
				}
				}
				setState(306);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(307); match(BRACKET_CLOSE);
			setState(308); match(MIENTRAS);
			setState(309); match(PAR_OPEN);
			setState(310); ((HazMientrasStmtContext)_localctx).cond = expression();
			setState(311); match(PAR_CLOSE);

			        requireBooleanType(((HazMientrasStmtContext)_localctx).cond.node, "condición de HAZ.MIENTRAS");
			        ((HazMientrasStmtContext)_localctx).node =  new DoWhileNode(body, ((HazMientrasStmtContext)_localctx).cond.node);
			      
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
		public TerminalNode MIENTRAS() { return getToken(LogoTecCopiaParser.MIENTRAS, 0); }
		public TerminalNode PAR_CLOSE() { return getToken(LogoTecCopiaParser.PAR_CLOSE, 0); }
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public TerminalNode BRACKET_CLOSE() { return getToken(LogoTecCopiaParser.BRACKET_CLOSE, 0); }
		public TerminalNode PAR_OPEN() { return getToken(LogoTecCopiaParser.PAR_OPEN, 0); }
		public TerminalNode BRACKET_OPEN() { return getToken(LogoTecCopiaParser.BRACKET_OPEN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public MientrasStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mientrasStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).enterMientrasStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).exitMientrasStmt(this);
		}
	}

	public final MientrasStmtContext mientrasStmt() throws RecognitionException {
		MientrasStmtContext _localctx = new MientrasStmtContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_mientrasStmt);
		 List<StmtNode> body = new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(314); match(MIENTRAS);
			setState(315); match(PAR_OPEN);
			setState(316); ((MientrasStmtContext)_localctx).cond = expression();
			setState(317); match(PAR_CLOSE);

			        requireBooleanType(((MientrasStmtContext)_localctx).cond.node, "condición de MIENTRAS");
			      
			setState(319); match(BRACKET_OPEN);
			setState(325);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONCOLORLAPIZ) | (1L << PONCL))) != 0) || _la==ID) {
				{
				{
				setState(320); ((MientrasStmtContext)_localctx).sentence = sentence();
				body.add(((MientrasStmtContext)_localctx).sentence.node);
				}
				}
				setState(327);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(328); match(BRACKET_CLOSE);

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
		public TerminalNode PAR_CLOSE() { return getToken(LogoTecCopiaParser.PAR_CLOSE, 0); }
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public TerminalNode BRACKET_CLOSE() { return getToken(LogoTecCopiaParser.BRACKET_CLOSE, 0); }
		public TerminalNode PAR_OPEN() { return getToken(LogoTecCopiaParser.PAR_OPEN, 0); }
		public TerminalNode HASTA() { return getToken(LogoTecCopiaParser.HASTA, 0); }
		public TerminalNode BRACKET_OPEN() { return getToken(LogoTecCopiaParser.BRACKET_OPEN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public HastaStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hastaStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).enterHastaStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).exitHastaStmt(this);
		}
	}

	public final HastaStmtContext hastaStmt() throws RecognitionException {
		HastaStmtContext _localctx = new HastaStmtContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_hastaStmt);
		 List<StmtNode> body = new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(331); match(HASTA);
			setState(332); match(PAR_OPEN);
			setState(333); ((HastaStmtContext)_localctx).cond = expression();
			setState(334); match(PAR_CLOSE);

			        requireBooleanType(((HastaStmtContext)_localctx).cond.node, "condición de HASTA");
			      
			setState(336); match(BRACKET_OPEN);
			setState(342);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONCOLORLAPIZ) | (1L << PONCL))) != 0) || _la==ID) {
				{
				{
				setState(337); ((HastaStmtContext)_localctx).sentence = sentence();
				body.add(((HastaStmtContext)_localctx).sentence.node);
				}
				}
				setState(344);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(345); match(BRACKET_CLOSE);

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
		public TerminalNode AT() { return getToken(LogoTecCopiaParser.AT, 0); }
		public TerminalNode CENTRO() { return getToken(LogoTecCopiaParser.CENTRO, 0); }
		public TerminalNode AVANZA() { return getToken(LogoTecCopiaParser.AVANZA, 0); }
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode PONXY() { return getToken(LogoTecCopiaParser.PONXY, 0); }
		public TerminalNode SB() { return getToken(LogoTecCopiaParser.SB, 0); }
		public TerminalNode PONRUMBO() { return getToken(LogoTecCopiaParser.PONRUMBO, 0); }
		public TerminalNode ID() { return getToken(LogoTecCopiaParser.ID, 0); }
		public TerminalNode PONX() { return getToken(LogoTecCopiaParser.PONX, 0); }
		public TerminalNode ESPERA() { return getToken(LogoTecCopiaParser.ESPERA, 0); }
		public TerminalNode INC() { return getToken(LogoTecCopiaParser.INC, 0); }
		public TerminalNode PONY() { return getToken(LogoTecCopiaParser.PONY, 0); }
		public TerminalNode GD() { return getToken(LogoTecCopiaParser.GD, 0); }
		public TerminalNode BAJALAPIZ() { return getToken(LogoTecCopiaParser.BAJALAPIZ, 0); }
		public TerminalNode PONCOLORLAPIZ() { return getToken(LogoTecCopiaParser.PONCOLORLAPIZ, 0); }
		public TerminalNode AV() { return getToken(LogoTecCopiaParser.AV, 0); }
		public TerminalNode APARECETORTUGA() { return getToken(LogoTecCopiaParser.APARECETORTUGA, 0); }
		public TerminalNode SUBELAPIZ() { return getToken(LogoTecCopiaParser.SUBELAPIZ, 0); }
		public TerminalNode GIRADERECHA() { return getToken(LogoTecCopiaParser.GIRADERECHA, 0); }
		public TerminalNode OT() { return getToken(LogoTecCopiaParser.OT, 0); }
		public ColorNameContext colorName() {
			return getRuleContext(ColorNameContext.class,0);
		}
		public TerminalNode OCULTATORTUGA() { return getToken(LogoTecCopiaParser.OCULTATORTUGA, 0); }
		public TerminalNode RETROCEDE() { return getToken(LogoTecCopiaParser.RETROCEDE, 0); }
		public TerminalNode PONPOS() { return getToken(LogoTecCopiaParser.PONPOS, 0); }
		public TerminalNode BRACKET_OPEN() { return getToken(LogoTecCopiaParser.BRACKET_OPEN, 0); }
		public TerminalNode GIRAIZQUIERDA() { return getToken(LogoTecCopiaParser.GIRAIZQUIERDA, 0); }
		public TerminalNode RE() { return getToken(LogoTecCopiaParser.RE, 0); }
		public TerminalNode BRACKET_CLOSE() { return getToken(LogoTecCopiaParser.BRACKET_CLOSE, 0); }
		public TerminalNode BL() { return getToken(LogoTecCopiaParser.BL, 0); }
		public TerminalNode GI() { return getToken(LogoTecCopiaParser.GI, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public TerminalNode RUMBO() { return getToken(LogoTecCopiaParser.RUMBO, 0); }
		public TerminalNode PONCL() { return getToken(LogoTecCopiaParser.PONCL, 0); }
		public ExpressionSeriesContext expressionSeries() {
			return getRuleContext(ExpressionSeriesContext.class,0);
		}
		public TurtleCmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_turtleCmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).enterTurtleCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).exitTurtleCmd(this);
		}
	}

	public final TurtleCmdContext turtleCmd() throws RecognitionException {
		TurtleCmdContext _localctx = new TurtleCmdContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_turtleCmd);
		try {
			setState(459);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(348); match(AVANZA);
				setState(349); ((TurtleCmdContext)_localctx).e = expression();
				 
				        requireNumericType(((TurtleCmdContext)_localctx).e.node, "AVANZA");
				        ((TurtleCmdContext)_localctx).node =  new ForwardNode(((TurtleCmdContext)_localctx).e.node); 
				      
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(352); match(AV);
				setState(353); ((TurtleCmdContext)_localctx).e = expression();
				 
				        requireNumericType(((TurtleCmdContext)_localctx).e.node, "AV");
				        ((TurtleCmdContext)_localctx).node =  new ForwardNode(((TurtleCmdContext)_localctx).e.node); 
				      
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(356); match(RETROCEDE);
				setState(357); ((TurtleCmdContext)_localctx).e = expression();
				 
				        requireNumericType(((TurtleCmdContext)_localctx).e.node, "RETROCEDE");
				        ((TurtleCmdContext)_localctx).node =  new BackwardNode(((TurtleCmdContext)_localctx).e.node); 
				      
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(360); match(RE);
				setState(361); ((TurtleCmdContext)_localctx).e = expression();
				 
				        requireNumericType(((TurtleCmdContext)_localctx).e.node, "RE");
				        ((TurtleCmdContext)_localctx).node =  new BackwardNode(((TurtleCmdContext)_localctx).e.node); 
				      
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(364); match(GIRADERECHA);
				setState(365); ((TurtleCmdContext)_localctx).e = expression();
				 
				        requireNumericType(((TurtleCmdContext)_localctx).e.node, "GIRADERECHA");
				        ((TurtleCmdContext)_localctx).node =  new TurnRightNode(((TurtleCmdContext)_localctx).e.node); 
				      
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(368); match(GD);
				setState(369); ((TurtleCmdContext)_localctx).e = expression();
				 
				        requireNumericType(((TurtleCmdContext)_localctx).e.node, "GD");
				        ((TurtleCmdContext)_localctx).node =  new TurnRightNode(((TurtleCmdContext)_localctx).e.node); 
				      
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(372); match(GIRAIZQUIERDA);
				setState(373); ((TurtleCmdContext)_localctx).e = expression();
				 
				        requireNumericType(((TurtleCmdContext)_localctx).e.node, "GIRAIZQUIERDA");
				        ((TurtleCmdContext)_localctx).node =  new TurnLeftNode(((TurtleCmdContext)_localctx).e.node); 
				      
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(376); match(GI);
				setState(377); ((TurtleCmdContext)_localctx).e = expression();
				 
				        requireNumericType(((TurtleCmdContext)_localctx).e.node, "GI");
				        ((TurtleCmdContext)_localctx).node =  new TurnLeftNode(((TurtleCmdContext)_localctx).e.node); 
				      
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(380); match(OCULTATORTUGA);
				 ((TurtleCmdContext)_localctx).node =  new HideTurtleNode(); 
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(382); match(OT);
				 ((TurtleCmdContext)_localctx).node =  new HideTurtleNode(); 
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(384); match(APARECETORTUGA);
				 ((TurtleCmdContext)_localctx).node =  new ShowTurtleNode(); 
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(386); match(AT);
				 ((TurtleCmdContext)_localctx).node =  new ShowTurtleNode(); 
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(388); match(PONPOS);
				setState(389); match(BRACKET_OPEN);
				setState(390); ((TurtleCmdContext)_localctx).coords = expressionSeries();
				setState(391); match(BRACKET_CLOSE);

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
				setState(394); match(PONXY);
				setState(395); ((TurtleCmdContext)_localctx).x = expression();
				setState(396); ((TurtleCmdContext)_localctx).y = expression();
				 
				        requireNumericType(((TurtleCmdContext)_localctx).x.node, "PONXY (coordenada X)");
				        requireNumericType(((TurtleCmdContext)_localctx).y.node, "PONXY (coordenada Y)");
				        ((TurtleCmdContext)_localctx).node =  new SetPosNode(((TurtleCmdContext)_localctx).x.node, ((TurtleCmdContext)_localctx).y.node, false); 
				      
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(399); match(PONRUMBO);
				setState(400); ((TurtleCmdContext)_localctx).e = expression();
				 
				        requireNumericType(((TurtleCmdContext)_localctx).e.node, "PONRUMBO");
				        ((TurtleCmdContext)_localctx).node =  new SetHeadingNode(((TurtleCmdContext)_localctx).e.node); 
				      
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(403); match(RUMBO);
				 ((TurtleCmdContext)_localctx).node =  new ShowHeadingNode(); 
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(405); match(PONX);
				setState(406); ((TurtleCmdContext)_localctx).e = expression();
				 
				        requireNumericType(((TurtleCmdContext)_localctx).e.node, "PONX");
				        ((TurtleCmdContext)_localctx).node =  new SetXNode(((TurtleCmdContext)_localctx).e.node); 
				      
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(409); match(PONY);
				setState(410); ((TurtleCmdContext)_localctx).e = expression();
				 
				        requireNumericType(((TurtleCmdContext)_localctx).e.node, "PONY");
				        ((TurtleCmdContext)_localctx).node =  new SetYNode(((TurtleCmdContext)_localctx).e.node); 
				      
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(413); match(BAJALAPIZ);
				 ((TurtleCmdContext)_localctx).node =  new PenDownNode(); 
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(415); match(BL);
				 ((TurtleCmdContext)_localctx).node =  new PenDownNode(); 
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 21);
				{
				setState(417); match(SUBELAPIZ);
				 ((TurtleCmdContext)_localctx).node =  new PenUpNode(); 
				}
				break;
			case 22:
				enterOuterAlt(_localctx, 22);
				{
				setState(419); match(SB);
				 ((TurtleCmdContext)_localctx).node =  new PenUpNode(); 
				}
				break;
			case 23:
				enterOuterAlt(_localctx, 23);
				{
				setState(421); match(CENTRO);
				 ((TurtleCmdContext)_localctx).node =  new CenterNode(); 
				}
				break;
			case 24:
				enterOuterAlt(_localctx, 24);
				{
				setState(423); match(ESPERA);
				setState(424); ((TurtleCmdContext)_localctx).e = expression();
				 
				        requireNumericType(((TurtleCmdContext)_localctx).e.node, "ESPERA");
				        ((TurtleCmdContext)_localctx).node =  new WaitNode(((TurtleCmdContext)_localctx).e.node); 
				      
				}
				break;
			case 25:
				enterOuterAlt(_localctx, 25);
				{
				setState(427); match(INC);
				setState(428); match(BRACKET_OPEN);
				setState(429); ((TurtleCmdContext)_localctx).id = match(ID);
				setState(430); match(BRACKET_CLOSE);
				 
				        validateIncrement((((TurtleCmdContext)_localctx).id!=null?((TurtleCmdContext)_localctx).id.getText():null), new ConstNode(1));
				        ((TurtleCmdContext)_localctx).node =  new IncNode(new VarRefNode((((TurtleCmdContext)_localctx).id!=null?((TurtleCmdContext)_localctx).id.getText():null)), new ConstNode(1)); 
				      
				}
				break;
			case 26:
				enterOuterAlt(_localctx, 26);
				{
				setState(432); match(INC);
				setState(433); match(BRACKET_OPEN);
				setState(434); ((TurtleCmdContext)_localctx).id = match(ID);
				setState(435); ((TurtleCmdContext)_localctx).n = expression();
				setState(436); match(BRACKET_CLOSE);
				 
				        validateIncrement((((TurtleCmdContext)_localctx).id!=null?((TurtleCmdContext)_localctx).id.getText():null), ((TurtleCmdContext)_localctx).n.node);
				        ((TurtleCmdContext)_localctx).node =  new IncNode(new VarRefNode((((TurtleCmdContext)_localctx).id!=null?((TurtleCmdContext)_localctx).id.getText():null)), ((TurtleCmdContext)_localctx).n.node); 
				      
				}
				break;
			case 27:
				enterOuterAlt(_localctx, 27);
				{
				setState(439); match(PONCOLORLAPIZ);
				setState(440); match(BRACKET_OPEN);
				setState(441); ((TurtleCmdContext)_localctx).coords = expressionSeries();
				setState(442); match(BRACKET_CLOSE);

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
				setState(445); match(PONCL);
				setState(446); match(BRACKET_OPEN);
				setState(447); ((TurtleCmdContext)_localctx).coords = expressionSeries();
				setState(448); match(BRACKET_CLOSE);

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
				setState(451); match(PONCOLORLAPIZ);
				setState(452); ((TurtleCmdContext)_localctx).c = colorName();
				 ((TurtleCmdContext)_localctx).node =  new SetPenColorNode(((TurtleCmdContext)_localctx).c.value); 
				}
				break;
			case 30:
				enterOuterAlt(_localctx, 30);
				{
				setState(455); match(PONCL);
				setState(456); ((TurtleCmdContext)_localctx).c = colorName();
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
		public TerminalNode COLOR() { return getToken(LogoTecCopiaParser.COLOR, 0); }
		public TerminalNode ID() { return getToken(LogoTecCopiaParser.ID, 0); }
		public ColorNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_colorName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).enterColorName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).exitColorName(this);
		}
	}

	public final ColorNameContext colorName() throws RecognitionException {
		ColorNameContext _localctx = new ColorNameContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_colorName);
		try {
			setState(465);
			switch (_input.LA(1)) {
			case COLOR:
				enterOuterAlt(_localctx, 1);
				{
				setState(461); ((ColorNameContext)_localctx).c = match(COLOR);
				 ((ColorNameContext)_localctx).value =  ((ColorNameContext)_localctx).c.getText().toLowerCase(); 
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(463); ((ColorNameContext)_localctx).id = match(ID);
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
		public List<TerminalNode> OR() { return getTokens(LogoTecCopiaParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(LogoTecCopiaParser.OR, i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(467); ((ExpressionContext)_localctx).t1 = logicTerm();
			 ((ExpressionContext)_localctx).node =  ((ExpressionContext)_localctx).t1.node; ((ExpressionContext)_localctx).val =  ((ExpressionContext)_localctx).t1.val; 
			setState(475);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(469); match(OR);
				setState(470); ((ExpressionContext)_localctx).t2 = logicTerm();
				 ((ExpressionContext)_localctx).node =  new LogicalOrNode(_localctx.node, ((ExpressionContext)_localctx).t2.node); 
				}
				}
				setState(477);
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
		public TerminalNode AND(int i) {
			return getToken(LogoTecCopiaParser.AND, i);
		}
		public LogicFactorContext logicFactor(int i) {
			return getRuleContext(LogicFactorContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(LogoTecCopiaParser.AND); }
		public List<LogicFactorContext> logicFactor() {
			return getRuleContexts(LogicFactorContext.class);
		}
		public LogicTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).enterLogicTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).exitLogicTerm(this);
		}
	}

	public final LogicTermContext logicTerm() throws RecognitionException {
		LogicTermContext _localctx = new LogicTermContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_logicTerm);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(478); ((LogicTermContext)_localctx).f1 = logicFactor();
			 ((LogicTermContext)_localctx).node =  ((LogicTermContext)_localctx).f1.node; ((LogicTermContext)_localctx).val =  ((LogicTermContext)_localctx).f1.val; 
			setState(486);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(480); match(AND);
				setState(481); ((LogicTermContext)_localctx).f2 = logicFactor();
				 ((LogicTermContext)_localctx).node =  new LogicalAndNode(_localctx.node, ((LogicTermContext)_localctx).f2.node); 
				}
				}
				setState(488);
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
		public TerminalNode NOT() { return getToken(LogoTecCopiaParser.NOT, 0); }
		public RelationalContext relational() {
			return getRuleContext(RelationalContext.class,0);
		}
		public LogicFactorContext logicFactor() {
			return getRuleContext(LogicFactorContext.class,0);
		}
		public LogicFactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicFactor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).enterLogicFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).exitLogicFactor(this);
		}
	}

	public final LogicFactorContext logicFactor() throws RecognitionException {
		LogicFactorContext _localctx = new LogicFactorContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_logicFactor);
		try {
			setState(496);
			switch (_input.LA(1)) {
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(489); match(NOT);
				setState(490); ((LogicFactorContext)_localctx).lf = logicFactor();
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
				setState(493); ((LogicFactorContext)_localctx).r = relational();
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
		public List<TerminalNode> NEQ() { return getTokens(LogoTecCopiaParser.NEQ); }
		public TerminalNode EQ(int i) {
			return getToken(LogoTecCopiaParser.EQ, i);
		}
		public List<TerminalNode> LT() { return getTokens(LogoTecCopiaParser.LT); }
		public List<ArithExprContext> arithExpr() {
			return getRuleContexts(ArithExprContext.class);
		}
		public List<TerminalNode> GT() { return getTokens(LogoTecCopiaParser.GT); }
		public TerminalNode NEQ(int i) {
			return getToken(LogoTecCopiaParser.NEQ, i);
		}
		public List<TerminalNode> GEQ() { return getTokens(LogoTecCopiaParser.GEQ); }
		public ArithExprContext arithExpr(int i) {
			return getRuleContext(ArithExprContext.class,i);
		}
		public List<TerminalNode> LEQ() { return getTokens(LogoTecCopiaParser.LEQ); }
		public TerminalNode LEQ(int i) {
			return getToken(LogoTecCopiaParser.LEQ, i);
		}
		public List<TerminalNode> EQ() { return getTokens(LogoTecCopiaParser.EQ); }
		public TerminalNode GT(int i) {
			return getToken(LogoTecCopiaParser.GT, i);
		}
		public TerminalNode LT(int i) {
			return getToken(LogoTecCopiaParser.LT, i);
		}
		public TerminalNode GEQ(int i) {
			return getToken(LogoTecCopiaParser.GEQ, i);
		}
		public RelationalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relational; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).enterRelational(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).exitRelational(this);
		}
	}

	public final RelationalContext relational() throws RecognitionException {
		RelationalContext _localctx = new RelationalContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_relational);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(498); ((RelationalContext)_localctx).a1 = arithExpr();
			 ((RelationalContext)_localctx).node =  ((RelationalContext)_localctx).a1.node; ((RelationalContext)_localctx).val =  ((RelationalContext)_localctx).a1.val; 
			setState(526);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GT) | (1L << LT) | (1L << GEQ) | (1L << LEQ) | (1L << EQ) | (1L << NEQ))) != 0)) {
				{
				setState(524);
				switch (_input.LA(1)) {
				case GT:
					{
					setState(500); match(GT);
					setState(501); ((RelationalContext)_localctx).a2 = arithExpr();
					 ((RelationalContext)_localctx).node =  new GreaterThanNode(_localctx.node, ((RelationalContext)_localctx).a2.node); 
					}
					break;
				case LT:
					{
					setState(504); match(LT);
					setState(505); ((RelationalContext)_localctx).a2 = arithExpr();
					 ((RelationalContext)_localctx).node =  new LessThanNode(_localctx.node, ((RelationalContext)_localctx).a2.node); 
					}
					break;
				case GEQ:
					{
					setState(508); match(GEQ);
					setState(509); ((RelationalContext)_localctx).a2 = arithExpr();
					 ((RelationalContext)_localctx).node =  new GreaterEqualNode(_localctx.node, ((RelationalContext)_localctx).a2.node); 
					}
					break;
				case LEQ:
					{
					setState(512); match(LEQ);
					setState(513); ((RelationalContext)_localctx).a2 = arithExpr();
					 ((RelationalContext)_localctx).node =  new LessEqualNode(_localctx.node, ((RelationalContext)_localctx).a2.node); 
					}
					break;
				case EQ:
					{
					setState(516); match(EQ);
					setState(517); ((RelationalContext)_localctx).a2 = arithExpr();
					 ((RelationalContext)_localctx).node =  new EqualsNode(_localctx.node, ((RelationalContext)_localctx).a2.node); 
					}
					break;
				case NEQ:
					{
					setState(520); match(NEQ);
					setState(521); ((RelationalContext)_localctx).a2 = arithExpr();
					 ((RelationalContext)_localctx).node =  new NotEqualsNode(_localctx.node, ((RelationalContext)_localctx).a2.node); 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(528);
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
		public TerminalNode MINUS(int i) {
			return getToken(LogoTecCopiaParser.MINUS, i);
		}
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<TerminalNode> PLUS() { return getTokens(LogoTecCopiaParser.PLUS); }
		public List<TerminalNode> MINUS() { return getTokens(LogoTecCopiaParser.MINUS); }
		public TerminalNode PLUS(int i) {
			return getToken(LogoTecCopiaParser.PLUS, i);
		}
		public ArithExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).enterArithExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).exitArithExpr(this);
		}
	}

	public final ArithExprContext arithExpr() throws RecognitionException {
		ArithExprContext _localctx = new ArithExprContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_arithExpr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(529); ((ArithExprContext)_localctx).t1 = term();
			 ((ArithExprContext)_localctx).node =  ((ArithExprContext)_localctx).t1.node; ((ArithExprContext)_localctx).val =  ((ArithExprContext)_localctx).t1.val; 
			setState(541);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(539);
					switch (_input.LA(1)) {
					case PLUS:
						{
						setState(531); match(PLUS);
						setState(532); ((ArithExprContext)_localctx).t2 = term();
						 ((ArithExprContext)_localctx).node =  new AdditionNode(_localctx.node, ((ArithExprContext)_localctx).t2.node); 
						}
						break;
					case MINUS:
						{
						setState(535); match(MINUS);
						setState(536); ((ArithExprContext)_localctx).t2 = term();
						 ((ArithExprContext)_localctx).node =  new SubtractionNode(_localctx.node, ((ArithExprContext)_localctx).t2.node);  
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(543);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
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
		public TerminalNode MULT(int i) {
			return getToken(LogoTecCopiaParser.MULT, i);
		}
		public List<FactorContext> factor() {
			return getRuleContexts(FactorContext.class);
		}
		public FactorContext factor(int i) {
			return getRuleContext(FactorContext.class,i);
		}
		public List<TerminalNode> MULT() { return getTokens(LogoTecCopiaParser.MULT); }
		public List<TerminalNode> DIV() { return getTokens(LogoTecCopiaParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(LogoTecCopiaParser.DIV, i);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).exitTerm(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(544); ((TermContext)_localctx).f1 = factor();
			 ((TermContext)_localctx).node =  ((TermContext)_localctx).f1.node; ((TermContext)_localctx).val =  ((TermContext)_localctx).f1.val; 
			setState(556);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==MULT || _la==DIV) {
				{
				setState(554);
				switch (_input.LA(1)) {
				case MULT:
					{
					setState(546); match(MULT);
					setState(547); ((TermContext)_localctx).f2 = factor();
					 ((TermContext)_localctx).node =  new MultiplicationNode(_localctx.node, ((TermContext)_localctx).f2.node);  
					}
					break;
				case DIV:
					{
					setState(550); match(DIV);
					setState(551); ((TermContext)_localctx).f2 = factor();
					 ((TermContext)_localctx).node =  new DivisionNode(_localctx.node, ((TermContext)_localctx).f2.node); 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(558);
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
		public TerminalNode EXP(int i) {
			return getToken(LogoTecCopiaParser.EXP, i);
		}
		public ExponentContext exponent(int i) {
			return getRuleContext(ExponentContext.class,i);
		}
		public List<TerminalNode> EXP() { return getTokens(LogoTecCopiaParser.EXP); }
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).enterFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).exitFactor(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_factor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(559); ((FactorContext)_localctx).e1 = exponent();
			 ((FactorContext)_localctx).node =  ((FactorContext)_localctx).e1.node; ((FactorContext)_localctx).val =  ((FactorContext)_localctx).e1.val; 
			setState(567);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EXP) {
				{
				{
				setState(561); match(EXP);
				setState(562); ((FactorContext)_localctx).e2 = exponent();
				 ((FactorContext)_localctx).node =  new ExponentiationNode(_localctx.node, ((FactorContext)_localctx).e2.node);  
				}
				}
				setState(569);
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
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).enterExponent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).exitExponent(this);
		}
	}

	public final ExponentContext exponent() throws RecognitionException {
		ExponentContext _localctx = new ExponentContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_exponent);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(570); ((ExponentContext)_localctx).unary = unary();
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
		public FuncCallContext funcCall() {
			return getRuleContext(FuncCallContext.class,0);
		}
		public UnaryContext unary() {
			return getRuleContext(UnaryContext.class,0);
		}
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(LogoTecCopiaParser.MINUS, 0); }
		public TerminalNode PLUS() { return getToken(LogoTecCopiaParser.PLUS, 0); }
		public UnaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).enterUnary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).exitUnary(this);
		}
	}

	public final UnaryContext unary() throws RecognitionException {
		UnaryContext _localctx = new UnaryContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_unary);
		try {
			setState(587);
			switch (_input.LA(1)) {
			case MINUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(573); match(MINUS);
				setState(574); ((UnaryContext)_localctx).u = unary();
				 ((UnaryContext)_localctx).node =  new SubtractionNode(new ConstNode(0), ((UnaryContext)_localctx).u.node); ((UnaryContext)_localctx).val =  Value.unknown(); 
				}
				break;
			case PLUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(577); match(PLUS);
				setState(578); ((UnaryContext)_localctx).u = unary();
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
				setState(581); ((UnaryContext)_localctx).funcCall = funcCall();
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
				setState(584); ((UnaryContext)_localctx).primary = primary();
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
		public ExpressionContext e1;
		public ExpressionContext e2;
		public ExpressionContext e;
		public ExpressionContext expression;
		public List<ExpressionContext> rest = new ArrayList<ExpressionContext>();
		public TerminalNode IGUALESQ() { return getToken(LogoTecCopiaParser.IGUALESQ, 0); }
		public TerminalNode DIVISION() { return getToken(LogoTecCopiaParser.DIVISION, 0); }
		public TerminalNode POTENCIA() { return getToken(LogoTecCopiaParser.POTENCIA, 0); }
		public List<TerminalNode> PAR_CLOSE() { return getTokens(LogoTecCopiaParser.PAR_CLOSE); }
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode MENORQ() { return getToken(LogoTecCopiaParser.MENORQ, 0); }
		public List<TerminalNode> PAR_OPEN() { return getTokens(LogoTecCopiaParser.PAR_OPEN); }
		public TerminalNode MAYORQ() { return getToken(LogoTecCopiaParser.MAYORQ, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(LogoTecCopiaParser.COMMA, i);
		}
		public TerminalNode OFUNC() { return getToken(LogoTecCopiaParser.OFUNC, 0); }
		public TerminalNode PRODUCTO() { return getToken(LogoTecCopiaParser.PRODUCTO, 0); }
		public TerminalNode AZAR() { return getToken(LogoTecCopiaParser.AZAR, 0); }
		public TerminalNode PAR_OPEN(int i) {
			return getToken(LogoTecCopiaParser.PAR_OPEN, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LogoTecCopiaParser.COMMA); }
		public TerminalNode YFUNC() { return getToken(LogoTecCopiaParser.YFUNC, 0); }
		public TerminalNode DIFERENCIA() { return getToken(LogoTecCopiaParser.DIFERENCIA, 0); }
		public TerminalNode SUMA() { return getToken(LogoTecCopiaParser.SUMA, 0); }
		public TerminalNode PAR_CLOSE(int i) {
			return getToken(LogoTecCopiaParser.PAR_CLOSE, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public FuncCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).enterFuncCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).exitFuncCall(this);
		}
	}

	public final FuncCallContext funcCall() throws RecognitionException {
		FuncCallContext _localctx = new FuncCallContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_funcCall);
		int _la;
		try {
			setState(708);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(589); match(IGUALESQ);
				setState(590); match(PAR_OPEN);
				setState(591); ((FuncCallContext)_localctx).e1 = expression();
				setState(592); match(COMMA);
				setState(593); ((FuncCallContext)_localctx).e2 = expression();
				setState(594); match(PAR_CLOSE);
				 ((FuncCallContext)_localctx).node =  new EqualsFuncNode(((FuncCallContext)_localctx).e1.node, ((FuncCallContext)_localctx).e2.node); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(597); match(YFUNC);
				setState(598); match(PAR_OPEN);
				setState(599); ((FuncCallContext)_localctx).e1 = expression();
				setState(600); match(PAR_CLOSE);
				setState(601); match(PAR_OPEN);
				setState(602); ((FuncCallContext)_localctx).e2 = expression();
				setState(603); match(PAR_CLOSE);
				 ((FuncCallContext)_localctx).node =  new LogicalAndNode(((FuncCallContext)_localctx).e1.node,((FuncCallContext)_localctx).e2.node); 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(606); match(YFUNC);
				setState(607); match(PAR_OPEN);
				setState(608); ((FuncCallContext)_localctx).e1 = expression();
				setState(609); match(COMMA);
				setState(610); ((FuncCallContext)_localctx).e2 = expression();
				setState(611); match(PAR_CLOSE);
				 ((FuncCallContext)_localctx).node =  new LogicalAndNode(((FuncCallContext)_localctx).e1.node,((FuncCallContext)_localctx).e2.node); 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(614); match(OFUNC);
				setState(615); match(PAR_OPEN);
				setState(616); ((FuncCallContext)_localctx).e1 = expression();
				setState(617); match(PAR_CLOSE);
				setState(618); match(PAR_OPEN);
				setState(619); ((FuncCallContext)_localctx).e2 = expression();
				setState(620); match(PAR_CLOSE);
				 ((FuncCallContext)_localctx).node =  new LogicalOrNode(((FuncCallContext)_localctx).e1.node,((FuncCallContext)_localctx).e2.node); 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(623); match(OFUNC);
				setState(624); match(PAR_OPEN);
				setState(625); ((FuncCallContext)_localctx).e1 = expression();
				setState(626); match(COMMA);
				setState(627); ((FuncCallContext)_localctx).e2 = expression();
				setState(628); match(PAR_CLOSE);
				 ((FuncCallContext)_localctx).node =  new LogicalOrNode(((FuncCallContext)_localctx).e1.node,((FuncCallContext)_localctx).e2.node); 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(631); match(MAYORQ);
				setState(632); match(PAR_OPEN);
				setState(633); ((FuncCallContext)_localctx).e1 = expression();
				setState(634); match(COMMA);
				setState(635); ((FuncCallContext)_localctx).e2 = expression();
				setState(636); match(PAR_CLOSE);
				 
				        requireNumericArgs(((FuncCallContext)_localctx).e1.node, ((FuncCallContext)_localctx).e2.node, "MayorQue?");
				        ((FuncCallContext)_localctx).node =  new GreaterThanNode(((FuncCallContext)_localctx).e1.node,((FuncCallContext)_localctx).e2.node);  
				      
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(639); match(MENORQ);
				setState(640); match(PAR_OPEN);
				setState(641); ((FuncCallContext)_localctx).e1 = expression();
				setState(642); match(COMMA);
				setState(643); ((FuncCallContext)_localctx).e2 = expression();
				setState(644); match(PAR_CLOSE);
				 
				        requireNumericArgs(((FuncCallContext)_localctx).e1.node, ((FuncCallContext)_localctx).e2.node, "MenorQue?");
				        ((FuncCallContext)_localctx).node =  new LessThanNode(((FuncCallContext)_localctx).e1.node,((FuncCallContext)_localctx).e2.node);  
				      
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(647); match(AZAR);
				setState(648); match(PAR_OPEN);
				setState(649); ((FuncCallContext)_localctx).e = expression();
				setState(650); match(PAR_CLOSE);
				 
				        requireNumericType(((FuncCallContext)_localctx).e.node, "AZAR");
				        ((FuncCallContext)_localctx).node =  new RandomNode(((FuncCallContext)_localctx).e.node); 
				      
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(653); match(PRODUCTO);
				setState(654); match(PAR_OPEN);
				setState(655); ((FuncCallContext)_localctx).e1 = expression();
				setState(660);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(656); match(COMMA);
					setState(657); ((FuncCallContext)_localctx).expression = expression();
					((FuncCallContext)_localctx).rest.add(((FuncCallContext)_localctx).expression);
					}
					}
					setState(662);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(663); match(PAR_CLOSE);

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
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(666); match(POTENCIA);
				setState(667); match(PAR_OPEN);
				setState(668); ((FuncCallContext)_localctx).e1 = expression();
				setState(669); match(COMMA);
				setState(670); ((FuncCallContext)_localctx).e2 = expression();
				setState(671); match(PAR_CLOSE);
				 
				        requireNumericArgs(((FuncCallContext)_localctx).e1.node, ((FuncCallContext)_localctx).e2.node, "POTENCIA");
				        ((FuncCallContext)_localctx).node =  new ExponentiationNode(((FuncCallContext)_localctx).e1.node, ((FuncCallContext)_localctx).e2.node); 
				      
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(674); match(DIVISION);
				setState(675); match(PAR_OPEN);
				setState(676); ((FuncCallContext)_localctx).e1 = expression();
				setState(677); match(COMMA);
				setState(678); ((FuncCallContext)_localctx).e2 = expression();
				setState(679); match(PAR_CLOSE);
				 
				        requireNumericArgs(((FuncCallContext)_localctx).e1.node, ((FuncCallContext)_localctx).e2.node, "DIVISION");
				        ((FuncCallContext)_localctx).node =  new DivisionNode(((FuncCallContext)_localctx).e1.node, ((FuncCallContext)_localctx).e2.node); 
				      
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(682); match(SUMA);
				setState(683); match(PAR_OPEN);
				setState(684); ((FuncCallContext)_localctx).e1 = expression();
				setState(689);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(685); match(COMMA);
					setState(686); ((FuncCallContext)_localctx).expression = expression();
					((FuncCallContext)_localctx).rest.add(((FuncCallContext)_localctx).expression);
					}
					}
					setState(691);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(692); match(PAR_CLOSE);

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
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(695); match(DIFERENCIA);
				setState(696); match(PAR_OPEN);
				setState(697); ((FuncCallContext)_localctx).e1 = expression();
				setState(702);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(698); match(COMMA);
					setState(699); ((FuncCallContext)_localctx).expression = expression();
					((FuncCallContext)_localctx).rest.add(((FuncCallContext)_localctx).expression);
					}
					}
					setState(704);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(705); match(PAR_CLOSE);

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
		public TerminalNode DECIMAL() { return getToken(LogoTecCopiaParser.DECIMAL, 0); }
		public TerminalNode ID() { return getToken(LogoTecCopiaParser.ID, 0); }
		public TerminalNode PAR_CLOSE() { return getToken(LogoTecCopiaParser.PAR_CLOSE, 0); }
		public TerminalNode STRING() { return getToken(LogoTecCopiaParser.STRING, 0); }
		public TerminalNode PAR_OPEN() { return getToken(LogoTecCopiaParser.PAR_OPEN, 0); }
		public TerminalNode NUMBER() { return getToken(LogoTecCopiaParser.NUMBER, 0); }
		public TerminalNode BOOLEAN() { return getToken(LogoTecCopiaParser.BOOLEAN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).exitPrimary(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_primary);
		try {
			setState(725);
			switch (_input.LA(1)) {
			case DECIMAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(710); ((PrimaryContext)_localctx).DECIMAL = match(DECIMAL);

				        double v = Double.parseDouble((((PrimaryContext)_localctx).DECIMAL!=null?((PrimaryContext)_localctx).DECIMAL.getText():null).replace(',', '.'));
				        ((PrimaryContext)_localctx).node =  new ConstNode(v);
				        ((PrimaryContext)_localctx).val =  Value.unknown();
				      
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(712); ((PrimaryContext)_localctx).NUMBER = match(NUMBER);
				 
				        int v = Integer.parseInt((((PrimaryContext)_localctx).NUMBER!=null?((PrimaryContext)_localctx).NUMBER.getText():null));
				        ((PrimaryContext)_localctx).node =  new ConstNode(v);
				        ((PrimaryContext)_localctx).val =  Value.integer(v);
				      
				}
				break;
			case BOOLEAN:
				enterOuterAlt(_localctx, 3);
				{
				setState(714); ((PrimaryContext)_localctx).BOOLEAN = match(BOOLEAN);
				 
				        boolean b = Boolean.parseBoolean((((PrimaryContext)_localctx).BOOLEAN!=null?((PrimaryContext)_localctx).BOOLEAN.getText():null));
				        ((PrimaryContext)_localctx).node =  new ConstNode(b);
				        ((PrimaryContext)_localctx).val =  Value.bool(b);
				      
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 4);
				{
				setState(716); ((PrimaryContext)_localctx).ID = match(ID);
				 
				        ((PrimaryContext)_localctx).node =  new VarRefNode((((PrimaryContext)_localctx).ID!=null?((PrimaryContext)_localctx).ID.getText():null));
				        ((PrimaryContext)_localctx).val =  Value.unknown();
				      
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 5);
				{
				setState(718); ((PrimaryContext)_localctx).STRING = match(STRING);

				        String s = (((PrimaryContext)_localctx).STRING!=null?((PrimaryContext)_localctx).STRING.getText():null).substring(1,(((PrimaryContext)_localctx).STRING!=null?((PrimaryContext)_localctx).STRING.getText():null).length()-1);
				        ((PrimaryContext)_localctx).node =  new ConstNode(s);
				        ((PrimaryContext)_localctx).val =  Value.string(s);
				      
				}
				break;
			case PAR_OPEN:
				enterOuterAlt(_localctx, 6);
				{
				setState(720); match(PAR_OPEN);
				setState(721); ((PrimaryContext)_localctx).expression = expression();
				setState(722); match(PAR_CLOSE);
				 
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
		public TerminalNode DECIMAL() { return getToken(LogoTecCopiaParser.DECIMAL, 0); }
		public TerminalNode MINUS() { return getToken(LogoTecCopiaParser.MINUS, 0); }
		public TerminalNode STRING() { return getToken(LogoTecCopiaParser.STRING, 0); }
		public TerminalNode NUMBER() { return getToken(LogoTecCopiaParser.NUMBER, 0); }
		public TerminalNode BOOLEAN() { return getToken(LogoTecCopiaParser.BOOLEAN, 0); }
		public LiteralOrStringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literalOrString; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).enterLiteralOrString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).exitLiteralOrString(this);
		}
	}

	public final LiteralOrStringContext literalOrString() throws RecognitionException {
		LiteralOrStringContext _localctx = new LiteralOrStringContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_literalOrString);
		try {
			setState(741);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(727); match(MINUS);
				setState(728); ((LiteralOrStringContext)_localctx).DECIMAL = match(DECIMAL);

				        double v = -Double.parseDouble((((LiteralOrStringContext)_localctx).DECIMAL!=null?((LiteralOrStringContext)_localctx).DECIMAL.getText():null).replace(',', '.'));
				        ((LiteralOrStringContext)_localctx).node =  new ConstNode(v);
				        ((LiteralOrStringContext)_localctx).jval =  Double.valueOf(v);
				      
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(730); ((LiteralOrStringContext)_localctx).DECIMAL = match(DECIMAL);

				        double v = Double.parseDouble((((LiteralOrStringContext)_localctx).DECIMAL!=null?((LiteralOrStringContext)_localctx).DECIMAL.getText():null).replace(',', '.'));
				        ((LiteralOrStringContext)_localctx).node =  new ConstNode(v);
				        ((LiteralOrStringContext)_localctx).jval =  Double.valueOf(v);
				      
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(732); match(MINUS);
				setState(733); ((LiteralOrStringContext)_localctx).NUMBER = match(NUMBER);

				        int v = -Integer.parseInt((((LiteralOrStringContext)_localctx).NUMBER!=null?((LiteralOrStringContext)_localctx).NUMBER.getText():null));
				        ((LiteralOrStringContext)_localctx).node =  new ConstNode(v);
				        ((LiteralOrStringContext)_localctx).jval =  Integer.valueOf(v);
				      
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(735); ((LiteralOrStringContext)_localctx).NUMBER = match(NUMBER);

				        int v = Integer.parseInt((((LiteralOrStringContext)_localctx).NUMBER!=null?((LiteralOrStringContext)_localctx).NUMBER.getText():null));
				        ((LiteralOrStringContext)_localctx).node =  new ConstNode(v);
				        ((LiteralOrStringContext)_localctx).jval =  Integer.valueOf(v);
				      
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(737); ((LiteralOrStringContext)_localctx).BOOLEAN = match(BOOLEAN);

				        boolean v = Boolean.parseBoolean((((LiteralOrStringContext)_localctx).BOOLEAN!=null?((LiteralOrStringContext)_localctx).BOOLEAN.getText():null));
				        ((LiteralOrStringContext)_localctx).node =  new ConstNode(v);
				        ((LiteralOrStringContext)_localctx).jval =  Boolean.valueOf(v);
				      
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(739); ((LiteralOrStringContext)_localctx).STRING = match(STRING);

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
		public TerminalNode FIRSTLINE_COMMENT() { return getToken(LogoTecCopiaParser.FIRSTLINE_COMMENT, 0); }
		public CmtFirstLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmtFirstLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).enterCmtFirstLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).exitCmtFirstLine(this);
		}
	}

	public final CmtFirstLineContext cmtFirstLine() throws RecognitionException {
		CmtFirstLineContext _localctx = new CmtFirstLineContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_cmtFirstLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(743); match(FIRSTLINE_COMMENT);
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
		public TerminalNode INVALID_COMMENT() { return getToken(LogoTecCopiaParser.INVALID_COMMENT, 0); }
		public InvalidCommentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_invalidComment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).enterInvalidComment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecCopiaListener ) ((LogoTecCopiaListener)listener).exitInvalidComment(this);
		}
	}

	public final InvalidCommentContext invalidComment() throws RecognitionException {
		InvalidCommentContext _localctx = new InvalidCommentContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_invalidComment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(746); match(INVALID_COMMENT);

			        errors.add("Error en línea " + currentLine() + ": los comentarios deben empezar con //");
			      
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3R\u02f0\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\3\2\5\2D\n\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3Q\n\3"+
		"\f\3\16\3T\13\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\7\4^\n\4\f\4\16\4a\13"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4i\n\4\f\4\16\4l\13\4\3\4\3\4\3\4\3\4\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u0093\n"+
		"\5\3\6\3\6\3\6\3\6\5\6\u0099\n\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\b\3\b\3\b\3\b\3\b\5\b\u00a9\n\b\3\b\3\b\3\b\7\b\u00ae\n\b\f\b\16\b\u00b1"+
		"\13\b\5\b\u00b3\n\b\3\b\3\b\3\b\3\b\7\b\u00b9\n\b\f\b\16\b\u00bc\13\b"+
		"\5\b\u00be\n\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00cb\n"+
		"\t\3\n\3\n\3\n\5\n\u00d0\n\n\3\n\3\n\3\n\7\n\u00d5\n\n\f\n\16\n\u00d8"+
		"\13\n\5\n\u00da\n\n\3\13\3\13\3\13\3\13\3\13\7\13\u00e1\n\13\f\13\16\13"+
		"\u00e4\13\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00f0\n\f"+
		"\f\f\16\f\u00f3\13\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7"+
		"\r\u0101\n\r\f\r\16\r\u0104\13\r\3\r\3\r\3\r\3\r\3\r\7\r\u010b\n\r\f\r"+
		"\16\r\u010e\13\r\3\r\5\r\u0111\n\r\3\r\3\r\3\16\3\16\5\16\u0117\n\16\3"+
		"\16\3\16\3\16\3\16\7\16\u011d\n\16\f\16\16\16\u0120\13\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\17\3\17\5\17\u012b\n\17\3\17\3\17\3\17\3\17"+
		"\7\17\u0131\n\17\f\17\16\17\u0134\13\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\7\20\u0146\n\20\f\20"+
		"\16\20\u0149\13\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\7\21\u0157\n\21\f\21\16\21\u015a\13\21\3\21\3\21\3\21\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u01ce"+
		"\n\22\3\23\3\23\3\23\3\23\5\23\u01d4\n\23\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\7\24\u01dc\n\24\f\24\16\24\u01df\13\24\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\7\25\u01e7\n\25\f\25\16\25\u01ea\13\25\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\5\26\u01f3\n\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\7\27\u020f\n\27\f\27\16\27\u0212\13\27\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\7\30\u021e\n\30\f\30\16\30\u0221\13\30"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\7\31\u022d\n\31\f\31"+
		"\16\31\u0230\13\31\3\32\3\32\3\32\3\32\3\32\3\32\7\32\u0238\n\32\f\32"+
		"\16\32\u023b\13\32\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3"+
		"\34\3\34\3\34\3\34\3\34\3\34\3\34\5\34\u024e\n\34\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\7\35\u0295\n\35\f\35\16"+
		"\35\u0298\13\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\7\35"+
		"\u02b2\n\35\f\35\16\35\u02b5\13\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\7\35\u02bf\n\35\f\35\16\35\u02c2\13\35\3\35\3\35\3\35\5\35\u02c7"+
		"\n\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\5\36\u02d8\n\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37"+
		"\3\37\3\37\3\37\3\37\3\37\5\37\u02e8\n\37\3 \3 \3 \3!\3!\3!\3!\2\2\"\2"+
		"\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@\2\2\u033d"+
		"\2C\3\2\2\2\4R\3\2\2\2\6W\3\2\2\2\b\u0092\3\2\2\2\n\u0094\3\2\2\2\f\u009c"+
		"\3\2\2\2\16\u00a3\3\2\2\2\20\u00ca\3\2\2\2\22\u00d9\3\2\2\2\24\u00db\3"+
		"\2\2\2\26\u00e8\3\2\2\2\30\u00f7\3\2\2\2\32\u0114\3\2\2\2\34\u0128\3\2"+
		"\2\2\36\u013c\3\2\2\2 \u014d\3\2\2\2\"\u01cd\3\2\2\2$\u01d3\3\2\2\2&\u01d5"+
		"\3\2\2\2(\u01e0\3\2\2\2*\u01f2\3\2\2\2,\u01f4\3\2\2\2.\u0213\3\2\2\2\60"+
		"\u0222\3\2\2\2\62\u0231\3\2\2\2\64\u023c\3\2\2\2\66\u024d\3\2\2\28\u02c6"+
		"\3\2\2\2:\u02d7\3\2\2\2<\u02e7\3\2\2\2>\u02e9\3\2\2\2@\u02ec\3\2\2\2B"+
		"D\5> \2CB\3\2\2\2CD\3\2\2\2DE\3\2\2\2EF\5\4\3\2FG\7\2\2\3GH\b\2\1\2H\3"+
		"\3\2\2\2IJ\5\6\4\2JK\b\3\1\2KQ\3\2\2\2LM\5\b\5\2MN\b\3\1\2NQ\3\2\2\2O"+
		"Q\5@!\2PI\3\2\2\2PL\3\2\2\2PO\3\2\2\2QT\3\2\2\2RP\3\2\2\2RS\3\2\2\2SU"+
		"\3\2\2\2TR\3\2\2\2UV\b\3\1\2V\5\3\2\2\2WX\7\3\2\2XY\7M\2\2YZ\b\4\1\2Z"+
		"_\7B\2\2[\\\7M\2\2\\^\b\4\1\2][\3\2\2\2^a\3\2\2\2_]\3\2\2\2_`\3\2\2\2"+
		"`b\3\2\2\2a_\3\2\2\2bc\7C\2\2cd\b\4\1\2dj\7B\2\2ef\5\b\5\2fg\b\4\1\2g"+
		"i\3\2\2\2he\3\2\2\2il\3\2\2\2jh\3\2\2\2jk\3\2\2\2km\3\2\2\2lj\3\2\2\2"+
		"mn\7C\2\2no\7\5\2\2op\b\4\1\2p\7\3\2\2\2qr\5\32\16\2rs\b\5\1\2s\u0093"+
		"\3\2\2\2tu\5\34\17\2uv\b\5\1\2v\u0093\3\2\2\2wx\5\n\6\2xy\b\5\1\2y\u0093"+
		"\3\2\2\2z{\5\f\7\2{|\b\5\1\2|\u0093\3\2\2\2}~\5\16\b\2~\177\b\5\1\2\177"+
		"\u0093\3\2\2\2\u0080\u0081\5\"\22\2\u0081\u0082\b\5\1\2\u0082\u0093\3"+
		"\2\2\2\u0083\u0084\5\30\r\2\u0084\u0085\b\5\1\2\u0085\u0093\3\2\2\2\u0086"+
		"\u0087\5 \21\2\u0087\u0088\b\5\1\2\u0088\u0093\3\2\2\2\u0089\u008a\5\36"+
		"\20\2\u008a\u008b\b\5\1\2\u008b\u0093\3\2\2\2\u008c\u008d\5\26\f\2\u008d"+
		"\u008e\b\5\1\2\u008e\u0093\3\2\2\2\u008f\u0090\5\24\13\2\u0090\u0091\b"+
		"\5\1\2\u0091\u0093\3\2\2\2\u0092q\3\2\2\2\u0092t\3\2\2\2\u0092w\3\2\2"+
		"\2\u0092z\3\2\2\2\u0092}\3\2\2\2\u0092\u0080\3\2\2\2\u0092\u0083\3\2\2"+
		"\2\u0092\u0086\3\2\2\2\u0092\u0089\3\2\2\2\u0092\u008c\3\2\2\2\u0092\u008f"+
		"\3\2\2\2\u0093\t\3\2\2\2\u0094\u0095\7\4\2\2\u0095\u0096\7M\2\2\u0096"+
		"\u0098\5<\37\2\u0097\u0099\7F\2\2\u0098\u0097\3\2\2\2\u0098\u0099\3\2"+
		"\2\2\u0099\u009a\3\2\2\2\u009a\u009b\b\6\1\2\u009b\13\3\2\2\2\u009c\u009d"+
		"\7\32\2\2\u009d\u009e\7M\2\2\u009e\u009f\7A\2\2\u009f\u00a0\5&\24\2\u00a0"+
		"\u00a1\7F\2\2\u00a1\u00a2\b\7\1\2\u00a2\r\3\2\2\2\u00a3\u00bd\7M\2\2\u00a4"+
		"\u00b2\7B\2\2\u00a5\u00a6\5&\24\2\u00a6\u00af\b\b\1\2\u00a7\u00a9\7G\2"+
		"\2\u00a8\u00a7\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ab"+
		"\5&\24\2\u00ab\u00ac\b\b\1\2\u00ac\u00ae\3\2\2\2\u00ad\u00a8\3\2\2\2\u00ae"+
		"\u00b1\3\2\2\2\u00af\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b3\3\2"+
		"\2\2\u00b1\u00af\3\2\2\2\u00b2\u00a5\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3"+
		"\u00b4\3\2\2\2\u00b4\u00be\7C\2\2\u00b5\u00b6\5\20\t\2\u00b6\u00b7\b\b"+
		"\1\2\u00b7\u00b9\3\2\2\2\u00b8\u00b5\3\2\2\2\u00b9\u00bc\3\2\2\2\u00ba"+
		"\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00be\3\2\2\2\u00bc\u00ba\3\2"+
		"\2\2\u00bd\u00a4\3\2\2\2\u00bd\u00ba\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf"+
		"\u00c0\b\b\1\2\u00c0\17\3\2\2\2\u00c1\u00c2\7K\2\2\u00c2\u00cb\b\t\1\2"+
		"\u00c3\u00c4\7M\2\2\u00c4\u00cb\b\t\1\2\u00c5\u00c6\7D\2\2\u00c6\u00c7"+
		"\5&\24\2\u00c7\u00c8\7E\2\2\u00c8\u00c9\b\t\1\2\u00c9\u00cb\3\2\2\2\u00ca"+
		"\u00c1\3\2\2\2\u00ca\u00c3\3\2\2\2\u00ca\u00c5\3\2\2\2\u00cb\21\3\2\2"+
		"\2\u00cc\u00cd\5&\24\2\u00cd\u00d6\b\n\1\2\u00ce\u00d0\7G\2\2\u00cf\u00ce"+
		"\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d2\5&\24\2\u00d2"+
		"\u00d3\b\n\1\2\u00d3\u00d5\3\2\2\2\u00d4\u00cf\3\2\2\2\u00d5\u00d8\3\2"+
		"\2\2\u00d6\u00d4\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00da\3\2\2\2\u00d8"+
		"\u00d6\3\2\2\2\u00d9\u00cc\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\23\3\2\2"+
		"\2\u00db\u00dc\7\6\2\2\u00dc\u00e2\7B\2\2\u00dd\u00de\5\b\5\2\u00de\u00df"+
		"\b\13\1\2\u00df\u00e1\3\2\2\2\u00e0\u00dd\3\2\2\2\u00e1\u00e4\3\2\2\2"+
		"\u00e2\u00e0\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3\u00e5\3\2\2\2\u00e4\u00e2"+
		"\3\2\2\2\u00e5\u00e6\7C\2\2\u00e6\u00e7\b\13\1\2\u00e7\25\3\2\2\2\u00e8"+
		"\u00e9\7\7\2\2\u00e9\u00ea\5&\24\2\u00ea\u00eb\b\f\1\2\u00eb\u00f1\7B"+
		"\2\2\u00ec\u00ed\5\b\5\2\u00ed\u00ee\b\f\1\2\u00ee\u00f0\3\2\2\2\u00ef"+
		"\u00ec\3\2\2\2\u00f0\u00f3\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f1\u00f2\3\2"+
		"\2\2\u00f2\u00f4\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f4\u00f5\7C\2\2\u00f5"+
		"\u00f6\b\f\1\2\u00f6\27\3\2\2\2\u00f7\u00f8\7\27\2\2\u00f8\u00f9\7D\2"+
		"\2\u00f9\u00fa\5&\24\2\u00fa\u00fb\7E\2\2\u00fb\u00fc\b\r\1\2\u00fc\u0102"+
		"\7B\2\2\u00fd\u00fe\5\b\5\2\u00fe\u00ff\b\r\1\2\u00ff\u0101\3\2\2\2\u0100"+
		"\u00fd\3\2\2\2\u0101\u0104\3\2\2\2\u0102\u0100\3\2\2\2\u0102\u0103\3\2"+
		"\2\2\u0103\u0105\3\2\2\2\u0104\u0102\3\2\2\2\u0105\u0110\7C\2\2\u0106"+
		"\u010c\7B\2\2\u0107\u0108\5\b\5\2\u0108\u0109\b\r\1\2\u0109\u010b\3\2"+
		"\2\2\u010a\u0107\3\2\2\2\u010b\u010e\3\2\2\2\u010c\u010a\3\2\2\2\u010c"+
		"\u010d\3\2\2\2\u010d\u010f\3\2\2\2\u010e\u010c\3\2\2\2\u010f\u0111\7C"+
		"\2\2\u0110\u0106\3\2\2\2\u0110\u0111\3\2\2\2\u0111\u0112\3\2\2\2\u0112"+
		"\u0113\b\r\1\2\u0113\31\3\2\2\2\u0114\u0116\7\4\2\2\u0115\u0117\7H\2\2"+
		"\u0116\u0115\3\2\2\2\u0116\u0117\3\2\2\2\u0117\u0118\3\2\2\2\u0118\u011e"+
		"\7B\2\2\u0119\u011a\5\b\5\2\u011a\u011b\b\16\1\2\u011b\u011d\3\2\2\2\u011c"+
		"\u0119\3\2\2\2\u011d\u0120\3\2\2\2\u011e\u011c\3\2\2\2\u011e\u011f\3\2"+
		"\2\2\u011f\u0121\3\2\2\2\u0120\u011e\3\2\2\2\u0121\u0122\7C\2\2\u0122"+
		"\u0123\7\30\2\2\u0123\u0124\7D\2\2\u0124\u0125\5&\24\2\u0125\u0126\7E"+
		"\2\2\u0126\u0127\b\16\1\2\u0127\33\3\2\2\2\u0128\u012a\7\4\2\2\u0129\u012b"+
		"\7H\2\2\u012a\u0129\3\2\2\2\u012a\u012b\3\2\2\2\u012b\u012c\3\2\2\2\u012c"+
		"\u0132\7B\2\2\u012d\u012e\5\b\5\2\u012e\u012f\b\17\1\2\u012f\u0131\3\2"+
		"\2\2\u0130\u012d\3\2\2\2\u0131\u0134\3\2\2\2\u0132\u0130\3\2\2\2\u0132"+
		"\u0133\3\2\2\2\u0133\u0135\3\2\2\2\u0134\u0132\3\2\2\2\u0135\u0136\7C"+
		"\2\2\u0136\u0137\7\31\2\2\u0137\u0138\7D\2\2\u0138\u0139\5&\24\2\u0139"+
		"\u013a\7E\2\2\u013a\u013b\b\17\1\2\u013b\35\3\2\2\2\u013c\u013d\7\31\2"+
		"\2\u013d\u013e\7D\2\2\u013e\u013f\5&\24\2\u013f\u0140\7E\2\2\u0140\u0141"+
		"\b\20\1\2\u0141\u0147\7B\2\2\u0142\u0143\5\b\5\2\u0143\u0144\b\20\1\2"+
		"\u0144\u0146\3\2\2\2\u0145\u0142\3\2\2\2\u0146\u0149\3\2\2\2\u0147\u0145"+
		"\3\2\2\2\u0147\u0148\3\2\2\2\u0148\u014a\3\2\2\2\u0149\u0147\3\2\2\2\u014a"+
		"\u014b\7C\2\2\u014b\u014c\b\20\1\2\u014c\37\3\2\2\2\u014d\u014e\7\30\2"+
		"\2\u014e\u014f\7D\2\2\u014f\u0150\5&\24\2\u0150\u0151\7E\2\2\u0151\u0152"+
		"\b\21\1\2\u0152\u0158\7B\2\2\u0153\u0154\5\b\5\2\u0154\u0155\b\21\1\2"+
		"\u0155\u0157\3\2\2\2\u0156\u0153\3\2\2\2\u0157\u015a\3\2\2\2\u0158\u0156"+
		"\3\2\2\2\u0158\u0159\3\2\2\2\u0159\u015b\3\2\2\2\u015a\u0158\3\2\2\2\u015b"+
		"\u015c\7C\2\2\u015c\u015d\b\21\1\2\u015d!\3\2\2\2\u015e\u015f\7\33\2\2"+
		"\u015f\u0160\5&\24\2\u0160\u0161\b\22\1\2\u0161\u01ce\3\2\2\2\u0162\u0163"+
		"\7\34\2\2\u0163\u0164\5&\24\2\u0164\u0165\b\22\1\2\u0165\u01ce\3\2\2\2"+
		"\u0166\u0167\7\35\2\2\u0167\u0168\5&\24\2\u0168\u0169\b\22\1\2\u0169\u01ce"+
		"\3\2\2\2\u016a\u016b\7\36\2\2\u016b\u016c\5&\24\2\u016c\u016d\b\22\1\2"+
		"\u016d\u01ce\3\2\2\2\u016e\u016f\7 \2\2\u016f\u0170\5&\24\2\u0170\u0171"+
		"\b\22\1\2\u0171\u01ce\3\2\2\2\u0172\u0173\7!\2\2\u0173\u0174\5&\24\2\u0174"+
		"\u0175\b\22\1\2\u0175\u01ce\3\2\2\2\u0176\u0177\7\"\2\2\u0177\u0178\5"+
		"&\24\2\u0178\u0179\b\22\1\2\u0179\u01ce\3\2\2\2\u017a\u017b\7#\2\2\u017b"+
		"\u017c\5&\24\2\u017c\u017d\b\22\1\2\u017d\u01ce\3\2\2\2\u017e\u017f\7"+
		"$\2\2\u017f\u01ce\b\22\1\2\u0180\u0181\7%\2\2\u0181\u01ce\b\22\1\2\u0182"+
		"\u0183\7\b\2\2\u0183\u01ce\b\22\1\2\u0184\u0185\7\t\2\2\u0185\u01ce\b"+
		"\22\1\2\u0186\u0187\7\n\2\2\u0187\u0188\7B\2\2\u0188\u0189\5\22\n\2\u0189"+
		"\u018a\7C\2\2\u018a\u018b\b\22\1\2\u018b\u01ce\3\2\2\2\u018c\u018d\7\13"+
		"\2\2\u018d\u018e\5&\24\2\u018e\u018f\5&\24\2\u018f\u0190\b\22\1\2\u0190"+
		"\u01ce\3\2\2\2\u0191\u0192\7\f\2\2\u0192\u0193\5&\24\2\u0193\u0194\b\22"+
		"\1\2\u0194\u01ce\3\2\2\2\u0195\u0196\7\r\2\2\u0196\u01ce\b\22\1\2\u0197"+
		"\u0198\7\16\2\2\u0198\u0199\5&\24\2\u0199\u019a\b\22\1\2\u019a\u01ce\3"+
		"\2\2\2\u019b\u019c\7\17\2\2\u019c\u019d\5&\24\2\u019d\u019e\b\22\1\2\u019e"+
		"\u01ce\3\2\2\2\u019f\u01a0\7\20\2\2\u01a0\u01ce\b\22\1\2\u01a1\u01a2\7"+
		"\21\2\2\u01a2\u01ce\b\22\1\2\u01a3\u01a4\7\22\2\2\u01a4\u01ce\b\22\1\2"+
		"\u01a5\u01a6\7\23\2\2\u01a6\u01ce\b\22\1\2\u01a7\u01a8\7\24\2\2\u01a8"+
		"\u01ce\b\22\1\2\u01a9\u01aa\7\25\2\2\u01aa\u01ab\5&\24\2\u01ab\u01ac\b"+
		"\22\1\2\u01ac\u01ce\3\2\2\2\u01ad\u01ae\7\26\2\2\u01ae\u01af\7B\2\2\u01af"+
		"\u01b0\7M\2\2\u01b0\u01b1\7C\2\2\u01b1\u01ce\b\22\1\2\u01b2\u01b3\7\26"+
		"\2\2\u01b3\u01b4\7B\2\2\u01b4\u01b5\7M\2\2\u01b5\u01b6\5&\24\2\u01b6\u01b7"+
		"\7C\2\2\u01b7\u01b8\b\22\1\2\u01b8\u01ce\3\2\2\2\u01b9\u01ba\7&\2\2\u01ba"+
		"\u01bb\7B\2\2\u01bb\u01bc\5\22\n\2\u01bc\u01bd\7C\2\2\u01bd\u01be\b\22"+
		"\1\2\u01be\u01ce\3\2\2\2\u01bf\u01c0\7\'\2\2\u01c0\u01c1\7B\2\2\u01c1"+
		"\u01c2\5\22\n\2\u01c2\u01c3\7C\2\2\u01c3\u01c4\b\22\1\2\u01c4\u01ce\3"+
		"\2\2\2\u01c5\u01c6\7&\2\2\u01c6\u01c7\5$\23\2\u01c7\u01c8\b\22\1\2\u01c8"+
		"\u01ce\3\2\2\2\u01c9\u01ca\7\'\2\2\u01ca\u01cb\5$\23\2\u01cb\u01cc\b\22"+
		"\1\2\u01cc\u01ce\3\2\2\2\u01cd\u015e\3\2\2\2\u01cd\u0162\3\2\2\2\u01cd"+
		"\u0166\3\2\2\2\u01cd\u016a\3\2\2\2\u01cd\u016e\3\2\2\2\u01cd\u0172\3\2"+
		"\2\2\u01cd\u0176\3\2\2\2\u01cd\u017a\3\2\2\2\u01cd\u017e\3\2\2\2\u01cd"+
		"\u0180\3\2\2\2\u01cd\u0182\3\2\2\2\u01cd\u0184\3\2\2\2\u01cd\u0186\3\2"+
		"\2\2\u01cd\u018c\3\2\2\2\u01cd\u0191\3\2\2\2\u01cd\u0195\3\2\2\2\u01cd"+
		"\u0197\3\2\2\2\u01cd\u019b\3\2\2\2\u01cd\u019f\3\2\2\2\u01cd\u01a1\3\2"+
		"\2\2\u01cd\u01a3\3\2\2\2\u01cd\u01a5\3\2\2\2\u01cd\u01a7\3\2\2\2\u01cd"+
		"\u01a9\3\2\2\2\u01cd\u01ad\3\2\2\2\u01cd\u01b2\3\2\2\2\u01cd\u01b9\3\2"+
		"\2\2\u01cd\u01bf\3\2\2\2\u01cd\u01c5\3\2\2\2\u01cd\u01c9\3\2\2\2\u01ce"+
		"#\3\2\2\2\u01cf\u01d0\7R\2\2\u01d0\u01d4\b\23\1\2\u01d1\u01d2\7M\2\2\u01d2"+
		"\u01d4\b\23\1\2\u01d3\u01cf\3\2\2\2\u01d3\u01d1\3\2\2\2\u01d4%\3\2\2\2"+
		"\u01d5\u01d6\5(\25\2\u01d6\u01dd\b\24\1\2\u01d7\u01d8\79\2\2\u01d8\u01d9"+
		"\5(\25\2\u01d9\u01da\b\24\1\2\u01da\u01dc\3\2\2\2\u01db\u01d7\3\2\2\2"+
		"\u01dc\u01df\3\2\2\2\u01dd\u01db\3\2\2\2\u01dd\u01de\3\2\2\2\u01de\'\3"+
		"\2\2\2\u01df\u01dd\3\2\2\2\u01e0\u01e1\5*\26\2\u01e1\u01e8\b\25\1\2\u01e2"+
		"\u01e3\78\2\2\u01e3\u01e4\5*\26\2\u01e4\u01e5\b\25\1\2\u01e5\u01e7\3\2"+
		"\2\2\u01e6\u01e2\3\2\2\2\u01e7\u01ea\3\2\2\2\u01e8\u01e6\3\2\2\2\u01e8"+
		"\u01e9\3\2\2\2\u01e9)\3\2\2\2\u01ea\u01e8\3\2\2\2\u01eb\u01ec\7:\2\2\u01ec"+
		"\u01ed\5*\26\2\u01ed\u01ee\b\26\1\2\u01ee\u01f3\3\2\2\2\u01ef\u01f0\5"+
		",\27\2\u01f0\u01f1\b\26\1\2\u01f1\u01f3\3\2\2\2\u01f2\u01eb\3\2\2\2\u01f2"+
		"\u01ef\3\2\2\2\u01f3+\3\2\2\2\u01f4\u01f5\5.\30\2\u01f5\u0210\b\27\1\2"+
		"\u01f6\u01f7\7;\2\2\u01f7\u01f8\5.\30\2\u01f8\u01f9\b\27\1\2\u01f9\u020f"+
		"\3\2\2\2\u01fa\u01fb\7<\2\2\u01fb\u01fc\5.\30\2\u01fc\u01fd\b\27\1\2\u01fd"+
		"\u020f\3\2\2\2\u01fe\u01ff\7=\2\2\u01ff\u0200\5.\30\2\u0200\u0201\b\27"+
		"\1\2\u0201\u020f\3\2\2\2\u0202\u0203\7>\2\2\u0203\u0204\5.\30\2\u0204"+
		"\u0205\b\27\1\2\u0205\u020f\3\2\2\2\u0206\u0207\7?\2\2\u0207\u0208\5."+
		"\30\2\u0208\u0209\b\27\1\2\u0209\u020f\3\2\2\2\u020a\u020b\7@\2\2\u020b"+
		"\u020c\5.\30\2\u020c\u020d\b\27\1\2\u020d\u020f\3\2\2\2\u020e\u01f6\3"+
		"\2\2\2\u020e\u01fa\3\2\2\2\u020e\u01fe\3\2\2\2\u020e\u0202\3\2\2\2\u020e"+
		"\u0206\3\2\2\2\u020e\u020a\3\2\2\2\u020f\u0212\3\2\2\2\u0210\u020e\3\2"+
		"\2\2\u0210\u0211\3\2\2\2\u0211-\3\2\2\2\u0212\u0210\3\2\2\2\u0213\u0214"+
		"\5\60\31\2\u0214\u021f\b\30\1\2\u0215\u0216\7\63\2\2\u0216\u0217\5\60"+
		"\31\2\u0217\u0218\b\30\1\2\u0218\u021e\3\2\2\2\u0219\u021a\7\64\2\2\u021a"+
		"\u021b\5\60\31\2\u021b\u021c\b\30\1\2\u021c\u021e\3\2\2\2\u021d\u0215"+
		"\3\2\2\2\u021d\u0219\3\2\2\2\u021e\u0221\3\2\2\2\u021f\u021d\3\2\2\2\u021f"+
		"\u0220\3\2\2\2\u0220/\3\2\2\2\u0221\u021f\3\2\2\2\u0222\u0223\5\62\32"+
		"\2\u0223\u022e\b\31\1\2\u0224\u0225\7\65\2\2\u0225\u0226\5\62\32\2\u0226"+
		"\u0227\b\31\1\2\u0227\u022d\3\2\2\2\u0228\u0229\7\66\2\2\u0229\u022a\5"+
		"\62\32\2\u022a\u022b\b\31\1\2\u022b\u022d\3\2\2\2\u022c\u0224\3\2\2\2"+
		"\u022c\u0228\3\2\2\2\u022d\u0230\3\2\2\2\u022e\u022c\3\2\2\2\u022e\u022f"+
		"\3\2\2\2\u022f\61\3\2\2\2\u0230\u022e\3\2\2\2\u0231\u0232\5\64\33\2\u0232"+
		"\u0239\b\32\1\2\u0233\u0234\7\67\2\2\u0234\u0235\5\64\33\2\u0235\u0236"+
		"\b\32\1\2\u0236\u0238\3\2\2\2\u0237\u0233\3\2\2\2\u0238\u023b\3\2\2\2"+
		"\u0239\u0237\3\2\2\2\u0239\u023a\3\2\2\2\u023a\63\3\2\2\2\u023b\u0239"+
		"\3\2\2\2\u023c\u023d\5\66\34\2\u023d\u023e\b\33\1\2\u023e\65\3\2\2\2\u023f"+
		"\u0240\7\64\2\2\u0240\u0241\5\66\34\2\u0241\u0242\b\34\1\2\u0242\u024e"+
		"\3\2\2\2\u0243\u0244\7\63\2\2\u0244\u0245\5\66\34\2\u0245\u0246\b\34\1"+
		"\2\u0246\u024e\3\2\2\2\u0247\u0248\58\35\2\u0248\u0249\b\34\1\2\u0249"+
		"\u024e\3\2\2\2\u024a\u024b\5:\36\2\u024b\u024c\b\34\1\2\u024c\u024e\3"+
		"\2\2\2\u024d\u023f\3\2\2\2\u024d\u0243\3\2\2\2\u024d\u0247\3\2\2\2\u024d"+
		"\u024a\3\2\2\2\u024e\67\3\2\2\2\u024f\u0250\7(\2\2\u0250\u0251\7D\2\2"+
		"\u0251\u0252\5&\24\2\u0252\u0253\7G\2\2\u0253\u0254\5&\24\2\u0254\u0255"+
		"\7E\2\2\u0255\u0256\b\35\1\2\u0256\u02c7\3\2\2\2\u0257\u0258\7)\2\2\u0258"+
		"\u0259\7D\2\2\u0259\u025a\5&\24\2\u025a\u025b\7E\2\2\u025b\u025c\7D\2"+
		"\2\u025c\u025d\5&\24\2\u025d\u025e\7E\2\2\u025e\u025f\b\35\1\2\u025f\u02c7"+
		"\3\2\2\2\u0260\u0261\7)\2\2\u0261\u0262\7D\2\2\u0262\u0263\5&\24\2\u0263"+
		"\u0264\7G\2\2\u0264\u0265\5&\24\2\u0265\u0266\7E\2\2\u0266\u0267\b\35"+
		"\1\2\u0267\u02c7\3\2\2\2\u0268\u0269\7*\2\2\u0269\u026a\7D\2\2\u026a\u026b"+
		"\5&\24\2\u026b\u026c\7E\2\2\u026c\u026d\7D\2\2\u026d\u026e\5&\24\2\u026e"+
		"\u026f\7E\2\2\u026f\u0270\b\35\1\2\u0270\u02c7\3\2\2\2\u0271\u0272\7*"+
		"\2\2\u0272\u0273\7D\2\2\u0273\u0274\5&\24\2\u0274\u0275\7G\2\2\u0275\u0276"+
		"\5&\24\2\u0276\u0277\7E\2\2\u0277\u0278\b\35\1\2\u0278\u02c7\3\2\2\2\u0279"+
		"\u027a\7+\2\2\u027a\u027b\7D\2\2\u027b\u027c\5&\24\2\u027c\u027d\7G\2"+
		"\2\u027d\u027e\5&\24\2\u027e\u027f\7E\2\2\u027f\u0280\b\35\1\2\u0280\u02c7"+
		"\3\2\2\2\u0281\u0282\7,\2\2\u0282\u0283\7D\2\2\u0283\u0284\5&\24\2\u0284"+
		"\u0285\7G\2\2\u0285\u0286\5&\24\2\u0286\u0287\7E\2\2\u0287\u0288\b\35"+
		"\1\2\u0288\u02c7\3\2\2\2\u0289\u028a\7.\2\2\u028a\u028b\7D\2\2\u028b\u028c"+
		"\5&\24\2\u028c\u028d\7E\2\2\u028d\u028e\b\35\1\2\u028e\u02c7\3\2\2\2\u028f"+
		"\u0290\7/\2\2\u0290\u0291\7D\2\2\u0291\u0296\5&\24\2\u0292\u0293\7G\2"+
		"\2\u0293\u0295\5&\24\2\u0294\u0292\3\2\2\2\u0295\u0298\3\2\2\2\u0296\u0294"+
		"\3\2\2\2\u0296\u0297\3\2\2\2\u0297\u0299\3\2\2\2\u0298\u0296\3\2\2\2\u0299"+
		"\u029a\7E\2\2\u029a\u029b\b\35\1\2\u029b\u02c7\3\2\2\2\u029c\u029d\7\60"+
		"\2\2\u029d\u029e\7D\2\2\u029e\u029f\5&\24\2\u029f\u02a0\7G\2\2\u02a0\u02a1"+
		"\5&\24\2\u02a1\u02a2\7E\2\2\u02a2\u02a3\b\35\1\2\u02a3\u02c7\3\2\2\2\u02a4"+
		"\u02a5\7\61\2\2\u02a5\u02a6\7D\2\2\u02a6\u02a7\5&\24\2\u02a7\u02a8\7G"+
		"\2\2\u02a8\u02a9\5&\24\2\u02a9\u02aa\7E\2\2\u02aa\u02ab\b\35\1\2\u02ab"+
		"\u02c7\3\2\2\2\u02ac\u02ad\7\62\2\2\u02ad\u02ae\7D\2\2\u02ae\u02b3\5&"+
		"\24\2\u02af\u02b0\7G\2\2\u02b0\u02b2\5&\24\2\u02b1\u02af\3\2\2\2\u02b2"+
		"\u02b5\3\2\2\2\u02b3\u02b1\3\2\2\2\u02b3\u02b4\3\2\2\2\u02b4\u02b6\3\2"+
		"\2\2\u02b5\u02b3\3\2\2\2\u02b6\u02b7\7E\2\2\u02b7\u02b8\b\35\1\2\u02b8"+
		"\u02c7\3\2\2\2\u02b9\u02ba\7-\2\2\u02ba\u02bb\7D\2\2\u02bb\u02c0\5&\24"+
		"\2\u02bc\u02bd\7G\2\2\u02bd\u02bf\5&\24\2\u02be\u02bc\3\2\2\2\u02bf\u02c2"+
		"\3\2\2\2\u02c0\u02be\3\2\2\2\u02c0\u02c1\3\2\2\2\u02c1\u02c3\3\2\2\2\u02c2"+
		"\u02c0\3\2\2\2\u02c3\u02c4\7E\2\2\u02c4\u02c5\b\35\1\2\u02c5\u02c7\3\2"+
		"\2\2\u02c6\u024f\3\2\2\2\u02c6\u0257\3\2\2\2\u02c6\u0260\3\2\2\2\u02c6"+
		"\u0268\3\2\2\2\u02c6\u0271\3\2\2\2\u02c6\u0279\3\2\2\2\u02c6\u0281\3\2"+
		"\2\2\u02c6\u0289\3\2\2\2\u02c6\u028f\3\2\2\2\u02c6\u029c\3\2\2\2\u02c6"+
		"\u02a4\3\2\2\2\u02c6\u02ac\3\2\2\2\u02c6\u02b9\3\2\2\2\u02c79\3\2\2\2"+
		"\u02c8\u02c9\7J\2\2\u02c9\u02d8\b\36\1\2\u02ca\u02cb\7K\2\2\u02cb\u02d8"+
		"\b\36\1\2\u02cc\u02cd\7I\2\2\u02cd\u02d8\b\36\1\2\u02ce\u02cf\7M\2\2\u02cf"+
		"\u02d8\b\36\1\2\u02d0\u02d1\7L\2\2\u02d1\u02d8\b\36\1\2\u02d2\u02d3\7"+
		"D\2\2\u02d3\u02d4\5&\24\2\u02d4\u02d5\7E\2\2\u02d5\u02d6\b\36\1\2\u02d6"+
		"\u02d8\3\2\2\2\u02d7\u02c8\3\2\2\2\u02d7\u02ca\3\2\2\2\u02d7\u02cc\3\2"+
		"\2\2\u02d7\u02ce\3\2\2\2\u02d7\u02d0\3\2\2\2\u02d7\u02d2\3\2\2\2\u02d8"+
		";\3\2\2\2\u02d9\u02da\7\64\2\2\u02da\u02db\7J\2\2\u02db\u02e8\b\37\1\2"+
		"\u02dc\u02dd\7J\2\2\u02dd\u02e8\b\37\1\2\u02de\u02df\7\64\2\2\u02df\u02e0"+
		"\7K\2\2\u02e0\u02e8\b\37\1\2\u02e1\u02e2\7K\2\2\u02e2\u02e8\b\37\1\2\u02e3"+
		"\u02e4\7I\2\2\u02e4\u02e8\b\37\1\2\u02e5\u02e6\7L\2\2\u02e6\u02e8\b\37"+
		"\1\2\u02e7\u02d9\3\2\2\2\u02e7\u02dc\3\2\2\2\u02e7\u02de\3\2\2\2\u02e7"+
		"\u02e1\3\2\2\2\u02e7\u02e3\3\2\2\2\u02e7\u02e5\3\2\2\2\u02e8=\3\2\2\2"+
		"\u02e9\u02ea\7N\2\2\u02ea\u02eb\b \1\2\u02eb?\3\2\2\2\u02ec\u02ed\7P\2"+
		"\2\u02ed\u02ee\b!\1\2\u02eeA\3\2\2\2\60CPR_j\u0092\u0098\u00a8\u00af\u00b2"+
		"\u00ba\u00bd\u00ca\u00cf\u00d6\u00d9\u00e2\u00f1\u0102\u010c\u0110\u0116"+
		"\u011e\u012a\u0132\u0147\u0158\u01cd\u01d3\u01dd\u01e8\u01f2\u020e\u0210"+
		"\u021d\u021f\u022c\u022e\u0239\u024d\u0296\u02b3\u02c0\u02c6\u02d7\u02e7";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}