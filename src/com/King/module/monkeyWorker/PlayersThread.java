package com.King.module.monkeyWorker;

import com.King.MainInformation;
import com.King.module.Monkey;
import com.King.module.Summoner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Wed on 09.02.16.
 */
public class PlayersThread extends Thread {

    static PlayersThread thisThread = null;

    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
            }
            if(MainInformation.Initialize().getMonkeyList().size()==0)
                continue;

            int average = BalanceMonkey();
            if(MainInformation.Initialize().getPlayersOnNonState().size()!=0)
            {
                List<Summoner> nonState=new ArrayList<>();
                nonState.addAll(MainInformation.Initialize().getPlayersOnNonState());
                for (Monkey m :MainInformation.Initialize().getMonkeyList())
                {
                    if(nonState.size()==0)
                        break;
                    int count=average - m.getCountPlayers();
                    if(count>0)
                    {
                        m.addSummoners(nonState.subList(0,(count>nonState.size())?count:nonState.size()));
                        nonState.removeAll(nonState.subList(0,(count>nonState.size())?count:nonState.size()));
                    }
                }
            }
        }
    }
    private int BalanceMonkey()
    {
        int Average = Math.round((MainInformation.Initialize().getAllPlayers().size() - MainInformation.Initialize().getPlayersOnCamera().size())/ MainInformation.Initialize().getMonkeyList().size());
        int count;
        for (Monkey m :MainInformation.Initialize().getMonkeyList())
        {
            if((count = m.getCountPlayers()-Average)>0)
            {
                m.removeSomePPl(count);
            }
        }


        return Average;
    }

    public static void Go() {
        if (thisThread == null) {
            thisThread = new PlayersThread();
            thisThread.start();

            System.out.println("Thread Monitoring Players Start");
        }
    }

    public static PlayersThread getThread() {
        return thisThread;
    }
}