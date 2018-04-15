import parseMachine.ParseMachine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by 袁江超 on 2018/4/15.
 * JudgeCode
 * 对每个文本进行有效行注释行判断
 */
public class JudgeCode{
    private ParseMachine parseMachine;
    private int totalCount = 0;             //总行数
    private int emptyCount= 0;              //空行数
    private int effectiveCount =0;          //有效行数
    private int commentCount = 0;           //注释行数
    public JudgeCode(){
        parseMachine = new ParseMachine();

    }

    public void Judge(File file) throws FileNotFoundException {

        Scanner scanner = new Scanner(file,"utf-8");
        boolean isEffect = false;    //标记是否是有效行
        boolean isComment = false;   //标记是否是注释行
        boolean isEmpty = true;      //标记是否是空行
        while(scanner.hasNextLine()){
            String s = scanner.nextLine();
//            System.out.println(s);
            totalCount++;
            if(s.equals("")){
                //空行
                emptyCount++;
            }else {
                isEffect = false;
                isComment = false;
                isEmpty = true;
                char[] temp = s.toCharArray();
//                System.out.print("temp:"+temp.toString()+" ");
//                System.out.print("temp length:"+temp.length+
                for (int i = 0; i < temp.length; i++) {
                    parseMachine.Parse(parseMachine.ParseAction(temp[i]));
                    if (parseMachine.GetStatus().GetType() == ParseMachine.Type.EFFECTIVE) {
                        isEffect = true;

                    } else if (parseMachine.GetStatus().GetType() == ParseMachine.Type.COMMENT) {
                        isComment = true;
                    }
                    //处理空行内有空格和水平制表符的情况
                    if(temp[i]!=' '&&temp[i]!='\t')
                        isEmpty = false;
                }
                //每行结尾加行尾换行符进行处理
                parseMachine.Parse(parseMachine.CareerOperatorAction());
                if (parseMachine.GetStatus().GetType() == ParseMachine.Type.EFFECTIVE) {
                    isEffect = true;
//                    System.out.println("weihang有效行："+totalCount);
                } else if (parseMachine.GetStatus().GetType() == ParseMachine.Type.COMMENT) {
                    isComment = true;
                }
                if(isEffect){
                    effectiveCount++;
//                    System.out.println("有效行："+totalCount);
                }
                if(isComment){
                    commentCount++;
                }
                if(isEmpty)
                    emptyCount++;
            }

        }
        String tempS = "file:"+file.getName()+" "+
        "total:"+totalCount+" "+
        "empty:"+emptyCount+" "+
        "effective:"+effectiveCount+" "+
        "comment:"+commentCount;
        StatisticCodes.GetInstance().AddContent(tempS);

    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getEmptyCount() {
        return emptyCount;
    }

    public int getEffectiveCount() {
        return effectiveCount;
    }

    public int getCommentCount() {
        return commentCount;
    }
}
