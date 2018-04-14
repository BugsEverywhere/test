package com.siemens.allkindsoftest.ReflectionTest;

/**
 * Created by Chen Zhuo on 2017/9/13.
 */
public class TestObject {


}



class TestSubObject extends TestObject{

    public static void main(String[] args){

        TestObject testObject = new TestObject();

        testObject.hashCode();

        String aString = new String("ahaha");

        System.out.println(aString.hashCode());




    }

}