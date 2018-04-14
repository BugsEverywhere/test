package com.siemens.allkindsoftest.rpcTest;

import java.lang.reflect.Proxy;

/**
 * Created by Chen Zhuo on 2017/10/31.
 */
public class Caller {
    public static void main(String args[]) {
        //客户端只管调用接口中的方法，其具体实现在服务端
        //当然这也是通过动态代理来完成的
        EchoService echo = (EchoService) Proxy.newProxyInstance(EchoService.class.getClassLoader(),
                new Class<?>[]{EchoService.class}, new DynamicProxyHandler());

        for (int i = 0; i < 3; i++) {
            System.out.println(echo.echo(String.valueOf(i)));
        }
    }
}
