package com.King.module;

import com.King.MainInformation;
import com.King.module.monkeyWorker.PacketThread;
import com.King.network.ToClient.toMonkey.addWork;
import com.King.network.ToClient.toMonkey.removeWork;
import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wed on 09.02.16.
 */
public class Monkey extends Client {

    Channel chanel;
    PacketThread packetThread;
    List<Summoner> playerList=new ArrayList<>();

    public void addSummoners(List<Summoner> plist)
    {
        for (Summoner p:plist) {
            MainInformation.Initialize().movePlayerToMonkey(p);
            playerList.add(p);
        }
        chanel.writeAndFlush(new addWork(plist));
    }

    public void removePlayers(List<Summoner> plist,boolean move){
        for (Summoner p:plist) {
            if(move)
                MainInformation.Initialize().movePlayerToNoState(p);
            playerList.remove(p);
        }
        chanel.writeAndFlush(new removeWork(plist));
    }

    public void removePlayer(Summoner sum,boolean move){
        if(move)
            MainInformation.Initialize().movePlayerToNoState(sum);
            playerList.remove(sum);

        chanel.writeAndFlush(new removeWork(sum));
    }

    public int getCountPlayers()
    {
        return playerList.size();
    }

    public void removeSomePPl(int count)
    {
        removePlayers(playerList.subList(playerList.size()-count,playerList.size()),true);
    }

    public Monkey(Channel c)
    {
        chanel=c;
        packetThread=new PacketThread(this);
        packetThread.start();
        //MainInformation.Initialize().getMonkeyList().add(this);
    }

    public void close()
    {
        if(packetThread!=null)
            packetThread.Stop();

        MainInformation.Initialize().getMonkeyList().remove(this);
        for(Summoner p:playerList)
            MainInformation.Initialize().movePlayerToNoState(p);
    }

    public Channel getChanel()
    {
        return chanel;
    }
}
