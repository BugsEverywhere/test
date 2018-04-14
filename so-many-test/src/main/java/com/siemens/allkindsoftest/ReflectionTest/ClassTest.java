package com.siemens.allkindsoftest.ReflectionTest;

import java.util.Arrays;

/**
 * Created by Chen Zhuo on 2017/9/13.
 */
public class ClassTest {

    public static void main(String[] args){

//        Object[] haha = new Object[10];
//
//        Object[] haha2 = Arrays.copyOf(haha,5,Object[].class);
//
//        System.out.println();

        Object[] nums = Arrays.copyOf(new String[]{"a", "b"}, 2, Integer[].class);

    }


}
