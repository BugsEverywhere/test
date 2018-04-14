package com.siemens.allkindsoftest;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * Created by Simons on 2017/5/30.
 */
public class TestStringFinal {

     public static void main(String[] args ){

         String str = new String("hello");

         ReferenceQueue<String> rq = new ReferenceQueue<String>();

         //WeakReference<String> wf = new WeakReference<String>(str, rq);

         SoftReference<String> wf = new SoftReference<String>(str,rq);

         str=null;

         String str1=wf.get();

         Reference<? extends String> ref=rq.poll();

         System.out.println();


     }



}
