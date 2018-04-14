package parseMachine.status;

import parseMachine.IStatus;
import parseMachine.ParseMachine;

/**
 * Created by 袁江超 on 2018/4/13.
 */
public class StartStatus implements IStatus{
    @Override
    public IStatus Transform(ParseMachine.Action action) {
        switch (action){
            case DOUBLE_QUOTATION:
                return ParseMachine.constCharStatus;
            case TURN_OPERATOR:
                return ParseMachine.intermediateStatus;
            case BLANK:
                return ParseMachine.startStatus;
            case CAREER_OPERATOR:
                return ParseMachine.startStatus;
            default:
                return ParseMachine.normalStatus;
        }
    }

    @Override
    public ParseMachine.Type GetType() {
        return ParseMachine.Type.EMPTY;
    }

    public String toString(){
        return "StartStatus";
    }
}
