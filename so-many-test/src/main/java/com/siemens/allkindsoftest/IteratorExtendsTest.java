package com.siemens.allkindsoftest;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Created by Chen Zhuo on 2017/10/9.
 */
public class IteratorExtendsTest implements Iterator {
    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Object next() {
        return null;
    }

    @Override
    public void remove() {

    }

    @Override
    public void forEachRemaining(Consumer action) {

    }
}
