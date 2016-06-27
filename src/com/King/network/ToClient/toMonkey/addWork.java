package com.King.network.ToClient.toMonkey;

import com.King.dao.PlayerBD;
import com.King.module.Monkey;
import com.King.module.Summoner;
import com.King.network.ToClientPacket;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wed on 09.02.16.
 */
public class addWork extends ToClientPacket {

    List<Summoner> _player;
    public addWork(List<Summoner> player)
    {
        _player=new ArrayList<Summoner>();
        _player.addAll(player);
    }

    public int getId() {
        return 4;
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