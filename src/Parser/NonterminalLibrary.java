package Parser;

import ErrorManager.Error;
import Lexer.TokenLibrary;

public class NonterminalLibrary {
    public static Nonterminal file = new Nonterminal("file") {
        @Override
        public void parse(Parser parser) {

            while (!parser.eof()) {
                parser.eat(TokenLibrary.whitespace);

                //if (parser.at(TokenLibrary.let)) {
                //    letStatement.apply(parser);
                //} else {
                //    parser.advanceWithError(new Error(Error.ErrorType.PARSER_ERROR, "Expected a statement", false));
                //}
                expression.apply(parser);
            }

        }
    };

    static Nonterminal letStatement = new Nonterminal("let") {
        @Override
        public void parse(Parser parser) {

            parser.expect(TokenLibrary.let);
            parser.eat(TokenLibrary.whitespace);
            parser.expect(TokenLibrary.identifier);
            parser.eat(TokenLibrary.whitespace);
            parser.expect(TokenLibrary.equals);
            parser.eat(TokenLibrary.whitespace);
            expression.apply(parser);

        }
    };
    public static Nonterminal returnStatement = new Nonterminal("return") {
        @Override
        public void parse(Parser parser) {

            parser.expect(TokenLibrary.returnToken);
            parser.eat(TokenLibrary.whitespace);
            expression.apply(parser);
            parser.eat(TokenLibrary.whitespace);

        }
    };

    public static Nonterminal lambda = new Nonterminal("lambda") {
        @Override
        public void parse(Parser parser) {

            parser.expect(TokenLibrary.lBrace);

            parameterList.apply(parser);

            parser.eat(TokenLibrary.whitespace);

            parser.expect(TokenLibrary.arrow);

            parser.eat(TokenLibrary.whitespace);

            do {
                expression.apply(parser);
                parser.eat(TokenLibrary.whitespace);
            } while (!parser.at(TokenLibrary.rBrace));

            parser.expect(TokenLibrary.rBrace);
            // Expression lambdas and statement lambdas are identical in the parse: expression lambdas contain 1 expression and statement lambdas contain multiple.

        }
    };

    public static Nonterminal parameterList = new Nonterminal("parameter list") {
        @Override
        public void parse (Parser parser) {

            do {
                parser.eat(TokenLibrary.whitespace);
                expression.apply(parser);
                parser.eat(TokenLibrary.whitespace);
                parser.expect(TokenLibrary.identifier);
            } while (parser.eat(TokenLibrary.comma));

        }
    };

    public static Nonterminal argumentList = new Nonterminal("argument list") {
        @Override
        public void parse(Parser parser) {

            do {
                parser.eat(TokenLibrary.whitespace);
                expression.apply(parser);
            } while (parser.eat(TokenLibrary.comma));

        }
    };

    // Basic expressions: including expressions in parentheses, literals, identifiers, lambdas
    public static Nonterminal delimitedExpression = new Nonterminal("delimitedExpression") {
        @Override
        public void parse(Parser parser) {
            if (parser.at(TokenLibrary.lParen)) {
                parser.eat(TokenLibrary.lParen);
                expression.apply(parser);
                parser.expect(TokenLibrary.rParen);
            } else if (parser.at(TokenLibrary.intToken)) {
                parser.eat(TokenLibrary.intToken);
            } else if (parser.at(TokenLibrary.stringLiteral)) {
                parser.eat(TokenLibrary.stringLiteral);
            } else if (parser.at(TokenLibrary.identifier)) {
                parser.eat(TokenLibrary.identifier);
            } else if (parser.at(TokenLibrary.lBrace)) {
                lambda.apply(parser);
            } else if (parser.at(TokenLibrary.let)) {
                letStatement.apply(parser);
            } else if (parser.at(TokenLibrary.returnToken)) {
                returnStatement.apply(parser);
            }
        }
    };


    // Argument list in parentheses constituting a function call
    public static Nonterminal expressionCall = new Nonterminal("expression call") {
        @Override
        public void parse(Parser parser) {
            parser.expect(TokenLibrary.lParen);

            argumentList.apply(parser);

            parser.expect(TokenLibrary.rParen);
        }
    };

    // Complex expressions, including function calls
    public static Nonterminal expression = new Nonterminal("expression") {
        @Override
        public void parse(Parser parser) {
            MarkClosed leftSide = delimitedExpression.apply(parser);

            // Parses function calls
            while (parser.at(TokenLibrary.lParen)) {
                expressionCall.apply(parser, leftSide);
            }

        }
    };
}
