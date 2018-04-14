package com.siemens.allkindsoftest.sonfathertest;

import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
@Component("firstChild")
public class FirstChild extends Child {

    @Override
    public void print(){

        super.print();
        System.out.println("FirstChild");

    }
}
