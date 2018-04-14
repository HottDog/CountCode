package parseMachine.status;

import parseMachine.IStatus;
import parseMachine.ParseMachine;

/**
 * Created by 袁江超 on 2018/4/13.
 */
public class ConstCharNextStatus implements IStatus{
    @Override
    public IStatus Transform(ParseMachine.Action action) {
        return ParseMachine.constCharStatus;
    }

    @Override
    public ParseMachine.Type GetType() {
        return ParseMachine.Type.EFFECTIVE;
    }

    public String toString(){
        return "ConstCharNextStatus";
    }
}
