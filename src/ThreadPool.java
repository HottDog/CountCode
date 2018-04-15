import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by 袁江超 on 2018/4/15.
 * ThreadPool
 * 线程池
 * 使用线程池来维护线程的生命周期
 */
public class ThreadPool {
    private static class ThreadPoolExecutorHolder{
        public static ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(
                20,40,3, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(50)
                ,new ThreadPoolExecutor.DiscardOldestPolicy()
        );
    }
    public static ThreadPoolExecutor getInstance(){
        return ThreadPool.ThreadPoolExecutorHolder.threadPoolExecutor;
    }
}
