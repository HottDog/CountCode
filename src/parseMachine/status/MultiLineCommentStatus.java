package parseMachine.status;

import parseMachine.IStatus;
import parseMachine.ParseMachine;

/**
 * Created by 袁江超 on 2018/4/13.
 */
public class MultiLineCommentStatus implements IStatus{
    @Override
    public IStatus Transform(ParseMachine.Action action) {
        switch (action){
            case ASTERISK:
                return ParseMachine.multiLineCommentEndStatus;
            default:
                return ParseMachine.multiLineCommentStatus;
        }
    }

    @Override
    public ParseMachine.Type GetType() {
        return ParseMachine.Type.COMMENT;
    }

    public String toString(){
        return "MultiLineCommentStatus";
    }
}
