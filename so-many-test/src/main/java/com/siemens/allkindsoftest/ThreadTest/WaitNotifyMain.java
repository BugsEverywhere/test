package com.siemens.allkindsoftest.ThreadTest;

import java.util.concurrent.TimeUnit;

/**
 * Created by Chen Zhuo on 2017/11/13.
 */
public class WaitNotifyMain {

    public static void main(String[] args) throws InterruptedException {

        LockObject lockObject = new LockObject();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    synchronized (lockObject){
                        lockObject.wait();
                    }

                    lockObject.function1();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

//        Thread t2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                try {
//                    lockObject.function2();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
//
//        Thread t3 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                try {
//                    lockObject.function3();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });


        t1.start();
        TimeUnit.MILLISECONDS.sleep(5000L);

        synchronized (lockObject){
            lockObject.notifyAll();
        }

//        t2.start();
//
//        t3.start();
//
//        TimeUnit.MILLISECONDS.sleep(2000L);

    }
}
