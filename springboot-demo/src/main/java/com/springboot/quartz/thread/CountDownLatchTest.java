package com.springboot.quartz.thread;/**
 * Created by dell on 2017/11/22.
 */

import java.util.concurrent.TimeUnit;

/**
 * 测试计数器锁
 *
 * @author jiasx
 * @create 2017-11-22 15:06
 **/
public class CountDownLatchTest implements Runnable{

    public int count= 0;

    @Override
    public void run() {
        count ++;
    }


    public static void main(String[] args) {
        CountDownLatchTest countDownLatchTest = new CountDownLatchTest();
        for(int i=0;i<50;i++){
            new Thread(countDownLatchTest).start();
        }
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("count="+countDownLatchTest.count);
    }
}
