package com.King.network.ToClient.toSite;

import com.King.module.STS;
import com.King.network.ToClientPacket;
import io.netty.buffer.ByteBuf;

/**
 * Created by wed on 08.03.16.
 */
public class STSInfo extends ToClientPacket {

    public int getId() {
        return 13;
    }

    public void send(ByteBuf buffer) {
        buffer.writeBoolean(STS.Initializ()!=null);
    }
}
