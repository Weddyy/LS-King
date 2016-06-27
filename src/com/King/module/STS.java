package com.King.module;

import com.King.MainInformation;
import com.King.dao.GameBD;
import com.King.dao.GamePlayersBD;
import com.King.enums.ServerId;
import com.King.module.StsWorker.PacketThread;
import com.King.network.ToClient.toCamera.addWork;
import com.King.network.ToClient.toCamera.removeWork;
import com.King.network.ToClient.toSite.STSInfo;
import io.netty.channel.Channel;
import javafx.fxml.Initializable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wed on 12.02.16.
 */
public class STS  extends Client {

    static STS thisSts=null;
    Channel chanel;
    PacketThread packetThread;

    public STS(Channel c) {
        chanel = c;
        packetThread = new PacketThread(this);
        packetThread.start();
        thisSts=this;
        if(Site.Initializ()!=null)
            Site.Initializ().getChanel().writeAndFlush(new STSInfo());
    }

    public void close() {
        if (packetThread != null)
            packetThread.Stop();
        thisSts=null;
        if(Site.Initializ()!=null)
            Site.Initializ().getChanel().writeAndFlush(new STSInfo());
    }

    public Channel getChanel() {
        return chanel;
    }

    public static STS Initializ()
    {
        return thisSts;
    }
}