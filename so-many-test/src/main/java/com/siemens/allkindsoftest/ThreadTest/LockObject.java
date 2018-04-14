package com.siemens.allkindsoftest.ThreadTest;

import java.util.concurrent.TimeUnit;

/**
 * Created by Chen Zhuo on 2017/11/13.
 */
public class LockObject {

    public synchronized void function1() throws InterruptedException {

        //wait();

        System.out.println("Function1 starts sleeping");

        TimeUnit.MILLISECONDS.sleep(5000L);

        System.out.println("Function1 ends sleeping");

    }

    public synchronized void function2() throws InterruptedException {

        //wait();

        System.out.println("Function2 starts sleeping");

        TimeUnit.MILLISECONDS.sleep(5000L);

        System.out.println("Function2 ends sleeping");

    }

    public synchronized void function3() throws InterruptedException {

        //wait();

        System.out.println("Function3 starts sleeping");

        TimeUnit.MILLISECONDS.sleep(5000L);

        System.out.println("Function3 ends sleeping");

    }

}
