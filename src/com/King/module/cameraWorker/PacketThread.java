package com.King.module.cameraWorker;

import com.King.MainInformation;
import com.King.dao.GameBD;
import com.King.dao.GamePlayersBD;
import com.King.enums.PlayersState;
import com.King.enums.ServerId;
import com.King.module.Camera;
import com.King.module.DataBase;
import com.King.module.Summoner;
import com.King.network.ToServer.Game;
import com.King.network.ToServer.Info;
import com.King.network.ToServerPacket;

import javax.xml.crypto.Data;

/**
 * Created by Wed on 09.02.16.
 */
public class PacketThread extends Thread {

    private Camera _camera;
    public PacketThread(Camera mon){
        _camera=mon;
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
                packet = _camera.getPacket();
                if (packet != null) {
                    if (packet instanceof Info) {
                        Info inf = (Info) packet;
                        _camera.getHWInfo().setCountUsers(inf.countUsers);
                        _camera.getHWInfo().setCPU_IdleTime(inf.tCPU_IdleTime);
                        _camera.getHWInfo().setSwap_used(inf.swapUsed);
                        _camera.getHWInfo().setSwap_free(inf.swapFree);
                        _camera.getHWInfo().setMem_free(inf.memFree);
                        _camera.getHWInfo().setMem_used(inf.swapUsed);
                        _camera.getHWInfo().setTimeForOneRound(inf.round);
                        _camera.getHWInfo().setTimeLastRequestonAPI(inf.api);
                        _camera.getHWInfo().setTimeLastRequestonInfo(inf.request);
                    } else if (packet instanceof Game) {
                        Game g = ((Game) packet);
                        GameBD b= DataBase.Initialization().getGameByGameId(g.getGameId(),ServerId.valueOf(g.getServerId()));
                        if(b==null)
                        {
                            System.out.println("ERROR : Game not found ( game ID : "+g.getGameId()+" server ID : "+g.getServerId());
                            continue;
                        }
                        b.setLastChangInfo(g.getLastChunk());
                        DataBase.Initialization().addGame(b);
                        _camera.removeGame(b,true);
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
