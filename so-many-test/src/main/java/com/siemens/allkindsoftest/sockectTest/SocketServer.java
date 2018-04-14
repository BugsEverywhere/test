package com.siemens.allkindsoftest.sockectTest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Chen Zhuo on 2017/10/7.
 */
public class SocketServer {

    public static void main(String args[]) throws IOException {
        ServerSocket ss = new ServerSocket(9001);
        Socket conn = ss.accept();//阻塞，直到有新的client请求连接，返回一个Socket
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        String s = br.readLine();//阻塞，直到读到client往流中写入的数据
        while (s != null) {
            System.out.println(s);
            bw.write(s.toUpperCase() + "\n");
            bw.flush();
            s = br.readLine();//阻塞，直到读到client往流中写入的数据
        }

        br.close();
        bw.close();
        conn.close();
    }
}