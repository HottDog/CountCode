import java.util.ArrayList;

/**
 * Created by 袁江超 on 2018/4/15.
 * StatisticCodes
 * 对所有文件的有效行注释行进行统计处理
 */
public class StatisticCodes {
    //统计下结束的线程
    private int over_count=1;

    private int totalCount = 0;             //总行数
    private int emptyCount= 0;              //空行数
    private int effectiveCount =0;          //有效行数
    private int commentCount = 0;           //注释行数
    private ArrayList<String> statisticContent ;
    private StatisticCodes(){
        statisticContent = new ArrayList<>();
    }

    private static class ClassHoler{
        public static StatisticCodes statisticCodes = new StatisticCodes();
    }

    public static StatisticCodes GetInstance(){
        return ClassHoler.statisticCodes;
    }

    //涉及多线程，加同步处理

    synchronized public void AddTotalCount(int count){
        totalCount+=count;
    }

    synchronized public void AddEmptyCount(int count){
        emptyCount+=count;
    }

    synchronized public void AddEffectiveCount(int count){
        effectiveCount+=count;
    }

    synchronized public void AddCommentCount(int count){
        commentCount+=count;
    }

    synchronized public void AddContent(String s){
        statisticContent.add(s);
    }
    synchronized public int getCommentCount() {
        return commentCount;
    }

    synchronized public int getEffectiveCount() {
        return effectiveCount;
    }

    synchronized public int getEmptyCount() {
        return emptyCount;
    }

    synchronized public int getTotalCount() {
        return totalCount;
    }

    public void PrintContent(){
        for (int i=0;i<statisticContent.size();i++){
            System.out.println(statisticContent.get(i));
        }
    }
    public void print(){
        System.out.println("empty:"+emptyCount);
        System.out.println("total:"+totalCount);
        System.out.println("effective:"+effectiveCount);
        System.out.println("comment:"+commentCount);
    }

    public synchronized void Increse(){
        over_count++;
    }

    public synchronized boolean IsOver(){
        if(over_count==Main.THREAD_NUM){
            return true;
        }else {
            return false;
        }
    }
}
