import Interpreter.InterpretationSession;
import Testing.Test;
import Testing.Test.TestFunction;
import Testing.TestSuite;


public class Main {
    public static void main(String[] args) {

        // InterpretationSession newSession = new InterpretationSession("let func = a(10)");
        // InterpretationSession newSession = new InterpretationSession("let func = res(10)(30, \"abcs\", bob)(abced, a(c))");
        // newSession.runSession();

        TestSuite testSuite = new TestSuite(
                "Testing",

                new TestSuite(
                        "Lexer",
                        new Test(
                            "let a = 5 \nlet b = a \nlet c = a(1,2,\"hello\")\n let d = wep(a) let e = a(b(c(d(e))))",
                            "{ lexeme = `let` tokenType = let }{ lexeme = ` ` tokenType = whitespace }{ lexeme = `a` tokenType = identifier }{ lexeme = ` ` tokenType = whitespace }{ lexeme = `=` tokenType = = }{ lexeme = ` ` tokenType = whitespace }{ lexeme = `5` tokenType = int }{ lexeme = ` \\n` tokenType = whitespace }{ lexeme = `let` tokenType = let }{ lexeme = ` ` tokenType = whitespace }{ lexeme = `b` tokenType = identifier }{ lexeme = ` ` tokenType = whitespace }{ lexeme = `=` tokenType = = }{ lexeme = ` ` tokenType = whitespace }{ lexeme = `a` tokenType = identifier }{ lexeme = ` \\n` tokenType = whitespace }{ lexeme = `let` tokenType = let }{ lexeme = ` ` tokenType = whitespace }{ lexeme = `c` tokenType = identifier }{ lexeme = ` ` tokenType = whitespace }{ lexeme = `=` tokenType = = }{ lexeme = ` ` tokenType = whitespace }{ lexeme = `a` tokenType = identifier }{ lexeme = `(` tokenType = ( }{ lexeme = `1` tokenType = int }{ lexeme = `,` tokenType = , }{ lexeme = `2` tokenType = int }{ lexeme = `,` tokenType = , }{ lexeme = `\"hello\"` tokenType = String }{ lexeme = `)` tokenType = ) }{ lexeme = `\\n ` tokenType = whitespace }{ lexeme = `let` tokenType = let }{ lexeme = ` ` tokenType = whitespace }{ lexeme = `d` tokenType = identifier }{ lexeme = ` ` tokenType = whitespace }{ lexeme = `=` tokenType = = }{ lexeme = ` ` tokenType = whitespace }{ lexeme = `wep` tokenType = identifier }{ lexeme = `(` tokenType = ( }{ lexeme = `a` tokenType = identifier }{ lexeme = `)` tokenType = ) }{ lexeme = ` ` tokenType = whitespace }{ lexeme = `let` tokenType = let }{ lexeme = ` ` tokenType = whitespace }{ lexeme = `e` tokenType = identifier }{ lexeme = ` ` tokenType = whitespace }{ lexeme = `=` tokenType = = }{ lexeme = ` ` tokenType = whitespace }{ lexeme = `a` tokenType = identifier }{ lexeme = `(` tokenType = ( }{ lexeme = `b` tokenType = identifier }{ lexeme = `(` tokenType = ( }{ lexeme = `c` tokenType = identifier }{ lexeme = `(` tokenType = ( }{ lexeme = `d` tokenType = identifier }{ lexeme = `(` tokenType = ( }{ lexeme = `e` tokenType = identifier }{ lexeme = `)` tokenType = ) }{ lexeme = `)` tokenType = ) }{ lexeme = `)` tokenType = ) }{ lexeme = `)` tokenType = ) }\n",
                            TestFunction.lexer
                        ),
                        new Test(
                            "let",
                            "{ lexeme = `let` tokenType = let }\n",
                            TestFunction.lexer
                        )
                ),
                new TestSuite(
                        "Parser",
                        new Test(
                                "let a = 5",
                                "Nonterminal(TreeKind(file), [Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(delimitedExpression), [Nonterminal(TreeKind(let), [Terminal({ lexeme = `let` tokenType = let }), Terminal({ lexeme = ` ` tokenType = whitespace }), Terminal({ lexeme = `a` tokenType = identifier }), Terminal({ lexeme = ` ` tokenType = whitespace }), Terminal({ lexeme = `=` tokenType = = }), Terminal({ lexeme = ` ` tokenType = whitespace }), Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(delimitedExpression), [Terminal({ lexeme = `5` tokenType = int })])])])])])])\n",
                                TestFunction.parser
                        ),
                        new Test(
                                "let a = 5 \nlet b = a \nlet c = a(1,2,\"hello\")\n let d = wep(a) let e = a(b(c(d(e))))",
                                "Nonterminal(TreeKind(file), [Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(delimitedExpression), [Nonterminal(TreeKind(let), [Terminal({ lexeme = `let` tokenType = let }), Terminal({ lexeme = ` ` tokenType = whitespace }), Terminal({ lexeme = `a` tokenType = identifier }), Terminal({ lexeme = ` ` tokenType = whitespace }), Terminal({ lexeme = `=` tokenType = = }), Terminal({ lexeme = ` ` tokenType = whitespace }), Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(delimitedExpression), [Terminal({ lexeme = `5` tokenType = int })])])])])]), Terminal({ lexeme = ` \\n` tokenType = whitespace }), Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(delimitedExpression), [Nonterminal(TreeKind(let), [Terminal({ lexeme = `let` tokenType = let }), Terminal({ lexeme = ` ` tokenType = whitespace }), Terminal({ lexeme = `b` tokenType = identifier }), Terminal({ lexeme = ` ` tokenType = whitespace }), Terminal({ lexeme = `=` tokenType = = }), Terminal({ lexeme = ` ` tokenType = whitespace }), Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(delimitedExpression), [Terminal({ lexeme = `a` tokenType = identifier })])])])])]), Terminal({ lexeme = ` \\n` tokenType = whitespace }), Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(delimitedExpression), [Nonterminal(TreeKind(let), [Terminal({ lexeme = `let` tokenType = let }), Terminal({ lexeme = ` ` tokenType = whitespace }), Terminal({ lexeme = `c` tokenType = identifier }), Terminal({ lexeme = ` ` tokenType = whitespace }), Terminal({ lexeme = `=` tokenType = = }), Terminal({ lexeme = ` ` tokenType = whitespace }), Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(expression call), [Nonterminal(TreeKind(delimitedExpression), [Terminal({ lexeme = `a` tokenType = identifier })]), Terminal({ lexeme = `(` tokenType = ( }), Nonterminal(TreeKind(argument list), [Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(delimitedExpression), [Terminal({ lexeme = `1` tokenType = int })])]), Terminal({ lexeme = `,` tokenType = , }), Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(delimitedExpression), [Terminal({ lexeme = `2` tokenType = int })])]), Terminal({ lexeme = `,` tokenType = , }), Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(delimitedExpression), [Terminal({ lexeme = `\"hello\"` tokenType = String })])])]), Terminal({ lexeme = `)` tokenType = ) })])])])])]), Terminal({ lexeme = `\\n ` tokenType = whitespace }), Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(delimitedExpression), [Nonterminal(TreeKind(let), [Terminal({ lexeme = `let` tokenType = let }), Terminal({ lexeme = ` ` tokenType = whitespace }), Terminal({ lexeme = `d` tokenType = identifier }), Terminal({ lexeme = ` ` tokenType = whitespace }), Terminal({ lexeme = `=` tokenType = = }), Terminal({ lexeme = ` ` tokenType = whitespace }), Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(expression call), [Nonterminal(TreeKind(delimitedExpression), [Terminal({ lexeme = `wep` tokenType = identifier })]), Terminal({ lexeme = `(` tokenType = ( }), Nonterminal(TreeKind(argument list), [Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(delimitedExpression), [Terminal({ lexeme = `a` tokenType = identifier })])])]), Terminal({ lexeme = `)` tokenType = ) })])])])])]), Terminal({ lexeme = ` ` tokenType = whitespace }), Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(delimitedExpression), [Nonterminal(TreeKind(let), [Terminal({ lexeme = `let` tokenType = let }), Terminal({ lexeme = ` ` tokenType = whitespace }), Terminal({ lexeme = `e` tokenType = identifier }), Terminal({ lexeme = ` ` tokenType = whitespace }), Terminal({ lexeme = `=` tokenType = = }), Terminal({ lexeme = ` ` tokenType = whitespace }), Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(expression call), [Nonterminal(TreeKind(delimitedExpression), [Terminal({ lexeme = `a` tokenType = identifier })]), Terminal({ lexeme = `(` tokenType = ( }), Nonterminal(TreeKind(argument list), [Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(expression call), [Nonterminal(TreeKind(delimitedExpression), [Terminal({ lexeme = `b` tokenType = identifier })]), Terminal({ lexeme = `(` tokenType = ( }), Nonterminal(TreeKind(argument list), [Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(expression call), [Nonterminal(TreeKind(delimitedExpression), [Terminal({ lexeme = `c` tokenType = identifier })]), Terminal({ lexeme = `(` tokenType = ( }), Nonterminal(TreeKind(argument list), [Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(expression call), [Nonterminal(TreeKind(delimitedExpression), [Terminal({ lexeme = `d` tokenType = identifier })]), Terminal({ lexeme = `(` tokenType = ( }), Nonterminal(TreeKind(argument list), [Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(delimitedExpression), [Terminal({ lexeme = `e` tokenType = identifier })])])]), Terminal({ lexeme = `)` tokenType = ) })])])]), Terminal({ lexeme = `)` tokenType = ) })])])]), Terminal({ lexeme = `)` tokenType = ) })])])]), Terminal({ lexeme = `)` tokenType = ) })])])])])])])\n",
                                TestFunction.parser
                        ),
                        new Test(
                                "let func = {Int a -> a} let funcB = { Int b, Int c -> Int let ret = b(c)}",
                                "Nonterminal(TreeKind(file), [Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(delimitedExpression), [Nonterminal(TreeKind(let), [Terminal({ lexeme = `let` tokenType = let }), Terminal({ lexeme = ` ` tokenType = whitespace }), Terminal({ lexeme = `func` tokenType = identifier }), Terminal({ lexeme = ` ` tokenType = whitespace }), Terminal({ lexeme = `=` tokenType = = }), Terminal({ lexeme = ` ` tokenType = whitespace }), Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(delimitedExpression), [Nonterminal(TreeKind(lambda), [Terminal({ lexeme = `{` tokenType = { }), Nonterminal(TreeKind(parameter list), [Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(delimitedExpression), [Terminal({ lexeme = `Int` tokenType = identifier })])]), Terminal({ lexeme = ` ` tokenType = whitespace }), Terminal({ lexeme = `a` tokenType = identifier })]), Terminal({ lexeme = ` ` tokenType = whitespace }), Terminal({ lexeme = `->` tokenType = -> }), Terminal({ lexeme = ` ` tokenType = whitespace }), Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(delimitedExpression), [Terminal({ lexeme = `a` tokenType = identifier })])]), Terminal({ lexeme = `}` tokenType = } })])])])])])]), Terminal({ lexeme = ` ` tokenType = whitespace }), Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(delimitedExpression), [Nonterminal(TreeKind(let), [Terminal({ lexeme = `let` tokenType = let }), Terminal({ lexeme = ` ` tokenType = whitespace }), Terminal({ lexeme = `funcB` tokenType = identifier }), Terminal({ lexeme = ` ` tokenType = whitespace }), Terminal({ lexeme = `=` tokenType = = }), Terminal({ lexeme = ` ` tokenType = whitespace }), Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(delimitedExpression), [Nonterminal(TreeKind(lambda), [Terminal({ lexeme = `{` tokenType = { }), Nonterminal(TreeKind(parameter list), [Terminal({ lexeme = ` ` tokenType = whitespace }), Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(delimitedExpression), [Terminal({ lexeme = `Int` tokenType = identifier })])]), Terminal({ lexeme = ` ` tokenType = whitespace }), Terminal({ lexeme = `b` tokenType = identifier }), Terminal({ lexeme = `,` tokenType = , }), Terminal({ lexeme = ` ` tokenType = whitespace }), Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(delimitedExpression), [Terminal({ lexeme = `Int` tokenType = identifier })])]), Terminal({ lexeme = ` ` tokenType = whitespace }), Terminal({ lexeme = `c` tokenType = identifier })]), Terminal({ lexeme = ` ` tokenType = whitespace }), Terminal({ lexeme = `->` tokenType = -> }), Terminal({ lexeme = ` ` tokenType = whitespace }), Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(delimitedExpression), [Terminal({ lexeme = `Int` tokenType = identifier })])]), Terminal({ lexeme = ` ` tokenType = whitespace }), Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(delimitedExpression), [Nonterminal(TreeKind(let), [Terminal({ lexeme = `let` tokenType = let }), Terminal({ lexeme = ` ` tokenType = whitespace }), Terminal({ lexeme = `ret` tokenType = identifier }), Terminal({ lexeme = ` ` tokenType = whitespace }), Terminal({ lexeme = `=` tokenType = = }), Terminal({ lexeme = ` ` tokenType = whitespace }), Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(expression call), [Nonterminal(TreeKind(delimitedExpression), [Terminal({ lexeme = `b` tokenType = identifier })]), Terminal({ lexeme = `(` tokenType = ( }), Nonterminal(TreeKind(argument list), [Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(delimitedExpression), [Terminal({ lexeme = `c` tokenType = identifier })])])]), Terminal({ lexeme = `)` tokenType = ) })])])])])]), Terminal({ lexeme = `}` tokenType = } })])])])])])])])\n",
                                TestFunction.parser
                        ),
                        new Test(
                                "let a = {Int a -> return 123}",
                                "Nonterminal(TreeKind(file), [Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(delimitedExpression), [Nonterminal(TreeKind(let), [Terminal({ lexeme = `let` tokenType = let }), Terminal({ lexeme = ` ` tokenType = whitespace }), Terminal({ lexeme = `a` tokenType = identifier }), Terminal({ lexeme = ` ` tokenType = whitespace }), Terminal({ lexeme = `=` tokenType = = }), Terminal({ lexeme = ` ` tokenType = whitespace }), Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(delimitedExpression), [Nonterminal(TreeKind(lambda), [Terminal({ lexeme = `{` tokenType = { }), Nonterminal(TreeKind(parameter list), [Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(delimitedExpression), [Terminal({ lexeme = `Int` tokenType = identifier })])]), Terminal({ lexeme = ` ` tokenType = whitespace }), Terminal({ lexeme = `a` tokenType = identifier })]), Terminal({ lexeme = ` ` tokenType = whitespace }), Terminal({ lexeme = `->` tokenType = -> }), Terminal({ lexeme = ` ` tokenType = whitespace }), Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(delimitedExpression), [Nonterminal(TreeKind(return), [Terminal({ lexeme = `return` tokenType = return }), Terminal({ lexeme = ` ` tokenType = whitespace }), Nonterminal(TreeKind(expression), [Nonterminal(TreeKind(delimitedExpression), [Terminal({ lexeme = `123` tokenType = int })])])])])]), Terminal({ lexeme = `}` tokenType = } })])])])])])])])\n",
                                TestFunction.parser
                        )
                )
        );

        testSuite.getResults();


    }
}