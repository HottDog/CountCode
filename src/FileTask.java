import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by 袁江超 on 2018/4/15.
 * FileTask
 * 线程的处理任务
 */
public class FileTask implements Runnable{
    private TaskOverListener listener;
    public FileTask(TaskOverListener l){
        listener = l;
    }

    @Override
    public void run() {
        String path = FilePool.GetInstance().GetWork();
        while(path!=null){
            JudgeFile(path);
            path = FilePool.GetInstance().GetWork();
        }
        listener.TaskOver();
    }

    private void JudgeFile(String path){
        JudgeCode judgeCode = new JudgeCode();
        try {
            judgeCode.Judge(new File(path));
            StatisticCodes.GetInstance().AddCommentCount(judgeCode.getCommentCount());
            StatisticCodes.GetInstance().AddEffectiveCount(judgeCode.getEffectiveCount());
            StatisticCodes.GetInstance().AddEmptyCount(judgeCode.getEmptyCount());
            StatisticCodes.GetInstance().AddTotalCount(judgeCode.getTotalCount());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
