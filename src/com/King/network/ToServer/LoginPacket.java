package com.King.network.ToServer;

import com.King.enums.PacketToServer;
import com.King.network.ToServerPacket;
import io.netty.buffer.ByteBuf;

/**
 * Created by Wed on 08.02.16.
 */
public class LoginPacket extends ToServerPacket {

    public Short index;

    public void get(ByteBuf buffer) {
        index = buffer.readShort();
    }

    public PacketToServer getType()
    {
        return PacketToServer.LOGIN;
    }
}