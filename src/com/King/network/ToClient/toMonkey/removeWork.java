package com.King.network.ToClient.toMonkey;

import com.King.module.Summoner;
import com.King.network.ToClientPacket;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wed on 09.02.16.
 */
public class removeWork extends ToClientPacket {

    List<Summoner> _player;
    public removeWork(List<Summoner> player)
    {
        _player=new ArrayList<Summoner>();
        _player.addAll(player);
    }

    public removeWork(Summoner p)
    {
        _player=new ArrayList<Summoner>();
        _player.add(p);
    }

    public int getId() {
        return 5;
    }

    public void send(ByteBuf buffer) {
        buffer.writeInt(_player.size());

        for(Summoner p:_player)
        {
            buffer.writeLong(p.getPlayer().getSummonerId());
            buffer.writeShort(p.getPlayer().getServerIdE().getValue());
        }
    }
}