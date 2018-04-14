package com.siemens.allkindsoftest.classloadertest;

/**
 * Created by Chen Zhuo on 2017/11/13.
 */
public class LoadAndInit {

    public static void main(String[] args) throws ClassNotFoundException {

        Class clazz = Class.forName("com.siemens.allkindsoftest.classloadertest.TestClass");

        //Class clazz = TestClass.class;

    }

}


class TestClass{

    private int i;

    private int j;

    private int h;

    public static int staticPrint;

    static {

        System.out.println("static method runs");

    }


    public TestClass(int input1){

        this.i = input1;

    }

    public TestClass(int input1,int input2){

        this.j = input1;
        this.h = input2;

    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public int getH() {
        return h;
    }
}