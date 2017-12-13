package com.springboot.quartz.thread;/**
 * Created by dell on 2017/11/21.
 */

import sun.management.counter.Units;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁测试
 *
 * @author jiasx
 * @create 2017-11-21 15:55
 **/
public class ReentrantLockTest implements Runnable{
    private static ReentrantLock lock  = new ReentrantLock();
    private static int i=0;

    @Override
    public void run() {
//        lock.lock();
//        try {
//            i++;
//        }finally {
//            lock.unlock();
//        }
        i++;
//        System.out.println("========="+i);

    }

    public static void main(String[] args) throws InterruptedException {
        for(int j=0;j<1000;j++){
            Thread thread1 = new Thread(new ReentrantLockTest());
            thread1.start();
        }
        TimeUnit.SECONDS.sleep(2);
        System.out.println("========="+i);
    }
}
