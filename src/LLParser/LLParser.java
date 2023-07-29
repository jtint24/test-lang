package LLParser;


import ErrorManager.ErrorManager;
import Lexer.Symbol;
import Lexer.SymbolString;
import Lexer.Token;

import java.util.*;

import Lexer.Tokenizer;
import LLParser.EventLibrary.*;
import ErrorManager.Error;

public class LLParser {
    ArrayList<Symbol> symbols = new ArrayList<>();
    int pos = 0;
    int fuel = 255;
    ArrayList<Event> events = new ArrayList<>();
    ErrorManager errorManager;
    Tokenizer tokenizer;

     public LLParser(Tokenizer tokenizer, ErrorManager errorManager) {
         this.errorManager = errorManager;
         this.tokenizer = tokenizer;
     }

     public void setSymbols(List<Symbol> symbols) {
         this.symbols.clear();
         this.symbols.addAll(symbols);
     }
     MarkOpened open() {
         MarkOpened mark = new MarkOpened(this.events.size());
         this.events.add(new OpenEvent(TreeKind.invalid()));
         return mark;
     }

     MarkOpened openBefore(MarkClosed closer) {
        MarkOpened opener = new MarkOpened(closer.index);
        events.add(closer.index, new OpenEvent(TreeKind.invalid()));
        return opener;
     }

     void close(MarkOpened m, TreeKind kind) {
         this.events.set(m.index, new OpenEvent(kind));
         this.events.add(new CloseEvent());
     }

     void advance() {
         assert !this.eof();
         this.fuel = 25;
         this.events.add(new AdvanceEvent());
         this.pos++;
     }
     boolean eof() {
         return this.pos == this.symbols.size();
     }

     Token nth(int lookahead) {
         if (this.fuel == 0) {
             errorManager.logError(new Error(Error.ErrorType.PARSER_ERROR, "Parser is stuck!", true));
         }

         this.fuel --;
         Token retToken = this.symbols.get(this.pos+lookahead).getTokenType();
         return retToken;
     }

     boolean at(Token kind) {
         return this.nth(0) == kind;
     }

     boolean eat(Token kind) {
         if (this.at(kind)) {
             this.advance();
             return true;
         } else {
             return false;
         }
     }

     void expect(Token kind) {
         if (this.eat(kind)) {
             return;
         }
         errorManager.logError(new Error(Error.ErrorType.PARSER_ERROR, "Expected token of type "+kind+", got "+this.nth(0), true));
     }

     void advanceWithError(Error error) {
         MarkOpened m_opened = this.open();
         errorManager.logError(error);
         this.advance();
         this.close(m_opened, TreeKind.invalid());
     }

     public LLParseTreeNode buildTree() {
         Stack<LLParseTreeNode> stack = new Stack<>();

         Iterator<Symbol> symbolIterator = symbols.iterator();

         assert events.get(events.size()-1) instanceof CloseEvent;

         for (Event event : events) {
             if (event instanceof OpenEvent) {
                 stack.push(new LLNonterminalParseTreeNode(((OpenEvent) event).kind));
             } else if (event instanceof CloseEvent) {
                 LLParseTreeNode tree = stack.pop();
                 if (stack.isEmpty()) {
                     stack.push(tree);
                 } else {
                     ((LLNonterminalParseTreeNode) stack.peek()).addChild(tree);
                 }
             } else if (event instanceof AdvanceEvent) {
                 Symbol symbol = symbolIterator.next();
                 ((LLNonterminalParseTreeNode) stack.peek()).addChild(new LLTerminalParseTreeNode(symbol));
             }
         }
         assert stack.size() == 1;

         return stack.pop();
     }
}
