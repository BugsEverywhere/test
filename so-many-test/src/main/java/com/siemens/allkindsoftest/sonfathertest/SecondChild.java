package com.siemens.allkindsoftest.sonfathertest;

import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
@Component("secondChild")
public class SecondChild extends FirstChild{

    @Override
    public void print(){

        super.print();
        System.out.println("Second Child");

    }

}
