package com.King.module.StsWorker;

import com.King.dao.GameBD;
import com.King.enums.ServerId;
import com.King.module.Camera;
import com.King.module.DataBase;
import com.King.module.STS;
import com.King.network.ToClient.STS.sendGame;
import com.King.network.ToServer.Game;
import com.King.network.ToServer.GetGame;
import com.King.network.ToServer.Info;
import com.King.network.ToServerPacket;

/**
 * Created by Wed on 09.02.16.
 */
public class PacketThread extends Thread {

    private STS speack;
    public PacketThread(STS mon){
        speack=mon;
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
                packet = speack.getPacket();
                if (packet != null) {
                    if (packet instanceof Info) {
                        Info inf = (Info) packet;
                        speack.getHWInfo().setCountUsers(inf.countUsers);
                        speack.getHWInfo().setCPU_IdleTime(inf.tCPU_IdleTime);
                        speack.getHWInfo().setSwap_used(inf.swapUsed);
                        speack.getHWInfo().setSwap_free(inf.swapFree);
                        speack.getHWInfo().setMem_free(inf.memFree);
                        speack.getHWInfo().setMem_used(inf.swapUsed);
                        speack.getHWInfo().setTimeForOneRound(inf.round);
                        speack.getHWInfo().setTimeLastRequestonAPI(inf.api);
                        speack.getHWInfo().setTimeLastRequestonInfo(inf.request);
                    } else if (packet instanceof GetGame) {
                        GetGame g = ((GetGame) packet);
                        GameBD b= DataBase.Initialization().getGameByGameIdWheeEncrypt(g.getGameId(),ServerId.valueOf(g.getServerId()));
                        if(b==null)
                            speack.getChanel().writeAndFlush(new sendGame(g.getGameId(),g.getServerId()));
                        else
                            speack.getChanel().writeAndFlush(new sendGame(b));
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
