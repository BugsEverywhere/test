package com.siemens.allkindsoftest.ThreadTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.*;

/**
 * Created by Chen Zhuo on 2017/8/24.
 */
public class DelayQueueTest {

    public static void main(String[] args) throws InterruptedException {

        for(int i = 1;i<10;i++){
            DelayedTask delayedTask = new DelayedTask(i*1000L,i);
        }

        DelayQueue<DelayedTask> delayQueue = DelayedTask.getDelayedTskDelayQueue();

        //等所有delay任务都加入队列
        //TimeUnit.SECONDS.sleep(2);

        while(!Thread.interrupted()){

            System.out.println(delayQueue.take().toString());

        }
    }
}


class DelayedTask implements Delayed{

    static SimpleDateFormat sdf = new SimpleDateFormat("");
    static private DelayQueue<DelayedTask> delayedTskDelayQueue = new DelayQueue<DelayedTask>();
    private long delayHowLong;
    private long triggerTime;
    private int id;

    public DelayedTask(long dhl,int id){

        delayHowLong = dhl;
        triggerTime = Calendar.getInstance().getTimeInMillis()+dhl;
        this.id = id;
        delayedTskDelayQueue.add(this);

    }

    @Override
    public long getDelay(TimeUnit unit) {
        return triggerTime-Calendar.getInstance().getTimeInMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        //将过期时间最长（即triggerTime最小）的Delayed任务放在队列的head位置，
        // 还可以指定任意的顺序，将到期的Delay任务按照这种顺序排列，这样队列会判断出应当
        // 执行的任务的先后顺序，然后严格按照这个顺序开始执行所有任务，即使有些任务已经先
        //到期了。
        DelayedTask delayedTask = (DelayedTask)o;
        if(this.triggerTime-delayedTask.getTriggerTime()>0){
            return 1;
        }else if(this.triggerTime-delayedTask.getTriggerTime()==0){
            return 0;
        }else{
            return -1;
        }
    }

    public long getTriggerTime() {
        return triggerTime;
    }

    public String toString(){
        return "task "+id;
    }

    public static DelayQueue<DelayedTask> getDelayedTskDelayQueue() {
        return delayedTskDelayQueue;
    }

}

