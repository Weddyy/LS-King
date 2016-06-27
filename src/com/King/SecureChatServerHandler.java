package com.King;

import com.King.module.*;
import com.King.network.ToClient.toSite.STSInfo;
import com.King.network.ToServer.LoginPacket;
import com.King.network.ToServerPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by Wed on 08.02.16.
 */
 public class SecureChatServerHandler extends SimpleChannelInboundHandler<ToServerPacket> {

    Client client = null;
      /* @Override
       public void channelActive(final ChannelHandlerContext ctx) {

           ctx.pipeline().get(SslHandler.class).handshakeFuture().addListener(
                   new GenericFutureListener<Future<Channel>>() {
                       @Override
                       public void operationComplete(Future<Channel> future) throws Exception {
                           channels.add(ctx.channel());
                           //ctx.writeAndFlush("hi");
                       }
           });
       }*/

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ToServerPacket pck) throws Exception {

        if (client != null) {
            client.addPacket(pck);
        } else if (pck instanceof LoginPacket) {
            LoginPacket l = (LoginPacket) pck;
            if (l.index == 6) {
                client = new Monkey(ctx.channel());
                ctx.writeAndFlush(new com.King.network.ToClient.LoginPacket());
                MainInformation.Initialize()._listMonkeys.add((Monkey) client);
            } else if (l.index == 8) {
                client = new Camera(ctx.channel());
                ctx.writeAndFlush(new com.King.network.ToClient.LoginPacket());
                MainInformation.Initialize()._listCamera.add((Camera) client);
            } else if (l.index == 9) {
                client = new STS(ctx.channel());
                ctx.writeAndFlush(new com.King.network.ToClient.LoginPacket());
            } else if (l.index == 13) {
                client = new Site(ctx.channel());
                ctx.writeAndFlush(new com.King.network.ToClient.LoginPacket());
                ctx.writeAndFlush(new STSInfo());
            }
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        if (client != null) {
            if (client instanceof STS)
                System.out.println("STS Disconnect");
            else if (client instanceof Monkey)
                System.out.println("Monkey Disconnect");
            else if (client instanceof Camera)
                System.out.println("Camera Disconnect");
            else if (client instanceof Site)
                System.out.println("Site Disconnect");
            client.close();
        }
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}