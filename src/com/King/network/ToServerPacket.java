package com.King.network;

import com.King.network.ToServer.*;
import io.netty.buffer.ByteBuf;

import java.io.IOException;

/**
 * Created by Wed on 09.02.16.
 */
public abstract class ToServerPacket {

    public static ToServerPacket read(ByteBuf buffer) throws IOException {
        if(!buffer.isReadable())
            return null;
        int id = buffer.readUnsignedShort(); // Получаем ID пришедшего пакета, чтобы определить, каким классом его читать
        ToServerPacket packet = parse(id); // Получаем инстанс пакета с этим ID
        if(packet == null)
            throw new IOException("Bad packet ID: " + id); // Если произошла ошибка и такого пакета не может быть, генерируем исключение
        packet.get(buffer); // Читаем в пакет данные из буфера
        return packet;
    }

    private static ToServerPacket parse(int id)
    {
        switch (id)
        {
            case 1:
                return new LoginPacket();
            case 2:
                return new Info();
            case 3:
                return new GameStart();
            case 11:
                return new LoginPacket();
            case 12:
                return new Info();
            case 13:
                return new Game();
            case 21:
                return new LoginPacket();
            case 22:
                return new GetGame();
            case 51:
                return new LoginPacket();
            case 52:
                return new GetGameToSite();
            case 54:
                return new addNewUser();
            default: return null;
        }
    }

    public abstract void get(ByteBuf buffer);

    /*
    * private static ToServerPacket parse(int id)
    {
        switch (PacketToServer.get(id))
        {
            case LOGIN:
                return new LoginPacket();
            case INFO:
                return new Info();
            case GAME_START_M:
                return new GameStart();
            case GAME_END_C:
                return new Game();
            case GET_GAME_STS:
                return new GetGame();
            case GET_GAME_SITE:
                return new GetGameToSite();
            case ADD_USER:
                return new addNewUser();
            default: return null;
        }
    }

    public abstract void get(ByteBuf buffer);

    public abstract PacketToServer getType();
    * */
}
