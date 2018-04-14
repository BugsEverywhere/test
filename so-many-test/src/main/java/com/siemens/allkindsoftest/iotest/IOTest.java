package com.siemens.allkindsoftest.iotest;

import java.io.*;

/**
 * Created by Chen Zhuo on 2017/8/14.
 */
public class IOTest {

    public static void main(String[] args) throws IOException {

        BufferedReader reader;

        FilterReader reader1;

        FilterWriter writer;

        StringReader reader2;

        TestClass ts1 = new TestClass("张三", 1, 15, "化学");
        TestClass ts2 = new TestClass("李四", 2, 19, "生物");

        FileOutputStream fout=new FileOutputStream("student.txt");
        ObjectOutputStream out=new ObjectOutputStream(fout);

        out.writeObject(ts1);
        out.writeObject(ts2);


    }



}


class TestClass implements  Serializable{

    String name;
    int id ;
    int age;
    String department;
    public TestClass(String name, int id, int age, String department) {
        this.age = age;
        this.department = department;
        this.id = id;
        this.name = name;
    }

}
