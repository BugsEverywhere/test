package com.siemens.allkindsoftest.ThreadTest;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by Chen Zhuo on 2017/8/25.
 */
public class PriorityBlockingQueueTest {

    private static PriorityBlockingQueue<PriorityTask> ptb =
            new PriorityBlockingQueue<PriorityTask>();

    public static void main(String[] args) throws InterruptedException {

        for(int i = 1;i <= 10;i++){

            ptb.add(new PriorityTask(i));

        }

        while (!Thread.interrupted()){

            System.out.println(ptb.take().toString());

        }
    }
}


class PriorityTask implements Comparable<PriorityTask>{

    private int priority;

    public PriorityTask(int p){

        this.priority = p;

    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(PriorityTask o) {

        if(this.priority>o.getPriority()){

            return -1;

        }else if(this.priority==o.getPriority()){

            return 0;

        }else{

            return 1;

        }
    }

    public String toString(){

        return String.valueOf(this.priority);
    }
}