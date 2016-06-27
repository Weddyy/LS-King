package com.King;

import com.King.network.ToClientPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.List;

/**
 * Created by Wed on 08.02.16.
 */
public class Encoder extends MessageToByteEncoder<Object> {
    public Encoder() {
    }

    protected void encode(ChannelHandlerContext ctx, Object msg,ByteBuf buffer) throws Exception {
        if(msg instanceof ToClientPacket) {
            //ByteBuf buffer = Unpooled.buffer(); // Создаём динамический буфер для записи в него данных из пакета. Если Вы точно знаете длину пакета, Вам не обязательно использовать динамический буфер — ChannelBuffers предоставляет и буферы фиксированной длинны, они могут быть эффективнее.
            ToClientPacket.write((ToClientPacket) msg, buffer);
            //out.add(buffer);
        }
    }
}