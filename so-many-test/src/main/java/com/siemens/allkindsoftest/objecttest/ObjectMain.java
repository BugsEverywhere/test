package com.siemens.allkindsoftest.objecttest;

import com.sun.xml.internal.ws.api.Cancelable;
import org.apache.http.concurrent.Cancellable;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class ObjectMain {

    public static void main(String[] args) {

        AtomicReference<Cancellable> cancellableRef = new AtomicReference((Object)null);

        System.out.println(cancellableRef.toString());

    }
}
