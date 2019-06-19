package com.hpedu.test;

import io.netty.util.concurrent.DefaultThreadFactory;
import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class ThreadPool {

    /*int corePoolSize,
      int maximumPoolSize,
      long keepAliveTime,
      TimeUnit unit,
      BlockingQueue<Runnable> workQueue,
      ThreadFactory threadFactory,
      RejectedExecutionHandler handler */

    static int corePoolSize = 2 ;
    static int maximumPoolSize = 3 ;
    static  int keepAliveTime = 60 ;
    static TimeUnit unit = TimeUnit.SECONDS;
    static  BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(10) ;
    static RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();//任务数量超过队列限制,报错
    static ThreadFactory threadFactory = Executors.defaultThreadFactory();
    
    
@Test
    public void main() throws InterruptedException, ExecutionException, TimeoutException {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(corePoolSize,
                                                        maximumPoolSize,
                                                        keepAliveTime,
                                                        unit,
                                                        workQueue,
                                                        threadFactory,
                                                        handler ) ;

        for (int i = 0; i < 13; i++) { //超过(maximumPoolSize + workQueue.capacity)个 就会报错.
            Future<?> submit = threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("当前线程:" + Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000 * 2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },new StringBuilder("fuck") );// 这个Stringbuilder 对象相当于一个 标志, 任务执行ok ,则能成功返回此值. 
            Object o = submit.get(1000 * 6, TimeUnit.MILLISECONDS);
            System.out.println("result=" + o);
        }
        /*线程任务完成情况 如何确定?*/
        
    }
    
    public void test () {
        Executors.newCachedThreadPool();//无限
        Executors.newFixedThreadPool(3) ;
        Executors.newScheduledThreadPool(3) ;
        Executors.newSingleThreadExecutor() ;
    }
    
}
