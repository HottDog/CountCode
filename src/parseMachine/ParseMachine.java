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

    //获取ParseMachine的单例
    public static class ClassHolder{
        private static ParseMachine parseMachine ;
        public static ParseMachine Singleton(){
            if(parseMachine==null){
                parseMachine = new ParseMachine();
            }
            return parseMachine;
        }
    }

    //当前状态
    private IStatus _mStatus;

    public static ConstCharNextStatus constCharNextStatus;
    public static ConstCharStatus constCharStatus;
    public static IntermediateStatus intermediateStatus;
    public static IntermediateNextStatus intermediateNextStatus;
    public static MultiLineCommentEndStatus multiLineCommentEndStatus;
    public static MultiLineCommentEndNextStatus multiLineCommentEndNextStatus;
    public static MultiLineCommentStatus multiLineCommentStatus;
    public static NormalStatus normalStatus;
    public static SingleLineCommentNextStatus singleLineCommentNextStatus;
    public static SingleLineCommentStatus singleLineCommentStatus;
    public static StartStatus startStatus;


     ParseMachine(){
        constCharNextStatus = new ConstCharNextStatus();
        constCharStatus = new ConstCharStatus();
        intermediateStatus = new IntermediateStatus();
        intermediateNextStatus = new IntermediateNextStatus();
        multiLineCommentEndStatus = new MultiLineCommentEndStatus();
        multiLineCommentEndNextStatus = new MultiLineCommentEndNextStatus();
        multiLineCommentStatus = new MultiLineCommentStatus();
        normalStatus = new NormalStatus();
        singleLineCommentNextStatus = new SingleLineCommentNextStatus();
        singleLineCommentStatus = new SingleLineCommentStatus();
        startStatus = new StartStatus();

        _mStatus = startStatus;
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
        System.out.print("->"+_mStatus.toString()+"   ");
    }

    public IStatus GetStatus(){
        return _mStatus;
    }
}
