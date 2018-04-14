package com.siemens.allkindsoftest.niotest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by Chen Zhuo on 2017/9/6.
 */
public class SelectorServer1 {

    private static final int BUF_SIZE=1024;
    private static final int PORT = 8080;
    private static final int TIMEOUT = 3000;


    public static void main(String[] args)
    {
        selector();
    }

    //每次的接收事件都在这里处理，处理方法是新建一个SocketChannel专门与该客户端连接，然后
    //登记到selector，关心的key设为IO读事件，之后由selector控制对每个channel上发生的IO事件的处理
    //注意此处注册的是由ServerSocketChannel接收连接而返回的SocketChannel，这种通过key来获取
    //SocketChannel的方式必须要这么写，即通过key返回之前定义的负责监听的ServerSocketChannel对象引用，然后accept一下
    public static void handleAccept(SelectionKey key,ServerSocketChannel ssc) throws IOException{
        //调用key.channel()返回的是ServerSocketChannel是因为，接收连接事件发生在
        //这个监听Channel上。反正key.channel()返回的永远是发生这个事件的channel（）
        ServerSocketChannel ssChannel = (ServerSocketChannel)key.channel();
        //这里accept()不会阻塞，因为前面设置了configureBlocking(false)
        SocketChannel sc = ssChannel.accept();
        sc.configureBlocking(false);
        sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocateDirect(BUF_SIZE));
    }

    public static void handleRead(SelectionKey key) throws IOException{
        //那么在这里，因为客户端写了什么东西导致服务端的这个SocketChannel发生了可读事件，因而
        //产生了一个可读事件的key，注意是这个SocketChannel不是别人，所以在这里调用key.channel()
        //就会返回发生这个事件的Channel，也就是这个SocketChannel，而不是别的Channel
        SocketChannel sc = (SocketChannel)key.channel();
        ByteBuffer buf = (ByteBuffer)key.attachment();
        //这里read()不会阻塞，因为前面设置了configureBlocking(false)
        long bytesRead = sc.read(buf);
        while(bytesRead>0){
            buf.flip();
            while(buf.hasRemaining()){
                System.out.print((char)buf.get());
            }
            System.out.println();
            buf.clear();
            bytesRead = sc.read(buf);
        }
        if(bytesRead == -1){
            sc.close();
        }
    }

    public static void handleWrite(SelectionKey key) throws IOException{
        ByteBuffer buf = (ByteBuffer)key.attachment();
        buf.flip();
        SocketChannel sc = (SocketChannel) key.channel();
        while(buf.hasRemaining()){
            sc.write(buf);
        }
        buf.compact();
    }

    public static void selector() {
        Selector selector = null;
        //这是用于监听的channel，之后对每一次事件新来的接收事件，会建立新的一个连接
        ServerSocketChannel ssc = null;
        try{
            selector = Selector.open();
            ssc= ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress(PORT));
            //如果需要使用selector，则必须将channel设为非阻塞，不然会报错
            ssc.configureBlocking(false);
            //将channel注册到selector，这里这个key的意思是所关心的IO事件，不同的key表示
            //不同的IO事件，这里的这个叫做OP_ACCEPT的key表示是接收事件
            //调用这个注册方法会返回这个key，但是这里握有key并没有什么卵用，主要是在后面有用
            ssc.register(selector, SelectionKey.OP_ACCEPT);

            while(true){
                //在此阻塞TIMEOUT秒，然后while循环反复调用select方法
                if(selector.select(TIMEOUT) == 0){
                    System.out.println("==");
                    continue;
                }
                //一旦select方法返回非零值，值大小代表上一次调用select方法到本次之间通道事件响应的个数
                //则使用此方法遍历selector中产生的key，然后对每一个key调用其中的通道来完成相关动作
                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                while(iter.hasNext()){
                    SelectionKey key = iter.next();
                    if(key.isAcceptable()){
                        handleAccept(key,ssc);
                    }
                    if(key.isReadable()){
                        handleRead(key);
                    }
                    if(key.isWritable() && key.isValid()){
                        handleWrite(key);
                    }
                    if(key.isConnectable()){
                        System.out.println("isConnectable = true");
                    }
                    //处理完这个key要删除
                    iter.remove();
                }
            }

        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(selector!=null){
                    selector.close();
                }
                if(ssc!=null){
                    ssc.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
