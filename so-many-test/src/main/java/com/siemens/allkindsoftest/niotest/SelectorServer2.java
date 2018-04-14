package com.siemens.allkindsoftest.niotest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Chen Zhuo on 2017/10/17.
 */
public class SelectorServer2 {
    public static void main(String[] args) {
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress("127.0.0.1", 8080));
            //再次强调必须有这一步，不然会报错
            ssc.configureBlocking(false);

            Selector selector = Selector.open();
            // 注册 channel，并且指定感兴趣的事件是 Accept
            ssc.register(selector, SelectionKey.OP_ACCEPT);

            ByteBuffer readBuff = ByteBuffer.allocate(1024);
            ByteBuffer writeBuff = ByteBuffer.allocate(128);
            writeBuff.put("received".getBytes());
            writeBuff.flip();

            while (true) {
                int nReady = selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();

                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();

                    if (key.isAcceptable()) {
                        // 创建新的连接，并且把连接注册到selector上，而且，
                        // 声明这个channel只对读操作感兴趣。
                        SocketChannel socketChannel = ssc.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    }
                    else if (key.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        readBuff.clear();
                        socketChannel.read(readBuff);

                        readBuff.flip();
                        System.out.println("received : " + new String(readBuff.array()));
                        //读完之后在这里把key设置为对写事件感兴趣，这样在下一次select()时
                        //就自动有一个writable的key了，不用去监听Socket等待,因而在遍历所有返回
                        //的key的时候会进入写分支。
                        //由此可见，key的可写状态不是由远端客户端发起read而出现的，完全是服务器
                        //端自己设置的，只要这里设置了，那下一次调用select()不阻塞就能返回一个
                        //用于写的key。
                        //因此可以说服务端的写动作是由服务端自己控制的。
                        //且客户端调用read不会导致服务端出现任何事件，如果此时服务端不主动写，双方就都阻塞
                        //了，服务端阻塞在select()，客户端阻塞在read()
                        key.interestOps(SelectionKey.OP_WRITE);
                    }
                    else if (key.isWritable()) {
                        writeBuff.rewind();
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        socketChannel.write(writeBuff);
                        //同样在写完之后，将key设置为对读感兴趣，但是调用select()不会马上有
                        //readable的key，必须要等客户端写了，这边的select()方法才返回
                        key.interestOps(SelectionKey.OP_READ);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
