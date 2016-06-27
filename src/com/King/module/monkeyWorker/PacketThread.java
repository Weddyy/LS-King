package com.King.module.monkeyWorker;

import com.King.MainInformation;
import com.King.dao.GameBD;
import com.King.dao.GamePlayersBD;
import com.King.enums.PlayersState;
import com.King.enums.ServerId;
import com.King.module.DataBase;
import com.King.module.Monkey;
import com.King.module.Summoner;
import com.King.module.cameraWorker.GamesThreads;
import com.King.network.ToServer.GameStart;
import com.King.network.ToServer.Info;
import com.King.network.ToServerPacket;

/**
 * Created by Wed on 09.02.16.
 */
public class PacketThread extends Thread {

    private Monkey _monkey;
    public PacketThread(Monkey mon){
        _monkey=mon;
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
                packet = _monkey.getPacket();
                if (packet != null) {
                    if (packet instanceof Info) {
                        Info inf = (Info) packet;
                        _monkey.getHWInfo().setCountUsers(inf.countUsers);
                        _monkey.getHWInfo().setCPU_IdleTime(inf.tCPU_IdleTime);
                        _monkey.getHWInfo().setSwap_used(inf.swapUsed);
                        _monkey.getHWInfo().setSwap_free(inf.swapFree);
                        _monkey.getHWInfo().setMem_free(inf.memFree);
                        _monkey.getHWInfo().setMem_used(inf.swapUsed);
                        _monkey.getHWInfo().setTimeForOneRound(inf.round);
                        _monkey.getHWInfo().setTimeLastRequestonAPI(inf.api);
                        _monkey.getHWInfo().setTimeLastRequestonInfo(inf.request);
                    } else if (packet instanceof GameStart)
                    {
                        GameBD g = ((GameStart) packet).getInfo();
                        ServerId servId = ServerId.valueOf(g.getServerId());

                        boolean isExist=false;

                        for (GamePlayersBD p : g.getGamePlayersesById()) {
                            if (p.getIsBot() == (byte) 0) {
                                Summoner s = MainInformation.Initialize().getSummoner(p.getPlayerId(), servId);
                                if (s != null) {
                                    if(!isExist)
                                        isExist=s.getState() != PlayersState.in_monkey;
                                    _monkey.removePlayer(s, false);
                                    MainInformation.Initialize().movePlayerToCamera(s);
                                }
                            }
                        }
                        if(!isExist) {
                            g.setFilePath("dropbox://LoLStone/");
                            DataBase.Initialization().addGame(g);
                            DataBase.Initialization().addBanChampionList(g);
                            DataBase.Initialization().addConfig(g);
                            DataBase.Initialization().addPlayerList(g);
                            GamesThreads.addGame(g);

                        }
                    }
                }else {Thread.sleep(50);}
            }catch (Exception e){e.printStackTrace();}
        }
    }
}
