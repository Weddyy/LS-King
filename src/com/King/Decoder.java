package com.King;

import com.King.network.ToServerPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * Created by Wed on 08.02.16.
 */
public class Decoder extends ByteToMessageDecoder {
    public Decoder() {
    }

    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        ToServerPacket t=null;
        if (msg.readableBytes() % 1024 == 0)
            return;
        if((t=ToServerPacket.read(msg))!=null)
        out.add(t);
    }
}
