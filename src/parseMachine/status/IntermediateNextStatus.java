package parseMachine.status;

import parseMachine.IStatus;
import parseMachine.ParseMachine;

/**
 * Created by 袁江超 on 2018/4/13.
 */
public class IntermediateNextStatus implements IStatus{
    @Override
    public IStatus Transform(ParseMachine.Action action) {
        switch (action){
            case CAREER_OPERATOR:
                return ParseMachine.intermediateStatus;
            default:
                return ParseMachine.normalStatus;
        }
    }

    @Override
    public ParseMachine.Type GetType() {
        return ParseMachine.Type.COMMENT;
    }
}
