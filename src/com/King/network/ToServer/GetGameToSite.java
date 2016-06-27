package com.King.network.ToServer;

import com.King.network.ToServerPacket;
import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;

/**
 * Created by wed on 19.02.16.
 */
public class GetGameToSite extends ToServerPacket {

    boolean onlyOne;
    int gameId;

    public boolean isOnlyOne() {
        return onlyOne;
    }

    public int getGameId() {
        return gameId;
    }

    public void get(ByteBuf buffer) {

        gameId = buffer.readInt();
        onlyOne = buffer.readBoolean();
    }
}