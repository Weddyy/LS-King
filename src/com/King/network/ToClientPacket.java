package com.King.network;

import io.netty.buffer.ByteBuf;

import java.io.IOException;

/**
 * Created by Wed on 09.02.16.
 */
public abstract class ToClientPacket
{
    public static void write(ToClientPacket packet, ByteBuf buffer) {
        buffer.writeChar(packet.getId()); // Отправляем ID пакета
        packet.send(buffer); // Отправляем данные пакета
    }

    public abstract void send(ByteBuf buffer);
    public abstract int getId();
}
