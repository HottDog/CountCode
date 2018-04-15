import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by 袁江超 on 2018/4/15.
 * FilePool
 * 待处理的文件池
 */
public class FilePool {
    private LinkedList<String> files ;
    private int count = 0;    //总文件个数

    public FilePool(){
        files = new LinkedList<>();
    }

    //获取ParseMachine的单例
    private static class ClassHolder{
        public static FilePool  filePool = new FilePool();
    }
    public static FilePool GetInstance(){
        return ClassHolder.filePool;
    }

    /**
     * 把给定路径内的所有文件提取出来
     * @param path
     */
    public void ProcessFilePath(String path){
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
//                System.out.println("文件：" + tempList[i]);
                files.add(tempList[i].toString());
                count++;
            }
            if (tempList[i].isDirectory()) {
//              System.out.println("文件夹：" + tempList[i]);
                ProcessFilePath(tempList[i].toString());
            }
        }
    }

    synchronized public int GetCurrentNum(){
        return count;
    }

    //拿取一个待处理文件，如何没有则返回空
    synchronized public String GetWork(){
        if(count==0)
            return null;
        count--;
        return files.removeLast();
    }
}
