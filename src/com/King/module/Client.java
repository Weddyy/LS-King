package com.King.module;


import com.King.network.ToServerPacket;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Wed on 09.02.16.
 */
public abstract class Client {
    private ClientInfo HWInfo=new ClientInfo();

    public List<ToServerPacket> _packets= Collections.synchronizedList(new LinkedList<ToServerPacket>());

    public ToServerPacket getPacket()
    {
        ToServerPacket p=null;
            if (_packets.size() != 0) {
                p = _packets.get(0);
                _packets.remove(0);
            }
            return p;

    }

    public void addPacket(ToServerPacket packet)
    {
            _packets.add(packet);
    }

    public abstract void close();

    public ClientInfo getHWInfo()
    {
        return HWInfo;
    }
}
