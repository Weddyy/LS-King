package com.King.network.ToServer;

import com.King.network.ToServerPacket;
import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;

/**
 * Created by wed on 12.02.16.
 */
public class GetGame extends ToServerPacket {

    String ServerId;
    long gameId;

    public String getServerId() {
        return ServerId;
    }


    public long getGameId() {
        return gameId;
    }


    public void get(ByteBuf buffer) {

        gameId = buffer.readLong();

        int countBytes = buffer.readInt();
        byte[] b = new byte[countBytes];
        buffer.readBytes(b);

        ServerId = new String(b, Charset.forName("UTF-8"));
    }
}