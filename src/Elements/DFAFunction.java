package Elements;

import ErrorManager.ErrorManager;
import Interpreter.Expression;
import Interpreter.State;
import Regularity.DFA;

public abstract class DFAFunction extends Function {
    // This function acts like a passthrough for a stored function but also adds the ability to assign a DFA

    Function wrappedFunction;

    public DFAFunction(Function wrappedFunction) {
        super();
        this.wrappedFunction = wrappedFunction;
    }

    @Override
    public Value apply(ErrorManager errorManager, State state, Value... values) {
        return wrappedFunction.apply(errorManager, state, values);
    }

    public abstract DFA getDFA(Value... inputs);
}
