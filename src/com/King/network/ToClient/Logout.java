package com.King.network.ToClient;

import com.King.network.ToClientPacket;
import io.netty.buffer.ByteBuf;

/**
 * Created by Wed on 09.02.16.
 */
public class Logout extends ToClientPacket {

    public int getId() {
        return 2;
    }

    public void send(ByteBuf buffer) {
        buffer.writeBoolean(true);
    }
}
