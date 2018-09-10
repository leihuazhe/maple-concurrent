package com.may.one;

import java.util.concurrent.CountDownLatch;

public abstract class BaseHealthChecker implements Runnable{

    private CountDownLatch countDownLatch;

    private String serviceName;

    private boolean serviceUp;

    public BaseHealthChecker(String serviceName,CountDownLatch countDownLatch){
        this.serviceName = serviceName;
        this.countDownLatch = countDownLatch;
        this.serviceUp= false;
    }


    public void run() {
        try {
            verifyService();
            serviceUp = true;
        }catch (Throwable t) {
            t.printStackTrace(System.err);
            serviceUp = false;
        } finally {
            if(countDownLatch != null) {
                countDownLatch.countDown();
            }
        }

    }
    public String getServiceName() {
        return serviceName;
    }

    public boolean isServiceUp() {
        return serviceUp;
    }

    public abstract void verifyService();
}