package com.King.network.ToClient;

import com.King.network.ToClientPacket;
import io.netty.buffer.ByteBuf;

/**
 * Created by Wed on 08.02.16.
 */
public class LoginPacket extends ToClientPacket {

    public int getId() {
        return 1;
    }

    public void send(ByteBuf buffer) {
        buffer.writeBoolean(true);
    }
}