package com.King.network.ToClient.toCamera;

import com.King.dao.GameBD;
import com.King.module.Summoner;
import com.King.network.ToClientPacket;
import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wed on 09.02.16.
 */
public class addWork extends ToClientPacket {

    GameBD _game;
    public addWork(GameBD game)
    {
        _game=game;
    }

    public int getId() {
        return 4;
    }

    public void send(ByteBuf buffer) {
        buffer.writeInt(_game.getServerId().getBytes().length);
        buffer.writeBytes(_game.getServerId().getBytes(Charset.forName("UTF-8")));

        buffer.writeLong(_game.getIdGame());

        buffer.writeInt(_game.getFilePath().getBytes().length);
        buffer.writeBytes(_game.getFilePath().getBytes(Charset.forName("UTF-8")));
    }
}