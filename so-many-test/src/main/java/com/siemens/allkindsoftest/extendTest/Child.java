package com.siemens.allkindsoftest.extendTest;

/**
 * Created by Chen Zhuo on 2017/9/21.
 */
public class Child extends Father {

    public String testStringVariable;

    public int testIntVariable;

    public String getTestStringVariable() {
        return testStringVariable;
    }

    public void setTestStringVariable(String testStringVariable) {
        this.testStringVariable = testStringVariable;
    }

    public int getTestIntVariable() {
        return testIntVariable;
    }

    public void setTestIntVariable(int testIntVariable) {
        this.testIntVariable = testIntVariable;
    }

    @Override
    public void function(String var1){

        System.out.println("Child function");


    }


}


class AnotherChild{



}
