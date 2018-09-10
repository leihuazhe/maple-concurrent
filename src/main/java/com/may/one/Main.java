package com.may.one;

public class Main {
	
	public static void main(String[] args)
    {
        boolean result = false;
        try {
            result = ApplicationStartupUtil.checkExternalServices();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("External services validation completed !! Result was :: "+ result);
    }
	
	/*
	 
		    解释一下CountDownLatch概念?
		 	CountDownLatch 和CyclicBarrier的不同之处?
		    	给出一些CountDownLatch使用的例子?
		 			CountDownLatch 类中主要的方法?

	 
	  
	 
	 */
}
