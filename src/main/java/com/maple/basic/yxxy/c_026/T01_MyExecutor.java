/**
 * 认识Executor
 */
package com.maple.basic.yxxy.c_026;

import java.util.concurrent.Executor;

public class T01_MyExecutor implements Executor {

	public static void main(String[] args) {
		new T01_MyExecutor().execute(()->System.out.println("hello executor"));
	}

	@Override
	public void execute(Runnable command) {
		//new Thread(command).run();
		command.run();
		System.out.println("自定义的....");
	}

}

