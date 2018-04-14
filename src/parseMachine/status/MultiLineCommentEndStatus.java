package parseMachine.status;

import parseMachine.IStatus;
import parseMachine.ParseMachine;

/**
 * Created by 袁江超 on 2018/4/13.
 */
public class MultiLineCommentEndStatus implements IStatus{
    @Override
    public IStatus Transform(ParseMachine.Action action) {
        switch (action){
            case TURN_OPERATOR:
                return ParseMachine.startStatus;
            case ASTERISK:
                return ParseMachine.multiLineCommentEndStatus;
            case LINE_CONTINUATION:
                return ParseMachine.multiLineCommentEndNextStatus;
            default:
                return ParseMachine.multiLineCommentStatus;
        }
    }

    @Override
    public ParseMachine.Type GetType() {
        return ParseMachine.Type.COMMENT;
    }

    public String toString(){
        return "MultiLineCommentEndStatus";
    }
}
