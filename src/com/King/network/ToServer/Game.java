package com.King.network.ToServer;

import com.King.network.ToServerPacket;
import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;

/**
 * Created by wed on 11.02.16.
 */
public class Game extends ToServerPacket {

    String ServerId, lastChunk;
    long gameId;
    int state;

    public String getServerId() {
        return ServerId;
    }

    public String getLastChunk() {
        return lastChunk;
    }

    public long getGameId() {
        return gameId;
    }

    public int getState() {
        return state;
    }

    public void get(ByteBuf buffer) {

        gameId = buffer.readLong();

        int countBytes = buffer.readInt();
        byte[] b = new byte[countBytes];
        buffer.readBytes(b);

        ServerId = new String(b, Charset.forName("UTF-8"));
        state = buffer.readInt();

        countBytes = buffer.readInt();
        b = new byte[countBytes];
        buffer.readBytes(b);

        lastChunk = new String(b, Charset.forName("UTF-8"));
    }
}
