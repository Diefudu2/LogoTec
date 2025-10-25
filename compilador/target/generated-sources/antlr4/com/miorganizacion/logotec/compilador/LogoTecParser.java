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
		GD=31, GIRAIzQUIERDA=32, GI=33, OCULTATORTUGA=34, OT=35, PONCOLORLAPIZ=36, 
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
		RULE_varDecl = 4, RULE_varInit = 5, RULE_callProc = 6, RULE_execBlock = 7, 
		RULE_repiteBlock = 8, RULE_flowStmt = 9, RULE_siStmt = 10, RULE_hazHastaStmt = 11, 
		RULE_hastaStmt = 12, RULE_hazMientrasStmt = 13, RULE_mientrasStmt = 14, 
		RULE_turtleCmd = 15, RULE_expression = 16, RULE_logicTerm = 17, RULE_logicFactor = 18, 
		RULE_relational = 19, RULE_arithExpr = 20, RULE_term = 21, RULE_factor = 22, 
		RULE_exponent = 23, RULE_funcCall = 24, RULE_primary = 25, RULE_literalOrString = 26, 
		RULE_cmtFirstLine = 27, RULE_colorName = 28;
	public static final String[] ruleNames = {
		"program", "proceduresBlock", "procedureDecl", "sentence", "varDecl", 
		"varInit", "callProc", "execBlock", "repiteBlock", "flowStmt", "siStmt", 
		"hazHastaStmt", "hastaStmt", "hazMientrasStmt", "mientrasStmt", "turtleCmd", 
		"expression", "logicTerm", "logicFactor", "relational", "arithExpr", "term", 
		"factor", "exponent", "funcCall", "primary", "literalOrString", "cmtFirstLine", 
		"colorName"
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
			setState(59);
			_la = _input.LA(1);
			if (_la==FIRSTLINE_COMMENT) {
				{
				setState(58); cmtFirstLine();
				}
			}

			setState(61); ((ProgramContext)_localctx).p = proceduresBlock();
			setState(62); match(EOF);

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
			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PARA) | (1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << ATRAS) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIzQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT))) != 0) || _la==ID) {
				{
				setState(71);
				switch (_input.LA(1)) {
				case PARA:
					{
					setState(65); ((ProceduresBlockContext)_localctx).procedureDecl = procedureDecl();
					 decls.add(((ProceduresBlockContext)_localctx).procedureDecl.node); 
					}
					break;
				case HAZ:
				case EJECUTA:
				case REPITE:
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
				case ATRAS:
				case GIRADERECHA:
				case GD:
				case GIRAIzQUIERDA:
				case GI:
				case OCULTATORTUGA:
				case OT:
				case ID:
					{
					setState(68); ((ProceduresBlockContext)_localctx).sentence = sentence();
					 mainBody.add(((ProceduresBlockContext)_localctx).sentence.node); 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(75);
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
		 List<String> params = new ArrayList<>(); List<StmtNode> body = new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78); match(PARA);
			setState(79); ((ProcedureDeclContext)_localctx).procName = match(ID);
			setState(80); match(BRACKET_OPEN);
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(81); ((ProcedureDeclContext)_localctx).param = match(ID);
				 params.add(((ProcedureDeclContext)_localctx).param.getText()); 
				}
				}
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(88); match(BRACKET_CLOSE);
			setState(89); match(BRACKET_OPEN);
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << ATRAS) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIzQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT))) != 0) || _la==ID) {
				{
				{
				setState(90); ((ProcedureDeclContext)_localctx).sentence = sentence();
				body.add(((ProcedureDeclContext)_localctx).sentence.node);
				}
				}
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(98); match(BRACKET_CLOSE);
			setState(99); match(FIN);

			    if (!params.isEmpty()) atLeastOneVariable = true; // contar parámetros como variables válidas
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
		enterRule(_localctx, 6, RULE_sentence);
		try {
			setState(120);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(102); ((SentenceContext)_localctx).varDecl = varDecl();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).varDecl.node; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(105); ((SentenceContext)_localctx).varInit = varInit();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).varInit.node; 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(108); ((SentenceContext)_localctx).turtleCmd = turtleCmd();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).turtleCmd.node; 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(111); ((SentenceContext)_localctx).flowStmt = flowStmt();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).flowStmt.node; 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(114); ((SentenceContext)_localctx).execBlock = execBlock();
				 ((SentenceContext)_localctx).node =  ((SentenceContext)_localctx).execBlock.node; 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(117); ((SentenceContext)_localctx).callProc = callProc();
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
		public ExpressionContext expr;
		public TerminalNode ID() { return getToken(LogoTecParser.ID, 0); }
		public LiteralOrStringContext literalOrString() {
			return getRuleContext(LiteralOrStringContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(LogoTecParser.SEMICOLON, 0); }
		public TerminalNode HAZ() { return getToken(LogoTecParser.HAZ, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
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
			setState(122); match(HAZ);
			setState(123); ((VarDeclContext)_localctx).name = match(ID);
			setState(130);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(124); ((VarDeclContext)_localctx).value = literalOrString();
				 
				                        declareOrAssign((((VarDeclContext)_localctx).name!=null?((VarDeclContext)_localctx).name.getText():null), ValueType.infer(((VarDeclContext)_localctx).value.node), null);
				                        ((VarDeclContext)_localctx).node =  new VarDeclNode((((VarDeclContext)_localctx).name!=null?((VarDeclContext)_localctx).name.getText():null), ((VarDeclContext)_localctx).value.node);
				                      
				}
				break;
			case 2:
				{
				setState(127); ((VarDeclContext)_localctx).expr = expression();
				 
				                        declareOrAssign((((VarDeclContext)_localctx).name!=null?((VarDeclContext)_localctx).name.getText():null), ValueType.infer(((VarDeclContext)_localctx).expr.node), null);
				                        ((VarDeclContext)_localctx).node =  new VarDeclNode((((VarDeclContext)_localctx).name!=null?((VarDeclContext)_localctx).name.getText():null), ((VarDeclContext)_localctx).expr.node);
				                      
				}
				break;
			}
			setState(133);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(132); match(SEMICOLON);
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

	public static class VarInitContext extends ParserRuleContext {
		public StmtNode node;
		public Token name;
		public ExpressionContext expression;
		public TerminalNode ASSIGN() { return getToken(LogoTecParser.ASSIGN, 0); }
		public TerminalNode ID() { return getToken(LogoTecParser.ID, 0); }
		public TerminalNode SEMICOLON() { return getToken(LogoTecParser.SEMICOLON, 0); }
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135); match(INIC);
			setState(136); ((VarInitContext)_localctx).name = match(ID);
			setState(137); match(ASSIGN);
			setState(138); ((VarInitContext)_localctx).expression = expression();
			setState(140);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(139); match(SEMICOLON);
				}
			}


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
		enterRule(_localctx, 12, RULE_callProc);
		 List<ExprNode> args = new ArrayList<>(); 
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(144); ((CallProcContext)_localctx).proc = match(ID);
			setState(162);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(145); match(BRACKET_OPEN);
				setState(151);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 38)) & ~0x3f) == 0 && ((1L << (_la - 38)) & ((1L << (IGUALESQ - 38)) | (1L << (YFUNC - 38)) | (1L << (OFUNC - 38)) | (1L << (MAYORQ - 38)) | (1L << (MENORQ - 38)) | (1L << (DIFERENCIA - 38)) | (1L << (AZAR - 38)) | (1L << (PRODUCTO - 38)) | (1L << (POTENCIA - 38)) | (1L << (DIVISION - 38)) | (1L << (SUMA - 38)) | (1L << (NOT - 38)) | (1L << (PAR_OPEN - 38)) | (1L << (BOOLEAN - 38)) | (1L << (NUMBER - 38)) | (1L << (STRING - 38)) | (1L << (ID - 38)))) != 0)) {
					{
					{
					setState(146); ((CallProcContext)_localctx).expression = expression();
					args.add(((CallProcContext)_localctx).expression.node);
					}
					}
					setState(153);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(154); match(BRACKET_CLOSE);
				}
				break;
			case 2:
				{
				setState(158); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(155); ((CallProcContext)_localctx).expression = expression();
						args.add(((CallProcContext)_localctx).expression.node);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(160); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
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
		enterRule(_localctx, 14, RULE_execBlock);
		 List<StmtNode> body = new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166); match(EJECUTA);
			setState(167); match(BRACKET_OPEN);
			setState(173);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << ATRAS) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIzQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT))) != 0) || _la==ID) {
				{
				{
				setState(168); ((ExecBlockContext)_localctx).sentence = sentence();
				body.add(((ExecBlockContext)_localctx).sentence.node);
				}
				}
				setState(175);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(176); match(BRACKET_CLOSE);

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
		enterRule(_localctx, 16, RULE_repiteBlock);
		 List<StmtNode> body = new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179); match(REPITE);
			setState(180); ((RepiteBlockContext)_localctx).times = expression();
			setState(181); match(BRACKET_OPEN);
			setState(187);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << ATRAS) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIzQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT))) != 0) || _la==ID) {
				{
				{
				setState(182); ((RepiteBlockContext)_localctx).sentence = sentence();
				body.add(((RepiteBlockContext)_localctx).sentence.node);
				}
				}
				setState(189);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(190); match(BRACKET_CLOSE);

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
		enterRule(_localctx, 18, RULE_flowStmt);
		try {
			setState(211);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(193); ((FlowStmtContext)_localctx).siStmt = siStmt();
				 ((FlowStmtContext)_localctx).node =  ((FlowStmtContext)_localctx).siStmt.node; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(196); ((FlowStmtContext)_localctx).hazHastaStmt = hazHastaStmt();
				 ((FlowStmtContext)_localctx).node =  ((FlowStmtContext)_localctx).hazHastaStmt.node; 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(199); ((FlowStmtContext)_localctx).hastaStmt = hastaStmt();
				 ((FlowStmtContext)_localctx).node =  ((FlowStmtContext)_localctx).hastaStmt.node; 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(202); ((FlowStmtContext)_localctx).hazMientrasStmt = hazMientrasStmt();
				 ((FlowStmtContext)_localctx).node =  ((FlowStmtContext)_localctx).hazMientrasStmt.node; 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(205); ((FlowStmtContext)_localctx).mientrasStmt = mientrasStmt();
				 ((FlowStmtContext)_localctx).node =  ((FlowStmtContext)_localctx).mientrasStmt.node; 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(208); ((FlowStmtContext)_localctx).repiteBlock = repiteBlock();
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
		enterRule(_localctx, 20, RULE_siStmt);
		 List<StmtNode> thenBody = new ArrayList<>(); List<StmtNode> elseBody = new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(213); match(SI);
			setState(214); match(PAR_OPEN);
			setState(215); ((SiStmtContext)_localctx).cond = expression();
			setState(216); match(PAR_CLOSE);
			setState(217); match(BRACKET_OPEN);
			setState(223);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << ATRAS) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIzQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT))) != 0) || _la==ID) {
				{
				{
				setState(218); ((SiStmtContext)_localctx).sentence = sentence();
				thenBody.add(((SiStmtContext)_localctx).sentence.node);
				}
				}
				setState(225);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(226); match(BRACKET_CLOSE);
			setState(237);
			_la = _input.LA(1);
			if (_la==BRACKET_OPEN) {
				{
				setState(227); match(BRACKET_OPEN);
				setState(233);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << ATRAS) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIzQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT))) != 0) || _la==ID) {
					{
					{
					setState(228); ((SiStmtContext)_localctx).sentence = sentence();
					elseBody.add(((SiStmtContext)_localctx).sentence.node);
					}
					}
					setState(235);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(236); match(BRACKET_CLOSE);
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
			setState(241); match(HAZ);
			setState(242); match(DOT);
			setState(243); match(HASTA);
			setState(244); match(BRACKET_OPEN);
			setState(250);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << ATRAS) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIzQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT))) != 0) || _la==ID) {
				{
				{
				setState(245); ((HazHastaStmtContext)_localctx).sentence = sentence();
				body.add(((HazHastaStmtContext)_localctx).sentence.node);
				}
				}
				setState(252);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(253); match(BRACKET_CLOSE);
			setState(254); match(PAR_OPEN);
			setState(255); ((HazHastaStmtContext)_localctx).cond = expression();
			setState(256); match(PAR_CLOSE);

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
		enterRule(_localctx, 24, RULE_hastaStmt);
		 List<StmtNode> body = new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(259); match(HASTA);
			setState(260); match(PAR_OPEN);
			setState(261); ((HastaStmtContext)_localctx).cond = expression();
			setState(262); match(PAR_CLOSE);
			setState(263); match(BRACKET_OPEN);
			setState(269);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << ATRAS) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIzQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT))) != 0) || _la==ID) {
				{
				{
				setState(264); ((HastaStmtContext)_localctx).sentence = sentence();
				body.add(((HastaStmtContext)_localctx).sentence.node);
				}
				}
				setState(271);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(272); match(BRACKET_CLOSE);

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
		enterRule(_localctx, 26, RULE_hazMientrasStmt);
		 List<StmtNode> body = new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275); match(HAZ);
			setState(276); match(DOT);
			setState(277); match(MIENTRAS);
			setState(278); match(BRACKET_OPEN);
			setState(284);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << ATRAS) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIzQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT))) != 0) || _la==ID) {
				{
				{
				setState(279); ((HazMientrasStmtContext)_localctx).sentence = sentence();
				body.add(((HazMientrasStmtContext)_localctx).sentence.node);
				}
				}
				setState(286);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(287); match(BRACKET_CLOSE);
			setState(288); match(PAR_OPEN);
			setState(289); ((HazMientrasStmtContext)_localctx).cond = expression();
			setState(290); match(PAR_CLOSE);

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
		enterRule(_localctx, 28, RULE_mientrasStmt);
		 List<StmtNode> body = new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293); match(MIENTRAS);
			setState(294); match(PAR_OPEN);
			setState(295); ((MientrasStmtContext)_localctx).cond = expression();
			setState(296); match(PAR_CLOSE);
			setState(297); match(BRACKET_OPEN);
			setState(303);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAZ) | (1L << EJECUTA) | (1L << REPITE) | (1L << PONPOS) | (1L << PONXY) | (1L << PONRUMBO) | (1L << RUMBO) | (1L << PONX) | (1L << PONY) | (1L << BAJALAPIZ) | (1L << BL) | (1L << SUBELAPIZ) | (1L << SB) | (1L << CENTRO) | (1L << ESPERA) | (1L << INC) | (1L << SI) | (1L << HASTA) | (1L << MIENTRAS) | (1L << INIC) | (1L << AVANZA) | (1L << AV) | (1L << RETROCEDE) | (1L << RE) | (1L << ATRAS) | (1L << GIRADERECHA) | (1L << GD) | (1L << GIRAIzQUIERDA) | (1L << GI) | (1L << OCULTATORTUGA) | (1L << OT))) != 0) || _la==ID) {
				{
				{
				setState(298); ((MientrasStmtContext)_localctx).sentence = sentence();
				body.add(((MientrasStmtContext)_localctx).sentence.node);
				}
				}
				setState(305);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(306); match(BRACKET_CLOSE);

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
		public TerminalNode ATRAS() { return getToken(LogoTecParser.ATRAS, 0); }
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
		enterRule(_localctx, 30, RULE_turtleCmd);
		try {
			setState(401);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(309); match(AVANZA);
				setState(310); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new ForwardNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(313); match(AV);
				setState(314); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new ForwardNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(317); match(RETROCEDE);
				setState(318); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new BackwardNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(321); match(RE);
				setState(322); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new BackwardNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(325); match(ATRAS);
				setState(326); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new BackwardNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(329); match(GIRADERECHA);
				setState(330); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new TurnRightNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(333); match(GD);
				setState(334); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new TurnRightNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(337); match(GIRAIzQUIERDA);
				setState(338); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new TurnLeftNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(341); match(GI);
				setState(342); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new TurnLeftNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(345); match(OCULTATORTUGA);
				 ((TurtleCmdContext)_localctx).node =  new HideTurtleNode(); 
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(347); match(OT);
				 ((TurtleCmdContext)_localctx).node =  new HideTurtleNode(); 
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(349); match(PONPOS);
				setState(350); match(BRACKET_OPEN);
				setState(351); ((TurtleCmdContext)_localctx).x = expression();
				setState(352); ((TurtleCmdContext)_localctx).y = expression();
				setState(353); match(BRACKET_CLOSE);
				 ((TurtleCmdContext)_localctx).node =  new SetPosNode(((TurtleCmdContext)_localctx).x.node, ((TurtleCmdContext)_localctx).y.node, true); 
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(356); match(PONXY);
				setState(357); ((TurtleCmdContext)_localctx).x = expression();
				setState(358); ((TurtleCmdContext)_localctx).y = expression();
				 ((TurtleCmdContext)_localctx).node =  new SetPosNode(((TurtleCmdContext)_localctx).x.node, ((TurtleCmdContext)_localctx).y.node, false); 
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(361); match(PONRUMBO);
				setState(362); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new SetHeadingNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(365); match(RUMBO);
				 ((TurtleCmdContext)_localctx).node =  new ShowHeadingNode(); 
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(367); match(PONX);
				setState(368); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new SetXNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(371); match(PONY);
				setState(372); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new SetYNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(375); match(BAJALAPIZ);
				 ((TurtleCmdContext)_localctx).node =  new PenDownNode(); 
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(377); match(BL);
				 ((TurtleCmdContext)_localctx).node =  new PenDownNode(); 
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(379); match(SUBELAPIZ);
				 ((TurtleCmdContext)_localctx).node =  new PenUpNode(); 
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 21);
				{
				setState(381); match(SB);
				 ((TurtleCmdContext)_localctx).node =  new PenUpNode(); 
				}
				break;
			case 22:
				enterOuterAlt(_localctx, 22);
				{
				setState(383); match(CENTRO);
				 ((TurtleCmdContext)_localctx).node =  new CenterNode(); 
				}
				break;
			case 23:
				enterOuterAlt(_localctx, 23);
				{
				setState(385); match(ESPERA);
				setState(386); ((TurtleCmdContext)_localctx).e = expression();
				 ((TurtleCmdContext)_localctx).node =  new WaitNode(((TurtleCmdContext)_localctx).e.node); 
				}
				break;
			case 24:
				enterOuterAlt(_localctx, 24);
				{
				setState(389); match(INC);
				setState(390); match(BRACKET_OPEN);
				setState(391); ((TurtleCmdContext)_localctx).id = match(ID);
				setState(392); match(BRACKET_CLOSE);
				 ((TurtleCmdContext)_localctx).node =  new IncNode(new VarRefNode((((TurtleCmdContext)_localctx).id!=null?((TurtleCmdContext)_localctx).id.getText():null)), new ConstNode(1)); 
				}
				break;
			case 25:
				enterOuterAlt(_localctx, 25);
				{
				setState(394); match(INC);
				setState(395); match(BRACKET_OPEN);
				setState(396); ((TurtleCmdContext)_localctx).id = match(ID);
				setState(397); ((TurtleCmdContext)_localctx).n = expression();
				setState(398); match(BRACKET_CLOSE);
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
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(403); ((ExpressionContext)_localctx).t1 = logicTerm();
			 ((ExpressionContext)_localctx).node =  ((ExpressionContext)_localctx).t1.node; ((ExpressionContext)_localctx).val =  ((ExpressionContext)_localctx).t1.val; 
			setState(411);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(405); match(OR);
					setState(406); ((ExpressionContext)_localctx).t2 = logicTerm();
					 ((ExpressionContext)_localctx).node =  new LogicalOrNode(_localctx.node, ((ExpressionContext)_localctx).t2.node); 
					}
					} 
				}
				setState(413);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
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
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(414); ((LogicTermContext)_localctx).f1 = logicFactor();
			 ((LogicTermContext)_localctx).node =  ((LogicTermContext)_localctx).f1.node; ((LogicTermContext)_localctx).val =  ((LogicTermContext)_localctx).f1.val; 
			setState(422);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(416); match(AND);
					setState(417); ((LogicTermContext)_localctx).f2 = logicFactor();
					 ((LogicTermContext)_localctx).node =  new LogicalAndNode(_localctx.node, ((LogicTermContext)_localctx).f2.node); 
					}
					} 
				}
				setState(424);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
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
			setState(432);
			switch (_input.LA(1)) {
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(425); match(NOT);
				setState(426); ((LogicFactorContext)_localctx).lf = logicFactor();
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
				setState(429); ((LogicFactorContext)_localctx).r = relational();
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
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(434); ((RelationalContext)_localctx).a1 = arithExpr();
			 ((RelationalContext)_localctx).node =  ((RelationalContext)_localctx).a1.node; ((RelationalContext)_localctx).val =  ((RelationalContext)_localctx).a1.val; 
			setState(462);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(460);
					switch (_input.LA(1)) {
					case GT:
						{
						setState(436); match(GT);
						setState(437); ((RelationalContext)_localctx).a2 = arithExpr();
						 ((RelationalContext)_localctx).node =  new GreaterThanNode(_localctx.node, ((RelationalContext)_localctx).a2.node);
						}
						break;
					case LT:
						{
						setState(440); match(LT);
						setState(441); ((RelationalContext)_localctx).a2 = arithExpr();
						 ((RelationalContext)_localctx).node =  new LessThanNode(_localctx.node, ((RelationalContext)_localctx).a2.node); 
						}
						break;
					case GEQ:
						{
						setState(444); match(GEQ);
						setState(445); ((RelationalContext)_localctx).a2 = arithExpr();
						 ((RelationalContext)_localctx).node =  new GreaterEqualNode(_localctx.node, ((RelationalContext)_localctx).a2.node);  
						}
						break;
					case LEQ:
						{
						setState(448); match(LEQ);
						setState(449); ((RelationalContext)_localctx).a2 = arithExpr();
						 ((RelationalContext)_localctx).node =  new LessEqualNode(_localctx.node, ((RelationalContext)_localctx).a2.node);
						}
						break;
					case EQ:
						{
						setState(452); match(EQ);
						setState(453); ((RelationalContext)_localctx).a2 = arithExpr();
						 ((RelationalContext)_localctx).node =  new EqualsNode(_localctx.node, ((RelationalContext)_localctx).a2.node);  
						}
						break;
					case NEQ:
						{
						setState(456); match(NEQ);
						setState(457); ((RelationalContext)_localctx).a2 = arithExpr();
						 ((RelationalContext)_localctx).node =  new NotEqualsNode(_localctx.node, ((RelationalContext)_localctx).a2.node); ; 
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(464);
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
			setState(465); ((ArithExprContext)_localctx).t1 = term();
			 ((ArithExprContext)_localctx).node =  ((ArithExprContext)_localctx).t1.node; ((ArithExprContext)_localctx).val =  ((ArithExprContext)_localctx).t1.val; 
			setState(477);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(475);
					switch (_input.LA(1)) {
					case PLUS:
						{
						setState(467); match(PLUS);
						setState(468); ((ArithExprContext)_localctx).t2 = term();
						 ((ArithExprContext)_localctx).node =  new AdditionNode(_localctx.node, ((ArithExprContext)_localctx).t2.node); 
						}
						break;
					case MINUS:
						{
						setState(471); match(MINUS);
						setState(472); ((ArithExprContext)_localctx).t2 = term();
						 ((ArithExprContext)_localctx).node =  new SubtractionNode(_localctx.node, ((ArithExprContext)_localctx).t2.node);  
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(479);
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
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(480); ((TermContext)_localctx).f1 = factor();
			 ((TermContext)_localctx).node =  ((TermContext)_localctx).f1.node; ((TermContext)_localctx).val =  ((TermContext)_localctx).f1.val; 
			setState(492);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(490);
					switch (_input.LA(1)) {
					case MULT:
						{
						setState(482); match(MULT);
						setState(483); ((TermContext)_localctx).f2 = factor();
						 ((TermContext)_localctx).node =  new MultiplicationNode(_localctx.node, ((TermContext)_localctx).f2.node);  
						}
						break;
					case DIV:
						{
						setState(486); match(DIV);
						setState(487); ((TermContext)_localctx).f2 = factor();
						 ((TermContext)_localctx).node =  new DivisionNode(_localctx.node, ((TermContext)_localctx).f2.node); 
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(494);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
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
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(495); ((FactorContext)_localctx).e1 = exponent();
			 ((FactorContext)_localctx).node =  ((FactorContext)_localctx).e1.node; ((FactorContext)_localctx).val =  ((FactorContext)_localctx).e1.val; 
			setState(503);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(497); match(EXP);
					setState(498); ((FactorContext)_localctx).e2 = exponent();
					 ((FactorContext)_localctx).node =  new ExponentiationNode(_localctx.node, ((FactorContext)_localctx).e2.node);  
					}
					} 
				}
				setState(505);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
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
		enterRule(_localctx, 46, RULE_exponent);
		try {
			setState(512);
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
				setState(506); ((ExponentContext)_localctx).funcCall = funcCall();
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
				setState(509); ((ExponentContext)_localctx).primary = primary();
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
		enterRule(_localctx, 48, RULE_funcCall);
		try {
			int _alt;
			setState(591);
			switch (_input.LA(1)) {
			case IGUALESQ:
				enterOuterAlt(_localctx, 1);
				{
				setState(514); match(IGUALESQ);
				setState(515); ((FuncCallContext)_localctx).e1 = expression();
				setState(516); ((FuncCallContext)_localctx).e2 = expression();
				 ((FuncCallContext)_localctx).node =  new EqualsFuncNode(((FuncCallContext)_localctx).e1.node, ((FuncCallContext)_localctx).e2.node); 
				}
				break;
			case YFUNC:
				enterOuterAlt(_localctx, 2);
				{
				setState(519); match(YFUNC);
				setState(520); match(PAR_OPEN);
				setState(521); ((FuncCallContext)_localctx).e1 = expression();
				setState(522); match(PAR_CLOSE);
				setState(523); match(PAR_OPEN);
				setState(524); ((FuncCallContext)_localctx).e2 = expression();
				setState(525); match(PAR_CLOSE);
				 ((FuncCallContext)_localctx).node =  new LogicalAndNode(((FuncCallContext)_localctx).e1.node,((FuncCallContext)_localctx).e2.node); 
				}
				break;
			case OFUNC:
				enterOuterAlt(_localctx, 3);
				{
				setState(528); match(OFUNC);
				setState(529); match(PAR_OPEN);
				setState(530); ((FuncCallContext)_localctx).e1 = expression();
				setState(531); match(PAR_CLOSE);
				setState(532); match(PAR_OPEN);
				setState(533); ((FuncCallContext)_localctx).e2 = expression();
				setState(534); match(PAR_CLOSE);
				 ((FuncCallContext)_localctx).node =  new LogicalOrNode(((FuncCallContext)_localctx).e1.node,((FuncCallContext)_localctx).e2.node);  
				}
				break;
			case MAYORQ:
				enterOuterAlt(_localctx, 4);
				{
				setState(537); match(MAYORQ);
				setState(538); ((FuncCallContext)_localctx).e1 = expression();
				setState(539); ((FuncCallContext)_localctx).e2 = expression();
				 ((FuncCallContext)_localctx).node =  new GreaterThanNode(((FuncCallContext)_localctx).e1.node,((FuncCallContext)_localctx).e2.node);  
				}
				break;
			case MENORQ:
				enterOuterAlt(_localctx, 5);
				{
				setState(542); match(MENORQ);
				setState(543); ((FuncCallContext)_localctx).e1 = expression();
				setState(544); ((FuncCallContext)_localctx).e2 = expression();
				 ((FuncCallContext)_localctx).node =  new LessThanNode(((FuncCallContext)_localctx).e1.node,((FuncCallContext)_localctx).e2.node);  
				}
				break;
			case AZAR:
				enterOuterAlt(_localctx, 6);
				{
				setState(547); match(AZAR);
				setState(548); ((FuncCallContext)_localctx).e = expression();
				 ((FuncCallContext)_localctx).node =  new RandomNode(((FuncCallContext)_localctx).e.node); 
				}
				break;
			case PRODUCTO:
				enterOuterAlt(_localctx, 7);
				{
				setState(551); match(PRODUCTO);
				setState(552); ((FuncCallContext)_localctx).e1 = expression();
				setState(556);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(553); ((FuncCallContext)_localctx).expression = expression();
						((FuncCallContext)_localctx).eMore.add(((FuncCallContext)_localctx).expression);
						}
						} 
					}
					setState(558);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
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
				setState(561); match(POTENCIA);
				setState(562); ((FuncCallContext)_localctx).e1 = expression();
				setState(563); ((FuncCallContext)_localctx).e2 = expression();
				 ((FuncCallContext)_localctx).node =  new ExponentiationNode(((FuncCallContext)_localctx).e1.node, ((FuncCallContext)_localctx).e2.node); 
				}
				break;
			case DIVISION:
				enterOuterAlt(_localctx, 9);
				{
				setState(566); match(DIVISION);
				setState(567); ((FuncCallContext)_localctx).e1 = expression();
				setState(568); ((FuncCallContext)_localctx).e2 = expression();
				 ((FuncCallContext)_localctx).node =  new DivisionNode(((FuncCallContext)_localctx).e1.node, ((FuncCallContext)_localctx).e2.node); 
				}
				break;
			case SUMA:
				enterOuterAlt(_localctx, 10);
				{
				setState(571); match(SUMA);
				setState(572); ((FuncCallContext)_localctx).e1 = expression();
				setState(576);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(573); ((FuncCallContext)_localctx).expression = expression();
						((FuncCallContext)_localctx).eMore.add(((FuncCallContext)_localctx).expression);
						}
						} 
					}
					setState(578);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
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
				setState(581); match(DIFERENCIA);
				setState(582); ((FuncCallContext)_localctx).e1 = expression();
				setState(586);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(583); ((FuncCallContext)_localctx).expression = expression();
						((FuncCallContext)_localctx).eMore.add(((FuncCallContext)_localctx).expression);
						}
						} 
					}
					setState(588);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
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
		enterRule(_localctx, 50, RULE_primary);
		try {
			setState(606);
			switch (_input.LA(1)) {
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(593); ((PrimaryContext)_localctx).NUMBER = match(NUMBER);
				 ((PrimaryContext)_localctx).node =  new ConstNode(Integer.parseInt((((PrimaryContext)_localctx).NUMBER!=null?((PrimaryContext)_localctx).NUMBER.getText():null)));
				                ((PrimaryContext)_localctx).val =  Value.integer(Integer.parseInt((((PrimaryContext)_localctx).NUMBER!=null?((PrimaryContext)_localctx).NUMBER.getText():null))); 
				}
				break;
			case BOOLEAN:
				enterOuterAlt(_localctx, 2);
				{
				setState(595); ((PrimaryContext)_localctx).BOOLEAN = match(BOOLEAN);
				 ((PrimaryContext)_localctx).node =  new ConstNode(Boolean.parseBoolean((((PrimaryContext)_localctx).BOOLEAN!=null?((PrimaryContext)_localctx).BOOLEAN.getText():null)));
				                ((PrimaryContext)_localctx).val =  Value.bool(Boolean.parseBoolean((((PrimaryContext)_localctx).BOOLEAN!=null?((PrimaryContext)_localctx).BOOLEAN.getText():null))); 
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(597); ((PrimaryContext)_localctx).ID = match(ID);
				 ((PrimaryContext)_localctx).node =  new VarRefNode((((PrimaryContext)_localctx).ID!=null?((PrimaryContext)_localctx).ID.getText():null));
				                ((PrimaryContext)_localctx).val =  Value.unknown(); 
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 4);
				{
				setState(599); ((PrimaryContext)_localctx).STRING = match(STRING);
				 ((PrimaryContext)_localctx).node =  new ConstNode((((PrimaryContext)_localctx).STRING!=null?((PrimaryContext)_localctx).STRING.getText():null).substring(1,(((PrimaryContext)_localctx).STRING!=null?((PrimaryContext)_localctx).STRING.getText():null).length()-1));
				                ((PrimaryContext)_localctx).val =  Value.string((((PrimaryContext)_localctx).STRING!=null?((PrimaryContext)_localctx).STRING.getText():null).substring(1,(((PrimaryContext)_localctx).STRING!=null?((PrimaryContext)_localctx).STRING.getText():null).length()-1)); 
				}
				break;
			case PAR_OPEN:
				enterOuterAlt(_localctx, 5);
				{
				setState(601); match(PAR_OPEN);
				setState(602); ((PrimaryContext)_localctx).expression = expression();
				setState(603); match(PAR_CLOSE);
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
		enterRule(_localctx, 52, RULE_literalOrString);
		try {
			setState(614);
			switch (_input.LA(1)) {
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(608); ((LiteralOrStringContext)_localctx).NUMBER = match(NUMBER);
				 ((LiteralOrStringContext)_localctx).node =  new ConstNode(Integer.parseInt((((LiteralOrStringContext)_localctx).NUMBER!=null?((LiteralOrStringContext)_localctx).NUMBER.getText():null))); 
				}
				break;
			case BOOLEAN:
				enterOuterAlt(_localctx, 2);
				{
				setState(610); ((LiteralOrStringContext)_localctx).BOOLEAN = match(BOOLEAN);
				 ((LiteralOrStringContext)_localctx).node =  new ConstNode(Boolean.parseBoolean((((LiteralOrStringContext)_localctx).BOOLEAN!=null?((LiteralOrStringContext)_localctx).BOOLEAN.getText():null))); 
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(612); ((LiteralOrStringContext)_localctx).STRING = match(STRING);
				 ((LiteralOrStringContext)_localctx).node =  new ConstNode((((LiteralOrStringContext)_localctx).STRING!=null?((LiteralOrStringContext)_localctx).STRING.getText():null).substring(1,(((LiteralOrStringContext)_localctx).STRING!=null?((LiteralOrStringContext)_localctx).STRING.getText():null).length()-1)); 
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
		enterRule(_localctx, 54, RULE_cmtFirstLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(616); match(FIRSTLINE_COMMENT);
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
		enterRule(_localctx, 56, RULE_colorName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(619); ((ColorNameContext)_localctx).c = match(COLOR);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3Q\u0270\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\5\2>\n\2\3\2\3"+
		"\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\7\3J\n\3\f\3\16\3M\13\3\3\3\3\3\3\4"+
		"\3\4\3\4\3\4\3\4\7\4V\n\4\f\4\16\4Y\13\4\3\4\3\4\3\4\3\4\3\4\7\4`\n\4"+
		"\f\4\16\4c\13\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5{\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\5\6\u0085\n\6\3\6\5\6\u0088\n\6\3\7\3\7\3\7\3\7\3\7\5\7\u008f\n"+
		"\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\7\b\u0098\n\b\f\b\16\b\u009b\13\b\3\b\3"+
		"\b\3\b\3\b\6\b\u00a1\n\b\r\b\16\b\u00a2\5\b\u00a5\n\b\3\b\3\b\3\t\3\t"+
		"\3\t\3\t\3\t\7\t\u00ae\n\t\f\t\16\t\u00b1\13\t\3\t\3\t\3\t\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\7\n\u00bc\n\n\f\n\16\n\u00bf\13\n\3\n\3\n\3\n\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\5\13\u00d6\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00e0\n\f"+
		"\f\f\16\f\u00e3\13\f\3\f\3\f\3\f\3\f\3\f\7\f\u00ea\n\f\f\f\16\f\u00ed"+
		"\13\f\3\f\5\f\u00f0\n\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u00fb"+
		"\n\r\f\r\16\r\u00fe\13\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\7\16\u010e\n\16\f\16\16\16\u0111\13\16\3\16\3\16\3"+
		"\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\7\17\u011d\n\17\f\17\16\17\u0120"+
		"\13\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\7\20\u0130\n\20\f\20\16\20\u0133\13\20\3\20\3\20\3\20\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u0194\n\21\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\7\22\u019c\n\22\f\22\16\22\u019f\13\22\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\7\23\u01a7\n\23\f\23\16\23\u01aa\13\23\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\5\24\u01b3\n\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\7\25\u01cf\n\25\f\25\16\25\u01d2\13\25\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\7\26\u01de\n\26\f\26\16\26\u01e1\13"+
		"\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\7\27\u01ed\n\27"+
		"\f\27\16\27\u01f0\13\27\3\30\3\30\3\30\3\30\3\30\3\30\7\30\u01f8\n\30"+
		"\f\30\16\30\u01fb\13\30\3\31\3\31\3\31\3\31\3\31\3\31\5\31\u0203\n\31"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\7\32\u022d"+
		"\n\32\f\32\16\32\u0230\13\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3"+
		"\32\3\32\3\32\3\32\3\32\3\32\3\32\7\32\u0241\n\32\f\32\16\32\u0244\13"+
		"\32\3\32\3\32\3\32\3\32\3\32\7\32\u024b\n\32\f\32\16\32\u024e\13\32\3"+
		"\32\3\32\5\32\u0252\n\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\5\33\u0261\n\33\3\34\3\34\3\34\3\34\3\34\3\34\5\34"+
		"\u0269\n\34\3\35\3\35\3\35\3\36\3\36\3\36\2\2\37\2\4\6\b\n\f\16\20\22"+
		"\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:\2\2\u02ab\2=\3\2\2\2\4K\3\2"+
		"\2\2\6P\3\2\2\2\bz\3\2\2\2\n|\3\2\2\2\f\u0089\3\2\2\2\16\u0092\3\2\2\2"+
		"\20\u00a8\3\2\2\2\22\u00b5\3\2\2\2\24\u00d5\3\2\2\2\26\u00d7\3\2\2\2\30"+
		"\u00f3\3\2\2\2\32\u0105\3\2\2\2\34\u0115\3\2\2\2\36\u0127\3\2\2\2 \u0193"+
		"\3\2\2\2\"\u0195\3\2\2\2$\u01a0\3\2\2\2&\u01b2\3\2\2\2(\u01b4\3\2\2\2"+
		"*\u01d3\3\2\2\2,\u01e2\3\2\2\2.\u01f1\3\2\2\2\60\u0202\3\2\2\2\62\u0251"+
		"\3\2\2\2\64\u0260\3\2\2\2\66\u0268\3\2\2\28\u026a\3\2\2\2:\u026d\3\2\2"+
		"\2<>\58\35\2=<\3\2\2\2=>\3\2\2\2>?\3\2\2\2?@\5\4\3\2@A\7\2\2\3AB\b\2\1"+
		"\2B\3\3\2\2\2CD\5\6\4\2DE\b\3\1\2EJ\3\2\2\2FG\5\b\5\2GH\b\3\1\2HJ\3\2"+
		"\2\2IC\3\2\2\2IF\3\2\2\2JM\3\2\2\2KI\3\2\2\2KL\3\2\2\2LN\3\2\2\2MK\3\2"+
		"\2\2NO\b\3\1\2O\5\3\2\2\2PQ\7\3\2\2QR\7M\2\2RW\7B\2\2ST\7M\2\2TV\b\4\1"+
		"\2US\3\2\2\2VY\3\2\2\2WU\3\2\2\2WX\3\2\2\2XZ\3\2\2\2YW\3\2\2\2Z[\7C\2"+
		"\2[a\7B\2\2\\]\5\b\5\2]^\b\4\1\2^`\3\2\2\2_\\\3\2\2\2`c\3\2\2\2a_\3\2"+
		"\2\2ab\3\2\2\2bd\3\2\2\2ca\3\2\2\2de\7C\2\2ef\7\5\2\2fg\b\4\1\2g\7\3\2"+
		"\2\2hi\5\n\6\2ij\b\5\1\2j{\3\2\2\2kl\5\f\7\2lm\b\5\1\2m{\3\2\2\2no\5 "+
		"\21\2op\b\5\1\2p{\3\2\2\2qr\5\24\13\2rs\b\5\1\2s{\3\2\2\2tu\5\20\t\2u"+
		"v\b\5\1\2v{\3\2\2\2wx\5\16\b\2xy\b\5\1\2y{\3\2\2\2zh\3\2\2\2zk\3\2\2\2"+
		"zn\3\2\2\2zq\3\2\2\2zt\3\2\2\2zw\3\2\2\2{\t\3\2\2\2|}\7\4\2\2}\u0084\7"+
		"M\2\2~\177\5\66\34\2\177\u0080\b\6\1\2\u0080\u0085\3\2\2\2\u0081\u0082"+
		"\5\"\22\2\u0082\u0083\b\6\1\2\u0083\u0085\3\2\2\2\u0084~\3\2\2\2\u0084"+
		"\u0081\3\2\2\2\u0085\u0087\3\2\2\2\u0086\u0088\7F\2\2\u0087\u0086\3\2"+
		"\2\2\u0087\u0088\3\2\2\2\u0088\13\3\2\2\2\u0089\u008a\7\32\2\2\u008a\u008b"+
		"\7M\2\2\u008b\u008c\7A\2\2\u008c\u008e\5\"\22\2\u008d\u008f\7F\2\2\u008e"+
		"\u008d\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0091\b\7"+
		"\1\2\u0091\r\3\2\2\2\u0092\u00a4\7M\2\2\u0093\u0099\7B\2\2\u0094\u0095"+
		"\5\"\22\2\u0095\u0096\b\b\1\2\u0096\u0098\3\2\2\2\u0097\u0094\3\2\2\2"+
		"\u0098\u009b\3\2\2\2\u0099\u0097\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u009c"+
		"\3\2\2\2\u009b\u0099\3\2\2\2\u009c\u00a5\7C\2\2\u009d\u009e\5\"\22\2\u009e"+
		"\u009f\b\b\1\2\u009f\u00a1\3\2\2\2\u00a0\u009d\3\2\2\2\u00a1\u00a2\3\2"+
		"\2\2\u00a2\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a5\3\2\2\2\u00a4"+
		"\u0093\3\2\2\2\u00a4\u00a0\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a6\3\2"+
		"\2\2\u00a6\u00a7\b\b\1\2\u00a7\17\3\2\2\2\u00a8\u00a9\7\6\2\2\u00a9\u00af"+
		"\7B\2\2\u00aa\u00ab\5\b\5\2\u00ab\u00ac\b\t\1\2\u00ac\u00ae\3\2\2\2\u00ad"+
		"\u00aa\3\2\2\2\u00ae\u00b1\3\2\2\2\u00af\u00ad\3\2\2\2\u00af\u00b0\3\2"+
		"\2\2\u00b0\u00b2\3\2\2\2\u00b1\u00af\3\2\2\2\u00b2\u00b3\7C\2\2\u00b3"+
		"\u00b4\b\t\1\2\u00b4\21\3\2\2\2\u00b5\u00b6\7\7\2\2\u00b6\u00b7\5\"\22"+
		"\2\u00b7\u00bd\7B\2\2\u00b8\u00b9\5\b\5\2\u00b9\u00ba\b\n\1\2\u00ba\u00bc"+
		"\3\2\2\2\u00bb\u00b8\3\2\2\2\u00bc\u00bf\3\2\2\2\u00bd\u00bb\3\2\2\2\u00bd"+
		"\u00be\3\2\2\2\u00be\u00c0\3\2\2\2\u00bf\u00bd\3\2\2\2\u00c0\u00c1\7C"+
		"\2\2\u00c1\u00c2\b\n\1\2\u00c2\23\3\2\2\2\u00c3\u00c4\5\26\f\2\u00c4\u00c5"+
		"\b\13\1\2\u00c5\u00d6\3\2\2\2\u00c6\u00c7\5\30\r\2\u00c7\u00c8\b\13\1"+
		"\2\u00c8\u00d6\3\2\2\2\u00c9\u00ca\5\32\16\2\u00ca\u00cb\b\13\1\2\u00cb"+
		"\u00d6\3\2\2\2\u00cc\u00cd\5\34\17\2\u00cd\u00ce\b\13\1\2\u00ce\u00d6"+
		"\3\2\2\2\u00cf\u00d0\5\36\20\2\u00d0\u00d1\b\13\1\2\u00d1\u00d6\3\2\2"+
		"\2\u00d2\u00d3\5\22\n\2\u00d3\u00d4\b\13\1\2\u00d4\u00d6\3\2\2\2\u00d5"+
		"\u00c3\3\2\2\2\u00d5\u00c6\3\2\2\2\u00d5\u00c9\3\2\2\2\u00d5\u00cc\3\2"+
		"\2\2\u00d5\u00cf\3\2\2\2\u00d5\u00d2\3\2\2\2\u00d6\25\3\2\2\2\u00d7\u00d8"+
		"\7\27\2\2\u00d8\u00d9\7D\2\2\u00d9\u00da\5\"\22\2\u00da\u00db\7E\2\2\u00db"+
		"\u00e1\7B\2\2\u00dc\u00dd\5\b\5\2\u00dd\u00de\b\f\1\2\u00de\u00e0\3\2"+
		"\2\2\u00df\u00dc\3\2\2\2\u00e0\u00e3\3\2\2\2\u00e1\u00df\3\2\2\2\u00e1"+
		"\u00e2\3\2\2\2\u00e2\u00e4\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e4\u00ef\7C"+
		"\2\2\u00e5\u00eb\7B\2\2\u00e6\u00e7\5\b\5\2\u00e7\u00e8\b\f\1\2\u00e8"+
		"\u00ea\3\2\2\2\u00e9\u00e6\3\2\2\2\u00ea\u00ed\3\2\2\2\u00eb\u00e9\3\2"+
		"\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ee\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ee"+
		"\u00f0\7C\2\2\u00ef\u00e5\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\u00f1\3\2"+
		"\2\2\u00f1\u00f2\b\f\1\2\u00f2\27\3\2\2\2\u00f3\u00f4\7\4\2\2\u00f4\u00f5"+
		"\7H\2\2\u00f5\u00f6\7\30\2\2\u00f6\u00fc\7B\2\2\u00f7\u00f8\5\b\5\2\u00f8"+
		"\u00f9\b\r\1\2\u00f9\u00fb\3\2\2\2\u00fa\u00f7\3\2\2\2\u00fb\u00fe\3\2"+
		"\2\2\u00fc\u00fa\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fd\u00ff\3\2\2\2\u00fe"+
		"\u00fc\3\2\2\2\u00ff\u0100\7C\2\2\u0100\u0101\7D\2\2\u0101\u0102\5\"\22"+
		"\2\u0102\u0103\7E\2\2\u0103\u0104\b\r\1\2\u0104\31\3\2\2\2\u0105\u0106"+
		"\7\30\2\2\u0106\u0107\7D\2\2\u0107\u0108\5\"\22\2\u0108\u0109\7E\2\2\u0109"+
		"\u010f\7B\2\2\u010a\u010b\5\b\5\2\u010b\u010c\b\16\1\2\u010c\u010e\3\2"+
		"\2\2\u010d\u010a\3\2\2\2\u010e\u0111\3\2\2\2\u010f\u010d\3\2\2\2\u010f"+
		"\u0110\3\2\2\2\u0110\u0112\3\2\2\2\u0111\u010f\3\2\2\2\u0112\u0113\7C"+
		"\2\2\u0113\u0114\b\16\1\2\u0114\33\3\2\2\2\u0115\u0116\7\4\2\2\u0116\u0117"+
		"\7H\2\2\u0117\u0118\7\31\2\2\u0118\u011e\7B\2\2\u0119\u011a\5\b\5\2\u011a"+
		"\u011b\b\17\1\2\u011b\u011d\3\2\2\2\u011c\u0119\3\2\2\2\u011d\u0120\3"+
		"\2\2\2\u011e\u011c\3\2\2\2\u011e\u011f\3\2\2\2\u011f\u0121\3\2\2\2\u0120"+
		"\u011e\3\2\2\2\u0121\u0122\7C\2\2\u0122\u0123\7D\2\2\u0123\u0124\5\"\22"+
		"\2\u0124\u0125\7E\2\2\u0125\u0126\b\17\1\2\u0126\35\3\2\2\2\u0127\u0128"+
		"\7\31\2\2\u0128\u0129\7D\2\2\u0129\u012a\5\"\22\2\u012a\u012b\7E\2\2\u012b"+
		"\u0131\7B\2\2\u012c\u012d\5\b\5\2\u012d\u012e\b\20\1\2\u012e\u0130\3\2"+
		"\2\2\u012f\u012c\3\2\2\2\u0130\u0133\3\2\2\2\u0131\u012f\3\2\2\2\u0131"+
		"\u0132\3\2\2\2\u0132\u0134\3\2\2\2\u0133\u0131\3\2\2\2\u0134\u0135\7C"+
		"\2\2\u0135\u0136\b\20\1\2\u0136\37\3\2\2\2\u0137\u0138\7\33\2\2\u0138"+
		"\u0139\5\"\22\2\u0139\u013a\b\21\1\2\u013a\u0194\3\2\2\2\u013b\u013c\7"+
		"\34\2\2\u013c\u013d\5\"\22\2\u013d\u013e\b\21\1\2\u013e\u0194\3\2\2\2"+
		"\u013f\u0140\7\35\2\2\u0140\u0141\5\"\22\2\u0141\u0142\b\21\1\2\u0142"+
		"\u0194\3\2\2\2\u0143\u0144\7\36\2\2\u0144\u0145\5\"\22\2\u0145\u0146\b"+
		"\21\1\2\u0146\u0194\3\2\2\2\u0147\u0148\7\37\2\2\u0148\u0149\5\"\22\2"+
		"\u0149\u014a\b\21\1\2\u014a\u0194\3\2\2\2\u014b\u014c\7 \2\2\u014c\u014d"+
		"\5\"\22\2\u014d\u014e\b\21\1\2\u014e\u0194\3\2\2\2\u014f\u0150\7!\2\2"+
		"\u0150\u0151\5\"\22\2\u0151\u0152\b\21\1\2\u0152\u0194\3\2\2\2\u0153\u0154"+
		"\7\"\2\2\u0154\u0155\5\"\22\2\u0155\u0156\b\21\1\2\u0156\u0194\3\2\2\2"+
		"\u0157\u0158\7#\2\2\u0158\u0159\5\"\22\2\u0159\u015a\b\21\1\2\u015a\u0194"+
		"\3\2\2\2\u015b\u015c\7$\2\2\u015c\u0194\b\21\1\2\u015d\u015e\7%\2\2\u015e"+
		"\u0194\b\21\1\2\u015f\u0160\7\n\2\2\u0160\u0161\7B\2\2\u0161\u0162\5\""+
		"\22\2\u0162\u0163\5\"\22\2\u0163\u0164\7C\2\2\u0164\u0165\b\21\1\2\u0165"+
		"\u0194\3\2\2\2\u0166\u0167\7\13\2\2\u0167\u0168\5\"\22\2\u0168\u0169\5"+
		"\"\22\2\u0169\u016a\b\21\1\2\u016a\u0194\3\2\2\2\u016b\u016c\7\f\2\2\u016c"+
		"\u016d\5\"\22\2\u016d\u016e\b\21\1\2\u016e\u0194\3\2\2\2\u016f\u0170\7"+
		"\r\2\2\u0170\u0194\b\21\1\2\u0171\u0172\7\16\2\2\u0172\u0173\5\"\22\2"+
		"\u0173\u0174\b\21\1\2\u0174\u0194\3\2\2\2\u0175\u0176\7\17\2\2\u0176\u0177"+
		"\5\"\22\2\u0177\u0178\b\21\1\2\u0178\u0194\3\2\2\2\u0179\u017a\7\20\2"+
		"\2\u017a\u0194\b\21\1\2\u017b\u017c\7\21\2\2\u017c\u0194\b\21\1\2\u017d"+
		"\u017e\7\22\2\2\u017e\u0194\b\21\1\2\u017f\u0180\7\23\2\2\u0180\u0194"+
		"\b\21\1\2\u0181\u0182\7\24\2\2\u0182\u0194\b\21\1\2\u0183\u0184\7\25\2"+
		"\2\u0184\u0185\5\"\22\2\u0185\u0186\b\21\1\2\u0186\u0194\3\2\2\2\u0187"+
		"\u0188\7\26\2\2\u0188\u0189\7B\2\2\u0189\u018a\7M\2\2\u018a\u018b\7C\2"+
		"\2\u018b\u0194\b\21\1\2\u018c\u018d\7\26\2\2\u018d\u018e\7B\2\2\u018e"+
		"\u018f\7M\2\2\u018f\u0190\5\"\22\2\u0190\u0191\7C\2\2\u0191\u0192\b\21"+
		"\1\2\u0192\u0194\3\2\2\2\u0193\u0137\3\2\2\2\u0193\u013b\3\2\2\2\u0193"+
		"\u013f\3\2\2\2\u0193\u0143\3\2\2\2\u0193\u0147\3\2\2\2\u0193\u014b\3\2"+
		"\2\2\u0193\u014f\3\2\2\2\u0193\u0153\3\2\2\2\u0193\u0157\3\2\2\2\u0193"+
		"\u015b\3\2\2\2\u0193\u015d\3\2\2\2\u0193\u015f\3\2\2\2\u0193\u0166\3\2"+
		"\2\2\u0193\u016b\3\2\2\2\u0193\u016f\3\2\2\2\u0193\u0171\3\2\2\2\u0193"+
		"\u0175\3\2\2\2\u0193\u0179\3\2\2\2\u0193\u017b\3\2\2\2\u0193\u017d\3\2"+
		"\2\2\u0193\u017f\3\2\2\2\u0193\u0181\3\2\2\2\u0193\u0183\3\2\2\2\u0193"+
		"\u0187\3\2\2\2\u0193\u018c\3\2\2\2\u0194!\3\2\2\2\u0195\u0196\5$\23\2"+
		"\u0196\u019d\b\22\1\2\u0197\u0198\79\2\2\u0198\u0199\5$\23\2\u0199\u019a"+
		"\b\22\1\2\u019a\u019c\3\2\2\2\u019b\u0197\3\2\2\2\u019c\u019f\3\2\2\2"+
		"\u019d\u019b\3\2\2\2\u019d\u019e\3\2\2\2\u019e#\3\2\2\2\u019f\u019d\3"+
		"\2\2\2\u01a0\u01a1\5&\24\2\u01a1\u01a8\b\23\1\2\u01a2\u01a3\78\2\2\u01a3"+
		"\u01a4\5&\24\2\u01a4\u01a5\b\23\1\2\u01a5\u01a7\3\2\2\2\u01a6\u01a2\3"+
		"\2\2\2\u01a7\u01aa\3\2\2\2\u01a8\u01a6\3\2\2\2\u01a8\u01a9\3\2\2\2\u01a9"+
		"%\3\2\2\2\u01aa\u01a8\3\2\2\2\u01ab\u01ac\7:\2\2\u01ac\u01ad\5&\24\2\u01ad"+
		"\u01ae\b\24\1\2\u01ae\u01b3\3\2\2\2\u01af\u01b0\5(\25\2\u01b0\u01b1\b"+
		"\24\1\2\u01b1\u01b3\3\2\2\2\u01b2\u01ab\3\2\2\2\u01b2\u01af\3\2\2\2\u01b3"+
		"\'\3\2\2\2\u01b4\u01b5\5*\26\2\u01b5\u01d0\b\25\1\2\u01b6\u01b7\7;\2\2"+
		"\u01b7\u01b8\5*\26\2\u01b8\u01b9\b\25\1\2\u01b9\u01cf\3\2\2\2\u01ba\u01bb"+
		"\7<\2\2\u01bb\u01bc\5*\26\2\u01bc\u01bd\b\25\1\2\u01bd\u01cf\3\2\2\2\u01be"+
		"\u01bf\7=\2\2\u01bf\u01c0\5*\26\2\u01c0\u01c1\b\25\1\2\u01c1\u01cf\3\2"+
		"\2\2\u01c2\u01c3\7>\2\2\u01c3\u01c4\5*\26\2\u01c4\u01c5\b\25\1\2\u01c5"+
		"\u01cf\3\2\2\2\u01c6\u01c7\7?\2\2\u01c7\u01c8\5*\26\2\u01c8\u01c9\b\25"+
		"\1\2\u01c9\u01cf\3\2\2\2\u01ca\u01cb\7@\2\2\u01cb\u01cc\5*\26\2\u01cc"+
		"\u01cd\b\25\1\2\u01cd\u01cf\3\2\2\2\u01ce\u01b6\3\2\2\2\u01ce\u01ba\3"+
		"\2\2\2\u01ce\u01be\3\2\2\2\u01ce\u01c2\3\2\2\2\u01ce\u01c6\3\2\2\2\u01ce"+
		"\u01ca\3\2\2\2\u01cf\u01d2\3\2\2\2\u01d0\u01ce\3\2\2\2\u01d0\u01d1\3\2"+
		"\2\2\u01d1)\3\2\2\2\u01d2\u01d0\3\2\2\2\u01d3\u01d4\5,\27\2\u01d4\u01df"+
		"\b\26\1\2\u01d5\u01d6\7\63\2\2\u01d6\u01d7\5,\27\2\u01d7\u01d8\b\26\1"+
		"\2\u01d8\u01de\3\2\2\2\u01d9\u01da\7\64\2\2\u01da\u01db\5,\27\2\u01db"+
		"\u01dc\b\26\1\2\u01dc\u01de\3\2\2\2\u01dd\u01d5\3\2\2\2\u01dd\u01d9\3"+
		"\2\2\2\u01de\u01e1\3\2\2\2\u01df\u01dd\3\2\2\2\u01df\u01e0\3\2\2\2\u01e0"+
		"+\3\2\2\2\u01e1\u01df\3\2\2\2\u01e2\u01e3\5.\30\2\u01e3\u01ee\b\27\1\2"+
		"\u01e4\u01e5\7\65\2\2\u01e5\u01e6\5.\30\2\u01e6\u01e7\b\27\1\2\u01e7\u01ed"+
		"\3\2\2\2\u01e8\u01e9\7\66\2\2\u01e9\u01ea\5.\30\2\u01ea\u01eb\b\27\1\2"+
		"\u01eb\u01ed\3\2\2\2\u01ec\u01e4\3\2\2\2\u01ec\u01e8\3\2\2\2\u01ed\u01f0"+
		"\3\2\2\2\u01ee\u01ec\3\2\2\2\u01ee\u01ef\3\2\2\2\u01ef-\3\2\2\2\u01f0"+
		"\u01ee\3\2\2\2\u01f1\u01f2\5\60\31\2\u01f2\u01f9\b\30\1\2\u01f3\u01f4"+
		"\7\67\2\2\u01f4\u01f5\5\60\31\2\u01f5\u01f6\b\30\1\2\u01f6\u01f8\3\2\2"+
		"\2\u01f7\u01f3\3\2\2\2\u01f8\u01fb\3\2\2\2\u01f9\u01f7\3\2\2\2\u01f9\u01fa"+
		"\3\2\2\2\u01fa/\3\2\2\2\u01fb\u01f9\3\2\2\2\u01fc\u01fd\5\62\32\2\u01fd"+
		"\u01fe\b\31\1\2\u01fe\u0203\3\2\2\2\u01ff\u0200\5\64\33\2\u0200\u0201"+
		"\b\31\1\2\u0201\u0203\3\2\2\2\u0202\u01fc\3\2\2\2\u0202\u01ff\3\2\2\2"+
		"\u0203\61\3\2\2\2\u0204\u0205\7(\2\2\u0205\u0206\5\"\22\2\u0206\u0207"+
		"\5\"\22\2\u0207\u0208\b\32\1\2\u0208\u0252\3\2\2\2\u0209\u020a\7)\2\2"+
		"\u020a\u020b\7D\2\2\u020b\u020c\5\"\22\2\u020c\u020d\7E\2\2\u020d\u020e"+
		"\7D\2\2\u020e\u020f\5\"\22\2\u020f\u0210\7E\2\2\u0210\u0211\b\32\1\2\u0211"+
		"\u0252\3\2\2\2\u0212\u0213\7*\2\2\u0213\u0214\7D\2\2\u0214\u0215\5\"\22"+
		"\2\u0215\u0216\7E\2\2\u0216\u0217\7D\2\2\u0217\u0218\5\"\22\2\u0218\u0219"+
		"\7E\2\2\u0219\u021a\b\32\1\2\u021a\u0252\3\2\2\2\u021b\u021c\7+\2\2\u021c"+
		"\u021d\5\"\22\2\u021d\u021e\5\"\22\2\u021e\u021f\b\32\1\2\u021f\u0252"+
		"\3\2\2\2\u0220\u0221\7,\2\2\u0221\u0222\5\"\22\2\u0222\u0223\5\"\22\2"+
		"\u0223\u0224\b\32\1\2\u0224\u0252\3\2\2\2\u0225\u0226\7.\2\2\u0226\u0227"+
		"\5\"\22\2\u0227\u0228\b\32\1\2\u0228\u0252\3\2\2\2\u0229\u022a\7/\2\2"+
		"\u022a\u022e\5\"\22\2\u022b\u022d\5\"\22\2\u022c\u022b\3\2\2\2\u022d\u0230"+
		"\3\2\2\2\u022e\u022c\3\2\2\2\u022e\u022f\3\2\2\2\u022f\u0231\3\2\2\2\u0230"+
		"\u022e\3\2\2\2\u0231\u0232\b\32\1\2\u0232\u0252\3\2\2\2\u0233\u0234\7"+
		"\60\2\2\u0234\u0235\5\"\22\2\u0235\u0236\5\"\22\2\u0236\u0237\b\32\1\2"+
		"\u0237\u0252\3\2\2\2\u0238\u0239\7\61\2\2\u0239\u023a\5\"\22\2\u023a\u023b"+
		"\5\"\22\2\u023b\u023c\b\32\1\2\u023c\u0252\3\2\2\2\u023d\u023e\7\62\2"+
		"\2\u023e\u0242\5\"\22\2\u023f\u0241\5\"\22\2\u0240\u023f\3\2\2\2\u0241"+
		"\u0244\3\2\2\2\u0242\u0240\3\2\2\2\u0242\u0243\3\2\2\2\u0243\u0245\3\2"+
		"\2\2\u0244\u0242\3\2\2\2\u0245\u0246\b\32\1\2\u0246\u0252\3\2\2\2\u0247"+
		"\u0248\7-\2\2\u0248\u024c\5\"\22\2\u0249\u024b\5\"\22\2\u024a\u0249\3"+
		"\2\2\2\u024b\u024e\3\2\2\2\u024c\u024a\3\2\2\2\u024c\u024d\3\2\2\2\u024d"+
		"\u024f\3\2\2\2\u024e\u024c\3\2\2\2\u024f\u0250\b\32\1\2\u0250\u0252\3"+
		"\2\2\2\u0251\u0204\3\2\2\2\u0251\u0209\3\2\2\2\u0251\u0212\3\2\2\2\u0251"+
		"\u021b\3\2\2\2\u0251\u0220\3\2\2\2\u0251\u0225\3\2\2\2\u0251\u0229\3\2"+
		"\2\2\u0251\u0233\3\2\2\2\u0251\u0238\3\2\2\2\u0251\u023d\3\2\2\2\u0251"+
		"\u0247\3\2\2\2\u0252\63\3\2\2\2\u0253\u0254\7K\2\2\u0254\u0261\b\33\1"+
		"\2\u0255\u0256\7I\2\2\u0256\u0261\b\33\1\2\u0257\u0258\7M\2\2\u0258\u0261"+
		"\b\33\1\2\u0259\u025a\7L\2\2\u025a\u0261\b\33\1\2\u025b\u025c\7D\2\2\u025c"+
		"\u025d\5\"\22\2\u025d\u025e\7E\2\2\u025e\u025f\b\33\1\2\u025f\u0261\3"+
		"\2\2\2\u0260\u0253\3\2\2\2\u0260\u0255\3\2\2\2\u0260\u0257\3\2\2\2\u0260"+
		"\u0259\3\2\2\2\u0260\u025b\3\2\2\2\u0261\65\3\2\2\2\u0262\u0263\7K\2\2"+
		"\u0263\u0269\b\34\1\2\u0264\u0265\7I\2\2\u0265\u0269\b\34\1\2\u0266\u0267"+
		"\7L\2\2\u0267\u0269\b\34\1\2\u0268\u0262\3\2\2\2\u0268\u0264\3\2\2\2\u0268"+
		"\u0266\3\2\2\2\u0269\67\3\2\2\2\u026a\u026b\7N\2\2\u026b\u026c\b\35\1"+
		"\2\u026c9\3\2\2\2\u026d\u026e\7Q\2\2\u026e;\3\2\2\2*=IKWaz\u0084\u0087"+
		"\u008e\u0099\u00a2\u00a4\u00af\u00bd\u00d5\u00e1\u00eb\u00ef\u00fc\u010f"+
		"\u011e\u0131\u0193\u019d\u01a8\u01b2\u01ce\u01d0\u01dd\u01df\u01ec\u01ee"+
		"\u01f9\u0202\u022e\u0242\u024c\u0251\u0260\u0268";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}