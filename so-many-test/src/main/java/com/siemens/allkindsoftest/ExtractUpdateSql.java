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
public class ExtractUpdateSql {

    public static void main(String[] args) throws IOException {

        File inputSqlFile = new File("/Users/nali/Desktop/所有的update.sql");

        File idFile = new File("/Users/nali/Desktop/需要重新转码的audioId");

        File outputFile = new File("/Users/nali/Desktop/筛选后的update.sql");
        outputFile.createNewFile();

        Set<String> idSet = new HashSet<>();

        BufferedReader rd = new BufferedReader(new FileReader(idFile));

        String singleId = null;

        while(null!=(singleId=rd.readLine())){

            idSet.add(singleId.split(",")[0]);

        }

        rd.close();

        //=========================================

        BufferedReader rd1 = new BufferedReader(new FileReader(inputSqlFile));

        StringBuilder sb = new StringBuilder();

        String singleUpdateSql = null;

        while(null!=(singleUpdateSql = rd1.readLine())){

            String[] singleLineArray = singleUpdateSql.split(",");

            String idSetSentence = singleLineArray[10];

            String thisId = idSetSentence.split("=")[1].trim();

            if(!idSet.contains(thisId)){

                sb.append(singleUpdateSql+"\n");

            }

        }

        rd1.close();

        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(outputFile));
        BufferedWriter writer = new BufferedWriter(write);

        writer.write(sb.toString());

        writer.close();





    }
}
