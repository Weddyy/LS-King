package com.King.network.ToServer;

import com.King.network.ToServerPacket;
import io.netty.buffer.ByteBuf;

/**
 * Created by Wed on 10.02.16.
 */
public class Info extends ToServerPacket {

    public double tCPU_IdleTime,memUse,memFree,swapUsed,swapFree;
    public int countUsers;
    public long round,api,request;

    public void get(ByteBuf buffer) {
        tCPU_IdleTime=buffer.readDouble();
        memUse=buffer.readDouble();
        memFree=buffer.readDouble();
        swapUsed=buffer.readDouble();
        swapFree=buffer.readDouble();

        countUsers=buffer.readInt();

        round=buffer.readLong();
        api=buffer.readLong();
        request=buffer.readLong();
    }
}