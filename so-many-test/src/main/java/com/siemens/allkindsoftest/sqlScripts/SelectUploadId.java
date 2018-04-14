package com.siemens.allkindsoftest.sqlScripts;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class SelectUploadId {

    public static void main(String[] args) throws IOException {

        StringBuilder sb = new StringBuilder("");

        File idFile = new File("/Users/nali/Desktop/3479条有问题额audio生肉");

        BufferedReader reader = new BufferedReader(new FileReader(idFile));

        String singleId = null;

        int singleLineCount = 0;

        while(null!=(singleId = reader.readLine())){

            sb.append(" or audio_id = "+singleId.split("_")[1]);

            singleLineCount++;

            if(singleLineCount == 5){

                sb.append("\n");

                singleLineCount = 0;

            }

        }

        reader.close();

        File outputSqlFile = new File("/Users/nali/Desktop/查3479条的transcode.txt");

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputSqlFile)));

        writer.write(sb.toString());

        writer.close();


    }


}
