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
		PONCL=37, IGUALESQ=38, YFUNC=39, OFUNC=40, MAYORQ=41, MENORQ=42, DIFERENCIA=43, 
		AZAR=44, PRODUCTO=45, POTENCIA=46, DIVISION=47, SUMA=48, PLUS=49, MINUS=50, 
		MULT=51, DIV=52, EXP=53, AND=54, OR=55, NOT=56, GT=57, LT=58, GEQ=59, 
		LEQ=60, EQ=61, NEQ=62, ASSIGN=63, BRACKET_OPEN=64, BRACKET_CLOSE=65, PAR_OPEN=66, 
		PAR_CLOSE=67, SEMICOLON=68, COMMA=69, DOT=70, BOOLEAN=71, DECIMAL=72, 
		NUMBER=73, STRING=74, ID=75, FIRSTLINE_COMMENT=76, COMMENT_LINE=77, WS=78, 
		COLOR=79;
	public static final int
		RULE_program = 0, RULE_proceduresBlock = 1, RULE_procedureDecl = 2, RULE_sentence = 3, 
		RULE_varDecl = 4, RULE_varInit = 5, RULE_callProc = 6, RULE_primaryArg = 7, 
		RULE_expressionSeries = 8, RULE_execBlock = 9, RULE_repiteBlock = 10, 
		RULE_siStmt = 11, RULE_hazHastaStmt = 12, RULE_hazMientrasStmt = 13, RULE_mientrasStmt = 14, 
		RULE_hastaStmt = 15, RULE_turtleCmd = 16, RULE_colorName = 17, RULE_expression = 18, 
		RULE_logicTerm = 19, RULE_logicFactor = 20, RULE_relational = 21, RULE_arithExpr = 22, 
		RULE_term = 23, RULE_factor = 24, RULE_exponent = 25, RULE_unary = 26, 
		RULE_funcCall = 27, RULE_primary = 28, RULE_literalOrString = 29, RULE_cmtFirstLine = 30;
	public static final String[] ruleNames = {
		"program", "proceduresBlock", "procedureDecl", "sentence", "varDecl", 
		"varInit", "callProc", "primaryArg", "expressionSeries", "execBlock", 
		"repiteBlock", "siStmt", "hazHastaStmt", "hazMientrasStmt", "mientrasStmt", 
		"hastaStmt", "turtleCmd", "colorName", "expression", "logicTerm", "logicFactor", 
		"relational", "arithExpr", "term", "factor", "exponent", "unary", "funcCall", 
		"primary", "literalOrString", "cmtFirstLine"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, "'SI'", "'HASTA'", 
		"'MIENTRAS'", "'INIC'", "'avanza'", "'av'", "'retrocede'", "'re'", "'atras'", 
		"'giraderecha'", "'gd'", "'giraizquierda'", "'gi'", "'ocultatortuga'", 
		"'ot'", null, null, null, "'Y'", "'O'", null, null, null, null, null, 
		null, null, null, "'+'", "'-'", "'*'", "'/'", "'^'", "'&&'", "'||'", "'!'", 
		"'>'", "'<'", "'>='", "'<='", "'=='", "'!='", "'='", null, null, "'('", 
		"')'", "';'", null, "'.'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "PARA", "HAZ", "FIN", "EJECUTA", "REPITE", "APARECETORTUGA", "AT", 
		"PONPOS", "PONXY", "PONRUMBO", "RUMBO", "PONX", "PONY", "BAJALAPIZ", "BL", 
		"SUBELAPIZ", "SB", "CENTRO", "ESPERA", "INC", "SI", "HASTA", "MIENTRAS", 
		"INIC", "AVANZA", "AV", "RETROCEDE", "RE", "ATRAS", "GIRADERECHA", "GD", 
		"GIRAIZQUIERDA", "GI", "OCULTATORTUGA", "OT", "PONCOLORLAPIZ", "PONCL", 
		"IGUALESQ", "YFUNC", "OFUNC", "MAYORQ", "MENORQ", "DIFERENCIA", "AZAR", 
		"PRODUCTO", "POTENCIA", "DIVISION", "SUMA", "PLUS", "MINUS", "MULT", "DIV", 
		"EXP", "AND", "OR", "NOT", "GT", "LT", "GEQ", "LEQ", "EQ", "NEQ", "ASSIGN", 
		"BRACKET_OPEN", "BRACKET_CLOSE", "PAR_OPEN", "PAR_CLOSE", "SEMICOLON", 
		"COMMA", "DOT", "BOOLEAN", "DECIMAL", "NUMBER", "STRING", "ID", "FIRSTLINE_COMMENT", 
		"COMMENT_LINE", "WS", "COLOR"
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


	    Map<String, Symbol> symbols = new HashMap<>(); 
	    List<String> errors = new ArrayList<>();
	    boolean firstLineHasComment = false;
	    boolean atLeastOneVariable = false;

	    // Nuevo: llamadas a procedimientos pendientes para validar al final
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

	    void ensureProgramConstraints() {
	        // Ya no fallar por falta de comentario en la primera línea.
	        if (!atLeastOneVariable) {
	            errors.add("Error: el programa debe definir al menos una variable con 'Haz' o 'INIC'.");
	        }
	        // Validar llamadas pendientes a procedimientos (soporta referencias adelantadas)
	        for (PendingCall pc : pendingCalls) {
	            Symbol s = symbols.get(pc.name);
	            if (s == null) {
	                errors.add("Error semántico: procedimiento '" + pc.name + "' no está definido.");
	            } else if (s.type != ValueType.PROCEDURE) {
	                errors.add("Error semántico: '" + pc.name + "' no es un procedimiento.");
	            } else {
	                int expectedParams = (Integer) s.value;
	                if (pc.argCount != expectedParams) {
	                    errors.add("Error semántico: procedimiento '" + pc.name + "' espera " + expectedParams + " parámetros, pero se llamó con " + pc.argCount + ".");
	                }
	            }
	        }
	        pendingCalls.clear();

	        if (!errors.isEmpty()) {
	            throw new RuntimeException(String.join("\n", errors));
	        }
	    }

	    boolean isValidVarName(String id) {
	        if (id.length() == 0 || id.length() > 10) return false;
	        if (!Character.isLowerCase(id.charAt(0))) return false;
	        for (char c : id.toCharArray()) {
	            if (!(Character.isLetterOrDigit(c) || c=='_' || c=='&' || c=='@')) return false;
	        }
	        return true;
	    }

	    void declareOrAssign(String name, ValueType type, Object value) {
	        if (!isValidVarName(name)) {
	            errors.add("Error léxico: identificador inválido '" + name + "'.");
	            return;
	        }
	        Symbol s = symbols.get(name);
	        if (s == null) {
	            symbols.put(name, new Symbol(name, type, value));
	            atLeastOneVariable = true;
	        } else {
	            if (s.type != type) {
	                errors.add("Error semántico: intento de asignar " + type + " a variable '" + name + "' de tipo " + s.type + ".");
	            } else {
	                s.value = value;
	            }
	        }
	    }

	    void predeclareProcedure(String name) {
	        Symbol s = symbols.get(name);
	        if (s == null) {
	            symbols.put(name, new Symbol(name, ValueType.PROCEDURE, null));
	        } else if (s.type == ValueType.PROCEDURE && s.value == null) {
	            errors.add("Error semántico: procedimiento '" + name + "' ya está en proceso de definición.");
	        } else {
	            errors.add("Error semántico: símbolo '" + name + "' ya está definido y no puede volver a declararse como procedimiento.");
	        }
	    }

	    void declareProcedure(String name, int paramCount) {
	        Symbol s = symbols.get(name);
	        if (s == null) {
	            symbols.put(name, new Symbol(name, ValueType.PROCEDURE, paramCount));
	        } else if (s.type != ValueType.PROCEDURE) {
	            errors.add("Error semántico: símbolo '" + name + "' ya está definido y no es un procedimiento.");
	        } else if (s.value == null) {
	            s.value = paramCount;
	        } else {
	            int expectedParams = (Integer) s.value;
	            if (expectedParams != paramCount) {
	                errors.add("Error semántico: procedimiento '" + name + "' ya está definido con " + expectedParams + " parámetros.");
	            }
	        }
	    }

	    void validateProcedureCall(String name, int argCount) {
	        Symbol s = symbols.get(name);
	        // Si no existe aún o está predeclarado sin aridad, difiere la validación
	        if (s == null || (s.type == ValueType.PROCEDURE && s.value == null)) {
	            pendingCalls.add(new PendingCall(name, argCount, getCurrentToken()!=null ? getCurrentToken().getLine() : -1));
	            return;
	        }
	        if (s.type != ValueType.PROCEDURE) {
	            errors.add("Error semántico: '" + name + "' no es un procedimiento.");
	        } else {
	            int expectedParams = (Integer) s.value;
	            if (argCount != expectedParams) {
	                errors.add("Error semántico: procedimiento '" + name + "' espera " + expectedParams + " parámetros, pero se llamó con " + argCount + ".");
	            }
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
			setState(77);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PARA) | (1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONCOLORLAPIZ) | (1L << PONCL))) != 0) || _la==ID) {
				{
				setState(75);
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
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(79);
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
			setState(82);
			match(PARA);
			setState(83);
			((ProcedureDeclContext)_localctx).procName = match(ID);
			 predeclareProcedure((((ProcedureDeclContext)_localctx).procName!=null?((ProcedureDeclContext)_localctx).procName.getText():null)); 
			setState(85);
			match(BRACKET_OPEN);
			setState(90);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(86);
				((ProcedureDeclContext)_localctx).param = match(ID);
				 
				      params.add(((ProcedureDeclContext)_localctx).param.getText()); 
				      declareOrAssign((((ProcedureDeclContext)_localctx).param!=null?((ProcedureDeclContext)_localctx).param.getText():null), ValueType.UNKNOWN, null);
				  
				}
				}
				setState(92);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(93);
			match(BRACKET_CLOSE);
			 declareProcedure((((ProcedureDeclContext)_localctx).procName!=null?((ProcedureDeclContext)_localctx).procName.getText():null), params.size()); 
			setState(95);
			match(BRACKET_OPEN);
			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONCOLORLAPIZ) | (1L << PONCL))) != 0) || _la==ID) {
				{
				{
				setState(96);
				((ProcedureDeclContext)_localctx).sentence = sentence();
				body.add(((ProcedureDeclContext)_localctx).sentence.node);
				}
				}
				setState(103);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(104);
			match(BRACKET_CLOSE);
			setState(105);
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
		public HazMientrasStmtContext hazMientrasStmt() {
			return getRuleContext(HazMientrasStmtContext.class,0);
		}
		public VarDeclContext varDecl() {
			return getRuleContext(VarDeclContext.class,0);
		}
		public VarInitContext varInit() {
			return getRuleContext(VarInitContext.class,0);
		}
		public CallProcContext callProc() {
			return getRuleContext(CallProcContext.class,0);
		}
		public TurtleCmdContext turtleCmd() {
			return getRuleContext(TurtleCmdContext.class,0);
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
			setState(141);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(108);
				((SentenceContext)_localctx).hazHastaStmt = hazHastaStmt();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).hazHastaStmt.node; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(111);
				((SentenceContext)_localctx).hazMientrasStmt = hazMientrasStmt();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).hazMientrasStmt.node; 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(114);
				((SentenceContext)_localctx).varDecl = varDecl();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).varDecl.node; 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(117);
				((SentenceContext)_localctx).varInit = varInit();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).varInit.node; 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(120);
				((SentenceContext)_localctx).callProc = callProc();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).callProc.node; 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(123);
				((SentenceContext)_localctx).turtleCmd = turtleCmd();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).turtleCmd.node; 
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(126);
				((SentenceContext)_localctx).siStmt = siStmt();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).siStmt.node; 
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(129);
				((SentenceContext)_localctx).hastaStmt = hastaStmt();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).hastaStmt.node; 
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(132);
				((SentenceContext)_localctx).mientrasStmt = mientrasStmt();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).mientrasStmt.node; 
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(135);
				((SentenceContext)_localctx).repiteBlock = repiteBlock();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).repiteBlock.node; 
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(138);
				((SentenceContext)_localctx).execBlock = execBlock();
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
			setState(143);
			match(HAZ);
			setState(144);
			((VarDeclContext)_localctx).name = match(ID);
			setState(145);
			((VarDeclContext)_localctx).value = literalOrString();
			setState(147);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(146);
				match(SEMICOLON);
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
			setState(151);
			match(INIC);
			setState(152);
			((VarInitContext)_localctx).name = match(ID);
			setState(153);
			match(ASSIGN);
			setState(154);
			((VarInitContext)_localctx).expression = expression();
			setState(155);
			match(SEMICOLON);

			        ValueType t = ValueType.infer(((VarInitContext)_localctx).expression.node);
			        declareOrAssign((((VarInitContext)_localctx).name!=null?((VarInitContext)_localctx).name.getText():null), t, null);
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
			setState(158);
			((CallProcContext)_localctx).proc = match(ID);
			setState(184);
			switch (_input.LA(1)) {
			case BRACKET_OPEN:
				{
				setState(159);
				match(BRACKET_OPEN);
				setState(173);
				_la = _input.LA(1);
				if (((((_la - 38)) & ~0x3f) == 0 && ((1L << (_la - 38)) & ((1L << (IGUALESQ - 38)) | (1L << (YFUNC - 38)) | (1L << (OFUNC - 38)) | (1L << (MAYORQ - 38)) | (1L << (MENORQ - 38)) | (1L << (DIFERENCIA - 38)) | (1L << (AZAR - 38)) | (1L << (PRODUCTO - 38)) | (1L << (POTENCIA - 38)) | (1L << (DIVISION - 38)) | (1L << (SUMA - 38)) | (1L << (PLUS - 38)) | (1L << (MINUS - 38)) | (1L << (NOT - 38)) | (1L << (PAR_OPEN - 38)) | (1L << (BOOLEAN - 38)) | (1L << (DECIMAL - 38)) | (1L << (NUMBER - 38)) | (1L << (STRING - 38)) | (1L << (ID - 38)))) != 0)) {
					{
					setState(160);
					((CallProcContext)_localctx).expression = expression();
					 args.add(((CallProcContext)_localctx).expression.node); 
					setState(170);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (((((_la - 38)) & ~0x3f) == 0 && ((1L << (_la - 38)) & ((1L << (IGUALESQ - 38)) | (1L << (YFUNC - 38)) | (1L << (OFUNC - 38)) | (1L << (MAYORQ - 38)) | (1L << (MENORQ - 38)) | (1L << (DIFERENCIA - 38)) | (1L << (AZAR - 38)) | (1L << (PRODUCTO - 38)) | (1L << (POTENCIA - 38)) | (1L << (DIVISION - 38)) | (1L << (SUMA - 38)) | (1L << (PLUS - 38)) | (1L << (MINUS - 38)) | (1L << (NOT - 38)) | (1L << (PAR_OPEN - 38)) | (1L << (COMMA - 38)) | (1L << (BOOLEAN - 38)) | (1L << (DECIMAL - 38)) | (1L << (NUMBER - 38)) | (1L << (STRING - 38)) | (1L << (ID - 38)))) != 0)) {
						{
						{
						setState(163);
						_la = _input.LA(1);
						if (_la==COMMA) {
							{
							setState(162);
							match(COMMA);
							}
						}

						setState(165);
						((CallProcContext)_localctx).expression = expression();
						 args.add(((CallProcContext)_localctx).expression.node); 
						}
						}
						setState(172);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(175);
				match(BRACKET_CLOSE);
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
				{
				setState(181);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(176);
						((CallProcContext)_localctx).primaryArg = primaryArg();
						 args.add(((CallProcContext)_localctx).primaryArg.node); 
						}
						} 
					}
					setState(183);
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
			setState(197);
			switch (_input.LA(1)) {
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(188);
				((PrimaryArgContext)_localctx).NUMBER = match(NUMBER);
				 ((PrimaryArgContext)_localctx).node =  new ConstNode(Integer.parseInt((((PrimaryArgContext)_localctx).NUMBER!=null?((PrimaryArgContext)_localctx).NUMBER.getText():null))); 
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(190);
				((PrimaryArgContext)_localctx).ID = match(ID);
				 ((PrimaryArgContext)_localctx).node =  new VarRefNode((((PrimaryArgContext)_localctx).ID!=null?((PrimaryArgContext)_localctx).ID.getText():null)); 
				}
				break;
			case PAR_OPEN:
				enterOuterAlt(_localctx, 3);
				{
				setState(192);
				match(PAR_OPEN);
				setState(193);
				((PrimaryArgContext)_localctx).expression = expression();
				setState(194);
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
			setState(212);
			_la = _input.LA(1);
			if (((((_la - 38)) & ~0x3f) == 0 && ((1L << (_la - 38)) & ((1L << (IGUALESQ - 38)) | (1L << (YFUNC - 38)) | (1L << (OFUNC - 38)) | (1L << (MAYORQ - 38)) | (1L << (MENORQ - 38)) | (1L << (DIFERENCIA - 38)) | (1L << (AZAR - 38)) | (1L << (PRODUCTO - 38)) | (1L << (POTENCIA - 38)) | (1L << (DIVISION - 38)) | (1L << (SUMA - 38)) | (1L << (PLUS - 38)) | (1L << (MINUS - 38)) | (1L << (NOT - 38)) | (1L << (PAR_OPEN - 38)) | (1L << (BOOLEAN - 38)) | (1L << (DECIMAL - 38)) | (1L << (NUMBER - 38)) | (1L << (STRING - 38)) | (1L << (ID - 38)))) != 0)) {
				{
				setState(199);
				((ExpressionSeriesContext)_localctx).expression = expression();
				 _localctx.list.add(((ExpressionSeriesContext)_localctx).expression.node); 
				setState(209);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 38)) & ~0x3f) == 0 && ((1L << (_la - 38)) & ((1L << (IGUALESQ - 38)) | (1L << (YFUNC - 38)) | (1L << (OFUNC - 38)) | (1L << (MAYORQ - 38)) | (1L << (MENORQ - 38)) | (1L << (DIFERENCIA - 38)) | (1L << (AZAR - 38)) | (1L << (PRODUCTO - 38)) | (1L << (POTENCIA - 38)) | (1L << (DIVISION - 38)) | (1L << (SUMA - 38)) | (1L << (PLUS - 38)) | (1L << (MINUS - 38)) | (1L << (NOT - 38)) | (1L << (PAR_OPEN - 38)) | (1L << (COMMA - 38)) | (1L << (BOOLEAN - 38)) | (1L << (DECIMAL - 38)) | (1L << (NUMBER - 38)) | (1L << (STRING - 38)) | (1L << (ID - 38)))) != 0)) {
					{
					{
					setState(202);
					_la = _input.LA(1);
					if (_la==COMMA) {
						{
						setState(201);
						match(COMMA);
						}
					}

					setState(204);
					((ExpressionSeriesContext)_localctx).expression = expression();
					 _localctx.list.add(((ExpressionSeriesContext)_localctx).expression.node); 
					}
					}
					setState(211);
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
			setState(214);
			match(EJECUTA);
			setState(215);
			match(BRACKET_OPEN);
			setState(221);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONCOLORLAPIZ) | (1L << PONCL))) != 0) || _la==ID) {
				{
				{
				setState(216);
				((ExecBlockContext)_localctx).sentence = sentence();
				body.add(((ExecBlockContext)_localctx).sentence.node);
				}
				}
				setState(223);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(224);
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
			setState(227);
			match(REPITE);
			setState(228);
			((RepiteBlockContext)_localctx).times = expression();
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
		 List<StmtNode> thenBody = new ArrayList<>(); List<StmtNode> elseBody = new ArrayList<>(); 
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
			setState(245);
			match(BRACKET_OPEN);
			setState(251);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONCOLORLAPIZ) | (1L << PONCL))) != 0) || _la==ID) {
				{
				{
				setState(246);
				((SiStmtContext)_localctx).sentence = sentence();
				thenBody.add(((SiStmtContext)_localctx).sentence.node);
				}
				}
				setState(253);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(254);
			match(BRACKET_CLOSE);
			setState(265);
			_la = _input.LA(1);
			if (_la==BRACKET_OPEN) {
				{
				setState(255);
				match(BRACKET_OPEN);
				setState(261);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONCOLORLAPIZ) | (1L << PONCL))) != 0) || _la==ID) {
					{
					{
					setState(256);
					((SiStmtContext)_localctx).sentence = sentence();
					elseBody.add(((SiStmtContext)_localctx).sentence.node);
					}
					}
					setState(263);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(264);
				match(BRACKET_CLOSE);
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
		public TerminalNode HAZ() { return getToken(LogoTecParser.HAZ, 0); }
		public TerminalNode BRACKET_OPEN() { return getToken(LogoTecParser.BRACKET_OPEN, 0); }
		public TerminalNode BRACKET_CLOSE() { return getToken(LogoTecParser.BRACKET_CLOSE, 0); }
		public TerminalNode HASTA() { return getToken(LogoTecParser.HASTA, 0); }
		public TerminalNode PAR_OPEN() { return getToken(LogoTecParser.PAR_OPEN, 0); }
		public TerminalNode PAR_CLOSE() { return getToken(LogoTecParser.PAR_CLOSE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode DOT() { return getToken(LogoTecParser.DOT, 0); }
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public HazHastaStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hazHastaStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterHazHastaStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitHazHastaStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogoTecVisitor ) return ((LogoTecVisitor<? extends T>)visitor).visitHazHastaStmt(this);
			else return visitor.visitChildren(this);
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
			setState(269);
			match(HAZ);
			setState(271);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(270);
				match(DOT);
				}
			}

			setState(273);
			match(BRACKET_OPEN);
			setState(279);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONCOLORLAPIZ) | (1L << PONCL))) != 0) || _la==ID) {
				{
				{
				setState(274);
				((HazHastaStmtContext)_localctx).sentence = sentence();
				body.add(((HazHastaStmtContext)_localctx).sentence.node);
				}
				}
				setState(281);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(282);
			match(BRACKET_CLOSE);
			setState(283);
			match(HASTA);
			setState(284);
			match(PAR_OPEN);
			setState(285);
			((HazHastaStmtContext)_localctx).cond = expression();
			setState(286);
			match(PAR_CLOSE);

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
		public TerminalNode HAZ() { return getToken(LogoTecParser.HAZ, 0); }
		public TerminalNode BRACKET_OPEN() { return getToken(LogoTecParser.BRACKET_OPEN, 0); }
		public TerminalNode BRACKET_CLOSE() { return getToken(LogoTecParser.BRACKET_CLOSE, 0); }
		public TerminalNode MIENTRAS() { return getToken(LogoTecParser.MIENTRAS, 0); }
		public TerminalNode PAR_OPEN() { return getToken(LogoTecParser.PAR_OPEN, 0); }
		public TerminalNode PAR_CLOSE() { return getToken(LogoTecParser.PAR_CLOSE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode DOT() { return getToken(LogoTecParser.DOT, 0); }
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public HazMientrasStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hazMientrasStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterHazMientrasStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitHazMientrasStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogoTecVisitor ) return ((LogoTecVisitor<? extends T>)visitor).visitHazMientrasStmt(this);
			else return visitor.visitChildren(this);
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
			setState(289);
			match(HAZ);
			setState(291);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(290);
				match(DOT);
				}
			}

			setState(293);
			match(BRACKET_OPEN);
			setState(299);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONCOLORLAPIZ) | (1L << PONCL))) != 0) || _la==ID) {
				{
				{
				setState(294);
				((HazMientrasStmtContext)_localctx).sentence = sentence();
				body.add(((HazMientrasStmtContext)_localctx).sentence.node);
				}
				}
				setState(301);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(302);
			match(BRACKET_CLOSE);
			setState(303);
			match(MIENTRAS);
			setState(304);
			match(PAR_OPEN);
			setState(305);
			((HazMientrasStmtContext)_localctx).cond = expression();
			setState(306);
			match(PAR_CLOSE);

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
		enterRule(_localctx, 28, RULE_mientrasStmt);
		 List<StmtNode> body = new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(309);
			match(MIENTRAS);
			setState(310);
			match(PAR_OPEN);
			setState(311);
			((MientrasStmtContext)_localctx).cond = expression();
			setState(312);
			match(PAR_CLOSE);
			setState(313);
			match(BRACKET_OPEN);
			setState(319);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONCOLORLAPIZ) | (1L << PONCL))) != 0) || _la==ID) {
				{
				{
				setState(314);
				((MientrasStmtContext)_localctx).sentence = sentence();
				body.add(((MientrasStmtContext)_localctx).sentence.node);
				}
				}
				setState(321);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(322);
			match(BRACKET_CLOSE);

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
		enterRule(_localctx, 30, RULE_hastaStmt);
		 List<StmtNode> body = new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(325);
			match(HASTA);
			setState(326);
			match(PAR_OPEN);
			setState(327);
			((HastaStmtContext)_localctx).cond = expression();
			setState(328);
			match(PAR_CLOSE);
			setState(329);
			match(BRACKET_OPEN);
			setState(335);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONCOLORLAPIZ) | (1L << PONCL))) != 0) || _la==ID) {
				{
				{
				setState(330);
				((HastaStmtContext)_localctx).sentence = sentence();
				body.add(((HastaStmtContext)_localctx).sentence.node);
				}
				}
				setState(337);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(338);
			match(BRACKET_CLOSE);

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
		enterRule(_localctx, 32, RULE_turtleCmd);
		try {
			setState(452);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(341);
				match(AVANZA);
				setState(342);
				((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new ForwardNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(345);
				match(AV);
				setState(346);
				((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new ForwardNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(349);
				match(RETROCEDE);
				setState(350);
				((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new BackwardNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(353);
				match(RE);
				setState(354);
				((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new BackwardNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(357);
				match(GIRADERECHA);
				setState(358);
				((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new TurnRightNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(361);
				match(GD);
				setState(362);
				((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new TurnRightNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(365);
				match(GIRAIZQUIERDA);
				setState(366);
				((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new TurnLeftNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(369);
				match(GI);
				setState(370);
				((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new TurnLeftNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(373);
				match(OCULTATORTUGA);
				 ((TurtleCmdContext)_localctx).node =  new HideTurtleNode(); 
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(375);
				match(OT);
				 ((TurtleCmdContext)_localctx).node =  new HideTurtleNode(); 
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(377);
				match(APARECETORTUGA);
				 ((TurtleCmdContext)_localctx).node =  new ShowTurtleNode(); 
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(379);
				match(AT);
				 ((TurtleCmdContext)_localctx).node =  new ShowTurtleNode(); 
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(381);
				match(PONPOS);
				setState(382);
				match(BRACKET_OPEN);
				setState(383);
				((TurtleCmdContext)_localctx).coords = expressionSeries();
				setState(384);
				match(BRACKET_CLOSE);

				        List<ExprNode> coordsList = ((TurtleCmdContext)_localctx).coords.list;
				        if (coordsList.size() != 2) {
				            errors.add("Error semántico: 'PONPOS' requiere exactamente dos expresiones para X e Y.");
				        }
				        ExprNode xNode = coordsList.size() > 0 ? coordsList.get(0) : new ConstNode(0);
				        ExprNode yNode = coordsList.size() > 1 ? coordsList.get(1) : new ConstNode(0);
				        ((TurtleCmdContext)_localctx).node =  new SetPosNode(xNode, yNode, true);
				      
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(387);
				match(PONXY);
				setState(388);
				((TurtleCmdContext)_localctx).x = expression();
				setState(389);
				((TurtleCmdContext)_localctx).y = expression();
				 ((TurtleCmdContext)_localctx).node =  new SetPosNode(((TurtleCmdContext)_localctx).x.node, ((TurtleCmdContext)_localctx).y.node, false); 
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(392);
				match(PONRUMBO);
				setState(393);
				((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new SetHeadingNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(396);
				match(RUMBO);
				 ((TurtleCmdContext)_localctx).node =  new ShowHeadingNode(); 
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(398);
				match(PONX);
				setState(399);
				((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new SetXNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(402);
				match(PONY);
				setState(403);
				((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new SetYNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(406);
				match(BAJALAPIZ);
				 ((TurtleCmdContext)_localctx).node =  new PenDownNode(); 
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(408);
				match(BL);
				 ((TurtleCmdContext)_localctx).node =  new PenDownNode(); 
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 21);
				{
				setState(410);
				match(SUBELAPIZ);
				 ((TurtleCmdContext)_localctx).node =  new PenUpNode(); 
				}
				break;
			case 22:
				enterOuterAlt(_localctx, 22);
				{
				setState(412);
				match(SB);
				 ((TurtleCmdContext)_localctx).node =  new PenUpNode(); 
				}
				break;
			case 23:
				enterOuterAlt(_localctx, 23);
				{
				setState(414);
				match(CENTRO);
				 ((TurtleCmdContext)_localctx).node =  new CenterNode(); 
				}
				break;
			case 24:
				enterOuterAlt(_localctx, 24);
				{
				setState(416);
				match(ESPERA);
				setState(417);
				((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new WaitNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 25:
				enterOuterAlt(_localctx, 25);
				{
				setState(420);
				match(INC);
				setState(421);
				match(BRACKET_OPEN);
				setState(422);
				((TurtleCmdContext)_localctx).id = match(ID);
				setState(423);
				match(BRACKET_CLOSE);
				 ((TurtleCmdContext)_localctx).node =  new IncNode(new VarRefNode((((TurtleCmdContext)_localctx).id!=null?((TurtleCmdContext)_localctx).id.getText():null)), new ConstNode(1)); 
				}
				break;
			case 26:
				enterOuterAlt(_localctx, 26);
				{
				setState(425);
				match(INC);
				setState(426);
				match(BRACKET_OPEN);
				setState(427);
				((TurtleCmdContext)_localctx).id = match(ID);
				setState(428);
				((TurtleCmdContext)_localctx).n = expression();
				setState(429);
				match(BRACKET_CLOSE);
				 ((TurtleCmdContext)_localctx).node =  new IncNode(new VarRefNode((((TurtleCmdContext)_localctx).id!=null?((TurtleCmdContext)_localctx).id.getText():null)), ((TurtleCmdContext)_localctx).n.node); 
				}
				break;
			case 27:
				enterOuterAlt(_localctx, 27);
				{
				setState(432);
				match(PONCOLORLAPIZ);
				setState(433);
				match(BRACKET_OPEN);
				setState(434);
				((TurtleCmdContext)_localctx).coords = expressionSeries();
				setState(435);
				match(BRACKET_CLOSE);

				        List<ExprNode> colorList = ((TurtleCmdContext)_localctx).coords.list;
				        if (colorList.size() != 3) {
				            errors.add("Error semántico: 'PONCOLORLAPIZ' requiere exactamente tres valores RGB.");
				        }
				        ExprNode rNode = colorList.size() > 0 ? colorList.get(0) : new ConstNode(0);
				        ExprNode gNode = colorList.size() > 1 ? colorList.get(1) : new ConstNode(0);
				        ExprNode bNode = colorList.size() > 2 ? colorList.get(2) : new ConstNode(0);
				        // Nota: La validación de colores permitidos se hace en SetColorNode.execute()
				        ((TurtleCmdContext)_localctx).node =  new SetColorNode(rNode, gNode, bNode);
				      
				}
				break;
			case 28:
				enterOuterAlt(_localctx, 28);
				{
				setState(438);
				match(PONCL);
				setState(439);
				match(BRACKET_OPEN);
				setState(440);
				((TurtleCmdContext)_localctx).coords = expressionSeries();
				setState(441);
				match(BRACKET_CLOSE);

				        List<ExprNode> colorList = ((TurtleCmdContext)_localctx).coords.list;
				        if (colorList.size() != 3) {
				            errors.add("Error semántico: 'PONCL' requiere exactamente tres valores RGB.");
				        }
				        ExprNode rNode = colorList.size() > 0 ? colorList.get(0) : new ConstNode(0);
				        ExprNode gNode = colorList.size() > 1 ? colorList.get(1) : new ConstNode(0);
				        ExprNode bNode = colorList.size() > 2 ? colorList.get(2) : new ConstNode(0);
				        // Nota: La validación de colores permitidos se hace en SetColorNode.execute()
				        ((TurtleCmdContext)_localctx).node =  new SetColorNode(rNode, gNode, bNode);
				      
				}
				break;
			case 29:
				enterOuterAlt(_localctx, 29);
				{
				setState(444);
				match(PONCOLORLAPIZ);
				setState(445);
				((TurtleCmdContext)_localctx).c = colorName();
				 ((TurtleCmdContext)_localctx).node =  new SetPenColorNode(((TurtleCmdContext)_localctx).c.value); 
				}
				break;
			case 30:
				enterOuterAlt(_localctx, 30);
				{
				setState(448);
				match(PONCL);
				setState(449);
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
		enterRule(_localctx, 34, RULE_colorName);
		try {
			setState(458);
			switch (_input.LA(1)) {
			case COLOR:
				enterOuterAlt(_localctx, 1);
				{
				setState(454);
				((ColorNameContext)_localctx).c = match(COLOR);
				 ((ColorNameContext)_localctx).value =  ((ColorNameContext)_localctx).c.getText().toLowerCase(); 
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(456);
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
		enterRule(_localctx, 36, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(460);
			((ExpressionContext)_localctx).t1 = logicTerm();
			 ((ExpressionContext)_localctx).node =  ((ExpressionContext)_localctx).t1.node; ((ExpressionContext)_localctx).val =  ((ExpressionContext)_localctx).t1.val; 
			setState(468);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(462);
				match(OR);
				setState(463);
				((ExpressionContext)_localctx).t2 = logicTerm();
				 ((ExpressionContext)_localctx).node =  new LogicalOrNode(_localctx.node, ((ExpressionContext)_localctx).t2.node); 
				}
				}
				setState(470);
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
		enterRule(_localctx, 38, RULE_logicTerm);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(471);
			((LogicTermContext)_localctx).f1 = logicFactor();
			 ((LogicTermContext)_localctx).node =  ((LogicTermContext)_localctx).f1.node; ((LogicTermContext)_localctx).val =  ((LogicTermContext)_localctx).f1.val; 
			setState(479);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(473);
				match(AND);
				setState(474);
				((LogicTermContext)_localctx).f2 = logicFactor();
				 ((LogicTermContext)_localctx).node =  new LogicalAndNode(_localctx.node, ((LogicTermContext)_localctx).f2.node); 
				}
				}
				setState(481);
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
		enterRule(_localctx, 40, RULE_logicFactor);
		try {
			setState(489);
			switch (_input.LA(1)) {
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(482);
				match(NOT);
				setState(483);
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
				setState(486);
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
		enterRule(_localctx, 42, RULE_relational);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(491);
			((RelationalContext)_localctx).a1 = arithExpr();
			 ((RelationalContext)_localctx).node =  ((RelationalContext)_localctx).a1.node; ((RelationalContext)_localctx).val =  ((RelationalContext)_localctx).a1.val; 
			setState(519);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GT) | (1L << LT) | (1L << GEQ) | (1L << LEQ) | (1L << EQ) | (1L << NEQ))) != 0)) {
				{
				setState(517);
				switch (_input.LA(1)) {
				case GT:
					{
					setState(493);
					match(GT);
					setState(494);
					((RelationalContext)_localctx).a2 = arithExpr();
					 ((RelationalContext)_localctx).node =  new GreaterThanNode(_localctx.node, ((RelationalContext)_localctx).a2.node); 
					}
					break;
				case LT:
					{
					setState(497);
					match(LT);
					setState(498);
					((RelationalContext)_localctx).a2 = arithExpr();
					 ((RelationalContext)_localctx).node =  new LessThanNode(_localctx.node, ((RelationalContext)_localctx).a2.node); 
					}
					break;
				case GEQ:
					{
					setState(501);
					match(GEQ);
					setState(502);
					((RelationalContext)_localctx).a2 = arithExpr();
					 ((RelationalContext)_localctx).node =  new GreaterEqualNode(_localctx.node, ((RelationalContext)_localctx).a2.node); 
					}
					break;
				case LEQ:
					{
					setState(505);
					match(LEQ);
					setState(506);
					((RelationalContext)_localctx).a2 = arithExpr();
					 ((RelationalContext)_localctx).node =  new LessEqualNode(_localctx.node, ((RelationalContext)_localctx).a2.node); 
					}
					break;
				case EQ:
					{
					setState(509);
					match(EQ);
					setState(510);
					((RelationalContext)_localctx).a2 = arithExpr();
					 ((RelationalContext)_localctx).node =  new EqualsNode(_localctx.node, ((RelationalContext)_localctx).a2.node); 
					}
					break;
				case NEQ:
					{
					setState(513);
					match(NEQ);
					setState(514);
					((RelationalContext)_localctx).a2 = arithExpr();
					 ((RelationalContext)_localctx).node =  new NotEqualsNode(_localctx.node, ((RelationalContext)_localctx).a2.node); 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(521);
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
		enterRule(_localctx, 44, RULE_arithExpr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(522);
			((ArithExprContext)_localctx).t1 = term();
			 ((ArithExprContext)_localctx).node =  ((ArithExprContext)_localctx).t1.node; ((ArithExprContext)_localctx).val =  ((ArithExprContext)_localctx).t1.val; 
			setState(534);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(532);
					switch (_input.LA(1)) {
					case PLUS:
						{
						setState(524);
						match(PLUS);
						setState(525);
						((ArithExprContext)_localctx).t2 = term();
						 ((ArithExprContext)_localctx).node =  new AdditionNode(_localctx.node, ((ArithExprContext)_localctx).t2.node); 
						}
						break;
					case MINUS:
						{
						setState(528);
						match(MINUS);
						setState(529);
						((ArithExprContext)_localctx).t2 = term();
						 ((ArithExprContext)_localctx).node =  new SubtractionNode(_localctx.node, ((ArithExprContext)_localctx).t2.node);  
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(536);
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
		enterRule(_localctx, 46, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(537);
			((TermContext)_localctx).f1 = factor();
			 ((TermContext)_localctx).node =  ((TermContext)_localctx).f1.node; ((TermContext)_localctx).val =  ((TermContext)_localctx).f1.val; 
			setState(549);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==MULT || _la==DIV) {
				{
				setState(547);
				switch (_input.LA(1)) {
				case MULT:
					{
					setState(539);
					match(MULT);
					setState(540);
					((TermContext)_localctx).f2 = factor();
					 ((TermContext)_localctx).node =  new MultiplicationNode(_localctx.node, ((TermContext)_localctx).f2.node);  
					}
					break;
				case DIV:
					{
					setState(543);
					match(DIV);
					setState(544);
					((TermContext)_localctx).f2 = factor();
					 ((TermContext)_localctx).node =  new DivisionNode(_localctx.node, ((TermContext)_localctx).f2.node); 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(551);
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
		enterRule(_localctx, 48, RULE_factor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(552);
			((FactorContext)_localctx).e1 = exponent();
			 ((FactorContext)_localctx).node =  ((FactorContext)_localctx).e1.node; ((FactorContext)_localctx).val =  ((FactorContext)_localctx).e1.val; 
			setState(560);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EXP) {
				{
				{
				setState(554);
				match(EXP);
				setState(555);
				((FactorContext)_localctx).e2 = exponent();
				 ((FactorContext)_localctx).node =  new ExponentiationNode(_localctx.node, ((FactorContext)_localctx).e2.node);  
				}
				}
				setState(562);
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
		enterRule(_localctx, 50, RULE_exponent);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(563);
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
		enterRule(_localctx, 52, RULE_unary);
		try {
			setState(580);
			switch (_input.LA(1)) {
			case MINUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(566);
				match(MINUS);
				setState(567);
				((UnaryContext)_localctx).u = unary();
				 ((UnaryContext)_localctx).node =  new SubtractionNode(new ConstNode(0), ((UnaryContext)_localctx).u.node); ((UnaryContext)_localctx).val =  Value.unknown(); 
				}
				break;
			case PLUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(570);
				match(PLUS);
				setState(571);
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
				setState(574);
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
				setState(577);
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
		public ExpressionContext e1;
		public ExpressionContext e2;
		public ExpressionContext e;
		public ExpressionContext expression;
		public List<ExpressionContext> rest = new ArrayList<ExpressionContext>();
		public TerminalNode IGUALESQ() { return getToken(LogoTecParser.IGUALESQ, 0); }
		public List<TerminalNode> PAR_OPEN() { return getTokens(LogoTecParser.PAR_OPEN); }
		public TerminalNode PAR_OPEN(int i) {
			return getToken(LogoTecParser.PAR_OPEN, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LogoTecParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LogoTecParser.COMMA, i);
		}
		public List<TerminalNode> PAR_CLOSE() { return getTokens(LogoTecParser.PAR_CLOSE); }
		public TerminalNode PAR_CLOSE(int i) {
			return getToken(LogoTecParser.PAR_CLOSE, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode YFUNC() { return getToken(LogoTecParser.YFUNC, 0); }
		public TerminalNode OFUNC() { return getToken(LogoTecParser.OFUNC, 0); }
		public TerminalNode MAYORQ() { return getToken(LogoTecParser.MAYORQ, 0); }
		public TerminalNode MENORQ() { return getToken(LogoTecParser.MENORQ, 0); }
		public TerminalNode AZAR() { return getToken(LogoTecParser.AZAR, 0); }
		public TerminalNode PRODUCTO() { return getToken(LogoTecParser.PRODUCTO, 0); }
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
		enterRule(_localctx, 54, RULE_funcCall);
		int _la;
		try {
			setState(701);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(582);
				match(IGUALESQ);
				setState(583);
				match(PAR_OPEN);
				setState(584);
				((FuncCallContext)_localctx).e1 = expression();
				setState(585);
				match(COMMA);
				setState(586);
				((FuncCallContext)_localctx).e2 = expression();
				setState(587);
				match(PAR_CLOSE);
				 ((FuncCallContext)_localctx).node =  new EqualsFuncNode(((FuncCallContext)_localctx).e1.node, ((FuncCallContext)_localctx).e2.node); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(590);
				match(YFUNC);
				setState(591);
				match(PAR_OPEN);
				setState(592);
				((FuncCallContext)_localctx).e1 = expression();
				setState(593);
				match(PAR_CLOSE);
				setState(594);
				match(PAR_OPEN);
				setState(595);
				((FuncCallContext)_localctx).e2 = expression();
				setState(596);
				match(PAR_CLOSE);
				 ((FuncCallContext)_localctx).node =  new LogicalAndNode(((FuncCallContext)_localctx).e1.node,((FuncCallContext)_localctx).e2.node); 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(599);
				match(YFUNC);
				setState(600);
				match(PAR_OPEN);
				setState(601);
				((FuncCallContext)_localctx).e1 = expression();
				setState(602);
				match(COMMA);
				setState(603);
				((FuncCallContext)_localctx).e2 = expression();
				setState(604);
				match(PAR_CLOSE);
				 ((FuncCallContext)_localctx).node =  new LogicalAndNode(((FuncCallContext)_localctx).e1.node,((FuncCallContext)_localctx).e2.node); 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(607);
				match(OFUNC);
				setState(608);
				match(PAR_OPEN);
				setState(609);
				((FuncCallContext)_localctx).e1 = expression();
				setState(610);
				match(PAR_CLOSE);
				setState(611);
				match(PAR_OPEN);
				setState(612);
				((FuncCallContext)_localctx).e2 = expression();
				setState(613);
				match(PAR_CLOSE);
				 ((FuncCallContext)_localctx).node =  new LogicalOrNode(((FuncCallContext)_localctx).e1.node,((FuncCallContext)_localctx).e2.node); 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(616);
				match(OFUNC);
				setState(617);
				match(PAR_OPEN);
				setState(618);
				((FuncCallContext)_localctx).e1 = expression();
				setState(619);
				match(COMMA);
				setState(620);
				((FuncCallContext)_localctx).e2 = expression();
				setState(621);
				match(PAR_CLOSE);
				 ((FuncCallContext)_localctx).node =  new LogicalOrNode(((FuncCallContext)_localctx).e1.node,((FuncCallContext)_localctx).e2.node); 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(624);
				match(MAYORQ);
				setState(625);
				match(PAR_OPEN);
				setState(626);
				((FuncCallContext)_localctx).e1 = expression();
				setState(627);
				match(COMMA);
				setState(628);
				((FuncCallContext)_localctx).e2 = expression();
				setState(629);
				match(PAR_CLOSE);
				 ((FuncCallContext)_localctx).node =  new GreaterThanNode(((FuncCallContext)_localctx).e1.node,((FuncCallContext)_localctx).e2.node);  
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(632);
				match(MENORQ);
				setState(633);
				match(PAR_OPEN);
				setState(634);
				((FuncCallContext)_localctx).e1 = expression();
				setState(635);
				match(COMMA);
				setState(636);
				((FuncCallContext)_localctx).e2 = expression();
				setState(637);
				match(PAR_CLOSE);
				 ((FuncCallContext)_localctx).node =  new LessThanNode(((FuncCallContext)_localctx).e1.node,((FuncCallContext)_localctx).e2.node);  
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(640);
				match(AZAR);
				setState(641);
				match(PAR_OPEN);
				setState(642);
				((FuncCallContext)_localctx).e = expression();
				setState(643);
				match(PAR_CLOSE);
				 ((FuncCallContext)_localctx).node =  new RandomNode(((FuncCallContext)_localctx).e.node); 
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(646);
				match(PRODUCTO);
				setState(647);
				match(PAR_OPEN);
				setState(648);
				((FuncCallContext)_localctx).e1 = expression();
				setState(653);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(649);
					match(COMMA);
					setState(650);
					((FuncCallContext)_localctx).expression = expression();
					((FuncCallContext)_localctx).rest.add(((FuncCallContext)_localctx).expression);
					}
					}
					setState(655);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(656);
				match(PAR_CLOSE);

				        List<ExprNode> restNodes = new ArrayList<>();
				        if (((FuncCallContext)_localctx).rest != null) {
				            for (ExpressionContext ctx : ((FuncCallContext)_localctx).rest) {
				                restNodes.add(ctx.node);
				            }
				        }
				        ((FuncCallContext)_localctx).node =  new ProductNode(((FuncCallContext)_localctx).e1.node, restNodes);
				      
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(659);
				match(POTENCIA);
				setState(660);
				match(PAR_OPEN);
				setState(661);
				((FuncCallContext)_localctx).e1 = expression();
				setState(662);
				match(COMMA);
				setState(663);
				((FuncCallContext)_localctx).e2 = expression();
				setState(664);
				match(PAR_CLOSE);
				 ((FuncCallContext)_localctx).node =  new ExponentiationNode(((FuncCallContext)_localctx).e1.node, ((FuncCallContext)_localctx).e2.node); 
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(667);
				match(DIVISION);
				setState(668);
				match(PAR_OPEN);
				setState(669);
				((FuncCallContext)_localctx).e1 = expression();
				setState(670);
				match(COMMA);
				setState(671);
				((FuncCallContext)_localctx).e2 = expression();
				setState(672);
				match(PAR_CLOSE);
				 ((FuncCallContext)_localctx).node =  new DivisionNode(((FuncCallContext)_localctx).e1.node, ((FuncCallContext)_localctx).e2.node); 
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(675);
				match(SUMA);
				setState(676);
				match(PAR_OPEN);
				setState(677);
				((FuncCallContext)_localctx).e1 = expression();
				setState(682);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(678);
					match(COMMA);
					setState(679);
					((FuncCallContext)_localctx).expression = expression();
					((FuncCallContext)_localctx).rest.add(((FuncCallContext)_localctx).expression);
					}
					}
					setState(684);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(685);
				match(PAR_CLOSE);

				        List<ExprNode> restNodes = new ArrayList<>();
				        if (((FuncCallContext)_localctx).rest != null) {
				            for (ExpressionContext ctx : ((FuncCallContext)_localctx).rest) {
				                restNodes.add(ctx.node);
				            }
				        }
				        ((FuncCallContext)_localctx).node =  new SumNode(((FuncCallContext)_localctx).e1.node, restNodes);
				      
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(688);
				match(DIFERENCIA);
				setState(689);
				match(PAR_OPEN);
				setState(690);
				((FuncCallContext)_localctx).e1 = expression();
				setState(695);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(691);
					match(COMMA);
					setState(692);
					((FuncCallContext)_localctx).expression = expression();
					((FuncCallContext)_localctx).rest.add(((FuncCallContext)_localctx).expression);
					}
					}
					setState(697);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(698);
				match(PAR_CLOSE);

				        List<ExprNode> restNodes = new ArrayList<>();
				        if (((FuncCallContext)_localctx).rest != null) {
				            for (ExpressionContext ctx : ((FuncCallContext)_localctx).rest) {
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
		enterRule(_localctx, 56, RULE_primary);
		try {
			setState(718);
			switch (_input.LA(1)) {
			case DECIMAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(703);
				((PrimaryContext)_localctx).DECIMAL = match(DECIMAL);

				        double v = Double.parseDouble((((PrimaryContext)_localctx).DECIMAL!=null?((PrimaryContext)_localctx).DECIMAL.getText():null).replace(',', '.'));
				        ((PrimaryContext)_localctx).node =  new ConstNode(v);
				        ((PrimaryContext)_localctx).val =  Value.unknown(); // se mantiene inferencia desconocida si no hay soporte de double en Value
				      
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(705);
				((PrimaryContext)_localctx).NUMBER = match(NUMBER);
				 
				        int v = Integer.parseInt((((PrimaryContext)_localctx).NUMBER!=null?((PrimaryContext)_localctx).NUMBER.getText():null));
				        ((PrimaryContext)_localctx).node =  new ConstNode(v);
				        ((PrimaryContext)_localctx).val =  Value.integer(v);
				      
				}
				break;
			case BOOLEAN:
				enterOuterAlt(_localctx, 3);
				{
				setState(707);
				((PrimaryContext)_localctx).BOOLEAN = match(BOOLEAN);
				 
				        boolean b = Boolean.parseBoolean((((PrimaryContext)_localctx).BOOLEAN!=null?((PrimaryContext)_localctx).BOOLEAN.getText():null));
				        ((PrimaryContext)_localctx).node =  new ConstNode(b);
				        ((PrimaryContext)_localctx).val =  Value.bool(b);
				      
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 4);
				{
				setState(709);
				((PrimaryContext)_localctx).ID = match(ID);
				 
				        ((PrimaryContext)_localctx).node =  new VarRefNode((((PrimaryContext)_localctx).ID!=null?((PrimaryContext)_localctx).ID.getText():null));
				        ((PrimaryContext)_localctx).val =  Value.unknown();
				      
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 5);
				{
				setState(711);
				((PrimaryContext)_localctx).STRING = match(STRING);

				        String s = (((PrimaryContext)_localctx).STRING!=null?((PrimaryContext)_localctx).STRING.getText():null).substring(1,(((PrimaryContext)_localctx).STRING!=null?((PrimaryContext)_localctx).STRING.getText():null).length()-1);
				        ((PrimaryContext)_localctx).node =  new ConstNode(s);
				        ((PrimaryContext)_localctx).val =  Value.string(s);
				      
				}
				break;
			case PAR_OPEN:
				enterOuterAlt(_localctx, 6);
				{
				setState(713);
				match(PAR_OPEN);
				setState(714);
				((PrimaryContext)_localctx).expression = expression();
				setState(715);
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
		enterRule(_localctx, 58, RULE_literalOrString);
		try {
			setState(734);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(720);
				match(MINUS);
				setState(721);
				((LiteralOrStringContext)_localctx).DECIMAL = match(DECIMAL);

				        double v = -Double.parseDouble((((LiteralOrStringContext)_localctx).DECIMAL!=null?((LiteralOrStringContext)_localctx).DECIMAL.getText():null).replace(',', '.'));
				        ((LiteralOrStringContext)_localctx).node =  new ConstNode(v);
				        ((LiteralOrStringContext)_localctx).jval =  Double.valueOf(v);
				      
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(723);
				((LiteralOrStringContext)_localctx).DECIMAL = match(DECIMAL);

				        double v = Double.parseDouble((((LiteralOrStringContext)_localctx).DECIMAL!=null?((LiteralOrStringContext)_localctx).DECIMAL.getText():null).replace(',', '.'));
				        ((LiteralOrStringContext)_localctx).node =  new ConstNode(v);
				        ((LiteralOrStringContext)_localctx).jval =  Double.valueOf(v);
				      
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(725);
				match(MINUS);
				setState(726);
				((LiteralOrStringContext)_localctx).NUMBER = match(NUMBER);

				        int v = -Integer.parseInt((((LiteralOrStringContext)_localctx).NUMBER!=null?((LiteralOrStringContext)_localctx).NUMBER.getText():null));
				        ((LiteralOrStringContext)_localctx).node =  new ConstNode(v);
				        ((LiteralOrStringContext)_localctx).jval =  Integer.valueOf(v);
				      
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(728);
				((LiteralOrStringContext)_localctx).NUMBER = match(NUMBER);

				        int v = Integer.parseInt((((LiteralOrStringContext)_localctx).NUMBER!=null?((LiteralOrStringContext)_localctx).NUMBER.getText():null));
				        ((LiteralOrStringContext)_localctx).node =  new ConstNode(v);
				        ((LiteralOrStringContext)_localctx).jval =  Integer.valueOf(v);
				      
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(730);
				((LiteralOrStringContext)_localctx).BOOLEAN = match(BOOLEAN);

				        boolean v = Boolean.parseBoolean((((LiteralOrStringContext)_localctx).BOOLEAN!=null?((LiteralOrStringContext)_localctx).BOOLEAN.getText():null));
				        ((LiteralOrStringContext)_localctx).node =  new ConstNode(v);
				        ((LiteralOrStringContext)_localctx).jval =  Boolean.valueOf(v);
				      
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(732);
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
		enterRule(_localctx, 60, RULE_cmtFirstLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(736);
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3Q\u02e6\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \3\2"+
		"\5\2B\n\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\7\3N\n\3\f\3\16\3Q\13"+
		"\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\7\4[\n\4\f\4\16\4^\13\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\7\4f\n\4\f\4\16\4i\13\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u0090\n\5\3\6\3\6\3\6"+
		"\3\6\5\6\u0096\n\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b"+
		"\3\b\5\b\u00a6\n\b\3\b\3\b\3\b\7\b\u00ab\n\b\f\b\16\b\u00ae\13\b\5\b\u00b0"+
		"\n\b\3\b\3\b\3\b\3\b\7\b\u00b6\n\b\f\b\16\b\u00b9\13\b\5\b\u00bb\n\b\3"+
		"\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00c8\n\t\3\n\3\n\3\n\5"+
		"\n\u00cd\n\n\3\n\3\n\3\n\7\n\u00d2\n\n\f\n\16\n\u00d5\13\n\5\n\u00d7\n"+
		"\n\3\13\3\13\3\13\3\13\3\13\7\13\u00de\n\13\f\13\16\13\u00e1\13\13\3\13"+
		"\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00ec\n\f\f\f\16\f\u00ef\13\f\3"+
		"\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u00fc\n\r\f\r\16\r\u00ff"+
		"\13\r\3\r\3\r\3\r\3\r\3\r\7\r\u0106\n\r\f\r\16\r\u0109\13\r\3\r\5\r\u010c"+
		"\n\r\3\r\3\r\3\16\3\16\5\16\u0112\n\16\3\16\3\16\3\16\3\16\7\16\u0118"+
		"\n\16\f\16\16\16\u011b\13\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3"+
		"\17\5\17\u0126\n\17\3\17\3\17\3\17\3\17\7\17\u012c\n\17\f\17\16\17\u012f"+
		"\13\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\7\20\u0140\n\20\f\20\16\20\u0143\13\20\3\20\3\20\3\20\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\7\21\u0150\n\21\f\21\16\21\u0153\13"+
		"\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\5\22\u01c7\n\22\3\23\3\23\3\23\3\23\5\23\u01cd\n\23\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\7\24\u01d5\n\24\f\24\16\24\u01d8\13\24\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\7\25\u01e0\n\25\f\25\16\25\u01e3\13\25\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u01ec\n\26\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\7\27\u0208\n\27\f\27\16\27\u020b\13"+
		"\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\7\30\u0217\n\30"+
		"\f\30\16\30\u021a\13\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3"+
		"\31\7\31\u0226\n\31\f\31\16\31\u0229\13\31\3\32\3\32\3\32\3\32\3\32\3"+
		"\32\7\32\u0231\n\32\f\32\16\32\u0234\13\32\3\33\3\33\3\33\3\34\3\34\3"+
		"\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\5\34\u0247"+
		"\n\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\7\35\u028e\n\35\f\35\16\35\u0291\13\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\7\35\u02ab\n\35\f\35\16\35\u02ae\13\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\7\35\u02b8\n\35\f\35\16\35\u02bb\13\35"+
		"\3\35\3\35\3\35\5\35\u02c0\n\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\5\36\u02d1\n\36\3\37\3\37\3\37\3\37"+
		"\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\5\37\u02e1\n\37\3 "+
		"\3 \3 \3 \2\2!\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64"+
		"\668:<>\2\2\u0333\2A\3\2\2\2\4O\3\2\2\2\6T\3\2\2\2\b\u008f\3\2\2\2\n\u0091"+
		"\3\2\2\2\f\u0099\3\2\2\2\16\u00a0\3\2\2\2\20\u00c7\3\2\2\2\22\u00d6\3"+
		"\2\2\2\24\u00d8\3\2\2\2\26\u00e5\3\2\2\2\30\u00f3\3\2\2\2\32\u010f\3\2"+
		"\2\2\34\u0123\3\2\2\2\36\u0137\3\2\2\2 \u0147\3\2\2\2\"\u01c6\3\2\2\2"+
		"$\u01cc\3\2\2\2&\u01ce\3\2\2\2(\u01d9\3\2\2\2*\u01eb\3\2\2\2,\u01ed\3"+
		"\2\2\2.\u020c\3\2\2\2\60\u021b\3\2\2\2\62\u022a\3\2\2\2\64\u0235\3\2\2"+
		"\2\66\u0246\3\2\2\28\u02bf\3\2\2\2:\u02d0\3\2\2\2<\u02e0\3\2\2\2>\u02e2"+
		"\3\2\2\2@B\5> \2A@\3\2\2\2AB\3\2\2\2BC\3\2\2\2CD\5\4\3\2DE\7\2\2\3EF\b"+
		"\2\1\2F\3\3\2\2\2GH\5\6\4\2HI\b\3\1\2IN\3\2\2\2JK\5\b\5\2KL\b\3\1\2LN"+
		"\3\2\2\2MG\3\2\2\2MJ\3\2\2\2NQ\3\2\2\2OM\3\2\2\2OP\3\2\2\2PR\3\2\2\2Q"+
		"O\3\2\2\2RS\b\3\1\2S\5\3\2\2\2TU\7\3\2\2UV\7M\2\2VW\b\4\1\2W\\\7B\2\2"+
		"XY\7M\2\2Y[\b\4\1\2ZX\3\2\2\2[^\3\2\2\2\\Z\3\2\2\2\\]\3\2\2\2]_\3\2\2"+
		"\2^\\\3\2\2\2_`\7C\2\2`a\b\4\1\2ag\7B\2\2bc\5\b\5\2cd\b\4\1\2df\3\2\2"+
		"\2eb\3\2\2\2fi\3\2\2\2ge\3\2\2\2gh\3\2\2\2hj\3\2\2\2ig\3\2\2\2jk\7C\2"+
		"\2kl\7\5\2\2lm\b\4\1\2m\7\3\2\2\2no\5\32\16\2op\b\5\1\2p\u0090\3\2\2\2"+
		"qr\5\34\17\2rs\b\5\1\2s\u0090\3\2\2\2tu\5\n\6\2uv\b\5\1\2v\u0090\3\2\2"+
		"\2wx\5\f\7\2xy\b\5\1\2y\u0090\3\2\2\2z{\5\16\b\2{|\b\5\1\2|\u0090\3\2"+
		"\2\2}~\5\"\22\2~\177\b\5\1\2\177\u0090\3\2\2\2\u0080\u0081\5\30\r\2\u0081"+
		"\u0082\b\5\1\2\u0082\u0090\3\2\2\2\u0083\u0084\5 \21\2\u0084\u0085\b\5"+
		"\1\2\u0085\u0090\3\2\2\2\u0086\u0087\5\36\20\2\u0087\u0088\b\5\1\2\u0088"+
		"\u0090\3\2\2\2\u0089\u008a\5\26\f\2\u008a\u008b\b\5\1\2\u008b\u0090\3"+
		"\2\2\2\u008c\u008d\5\24\13\2\u008d\u008e\b\5\1\2\u008e\u0090\3\2\2\2\u008f"+
		"n\3\2\2\2\u008fq\3\2\2\2\u008ft\3\2\2\2\u008fw\3\2\2\2\u008fz\3\2\2\2"+
		"\u008f}\3\2\2\2\u008f\u0080\3\2\2\2\u008f\u0083\3\2\2\2\u008f\u0086\3"+
		"\2\2\2\u008f\u0089\3\2\2\2\u008f\u008c\3\2\2\2\u0090\t\3\2\2\2\u0091\u0092"+
		"\7\4\2\2\u0092\u0093\7M\2\2\u0093\u0095\5<\37\2\u0094\u0096\7F\2\2\u0095"+
		"\u0094\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0098\b\6"+
		"\1\2\u0098\13\3\2\2\2\u0099\u009a\7\32\2\2\u009a\u009b\7M\2\2\u009b\u009c"+
		"\7A\2\2\u009c\u009d\5&\24\2\u009d\u009e\7F\2\2\u009e\u009f\b\7\1\2\u009f"+
		"\r\3\2\2\2\u00a0\u00ba\7M\2\2\u00a1\u00af\7B\2\2\u00a2\u00a3\5&\24\2\u00a3"+
		"\u00ac\b\b\1\2\u00a4\u00a6\7G\2\2\u00a5\u00a4\3\2\2\2\u00a5\u00a6\3\2"+
		"\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a8\5&\24\2\u00a8\u00a9\b\b\1\2\u00a9"+
		"\u00ab\3\2\2\2\u00aa\u00a5\3\2\2\2\u00ab\u00ae\3\2\2\2\u00ac\u00aa\3\2"+
		"\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00b0\3\2\2\2\u00ae\u00ac\3\2\2\2\u00af"+
		"\u00a2\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00bb\7C"+
		"\2\2\u00b2\u00b3\5\20\t\2\u00b3\u00b4\b\b\1\2\u00b4\u00b6\3\2\2\2\u00b5"+
		"\u00b2\3\2\2\2\u00b6\u00b9\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7\u00b8\3\2"+
		"\2\2\u00b8\u00bb\3\2\2\2\u00b9\u00b7\3\2\2\2\u00ba\u00a1\3\2\2\2\u00ba"+
		"\u00b7\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00bd\b\b\1\2\u00bd\17\3\2\2"+
		"\2\u00be\u00bf\7K\2\2\u00bf\u00c8\b\t\1\2\u00c0\u00c1\7M\2\2\u00c1\u00c8"+
		"\b\t\1\2\u00c2\u00c3\7D\2\2\u00c3\u00c4\5&\24\2\u00c4\u00c5\7E\2\2\u00c5"+
		"\u00c6\b\t\1\2\u00c6\u00c8\3\2\2\2\u00c7\u00be\3\2\2\2\u00c7\u00c0\3\2"+
		"\2\2\u00c7\u00c2\3\2\2\2\u00c8\21\3\2\2\2\u00c9\u00ca\5&\24\2\u00ca\u00d3"+
		"\b\n\1\2\u00cb\u00cd\7G\2\2\u00cc\u00cb\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd"+
		"\u00ce\3\2\2\2\u00ce\u00cf\5&\24\2\u00cf\u00d0\b\n\1\2\u00d0\u00d2\3\2"+
		"\2\2\u00d1\u00cc\3\2\2\2\u00d2\u00d5\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d3"+
		"\u00d4\3\2\2\2\u00d4\u00d7\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d6\u00c9\3\2"+
		"\2\2\u00d6\u00d7\3\2\2\2\u00d7\23\3\2\2\2\u00d8\u00d9\7\6\2\2\u00d9\u00df"+
		"\7B\2\2\u00da\u00db\5\b\5\2\u00db\u00dc\b\13\1\2\u00dc\u00de\3\2\2\2\u00dd"+
		"\u00da\3\2\2\2\u00de\u00e1\3\2\2\2\u00df\u00dd\3\2\2\2\u00df\u00e0\3\2"+
		"\2\2\u00e0\u00e2\3\2\2\2\u00e1\u00df\3\2\2\2\u00e2\u00e3\7C\2\2\u00e3"+
		"\u00e4\b\13\1\2\u00e4\25\3\2\2\2\u00e5\u00e6\7\7\2\2\u00e6\u00e7\5&\24"+
		"\2\u00e7\u00ed\7B\2\2\u00e8\u00e9\5\b\5\2\u00e9\u00ea\b\f\1\2\u00ea\u00ec"+
		"\3\2\2\2\u00eb\u00e8\3\2\2\2\u00ec\u00ef\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ed"+
		"\u00ee\3\2\2\2\u00ee\u00f0\3\2\2\2\u00ef\u00ed\3\2\2\2\u00f0\u00f1\7C"+
		"\2\2\u00f1\u00f2\b\f\1\2\u00f2\27\3\2\2\2\u00f3\u00f4\7\27\2\2\u00f4\u00f5"+
		"\7D\2\2\u00f5\u00f6\5&\24\2\u00f6\u00f7\7E\2\2\u00f7\u00fd\7B\2\2\u00f8"+
		"\u00f9\5\b\5\2\u00f9\u00fa\b\r\1\2\u00fa\u00fc\3\2\2\2\u00fb\u00f8\3\2"+
		"\2\2\u00fc\u00ff\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe"+
		"\u0100\3\2\2\2\u00ff\u00fd\3\2\2\2\u0100\u010b\7C\2\2\u0101\u0107\7B\2"+
		"\2\u0102\u0103\5\b\5\2\u0103\u0104\b\r\1\2\u0104\u0106\3\2\2\2\u0105\u0102"+
		"\3\2\2\2\u0106\u0109\3\2\2\2\u0107\u0105\3\2\2\2\u0107\u0108\3\2\2\2\u0108"+
		"\u010a\3\2\2\2\u0109\u0107\3\2\2\2\u010a\u010c\7C\2\2\u010b\u0101\3\2"+
		"\2\2\u010b\u010c\3\2\2\2\u010c\u010d\3\2\2\2\u010d\u010e\b\r\1\2\u010e"+
		"\31\3\2\2\2\u010f\u0111\7\4\2\2\u0110\u0112\7H\2\2\u0111\u0110\3\2\2\2"+
		"\u0111\u0112\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u0119\7B\2\2\u0114\u0115"+
		"\5\b\5\2\u0115\u0116\b\16\1\2\u0116\u0118\3\2\2\2\u0117\u0114\3\2\2\2"+
		"\u0118\u011b\3\2\2\2\u0119\u0117\3\2\2\2\u0119\u011a\3\2\2\2\u011a\u011c"+
		"\3\2\2\2\u011b\u0119\3\2\2\2\u011c\u011d\7C\2\2\u011d\u011e\7\30\2\2\u011e"+
		"\u011f\7D\2\2\u011f\u0120\5&\24\2\u0120\u0121\7E\2\2\u0121\u0122\b\16"+
		"\1\2\u0122\33\3\2\2\2\u0123\u0125\7\4\2\2\u0124\u0126\7H\2\2\u0125\u0124"+
		"\3\2\2\2\u0125\u0126\3\2\2\2\u0126\u0127\3\2\2\2\u0127\u012d\7B\2\2\u0128"+
		"\u0129\5\b\5\2\u0129\u012a\b\17\1\2\u012a\u012c\3\2\2\2\u012b\u0128\3"+
		"\2\2\2\u012c\u012f\3\2\2\2\u012d\u012b\3\2\2\2\u012d\u012e\3\2\2\2\u012e"+
		"\u0130\3\2\2\2\u012f\u012d\3\2\2\2\u0130\u0131\7C\2\2\u0131\u0132\7\31"+
		"\2\2\u0132\u0133\7D\2\2\u0133\u0134\5&\24\2\u0134\u0135\7E\2\2\u0135\u0136"+
		"\b\17\1\2\u0136\35\3\2\2\2\u0137\u0138\7\31\2\2\u0138\u0139\7D\2\2\u0139"+
		"\u013a\5&\24\2\u013a\u013b\7E\2\2\u013b\u0141\7B\2\2\u013c\u013d\5\b\5"+
		"\2\u013d\u013e\b\20\1\2\u013e\u0140\3\2\2\2\u013f\u013c\3\2\2\2\u0140"+
		"\u0143\3\2\2\2\u0141\u013f\3\2\2\2\u0141\u0142\3\2\2\2\u0142\u0144\3\2"+
		"\2\2\u0143\u0141\3\2\2\2\u0144\u0145\7C\2\2\u0145\u0146\b\20\1\2\u0146"+
		"\37\3\2\2\2\u0147\u0148\7\30\2\2\u0148\u0149\7D\2\2\u0149\u014a\5&\24"+
		"\2\u014a\u014b\7E\2\2\u014b\u0151\7B\2\2\u014c\u014d\5\b\5\2\u014d\u014e"+
		"\b\21\1\2\u014e\u0150\3\2\2\2\u014f\u014c\3\2\2\2\u0150\u0153\3\2\2\2"+
		"\u0151\u014f\3\2\2\2\u0151\u0152\3\2\2\2\u0152\u0154\3\2\2\2\u0153\u0151"+
		"\3\2\2\2\u0154\u0155\7C\2\2\u0155\u0156\b\21\1\2\u0156!\3\2\2\2\u0157"+
		"\u0158\7\33\2\2\u0158\u0159\5&\24\2\u0159\u015a\b\22\1\2\u015a\u01c7\3"+
		"\2\2\2\u015b\u015c\7\34\2\2\u015c\u015d\5&\24\2\u015d\u015e\b\22\1\2\u015e"+
		"\u01c7\3\2\2\2\u015f\u0160\7\35\2\2\u0160\u0161\5&\24\2\u0161\u0162\b"+
		"\22\1\2\u0162\u01c7\3\2\2\2\u0163\u0164\7\36\2\2\u0164\u0165\5&\24\2\u0165"+
		"\u0166\b\22\1\2\u0166\u01c7\3\2\2\2\u0167\u0168\7 \2\2\u0168\u0169\5&"+
		"\24\2\u0169\u016a\b\22\1\2\u016a\u01c7\3\2\2\2\u016b\u016c\7!\2\2\u016c"+
		"\u016d\5&\24\2\u016d\u016e\b\22\1\2\u016e\u01c7\3\2\2\2\u016f\u0170\7"+
		"\"\2\2\u0170\u0171\5&\24\2\u0171\u0172\b\22\1\2\u0172\u01c7\3\2\2\2\u0173"+
		"\u0174\7#\2\2\u0174\u0175\5&\24\2\u0175\u0176\b\22\1\2\u0176\u01c7\3\2"+
		"\2\2\u0177\u0178\7$\2\2\u0178\u01c7\b\22\1\2\u0179\u017a\7%\2\2\u017a"+
		"\u01c7\b\22\1\2\u017b\u017c\7\b\2\2\u017c\u01c7\b\22\1\2\u017d\u017e\7"+
		"\t\2\2\u017e\u01c7\b\22\1\2\u017f\u0180\7\n\2\2\u0180\u0181\7B\2\2\u0181"+
		"\u0182\5\22\n\2\u0182\u0183\7C\2\2\u0183\u0184\b\22\1\2\u0184\u01c7\3"+
		"\2\2\2\u0185\u0186\7\13\2\2\u0186\u0187\5&\24\2\u0187\u0188\5&\24\2\u0188"+
		"\u0189\b\22\1\2\u0189\u01c7\3\2\2\2\u018a\u018b\7\f\2\2\u018b\u018c\5"+
		"&\24\2\u018c\u018d\b\22\1\2\u018d\u01c7\3\2\2\2\u018e\u018f\7\r\2\2\u018f"+
		"\u01c7\b\22\1\2\u0190\u0191\7\16\2\2\u0191\u0192\5&\24\2\u0192\u0193\b"+
		"\22\1\2\u0193\u01c7\3\2\2\2\u0194\u0195\7\17\2\2\u0195\u0196\5&\24\2\u0196"+
		"\u0197\b\22\1\2\u0197\u01c7\3\2\2\2\u0198\u0199\7\20\2\2\u0199\u01c7\b"+
		"\22\1\2\u019a\u019b\7\21\2\2\u019b\u01c7\b\22\1\2\u019c\u019d\7\22\2\2"+
		"\u019d\u01c7\b\22\1\2\u019e\u019f\7\23\2\2\u019f\u01c7\b\22\1\2\u01a0"+
		"\u01a1\7\24\2\2\u01a1\u01c7\b\22\1\2\u01a2\u01a3\7\25\2\2\u01a3\u01a4"+
		"\5&\24\2\u01a4\u01a5\b\22\1\2\u01a5\u01c7\3\2\2\2\u01a6\u01a7\7\26\2\2"+
		"\u01a7\u01a8\7B\2\2\u01a8\u01a9\7M\2\2\u01a9\u01aa\7C\2\2\u01aa\u01c7"+
		"\b\22\1\2\u01ab\u01ac\7\26\2\2\u01ac\u01ad\7B\2\2\u01ad\u01ae\7M\2\2\u01ae"+
		"\u01af\5&\24\2\u01af\u01b0\7C\2\2\u01b0\u01b1\b\22\1\2\u01b1\u01c7\3\2"+
		"\2\2\u01b2\u01b3\7&\2\2\u01b3\u01b4\7B\2\2\u01b4\u01b5\5\22\n\2\u01b5"+
		"\u01b6\7C\2\2\u01b6\u01b7\b\22\1\2\u01b7\u01c7\3\2\2\2\u01b8\u01b9\7\'"+
		"\2\2\u01b9\u01ba\7B\2\2\u01ba\u01bb\5\22\n\2\u01bb\u01bc\7C\2\2\u01bc"+
		"\u01bd\b\22\1\2\u01bd\u01c7\3\2\2\2\u01be\u01bf\7&\2\2\u01bf\u01c0\5$"+
		"\23\2\u01c0\u01c1\b\22\1\2\u01c1\u01c7\3\2\2\2\u01c2\u01c3\7\'\2\2\u01c3"+
		"\u01c4\5$\23\2\u01c4\u01c5\b\22\1\2\u01c5\u01c7\3\2\2\2\u01c6\u0157\3"+
		"\2\2\2\u01c6\u015b\3\2\2\2\u01c6\u015f\3\2\2\2\u01c6\u0163\3\2\2\2\u01c6"+
		"\u0167\3\2\2\2\u01c6\u016b\3\2\2\2\u01c6\u016f\3\2\2\2\u01c6\u0173\3\2"+
		"\2\2\u01c6\u0177\3\2\2\2\u01c6\u0179\3\2\2\2\u01c6\u017b\3\2\2\2\u01c6"+
		"\u017d\3\2\2\2\u01c6\u017f\3\2\2\2\u01c6\u0185\3\2\2\2\u01c6\u018a\3\2"+
		"\2\2\u01c6\u018e\3\2\2\2\u01c6\u0190\3\2\2\2\u01c6\u0194\3\2\2\2\u01c6"+
		"\u0198\3\2\2\2\u01c6\u019a\3\2\2\2\u01c6\u019c\3\2\2\2\u01c6\u019e\3\2"+
		"\2\2\u01c6\u01a0\3\2\2\2\u01c6\u01a2\3\2\2\2\u01c6\u01a6\3\2\2\2\u01c6"+
		"\u01ab\3\2\2\2\u01c6\u01b2\3\2\2\2\u01c6\u01b8\3\2\2\2\u01c6\u01be\3\2"+
		"\2\2\u01c6\u01c2\3\2\2\2\u01c7#\3\2\2\2\u01c8\u01c9\7Q\2\2\u01c9\u01cd"+
		"\b\23\1\2\u01ca\u01cb\7M\2\2\u01cb\u01cd\b\23\1\2\u01cc\u01c8\3\2\2\2"+
		"\u01cc\u01ca\3\2\2\2\u01cd%\3\2\2\2\u01ce\u01cf\5(\25\2\u01cf\u01d6\b"+
		"\24\1\2\u01d0\u01d1\79\2\2\u01d1\u01d2\5(\25\2\u01d2\u01d3\b\24\1\2\u01d3"+
		"\u01d5\3\2\2\2\u01d4\u01d0\3\2\2\2\u01d5\u01d8\3\2\2\2\u01d6\u01d4\3\2"+
		"\2\2\u01d6\u01d7\3\2\2\2\u01d7\'\3\2\2\2\u01d8\u01d6\3\2\2\2\u01d9\u01da"+
		"\5*\26\2\u01da\u01e1\b\25\1\2\u01db\u01dc\78\2\2\u01dc\u01dd\5*\26\2\u01dd"+
		"\u01de\b\25\1\2\u01de\u01e0\3\2\2\2\u01df\u01db\3\2\2\2\u01e0\u01e3\3"+
		"\2\2\2\u01e1\u01df\3\2\2\2\u01e1\u01e2\3\2\2\2\u01e2)\3\2\2\2\u01e3\u01e1"+
		"\3\2\2\2\u01e4\u01e5\7:\2\2\u01e5\u01e6\5*\26\2\u01e6\u01e7\b\26\1\2\u01e7"+
		"\u01ec\3\2\2\2\u01e8\u01e9\5,\27\2\u01e9\u01ea\b\26\1\2\u01ea\u01ec\3"+
		"\2\2\2\u01eb\u01e4\3\2\2\2\u01eb\u01e8\3\2\2\2\u01ec+\3\2\2\2\u01ed\u01ee"+
		"\5.\30\2\u01ee\u0209\b\27\1\2\u01ef\u01f0\7;\2\2\u01f0\u01f1\5.\30\2\u01f1"+
		"\u01f2\b\27\1\2\u01f2\u0208\3\2\2\2\u01f3\u01f4\7<\2\2\u01f4\u01f5\5."+
		"\30\2\u01f5\u01f6\b\27\1\2\u01f6\u0208\3\2\2\2\u01f7\u01f8\7=\2\2\u01f8"+
		"\u01f9\5.\30\2\u01f9\u01fa\b\27\1\2\u01fa\u0208\3\2\2\2\u01fb\u01fc\7"+
		">\2\2\u01fc\u01fd\5.\30\2\u01fd\u01fe\b\27\1\2\u01fe\u0208\3\2\2\2\u01ff"+
		"\u0200\7?\2\2\u0200\u0201\5.\30\2\u0201\u0202\b\27\1\2\u0202\u0208\3\2"+
		"\2\2\u0203\u0204\7@\2\2\u0204\u0205\5.\30\2\u0205\u0206\b\27\1\2\u0206"+
		"\u0208\3\2\2\2\u0207\u01ef\3\2\2\2\u0207\u01f3\3\2\2\2\u0207\u01f7\3\2"+
		"\2\2\u0207\u01fb\3\2\2\2\u0207\u01ff\3\2\2\2\u0207\u0203\3\2\2\2\u0208"+
		"\u020b\3\2\2\2\u0209\u0207\3\2\2\2\u0209\u020a\3\2\2\2\u020a-\3\2\2\2"+
		"\u020b\u0209\3\2\2\2\u020c\u020d\5\60\31\2\u020d\u0218\b\30\1\2\u020e"+
		"\u020f\7\63\2\2\u020f\u0210\5\60\31\2\u0210\u0211\b\30\1\2\u0211\u0217"+
		"\3\2\2\2\u0212\u0213\7\64\2\2\u0213\u0214\5\60\31\2\u0214\u0215\b\30\1"+
		"\2\u0215\u0217\3\2\2\2\u0216\u020e\3\2\2\2\u0216\u0212\3\2\2\2\u0217\u021a"+
		"\3\2\2\2\u0218\u0216\3\2\2\2\u0218\u0219\3\2\2\2\u0219/\3\2\2\2\u021a"+
		"\u0218\3\2\2\2\u021b\u021c\5\62\32\2\u021c\u0227\b\31\1\2\u021d\u021e"+
		"\7\65\2\2\u021e\u021f\5\62\32\2\u021f\u0220\b\31\1\2\u0220\u0226\3\2\2"+
		"\2\u0221\u0222\7\66\2\2\u0222\u0223\5\62\32\2\u0223\u0224\b\31\1\2\u0224"+
		"\u0226\3\2\2\2\u0225\u021d\3\2\2\2\u0225\u0221\3\2\2\2\u0226\u0229\3\2"+
		"\2\2\u0227\u0225\3\2\2\2\u0227\u0228\3\2\2\2\u0228\61\3\2\2\2\u0229\u0227"+
		"\3\2\2\2\u022a\u022b\5\64\33\2\u022b\u0232\b\32\1\2\u022c\u022d\7\67\2"+
		"\2\u022d\u022e\5\64\33\2\u022e\u022f\b\32\1\2\u022f\u0231\3\2\2\2\u0230"+
		"\u022c\3\2\2\2\u0231\u0234\3\2\2\2\u0232\u0230\3\2\2\2\u0232\u0233\3\2"+
		"\2\2\u0233\63\3\2\2\2\u0234\u0232\3\2\2\2\u0235\u0236\5\66\34\2\u0236"+
		"\u0237\b\33\1\2\u0237\65\3\2\2\2\u0238\u0239\7\64\2\2\u0239\u023a\5\66"+
		"\34\2\u023a\u023b\b\34\1\2\u023b\u0247\3\2\2\2\u023c\u023d\7\63\2\2\u023d"+
		"\u023e\5\66\34\2\u023e\u023f\b\34\1\2\u023f\u0247\3\2\2\2\u0240\u0241"+
		"\58\35\2\u0241\u0242\b\34\1\2\u0242\u0247\3\2\2\2\u0243\u0244\5:\36\2"+
		"\u0244\u0245\b\34\1\2\u0245\u0247\3\2\2\2\u0246\u0238\3\2\2\2\u0246\u023c"+
		"\3\2\2\2\u0246\u0240\3\2\2\2\u0246\u0243\3\2\2\2\u0247\67\3\2\2\2\u0248"+
		"\u0249\7(\2\2\u0249\u024a\7D\2\2\u024a\u024b\5&\24\2\u024b\u024c\7G\2"+
		"\2\u024c\u024d\5&\24\2\u024d\u024e\7E\2\2\u024e\u024f\b\35\1\2\u024f\u02c0"+
		"\3\2\2\2\u0250\u0251\7)\2\2\u0251\u0252\7D\2\2\u0252\u0253\5&\24\2\u0253"+
		"\u0254\7E\2\2\u0254\u0255\7D\2\2\u0255\u0256\5&\24\2\u0256\u0257\7E\2"+
		"\2\u0257\u0258\b\35\1\2\u0258\u02c0\3\2\2\2\u0259\u025a\7)\2\2\u025a\u025b"+
		"\7D\2\2\u025b\u025c\5&\24\2\u025c\u025d\7G\2\2\u025d\u025e\5&\24\2\u025e"+
		"\u025f\7E\2\2\u025f\u0260\b\35\1\2\u0260\u02c0\3\2\2\2\u0261\u0262\7*"+
		"\2\2\u0262\u0263\7D\2\2\u0263\u0264\5&\24\2\u0264\u0265\7E\2\2\u0265\u0266"+
		"\7D\2\2\u0266\u0267\5&\24\2\u0267\u0268\7E\2\2\u0268\u0269\b\35\1\2\u0269"+
		"\u02c0\3\2\2\2\u026a\u026b\7*\2\2\u026b\u026c\7D\2\2\u026c\u026d\5&\24"+
		"\2\u026d\u026e\7G\2\2\u026e\u026f\5&\24\2\u026f\u0270\7E\2\2\u0270\u0271"+
		"\b\35\1\2\u0271\u02c0\3\2\2\2\u0272\u0273\7+\2\2\u0273\u0274\7D\2\2\u0274"+
		"\u0275\5&\24\2\u0275\u0276\7G\2\2\u0276\u0277\5&\24\2\u0277\u0278\7E\2"+
		"\2\u0278\u0279\b\35\1\2\u0279\u02c0\3\2\2\2\u027a\u027b\7,\2\2\u027b\u027c"+
		"\7D\2\2\u027c\u027d\5&\24\2\u027d\u027e\7G\2\2\u027e\u027f\5&\24\2\u027f"+
		"\u0280\7E\2\2\u0280\u0281\b\35\1\2\u0281\u02c0\3\2\2\2\u0282\u0283\7."+
		"\2\2\u0283\u0284\7D\2\2\u0284\u0285\5&\24\2\u0285\u0286\7E\2\2\u0286\u0287"+
		"\b\35\1\2\u0287\u02c0\3\2\2\2\u0288\u0289\7/\2\2\u0289\u028a\7D\2\2\u028a"+
		"\u028f\5&\24\2\u028b\u028c\7G\2\2\u028c\u028e\5&\24\2\u028d\u028b\3\2"+
		"\2\2\u028e\u0291\3\2\2\2\u028f\u028d\3\2\2\2\u028f\u0290\3\2\2\2\u0290"+
		"\u0292\3\2\2\2\u0291\u028f\3\2\2\2\u0292\u0293\7E\2\2\u0293\u0294\b\35"+
		"\1\2\u0294\u02c0\3\2\2\2\u0295\u0296\7\60\2\2\u0296\u0297\7D\2\2\u0297"+
		"\u0298\5&\24\2\u0298\u0299\7G\2\2\u0299\u029a\5&\24\2\u029a\u029b\7E\2"+
		"\2\u029b\u029c\b\35\1\2\u029c\u02c0\3\2\2\2\u029d\u029e\7\61\2\2\u029e"+
		"\u029f\7D\2\2\u029f\u02a0\5&\24\2\u02a0\u02a1\7G\2\2\u02a1\u02a2\5&\24"+
		"\2\u02a2\u02a3\7E\2\2\u02a3\u02a4\b\35\1\2\u02a4\u02c0\3\2\2\2\u02a5\u02a6"+
		"\7\62\2\2\u02a6\u02a7\7D\2\2\u02a7\u02ac\5&\24\2\u02a8\u02a9\7G\2\2\u02a9"+
		"\u02ab\5&\24\2\u02aa\u02a8\3\2\2\2\u02ab\u02ae\3\2\2\2\u02ac\u02aa\3\2"+
		"\2\2\u02ac\u02ad\3\2\2\2\u02ad\u02af\3\2\2\2\u02ae\u02ac\3\2\2\2\u02af"+
		"\u02b0\7E\2\2\u02b0\u02b1\b\35\1\2\u02b1\u02c0\3\2\2\2\u02b2\u02b3\7-"+
		"\2\2\u02b3\u02b4\7D\2\2\u02b4\u02b9\5&\24\2\u02b5\u02b6\7G\2\2\u02b6\u02b8"+
		"\5&\24\2\u02b7\u02b5\3\2\2\2\u02b8\u02bb\3\2\2\2\u02b9\u02b7\3\2\2\2\u02b9"+
		"\u02ba\3\2\2\2\u02ba\u02bc\3\2\2\2\u02bb\u02b9\3\2\2\2\u02bc\u02bd\7E"+
		"\2\2\u02bd\u02be\b\35\1\2\u02be\u02c0\3\2\2\2\u02bf\u0248\3\2\2\2\u02bf"+
		"\u0250\3\2\2\2\u02bf\u0259\3\2\2\2\u02bf\u0261\3\2\2\2\u02bf\u026a\3\2"+
		"\2\2\u02bf\u0272\3\2\2\2\u02bf\u027a\3\2\2\2\u02bf\u0282\3\2\2\2\u02bf"+
		"\u0288\3\2\2\2\u02bf\u0295\3\2\2\2\u02bf\u029d\3\2\2\2\u02bf\u02a5\3\2"+
		"\2\2\u02bf\u02b2\3\2\2\2\u02c09\3\2\2\2\u02c1\u02c2\7J\2\2\u02c2\u02d1"+
		"\b\36\1\2\u02c3\u02c4\7K\2\2\u02c4\u02d1\b\36\1\2\u02c5\u02c6\7I\2\2\u02c6"+
		"\u02d1\b\36\1\2\u02c7\u02c8\7M\2\2\u02c8\u02d1\b\36\1\2\u02c9\u02ca\7"+
		"L\2\2\u02ca\u02d1\b\36\1\2\u02cb\u02cc\7D\2\2\u02cc\u02cd\5&\24\2\u02cd"+
		"\u02ce\7E\2\2\u02ce\u02cf\b\36\1\2\u02cf\u02d1\3\2\2\2\u02d0\u02c1\3\2"+
		"\2\2\u02d0\u02c3\3\2\2\2\u02d0\u02c5\3\2\2\2\u02d0\u02c7\3\2\2\2\u02d0"+
		"\u02c9\3\2\2\2\u02d0\u02cb\3\2\2\2\u02d1;\3\2\2\2\u02d2\u02d3\7\64\2\2"+
		"\u02d3\u02d4\7J\2\2\u02d4\u02e1\b\37\1\2\u02d5\u02d6\7J\2\2\u02d6\u02e1"+
		"\b\37\1\2\u02d7\u02d8\7\64\2\2\u02d8\u02d9\7K\2\2\u02d9\u02e1\b\37\1\2"+
		"\u02da\u02db\7K\2\2\u02db\u02e1\b\37\1\2\u02dc\u02dd\7I\2\2\u02dd\u02e1"+
		"\b\37\1\2\u02de\u02df\7L\2\2\u02df\u02e1\b\37\1\2\u02e0\u02d2\3\2\2\2"+
		"\u02e0\u02d5\3\2\2\2\u02e0\u02d7\3\2\2\2\u02e0\u02da\3\2\2\2\u02e0\u02dc"+
		"\3\2\2\2\u02e0\u02de\3\2\2\2\u02e1=\3\2\2\2\u02e2\u02e3\7N\2\2\u02e3\u02e4"+
		"\b \1\2\u02e4?\3\2\2\2\60AMO\\g\u008f\u0095\u00a5\u00ac\u00af\u00b7\u00ba"+
		"\u00c7\u00cc\u00d3\u00d6\u00df\u00ed\u00fd\u0107\u010b\u0111\u0119\u0125"+
		"\u012d\u0141\u0151\u01c6\u01cc\u01d6\u01e1\u01eb\u0207\u0209\u0216\u0218"+
		"\u0225\u0227\u0232\u0246\u028f\u02ac\u02b9\u02bf\u02d0\u02e0";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}