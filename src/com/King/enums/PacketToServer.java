package com.King.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wed on 10.03.16.
 */
public enum PacketToServer {
    UNKNOWN(0),LOGIN(1),INFO(2),GAME_START_M(3),GAME_END_C(4),GET_GAME_STS(5),GET_GAME_SITE(6),ADD_USER(7);

    private static Map<Integer,PacketToServer> _pakets=new HashMap<>();
    private final int value;

    private PacketToServer(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    static {
        for(PacketToServer p:PacketToServer.values())
        {
            _pakets.put(p.getValue(),p);
        }
    }

    public static PacketToServer get(int i)
    {
        if(_pakets.containsKey(i))
            return _pakets.get(i);
        return PacketToServer.UNKNOWN;
    }
}
