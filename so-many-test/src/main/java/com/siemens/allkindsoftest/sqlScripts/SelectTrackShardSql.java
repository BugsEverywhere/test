package com.siemens.allkindsoftest.sqlScripts;

import java.io.*;
import java.lang.reflect.Array;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class SelectTrackShardSql {

    public static void main(String[] args) throws IOException {

        StringBuilder sb = new StringBuilder("");

        File trackIdFile = new File("/Users/nali/Desktop/3479条问题trackId");

        File audioIdFile = new File("/Users/nali/Desktop/3479条有问题额audio生肉");

        BufferedReader audioReader = new BufferedReader(new FileReader(audioIdFile));

        BufferedReader trackReader = new BufferedReader(new FileReader(trackIdFile));

        String singleAudioId = null;

        while(null!=(singleAudioId = audioReader.readLine())){

            String singleTrackId = trackReader.readLine();

            sb.append(singleAudioId.split("_")[1]+","+singleTrackId+"\n");


        }

        audioReader.close();

        trackReader.close();

        File outputSqlFile = new File("/Users/nali/Desktop/3479条audio和track.txt");

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputSqlFile)));

        writer.write(sb.toString());

        writer.close();


    }




}
