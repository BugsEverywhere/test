package com.siemens.allkindsoftest.extendTest;

/**
 * Created by Chen Zhuo on 2017/9/21.
 */
public class Father {

    private AnotherChild anotherChild;

    public void function(String var1){

        System.out.println("Function in father!");

    }

    private void fatherPrivateFunction(){

        System.out.println("Private function in father!");

    }

}
