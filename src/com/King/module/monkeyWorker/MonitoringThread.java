package com.King.module.monkeyWorker;


import com.King.MainInformation;
import com.King.module.Monkey;
import com.King.network.ToClient.Info;

import java.util.concurrent.TimeUnit;

/**
 * Created by Wed on 09.02.16.
 */
public class MonitoringThread extends Thread{

    static MonitoringThread thisThread=null;

    @Override
    public void run()
    {
        while (true)
        {
            try{
                TimeUnit.MINUTES.sleep(10);
            }catch (InterruptedException e){}

            for(Monkey m: MainInformation.Initialize().getMonkeyList())
            {
                m.getChanel().writeAndFlush(new Info());
            }
        }
    }

    public static void Go()
    {
        if(thisThread==null) {
            thisThread=new MonitoringThread();
            thisThread.start();

            System.out.println("Thread Monitoring Start");
        }
    }

    public static MonitoringThread getThread()
    {
        return thisThread;
    }
}
