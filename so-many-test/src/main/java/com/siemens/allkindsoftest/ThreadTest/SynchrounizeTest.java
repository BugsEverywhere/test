package com.siemens.allkindsoftest.ThreadTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class SynchrounizeTest {


    public static void main(String[] args) throws InterruptedException {

        TestModel testModel = new TestModel();

        List<String> testList = new ArrayList<>(2);

        List<String> testList2 = new ArrayList<>();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    testModel.print1();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    testModel.print2();

                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        thread1.start();

        TimeUnit.MILLISECONDS.sleep(1000L);


        thread2.start();



    }






}
