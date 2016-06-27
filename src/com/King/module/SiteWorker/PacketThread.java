package com.King.module.SiteWorker;

import com.King.dao.GameBD;
import com.King.dao.PlayerBD;
import com.King.enums.ServerId;
import com.King.module.DataBase;
import com.King.module.Site;
import com.King.network.ToClient.toSite.putGame;
import com.King.network.ToServer.GetGameToSite;
import com.King.network.ToServer.addNewUser;
import com.King.network.ToServerPacket;

import javax.xml.crypto.Data;
import java.util.List;

/**
 * Created by wed on 19.02.16.
 */
public class PacketThread extends Thread {

    private Site site;
    public PacketThread(Site mon){
        site=mon;
    }
    private boolean isStart=true;

    public void Stop()
    {
        isStart=false;
    }

    @Override
    public void run()
    {
        ToServerPacket packet;
        while (isStart)
        {
            try {
                packet = site.getPacket();
                if (packet != null) {
                    if (packet instanceof GetGameToSite) {
                        GetGameToSite game=(GetGameToSite)packet;

                        if(game.isOnlyOne())
                        {
                           GameBD g = DataBase.Initialization().getGameById(game.getGameId());
                            if(g!=null)
                                site.getChanel().writeAndFlush(new putGame(g));
                        }else
                        {
                            List<GameBD> gameBDList=DataBase.Initialization().getAllGameById(game.getGameId());
                            long time=System.nanoTime();
                            int count=0;
                            for(GameBD gg:gameBDList)
                            {
                                site.getChanel().write(new putGame(gg));
                                count++;
                                if(count==15)
                                {
                                    count=0;
                                    site.getChanel().flush();
                                }
                            }
                            if(count!=0)
                            site.getChanel().flush();
                            System.out.print(System.nanoTime()-time);
                        }
                    }else if(packet instanceof addNewUser)
                    {
                        addNewUser user=(addNewUser)packet;
                        if(DataBase.Initialization().isPlayerExist(ServerId.valueOf(user.getServerId()),user.getUserId()))
                            continue;
                        PlayerBD pl=new PlayerBD();
                        pl.setSummonerId(user.getUserId());
                        pl.setName(user.getName());
                        pl.setServerId(user.getServerId());
                        DataBase.Initialization().addPlayer(pl);
                    }
                }else
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }catch (Exception e){e.printStackTrace();}
        }
    }
}
