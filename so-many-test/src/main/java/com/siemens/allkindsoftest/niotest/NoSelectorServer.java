package com.siemens.allkindsoftest.niotest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by Chen Zhuo on 2017/10/18.
 */
public class NoSelectorServer {
    //这个Server是NIO中普通的用法，也就是不用Selector。因此在socketChannel.read(readBuffer)处
    //会发生阻塞，这种用法跟JAVA IO就没有什么区别了，完全没有发挥出NIO非阻塞的特点
    public static void main(String args[]) {
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress("127.0.0.1", 8000));
            SocketChannel socketChannel = ssc.accept();

            ByteBuffer readBuffer = ByteBuffer.allocate(128);
            socketChannel.read(readBuffer);

            readBuffer.flip();
            while (readBuffer.hasRemaining()) {
                System.out.println((char)readBuffer.get());
            }

            socketChannel.close();
            ssc.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
