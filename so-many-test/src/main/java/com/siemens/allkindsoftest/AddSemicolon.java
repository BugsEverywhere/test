package com.siemens.allkindsoftest;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class AddSemicolon {


    public static void main(String args[]) throws IOException {

        File inputSqlFile = new File("/Users/nali/Downloads/outputSql.txt");
        File outputSqlFile = new File("/Users/nali/Downloads/outputSqlagain.txt");
        outputSqlFile.createNewFile();

        BufferedReader reader = new BufferedReader(new FileReader(inputSqlFile));

        StringBuilder sb = new StringBuilder();

        String singleLine = null;

        while((singleLine=reader.readLine())!=null){

            String[] lineArrays = singleLine.split(",");

            String resultLine = lineArrays[0]+","+lineArrays[1]+";";

            sb.append(resultLine+"\n");

        }

        reader.close();

        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(outputSqlFile));
        BufferedWriter writer = new BufferedWriter(write);


        writer.write(sb.toString());

        writer.close();




    }





}
