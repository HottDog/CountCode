package parseMachine.status;

import parseMachine.IStatus;
import parseMachine.ParseMachine;

/**
 * Created by 袁江超 on 2018/4/13.
 */
public class SingleLineCommentNextStatus implements IStatus{
    @Override
    public IStatus Transform(ParseMachine.Action action) {
        switch (action){
            case LINE_CONTINUATION:
                return ParseMachine.singleLineCommentNextStatus;
            default:
                System.out.println("下一行还是注释行");
                return ParseMachine.singleLineCommentStatus;
        }
    }

    @Override
    public ParseMachine.Type GetType() {
        return ParseMachine.Type.COMMENT;
    }

    public String toString(){
        return "SingleLineCommentNextStatus";
    }
}
