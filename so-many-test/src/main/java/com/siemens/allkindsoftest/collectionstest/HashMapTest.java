package com.siemens.allkindsoftest.collectionstest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chen Zhuo on 2017/9/19.
 */
public class HashMapTest {

    public static void main(String[] args){

        Map<String,Person> objectMap = new HashMap<>();

        Person person1 = new Person("John",18);
        //Person person2 = new Person("Susan",28);

        objectMap.put("John",person1);
        //objectMap.put("Susan",person2);

        person1.setAge(48);

        //System.out.println(person1.getAge());
        System.out.println(objectMap.get("John").getAge());


    }
}
