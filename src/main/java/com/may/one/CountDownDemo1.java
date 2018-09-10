package com.may.one;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownDemo1 {
    public static void main(String[] args) {
        ExecutorService executor=Executors.newFixedThreadPool(5);
        final CountDownLatch countDownLatch=new CountDownLatch(3);

        executor.execute(new Runnable(){
            public void run() {
                try{
                    System.out.println("订机票");
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }finally{
                    countDownLatch.countDown();
                }
            }
        });
        executor.execute(new Runnable(){
            public void run() {
                try{
                    System.out.println("订巴士");
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }finally{
                    countDownLatch.countDown();
                }
            }
        });
        executor.execute(new Runnable(){
            public void run() {
                try{
                    System.out.println("订酒店");
                    TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }finally{
                    countDownLatch.countDown();
                }
            }
        });

        try {
            countDownLatch.await();
            System.out.println("可以出发了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            executor.shutdown();
        }
    }
}
