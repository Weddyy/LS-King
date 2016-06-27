package com.King.network.ToClient.STS;

import com.King.dao.GameBD;
import com.King.module.Summoner;
import com.King.network.ToClientPacket;
import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;

/**
 * Created by wed on 12.02.16.
 */
public class sendGame extends ToClientPacket {

    GameBD _game;
    long id=0;
    String serverId;

    public int getId() {
        return 2;
    }

    public sendGame(GameBD g)
    {
        _game=g;
    }

    public sendGame(long i,String ser)
    {
        id=i;
        serverId=ser;
    }

    public void send(ByteBuf buffer) {

        if(id!=0)
        {
            buffer.writeLong(id);

            buffer.writeInt(serverId.getBytes().length);
            buffer.writeBytes(serverId.getBytes(Charset.forName("UTF-8")));
            buffer.writeBoolean(false);
            return;
        }

        buffer.writeLong(_game.getIdGame());

        buffer.writeInt(_game.getServerId().getBytes().length);
        buffer.writeBytes(_game.getServerId().getBytes(Charset.forName("UTF-8")));
        buffer.writeBoolean(true);

        buffer.writeInt(_game.getFilePath().getBytes().length);
        buffer.writeBytes(_game.getFilePath().getBytes(Charset.forName("UTF-8")));

        buffer.writeInt(_game.getLastChangInfo().getBytes().length);
        buffer.writeBytes(_game.getLastChangInfo().getBytes(Charset.forName("UTF-8")));
    }

}
