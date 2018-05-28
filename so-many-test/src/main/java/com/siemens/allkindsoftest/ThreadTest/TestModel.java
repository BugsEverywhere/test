package com.siemens.allkindsoftest.ThreadTest;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class TestModel {


    public synchronized void print1() throws InterruptedException {

        System.out.println("Thread1");

        TimeUnit.HOURS.sleep(1);

    }


    public synchronized void print2(){

        System.out.println("Thread2 oh yeah!!!!!");

    }

}
