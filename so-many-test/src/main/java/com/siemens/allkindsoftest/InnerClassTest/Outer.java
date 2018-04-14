package com.siemens.allkindsoftest.InnerClassTest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chen Zhuo on 2017/9/11.
 */
public class Outer {

    private  Inner inner;

    public Inner getInner() {
        return inner;
    }

    public void setInner(Inner inner) {
        this.inner = inner;
    }

    private static class Inner{

        public static void main(String[] args){

            System.out.println("Hello World");


        }


    }
}
