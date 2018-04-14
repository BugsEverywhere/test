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
 * Created by Chen Zhuo on 2017/10/18.
 */
public class CallbackServer
{
    public void run() throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress("127.0.0.1", 8000));

        Selector selector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            Iterator ite = selector.selectedKeys().iterator();

            while (ite.hasNext()) {
                SelectionKey key = (SelectionKey)ite.next();

                if (key.isAcceptable()) {
                    ServerSocketChannel s = (ServerSocketChannel)key.channel();
                    SocketChannel clientSocket = s.accept();
                    System.out.println("Got a new Connection");

                    clientSocket.configureBlocking(false);

                    SelectionKey newKey = clientSocket.register(selector, SelectionKey.OP_WRITE);

                    CommonClient client = new CommonClient(clientSocket, newKey);
                    newKey.attach(client);

                    System.out.println("client waiting");
                }
                else if (key.isReadable()) {
                    CommonClient client = (CommonClient) key.attachment();
                    client.onRead();
                }
                else if (key.isWritable()) {
                    CommonClient client = (CommonClient) key.attachment();
                    client.onWrite();
                }

                ite.remove();
            }
        }
    }

    public static void main( String[] args ) throws Exception
    {
        CallbackServer server = new CallbackServer();
        server.run();
    }
}


//该类封装了所有的响应动作，包括读、写、异步发送消息
class CommonClient {
    private SocketChannel clientSocket;
    private ByteBuffer recvBuffer;
    private SelectionKey key;
    //这里持有一个Callback接口的引用，后头每次都会更新这个引用中方法的实现，以满足不同的回调
    private Callback callback;

    private String msg;


    public CommonClient(SocketChannel clientSocket, SelectionKey key) {
        this.clientSocket = clientSocket;
        this.key = key;
        recvBuffer = ByteBuffer.allocate(8);

        try {
            this.clientSocket.configureBlocking(false);
            //这里构造方法结束的时候会触发一个写事件！所以会进入一次onWrite()方法
            //在该方法中调用了sendMessage()方法会第一次初始化持有的Callback接口，
            //这是关键！这样在onRead()结束的时候调用onSucceed()方法，才能正确使用
            //所需要的实现。
            key.interestOps(SelectionKey.OP_WRITE);
        } catch (IOException e) {
        }
    }

    public void close() {
        try {
            clientSocket.close();
            key.cancel();
        }
        catch (IOException e){};
    }

    // an rpc to notify client to send a number
    public void sendMessage(String msg, Callback cback)  {
        this.callback = cback;

        try {
            try {
                recvBuffer.clear();
                recvBuffer.put(msg.getBytes());
                recvBuffer.flip();
                clientSocket.write(recvBuffer);

                key.interestOps(SelectionKey.OP_READ);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (Exception e) {
        }
    }

    // when key is writable, resume the fiber to continue
    // to write.
    //在此方法中三次递归地调用了sendMessage()方法，每次都是为了用来初始
    //化该对象中的Callback接口引用，也就是为onSucceed()方法提供实现，
    //第一次是在CommonClient对象的构造方法里
    //调用，导致读的时候也就是在onRead()方法里调用的onSucceed()是第一层实现，完成存储
    //方法会使用这里的第一层
    public void onWrite() {
        sendMessage("divident", new Callback() {
            @Override
            public void onSucceed(int data) {
                int a = data;
                sendMessage("divisor", new Callback() {
                    @Override
                    public void onSucceed(int data) {
                        int b = data;

                        sendMessage(String.valueOf(a / b), null);
                    }
                });
            }
        });
    }

    public void onRead() {
        int res = 0;
        try {
            try {
                recvBuffer.clear();

                // read may fail even SelectionKey is readable
                // when read fails, the fiber should suspend, waiting for next
                // time the key is ready.
                int n = clientSocket.read(recvBuffer);
                while (n == 0) {
                    n = clientSocket.read(recvBuffer);
                }

                if (n == -1) {
                    close();
                    return;
                }

                System.out.println("received " + n + " bytes from client");
            } catch (IOException e) {
                e.printStackTrace();
            }

            recvBuffer.flip();
            res = getInt(recvBuffer);

            // when read ends, we are no longer interested in reading,
            // but in writing.
            key.interestOps(SelectionKey.OP_WRITE);
        } catch (Exception e) {
        }

        this.callback.onSucceed(res);
    }

    public int getInt(ByteBuffer buf) {
        int r = 0;
        while (buf.hasRemaining()) {
            r *= 10;
            r += buf.get() - '0';
        }

        return r;
    }
}

interface Callback {
    public void onSucceed(int data);
}
