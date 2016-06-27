package com.King.network.ToClient.toCamera;

import com.King.network.ToClientPacket;
import io.netty.buffer.ByteBuf;

/**
 * Created by Wed on 09.02.16.
 */
public class stopWork extends ToClientPacket {

    public int getId() {
        return 6;
    }

    public void send(ByteBuf buffer) {
        buffer.writeByte(0);
    }
}