package com.siemens.allkindsoftest.iotest;

import java.io.*;

/**
 * Created by Chen Zhuo on 2017/9/3.
 */
public class DataInputStreamTest {

    public static void main(String[] args) throws IOException {

        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File("test.txt")));

        for(int i = 0;i<10;i++){

            bos.write(i+128*3);

        }

        bos.close();

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("test.txt"));

        int available = bis.available();

        int singleByte = 0;

        while((singleByte=bis.read())!=-1){

            System.out.println(singleByte);

        }

        bis.close();

        BufferedReader reader = new BufferedReader(new FileReader(new File("test.txt")));

        int sinleChar = 0;

        while((sinleChar=reader.read())!=-1){

            System.out.println(sinleChar);

        }

        reader.close();

    }
}
