package com.siemens.allkindsoftest;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class SqlScript {


    public static void main(String[] args) throws IOException, ParseException {

        File inputSqlFile = new File("/Users/nali/Downloads/tmp2.txt");

        File outputSqlFile = new File("/Users/nali/Downloads/outputSql.txt");
        outputSqlFile.createNewFile();

        BufferedReader reader = new BufferedReader(new FileReader(inputSqlFile));

        String singleLine = null;

        Set<SingleLine> set = new HashSet<>();

        int lineNum = 0;

        while(null!=(singleLine=reader.readLine())){
            lineNum++;
            String thisTId = singleLine.substring(60,68);
            String thisDate = singleLine.substring(90,109)+"Z";
            Instant instant = Instant.parse(thisDate);
            Date date = Date.from(instant);
            SingleLine singleLine1 = new SingleLine();
            singleLine1.setLine(lineNum);
            singleLine1.setId(thisTId);
            singleLine1.setDate(date);
            singleLine1.setWholeLine(singleLine.substring(0,69));
            set.add(singleLine1);
        }

        reader.close();

        Set<String> idSet = new HashSet<>();
        Set<SingleLine> resultSet = new HashSet<>();


        for(SingleLine single:set){
        //遍历所有行
            if(!idSet.contains(single.getId())){
                resultSet.add(single);
                idSet.add(single.getId());
            }else{
                SingleLine originalLine = null;
                for(SingleLine line : resultSet){

                    if(line.getId().equals(single.getId())){

                        originalLine = line;

                    }
                }

                if(originalLine.getDate().getTime() > single.getDate().getTime()){
                    resultSet.remove(originalLine);
                    resultSet.add(single);
                }

            }
        }

        //===================================================写文件

        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(outputSqlFile));
        BufferedWriter writer = new BufferedWriter(write);

        StringBuilder sb = new StringBuilder();

        for(SingleLine sl:resultSet){
            sb.append(sl.wholeLine+'\n');
        }

        writer.write(sb.toString());
        writer.close();


    }

}
