package com.siemens.allkindsoftest.ThreadTest;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Chen Zhuo on 2017/8/19.
 */
public class LockTest {

    public static void main(String[] args) throws InterruptedException {

        LockTest lockTest = new LockTest();

        Thread thread1 = new Thread(new SelfThread1(lockTest));

        Thread thread2 = new Thread(new SelfThread2(lockTest));

        ExecutorService pool = Executors.newCachedThreadPool();

        pool.execute(thread1);

        //TimeUnit.MILLISECONDS.sleep(2000);

        pool.execute(thread2);

        pool.shutdown();


    }

}


class SelfThread1 implements Runnable{

    LockTest lockTest;

    public SelfThread1(LockTest lt){

        lockTest = lt;

    }

    @Override
    public void run() {

        try {

            synchronized (lockTest){

                System.out.println("Thread1 is waiting");

                lockTest.wait();

            }

            while(true){

                System.out.println("Thread1 waked up");

                TimeUnit.SECONDS.sleep(1);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class SelfThread2 implements Runnable{

    LockTest lockTest;

    public SelfThread2(LockTest lt){

        lockTest = lt;

    }

    @Override
    public void run() {

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread2 is notifying");

        synchronized (lockTest){

            lockTest.notifyAll();

        }
    }
}



