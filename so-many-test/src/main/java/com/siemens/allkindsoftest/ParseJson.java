package com.siemens.allkindsoftest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.io.*;

/**
 * Created by Simons on 2017/5/11.
 */
public class ParseJson {

    public static void main(String[] args) throws IOException {

        File file = new File("C:\\Users\\Simons\\Desktop\\jsonFiles\\json.json");

        BufferedReader reader = new BufferedReader(new FileReader(file));

        String currentLine;

        String jsonString = "";

        while((currentLine = reader.readLine())!=null){

            jsonString = jsonString+currentLine;

        }

        JSONArray jsonArray = JSON.parseArray(jsonString);

        reader.close();

        System.out.println(jsonArray.size());

    }
}
