package com.siemens.allkindsoftest;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class compareIds {

    public static void main(String[] args) throws IOException {

        Set<String> file1String = new HashSet<>();

        Set<String> file2String = new HashSet<>();

        String encoding = "UTF-8";

        File idFile1 = new File("/Users/nali/Desktop/idFile1");

        FileInputStream in = new FileInputStream(idFile1);

        Long filelength = idFile1.length();
        byte[] filecontent = new byte[filelength.intValue()];


        in.read(filecontent);

        String content = new String(filecontent,encoding);

        String[] idsArray = content.split(",");

        for(String singleId:idsArray){

            file1String.add(singleId);

        }

        in.close();

        //=======================================================================

        File idFile2 = new File("/Users/nali/Desktop/idFile2");

        Long filelength2 = idFile2.length();
        byte[] filecontent2 = new byte[filelength2.intValue()];

        FileInputStream in2 = new FileInputStream(idFile2);


        in2.read(filecontent2);

        String content2 = new String(filecontent2,encoding);

        String[] idsArray2 = content2.split(",");

        for(String singleId:idsArray2){

            file2String.add(singleId);

        }

        in2.close();

        //==========================================================================

        for(String singleFile2Id:file2String){

            if(!file1String.contains(singleFile2Id)){

                System.out.println(singleFile2Id);

            }

        }

    }



}
