package com.siemens.allkindsoftest.httpClientTest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class HttpClientTestMain {

    private static MultiThreadedHttpConnectionManager connectionManager;

    public static void main(String[] args) {

        connectionManager = new MultiThreadedHttpConnectionManager();

        HttpClient client = new HttpClient(connectionManager);






    }
}
