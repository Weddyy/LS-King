package com.King.module;

import com.King.MainInformation;
import com.King.dao.GameBD;
import com.King.dao.GamePlayersBD;
import com.King.enums.ServerId;
import com.King.module.cameraWorker.PacketThread;
import com.King.network.ToClient.toCamera.addWork;
import com.King.network.ToClient.toCamera.removeWork;
import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wed on 11.02.16.
 */
public class Camera extends Client {

    Channel chanel;
    PacketThread packetThread;
    List<GameBD> _gameList=new ArrayList<>();

    public void addGame(GameBD game,boolean move)
    {
        _gameList.add(game);
        if(move)
        {
            ServerId servId = ServerId.valueOf(game.getServerId());
            for (GamePlayersBD p : game.getGamePlayersesById()) {
                Summoner sum = MainInformation.Initialize().getSummoner(p.getPlayerId(),servId);
                if (sum != null)
                    MainInformation.Initialize().movePlayerToCamera(sum);
            }
        }
        chanel.writeAndFlush(new addWork(game));
    }

    public void removeGame(GameBD game,boolean move){
        if(move)
        {
            ServerId servId = ServerId.valueOf(game.getServerId());
            for (GamePlayersBD p : game.getGamePlayersesById()) {
                Summoner sum = MainInformation.Initialize().getSummoner(p.getPlayerId(),servId);
                if (sum != null)
                    MainInformation.Initialize().movePlayerToNoState(sum);
            }
        }

        _gameList.remove(game);
        chanel.writeAndFlush(new removeWork(game));
    }


    public int getCountGame()
    {
        return _gameList.size();
    }

    public Camera(Channel c)
    {
        chanel=c;
        packetThread=new PacketThread(this);
        packetThread.start();
    }

    public void close()
    {
        if(packetThread!=null)
            packetThread.Stop();

        MainInformation.Initialize().getListCamera().remove(this);
        for(GameBD g:_gameList) {
            ServerId servId = ServerId.valueOf(g.getServerId());
            for (GamePlayersBD p : g.getGamePlayersesById()) {
                Summoner sum = MainInformation.Initialize().getSummoner(p.getPlayerId(),servId);
                if (sum != null)
                    MainInformation.Initialize().movePlayerToNoState(sum);
            }
        }
    }

    public Channel getChanel()
    {
        return chanel;
    }
}
