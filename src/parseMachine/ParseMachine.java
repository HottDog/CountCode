package parseMachine;

import parseMachine.status.*;

/**
 * Created by 袁江超 on 2018/4/13.
 */
public class ParseMachine {
    public enum Type{
        EFFECTIVE,      // 有效行
        COMMENT,        // 注释
        INTERMEDIATE,   // 未确认状态
        EMPTY,          // 空行
    }
    //基于windows
    public enum Action{
        DOUBLE_QUOTATION,    // "
        TURN_OPERATOR,       // / 转义符
        ASTERISK,            // *
        CAREER_OPERATOR,     // 文本内部的换行符
        LINE_CONTINUATION,   // \ 续行符
        OTHER,               // 其他，后续可以添加
        BLANK,               //空格
    }


    //当前状态
    private IStatus _mStatus;

    public static ConstCharNextStatus constCharNextStatus = new ConstCharNextStatus();
    public static ConstCharStatus constCharStatus= new ConstCharStatus();
    public static IntermediateStatus intermediateStatus = new IntermediateStatus();
    public static IntermediateNextStatus intermediateNextStatus = new IntermediateNextStatus();
    public static MultiLineCommentEndStatus multiLineCommentEndStatus= new MultiLineCommentEndStatus();
    public static MultiLineCommentEndNextStatus multiLineCommentEndNextStatus= new MultiLineCommentEndNextStatus();
    public static MultiLineCommentStatus multiLineCommentStatus = new MultiLineCommentStatus();
    public static NormalStatus normalStatus = new NormalStatus();
    public static SingleLineCommentNextStatus singleLineCommentNextStatus= new SingleLineCommentNextStatus();
    public static SingleLineCommentStatus singleLineCommentStatus= new SingleLineCommentStatus();
    public static StartStatus startStatus= new StartStatus();


     public ParseMachine(){
        _mStatus = startStatus;    //每个文本初始状态是StartStatus
    }

    public Action ParseAction(char s){
//        System.out.print("ActionChar:"+s+"  ");
        switch (s){
            case '"':
                return Action.DOUBLE_QUOTATION;
            case '\\':
//                System.out.print("续行符："+s);
                return Action.LINE_CONTINUATION;
            case '*':
                return Action.ASTERISK;
            case '/':
                return Action.TURN_OPERATOR;
            case ' ':
                return Action.BLANK;
            case '\t':
                return Action.BLANK;
            default:
                return Action.OTHER;
        }
    }

    public Action CareerOperatorAction(){
        return Action.CAREER_OPERATOR;
    }

    public void Parse(Action action){
//        System.out.print("action:"+ action.toString()+"  ");
//        System.out.print(_mStatus.toString());
        _mStatus = _mStatus.Transform(action);
//        System.out.print("->"+_mStatus.toString()+"   ");
    }

    public IStatus GetStatus(){
        return _mStatus;
    }
}
