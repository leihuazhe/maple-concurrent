package com.may.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author maple 2018.09.10 下午9:40
 */
public class TestCdLatch {

    static final CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        String ss = "1111";
        System.out.println(111);
        if (ss.equals("111")) {
            Thread.sleep(2000);
            TimeUnit.SECONDS.sleep(1);
            ;
            System.out.println("˯2s");
            connectedSemaphore.countDown();

        }
        connectedSemaphore.await();
        System.out.println(222);
    }
}
