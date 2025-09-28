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
		PARA=1, FIN=2, EJECUTA=3, REPITE=4, SI=5, HASTA=6, MIENTRAS=7, HAZ=8, 
		INIC=9, AVANZA=10, AV=11, RETROCEDE=12, RE=13, GIRADERECHA=14, GD=15, 
		GIRAIzQUIERDA=16, GI=17, OCULTATORTUGA=18, OT=19, PONPOS=20, PONXY=21, 
		PONRUMBO=22, RUMBO=23, PONX=24, PONY=25, BAJALAPIZ=26, BL=27, SUBELAPIZ=28, 
		SB=29, PONCOLORLAPIZ=30, PONCL=31, CENTRO=32, ESPERA=33, INC=34, IGUALESQ=35, 
		YFUNC=36, OFUNC=37, MAYORQ=38, MENORQ=39, DIFERENCIA=40, AZAR=41, PRODUCTO=42, 
		POTENCIA=43, DIVISION=44, SUMA=45, PLUS=46, MINUS=47, MULT=48, DIV=49, 
		EXP=50, AND=51, OR=52, NOT=53, GT=54, LT=55, GEQ=56, LEQ=57, EQ=58, NEQ=59, 
		ASSIGN=60, BRACKET_OPEN=61, BRACKET_CLOSE=62, PAR_OPEN=63, PAR_CLOSE=64, 
		SEMICOLON=65, COMMA=66, DOT=67, BOOLEAN=68, NUMBER=69, STRING=70, ID=71, 
		COMMENT_LINE=72, WS=73, COLOR=74;
	public static final String[] tokenNames = {
		"<INVALID>", "'Para'", "'fin'", "'Ejecuta'", "REPITE", "'SI'", "'HASTA'", 
		"'MIENTRAS'", "HAZ", "'INIC'", "'avanza'", "'av'", "'retrocede'", "'re'", 
		"'giraderecha'", "'gd'", "'giraizquierda'", "'gi'", "'ocultatortuga'", 
		"'ot'", "'ponpos'", "'ponxy'", "'ponrumbo'", "'rumbo'", "'ponx'", "'pony'", 
		"'bajalapiz'", "'bl'", "'subelapiz'", "'sb'", "'poncolorlapiz'", "'poncl'", 
		"'centro'", "'espera'", "'inc'", "'iguales?'", "'Y'", "'O'", "'mayorque?'", 
		"'menorque?'", "'Diferencia'", "'azar'", "'producto'", "'potencia'", "'división'", 
		"'suma'", "'+'", "'-'", "'*'", "'/'", "'^'", "'&&'", "'||'", "'!'", "'>'", 
		"'<'", "'>='", "'<='", "'=='", "'!='", "'='", "BRACKET_OPEN", "BRACKET_CLOSE", 
		"'('", "')'", "';'", "','", "'.'", "BOOLEAN", "NUMBER", "STRING", "ID", 
		"COMMENT_LINE", "WS", "COLOR"
	};
	public static final int
		RULE_program = 0, RULE_proceduresBlock = 1, RULE_procedureDecl = 2, RULE_procParams = 3, 
		RULE_sentence = 4, RULE_varDecl = 5, RULE_varInit = 6, RULE_callProc = 7, 
		RULE_execBlock = 8, RULE_repiteBlock = 9, RULE_flowStmt = 10, RULE_siStmt = 11, 
		RULE_hazHastaStmt = 12, RULE_hastaStmt = 13, RULE_hazMientrasStmt = 14, 
		RULE_mientrasStmt = 15, RULE_turtleCmd = 16, RULE_expression = 17, RULE_logicTerm = 18, 
		RULE_logicFactor = 19, RULE_relational = 20, RULE_arithExpr = 21, RULE_term = 22, 
		RULE_factor = 23, RULE_exponent = 24, RULE_funcCall = 25, RULE_primary = 26, 
		RULE_literalOrString = 27, RULE_cmtFirstLine = 28, RULE_colorName = 29;
	public static final String[] ruleNames = {
		"program", "proceduresBlock", "procedureDecl", "procParams", "sentence", 
		"varDecl", "varInit", "callProc", "execBlock", "repiteBlock", "flowStmt", 
		"siStmt", "hazHastaStmt", "hastaStmt", "hazMientrasStmt", "mientrasStmt", 
		"turtleCmd", "expression", "logicTerm", "logicFactor", "relational", "arithExpr", 
		"term", "factor", "exponent", "funcCall", "primary", "literalOrString", 
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

	    void ensureProgramConstraints() {
	        if (!firstLineHasComment) {
	            errors.add("Error: debe existir al menos un comentario en la primera línea del programa.");
	        }
	        if (!atLeastOneVariable) {
	            errors.add("Error: el programa debe definir al menos una variable con 'Haz' o 'INIC'.");
	        }
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60); ((ProgramContext)_localctx).p = proceduresBlock();
			setState(61); match(EOF);

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
		public CmtFirstLineContext cmtFirstLine() {
			return getRuleContext(CmtFirstLineContext.class,0);
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
		 List<ProcDeclNode> decls = new ArrayList<>(); List<StmtNode> mainBody = new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			_la = _input.LA(1);
			if (_la==COMMENT_LINE) {
				{
				setState(64); cmtFirstLine();
				firstLineHasComment = true;
				}
			}

			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PARA) {
				{
				{
				setState(69); ((ProceduresBlockContext)_localctx).procedureDecl = procedureDecl();
				decls.add(((ProceduresBlockContext)_localctx).procedureDecl.node);
				}
				}
				setState(76);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(82);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EJECUTA) | (1L << REPITE) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << HAZ) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIzQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC))) != 0) || _la==ID) {
				{
				{
				setState(77); ((ProceduresBlockContext)_localctx).sentence = sentence();
				mainBody.add(((ProceduresBlockContext)_localctx).sentence.node);
				}
				}
				setState(84);
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
		public ProcParamsContext procParams;
		public SentenceContext sentence;
		public TerminalNode ID() { return getToken(LogoTecParser.ID, 0); }
		public ProcParamsContext procParams() {
			return getRuleContext(ProcParamsContext.class,0);
		}
		public TerminalNode PARA() { return getToken(LogoTecParser.PARA, 0); }
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public TerminalNode BRACKET_CLOSE() { return getToken(LogoTecParser.BRACKET_CLOSE, 0); }
		public TerminalNode BRACKET_OPEN() { return getToken(LogoTecParser.BRACKET_OPEN, 0); }
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
		 List<String> params = new ArrayList<>(); List<StmtNode> body = new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87); match(PARA);
			setState(88); ((ProcedureDeclContext)_localctx).procName = match(ID);
			setState(92);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(89); ((ProcedureDeclContext)_localctx).procParams = procParams();
				 for (Token p : ((ProcedureDeclContext)_localctx).procParams.list) params.add(p.getText()); 
				}
				break;
			}
			setState(94); match(BRACKET_OPEN);
			setState(100);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EJECUTA) | (1L << REPITE) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << HAZ) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIzQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC))) != 0) || _la==ID) {
				{
				{
				setState(95); ((ProcedureDeclContext)_localctx).sentence = sentence();
				body.add(((ProcedureDeclContext)_localctx).sentence.node);
				}
				}
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(103); match(BRACKET_CLOSE);
			setState(104); match(FIN);

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

	public static class ProcParamsContext extends ParserRuleContext {
		public List<Token> list;
		public Token param;
		public List<TerminalNode> ID() { return getTokens(LogoTecParser.ID); }
		public TerminalNode BRACKET_CLOSE() { return getToken(LogoTecParser.BRACKET_CLOSE, 0); }
		public TerminalNode ID(int i) {
			return getToken(LogoTecParser.ID, i);
		}
		public TerminalNode BRACKET_OPEN() { return getToken(LogoTecParser.BRACKET_OPEN, 0); }
		public ProcParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_procParams; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterProcParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitProcParams(this);
		}
	}

	public final ProcParamsContext procParams() throws RecognitionException {
		ProcParamsContext _localctx = new ProcParamsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_procParams);
		 ((ProcParamsContext)_localctx).list =  new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107); match(BRACKET_OPEN);
			setState(112);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(108); ((ProcParamsContext)_localctx).param = match(ID);
				 _localctx.list.add(((ProcParamsContext)_localctx).param); 
				}
				}
				setState(114);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(115); match(BRACKET_CLOSE);
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
		public TurtleCmdContext turtleCmd;
		public FlowStmtContext flowStmt;
		public ExecBlockContext execBlock;
		public CallProcContext callProc;
		public FlowStmtContext flowStmt() {
			return getRuleContext(FlowStmtContext.class,0);
		}
		public VarInitContext varInit() {
			return getRuleContext(VarInitContext.class,0);
		}
		public VarDeclContext varDecl() {
			return getRuleContext(VarDeclContext.class,0);
		}
		public TurtleCmdContext turtleCmd() {
			return getRuleContext(TurtleCmdContext.class,0);
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
		enterRule(_localctx, 8, RULE_sentence);
		try {
			setState(135);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(117); ((SentenceContext)_localctx).varDecl = varDecl();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).varDecl.node; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(120); ((SentenceContext)_localctx).varInit = varInit();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).varInit.node; 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(123); ((SentenceContext)_localctx).turtleCmd = turtleCmd();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).turtleCmd.node; 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(126); ((SentenceContext)_localctx).flowStmt = flowStmt();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).flowStmt.node; 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(129); ((SentenceContext)_localctx).execBlock = execBlock();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).execBlock.node; 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(132); ((SentenceContext)_localctx).callProc = callProc();
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
		enterRule(_localctx, 10, RULE_varDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137); match(HAZ);
			setState(138); ((VarDeclContext)_localctx).name = match(ID);
			setState(139); ((VarDeclContext)_localctx).value = literalOrString();
			setState(141);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(140); match(SEMICOLON);
				}
			}


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
		enterRule(_localctx, 12, RULE_varInit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145); match(INIC);
			setState(146); ((VarInitContext)_localctx).name = match(ID);
			setState(147); match(ASSIGN);
			setState(148); ((VarInitContext)_localctx).expression = expression();
			setState(149); match(SEMICOLON);

			        Value v = ((VarInitContext)_localctx).expression.val;
			        declareOrAssign((((VarInitContext)_localctx).name!=null?((VarInitContext)_localctx).name.getText():null), v.type, v.raw);
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
		public TerminalNode BRACKET_CLOSE() { return getToken(LogoTecParser.BRACKET_CLOSE, 0); }
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode BRACKET_OPEN() { return getToken(LogoTecParser.BRACKET_OPEN, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
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
		enterRule(_localctx, 14, RULE_callProc);
		 List<ExprNode> args = new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152); ((CallProcContext)_localctx).proc = match(ID);
			setState(163);
			_la = _input.LA(1);
			if (_la==BRACKET_OPEN) {
				{
				setState(153); match(BRACKET_OPEN);
				setState(159);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 35)) & ~0x3f) == 0 && ((1L << (_la - 35)) & ((1L << (IGUALESQ - 35)) | (1L << (YFUNC - 35)) | (1L << (OFUNC - 35)) | (1L << (MAYORQ - 35)) | (1L << (MENORQ - 35)) | (1L << (DIFERENCIA - 35)) | (1L << (AZAR - 35)) | (1L << (PRODUCTO - 35)) | (1L << (POTENCIA - 35)) | (1L << (DIVISION - 35)) | (1L << (SUMA - 35)) | (1L << (NOT - 35)) | (1L << (PAR_OPEN - 35)) | (1L << (BOOLEAN - 35)) | (1L << (NUMBER - 35)) | (1L << (STRING - 35)) | (1L << (ID - 35)))) != 0)) {
					{
					{
					setState(154); ((CallProcContext)_localctx).expression = expression();
					args.add(((CallProcContext)_localctx).expression.node);
					}
					}
					setState(161);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(162); match(BRACKET_CLOSE);
				}
			}


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
			setState(167); match(EJECUTA);
			setState(168); match(BRACKET_OPEN);
			setState(174);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EJECUTA) | (1L << REPITE) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << HAZ) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIzQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC))) != 0) || _la==ID) {
				{
				{
				setState(169); ((ExecBlockContext)_localctx).sentence = sentence();
				body.add(((ExecBlockContext)_localctx).sentence.node);
				}
				}
				setState(176);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(177); match(BRACKET_CLOSE);

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
			setState(180); match(REPITE);
			setState(181); ((RepiteBlockContext)_localctx).times = expression();
			setState(182); match(BRACKET_OPEN);
			setState(188);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EJECUTA) | (1L << REPITE) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << HAZ) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIzQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC))) != 0) || _la==ID) {
				{
				{
				setState(183); ((RepiteBlockContext)_localctx).sentence = sentence();
				body.add(((RepiteBlockContext)_localctx).sentence.node);
				}
				}
				setState(190);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(191); match(BRACKET_CLOSE);

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

	public static class FlowStmtContext extends ParserRuleContext {
		public StmtNode node;
		public SiStmtContext siStmt;
		public HazHastaStmtContext hazHastaStmt;
		public HastaStmtContext hastaStmt;
		public HazMientrasStmtContext hazMientrasStmt;
		public MientrasStmtContext mientrasStmt;
		public RepiteBlockContext repiteBlock;
		public HazHastaStmtContext hazHastaStmt() {
			return getRuleContext(HazHastaStmtContext.class,0);
		}
		public HastaStmtContext hastaStmt() {
			return getRuleContext(HastaStmtContext.class,0);
		}
		public SiStmtContext siStmt() {
			return getRuleContext(SiStmtContext.class,0);
		}
		public HazMientrasStmtContext hazMientrasStmt() {
			return getRuleContext(HazMientrasStmtContext.class,0);
		}
		public MientrasStmtContext mientrasStmt() {
			return getRuleContext(MientrasStmtContext.class,0);
		}
		public RepiteBlockContext repiteBlock() {
			return getRuleContext(RepiteBlockContext.class,0);
		}
		public FlowStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_flowStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).enterFlowStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogoTecListener ) ((LogoTecListener)listener).exitFlowStmt(this);
		}
	}

	public final FlowStmtContext flowStmt() throws RecognitionException {
		FlowStmtContext _localctx = new FlowStmtContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_flowStmt);
		try {
			setState(212);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(194); ((FlowStmtContext)_localctx).siStmt = siStmt();
				 ((FlowStmtContext)_localctx).node =  ((FlowStmtContext)_localctx).siStmt.node; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(197); ((FlowStmtContext)_localctx).hazHastaStmt = hazHastaStmt();
				 ((FlowStmtContext)_localctx).node =  ((FlowStmtContext)_localctx).hazHastaStmt.node; 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(200); ((FlowStmtContext)_localctx).hastaStmt = hastaStmt();
				 ((FlowStmtContext)_localctx).node =  ((FlowStmtContext)_localctx).hastaStmt.node; 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(203); ((FlowStmtContext)_localctx).hazMientrasStmt = hazMientrasStmt();
				 ((FlowStmtContext)_localctx).node =  ((FlowStmtContext)_localctx).hazMientrasStmt.node; 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(206); ((FlowStmtContext)_localctx).mientrasStmt = mientrasStmt();
				 ((FlowStmtContext)_localctx).node =  ((FlowStmtContext)_localctx).mientrasStmt.node; 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(209); ((FlowStmtContext)_localctx).repiteBlock = repiteBlock();
				 ((FlowStmtContext)_localctx).node =  ((FlowStmtContext)_localctx).repiteBlock.node; 
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
		enterRule(_localctx, 22, RULE_siStmt);
		 List<StmtNode> thenBody = new ArrayList<>(); List<StmtNode> elseBody = new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214); match(SI);
			setState(215); match(PAR_OPEN);
			setState(216); ((SiStmtContext)_localctx).cond = expression();
			setState(217); match(PAR_CLOSE);
			setState(218); match(BRACKET_OPEN);
			setState(224);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EJECUTA) | (1L << REPITE) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << HAZ) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIzQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC))) != 0) || _la==ID) {
				{
				{
				setState(219); ((SiStmtContext)_localctx).sentence = sentence();
				thenBody.add(((SiStmtContext)_localctx).sentence.node);
				}
				}
				setState(226);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(227); match(BRACKET_CLOSE);
			setState(238);
			_la = _input.LA(1);
			if (_la==BRACKET_OPEN) {
				{
				setState(228); match(BRACKET_OPEN);
				setState(234);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EJECUTA) | (1L << REPITE) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << HAZ) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIzQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC))) != 0) || _la==ID) {
					{
					{
					setState(229); ((SiStmtContext)_localctx).sentence = sentence();
					elseBody.add(((SiStmtContext)_localctx).sentence.node);
					}
					}
					setState(236);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(237); match(BRACKET_CLOSE);
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
		enterRule(_localctx, 24, RULE_hazHastaStmt);
		 List<StmtNode> body = new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242); match(HAZ);
			setState(243); match(DOT);
			setState(244); match(HASTA);
			setState(245); match(BRACKET_OPEN);
			setState(251);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EJECUTA) | (1L << REPITE) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << HAZ) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIzQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC))) != 0) || _la==ID) {
				{
				{
				setState(246); ((HazHastaStmtContext)_localctx).sentence = sentence();
				body.add(((HazHastaStmtContext)_localctx).sentence.node);
				}
				}
				setState(253);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(254); match(BRACKET_CLOSE);
			setState(255); match(PAR_OPEN);
			setState(256); ((HazHastaStmtContext)_localctx).cond = expression();
			setState(257); match(PAR_CLOSE);

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
		enterRule(_localctx, 26, RULE_hastaStmt);
		 List<StmtNode> body = new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260); match(HASTA);
			setState(261); match(PAR_OPEN);
			setState(262); ((HastaStmtContext)_localctx).cond = expression();
			setState(263); match(PAR_CLOSE);
			setState(264); match(BRACKET_OPEN);
			setState(270);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EJECUTA) | (1L << REPITE) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << HAZ) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIzQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC))) != 0) || _la==ID) {
				{
				{
				setState(265); ((HastaStmtContext)_localctx).sentence = sentence();
				body.add(((HastaStmtContext)_localctx).sentence.node);
				}
				}
				setState(272);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(273); match(BRACKET_CLOSE);

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
		enterRule(_localctx, 28, RULE_hazMientrasStmt);
		 List<StmtNode> body = new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276); match(HAZ);
			setState(277); match(DOT);
			setState(278); match(MIENTRAS);
			setState(279); match(BRACKET_OPEN);
			setState(285);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EJECUTA) | (1L << REPITE) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << HAZ) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIzQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC))) != 0) || _la==ID) {
				{
				{
				setState(280); ((HazMientrasStmtContext)_localctx).sentence = sentence();
				body.add(((HazMientrasStmtContext)_localctx).sentence.node);
				}
				}
				setState(287);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(288); match(BRACKET_CLOSE);
			setState(289); match(PAR_OPEN);
			setState(290); ((HazMientrasStmtContext)_localctx).cond = expression();
			setState(291); match(PAR_CLOSE);

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
		enterRule(_localctx, 30, RULE_mientrasStmt);
		 List<StmtNode> body = new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(294); match(MIENTRAS);
			setState(295); match(PAR_OPEN);
			setState(296); ((MientrasStmtContext)_localctx).cond = expression();
			setState(297); match(PAR_CLOSE);
			setState(298); match(BRACKET_OPEN);
			setState(304);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EJECUTA) | (1L << REPITE) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << HAZ) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIzQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC))) != 0) || _la==ID) {
				{
				{
				setState(299); ((MientrasStmtContext)_localctx).sentence = sentence();
				body.add(((MientrasStmtContext)_localctx).sentence.node);
				}
				}
				setState(306);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(307); match(BRACKET_CLOSE);

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

	public static class TurtleCmdContext extends ParserRuleContext {
		public StmtNode node;
		public ExpressionContext e;
		public ExpressionContext x;
		public ExpressionContext y;
		public Token id;
		public ExpressionContext n;
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
		public TerminalNode SUBELAPIZ() { return getToken(LogoTecParser.SUBELAPIZ, 0); }
		public TerminalNode GIRADERECHA() { return getToken(LogoTecParser.GIRADERECHA, 0); }
		public TerminalNode OT() { return getToken(LogoTecParser.OT, 0); }
		public TerminalNode OCULTATORTUGA() { return getToken(LogoTecParser.OCULTATORTUGA, 0); }
		public TerminalNode RETROCEDE() { return getToken(LogoTecParser.RETROCEDE, 0); }
		public TerminalNode PONPOS() { return getToken(LogoTecParser.PONPOS, 0); }
		public TerminalNode BRACKET_OPEN() { return getToken(LogoTecParser.BRACKET_OPEN, 0); }
		public TerminalNode GIRAIzQUIERDA() { return getToken(LogoTecParser.GIRAIzQUIERDA, 0); }
		public TerminalNode RE() { return getToken(LogoTecParser.RE, 0); }
		public TerminalNode BRACKET_CLOSE() { return getToken(LogoTecParser.BRACKET_CLOSE, 0); }
		public TerminalNode BL() { return getToken(LogoTecParser.BL, 0); }
		public TerminalNode GI() { return getToken(LogoTecParser.GI, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public TerminalNode RUMBO() { return getToken(LogoTecParser.RUMBO, 0); }
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
		enterRule(_localctx, 32, RULE_turtleCmd);
		try {
			setState(398);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(310); match(AVANZA);
				setState(311); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new ForwardNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(314); match(AV);
				setState(315); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new ForwardNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(318); match(RETROCEDE);
				setState(319); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new BackwardNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(322); match(RE);
				setState(323); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new BackwardNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(326); match(GIRADERECHA);
				setState(327); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new TurnRightNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(330); match(GD);
				setState(331); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new TurnRightNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(334); match(GIRAIzQUIERDA);
				setState(335); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new TurnLeftNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(338); match(GI);
				setState(339); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new TurnLeftNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(342); match(OCULTATORTUGA);
				 ((TurtleCmdContext)_localctx).node =  new HideTurtleNode(); 
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(344); match(OT);
				 ((TurtleCmdContext)_localctx).node =  new HideTurtleNode(); 
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(346); match(PONPOS);
				setState(347); match(BRACKET_OPEN);
				setState(348); ((TurtleCmdContext)_localctx).x = expression();
				setState(349); ((TurtleCmdContext)_localctx).y = expression();
				setState(350); match(BRACKET_CLOSE);
				 ((TurtleCmdContext)_localctx).node =  new SetPosNode(((TurtleCmdContext)_localctx).x.node, ((TurtleCmdContext)_localctx).y.node, true); 
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(353); match(PONXY);
				setState(354); ((TurtleCmdContext)_localctx).x = expression();
				setState(355); ((TurtleCmdContext)_localctx).y = expression();
				 ((TurtleCmdContext)_localctx).node =  new SetPosNode(((TurtleCmdContext)_localctx).x.node, ((TurtleCmdContext)_localctx).y.node, false); 
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(358); match(PONRUMBO);
				setState(359); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new SetHeadingNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(362); match(RUMBO);
				 ((TurtleCmdContext)_localctx).node =  new ShowHeadingNode(); 
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(364); match(PONX);
				setState(365); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new SetXNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(368); match(PONY);
				setState(369); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new SetYNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(372); match(BAJALAPIZ);
				 ((TurtleCmdContext)_localctx).node =  new PenDownNode(); 
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(374); match(BL);
				 ((TurtleCmdContext)_localctx).node =  new PenDownNode(); 
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(376); match(SUBELAPIZ);
				 ((TurtleCmdContext)_localctx).node =  new PenUpNode(); 
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(378); match(SB);
				 ((TurtleCmdContext)_localctx).node =  new PenUpNode(); 
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 21);
				{
				setState(380); match(CENTRO);
				 ((TurtleCmdContext)_localctx).node =  new CenterNode(); 
				}
				break;
			case 22:
				enterOuterAlt(_localctx, 22);
				{
				setState(382); match(ESPERA);
				setState(383); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new WaitNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 23:
				enterOuterAlt(_localctx, 23);
				{
				setState(386); match(INC);
				setState(387); match(BRACKET_OPEN);
				setState(388); ((TurtleCmdContext)_localctx).id = match(ID);
				setState(389); match(BRACKET_CLOSE);
				 ((TurtleCmdContext)_localctx).node =  new IncNode(new VarRefNode((((TurtleCmdContext)_localctx).id!=null?((TurtleCmdContext)_localctx).id.getText():null)), new ConstNode(1)); 
				}
				break;
			case 24:
				enterOuterAlt(_localctx, 24);
				{
				setState(391); match(INC);
				setState(392); match(BRACKET_OPEN);
				setState(393); ((TurtleCmdContext)_localctx).id = match(ID);
				setState(394); ((TurtleCmdContext)_localctx).n = expression();
				setState(395); match(BRACKET_CLOSE);
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
		enterRule(_localctx, 34, RULE_expression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(400); ((ExpressionContext)_localctx).t1 = logicTerm();
			 ((ExpressionContext)_localctx).node =  ((ExpressionContext)_localctx).t1.node; ((ExpressionContext)_localctx).val =  ((ExpressionContext)_localctx).t1.val; 
			setState(408);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(402); match(OR);
					setState(403); ((ExpressionContext)_localctx).t2 = logicTerm();
					 ((ExpressionContext)_localctx).node =  new LogicalOrNode(_localctx.node, ((ExpressionContext)_localctx).t2.node); 
					}
					} 
				}
				setState(410);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
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
		enterRule(_localctx, 36, RULE_logicTerm);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(411); ((LogicTermContext)_localctx).f1 = logicFactor();
			 ((LogicTermContext)_localctx).node =  ((LogicTermContext)_localctx).f1.node; ((LogicTermContext)_localctx).val =  ((LogicTermContext)_localctx).f1.val; 
			setState(419);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(413); match(AND);
					setState(414); ((LogicTermContext)_localctx).f2 = logicFactor();
					 ((LogicTermContext)_localctx).node =  new LogicalAndNode(_localctx.node, ((LogicTermContext)_localctx).f2.node); 
					}
					} 
				}
				setState(421);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
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
		enterRule(_localctx, 38, RULE_logicFactor);
		try {
			setState(429);
			switch (_input.LA(1)) {
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(422); match(NOT);
				setState(423); ((LogicFactorContext)_localctx).lf = logicFactor();
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
			case PAR_OPEN:
			case BOOLEAN:
			case NUMBER:
			case STRING:
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(426); ((LogicFactorContext)_localctx).r = relational();
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
		enterRule(_localctx, 40, RULE_relational);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(431); ((RelationalContext)_localctx).a1 = arithExpr();
			 ((RelationalContext)_localctx).node =  ((RelationalContext)_localctx).a1.node; ((RelationalContext)_localctx).val =  ((RelationalContext)_localctx).a1.val; 
			setState(459);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(457);
					switch (_input.LA(1)) {
					case GT:
						{
						setState(433); match(GT);
						setState(434); ((RelationalContext)_localctx).a2 = arithExpr();
						 ((RelationalContext)_localctx).node =  new GreaterThanNode(_localctx.node, ((RelationalContext)_localctx).a2.node);
						}
						break;
					case LT:
						{
						setState(437); match(LT);
						setState(438); ((RelationalContext)_localctx).a2 = arithExpr();
						 ((RelationalContext)_localctx).node =  new LessThanNode(_localctx.node, ((RelationalContext)_localctx).a2.node); 
						}
						break;
					case GEQ:
						{
						setState(441); match(GEQ);
						setState(442); ((RelationalContext)_localctx).a2 = arithExpr();
						 ((RelationalContext)_localctx).node =  new GreaterEqualNode(_localctx.node, ((RelationalContext)_localctx).a2.node);  
						}
						break;
					case LEQ:
						{
						setState(445); match(LEQ);
						setState(446); ((RelationalContext)_localctx).a2 = arithExpr();
						 ((RelationalContext)_localctx).node =  new LessEqualNode(_localctx.node, ((RelationalContext)_localctx).a2.node);
						}
						break;
					case EQ:
						{
						setState(449); match(EQ);
						setState(450); ((RelationalContext)_localctx).a2 = arithExpr();
						 ((RelationalContext)_localctx).node =  new EqualsNode(_localctx.node, ((RelationalContext)_localctx).a2.node);  
						}
						break;
					case NEQ:
						{
						setState(453); match(NEQ);
						setState(454); ((RelationalContext)_localctx).a2 = arithExpr();
						 ((RelationalContext)_localctx).node =  new NotEqualsNode(_localctx.node, ((RelationalContext)_localctx).a2.node); ; 
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(461);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
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
		enterRule(_localctx, 42, RULE_arithExpr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(462); ((ArithExprContext)_localctx).t1 = term();
			 ((ArithExprContext)_localctx).node =  ((ArithExprContext)_localctx).t1.node; ((ArithExprContext)_localctx).val =  ((ArithExprContext)_localctx).t1.val; 
			setState(474);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(472);
					switch (_input.LA(1)) {
					case PLUS:
						{
						setState(464); match(PLUS);
						setState(465); ((ArithExprContext)_localctx).t2 = term();
						 ((ArithExprContext)_localctx).node =  new AdditionNode(_localctx.node, ((ArithExprContext)_localctx).t2.node); 
						}
						break;
					case MINUS:
						{
						setState(468); match(MINUS);
						setState(469); ((ArithExprContext)_localctx).t2 = term();
						 ((ArithExprContext)_localctx).node =  new SubtractionNode(_localctx.node, ((ArithExprContext)_localctx).t2.node);  
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(476);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
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
		enterRule(_localctx, 44, RULE_term);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(477); ((TermContext)_localctx).f1 = factor();
			 ((TermContext)_localctx).node =  ((TermContext)_localctx).f1.node; ((TermContext)_localctx).val =  ((TermContext)_localctx).f1.val; 
			setState(489);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(487);
					switch (_input.LA(1)) {
					case MULT:
						{
						setState(479); match(MULT);
						setState(480); ((TermContext)_localctx).f2 = factor();
						 ((TermContext)_localctx).node =  new MultiplicationNode(_localctx.node, ((TermContext)_localctx).f2.node);  
						}
						break;
					case DIV:
						{
						setState(483); match(DIV);
						setState(484); ((TermContext)_localctx).f2 = factor();
						 ((TermContext)_localctx).node =  new DivisionNode(_localctx.node, ((TermContext)_localctx).f2.node); 
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(491);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
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
		enterRule(_localctx, 46, RULE_factor);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(492); ((FactorContext)_localctx).e1 = exponent();
			 ((FactorContext)_localctx).node =  ((FactorContext)_localctx).e1.node; ((FactorContext)_localctx).val =  ((FactorContext)_localctx).e1.val; 
			setState(500);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(494); match(EXP);
					setState(495); ((FactorContext)_localctx).e2 = exponent();
					 ((FactorContext)_localctx).node =  new ExponentiationNode(_localctx.node, ((FactorContext)_localctx).e2.node);  
					}
					} 
				}
				setState(502);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
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
		public FuncCallContext funcCall;
		public PrimaryContext primary;
		public FuncCallContext funcCall() {
			return getRuleContext(FuncCallContext.class,0);
		}
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
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
		enterRule(_localctx, 48, RULE_exponent);
		try {
			setState(509);
			switch (_input.LA(1)) {
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
				enterOuterAlt(_localctx, 1);
				{
				setState(503); ((ExponentContext)_localctx).funcCall = funcCall();
				 ((ExponentContext)_localctx).node =  ((ExponentContext)_localctx).funcCall.node; ((ExponentContext)_localctx).val =  ((ExponentContext)_localctx).funcCall.val; 
				}
				break;
			case PAR_OPEN:
			case BOOLEAN:
			case NUMBER:
			case STRING:
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(506); ((ExponentContext)_localctx).primary = primary();
				 ((ExponentContext)_localctx).node =  ((ExponentContext)_localctx).primary.node; ((ExponentContext)_localctx).val =  ((ExponentContext)_localctx).primary.val; 
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
		public List<ExpressionContext> eMore = new ArrayList<ExpressionContext>();
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
		public TerminalNode OFUNC() { return getToken(LogoTecParser.OFUNC, 0); }
		public TerminalNode PRODUCTO() { return getToken(LogoTecParser.PRODUCTO, 0); }
		public TerminalNode AZAR() { return getToken(LogoTecParser.AZAR, 0); }
		public TerminalNode PAR_OPEN(int i) {
			return getToken(LogoTecParser.PAR_OPEN, i);
		}
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
		try {
			int _alt;
			setState(588);
			switch (_input.LA(1)) {
			case IGUALESQ:
				enterOuterAlt(_localctx, 1);
				{
				setState(511); match(IGUALESQ);
				setState(512); ((FuncCallContext)_localctx).e1 = expression();
				setState(513); ((FuncCallContext)_localctx).e2 = expression();
				 ((FuncCallContext)_localctx).node =  new EqualsFuncNode(((FuncCallContext)_localctx).e1.node, ((FuncCallContext)_localctx).e2.node); 
				}
				break;
			case YFUNC:
				enterOuterAlt(_localctx, 2);
				{
				setState(516); match(YFUNC);
				setState(517); match(PAR_OPEN);
				setState(518); ((FuncCallContext)_localctx).e1 = expression();
				setState(519); match(PAR_CLOSE);
				setState(520); match(PAR_OPEN);
				setState(521); ((FuncCallContext)_localctx).e2 = expression();
				setState(522); match(PAR_CLOSE);
				 ((FuncCallContext)_localctx).node =  new LogicalAndNode(((FuncCallContext)_localctx).e1.node,((FuncCallContext)_localctx).e2.node); 
				}
				break;
			case OFUNC:
				enterOuterAlt(_localctx, 3);
				{
				setState(525); match(OFUNC);
				setState(526); match(PAR_OPEN);
				setState(527); ((FuncCallContext)_localctx).e1 = expression();
				setState(528); match(PAR_CLOSE);
				setState(529); match(PAR_OPEN);
				setState(530); ((FuncCallContext)_localctx).e2 = expression();
				setState(531); match(PAR_CLOSE);
				 ((FuncCallContext)_localctx).node =  new LogicalOrNode(((FuncCallContext)_localctx).e1.node,((FuncCallContext)_localctx).e2.node);  
				}
				break;
			case MAYORQ:
				enterOuterAlt(_localctx, 4);
				{
				setState(534); match(MAYORQ);
				setState(535); ((FuncCallContext)_localctx).e1 = expression();
				setState(536); ((FuncCallContext)_localctx).e2 = expression();
				 ((FuncCallContext)_localctx).node =  new GreaterThanNode(((FuncCallContext)_localctx).e1.node,((FuncCallContext)_localctx).e2.node);  
				}
				break;
			case MENORQ:
				enterOuterAlt(_localctx, 5);
				{
				setState(539); match(MENORQ);
				setState(540); ((FuncCallContext)_localctx).e1 = expression();
				setState(541); ((FuncCallContext)_localctx).e2 = expression();
				 ((FuncCallContext)_localctx).node =  new LessThanNode(((FuncCallContext)_localctx).e1.node,((FuncCallContext)_localctx).e2.node);  
				}
				break;
			case AZAR:
				enterOuterAlt(_localctx, 6);
				{
				setState(544); match(AZAR);
				setState(545); ((FuncCallContext)_localctx).e = expression();
				 ((FuncCallContext)_localctx).node =  new RandomNode(((FuncCallContext)_localctx).e.node); 
				}
				break;
			case PRODUCTO:
				enterOuterAlt(_localctx, 7);
				{
				setState(548); match(PRODUCTO);
				setState(549); ((FuncCallContext)_localctx).e1 = expression();
				setState(553);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(550); ((FuncCallContext)_localctx).expression = expression();
						((FuncCallContext)_localctx).eMore.add(((FuncCallContext)_localctx).expression);
						}
						} 
					}
					setState(555);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
				}

				    List<ExprNode> rest = new ArrayList<>();
				    for (ExpressionContext ctx : ((FuncCallContext)_localctx).eMore) {
				        rest.add(ctx.node);
				    }
				    ((FuncCallContext)_localctx).node =  new ProductNode(((FuncCallContext)_localctx).e1.node, rest);
				  
				}
				break;
			case POTENCIA:
				enterOuterAlt(_localctx, 8);
				{
				setState(558); match(POTENCIA);
				setState(559); ((FuncCallContext)_localctx).e1 = expression();
				setState(560); ((FuncCallContext)_localctx).e2 = expression();
				 ((FuncCallContext)_localctx).node =  new ExponentiationNode(((FuncCallContext)_localctx).e1.node, ((FuncCallContext)_localctx).e2.node); 
				}
				break;
			case DIVISION:
				enterOuterAlt(_localctx, 9);
				{
				setState(563); match(DIVISION);
				setState(564); ((FuncCallContext)_localctx).e1 = expression();
				setState(565); ((FuncCallContext)_localctx).e2 = expression();
				 ((FuncCallContext)_localctx).node =  new DivisionNode(((FuncCallContext)_localctx).e1.node, ((FuncCallContext)_localctx).e2.node); 
				}
				break;
			case SUMA:
				enterOuterAlt(_localctx, 10);
				{
				setState(568); match(SUMA);
				setState(569); ((FuncCallContext)_localctx).e1 = expression();
				setState(573);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(570); ((FuncCallContext)_localctx).expression = expression();
						((FuncCallContext)_localctx).eMore.add(((FuncCallContext)_localctx).expression);
						}
						} 
					}
					setState(575);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
				}

				    List<ExprNode> rest = new ArrayList<>();
				    for (ExpressionContext ctx : ((FuncCallContext)_localctx).eMore) {
				        rest.add(ctx.node);
				    }
				    ((FuncCallContext)_localctx).node =  new SumNode(((FuncCallContext)_localctx).e1.node, rest);
				  
				}
				break;
			case DIFERENCIA:
				enterOuterAlt(_localctx, 11);
				{
				setState(578); match(DIFERENCIA);
				setState(579); ((FuncCallContext)_localctx).e1 = expression();
				setState(583);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(580); ((FuncCallContext)_localctx).expression = expression();
						((FuncCallContext)_localctx).eMore.add(((FuncCallContext)_localctx).expression);
						}
						} 
					}
					setState(585);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
				}

				    List<ExprNode> rest = new ArrayList<>();
				    for (ExpressionContext ctx : ((FuncCallContext)_localctx).eMore) {
				        rest.add(ctx.node);
				    }
				    ((FuncCallContext)_localctx).node =  new DifferenceNode(((FuncCallContext)_localctx).e1.node, rest);
				  
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
		public Token NUMBER;
		public Token BOOLEAN;
		public Token ID;
		public Token STRING;
		public ExpressionContext expression;
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
			setState(603);
			switch (_input.LA(1)) {
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(590); ((PrimaryContext)_localctx).NUMBER = match(NUMBER);
				 ((PrimaryContext)_localctx).node =  new ConstNode(Integer.parseInt((((PrimaryContext)_localctx).NUMBER!=null?((PrimaryContext)_localctx).NUMBER.getText():null)));
				                ((PrimaryContext)_localctx).val =  Value.integer(Integer.parseInt((((PrimaryContext)_localctx).NUMBER!=null?((PrimaryContext)_localctx).NUMBER.getText():null))); 
				}
				break;
			case BOOLEAN:
				enterOuterAlt(_localctx, 2);
				{
				setState(592); ((PrimaryContext)_localctx).BOOLEAN = match(BOOLEAN);
				 ((PrimaryContext)_localctx).node =  new ConstNode(Boolean.parseBoolean((((PrimaryContext)_localctx).BOOLEAN!=null?((PrimaryContext)_localctx).BOOLEAN.getText():null)));
				                ((PrimaryContext)_localctx).val =  Value.bool(Boolean.parseBoolean((((PrimaryContext)_localctx).BOOLEAN!=null?((PrimaryContext)_localctx).BOOLEAN.getText():null))); 
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(594); ((PrimaryContext)_localctx).ID = match(ID);
				 ((PrimaryContext)_localctx).node =  new VarRefNode((((PrimaryContext)_localctx).ID!=null?((PrimaryContext)_localctx).ID.getText():null));
				                ((PrimaryContext)_localctx).val =  Value.unknown(); 
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 4);
				{
				setState(596); ((PrimaryContext)_localctx).STRING = match(STRING);
				 ((PrimaryContext)_localctx).node =  new ConstNode((((PrimaryContext)_localctx).STRING!=null?((PrimaryContext)_localctx).STRING.getText():null).substring(1,(((PrimaryContext)_localctx).STRING!=null?((PrimaryContext)_localctx).STRING.getText():null).length()-1));
				                ((PrimaryContext)_localctx).val =  Value.string((((PrimaryContext)_localctx).STRING!=null?((PrimaryContext)_localctx).STRING.getText():null).substring(1,(((PrimaryContext)_localctx).STRING!=null?((PrimaryContext)_localctx).STRING.getText():null).length()-1)); 
				}
				break;
			case PAR_OPEN:
				enterOuterAlt(_localctx, 5);
				{
				setState(598); match(PAR_OPEN);
				setState(599); ((PrimaryContext)_localctx).expression = expression();
				setState(600); match(PAR_CLOSE);
				 ((PrimaryContext)_localctx).node =  ((PrimaryContext)_localctx).expression.node; ((PrimaryContext)_localctx).val =  ((PrimaryContext)_localctx).expression.val; 
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
		public Token NUMBER;
		public Token BOOLEAN;
		public Token STRING;
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
			setState(611);
			switch (_input.LA(1)) {
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(605); ((LiteralOrStringContext)_localctx).NUMBER = match(NUMBER);
				 ((LiteralOrStringContext)_localctx).node =  new ConstNode(Integer.parseInt((((LiteralOrStringContext)_localctx).NUMBER!=null?((LiteralOrStringContext)_localctx).NUMBER.getText():null))); 
				}
				break;
			case BOOLEAN:
				enterOuterAlt(_localctx, 2);
				{
				setState(607); ((LiteralOrStringContext)_localctx).BOOLEAN = match(BOOLEAN);
				 ((LiteralOrStringContext)_localctx).node =  new ConstNode(Boolean.parseBoolean((((LiteralOrStringContext)_localctx).BOOLEAN!=null?((LiteralOrStringContext)_localctx).BOOLEAN.getText():null))); 
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(609); ((LiteralOrStringContext)_localctx).STRING = match(STRING);
				 ((LiteralOrStringContext)_localctx).node =  new ConstNode((((LiteralOrStringContext)_localctx).STRING!=null?((LiteralOrStringContext)_localctx).STRING.getText():null)); 
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

	public static class CmtFirstLineContext extends ParserRuleContext {
		public TerminalNode COMMENT_LINE() { return getToken(LogoTecParser.COMMENT_LINE, 0); }
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
			setState(613); match(COMMENT_LINE);
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
			setState(615); ((ColorNameContext)_localctx).c = match(COLOR);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3L\u026c\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2\3\2\3"+
		"\2\3\2\3\3\3\3\3\3\5\3F\n\3\3\3\3\3\3\3\7\3K\n\3\f\3\16\3N\13\3\3\3\3"+
		"\3\3\3\7\3S\n\3\f\3\16\3V\13\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\5\4_\n\4\3"+
		"\4\3\4\3\4\3\4\7\4e\n\4\f\4\16\4h\13\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\7\5"+
		"q\n\5\f\5\16\5t\13\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u008a\n\6\3\7\3\7\3\7\3\7\5\7\u0090"+
		"\n\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\7\t\u00a0"+
		"\n\t\f\t\16\t\u00a3\13\t\3\t\5\t\u00a6\n\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n"+
		"\7\n\u00af\n\n\f\n\16\n\u00b2\13\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\7\13\u00bd\n\13\f\13\16\13\u00c0\13\13\3\13\3\13\3\13\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00d7"+
		"\n\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u00e1\n\r\f\r\16\r\u00e4\13\r"+
		"\3\r\3\r\3\r\3\r\3\r\7\r\u00eb\n\r\f\r\16\r\u00ee\13\r\3\r\5\r\u00f1\n"+
		"\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u00fc\n\16\f\16\16"+
		"\16\u00ff\13\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\7\17\u010f\n\17\f\17\16\17\u0112\13\17\3\17\3\17\3\17"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\7\20\u011e\n\20\f\20\16\20\u0121\13"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\7\21\u0131\n\21\f\21\16\21\u0134\13\21\3\21\3\21\3\21\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\5\22\u0191\n\22\3\23\3\23\3\23\3\23\3\23\3\23\7\23\u0199\n\23"+
		"\f\23\16\23\u019c\13\23\3\24\3\24\3\24\3\24\3\24\3\24\7\24\u01a4\n\24"+
		"\f\24\16\24\u01a7\13\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u01b0"+
		"\n\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\7\26"+
		"\u01cc\n\26\f\26\16\26\u01cf\13\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\7\27\u01db\n\27\f\27\16\27\u01de\13\27\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\7\30\u01ea\n\30\f\30\16\30\u01ed\13"+
		"\30\3\31\3\31\3\31\3\31\3\31\3\31\7\31\u01f5\n\31\f\31\16\31\u01f8\13"+
		"\31\3\32\3\32\3\32\3\32\3\32\3\32\5\32\u0200\n\32\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\7\33\u022a\n\33\f\33\16\33\u022d"+
		"\13\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\7\33\u023e\n\33\f\33\16\33\u0241\13\33\3\33\3\33\3\33\3\33"+
		"\3\33\7\33\u0248\n\33\f\33\16\33\u024b\13\33\3\33\3\33\5\33\u024f\n\33"+
		"\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\5\34"+
		"\u025e\n\34\3\35\3\35\3\35\3\35\3\35\3\35\5\35\u0266\n\35\3\36\3\36\3"+
		"\37\3\37\3\37\2\2 \2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60"+
		"\62\64\668:<\2\2\u02a2\2>\3\2\2\2\4E\3\2\2\2\6Y\3\2\2\2\bm\3\2\2\2\n\u0089"+
		"\3\2\2\2\f\u008b\3\2\2\2\16\u0093\3\2\2\2\20\u009a\3\2\2\2\22\u00a9\3"+
		"\2\2\2\24\u00b6\3\2\2\2\26\u00d6\3\2\2\2\30\u00d8\3\2\2\2\32\u00f4\3\2"+
		"\2\2\34\u0106\3\2\2\2\36\u0116\3\2\2\2 \u0128\3\2\2\2\"\u0190\3\2\2\2"+
		"$\u0192\3\2\2\2&\u019d\3\2\2\2(\u01af\3\2\2\2*\u01b1\3\2\2\2,\u01d0\3"+
		"\2\2\2.\u01df\3\2\2\2\60\u01ee\3\2\2\2\62\u01ff\3\2\2\2\64\u024e\3\2\2"+
		"\2\66\u025d\3\2\2\28\u0265\3\2\2\2:\u0267\3\2\2\2<\u0269\3\2\2\2>?\5\4"+
		"\3\2?@\7\2\2\3@A\b\2\1\2A\3\3\2\2\2BC\5:\36\2CD\b\3\1\2DF\3\2\2\2EB\3"+
		"\2\2\2EF\3\2\2\2FL\3\2\2\2GH\5\6\4\2HI\b\3\1\2IK\3\2\2\2JG\3\2\2\2KN\3"+
		"\2\2\2LJ\3\2\2\2LM\3\2\2\2MT\3\2\2\2NL\3\2\2\2OP\5\n\6\2PQ\b\3\1\2QS\3"+
		"\2\2\2RO\3\2\2\2SV\3\2\2\2TR\3\2\2\2TU\3\2\2\2UW\3\2\2\2VT\3\2\2\2WX\b"+
		"\3\1\2X\5\3\2\2\2YZ\7\3\2\2Z^\7I\2\2[\\\5\b\5\2\\]\b\4\1\2]_\3\2\2\2^"+
		"[\3\2\2\2^_\3\2\2\2_`\3\2\2\2`f\7?\2\2ab\5\n\6\2bc\b\4\1\2ce\3\2\2\2d"+
		"a\3\2\2\2eh\3\2\2\2fd\3\2\2\2fg\3\2\2\2gi\3\2\2\2hf\3\2\2\2ij\7@\2\2j"+
		"k\7\4\2\2kl\b\4\1\2l\7\3\2\2\2mr\7?\2\2no\7I\2\2oq\b\5\1\2pn\3\2\2\2q"+
		"t\3\2\2\2rp\3\2\2\2rs\3\2\2\2su\3\2\2\2tr\3\2\2\2uv\7@\2\2v\t\3\2\2\2"+
		"wx\5\f\7\2xy\b\6\1\2y\u008a\3\2\2\2z{\5\16\b\2{|\b\6\1\2|\u008a\3\2\2"+
		"\2}~\5\"\22\2~\177\b\6\1\2\177\u008a\3\2\2\2\u0080\u0081\5\26\f\2\u0081"+
		"\u0082\b\6\1\2\u0082\u008a\3\2\2\2\u0083\u0084\5\22\n\2\u0084\u0085\b"+
		"\6\1\2\u0085\u008a\3\2\2\2\u0086\u0087\5\20\t\2\u0087\u0088\b\6\1\2\u0088"+
		"\u008a\3\2\2\2\u0089w\3\2\2\2\u0089z\3\2\2\2\u0089}\3\2\2\2\u0089\u0080"+
		"\3\2\2\2\u0089\u0083\3\2\2\2\u0089\u0086\3\2\2\2\u008a\13\3\2\2\2\u008b"+
		"\u008c\7\n\2\2\u008c\u008d\7I\2\2\u008d\u008f\58\35\2\u008e\u0090\7C\2"+
		"\2\u008f\u008e\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0092"+
		"\b\7\1\2\u0092\r\3\2\2\2\u0093\u0094\7\13\2\2\u0094\u0095\7I\2\2\u0095"+
		"\u0096\7>\2\2\u0096\u0097\5$\23\2\u0097\u0098\7C\2\2\u0098\u0099\b\b\1"+
		"\2\u0099\17\3\2\2\2\u009a\u00a5\7I\2\2\u009b\u00a1\7?\2\2\u009c\u009d"+
		"\5$\23\2\u009d\u009e\b\t\1\2\u009e\u00a0\3\2\2\2\u009f\u009c\3\2\2\2\u00a0"+
		"\u00a3\3\2\2\2\u00a1\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a4\3\2"+
		"\2\2\u00a3\u00a1\3\2\2\2\u00a4\u00a6\7@\2\2\u00a5\u009b\3\2\2\2\u00a5"+
		"\u00a6\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a8\b\t\1\2\u00a8\21\3\2\2"+
		"\2\u00a9\u00aa\7\5\2\2\u00aa\u00b0\7?\2\2\u00ab\u00ac\5\n\6\2\u00ac\u00ad"+
		"\b\n\1\2\u00ad\u00af\3\2\2\2\u00ae\u00ab\3\2\2\2\u00af\u00b2\3\2\2\2\u00b0"+
		"\u00ae\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b3\3\2\2\2\u00b2\u00b0\3\2"+
		"\2\2\u00b3\u00b4\7@\2\2\u00b4\u00b5\b\n\1\2\u00b5\23\3\2\2\2\u00b6\u00b7"+
		"\7\6\2\2\u00b7\u00b8\5$\23\2\u00b8\u00be\7?\2\2\u00b9\u00ba\5\n\6\2\u00ba"+
		"\u00bb\b\13\1\2\u00bb\u00bd\3\2\2\2\u00bc\u00b9\3\2\2\2\u00bd\u00c0\3"+
		"\2\2\2\u00be\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c1\3\2\2\2\u00c0"+
		"\u00be\3\2\2\2\u00c1\u00c2\7@\2\2\u00c2\u00c3\b\13\1\2\u00c3\25\3\2\2"+
		"\2\u00c4\u00c5\5\30\r\2\u00c5\u00c6\b\f\1\2\u00c6\u00d7\3\2\2\2\u00c7"+
		"\u00c8\5\32\16\2\u00c8\u00c9\b\f\1\2\u00c9\u00d7\3\2\2\2\u00ca\u00cb\5"+
		"\34\17\2\u00cb\u00cc\b\f\1\2\u00cc\u00d7\3\2\2\2\u00cd\u00ce\5\36\20\2"+
		"\u00ce\u00cf\b\f\1\2\u00cf\u00d7\3\2\2\2\u00d0\u00d1\5 \21\2\u00d1\u00d2"+
		"\b\f\1\2\u00d2\u00d7\3\2\2\2\u00d3\u00d4\5\24\13\2\u00d4\u00d5\b\f\1\2"+
		"\u00d5\u00d7\3\2\2\2\u00d6\u00c4\3\2\2\2\u00d6\u00c7\3\2\2\2\u00d6\u00ca"+
		"\3\2\2\2\u00d6\u00cd\3\2\2\2\u00d6\u00d0\3\2\2\2\u00d6\u00d3\3\2\2\2\u00d7"+
		"\27\3\2\2\2\u00d8\u00d9\7\7\2\2\u00d9\u00da\7A\2\2\u00da\u00db\5$\23\2"+
		"\u00db\u00dc\7B\2\2\u00dc\u00e2\7?\2\2\u00dd\u00de\5\n\6\2\u00de\u00df"+
		"\b\r\1\2\u00df\u00e1\3\2\2\2\u00e0\u00dd\3\2\2\2\u00e1\u00e4\3\2\2\2\u00e2"+
		"\u00e0\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3\u00e5\3\2\2\2\u00e4\u00e2\3\2"+
		"\2\2\u00e5\u00f0\7@\2\2\u00e6\u00ec\7?\2\2\u00e7\u00e8\5\n\6\2\u00e8\u00e9"+
		"\b\r\1\2\u00e9\u00eb\3\2\2\2\u00ea\u00e7\3\2\2\2\u00eb\u00ee\3\2\2\2\u00ec"+
		"\u00ea\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00ef\3\2\2\2\u00ee\u00ec\3\2"+
		"\2\2\u00ef\u00f1\7@\2\2\u00f0\u00e6\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1"+
		"\u00f2\3\2\2\2\u00f2\u00f3\b\r\1\2\u00f3\31\3\2\2\2\u00f4\u00f5\7\n\2"+
		"\2\u00f5\u00f6\7E\2\2\u00f6\u00f7\7\b\2\2\u00f7\u00fd\7?\2\2\u00f8\u00f9"+
		"\5\n\6\2\u00f9\u00fa\b\16\1\2\u00fa\u00fc\3\2\2\2\u00fb\u00f8\3\2\2\2"+
		"\u00fc\u00ff\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\u0100"+
		"\3\2\2\2\u00ff\u00fd\3\2\2\2\u0100\u0101\7@\2\2\u0101\u0102\7A\2\2\u0102"+
		"\u0103\5$\23\2\u0103\u0104\7B\2\2\u0104\u0105\b\16\1\2\u0105\33\3\2\2"+
		"\2\u0106\u0107\7\b\2\2\u0107\u0108\7A\2\2\u0108\u0109\5$\23\2\u0109\u010a"+
		"\7B\2\2\u010a\u0110\7?\2\2\u010b\u010c\5\n\6\2\u010c\u010d\b\17\1\2\u010d"+
		"\u010f\3\2\2\2\u010e\u010b\3\2\2\2\u010f\u0112\3\2\2\2\u0110\u010e\3\2"+
		"\2\2\u0110\u0111\3\2\2\2\u0111\u0113\3\2\2\2\u0112\u0110\3\2\2\2\u0113"+
		"\u0114\7@\2\2\u0114\u0115\b\17\1\2\u0115\35\3\2\2\2\u0116\u0117\7\n\2"+
		"\2\u0117\u0118\7E\2\2\u0118\u0119\7\t\2\2\u0119\u011f\7?\2\2\u011a\u011b"+
		"\5\n\6\2\u011b\u011c\b\20\1\2\u011c\u011e\3\2\2\2\u011d\u011a\3\2\2\2"+
		"\u011e\u0121\3\2\2\2\u011f\u011d\3\2\2\2\u011f\u0120\3\2\2\2\u0120\u0122"+
		"\3\2\2\2\u0121\u011f\3\2\2\2\u0122\u0123\7@\2\2\u0123\u0124\7A\2\2\u0124"+
		"\u0125\5$\23\2\u0125\u0126\7B\2\2\u0126\u0127\b\20\1\2\u0127\37\3\2\2"+
		"\2\u0128\u0129\7\t\2\2\u0129\u012a\7A\2\2\u012a\u012b\5$\23\2\u012b\u012c"+
		"\7B\2\2\u012c\u0132\7?\2\2\u012d\u012e\5\n\6\2\u012e\u012f\b\21\1\2\u012f"+
		"\u0131\3\2\2\2\u0130\u012d\3\2\2\2\u0131\u0134\3\2\2\2\u0132\u0130\3\2"+
		"\2\2\u0132\u0133\3\2\2\2\u0133\u0135\3\2\2\2\u0134\u0132\3\2\2\2\u0135"+
		"\u0136\7@\2\2\u0136\u0137\b\21\1\2\u0137!\3\2\2\2\u0138\u0139\7\f\2\2"+
		"\u0139\u013a\5$\23\2\u013a\u013b\b\22\1\2\u013b\u0191\3\2\2\2\u013c\u013d"+
		"\7\r\2\2\u013d\u013e\5$\23\2\u013e\u013f\b\22\1\2\u013f\u0191\3\2\2\2"+
		"\u0140\u0141\7\16\2\2\u0141\u0142\5$\23\2\u0142\u0143\b\22\1\2\u0143\u0191"+
		"\3\2\2\2\u0144\u0145\7\17\2\2\u0145\u0146\5$\23\2\u0146\u0147\b\22\1\2"+
		"\u0147\u0191\3\2\2\2\u0148\u0149\7\20\2\2\u0149\u014a\5$\23\2\u014a\u014b"+
		"\b\22\1\2\u014b\u0191\3\2\2\2\u014c\u014d\7\21\2\2\u014d\u014e\5$\23\2"+
		"\u014e\u014f\b\22\1\2\u014f\u0191\3\2\2\2\u0150\u0151\7\22\2\2\u0151\u0152"+
		"\5$\23\2\u0152\u0153\b\22\1\2\u0153\u0191\3\2\2\2\u0154\u0155\7\23\2\2"+
		"\u0155\u0156\5$\23\2\u0156\u0157\b\22\1\2\u0157\u0191\3\2\2\2\u0158\u0159"+
		"\7\24\2\2\u0159\u0191\b\22\1\2\u015a\u015b\7\25\2\2\u015b\u0191\b\22\1"+
		"\2\u015c\u015d\7\26\2\2\u015d\u015e\7?\2\2\u015e\u015f\5$\23\2\u015f\u0160"+
		"\5$\23\2\u0160\u0161\7@\2\2\u0161\u0162\b\22\1\2\u0162\u0191\3\2\2\2\u0163"+
		"\u0164\7\27\2\2\u0164\u0165\5$\23\2\u0165\u0166\5$\23\2\u0166\u0167\b"+
		"\22\1\2\u0167\u0191\3\2\2\2\u0168\u0169\7\30\2\2\u0169\u016a\5$\23\2\u016a"+
		"\u016b\b\22\1\2\u016b\u0191\3\2\2\2\u016c\u016d\7\31\2\2\u016d\u0191\b"+
		"\22\1\2\u016e\u016f\7\32\2\2\u016f\u0170\5$\23\2\u0170\u0171\b\22\1\2"+
		"\u0171\u0191\3\2\2\2\u0172\u0173\7\33\2\2\u0173\u0174\5$\23\2\u0174\u0175"+
		"\b\22\1\2\u0175\u0191\3\2\2\2\u0176\u0177\7\34\2\2\u0177\u0191\b\22\1"+
		"\2\u0178\u0179\7\35\2\2\u0179\u0191\b\22\1\2\u017a\u017b\7\36\2\2\u017b"+
		"\u0191\b\22\1\2\u017c\u017d\7\37\2\2\u017d\u0191\b\22\1\2\u017e\u017f"+
		"\7\"\2\2\u017f\u0191\b\22\1\2\u0180\u0181\7#\2\2\u0181\u0182\5$\23\2\u0182"+
		"\u0183\b\22\1\2\u0183\u0191\3\2\2\2\u0184\u0185\7$\2\2\u0185\u0186\7?"+
		"\2\2\u0186\u0187\7I\2\2\u0187\u0188\7@\2\2\u0188\u0191\b\22\1\2\u0189"+
		"\u018a\7$\2\2\u018a\u018b\7?\2\2\u018b\u018c\7I\2\2\u018c\u018d\5$\23"+
		"\2\u018d\u018e\7@\2\2\u018e\u018f\b\22\1\2\u018f\u0191\3\2\2\2\u0190\u0138"+
		"\3\2\2\2\u0190\u013c\3\2\2\2\u0190\u0140\3\2\2\2\u0190\u0144\3\2\2\2\u0190"+
		"\u0148\3\2\2\2\u0190\u014c\3\2\2\2\u0190\u0150\3\2\2\2\u0190\u0154\3\2"+
		"\2\2\u0190\u0158\3\2\2\2\u0190\u015a\3\2\2\2\u0190\u015c\3\2\2\2\u0190"+
		"\u0163\3\2\2\2\u0190\u0168\3\2\2\2\u0190\u016c\3\2\2\2\u0190\u016e\3\2"+
		"\2\2\u0190\u0172\3\2\2\2\u0190\u0176\3\2\2\2\u0190\u0178\3\2\2\2\u0190"+
		"\u017a\3\2\2\2\u0190\u017c\3\2\2\2\u0190\u017e\3\2\2\2\u0190\u0180\3\2"+
		"\2\2\u0190\u0184\3\2\2\2\u0190\u0189\3\2\2\2\u0191#\3\2\2\2\u0192\u0193"+
		"\5&\24\2\u0193\u019a\b\23\1\2\u0194\u0195\7\66\2\2\u0195\u0196\5&\24\2"+
		"\u0196\u0197\b\23\1\2\u0197\u0199\3\2\2\2\u0198\u0194\3\2\2\2\u0199\u019c"+
		"\3\2\2\2\u019a\u0198\3\2\2\2\u019a\u019b\3\2\2\2\u019b%\3\2\2\2\u019c"+
		"\u019a\3\2\2\2\u019d\u019e\5(\25\2\u019e\u01a5\b\24\1\2\u019f\u01a0\7"+
		"\65\2\2\u01a0\u01a1\5(\25\2\u01a1\u01a2\b\24\1\2\u01a2\u01a4\3\2\2\2\u01a3"+
		"\u019f\3\2\2\2\u01a4\u01a7\3\2\2\2\u01a5\u01a3\3\2\2\2\u01a5\u01a6\3\2"+
		"\2\2\u01a6\'\3\2\2\2\u01a7\u01a5\3\2\2\2\u01a8\u01a9\7\67\2\2\u01a9\u01aa"+
		"\5(\25\2\u01aa\u01ab\b\25\1\2\u01ab\u01b0\3\2\2\2\u01ac\u01ad\5*\26\2"+
		"\u01ad\u01ae\b\25\1\2\u01ae\u01b0\3\2\2\2\u01af\u01a8\3\2\2\2\u01af\u01ac"+
		"\3\2\2\2\u01b0)\3\2\2\2\u01b1\u01b2\5,\27\2\u01b2\u01cd\b\26\1\2\u01b3"+
		"\u01b4\78\2\2\u01b4\u01b5\5,\27\2\u01b5\u01b6\b\26\1\2\u01b6\u01cc\3\2"+
		"\2\2\u01b7\u01b8\79\2\2\u01b8\u01b9\5,\27\2\u01b9\u01ba\b\26\1\2\u01ba"+
		"\u01cc\3\2\2\2\u01bb\u01bc\7:\2\2\u01bc\u01bd\5,\27\2\u01bd\u01be\b\26"+
		"\1\2\u01be\u01cc\3\2\2\2\u01bf\u01c0\7;\2\2\u01c0\u01c1\5,\27\2\u01c1"+
		"\u01c2\b\26\1\2\u01c2\u01cc\3\2\2\2\u01c3\u01c4\7<\2\2\u01c4\u01c5\5,"+
		"\27\2\u01c5\u01c6\b\26\1\2\u01c6\u01cc\3\2\2\2\u01c7\u01c8\7=\2\2\u01c8"+
		"\u01c9\5,\27\2\u01c9\u01ca\b\26\1\2\u01ca\u01cc\3\2\2\2\u01cb\u01b3\3"+
		"\2\2\2\u01cb\u01b7\3\2\2\2\u01cb\u01bb\3\2\2\2\u01cb\u01bf\3\2\2\2\u01cb"+
		"\u01c3\3\2\2\2\u01cb\u01c7\3\2\2\2\u01cc\u01cf\3\2\2\2\u01cd\u01cb\3\2"+
		"\2\2\u01cd\u01ce\3\2\2\2\u01ce+\3\2\2\2\u01cf\u01cd\3\2\2\2\u01d0\u01d1"+
		"\5.\30\2\u01d1\u01dc\b\27\1\2\u01d2\u01d3\7\60\2\2\u01d3\u01d4\5.\30\2"+
		"\u01d4\u01d5\b\27\1\2\u01d5\u01db\3\2\2\2\u01d6\u01d7\7\61\2\2\u01d7\u01d8"+
		"\5.\30\2\u01d8\u01d9\b\27\1\2\u01d9\u01db\3\2\2\2\u01da\u01d2\3\2\2\2"+
		"\u01da\u01d6\3\2\2\2\u01db\u01de\3\2\2\2\u01dc\u01da\3\2\2\2\u01dc\u01dd"+
		"\3\2\2\2\u01dd-\3\2\2\2\u01de\u01dc\3\2\2\2\u01df\u01e0\5\60\31\2\u01e0"+
		"\u01eb\b\30\1\2\u01e1\u01e2\7\62\2\2\u01e2\u01e3\5\60\31\2\u01e3\u01e4"+
		"\b\30\1\2\u01e4\u01ea\3\2\2\2\u01e5\u01e6\7\63\2\2\u01e6\u01e7\5\60\31"+
		"\2\u01e7\u01e8\b\30\1\2\u01e8\u01ea\3\2\2\2\u01e9\u01e1\3\2\2\2\u01e9"+
		"\u01e5\3\2\2\2\u01ea\u01ed\3\2\2\2\u01eb\u01e9\3\2\2\2\u01eb\u01ec\3\2"+
		"\2\2\u01ec/\3\2\2\2\u01ed\u01eb\3\2\2\2\u01ee\u01ef\5\62\32\2\u01ef\u01f6"+
		"\b\31\1\2\u01f0\u01f1\7\64\2\2\u01f1\u01f2\5\62\32\2\u01f2\u01f3\b\31"+
		"\1\2\u01f3\u01f5\3\2\2\2\u01f4\u01f0\3\2\2\2\u01f5\u01f8\3\2\2\2\u01f6"+
		"\u01f4\3\2\2\2\u01f6\u01f7\3\2\2\2\u01f7\61\3\2\2\2\u01f8\u01f6\3\2\2"+
		"\2\u01f9\u01fa\5\64\33\2\u01fa\u01fb\b\32\1\2\u01fb\u0200\3\2\2\2\u01fc"+
		"\u01fd\5\66\34\2\u01fd\u01fe\b\32\1\2\u01fe\u0200\3\2\2\2\u01ff\u01f9"+
		"\3\2\2\2\u01ff\u01fc\3\2\2\2\u0200\63\3\2\2\2\u0201\u0202\7%\2\2\u0202"+
		"\u0203\5$\23\2\u0203\u0204\5$\23\2\u0204\u0205\b\33\1\2\u0205\u024f\3"+
		"\2\2\2\u0206\u0207\7&\2\2\u0207\u0208\7A\2\2\u0208\u0209\5$\23\2\u0209"+
		"\u020a\7B\2\2\u020a\u020b\7A\2\2\u020b\u020c\5$\23\2\u020c\u020d\7B\2"+
		"\2\u020d\u020e\b\33\1\2\u020e\u024f\3\2\2\2\u020f\u0210\7\'\2\2\u0210"+
		"\u0211\7A\2\2\u0211\u0212\5$\23\2\u0212\u0213\7B\2\2\u0213\u0214\7A\2"+
		"\2\u0214\u0215\5$\23\2\u0215\u0216\7B\2\2\u0216\u0217\b\33\1\2\u0217\u024f"+
		"\3\2\2\2\u0218\u0219\7(\2\2\u0219\u021a\5$\23\2\u021a\u021b\5$\23\2\u021b"+
		"\u021c\b\33\1\2\u021c\u024f\3\2\2\2\u021d\u021e\7)\2\2\u021e\u021f\5$"+
		"\23\2\u021f\u0220\5$\23\2\u0220\u0221\b\33\1\2\u0221\u024f\3\2\2\2\u0222"+
		"\u0223\7+\2\2\u0223\u0224\5$\23\2\u0224\u0225\b\33\1\2\u0225\u024f\3\2"+
		"\2\2\u0226\u0227\7,\2\2\u0227\u022b\5$\23\2\u0228\u022a\5$\23\2\u0229"+
		"\u0228\3\2\2\2\u022a\u022d\3\2\2\2\u022b\u0229\3\2\2\2\u022b\u022c\3\2"+
		"\2\2\u022c\u022e\3\2\2\2\u022d\u022b\3\2\2\2\u022e\u022f\b\33\1\2\u022f"+
		"\u024f\3\2\2\2\u0230\u0231\7-\2\2\u0231\u0232\5$\23\2\u0232\u0233\5$\23"+
		"\2\u0233\u0234\b\33\1\2\u0234\u024f\3\2\2\2\u0235\u0236\7.\2\2\u0236\u0237"+
		"\5$\23\2\u0237\u0238\5$\23\2\u0238\u0239\b\33\1\2\u0239\u024f\3\2\2\2"+
		"\u023a\u023b\7/\2\2\u023b\u023f\5$\23\2\u023c\u023e\5$\23\2\u023d\u023c"+
		"\3\2\2\2\u023e\u0241\3\2\2\2\u023f\u023d\3\2\2\2\u023f\u0240\3\2\2\2\u0240"+
		"\u0242\3\2\2\2\u0241\u023f\3\2\2\2\u0242\u0243\b\33\1\2\u0243\u024f\3"+
		"\2\2\2\u0244\u0245\7*\2\2\u0245\u0249\5$\23\2\u0246\u0248\5$\23\2\u0247"+
		"\u0246\3\2\2\2\u0248\u024b\3\2\2\2\u0249\u0247\3\2\2\2\u0249\u024a\3\2"+
		"\2\2\u024a\u024c\3\2\2\2\u024b\u0249\3\2\2\2\u024c\u024d\b\33\1\2\u024d"+
		"\u024f\3\2\2\2\u024e\u0201\3\2\2\2\u024e\u0206\3\2\2\2\u024e\u020f\3\2"+
		"\2\2\u024e\u0218\3\2\2\2\u024e\u021d\3\2\2\2\u024e\u0222\3\2\2\2\u024e"+
		"\u0226\3\2\2\2\u024e\u0230\3\2\2\2\u024e\u0235\3\2\2\2\u024e\u023a\3\2"+
		"\2\2\u024e\u0244\3\2\2\2\u024f\65\3\2\2\2\u0250\u0251\7G\2\2\u0251\u025e"+
		"\b\34\1\2\u0252\u0253\7F\2\2\u0253\u025e\b\34\1\2\u0254\u0255\7I\2\2\u0255"+
		"\u025e\b\34\1\2\u0256\u0257\7H\2\2\u0257\u025e\b\34\1\2\u0258\u0259\7"+
		"A\2\2\u0259\u025a\5$\23\2\u025a\u025b\7B\2\2\u025b\u025c\b\34\1\2\u025c"+
		"\u025e\3\2\2\2\u025d\u0250\3\2\2\2\u025d\u0252\3\2\2\2\u025d\u0254\3\2"+
		"\2\2\u025d\u0256\3\2\2\2\u025d\u0258\3\2\2\2\u025e\67\3\2\2\2\u025f\u0260"+
		"\7G\2\2\u0260\u0266\b\35\1\2\u0261\u0262\7F\2\2\u0262\u0266\b\35\1\2\u0263"+
		"\u0264\7H\2\2\u0264\u0266\b\35\1\2\u0265\u025f\3\2\2\2\u0265\u0261\3\2"+
		"\2\2\u0265\u0263\3\2\2\2\u02669\3\2\2\2\u0267\u0268\7J\2\2\u0268;\3\2"+
		"\2\2\u0269\u026a\7L\2\2\u026a=\3\2\2\2(ELT^fr\u0089\u008f\u00a1\u00a5"+
		"\u00b0\u00be\u00d6\u00e2\u00ec\u00f0\u00fd\u0110\u011f\u0132\u0190\u019a"+
		"\u01a5\u01af\u01cb\u01cd\u01da\u01dc\u01e9\u01eb\u01f6\u01ff\u022b\u023f"+
		"\u0249\u024e\u025d\u0265";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}