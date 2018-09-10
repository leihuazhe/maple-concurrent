package com.may.test;

import java.util.concurrent.CountDownLatch;

public class TestCountDown2 {
	
	
	static final CountDownLatch connectedSemaphore = new CountDownLatch(100);
	
	public static void main(String[] args) throws InterruptedException {
		for(int i = 0;i <=100;i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName()+" 线程开始执行业务逻辑!");
					connectedSemaphore.countDown();
				}
			},"t"+i).start();
		}
		connectedSemaphore.await();
		System.out.println("全部执行完毕了！！！");
	}
}
