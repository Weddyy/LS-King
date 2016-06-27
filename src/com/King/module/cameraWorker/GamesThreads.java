package com.King.module.cameraWorker;

import com.King.MainInformation;
import com.King.dao.GameBD;
import com.King.module.Camera;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by wed on 11.02.16.
 */
public class GamesThreads extends Thread{

    static List<GameBD> gameList=new CopyOnWriteArrayList<>();

    public static void addGame(GameBD g)
    {
        gameList.add(g);
    }
    @Override
    public void run()
    {
        final Comparator<Camera> comp = (p1, p2) -> Integer.compare( p1.getCountGame(), p2.getCountGame());

        while (true)
        {
            if(MainInformation.Initialize().getListCamera().size()!=0) {
                if (gameList.size() > 0) {
                    for (GameBD g : gameList) {
                        Camera c = MainInformation.Initialize().getListCamera().stream().min(comp).get();
                        c.addGame(g,true);
                        gameList.remove(g);
                    }
                }
            }
            try {
                Thread.sleep(20000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
