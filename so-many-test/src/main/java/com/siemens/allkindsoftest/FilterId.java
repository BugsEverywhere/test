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
public class FilterId {

    public static void main(String[] args) throws IOException {

        File inputSqlFile1 = new File("/Users/nali/Downloads/放弃治疗的audio.csv");

        File inputSqlFile2 = new File("/Users/nali/Desktop/需要重新转码的audioId");

        File outputFile = new File("/Users/nali/Desktop/最终需要重新转码的audioId.txt");
        outputFile.createNewFile();

        Set<String> allIds = new HashSet<>();

        BufferedReader rd = new BufferedReader(new FileReader(inputSqlFile2));

        String singleId = null;

        while(null!=(singleId = rd.readLine())){

            singleId = singleId.split(",")[0];

            allIds.add(singleId);

        }

        rd.close();

        //=================================================

        BufferedReader rd1 = new BufferedReader(new FileReader(inputSqlFile1));

        String singleId1 = null;

        int line = 0;

        while(null!=(singleId1 = rd1.readLine())){

            line++;

            if(line==1){
                continue;
            }

            String toBeChecked = singleId1.split(",")[0];

            allIds.remove(toBeChecked);


        }

        rd1.close();


        //====================================================

        StringBuilder sb = new StringBuilder();

        for(String singleId2 : allIds){

            sb.append(singleId2+"\n");

        }

        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(outputFile));
        BufferedWriter writer = new BufferedWriter(write);

        writer.write(sb.toString());

        writer.close();



    }


}
