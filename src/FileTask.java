import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by 袁江超 on 2018/4/15.
 */
public class FileProcessTask implements Runnable{
    @Override
    public void run() {
        JudgeCode judgeCode = new JudgeCode();
        try {
            judgeCode.Judge(new File(Main.TEST_PATH));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
