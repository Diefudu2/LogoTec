// Generated from LogoTec.g4 by ANTLR 4.4
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
		NUMBER=73, STRING=74, ID=75, FIRSTLINE_COMMENT=76, COMMENT_LINE=77, WS=78, 
		COLOR=79;
	public static final String[] tokenNames = {
		"<INVALID>", "PARA", "HAZ", "FIN", "EJECUTA", "REPITE", "APARECETORTUGA", 
		"AT", "PONPOS", "PONXY", "PONRUMBO", "RUMBO", "PONX", "PONY", "BAJALAPIZ", 
		"BL", "SUBELAPIZ", "SB", "CENTRO", "ESPERA", "INC", "'SI'", "'HASTA'", 
		"'MIENTRAS'", "'INIC'", "'avanza'", "'av'", "'retrocede'", "'re'", "'atras'", 
		"'giraderecha'", "'gd'", "'giraizquierda'", "'gi'", "'ocultatortuga'", 
		"'ot'", "'poncolorlapiz'", "'poncl'", "IGUALESQ", "'Y'", "'O'", "MAYORQ", 
		"MENORQ", "DIFERENCIA", "AZAR", "PRODUCTO", "POTENCIA", "DIVISION", "SUMA", 
		"'+'", "'-'", "'*'", "'/'", "'^'", "'&&'", "'||'", "'!'", "'>'", "'<'", 
		"'>='", "'<='", "'=='", "'!='", "'='", "BRACKET_OPEN", "BRACKET_CLOSE", 
		"'('", "')'", "';'", "COMMA", "'.'", "BOOLEAN", "DECIMAL", "NUMBER", "STRING", 
		"ID", "FIRSTLINE_COMMENT", "COMMENT_LINE", "WS", "COLOR"
	};
	public static final int
		RULE_program = 0, RULE_proceduresBlock = 1, RULE_procedureDecl = 2, RULE_sentence = 3, 
		RULE_varDecl = 4, RULE_varInit = 5, RULE_callProc = 6, RULE_expressionSeries = 7, 
		RULE_execBlock = 8, RULE_repiteBlock = 9, RULE_siStmt = 10, RULE_hazHastaStmt = 11, 
		RULE_hazMientrasStmt = 12, RULE_mientrasStmt = 13, RULE_hastaStmt = 14, 
		RULE_turtleCmd = 15, RULE_expression = 16, RULE_logicTerm = 17, RULE_logicFactor = 18, 
		RULE_relational = 19, RULE_arithExpr = 20, RULE_term = 21, RULE_factor = 22, 
		RULE_exponent = 23, RULE_unary = 24, RULE_funcCall = 25, RULE_primary = 26, 
		RULE_literalOrString = 27, RULE_cmtFirstLine = 28, RULE_colorName = 29;
	public static final String[] ruleNames = {
		"program", "proceduresBlock", "procedureDecl", "sentence", "varDecl", 
		"varInit", "callProc", "expressionSeries", "execBlock", "repiteBlock", 
		"siStmt", "hazHastaStmt", "hazMientrasStmt", "mientrasStmt", "hastaStmt", 
		"turtleCmd", "expression", "logicTerm", "logicFactor", "relational", "arithExpr", 
		"term", "factor", "exponent", "unary", "funcCall", "primary", "literalOrString", 
		"cmtFirstLine", "colorName"
	};

	@Override
	public String getGrammarFileName() { return "LogoTec.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

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
		public ProceduresBlockContext proceduresBlock() {
			return getRuleContext(ProceduresBlockContext.class,0);
		}
		public TerminalNode EOF() { return getToken(LogoTecParser.EOF, 0); }
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
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			_la = _input.LA(1);
			if (_la==FIRSTLINE_COMMENT) {
				{
				setState(60); cmtFirstLine();
				}
			}

			setState(63); ((ProgramContext)_localctx).p = proceduresBlock();
			setState(64); match(EOF);

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
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
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
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterProceduresBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitProceduresBlock(this);
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
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PARA) | (1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT))) != 0) || _la==ID) {
				{
				setState(73);
				switch (_input.LA(1)) {
				case PARA:
					{
					setState(67); ((ProceduresBlockContext)_localctx).procedureDecl = procedureDecl();
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
				case ID:
					{
					setState(70); ((ProceduresBlockContext)_localctx).sentence = sentence();
					 mainBody.add(((ProceduresBlockContext)_localctx).sentence.node); 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(77);
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
		public List<TerminalNode> ID() { return getTokens(LogoTecParser.ID); }
		public TerminalNode PARA() { return getToken(LogoTecParser.PARA, 0); }
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public List<TerminalNode> BRACKET_CLOSE() { return getTokens(LogoTecParser.BRACKET_CLOSE); }
		public TerminalNode ID(int i) {
			return getToken(LogoTecParser.ID, i);
		}
		public List<TerminalNode> BRACKET_OPEN() { return getTokens(LogoTecParser.BRACKET_OPEN); }
		public TerminalNode BRACKET_OPEN(int i) {
			return getToken(LogoTecParser.BRACKET_OPEN, i);
		}
		public TerminalNode BRACKET_CLOSE(int i) {
			return getToken(LogoTecParser.BRACKET_CLOSE, i);
		}
		public TerminalNode FIN() { return getToken(LogoTecParser.FIN, 0); }
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
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
			setState(80); match(PARA);
			setState(81); ((ProcedureDeclContext)_localctx).procName = match(ID);
			 predeclareProcedure((((ProcedureDeclContext)_localctx).procName!=null?((ProcedureDeclContext)_localctx).procName.getText():null)); 
			setState(83); match(BRACKET_OPEN);
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(84); ((ProcedureDeclContext)_localctx).param = match(ID);
				 
				      params.add(((ProcedureDeclContext)_localctx).param.getText()); 
				      declareOrAssign((((ProcedureDeclContext)_localctx).param!=null?((ProcedureDeclContext)_localctx).param.getText():null), ValueType.UNKNOWN, null);
				  
				}
				}
				setState(90);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(91); match(BRACKET_CLOSE);
			 declareProcedure((((ProcedureDeclContext)_localctx).procName!=null?((ProcedureDeclContext)_localctx).procName.getText():null), params.size()); 
			setState(93); match(BRACKET_OPEN);
			setState(99);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT))) != 0) || _la==ID) {
				{
				{
				setState(94); ((ProcedureDeclContext)_localctx).sentence = sentence();
				body.add(((ProcedureDeclContext)_localctx).sentence.node);
				}
				}
				setState(101);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(102); match(BRACKET_CLOSE);
			setState(103); match(FIN);

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
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterSentence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitSentence(this);
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
				setState(106); ((SentenceContext)_localctx).hazHastaStmt = hazHastaStmt();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).hazHastaStmt.node; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(109); ((SentenceContext)_localctx).hazMientrasStmt = hazMientrasStmt();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).hazMientrasStmt.node; 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(112); ((SentenceContext)_localctx).varDecl = varDecl();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).varDecl.node; 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(115); ((SentenceContext)_localctx).varInit = varInit();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).varInit.node; 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(118); ((SentenceContext)_localctx).callProc = callProc();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).callProc.node; 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(121); ((SentenceContext)_localctx).turtleCmd = turtleCmd();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).turtleCmd.node; 
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(124); ((SentenceContext)_localctx).siStmt = siStmt();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).siStmt.node; 
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(127); ((SentenceContext)_localctx).hastaStmt = hastaStmt();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).hastaStmt.node; 
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(130); ((SentenceContext)_localctx).mientrasStmt = mientrasStmt();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).mientrasStmt.node; 
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(133); ((SentenceContext)_localctx).repiteBlock = repiteBlock();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).repiteBlock.node; 
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(136); ((SentenceContext)_localctx).execBlock = execBlock();
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
		public TerminalNode ID() { return getToken(LogoTecParser.ID, 0); }
		public LiteralOrStringContext literalOrString() {
			return getRuleContext(LiteralOrStringContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(LogoTecParser.SEMICOLON, 0); }
		public TerminalNode HAZ() { return getToken(LogoTecParser.HAZ, 0); }
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
	}

	public final VarDeclContext varDecl() throws RecognitionException {
		VarDeclContext _localctx = new VarDeclContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_varDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141); match(HAZ);
			setState(142); ((VarDeclContext)_localctx).name = match(ID);
			setState(143); ((VarDeclContext)_localctx).value = literalOrString();
			setState(145);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(144); match(SEMICOLON);
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
		public TerminalNode ASSIGN() { return getToken(LogoTecParser.ASSIGN, 0); }
		public TerminalNode SEMICOLON() { return getToken(LogoTecParser.SEMICOLON, 0); }
		public TerminalNode ID() { return getToken(LogoTecParser.ID, 0); }
		public TerminalNode INIC() { return getToken(LogoTecParser.INIC, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
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
	}

	public final VarInitContext varInit() throws RecognitionException {
		VarInitContext _localctx = new VarInitContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_varInit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149); match(INIC);
			setState(150); ((VarInitContext)_localctx).name = match(ID);
			setState(151); match(ASSIGN);
			setState(152); ((VarInitContext)_localctx).expression = expression();
			setState(153); match(SEMICOLON);

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
		public TerminalNode ID() { return getToken(LogoTecParser.ID, 0); }
		public List<TerminalNode> COMMA() { return getTokens(LogoTecParser.COMMA); }
		public TerminalNode BRACKET_CLOSE() { return getToken(LogoTecParser.BRACKET_CLOSE, 0); }
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode BRACKET_OPEN() { return getToken(LogoTecParser.BRACKET_OPEN, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
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
			setState(156); ((CallProcContext)_localctx).proc = match(ID);
			setState(189);
			switch (_input.LA(1)) {
			case BRACKET_OPEN:
				{
				setState(157); match(BRACKET_OPEN);
				setState(171);
				_la = _input.LA(1);
				if (((((_la - 38)) & ~0x3f) == 0 && ((1L << (_la - 38)) & ((1L << (IGUALESQ - 38)) | (1L << (YFUNC - 38)) | (1L << (OFUNC - 38)) | (1L << (MAYORQ - 38)) | (1L << (MENORQ - 38)) | (1L << (DIFERENCIA - 38)) | (1L << (AZAR - 38)) | (1L << (PRODUCTO - 38)) | (1L << (POTENCIA - 38)) | (1L << (DIVISION - 38)) | (1L << (SUMA - 38)) | (1L << (PLUS - 38)) | (1L << (MINUS - 38)) | (1L << (NOT - 38)) | (1L << (PAR_OPEN - 38)) | (1L << (BOOLEAN - 38)) | (1L << (DECIMAL - 38)) | (1L << (NUMBER - 38)) | (1L << (STRING - 38)) | (1L << (ID - 38)))) != 0)) {
					{
					setState(158); ((CallProcContext)_localctx).expression = expression();
					 args.add(((CallProcContext)_localctx).expression.node); 
					setState(168);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (((((_la - 38)) & ~0x3f) == 0 && ((1L << (_la - 38)) & ((1L << (IGUALESQ - 38)) | (1L << (YFUNC - 38)) | (1L << (OFUNC - 38)) | (1L << (MAYORQ - 38)) | (1L << (MENORQ - 38)) | (1L << (DIFERENCIA - 38)) | (1L << (AZAR - 38)) | (1L << (PRODUCTO - 38)) | (1L << (POTENCIA - 38)) | (1L << (DIVISION - 38)) | (1L << (SUMA - 38)) | (1L << (PLUS - 38)) | (1L << (MINUS - 38)) | (1L << (NOT - 38)) | (1L << (PAR_OPEN - 38)) | (1L << (COMMA - 38)) | (1L << (BOOLEAN - 38)) | (1L << (DECIMAL - 38)) | (1L << (NUMBER - 38)) | (1L << (STRING - 38)) | (1L << (ID - 38)))) != 0)) {
						{
						{
						setState(161);
						_la = _input.LA(1);
						if (_la==COMMA) {
							{
							setState(160); match(COMMA);
							}
						}

						setState(163); ((CallProcContext)_localctx).expression = expression();
						 args.add(((CallProcContext)_localctx).expression.node); 
						}
						}
						setState(170);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(173); match(BRACKET_CLOSE);
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
			case NOT:
			case BRACKET_CLOSE:
			case PAR_OPEN:
			case BOOLEAN:
			case DECIMAL:
			case NUMBER:
			case STRING:
			case ID:
				{
				setState(187);
				switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
				case 1:
					{
					setState(174); ((CallProcContext)_localctx).expression = expression();
					 args.add(((CallProcContext)_localctx).expression.node); 
					setState(184);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(177);
							_la = _input.LA(1);
							if (_la==COMMA) {
								{
								setState(176); match(COMMA);
								}
							}

							setState(179); ((CallProcContext)_localctx).expression = expression();
							 args.add(((CallProcContext)_localctx).expression.node); 
							}
							} 
						}
						setState(186);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
					}
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}

			        // Validar (o diferir) que el procedimiento exista y tenga el número correcto de argumentos
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

	public static class ExpressionSeriesContext extends ParserRuleContext {
		public List<ExprNode> list;
		public ExpressionContext expression;
		public List<TerminalNode> COMMA() { return getTokens(LogoTecParser.COMMA); }
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
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
	}

	public final ExpressionSeriesContext expressionSeries() throws RecognitionException {
		ExpressionSeriesContext _localctx = new ExpressionSeriesContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_expressionSeries);
		 ((ExpressionSeriesContext)_localctx).list =  new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			_la = _input.LA(1);
			if (((((_la - 38)) & ~0x3f) == 0 && ((1L << (_la - 38)) & ((1L << (IGUALESQ - 38)) | (1L << (YFUNC - 38)) | (1L << (OFUNC - 38)) | (1L << (MAYORQ - 38)) | (1L << (MENORQ - 38)) | (1L << (DIFERENCIA - 38)) | (1L << (AZAR - 38)) | (1L << (PRODUCTO - 38)) | (1L << (POTENCIA - 38)) | (1L << (DIVISION - 38)) | (1L << (SUMA - 38)) | (1L << (PLUS - 38)) | (1L << (MINUS - 38)) | (1L << (NOT - 38)) | (1L << (PAR_OPEN - 38)) | (1L << (BOOLEAN - 38)) | (1L << (DECIMAL - 38)) | (1L << (NUMBER - 38)) | (1L << (STRING - 38)) | (1L << (ID - 38)))) != 0)) {
				{
				setState(193); ((ExpressionSeriesContext)_localctx).expression = expression();
				 _localctx.list.add(((ExpressionSeriesContext)_localctx).expression.node); 
				setState(203);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 38)) & ~0x3f) == 0 && ((1L << (_la - 38)) & ((1L << (IGUALESQ - 38)) | (1L << (YFUNC - 38)) | (1L << (OFUNC - 38)) | (1L << (MAYORQ - 38)) | (1L << (MENORQ - 38)) | (1L << (DIFERENCIA - 38)) | (1L << (AZAR - 38)) | (1L << (PRODUCTO - 38)) | (1L << (POTENCIA - 38)) | (1L << (DIVISION - 38)) | (1L << (SUMA - 38)) | (1L << (PLUS - 38)) | (1L << (MINUS - 38)) | (1L << (NOT - 38)) | (1L << (PAR_OPEN - 38)) | (1L << (COMMA - 38)) | (1L << (BOOLEAN - 38)) | (1L << (DECIMAL - 38)) | (1L << (NUMBER - 38)) | (1L << (STRING - 38)) | (1L << (ID - 38)))) != 0)) {
					{
					{
					setState(196);
					_la = _input.LA(1);
					if (_la==COMMA) {
						{
						setState(195); match(COMMA);
						}
					}

					setState(198); ((ExpressionSeriesContext)_localctx).expression = expression();
					 _localctx.list.add(((ExpressionSeriesContext)_localctx).expression.node); 
					}
					}
					setState(205);
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
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public TerminalNode BRACKET_CLOSE() { return getToken(LogoTecParser.BRACKET_CLOSE, 0); }
		public TerminalNode BRACKET_OPEN() { return getToken(LogoTecParser.BRACKET_OPEN, 0); }
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
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
	}

	public final ExecBlockContext execBlock() throws RecognitionException {
		ExecBlockContext _localctx = new ExecBlockContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_execBlock);
		 List<StmtNode> body = new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208); match(EJECUTA);
			setState(209); match(BRACKET_OPEN);
			setState(215);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT))) != 0) || _la==ID) {
				{
				{
				setState(210); ((ExecBlockContext)_localctx).sentence = sentence();
				body.add(((ExecBlockContext)_localctx).sentence.node);
				}
				}
				setState(217);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(218); match(BRACKET_CLOSE);
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
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public TerminalNode BRACKET_CLOSE() { return getToken(LogoTecParser.BRACKET_CLOSE, 0); }
		public TerminalNode BRACKET_OPEN() { return getToken(LogoTecParser.BRACKET_OPEN, 0); }
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
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterRepiteBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitRepiteBlock(this);
		}
	}

	public final RepiteBlockContext repiteBlock() throws RecognitionException {
		RepiteBlockContext _localctx = new RepiteBlockContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_repiteBlock);
		 List<StmtNode> body = new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221); match(REPITE);
			setState(222); ((RepiteBlockContext)_localctx).times = expression();
			setState(223); match(BRACKET_OPEN);
			setState(229);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT))) != 0) || _la==ID) {
				{
				{
				setState(224); ((RepiteBlockContext)_localctx).sentence = sentence();
				body.add(((RepiteBlockContext)_localctx).sentence.node);
				}
				}
				setState(231);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(232); match(BRACKET_CLOSE);
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
		public TerminalNode PAR_CLOSE() { return getToken(LogoTecParser.PAR_CLOSE, 0); }
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public List<TerminalNode> BRACKET_CLOSE() { return getTokens(LogoTecParser.BRACKET_CLOSE); }
		public TerminalNode PAR_OPEN() { return getToken(LogoTecParser.PAR_OPEN, 0); }
		public TerminalNode SI() { return getToken(LogoTecParser.SI, 0); }
		public List<TerminalNode> BRACKET_OPEN() { return getTokens(LogoTecParser.BRACKET_OPEN); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode BRACKET_OPEN(int i) {
			return getToken(LogoTecParser.BRACKET_OPEN, i);
		}
		public TerminalNode BRACKET_CLOSE(int i) {
			return getToken(LogoTecParser.BRACKET_CLOSE, i);
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
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterSiStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitSiStmt(this);
		}
	}

	public final SiStmtContext siStmt() throws RecognitionException {
		SiStmtContext _localctx = new SiStmtContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_siStmt);
		 List<StmtNode> thenBody = new ArrayList<>(); List<StmtNode> elseBody = new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235); match(SI);
			setState(236); match(PAR_OPEN);
			setState(237); ((SiStmtContext)_localctx).cond = expression();
			setState(238); match(PAR_CLOSE);
			setState(239); match(BRACKET_OPEN);
			setState(245);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT))) != 0) || _la==ID) {
				{
				{
				setState(240); ((SiStmtContext)_localctx).sentence = sentence();
				thenBody.add(((SiStmtContext)_localctx).sentence.node);
				}
				}
				setState(247);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(248); match(BRACKET_CLOSE);
			setState(259);
			_la = _input.LA(1);
			if (_la==BRACKET_OPEN) {
				{
				setState(249); match(BRACKET_OPEN);
				setState(255);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT))) != 0) || _la==ID) {
					{
					{
					setState(250); ((SiStmtContext)_localctx).sentence = sentence();
					elseBody.add(((SiStmtContext)_localctx).sentence.node);
					}
					}
					setState(257);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(258); match(BRACKET_CLOSE);
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
		public TerminalNode DOT() { return getToken(LogoTecParser.DOT, 0); }
		public TerminalNode PAR_CLOSE() { return getToken(LogoTecParser.PAR_CLOSE, 0); }
		public TerminalNode HAZ() { return getToken(LogoTecParser.HAZ, 0); }
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public TerminalNode BRACKET_CLOSE() { return getToken(LogoTecParser.BRACKET_CLOSE, 0); }
		public TerminalNode PAR_OPEN() { return getToken(LogoTecParser.PAR_OPEN, 0); }
		public TerminalNode HASTA() { return getToken(LogoTecParser.HASTA, 0); }
		public TerminalNode BRACKET_OPEN() { return getToken(LogoTecParser.BRACKET_OPEN, 0); }
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
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterHazHastaStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitHazHastaStmt(this);
		}
	}

	public final HazHastaStmtContext hazHastaStmt() throws RecognitionException {
		HazHastaStmtContext _localctx = new HazHastaStmtContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_hazHastaStmt);
		 List<StmtNode> body = new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(263); match(HAZ);
			setState(265);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(264); match(DOT);
				}
			}

			setState(267); match(BRACKET_OPEN);
			setState(273);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT))) != 0) || _la==ID) {
				{
				{
				setState(268); ((HazHastaStmtContext)_localctx).sentence = sentence();
				body.add(((HazHastaStmtContext)_localctx).sentence.node);
				}
				}
				setState(275);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(276); match(BRACKET_CLOSE);
			setState(277); match(HASTA);
			setState(278); match(PAR_OPEN);
			setState(279); ((HazHastaStmtContext)_localctx).cond = expression();
			setState(280); match(PAR_CLOSE);

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
		public TerminalNode DOT() { return getToken(LogoTecParser.DOT, 0); }
		public TerminalNode MIENTRAS() { return getToken(LogoTecParser.MIENTRAS, 0); }
		public TerminalNode PAR_CLOSE() { return getToken(LogoTecParser.PAR_CLOSE, 0); }
		public TerminalNode HAZ() { return getToken(LogoTecParser.HAZ, 0); }
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public TerminalNode BRACKET_CLOSE() { return getToken(LogoTecParser.BRACKET_CLOSE, 0); }
		public TerminalNode PAR_OPEN() { return getToken(LogoTecParser.PAR_OPEN, 0); }
		public TerminalNode BRACKET_OPEN() { return getToken(LogoTecParser.BRACKET_OPEN, 0); }
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
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterHazMientrasStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitHazMientrasStmt(this);
		}
	}

	public final HazMientrasStmtContext hazMientrasStmt() throws RecognitionException {
		HazMientrasStmtContext _localctx = new HazMientrasStmtContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_hazMientrasStmt);
		 List<StmtNode> body = new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(283); match(HAZ);
			setState(285);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(284); match(DOT);
				}
			}

			setState(287); match(BRACKET_OPEN);
			setState(293);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT))) != 0) || _la==ID) {
				{
				{
				setState(288); ((HazMientrasStmtContext)_localctx).sentence = sentence();
				body.add(((HazMientrasStmtContext)_localctx).sentence.node);
				}
				}
				setState(295);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(296); match(BRACKET_CLOSE);
			setState(297); match(MIENTRAS);
			setState(298); match(PAR_OPEN);
			setState(299); ((HazMientrasStmtContext)_localctx).cond = expression();
			setState(300); match(PAR_CLOSE);

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
		public TerminalNode PAR_CLOSE() { return getToken(LogoTecParser.PAR_CLOSE, 0); }
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public TerminalNode BRACKET_CLOSE() { return getToken(LogoTecParser.BRACKET_CLOSE, 0); }
		public TerminalNode PAR_OPEN() { return getToken(LogoTecParser.PAR_OPEN, 0); }
		public TerminalNode BRACKET_OPEN() { return getToken(LogoTecParser.BRACKET_OPEN, 0); }
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
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterMientrasStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitMientrasStmt(this);
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
			setState(303); match(MIENTRAS);
			setState(304); match(PAR_OPEN);
			setState(305); ((MientrasStmtContext)_localctx).cond = expression();
			setState(306); match(PAR_CLOSE);
			setState(307); match(BRACKET_OPEN);
			setState(313);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT))) != 0) || _la==ID) {
				{
				{
				setState(308); ((MientrasStmtContext)_localctx).sentence = sentence();
				body.add(((MientrasStmtContext)_localctx).sentence.node);
				}
				}
				setState(315);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(316); match(BRACKET_CLOSE);

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
		public TerminalNode PAR_CLOSE() { return getToken(LogoTecParser.PAR_CLOSE, 0); }
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public TerminalNode BRACKET_CLOSE() { return getToken(LogoTecParser.BRACKET_CLOSE, 0); }
		public TerminalNode PAR_OPEN() { return getToken(LogoTecParser.PAR_OPEN, 0); }
		public TerminalNode HASTA() { return getToken(LogoTecParser.HASTA, 0); }
		public TerminalNode BRACKET_OPEN() { return getToken(LogoTecParser.BRACKET_OPEN, 0); }
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
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterHastaStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitHastaStmt(this);
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
			setState(319); match(HASTA);
			setState(320); match(PAR_OPEN);
			setState(321); ((HastaStmtContext)_localctx).cond = expression();
			setState(322); match(PAR_CLOSE);
			setState(323); match(BRACKET_OPEN);
			setState(329);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << APARECETORTUGA) | (1L << AT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIZQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT))) != 0) || _la==ID) {
				{
				{
				setState(324); ((HastaStmtContext)_localctx).sentence = sentence();
				body.add(((HastaStmtContext)_localctx).sentence.node);
				}
				}
				setState(331);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(332); match(BRACKET_CLOSE);

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
		public TerminalNode AT() { return getToken(LogoTecParser.AT, 0); }
		public TerminalNode CENTRO() { return getToken(LogoTecParser.CENTRO, 0); }
		public TerminalNode AVANZA() { return getToken(LogoTecParser.AVANZA, 0); }
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode PONXY() { return getToken(LogoTecParser.PONXY, 0); }
		public TerminalNode SB() { return getToken(LogoTecParser.SB, 0); }
		public TerminalNode PONRUMBO() { return getToken(LogoTecParser.PONRUMBO, 0); }
		public TerminalNode ID() { return getToken(LogoTecParser.ID, 0); }
		public TerminalNode PONX() { return getToken(LogoTecParser.PONX, 0); }
		public TerminalNode ESPERA() { return getToken(LogoTecParser.ESPERA, 0); }
		public TerminalNode INC() { return getToken(LogoTecParser.INC, 0); }
		public TerminalNode PONY() { return getToken(LogoTecParser.PONY, 0); }
		public TerminalNode GD() { return getToken(LogoTecParser.GD, 0); }
		public TerminalNode BAJALAPIZ() { return getToken(LogoTecParser.BAJALAPIZ, 0); }
		public TerminalNode AV() { return getToken(LogoTecParser.AV, 0); }
		public TerminalNode APARECETORTUGA() { return getToken(LogoTecParser.APARECETORTUGA, 0); }
		public TerminalNode SUBELAPIZ() { return getToken(LogoTecParser.SUBELAPIZ, 0); }
		public TerminalNode GIRADERECHA() { return getToken(LogoTecParser.GIRADERECHA, 0); }
		public TerminalNode OT() { return getToken(LogoTecParser.OT, 0); }
		public TerminalNode OCULTATORTUGA() { return getToken(LogoTecParser.OCULTATORTUGA, 0); }
		public TerminalNode RETROCEDE() { return getToken(LogoTecParser.RETROCEDE, 0); }
		public TerminalNode PONPOS() { return getToken(LogoTecParser.PONPOS, 0); }
		public TerminalNode BRACKET_OPEN() { return getToken(LogoTecParser.BRACKET_OPEN, 0); }
		public TerminalNode GIRAIZQUIERDA() { return getToken(LogoTecParser.GIRAIZQUIERDA, 0); }
		public TerminalNode RE() { return getToken(LogoTecParser.RE, 0); }
		public TerminalNode BRACKET_CLOSE() { return getToken(LogoTecParser.BRACKET_CLOSE, 0); }
		public TerminalNode BL() { return getToken(LogoTecParser.BL, 0); }
		public TerminalNode GI() { return getToken(LogoTecParser.GI, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public TerminalNode RUMBO() { return getToken(LogoTecParser.RUMBO, 0); }
		public ExpressionSeriesContext expressionSeries() {
			return getRuleContext(ExpressionSeriesContext.class,0);
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
	}

	public final TurtleCmdContext turtleCmd() throws RecognitionException {
		TurtleCmdContext _localctx = new TurtleCmdContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_turtleCmd);
		try {
			setState(426);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(335); match(AVANZA);
				setState(336); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new ForwardNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(339); match(AV);
				setState(340); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new ForwardNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(343); match(RETROCEDE);
				setState(344); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new BackwardNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(347); match(RE);
				setState(348); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new BackwardNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(351); match(GIRADERECHA);
				setState(352); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new TurnRightNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(355); match(GD);
				setState(356); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new TurnRightNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(359); match(GIRAIZQUIERDA);
				setState(360); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new TurnLeftNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(363); match(GI);
				setState(364); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new TurnLeftNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(367); match(OCULTATORTUGA);
				 ((TurtleCmdContext)_localctx).node =  new HideTurtleNode(); 
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(369); match(OT);
				 ((TurtleCmdContext)_localctx).node =  new HideTurtleNode(); 
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(371); match(APARECETORTUGA);
				 ((TurtleCmdContext)_localctx).node =  new ShowTurtleNode(); 
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(373); match(AT);
				 ((TurtleCmdContext)_localctx).node =  new ShowTurtleNode(); 
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(375); match(PONPOS);
				setState(376); match(BRACKET_OPEN);
				setState(377); ((TurtleCmdContext)_localctx).coords = expressionSeries();
				setState(378); match(BRACKET_CLOSE);

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
				setState(381); match(PONXY);
				setState(382); ((TurtleCmdContext)_localctx).x = expression();
				setState(383); ((TurtleCmdContext)_localctx).y = expression();
				 ((TurtleCmdContext)_localctx).node =  new SetPosNode(((TurtleCmdContext)_localctx).x.node, ((TurtleCmdContext)_localctx).y.node, false); 
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(386); match(PONRUMBO);
				setState(387); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new SetHeadingNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(390); match(RUMBO);
				 ((TurtleCmdContext)_localctx).node =  new ShowHeadingNode(); 
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(392); match(PONX);
				setState(393); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new SetXNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(396); match(PONY);
				setState(397); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new SetYNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(400); match(BAJALAPIZ);
				 ((TurtleCmdContext)_localctx).node =  new PenDownNode(); 
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(402); match(BL);
				 ((TurtleCmdContext)_localctx).node =  new PenDownNode(); 
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 21);
				{
				setState(404); match(SUBELAPIZ);
				 ((TurtleCmdContext)_localctx).node =  new PenUpNode(); 
				}
				break;
			case 22:
				enterOuterAlt(_localctx, 22);
				{
				setState(406); match(SB);
				 ((TurtleCmdContext)_localctx).node =  new PenUpNode(); 
				}
				break;
			case 23:
				enterOuterAlt(_localctx, 23);
				{
				setState(408); match(CENTRO);
				 ((TurtleCmdContext)_localctx).node =  new CenterNode(); 
				}
				break;
			case 24:
				enterOuterAlt(_localctx, 24);
				{
				setState(410); match(ESPERA);
				setState(411); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new WaitNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 25:
				enterOuterAlt(_localctx, 25);
				{
				setState(414); match(INC);
				setState(415); match(BRACKET_OPEN);
				setState(416); ((TurtleCmdContext)_localctx).id = match(ID);
				setState(417); match(BRACKET_CLOSE);
				 ((TurtleCmdContext)_localctx).node =  new IncNode(new VarRefNode((((TurtleCmdContext)_localctx).id!=null?((TurtleCmdContext)_localctx).id.getText():null)), new ConstNode(1)); 
				}
				break;
			case 26:
				enterOuterAlt(_localctx, 26);
				{
				setState(419); match(INC);
				setState(420); match(BRACKET_OPEN);
				setState(421); ((TurtleCmdContext)_localctx).id = match(ID);
				setState(422); ((TurtleCmdContext)_localctx).n = expression();
				setState(423); match(BRACKET_CLOSE);
				 ((TurtleCmdContext)_localctx).node =  new IncNode(new VarRefNode((((TurtleCmdContext)_localctx).id!=null?((TurtleCmdContext)_localctx).id.getText():null)), ((TurtleCmdContext)_localctx).n.node); 
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
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(428); ((ExpressionContext)_localctx).t1 = logicTerm();
			 ((ExpressionContext)_localctx).node =  ((ExpressionContext)_localctx).t1.node; ((ExpressionContext)_localctx).val =  ((ExpressionContext)_localctx).t1.val; 
			setState(436);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(430); match(OR);
				setState(431); ((ExpressionContext)_localctx).t2 = logicTerm();
				 ((ExpressionContext)_localctx).node =  new LogicalOrNode(_localctx.node, ((ExpressionContext)_localctx).t2.node); 
				}
				}
				setState(438);
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
			return getToken(LogoTecParser.AND, i);
		}
		public LogicFactorContext logicFactor(int i) {
			return getRuleContext(LogicFactorContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(LogoTecParser.AND); }
		public List<LogicFactorContext> logicFactor() {
			return getRuleContexts(LogicFactorContext.class);
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
	}

	public final LogicTermContext logicTerm() throws RecognitionException {
		LogicTermContext _localctx = new LogicTermContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_logicTerm);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(439); ((LogicTermContext)_localctx).f1 = logicFactor();
			 ((LogicTermContext)_localctx).node =  ((LogicTermContext)_localctx).f1.node; ((LogicTermContext)_localctx).val =  ((LogicTermContext)_localctx).f1.val; 
			setState(447);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(441); match(AND);
				setState(442); ((LogicTermContext)_localctx).f2 = logicFactor();
				 ((LogicTermContext)_localctx).node =  new LogicalAndNode(_localctx.node, ((LogicTermContext)_localctx).f2.node); 
				}
				}
				setState(449);
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
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterLogicFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitLogicFactor(this);
		}
	}

	public final LogicFactorContext logicFactor() throws RecognitionException {
		LogicFactorContext _localctx = new LogicFactorContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_logicFactor);
		try {
			setState(457);
			switch (_input.LA(1)) {
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(450); match(NOT);
				setState(451); ((LogicFactorContext)_localctx).lf = logicFactor();
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
				setState(454); ((LogicFactorContext)_localctx).r = relational();
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
		public List<TerminalNode> NEQ() { return getTokens(LogoTecParser.NEQ); }
		public TerminalNode EQ(int i) {
			return getToken(LogoTecParser.EQ, i);
		}
		public List<TerminalNode> LT() { return getTokens(LogoTecParser.LT); }
		public List<ArithExprContext> arithExpr() {
			return getRuleContexts(ArithExprContext.class);
		}
		public List<TerminalNode> GT() { return getTokens(LogoTecParser.GT); }
		public TerminalNode NEQ(int i) {
			return getToken(LogoTecParser.NEQ, i);
		}
		public List<TerminalNode> GEQ() { return getTokens(LogoTecParser.GEQ); }
		public ArithExprContext arithExpr(int i) {
			return getRuleContext(ArithExprContext.class,i);
		}
		public List<TerminalNode> LEQ() { return getTokens(LogoTecParser.LEQ); }
		public TerminalNode LEQ(int i) {
			return getToken(LogoTecParser.LEQ, i);
		}
		public List<TerminalNode> EQ() { return getTokens(LogoTecParser.EQ); }
		public TerminalNode GT(int i) {
			return getToken(LogoTecParser.GT, i);
		}
		public TerminalNode LT(int i) {
			return getToken(LogoTecParser.LT, i);
		}
		public TerminalNode GEQ(int i) {
			return getToken(LogoTecParser.GEQ, i);
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
	}

	public final RelationalContext relational() throws RecognitionException {
		RelationalContext _localctx = new RelationalContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_relational);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(459); ((RelationalContext)_localctx).a1 = arithExpr();
			 ((RelationalContext)_localctx).node =  ((RelationalContext)_localctx).a1.node; ((RelationalContext)_localctx).val =  ((RelationalContext)_localctx).a1.val; 
			setState(487);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GT) | (1L << LT) | (1L << GEQ) | (1L << LEQ) | (1L << EQ) | (1L << NEQ))) != 0)) {
				{
				setState(485);
				switch (_input.LA(1)) {
				case GT:
					{
					setState(461); match(GT);
					setState(462); ((RelationalContext)_localctx).a2 = arithExpr();
					 ((RelationalContext)_localctx).node =  new GreaterThanNode(_localctx.node, ((RelationalContext)_localctx).a2.node); 
					}
					break;
				case LT:
					{
					setState(465); match(LT);
					setState(466); ((RelationalContext)_localctx).a2 = arithExpr();
					 ((RelationalContext)_localctx).node =  new LessThanNode(_localctx.node, ((RelationalContext)_localctx).a2.node); 
					}
					break;
				case GEQ:
					{
					setState(469); match(GEQ);
					setState(470); ((RelationalContext)_localctx).a2 = arithExpr();
					 ((RelationalContext)_localctx).node =  new GreaterEqualNode(_localctx.node, ((RelationalContext)_localctx).a2.node); 
					}
					break;
				case LEQ:
					{
					setState(473); match(LEQ);
					setState(474); ((RelationalContext)_localctx).a2 = arithExpr();
					 ((RelationalContext)_localctx).node =  new LessEqualNode(_localctx.node, ((RelationalContext)_localctx).a2.node); 
					}
					break;
				case EQ:
					{
					setState(477); match(EQ);
					setState(478); ((RelationalContext)_localctx).a2 = arithExpr();
					 ((RelationalContext)_localctx).node =  new EqualsNode(_localctx.node, ((RelationalContext)_localctx).a2.node); 
					}
					break;
				case NEQ:
					{
					setState(481); match(NEQ);
					setState(482); ((RelationalContext)_localctx).a2 = arithExpr();
					 ((RelationalContext)_localctx).node =  new NotEqualsNode(_localctx.node, ((RelationalContext)_localctx).a2.node); 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(489);
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
			return getToken(LogoTecParser.MINUS, i);
		}
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<TerminalNode> PLUS() { return getTokens(LogoTecParser.PLUS); }
		public List<TerminalNode> MINUS() { return getTokens(LogoTecParser.MINUS); }
		public TerminalNode PLUS(int i) {
			return getToken(LogoTecParser.PLUS, i);
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
	}

	public final ArithExprContext arithExpr() throws RecognitionException {
		ArithExprContext _localctx = new ArithExprContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_arithExpr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(490); ((ArithExprContext)_localctx).t1 = term();
			 ((ArithExprContext)_localctx).node =  ((ArithExprContext)_localctx).t1.node; ((ArithExprContext)_localctx).val =  ((ArithExprContext)_localctx).t1.val; 
			setState(502);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(500);
					switch (_input.LA(1)) {
					case PLUS:
						{
						setState(492); match(PLUS);
						setState(493); ((ArithExprContext)_localctx).t2 = term();
						 ((ArithExprContext)_localctx).node =  new AdditionNode(_localctx.node, ((ArithExprContext)_localctx).t2.node); 
						}
						break;
					case MINUS:
						{
						setState(496); match(MINUS);
						setState(497); ((ArithExprContext)_localctx).t2 = term();
						 ((ArithExprContext)_localctx).node =  new SubtractionNode(_localctx.node, ((ArithExprContext)_localctx).t2.node);  
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(504);
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
			return getToken(LogoTecParser.MULT, i);
		}
		public List<FactorContext> factor() {
			return getRuleContexts(FactorContext.class);
		}
		public FactorContext factor(int i) {
			return getRuleContext(FactorContext.class,i);
		}
		public List<TerminalNode> MULT() { return getTokens(LogoTecParser.MULT); }
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
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(505); ((TermContext)_localctx).f1 = factor();
			 ((TermContext)_localctx).node =  ((TermContext)_localctx).f1.node; ((TermContext)_localctx).val =  ((TermContext)_localctx).f1.val; 
			setState(517);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==MULT || _la==DIV) {
				{
				setState(515);
				switch (_input.LA(1)) {
				case MULT:
					{
					setState(507); match(MULT);
					setState(508); ((TermContext)_localctx).f2 = factor();
					 ((TermContext)_localctx).node =  new MultiplicationNode(_localctx.node, ((TermContext)_localctx).f2.node);  
					}
					break;
				case DIV:
					{
					setState(511); match(DIV);
					setState(512); ((TermContext)_localctx).f2 = factor();
					 ((TermContext)_localctx).node =  new DivisionNode(_localctx.node, ((TermContext)_localctx).f2.node); 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(519);
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
			return getToken(LogoTecParser.EXP, i);
		}
		public ExponentContext exponent(int i) {
			return getRuleContext(ExponentContext.class,i);
		}
		public List<TerminalNode> EXP() { return getTokens(LogoTecParser.EXP); }
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
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_factor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(520); ((FactorContext)_localctx).e1 = exponent();
			 ((FactorContext)_localctx).node =  ((FactorContext)_localctx).e1.node; ((FactorContext)_localctx).val =  ((FactorContext)_localctx).e1.val; 
			setState(528);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EXP) {
				{
				{
				setState(522); match(EXP);
				setState(523); ((FactorContext)_localctx).e2 = exponent();
				 ((FactorContext)_localctx).node =  new ExponentiationNode(_localctx.node, ((FactorContext)_localctx).e2.node);  
				}
				}
				setState(530);
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
	}

	public final ExponentContext exponent() throws RecognitionException {
		ExponentContext _localctx = new ExponentContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_exponent);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(531); ((ExponentContext)_localctx).unary = unary();
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
		public TerminalNode MINUS() { return getToken(LogoTecParser.MINUS, 0); }
		public TerminalNode PLUS() { return getToken(LogoTecParser.PLUS, 0); }
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
	}

	public final UnaryContext unary() throws RecognitionException {
		UnaryContext _localctx = new UnaryContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_unary);
		try {
			setState(548);
			switch (_input.LA(1)) {
			case MINUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(534); match(MINUS);
				setState(535); ((UnaryContext)_localctx).u = unary();
				 ((UnaryContext)_localctx).node =  new SubtractionNode(new ConstNode(0), ((UnaryContext)_localctx).u.node); ((UnaryContext)_localctx).val =  Value.unknown(); 
				}
				break;
			case PLUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(538); match(PLUS);
				setState(539); ((UnaryContext)_localctx).u = unary();
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
				setState(542); ((UnaryContext)_localctx).funcCall = funcCall();
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
				setState(545); ((UnaryContext)_localctx).primary = primary();
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
		public TerminalNode DIVISION() { return getToken(LogoTecParser.DIVISION, 0); }
		public TerminalNode POTENCIA() { return getToken(LogoTecParser.POTENCIA, 0); }
		public List<TerminalNode> PAR_CLOSE() { return getTokens(LogoTecParser.PAR_CLOSE); }
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode MENORQ() { return getToken(LogoTecParser.MENORQ, 0); }
		public List<TerminalNode> PAR_OPEN() { return getTokens(LogoTecParser.PAR_OPEN); }
		public TerminalNode MAYORQ() { return getToken(LogoTecParser.MAYORQ, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(LogoTecParser.COMMA, i);
		}
		public TerminalNode OFUNC() { return getToken(LogoTecParser.OFUNC, 0); }
		public TerminalNode PRODUCTO() { return getToken(LogoTecParser.PRODUCTO, 0); }
		public TerminalNode AZAR() { return getToken(LogoTecParser.AZAR, 0); }
		public TerminalNode PAR_OPEN(int i) {
			return getToken(LogoTecParser.PAR_OPEN, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LogoTecParser.COMMA); }
		public TerminalNode YFUNC() { return getToken(LogoTecParser.YFUNC, 0); }
		public TerminalNode DIFERENCIA() { return getToken(LogoTecParser.DIFERENCIA, 0); }
		public TerminalNode SUMA() { return getToken(LogoTecParser.SUMA, 0); }
		public TerminalNode PAR_CLOSE(int i) {
			return getToken(LogoTecParser.PAR_CLOSE, i);
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
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterFuncCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitFuncCall(this);
		}
	}

	public final FuncCallContext funcCall() throws RecognitionException {
		FuncCallContext _localctx = new FuncCallContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_funcCall);
		int _la;
		try {
			setState(669);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(550); match(IGUALESQ);
				setState(551); match(PAR_OPEN);
				setState(552); ((FuncCallContext)_localctx).e1 = expression();
				setState(553); match(COMMA);
				setState(554); ((FuncCallContext)_localctx).e2 = expression();
				setState(555); match(PAR_CLOSE);
				 ((FuncCallContext)_localctx).node =  new EqualsFuncNode(((FuncCallContext)_localctx).e1.node, ((FuncCallContext)_localctx).e2.node); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(558); match(YFUNC);
				setState(559); match(PAR_OPEN);
				setState(560); ((FuncCallContext)_localctx).e1 = expression();
				setState(561); match(PAR_CLOSE);
				setState(562); match(PAR_OPEN);
				setState(563); ((FuncCallContext)_localctx).e2 = expression();
				setState(564); match(PAR_CLOSE);
				 ((FuncCallContext)_localctx).node =  new LogicalAndNode(((FuncCallContext)_localctx).e1.node,((FuncCallContext)_localctx).e2.node); 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(567); match(YFUNC);
				setState(568); match(PAR_OPEN);
				setState(569); ((FuncCallContext)_localctx).e1 = expression();
				setState(570); match(COMMA);
				setState(571); ((FuncCallContext)_localctx).e2 = expression();
				setState(572); match(PAR_CLOSE);
				 ((FuncCallContext)_localctx).node =  new LogicalAndNode(((FuncCallContext)_localctx).e1.node,((FuncCallContext)_localctx).e2.node); 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(575); match(OFUNC);
				setState(576); match(PAR_OPEN);
				setState(577); ((FuncCallContext)_localctx).e1 = expression();
				setState(578); match(PAR_CLOSE);
				setState(579); match(PAR_OPEN);
				setState(580); ((FuncCallContext)_localctx).e2 = expression();
				setState(581); match(PAR_CLOSE);
				 ((FuncCallContext)_localctx).node =  new LogicalOrNode(((FuncCallContext)_localctx).e1.node,((FuncCallContext)_localctx).e2.node); 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(584); match(OFUNC);
				setState(585); match(PAR_OPEN);
				setState(586); ((FuncCallContext)_localctx).e1 = expression();
				setState(587); match(COMMA);
				setState(588); ((FuncCallContext)_localctx).e2 = expression();
				setState(589); match(PAR_CLOSE);
				 ((FuncCallContext)_localctx).node =  new LogicalOrNode(((FuncCallContext)_localctx).e1.node,((FuncCallContext)_localctx).e2.node); 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(592); match(MAYORQ);
				setState(593); match(PAR_OPEN);
				setState(594); ((FuncCallContext)_localctx).e1 = expression();
				setState(595); match(COMMA);
				setState(596); ((FuncCallContext)_localctx).e2 = expression();
				setState(597); match(PAR_CLOSE);
				 ((FuncCallContext)_localctx).node =  new GreaterThanNode(((FuncCallContext)_localctx).e1.node,((FuncCallContext)_localctx).e2.node);  
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(600); match(MENORQ);
				setState(601); match(PAR_OPEN);
				setState(602); ((FuncCallContext)_localctx).e1 = expression();
				setState(603); match(COMMA);
				setState(604); ((FuncCallContext)_localctx).e2 = expression();
				setState(605); match(PAR_CLOSE);
				 ((FuncCallContext)_localctx).node =  new LessThanNode(((FuncCallContext)_localctx).e1.node,((FuncCallContext)_localctx).e2.node);  
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(608); match(AZAR);
				setState(609); match(PAR_OPEN);
				setState(610); ((FuncCallContext)_localctx).e = expression();
				setState(611); match(PAR_CLOSE);
				 ((FuncCallContext)_localctx).node =  new RandomNode(((FuncCallContext)_localctx).e.node); 
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(614); match(PRODUCTO);
				setState(615); match(PAR_OPEN);
				setState(616); ((FuncCallContext)_localctx).e1 = expression();
				setState(621);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(617); match(COMMA);
					setState(618); ((FuncCallContext)_localctx).expression = expression();
					((FuncCallContext)_localctx).rest.add(((FuncCallContext)_localctx).expression);
					}
					}
					setState(623);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(624); match(PAR_CLOSE);

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
				setState(627); match(POTENCIA);
				setState(628); match(PAR_OPEN);
				setState(629); ((FuncCallContext)_localctx).e1 = expression();
				setState(630); match(COMMA);
				setState(631); ((FuncCallContext)_localctx).e2 = expression();
				setState(632); match(PAR_CLOSE);
				 ((FuncCallContext)_localctx).node =  new ExponentiationNode(((FuncCallContext)_localctx).e1.node, ((FuncCallContext)_localctx).e2.node); 
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(635); match(DIVISION);
				setState(636); match(PAR_OPEN);
				setState(637); ((FuncCallContext)_localctx).e1 = expression();
				setState(638); match(COMMA);
				setState(639); ((FuncCallContext)_localctx).e2 = expression();
				setState(640); match(PAR_CLOSE);
				 ((FuncCallContext)_localctx).node =  new DivisionNode(((FuncCallContext)_localctx).e1.node, ((FuncCallContext)_localctx).e2.node); 
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(643); match(SUMA);
				setState(644); match(PAR_OPEN);
				setState(645); ((FuncCallContext)_localctx).e1 = expression();
				setState(650);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(646); match(COMMA);
					setState(647); ((FuncCallContext)_localctx).expression = expression();
					((FuncCallContext)_localctx).rest.add(((FuncCallContext)_localctx).expression);
					}
					}
					setState(652);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(653); match(PAR_CLOSE);

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
				setState(656); match(DIFERENCIA);
				setState(657); match(PAR_OPEN);
				setState(658); ((FuncCallContext)_localctx).e1 = expression();
				setState(663);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(659); match(COMMA);
					setState(660); ((FuncCallContext)_localctx).expression = expression();
					((FuncCallContext)_localctx).rest.add(((FuncCallContext)_localctx).expression);
					}
					}
					setState(665);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(666); match(PAR_CLOSE);

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
		public TerminalNode ID() { return getToken(LogoTecParser.ID, 0); }
		public TerminalNode PAR_CLOSE() { return getToken(LogoTecParser.PAR_CLOSE, 0); }
		public TerminalNode STRING() { return getToken(LogoTecParser.STRING, 0); }
		public TerminalNode PAR_OPEN() { return getToken(LogoTecParser.PAR_OPEN, 0); }
		public TerminalNode NUMBER() { return getToken(LogoTecParser.NUMBER, 0); }
		public TerminalNode BOOLEAN() { return getToken(LogoTecParser.BOOLEAN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
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
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_primary);
		try {
			setState(686);
			switch (_input.LA(1)) {
			case DECIMAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(671); ((PrimaryContext)_localctx).DECIMAL = match(DECIMAL);

				        double v = Double.parseDouble((((PrimaryContext)_localctx).DECIMAL!=null?((PrimaryContext)_localctx).DECIMAL.getText():null).replace(',', '.'));
				        ((PrimaryContext)_localctx).node =  new ConstNode(v);
				        ((PrimaryContext)_localctx).val =  Value.unknown(); // se mantiene inferencia desconocida si no hay soporte de double en Value
				      
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(673); ((PrimaryContext)_localctx).NUMBER = match(NUMBER);
				 
				        int v = Integer.parseInt((((PrimaryContext)_localctx).NUMBER!=null?((PrimaryContext)_localctx).NUMBER.getText():null));
				        ((PrimaryContext)_localctx).node =  new ConstNode(v);
				        ((PrimaryContext)_localctx).val =  Value.integer(v);
				      
				}
				break;
			case BOOLEAN:
				enterOuterAlt(_localctx, 3);
				{
				setState(675); ((PrimaryContext)_localctx).BOOLEAN = match(BOOLEAN);
				 
				        boolean b = Boolean.parseBoolean((((PrimaryContext)_localctx).BOOLEAN!=null?((PrimaryContext)_localctx).BOOLEAN.getText():null));
				        ((PrimaryContext)_localctx).node =  new ConstNode(b);
				        ((PrimaryContext)_localctx).val =  Value.bool(b);
				      
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 4);
				{
				setState(677); ((PrimaryContext)_localctx).ID = match(ID);
				 
				        ((PrimaryContext)_localctx).node =  new VarRefNode((((PrimaryContext)_localctx).ID!=null?((PrimaryContext)_localctx).ID.getText():null));
				        ((PrimaryContext)_localctx).val =  Value.unknown();
				      
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 5);
				{
				setState(679); ((PrimaryContext)_localctx).STRING = match(STRING);

				        String s = (((PrimaryContext)_localctx).STRING!=null?((PrimaryContext)_localctx).STRING.getText():null).substring(1,(((PrimaryContext)_localctx).STRING!=null?((PrimaryContext)_localctx).STRING.getText():null).length()-1);
				        ((PrimaryContext)_localctx).node =  new ConstNode(s);
				        ((PrimaryContext)_localctx).val =  Value.string(s);
				      
				}
				break;
			case PAR_OPEN:
				enterOuterAlt(_localctx, 6);
				{
				setState(681); match(PAR_OPEN);
				setState(682); ((PrimaryContext)_localctx).expression = expression();
				setState(683); match(PAR_CLOSE);
				 
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
		public TerminalNode DECIMAL() { return getToken(LogoTecParser.DECIMAL, 0); }
		public TerminalNode MINUS() { return getToken(LogoTecParser.MINUS, 0); }
		public TerminalNode STRING() { return getToken(LogoTecParser.STRING, 0); }
		public TerminalNode NUMBER() { return getToken(LogoTecParser.NUMBER, 0); }
		public TerminalNode BOOLEAN() { return getToken(LogoTecParser.BOOLEAN, 0); }
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
	}

	public final LiteralOrStringContext literalOrString() throws RecognitionException {
		LiteralOrStringContext _localctx = new LiteralOrStringContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_literalOrString);
		try {
			setState(702);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(688); match(MINUS);
				setState(689); ((LiteralOrStringContext)_localctx).DECIMAL = match(DECIMAL);

				        double v = -Double.parseDouble((((LiteralOrStringContext)_localctx).DECIMAL!=null?((LiteralOrStringContext)_localctx).DECIMAL.getText():null).replace(',', '.'));
				        ((LiteralOrStringContext)_localctx).node =  new ConstNode(v);
				        ((LiteralOrStringContext)_localctx).jval =  Double.valueOf(v);
				      
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(691); ((LiteralOrStringContext)_localctx).DECIMAL = match(DECIMAL);

				        double v = Double.parseDouble((((LiteralOrStringContext)_localctx).DECIMAL!=null?((LiteralOrStringContext)_localctx).DECIMAL.getText():null).replace(',', '.'));
				        ((LiteralOrStringContext)_localctx).node =  new ConstNode(v);
				        ((LiteralOrStringContext)_localctx).jval =  Double.valueOf(v);
				      
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(693); match(MINUS);
				setState(694); ((LiteralOrStringContext)_localctx).NUMBER = match(NUMBER);

				        int v = -Integer.parseInt((((LiteralOrStringContext)_localctx).NUMBER!=null?((LiteralOrStringContext)_localctx).NUMBER.getText():null));
				        ((LiteralOrStringContext)_localctx).node =  new ConstNode(v);
				        ((LiteralOrStringContext)_localctx).jval =  Integer.valueOf(v);
				      
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(696); ((LiteralOrStringContext)_localctx).NUMBER = match(NUMBER);

				        int v = Integer.parseInt((((LiteralOrStringContext)_localctx).NUMBER!=null?((LiteralOrStringContext)_localctx).NUMBER.getText():null));
				        ((LiteralOrStringContext)_localctx).node =  new ConstNode(v);
				        ((LiteralOrStringContext)_localctx).jval =  Integer.valueOf(v);
				      
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(698); ((LiteralOrStringContext)_localctx).BOOLEAN = match(BOOLEAN);

				        boolean v = Boolean.parseBoolean((((LiteralOrStringContext)_localctx).BOOLEAN!=null?((LiteralOrStringContext)_localctx).BOOLEAN.getText():null));
				        ((LiteralOrStringContext)_localctx).node =  new ConstNode(v);
				        ((LiteralOrStringContext)_localctx).jval =  Boolean.valueOf(v);
				      
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(700); ((LiteralOrStringContext)_localctx).STRING = match(STRING);

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
	}

	public final CmtFirstLineContext cmtFirstLine() throws RecognitionException {
		CmtFirstLineContext _localctx = new CmtFirstLineContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_cmtFirstLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(704); match(FIRSTLINE_COMMENT);
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

	public static class ColorNameContext extends ParserRuleContext {
		public Token c;
		public TerminalNode COLOR() { return getToken(LogoTecParser.COLOR, 0); }
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
	}

	public final ColorNameContext colorName() throws RecognitionException {
		ColorNameContext _localctx = new ColorNameContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_colorName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(707); ((ColorNameContext)_localctx).c = match(COLOR);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3Q\u02c8\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2\5\2@"+
		"\n\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\7\3L\n\3\f\3\16\3O\13\3\3"+
		"\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\7\4Y\n\4\f\4\16\4\\\13\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\7\4d\n\4\f\4\16\4g\13\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u008e\n\5\3\6\3\6\3\6\3"+
		"\6\5\6\u0094\n\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3"+
		"\b\5\b\u00a4\n\b\3\b\3\b\3\b\7\b\u00a9\n\b\f\b\16\b\u00ac\13\b\5\b\u00ae"+
		"\n\b\3\b\3\b\3\b\3\b\5\b\u00b4\n\b\3\b\3\b\3\b\7\b\u00b9\n\b\f\b\16\b"+
		"\u00bc\13\b\5\b\u00be\n\b\5\b\u00c0\n\b\3\b\3\b\3\t\3\t\3\t\5\t\u00c7"+
		"\n\t\3\t\3\t\3\t\7\t\u00cc\n\t\f\t\16\t\u00cf\13\t\5\t\u00d1\n\t\3\n\3"+
		"\n\3\n\3\n\3\n\7\n\u00d8\n\n\f\n\16\n\u00db\13\n\3\n\3\n\3\n\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\7\13\u00e6\n\13\f\13\16\13\u00e9\13\13\3\13\3\13"+
		"\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00f6\n\f\f\f\16\f\u00f9\13"+
		"\f\3\f\3\f\3\f\3\f\3\f\7\f\u0100\n\f\f\f\16\f\u0103\13\f\3\f\5\f\u0106"+
		"\n\f\3\f\3\f\3\r\3\r\5\r\u010c\n\r\3\r\3\r\3\r\3\r\7\r\u0112\n\r\f\r\16"+
		"\r\u0115\13\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\5\16\u0120\n\16\3"+
		"\16\3\16\3\16\3\16\7\16\u0126\n\16\f\16\16\16\u0129\13\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\7\17\u013a"+
		"\n\17\f\17\16\17\u013d\13\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\7\20\u014a\n\20\f\20\16\20\u014d\13\20\3\20\3\20\3\20\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u01ad\n\21\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\7\22\u01b5\n\22\f\22\16\22\u01b8\13\22\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\7\23\u01c0\n\23\f\23\16\23\u01c3\13\23\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\5\24\u01cc\n\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\7\25\u01e8\n\25\f\25\16\25\u01eb\13\25\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\7\26\u01f7\n\26\f\26\16\26\u01fa"+
		"\13\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\7\27\u0206\n"+
		"\27\f\27\16\27\u0209\13\27\3\30\3\30\3\30\3\30\3\30\3\30\7\30\u0211\n"+
		"\30\f\30\16\30\u0214\13\30\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\5\32\u0227\n\32\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\7\33\u026e\n\33\f\33"+
		"\16\33\u0271\13\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3"+
		"\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\7"+
		"\33\u028b\n\33\f\33\16\33\u028e\13\33\3\33\3\33\3\33\3\33\3\33\3\33\3"+
		"\33\3\33\7\33\u0298\n\33\f\33\16\33\u029b\13\33\3\33\3\33\3\33\5\33\u02a0"+
		"\n\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\5\34\u02b1\n\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\5\35\u02c1\n\35\3\36\3\36\3\36\3\37\3\37\3\37"+
		"\2\2 \2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<\2"+
		"\2\u0311\2?\3\2\2\2\4M\3\2\2\2\6R\3\2\2\2\b\u008d\3\2\2\2\n\u008f\3\2"+
		"\2\2\f\u0097\3\2\2\2\16\u009e\3\2\2\2\20\u00d0\3\2\2\2\22\u00d2\3\2\2"+
		"\2\24\u00df\3\2\2\2\26\u00ed\3\2\2\2\30\u0109\3\2\2\2\32\u011d\3\2\2\2"+
		"\34\u0131\3\2\2\2\36\u0141\3\2\2\2 \u01ac\3\2\2\2\"\u01ae\3\2\2\2$\u01b9"+
		"\3\2\2\2&\u01cb\3\2\2\2(\u01cd\3\2\2\2*\u01ec\3\2\2\2,\u01fb\3\2\2\2."+
		"\u020a\3\2\2\2\60\u0215\3\2\2\2\62\u0226\3\2\2\2\64\u029f\3\2\2\2\66\u02b0"+
		"\3\2\2\28\u02c0\3\2\2\2:\u02c2\3\2\2\2<\u02c5\3\2\2\2>@\5:\36\2?>\3\2"+
		"\2\2?@\3\2\2\2@A\3\2\2\2AB\5\4\3\2BC\7\2\2\3CD\b\2\1\2D\3\3\2\2\2EF\5"+
		"\6\4\2FG\b\3\1\2GL\3\2\2\2HI\5\b\5\2IJ\b\3\1\2JL\3\2\2\2KE\3\2\2\2KH\3"+
		"\2\2\2LO\3\2\2\2MK\3\2\2\2MN\3\2\2\2NP\3\2\2\2OM\3\2\2\2PQ\b\3\1\2Q\5"+
		"\3\2\2\2RS\7\3\2\2ST\7M\2\2TU\b\4\1\2UZ\7B\2\2VW\7M\2\2WY\b\4\1\2XV\3"+
		"\2\2\2Y\\\3\2\2\2ZX\3\2\2\2Z[\3\2\2\2[]\3\2\2\2\\Z\3\2\2\2]^\7C\2\2^_"+
		"\b\4\1\2_e\7B\2\2`a\5\b\5\2ab\b\4\1\2bd\3\2\2\2c`\3\2\2\2dg\3\2\2\2ec"+
		"\3\2\2\2ef\3\2\2\2fh\3\2\2\2ge\3\2\2\2hi\7C\2\2ij\7\5\2\2jk\b\4\1\2k\7"+
		"\3\2\2\2lm\5\30\r\2mn\b\5\1\2n\u008e\3\2\2\2op\5\32\16\2pq\b\5\1\2q\u008e"+
		"\3\2\2\2rs\5\n\6\2st\b\5\1\2t\u008e\3\2\2\2uv\5\f\7\2vw\b\5\1\2w\u008e"+
		"\3\2\2\2xy\5\16\b\2yz\b\5\1\2z\u008e\3\2\2\2{|\5 \21\2|}\b\5\1\2}\u008e"+
		"\3\2\2\2~\177\5\26\f\2\177\u0080\b\5\1\2\u0080\u008e\3\2\2\2\u0081\u0082"+
		"\5\36\20\2\u0082\u0083\b\5\1\2\u0083\u008e\3\2\2\2\u0084\u0085\5\34\17"+
		"\2\u0085\u0086\b\5\1\2\u0086\u008e\3\2\2\2\u0087\u0088\5\24\13\2\u0088"+
		"\u0089\b\5\1\2\u0089\u008e\3\2\2\2\u008a\u008b\5\22\n\2\u008b\u008c\b"+
		"\5\1\2\u008c\u008e\3\2\2\2\u008dl\3\2\2\2\u008do\3\2\2\2\u008dr\3\2\2"+
		"\2\u008du\3\2\2\2\u008dx\3\2\2\2\u008d{\3\2\2\2\u008d~\3\2\2\2\u008d\u0081"+
		"\3\2\2\2\u008d\u0084\3\2\2\2\u008d\u0087\3\2\2\2\u008d\u008a\3\2\2\2\u008e"+
		"\t\3\2\2\2\u008f\u0090\7\4\2\2\u0090\u0091\7M\2\2\u0091\u0093\58\35\2"+
		"\u0092\u0094\7F\2\2\u0093\u0092\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0095"+
		"\3\2\2\2\u0095\u0096\b\6\1\2\u0096\13\3\2\2\2\u0097\u0098\7\32\2\2\u0098"+
		"\u0099\7M\2\2\u0099\u009a\7A\2\2\u009a\u009b\5\"\22\2\u009b\u009c\7F\2"+
		"\2\u009c\u009d\b\7\1\2\u009d\r\3\2\2\2\u009e\u00bf\7M\2\2\u009f\u00ad"+
		"\7B\2\2\u00a0\u00a1\5\"\22\2\u00a1\u00aa\b\b\1\2\u00a2\u00a4\7G\2\2\u00a3"+
		"\u00a2\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a6\5\""+
		"\22\2\u00a6\u00a7\b\b\1\2\u00a7\u00a9\3\2\2\2\u00a8\u00a3\3\2\2\2\u00a9"+
		"\u00ac\3\2\2\2\u00aa\u00a8\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00ae\3\2"+
		"\2\2\u00ac\u00aa\3\2\2\2\u00ad\u00a0\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae"+
		"\u00af\3\2\2\2\u00af\u00c0\7C\2\2\u00b0\u00b1\5\"\22\2\u00b1\u00ba\b\b"+
		"\1\2\u00b2\u00b4\7G\2\2\u00b3\u00b2\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4"+
		"\u00b5\3\2\2\2\u00b5\u00b6\5\"\22\2\u00b6\u00b7\b\b\1\2\u00b7\u00b9\3"+
		"\2\2\2\u00b8\u00b3\3\2\2\2\u00b9\u00bc\3\2\2\2\u00ba\u00b8\3\2\2\2\u00ba"+
		"\u00bb\3\2\2\2\u00bb\u00be\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bd\u00b0\3\2"+
		"\2\2\u00bd\u00be\3\2\2\2\u00be\u00c0\3\2\2\2\u00bf\u009f\3\2\2\2\u00bf"+
		"\u00bd\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00c2\b\b\1\2\u00c2\17\3\2\2"+
		"\2\u00c3\u00c4\5\"\22\2\u00c4\u00cd\b\t\1\2\u00c5\u00c7\7G\2\2\u00c6\u00c5"+
		"\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00c9\5\"\22\2"+
		"\u00c9\u00ca\b\t\1\2\u00ca\u00cc\3\2\2\2\u00cb\u00c6\3\2\2\2\u00cc\u00cf"+
		"\3\2\2\2\u00cd\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00d1\3\2\2\2\u00cf"+
		"\u00cd\3\2\2\2\u00d0\u00c3\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\21\3\2\2"+
		"\2\u00d2\u00d3\7\6\2\2\u00d3\u00d9\7B\2\2\u00d4\u00d5\5\b\5\2\u00d5\u00d6"+
		"\b\n\1\2\u00d6\u00d8\3\2\2\2\u00d7\u00d4\3\2\2\2\u00d8\u00db\3\2\2\2\u00d9"+
		"\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00dc\3\2\2\2\u00db\u00d9\3\2"+
		"\2\2\u00dc\u00dd\7C\2\2\u00dd\u00de\b\n\1\2\u00de\23\3\2\2\2\u00df\u00e0"+
		"\7\7\2\2\u00e0\u00e1\5\"\22\2\u00e1\u00e7\7B\2\2\u00e2\u00e3\5\b\5\2\u00e3"+
		"\u00e4\b\13\1\2\u00e4\u00e6\3\2\2\2\u00e5\u00e2\3\2\2\2\u00e6\u00e9\3"+
		"\2\2\2\u00e7\u00e5\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8\u00ea\3\2\2\2\u00e9"+
		"\u00e7\3\2\2\2\u00ea\u00eb\7C\2\2\u00eb\u00ec\b\13\1\2\u00ec\25\3\2\2"+
		"\2\u00ed\u00ee\7\27\2\2\u00ee\u00ef\7D\2\2\u00ef\u00f0\5\"\22\2\u00f0"+
		"\u00f1\7E\2\2\u00f1\u00f7\7B\2\2\u00f2\u00f3\5\b\5\2\u00f3\u00f4\b\f\1"+
		"\2\u00f4\u00f6\3\2\2\2\u00f5\u00f2\3\2\2\2\u00f6\u00f9\3\2\2\2\u00f7\u00f5"+
		"\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00fa\3\2\2\2\u00f9\u00f7\3\2\2\2\u00fa"+
		"\u0105\7C\2\2\u00fb\u0101\7B\2\2\u00fc\u00fd\5\b\5\2\u00fd\u00fe\b\f\1"+
		"\2\u00fe\u0100\3\2\2\2\u00ff\u00fc\3\2\2\2\u0100\u0103\3\2\2\2\u0101\u00ff"+
		"\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0104\3\2\2\2\u0103\u0101\3\2\2\2\u0104"+
		"\u0106\7C\2\2\u0105\u00fb\3\2\2\2\u0105\u0106\3\2\2\2\u0106\u0107\3\2"+
		"\2\2\u0107\u0108\b\f\1\2\u0108\27\3\2\2\2\u0109\u010b\7\4\2\2\u010a\u010c"+
		"\7H\2\2\u010b\u010a\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u010d\3\2\2\2\u010d"+
		"\u0113\7B\2\2\u010e\u010f\5\b\5\2\u010f\u0110\b\r\1\2\u0110\u0112\3\2"+
		"\2\2\u0111\u010e\3\2\2\2\u0112\u0115\3\2\2\2\u0113\u0111\3\2\2\2\u0113"+
		"\u0114\3\2\2\2\u0114\u0116\3\2\2\2\u0115\u0113\3\2\2\2\u0116\u0117\7C"+
		"\2\2\u0117\u0118\7\30\2\2\u0118\u0119\7D\2\2\u0119\u011a\5\"\22\2\u011a"+
		"\u011b\7E\2\2\u011b\u011c\b\r\1\2\u011c\31\3\2\2\2\u011d\u011f\7\4\2\2"+
		"\u011e\u0120\7H\2\2\u011f\u011e\3\2\2\2\u011f\u0120\3\2\2\2\u0120\u0121"+
		"\3\2\2\2\u0121\u0127\7B\2\2\u0122\u0123\5\b\5\2\u0123\u0124\b\16\1\2\u0124"+
		"\u0126\3\2\2\2\u0125\u0122\3\2\2\2\u0126\u0129\3\2\2\2\u0127\u0125\3\2"+
		"\2\2\u0127\u0128\3\2\2\2\u0128\u012a\3\2\2\2\u0129\u0127\3\2\2\2\u012a"+
		"\u012b\7C\2\2\u012b\u012c\7\31\2\2\u012c\u012d\7D\2\2\u012d\u012e\5\""+
		"\22\2\u012e\u012f\7E\2\2\u012f\u0130\b\16\1\2\u0130\33\3\2\2\2\u0131\u0132"+
		"\7\31\2\2\u0132\u0133\7D\2\2\u0133\u0134\5\"\22\2\u0134\u0135\7E\2\2\u0135"+
		"\u013b\7B\2\2\u0136\u0137\5\b\5\2\u0137\u0138\b\17\1\2\u0138\u013a\3\2"+
		"\2\2\u0139\u0136\3\2\2\2\u013a\u013d\3\2\2\2\u013b\u0139\3\2\2\2\u013b"+
		"\u013c\3\2\2\2\u013c\u013e\3\2\2\2\u013d\u013b\3\2\2\2\u013e\u013f\7C"+
		"\2\2\u013f\u0140\b\17\1\2\u0140\35\3\2\2\2\u0141\u0142\7\30\2\2\u0142"+
		"\u0143\7D\2\2\u0143\u0144\5\"\22\2\u0144\u0145\7E\2\2\u0145\u014b\7B\2"+
		"\2\u0146\u0147\5\b\5\2\u0147\u0148\b\20\1\2\u0148\u014a\3\2\2\2\u0149"+
		"\u0146\3\2\2\2\u014a\u014d\3\2\2\2\u014b\u0149\3\2\2\2\u014b\u014c\3\2"+
		"\2\2\u014c\u014e\3\2\2\2\u014d\u014b\3\2\2\2\u014e\u014f\7C\2\2\u014f"+
		"\u0150\b\20\1\2\u0150\37\3\2\2\2\u0151\u0152\7\33\2\2\u0152\u0153\5\""+
		"\22\2\u0153\u0154\b\21\1\2\u0154\u01ad\3\2\2\2\u0155\u0156\7\34\2\2\u0156"+
		"\u0157\5\"\22\2\u0157\u0158\b\21\1\2\u0158\u01ad\3\2\2\2\u0159\u015a\7"+
		"\35\2\2\u015a\u015b\5\"\22\2\u015b\u015c\b\21\1\2\u015c\u01ad\3\2\2\2"+
		"\u015d\u015e\7\36\2\2\u015e\u015f\5\"\22\2\u015f\u0160\b\21\1\2\u0160"+
		"\u01ad\3\2\2\2\u0161\u0162\7 \2\2\u0162\u0163\5\"\22\2\u0163\u0164\b\21"+
		"\1\2\u0164\u01ad\3\2\2\2\u0165\u0166\7!\2\2\u0166\u0167\5\"\22\2\u0167"+
		"\u0168\b\21\1\2\u0168\u01ad\3\2\2\2\u0169\u016a\7\"\2\2\u016a\u016b\5"+
		"\"\22\2\u016b\u016c\b\21\1\2\u016c\u01ad\3\2\2\2\u016d\u016e\7#\2\2\u016e"+
		"\u016f\5\"\22\2\u016f\u0170\b\21\1\2\u0170\u01ad\3\2\2\2\u0171\u0172\7"+
		"$\2\2\u0172\u01ad\b\21\1\2\u0173\u0174\7%\2\2\u0174\u01ad\b\21\1\2\u0175"+
		"\u0176\7\b\2\2\u0176\u01ad\b\21\1\2\u0177\u0178\7\t\2\2\u0178\u01ad\b"+
		"\21\1\2\u0179\u017a\7\n\2\2\u017a\u017b\7B\2\2\u017b\u017c\5\20\t\2\u017c"+
		"\u017d\7C\2\2\u017d\u017e\b\21\1\2\u017e\u01ad\3\2\2\2\u017f\u0180\7\13"+
		"\2\2\u0180\u0181\5\"\22\2\u0181\u0182\5\"\22\2\u0182\u0183\b\21\1\2\u0183"+
		"\u01ad\3\2\2\2\u0184\u0185\7\f\2\2\u0185\u0186\5\"\22\2\u0186\u0187\b"+
		"\21\1\2\u0187\u01ad\3\2\2\2\u0188\u0189\7\r\2\2\u0189\u01ad\b\21\1\2\u018a"+
		"\u018b\7\16\2\2\u018b\u018c\5\"\22\2\u018c\u018d\b\21\1\2\u018d\u01ad"+
		"\3\2\2\2\u018e\u018f\7\17\2\2\u018f\u0190\5\"\22\2\u0190\u0191\b\21\1"+
		"\2\u0191\u01ad\3\2\2\2\u0192\u0193\7\20\2\2\u0193\u01ad\b\21\1\2\u0194"+
		"\u0195\7\21\2\2\u0195\u01ad\b\21\1\2\u0196\u0197\7\22\2\2\u0197\u01ad"+
		"\b\21\1\2\u0198\u0199\7\23\2\2\u0199\u01ad\b\21\1\2\u019a\u019b\7\24\2"+
		"\2\u019b\u01ad\b\21\1\2\u019c\u019d\7\25\2\2\u019d\u019e\5\"\22\2\u019e"+
		"\u019f\b\21\1\2\u019f\u01ad\3\2\2\2\u01a0\u01a1\7\26\2\2\u01a1\u01a2\7"+
		"B\2\2\u01a2\u01a3\7M\2\2\u01a3\u01a4\7C\2\2\u01a4\u01ad\b\21\1\2\u01a5"+
		"\u01a6\7\26\2\2\u01a6\u01a7\7B\2\2\u01a7\u01a8\7M\2\2\u01a8\u01a9\5\""+
		"\22\2\u01a9\u01aa\7C\2\2\u01aa\u01ab\b\21\1\2\u01ab\u01ad\3\2\2\2\u01ac"+
		"\u0151\3\2\2\2\u01ac\u0155\3\2\2\2\u01ac\u0159\3\2\2\2\u01ac\u015d\3\2"+
		"\2\2\u01ac\u0161\3\2\2\2\u01ac\u0165\3\2\2\2\u01ac\u0169\3\2\2\2\u01ac"+
		"\u016d\3\2\2\2\u01ac\u0171\3\2\2\2\u01ac\u0173\3\2\2\2\u01ac\u0175\3\2"+
		"\2\2\u01ac\u0177\3\2\2\2\u01ac\u0179\3\2\2\2\u01ac\u017f\3\2\2\2\u01ac"+
		"\u0184\3\2\2\2\u01ac\u0188\3\2\2\2\u01ac\u018a\3\2\2\2\u01ac\u018e\3\2"+
		"\2\2\u01ac\u0192\3\2\2\2\u01ac\u0194\3\2\2\2\u01ac\u0196\3\2\2\2\u01ac"+
		"\u0198\3\2\2\2\u01ac\u019a\3\2\2\2\u01ac\u019c\3\2\2\2\u01ac\u01a0\3\2"+
		"\2\2\u01ac\u01a5\3\2\2\2\u01ad!\3\2\2\2\u01ae\u01af\5$\23\2\u01af\u01b6"+
		"\b\22\1\2\u01b0\u01b1\79\2\2\u01b1\u01b2\5$\23\2\u01b2\u01b3\b\22\1\2"+
		"\u01b3\u01b5\3\2\2\2\u01b4\u01b0\3\2\2\2\u01b5\u01b8\3\2\2\2\u01b6\u01b4"+
		"\3\2\2\2\u01b6\u01b7\3\2\2\2\u01b7#\3\2\2\2\u01b8\u01b6\3\2\2\2\u01b9"+
		"\u01ba\5&\24\2\u01ba\u01c1\b\23\1\2\u01bb\u01bc\78\2\2\u01bc\u01bd\5&"+
		"\24\2\u01bd\u01be\b\23\1\2\u01be\u01c0\3\2\2\2\u01bf\u01bb\3\2\2\2\u01c0"+
		"\u01c3\3\2\2\2\u01c1\u01bf\3\2\2\2\u01c1\u01c2\3\2\2\2\u01c2%\3\2\2\2"+
		"\u01c3\u01c1\3\2\2\2\u01c4\u01c5\7:\2\2\u01c5\u01c6\5&\24\2\u01c6\u01c7"+
		"\b\24\1\2\u01c7\u01cc\3\2\2\2\u01c8\u01c9\5(\25\2\u01c9\u01ca\b\24\1\2"+
		"\u01ca\u01cc\3\2\2\2\u01cb\u01c4\3\2\2\2\u01cb\u01c8\3\2\2\2\u01cc\'\3"+
		"\2\2\2\u01cd\u01ce\5*\26\2\u01ce\u01e9\b\25\1\2\u01cf\u01d0\7;\2\2\u01d0"+
		"\u01d1\5*\26\2\u01d1\u01d2\b\25\1\2\u01d2\u01e8\3\2\2\2\u01d3\u01d4\7"+
		"<\2\2\u01d4\u01d5\5*\26\2\u01d5\u01d6\b\25\1\2\u01d6\u01e8\3\2\2\2\u01d7"+
		"\u01d8\7=\2\2\u01d8\u01d9\5*\26\2\u01d9\u01da\b\25\1\2\u01da\u01e8\3\2"+
		"\2\2\u01db\u01dc\7>\2\2\u01dc\u01dd\5*\26\2\u01dd\u01de\b\25\1\2\u01de"+
		"\u01e8\3\2\2\2\u01df\u01e0\7?\2\2\u01e0\u01e1\5*\26\2\u01e1\u01e2\b\25"+
		"\1\2\u01e2\u01e8\3\2\2\2\u01e3\u01e4\7@\2\2\u01e4\u01e5\5*\26\2\u01e5"+
		"\u01e6\b\25\1\2\u01e6\u01e8\3\2\2\2\u01e7\u01cf\3\2\2\2\u01e7\u01d3\3"+
		"\2\2\2\u01e7\u01d7\3\2\2\2\u01e7\u01db\3\2\2\2\u01e7\u01df\3\2\2\2\u01e7"+
		"\u01e3\3\2\2\2\u01e8\u01eb\3\2\2\2\u01e9\u01e7\3\2\2\2\u01e9\u01ea\3\2"+
		"\2\2\u01ea)\3\2\2\2\u01eb\u01e9\3\2\2\2\u01ec\u01ed\5,\27\2\u01ed\u01f8"+
		"\b\26\1\2\u01ee\u01ef\7\63\2\2\u01ef\u01f0\5,\27\2\u01f0\u01f1\b\26\1"+
		"\2\u01f1\u01f7\3\2\2\2\u01f2\u01f3\7\64\2\2\u01f3\u01f4\5,\27\2\u01f4"+
		"\u01f5\b\26\1\2\u01f5\u01f7\3\2\2\2\u01f6\u01ee\3\2\2\2\u01f6\u01f2\3"+
		"\2\2\2\u01f7\u01fa\3\2\2\2\u01f8\u01f6\3\2\2\2\u01f8\u01f9\3\2\2\2\u01f9"+
		"+\3\2\2\2\u01fa\u01f8\3\2\2\2\u01fb\u01fc\5.\30\2\u01fc\u0207\b\27\1\2"+
		"\u01fd\u01fe\7\65\2\2\u01fe\u01ff\5.\30\2\u01ff\u0200\b\27\1\2\u0200\u0206"+
		"\3\2\2\2\u0201\u0202\7\66\2\2\u0202\u0203\5.\30\2\u0203\u0204\b\27\1\2"+
		"\u0204\u0206\3\2\2\2\u0205\u01fd\3\2\2\2\u0205\u0201\3\2\2\2\u0206\u0209"+
		"\3\2\2\2\u0207\u0205\3\2\2\2\u0207\u0208\3\2\2\2\u0208-\3\2\2\2\u0209"+
		"\u0207\3\2\2\2\u020a\u020b\5\60\31\2\u020b\u0212\b\30\1\2\u020c\u020d"+
		"\7\67\2\2\u020d\u020e\5\60\31\2\u020e\u020f\b\30\1\2\u020f\u0211\3\2\2"+
		"\2\u0210\u020c\3\2\2\2\u0211\u0214\3\2\2\2\u0212\u0210\3\2\2\2\u0212\u0213"+
		"\3\2\2\2\u0213/\3\2\2\2\u0214\u0212\3\2\2\2\u0215\u0216\5\62\32\2\u0216"+
		"\u0217\b\31\1\2\u0217\61\3\2\2\2\u0218\u0219\7\64\2\2\u0219\u021a\5\62"+
		"\32\2\u021a\u021b\b\32\1\2\u021b\u0227\3\2\2\2\u021c\u021d\7\63\2\2\u021d"+
		"\u021e\5\62\32\2\u021e\u021f\b\32\1\2\u021f\u0227\3\2\2\2\u0220\u0221"+
		"\5\64\33\2\u0221\u0222\b\32\1\2\u0222\u0227\3\2\2\2\u0223\u0224\5\66\34"+
		"\2\u0224\u0225\b\32\1\2\u0225\u0227\3\2\2\2\u0226\u0218\3\2\2\2\u0226"+
		"\u021c\3\2\2\2\u0226\u0220\3\2\2\2\u0226\u0223\3\2\2\2\u0227\63\3\2\2"+
		"\2\u0228\u0229\7(\2\2\u0229\u022a\7D\2\2\u022a\u022b\5\"\22\2\u022b\u022c"+
		"\7G\2\2\u022c\u022d\5\"\22\2\u022d\u022e\7E\2\2\u022e\u022f\b\33\1\2\u022f"+
		"\u02a0\3\2\2\2\u0230\u0231\7)\2\2\u0231\u0232\7D\2\2\u0232\u0233\5\"\22"+
		"\2\u0233\u0234\7E\2\2\u0234\u0235\7D\2\2\u0235\u0236\5\"\22\2\u0236\u0237"+
		"\7E\2\2\u0237\u0238\b\33\1\2\u0238\u02a0\3\2\2\2\u0239\u023a\7)\2\2\u023a"+
		"\u023b\7D\2\2\u023b\u023c\5\"\22\2\u023c\u023d\7G\2\2\u023d\u023e\5\""+
		"\22\2\u023e\u023f\7E\2\2\u023f\u0240\b\33\1\2\u0240\u02a0\3\2\2\2\u0241"+
		"\u0242\7*\2\2\u0242\u0243\7D\2\2\u0243\u0244\5\"\22\2\u0244\u0245\7E\2"+
		"\2\u0245\u0246\7D\2\2\u0246\u0247\5\"\22\2\u0247\u0248\7E\2\2\u0248\u0249"+
		"\b\33\1\2\u0249\u02a0\3\2\2\2\u024a\u024b\7*\2\2\u024b\u024c\7D\2\2\u024c"+
		"\u024d\5\"\22\2\u024d\u024e\7G\2\2\u024e\u024f\5\"\22\2\u024f\u0250\7"+
		"E\2\2\u0250\u0251\b\33\1\2\u0251\u02a0\3\2\2\2\u0252\u0253\7+\2\2\u0253"+
		"\u0254\7D\2\2\u0254\u0255\5\"\22\2\u0255\u0256\7G\2\2\u0256\u0257\5\""+
		"\22\2\u0257\u0258\7E\2\2\u0258\u0259\b\33\1\2\u0259\u02a0\3\2\2\2\u025a"+
		"\u025b\7,\2\2\u025b\u025c\7D\2\2\u025c\u025d\5\"\22\2\u025d\u025e\7G\2"+
		"\2\u025e\u025f\5\"\22\2\u025f\u0260\7E\2\2\u0260\u0261\b\33\1\2\u0261"+
		"\u02a0\3\2\2\2\u0262\u0263\7.\2\2\u0263\u0264\7D\2\2\u0264\u0265\5\"\22"+
		"\2\u0265\u0266\7E\2\2\u0266\u0267\b\33\1\2\u0267\u02a0\3\2\2\2\u0268\u0269"+
		"\7/\2\2\u0269\u026a\7D\2\2\u026a\u026f\5\"\22\2\u026b\u026c\7G\2\2\u026c"+
		"\u026e\5\"\22\2\u026d\u026b\3\2\2\2\u026e\u0271\3\2\2\2\u026f\u026d\3"+
		"\2\2\2\u026f\u0270\3\2\2\2\u0270\u0272\3\2\2\2\u0271\u026f\3\2\2\2\u0272"+
		"\u0273\7E\2\2\u0273\u0274\b\33\1\2\u0274\u02a0\3\2\2\2\u0275\u0276\7\60"+
		"\2\2\u0276\u0277\7D\2\2\u0277\u0278\5\"\22\2\u0278\u0279\7G\2\2\u0279"+
		"\u027a\5\"\22\2\u027a\u027b\7E\2\2\u027b\u027c\b\33\1\2\u027c\u02a0\3"+
		"\2\2\2\u027d\u027e\7\61\2\2\u027e\u027f\7D\2\2\u027f\u0280\5\"\22\2\u0280"+
		"\u0281\7G\2\2\u0281\u0282\5\"\22\2\u0282\u0283\7E\2\2\u0283\u0284\b\33"+
		"\1\2\u0284\u02a0\3\2\2\2\u0285\u0286\7\62\2\2\u0286\u0287\7D\2\2\u0287"+
		"\u028c\5\"\22\2\u0288\u0289\7G\2\2\u0289\u028b\5\"\22\2\u028a\u0288\3"+
		"\2\2\2\u028b\u028e\3\2\2\2\u028c\u028a\3\2\2\2\u028c\u028d\3\2\2\2\u028d"+
		"\u028f\3\2\2\2\u028e\u028c\3\2\2\2\u028f\u0290\7E\2\2\u0290\u0291\b\33"+
		"\1\2\u0291\u02a0\3\2\2\2\u0292\u0293\7-\2\2\u0293\u0294\7D\2\2\u0294\u0299"+
		"\5\"\22\2\u0295\u0296\7G\2\2\u0296\u0298\5\"\22\2\u0297\u0295\3\2\2\2"+
		"\u0298\u029b\3\2\2\2\u0299\u0297\3\2\2\2\u0299\u029a\3\2\2\2\u029a\u029c"+
		"\3\2\2\2\u029b\u0299\3\2\2\2\u029c\u029d\7E\2\2\u029d\u029e\b\33\1\2\u029e"+
		"\u02a0\3\2\2\2\u029f\u0228\3\2\2\2\u029f\u0230\3\2\2\2\u029f\u0239\3\2"+
		"\2\2\u029f\u0241\3\2\2\2\u029f\u024a\3\2\2\2\u029f\u0252\3\2\2\2\u029f"+
		"\u025a\3\2\2\2\u029f\u0262\3\2\2\2\u029f\u0268\3\2\2\2\u029f\u0275\3\2"+
		"\2\2\u029f\u027d\3\2\2\2\u029f\u0285\3\2\2\2\u029f\u0292\3\2\2\2\u02a0"+
		"\65\3\2\2\2\u02a1\u02a2\7J\2\2\u02a2\u02b1\b\34\1\2\u02a3\u02a4\7K\2\2"+
		"\u02a4\u02b1\b\34\1\2\u02a5\u02a6\7I\2\2\u02a6\u02b1\b\34\1\2\u02a7\u02a8"+
		"\7M\2\2\u02a8\u02b1\b\34\1\2\u02a9\u02aa\7L\2\2\u02aa\u02b1\b\34\1\2\u02ab"+
		"\u02ac\7D\2\2\u02ac\u02ad\5\"\22\2\u02ad\u02ae\7E\2\2\u02ae\u02af\b\34"+
		"\1\2\u02af\u02b1\3\2\2\2\u02b0\u02a1\3\2\2\2\u02b0\u02a3\3\2\2\2\u02b0"+
		"\u02a5\3\2\2\2\u02b0\u02a7\3\2\2\2\u02b0\u02a9\3\2\2\2\u02b0\u02ab\3\2"+
		"\2\2\u02b1\67\3\2\2\2\u02b2\u02b3\7\64\2\2\u02b3\u02b4\7J\2\2\u02b4\u02c1"+
		"\b\35\1\2\u02b5\u02b6\7J\2\2\u02b6\u02c1\b\35\1\2\u02b7\u02b8\7\64\2\2"+
		"\u02b8\u02b9\7K\2\2\u02b9\u02c1\b\35\1\2\u02ba\u02bb\7K\2\2\u02bb\u02c1"+
		"\b\35\1\2\u02bc\u02bd\7I\2\2\u02bd\u02c1\b\35\1\2\u02be\u02bf\7L\2\2\u02bf"+
		"\u02c1\b\35\1\2\u02c0\u02b2\3\2\2\2\u02c0\u02b5\3\2\2\2\u02c0\u02b7\3"+
		"\2\2\2\u02c0\u02ba\3\2\2\2\u02c0\u02bc\3\2\2\2\u02c0\u02be\3\2\2\2\u02c1"+
		"9\3\2\2\2\u02c2\u02c3\7N\2\2\u02c3\u02c4\b\36\1\2\u02c4;\3\2\2\2\u02c5"+
		"\u02c6\7Q\2\2\u02c6=\3\2\2\2\60?KMZe\u008d\u0093\u00a3\u00aa\u00ad\u00b3"+
		"\u00ba\u00bd\u00bf\u00c6\u00cd\u00d0\u00d9\u00e7\u00f7\u0101\u0105\u010b"+
		"\u0113\u011f\u0127\u013b\u014b\u01ac\u01b6\u01c1\u01cb\u01e7\u01e9\u01f6"+
		"\u01f8\u0205\u0207\u0212\u0226\u026f\u028c\u0299\u029f\u02b0\u02c0";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}