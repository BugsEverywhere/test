package com.siemens.allkindsoftest.InnerClassTest;

/**
 * Created by Chen Zhuo on 2017/9/11.
 */
public class InnerClassTest {

    static Outer outer = new Outer();

    //Outer.Inner inner = new Outer.Inner();

    public static void main(String [] args){

        char[] charArr = {'A','B','C'};

        char[] charArr1 = charArr;

        charArr[1] = 'x';

        System.out.println(charArr1);

        outer.hashCode();


    }


}
