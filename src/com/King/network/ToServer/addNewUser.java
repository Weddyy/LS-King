package com.King.network.ToServer;

import com.King.network.ToServerPacket;
import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;

/**
 * Created by wed on 19.02.16.
 */
public class addNewUser  extends ToServerPacket {

    String ServerId,name;
    long userId;

    public String getServerId() {
        return ServerId;
    }

    public String getName() {
        return name;
    }

    public long getUserId() {
        return userId;
    }


    public void get(ByteBuf buffer) {

        userId = buffer.readLong();

        int countBytes = buffer.readInt();
        byte[] b = new byte[countBytes];
        buffer.readBytes(b);

        ServerId = new String(b, Charset.forName("UTF-8"));

         countBytes = buffer.readInt();
        b = new byte[countBytes];
        buffer.readBytes(b);

        name = new String(b, Charset.forName("UTF-8"));
    }
}
