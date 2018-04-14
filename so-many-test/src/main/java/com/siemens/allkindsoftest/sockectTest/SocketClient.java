package com.siemens.allkindsoftest.sockectTest;

import java.io.*;
import java.net.Socket;

/**
 * Created by Chen Zhuo on 2017/10/7.
 */
public class SocketClient {

    public static void main(String args[]) throws IOException {
        Socket conn = new Socket("127.0.0.1", 9001);
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        bw.write("hello\n");
        bw.flush();
        String s = br.readLine();//阻塞，直到读到server往流中写入的数据
        System.out.println(s);

        bw.write("world\n");
        bw.flush();
        s = br.readLine();//阻塞，直到读到server往流中写入的数据
        System.out.println(s);


        br.close();
        bw.close();
        conn.close();

    }
}
