package com.siemens.allkindsoftest.ThreadTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Chen Zhuo on 2017/8/20.
 */
public class IOBlocked implements Runnable {

    private InputStream in;

    public IOBlocked(InputStream is){

        in = is;

    }

    @Override
    public void run() {

        try{

            System.out.println("waiting for read:");

            in.read();

        } catch (IOException e) {
            if(Thread.currentThread().isInterrupted()){
                System.out.println("Interrupted from blocked I/O");
            } else{
                throw new RuntimeException();
            }

        }

        System.out.println("Exiting IOBlocked.run().");
    }
}

class MainTest{

    public static void main(String[] args) throws InterruptedException, IOException {

        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new IOBlocked(System.in));
        TimeUnit.MILLISECONDS.sleep(100);
        exec.shutdownNow();
        TimeUnit.MILLISECONDS.sleep(1000);
        System.in.close();

    }



}
