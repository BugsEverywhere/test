package com.siemens.allkindsoftest.exceptionTest;

/**
 * Created by Chen Zhuo on 2017/8/20.
 */
public class ExceptionCatchTest {

    public static void main(String[] args){

        try {
            ExceptionCatchTest.class.getClassLoader().loadClass("ahaha");
        } catch (ClassNotFoundException e) {
            System.out.println("catch me here!");
        }

        System.out.println("still running");


    }
}
