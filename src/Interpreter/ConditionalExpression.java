package Interpreter;

import Elements.Type;
import Elements.Value;
import Elements.ValueLibrary;
import ErrorManager.Error;
import Utils.Result;
import Utils.TriValue;

import java.util.ArrayList;


public class ConditionalExpression extends Expression {
    ExpressionSeries expressionSeries;
    Expression condition;

    public ConditionalExpression(ExpressionSeries expressionSeries, Expression condition) {
        this.expressionSeries = expressionSeries;
        this.condition = condition;
    }

    public Expression getCondition() {
        return condition;
    }

    public ExpressionSeries getBody() {
        return expressionSeries;
    }

    @Override
    public ExpressionResult evaluate(State situatedState) {
        ExpressionResult conditionResult = condition.evaluate(situatedState);
        situatedState = conditionResult.resultingState;

        if (conditionResult.resultingValue == ValueLibrary.trueValue) {
            return expressionSeries.evaluate(situatedState);
        }

        return conditionResult;
    }

    @Override
    public ValidationContext validate(ValidationContext context) {
        context = condition.validate(context);

        TriValue matchesType = condition.matchesType(ValueLibrary.boolType, context);

        if (matchesType == TriValue.FALSE) {
            context.addError(
                    new Error(Error.ErrorType.INTERPRETER_ERROR, "Condition of type "+condition.getType(context)+" doesn't match Bool", true)
            );
        } else if (matchesType == TriValue.UNKNOWN) {
            context.addError(
                    new Error(Error.ErrorType.INTERPRETER_ERROR, "Can't verify that condition of type "+condition.getType(context)+" matches Bool", false)
            );
        }

        context.addScope();
        for (Expression innerExpression : expressionSeries.getContainedExpressions()) {
            context = innerExpression.validate(context);
        }
        context.killScope();

        return context;
    }

    @Override
    public Type getType(ValidationContext context) {
        return ValueLibrary.boolType;
    }

    @Override
    public StaticReductionContext initializeStaticValues(StaticReductionContext context) {
        for (Expression innerExpression : expressionSeries.getContainedExpressions()) {
            context = innerExpression.initializeStaticValues(context);
        }
        staticValue = Result.ok(ValueLibrary.trueValue);

        return context;
    }


    @Override
    public String toString() {
        return "if "+condition+" {\n"+expressionSeries+"}";
    }

    /*

    @Override
    public ArrayList<Expression> getContainedExpressions() {
        return new ArrayList<>() {{
            add(expressionSeries);
        }};
    }

     */
}
