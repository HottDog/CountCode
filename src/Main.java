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
    public static final String TEST_FILES_PATH = "H:\\A_py_workspace\\CountCode\\Assets\\test";

    //线程数量
    public static final int THREAD_NUM = 4;
//    public static int over_count=1;
    public static void main(String []args) throws FileNotFoundException {
        //解析处理文件夹路径
//        System.out.print(args[0]);
        FilePool.GetInstance().ProcessFilePath(TEST_FILES_PATH);
        TaskOverListener listener = new TaskOverListener() {
            @Override
            public void TaskOver() {
                if(StatisticCodes.GetInstance().IsOver()) {
                    StatisticCodes.GetInstance().PrintContent();
                    System.exit(0);

                }else {
                    StatisticCodes.GetInstance().Increse();
                }
            }
        };
        for(int i=0;i<THREAD_NUM;i++){
            ThreadPool.getInstance().execute(new FileTask(listener));
        }
    }


}
