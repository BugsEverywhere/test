package com.siemens.allkindsoftest.rpcTest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by Chen Zhuo on 2017/10/31.
 */
public class DynamicProxyHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Socket s = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        try {
            s = new Socket();
            s.connect(new InetSocketAddress("localhost", 8081));
            oos = new ObjectOutputStream(s.getOutputStream());
            ois = new ObjectInputStream(s.getInputStream());

            //在这里指定具体实现类的名字，然后是写入想要调用的方法的名字，以及参数
            oos.writeUTF("com.siemens.allkindsoftest.rpcTest.EchoServiceImpl");
            oos.writeUTF(method.getName());
            oos.writeObject(method.getParameterTypes());
            oos.writeObject(args);

            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (s != null)
                s.close();

            if (ois != null)
                ois.close();

            if (oos != null)
                oos.close();
        }
        return null;
    }
}
