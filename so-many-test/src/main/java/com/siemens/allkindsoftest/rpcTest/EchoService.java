package com.siemens.allkindsoftest.rpcTest;

/**
 * Created by Chen Zhuo on 2017/10/31.
 */
public interface EchoService {
    String echo(String request);
}


class EchoServiceImpl implements EchoService {
    public String echo(String request) {
        return "echo : " + request;
    }
}