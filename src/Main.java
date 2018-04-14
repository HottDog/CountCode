import parseMachine.ParseMachine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by 袁江超 on 2018/4/12.
 */
public class Main {
    //测试文本的路径
    public static final String TEST_PATH = "H:\\A_py_workspace\\CountCode\\Assets\\test.cpp";

    public static void main(String []args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(TEST_PATH));
        int totalCount = 0;             //总行数
        int emptyCount= 0;              //空行数
        int effectiveCount =0;          //有效行数
        int commentCount = 0;           //注释行数
        boolean isEffect = false;
        boolean isComment = false;
        boolean isEmpty = true;
        ParseMachine parseMachine = ParseMachine.ClassHolder.Singleton();
        while(scanner.hasNextLine()){
            String s = scanner.nextLine();
            System.out.println(s);
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
        System.out.println("empty:"+emptyCount);
        System.out.println("total:"+totalCount);
        System.out.println("effective:"+effectiveCount);
        System.out.println("comment:"+commentCount);
    }


}
