package com.siemens.allkindsoftest.sqlScripts;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class trackNodeSelectSql {

    public static void main(String[] args) throws IOException {

        StringBuilder sb = new StringBuilder("");

        File idFile = new File("/Users/nali/Desktop/3479条问题trackId");

        BufferedReader reader = new BufferedReader(new FileReader(idFile));

        String singleTrackId = null;

        while(null!=(singleTrackId = reader.readLine())){

            sb.append("(select * from ting_content.tb_track_node where node>=crc32("+singleTrackId+") limit 1) union all\n");


        }

        reader.close();

        File outputSqlFile = new File("/Users/nali/Desktop/查3479条分表号.sql");

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputSqlFile)));


        writer.write(sb.toString());

        writer.close();

    }
}
