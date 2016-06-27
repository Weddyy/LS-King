package com.King.module;

import com.King.module.SiteWorker.PacketThread;
import com.King.network.ToClient.toSite.STSInfo;
import io.netty.channel.Channel;

/**
 * Created by wed on 19.02.16.
 */
public class Site extends Client {

    static Site thisSts=null;
    Channel chanel;
    PacketThread packetThread;

    public Site(Channel c) {
        chanel = c;
        packetThread = new PacketThread(this);
        packetThread.start();
        thisSts=this;
    }

    public void close() {
        if (packetThread != null)
            packetThread.Stop();
        thisSts=null;
    }

    public Channel getChanel() {
        return chanel;
    }

    public static Site Initializ()
    {
        return thisSts;
    }
}