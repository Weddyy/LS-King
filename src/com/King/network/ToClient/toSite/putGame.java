package com.King.network.ToClient.toSite;

import com.King.dao.GameBD;
import com.King.dao.GameConfigBD;
import com.King.dao.GamePlayersBD;
import com.King.module.Summoner;
import com.King.network.ToClientPacket;
import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wed on 19.02.16.
 */
public class putGame extends ToClientPacket {

    GameBD _game;
    public putGame(GameBD game)
    {
        _game=game;
    }

    public int getId() {
        return 11;
    }

    public void send(ByteBuf buffer) {

        buffer.writeInt(_game.getId());

        buffer.writeLong(_game.getIdGame());

        buffer.writeInt(_game.getServerId().getBytes().length);
        buffer.writeBytes(_game.getServerId().getBytes(Charset.forName("UTF-8")));

        boolean dis=_game.getLastChangInfo()==null || _game.getLastChangInfo().equals("");
        buffer.writeBoolean(dis);

        if(dis)
            return;

        buffer.writeInt(_game.getCryptKey().getBytes().length);
        buffer.writeBytes(_game.getCryptKey().getBytes(Charset.forName("UTF-8")));

        GameConfigBD conf=_game.getGameConfigsById().iterator().next();
        buffer.writeInt(conf.getGameMode().getBytes().length);
        buffer.writeBytes(conf.getGameMode().getBytes(Charset.forName("UTF-8")));

        buffer.writeInt(conf.getGameType().getBytes().length);
        buffer.writeBytes(conf.getGameType().getBytes(Charset.forName("UTF-8")));

        buffer.writeLong(conf.getGameDate().getTime());

        buffer.writeLong(conf.getMapId());

        buffer.writeInt(_game.getGamePlayersesById().size());

        Iterator<GamePlayersBD> in=_game.getGamePlayersesById().iterator();
        while (in.hasNext()) {
            GamePlayersBD g=in.next();

            buffer.writeInt(g.getName().getBytes().length);
            buffer.writeBytes(g.getName().getBytes(Charset.forName("UTF-8")));

            buffer.writeInt(g.getRune().getBytes().length);
            buffer.writeBytes(g.getRune().getBytes(Charset.forName("UTF-8")));

            buffer.writeInt(g.getMastery().getBytes().length);
            buffer.writeBytes(g.getMastery().getBytes(Charset.forName("UTF-8")));

            buffer.writeLong(g.getChampionId());

            buffer.writeLong(g.getPlayerId());

            buffer.writeLong(g.getSpell1D());

            buffer.writeLong(g.getSpell2D());

            buffer.writeLong(g.getTeadId());

        }

    }
}