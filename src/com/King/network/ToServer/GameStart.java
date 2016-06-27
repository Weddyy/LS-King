package com.King.network.ToServer;

import com.King.dao.GameBD;
import com.King.dao.GameChampionBanBD;
import com.King.dao.GameConfigBD;
import com.King.dao.GamePlayersBD;
import com.King.network.ToServerPacket;
import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.HashSet;

/**
 * Created by Wed on 10.02.16.
 */
public class GameStart extends ToServerPacket {

    GameBD gameBD=new GameBD();

    public GameBD getInfo(){
        return gameBD;
    }

    public void get(ByteBuf buffer) {
        GameConfigBD conf=new GameConfigBD();
        conf.setGameLength(buffer.readLong());
        conf.setGameStart(new Timestamp(buffer.readLong()));
        gameBD.setIdGame(buffer.readLong());
        conf.setGameQueryConfig(buffer.readLong());
        conf.setMapId(buffer.readLong());

        int countBytes=buffer.readInt();
        byte[] b=new byte[countBytes];
        buffer.readBytes(b);

        conf.setGameMode(new String(b, Charset.forName("UTF-8")));

        countBytes=buffer.readInt();
        b=new byte[countBytes];
        buffer.readBytes(b);

        conf.setGameType(new String(b, Charset.forName("UTF-8")));

        countBytes=buffer.readInt();
        b=new byte[countBytes];
        buffer.readBytes(b);

        gameBD.setCryptKey(new String(b, Charset.forName("UTF-8")));

        countBytes=buffer.readInt();
        b=new byte[countBytes];
        buffer.readBytes(b);

        gameBD.setServerId(new String(b, Charset.forName("UTF-8")));

        int countMass=buffer.readInt();

        for(int i=0;i<countMass;i++){
            if(gameBD.getGameChampionBansById()==null)
                gameBD.setGameChampionBansById(new HashSet<GameChampionBanBD>());
            GameChampionBanBD ban=new GameChampionBanBD();
            ban.setCharId(buffer.readLong());
            ban.setTeamId(buffer.readLong());
            ban.setPickTurn(buffer.readInt());
            gameBD.getGameChampionBansById().add(ban);
        }

        countMass=buffer.readInt();
        for(int i=0;i<countMass;i++){
            GamePlayersBD player=new GamePlayersBD();
            player.setIsBot((byte) (buffer.readBoolean() ? 1 : 0 ));
            player.setSpell1D(buffer.readLong());
            player.setSpell2D(buffer.readLong());
            player.setPlayerIcon(buffer.readLong());
            player.setChampionId(buffer.readLong());
            player.setTeadId(buffer.readLong());
            player.setPlayerId(buffer.readLong());

            countBytes=buffer.readInt();
            b=new byte[countBytes];
            buffer.readBytes(b);

            player.setName(new String(b, Charset.forName("UTF-8")));


            int countMast=buffer.readInt();
            String mast="";
            for(int t=0;t<countMast;t++){
                long id=buffer.readLong();
                int rank=buffer.readInt();
                mast+=String.valueOf(id)+":"+String.valueOf(rank)+";";
            }
            player.setMastery(mast);

            countMast=buffer.readInt();
            String rune="";
            for(int t=0;t<countMast;t++){
                long id=buffer.readLong();
                int count=buffer.readInt();
                rune+=String.valueOf(id)+":"+String.valueOf(count)+";";
            }

            player.setRune(rune);
            if(gameBD.getGamePlayersesById()==null)
                gameBD.setGamePlayersesById(new HashSet<GamePlayersBD>());
            gameBD.getGamePlayersesById().add(player);
        }
        conf.setGameDate(new Timestamp(System.currentTimeMillis()));

        gameBD.setGameConfigsById(new HashSet<GameConfigBD>());
        gameBD.getGameConfigsById().add(conf);


    }
}