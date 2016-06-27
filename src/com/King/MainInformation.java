package com.King;


import com.King.dao.PlayerBD;
import com.King.enums.PlayersState;
import com.King.enums.ServerId;
import com.King.module.*;

import java.util.*;

/**
 * Created by Wed on 09.02.16.
 */
public class MainInformation {

    List<Monkey> _listMonkeys=new ArrayList<>();
    List<Camera> _listCamera=new ArrayList<>();
    Map<String ,Summoner> players=new HashMap<>();
    Map<String ,Summoner> playersOnMonkey=new HashMap<>();
    Map<String ,Summoner> playersOnCamera=new HashMap<>();
    Map<String ,Summoner> playersOnNonState=new HashMap<>();

    private static MainInformation info=new MainInformation();

    public Collection<Summoner> getPlayersOnCamera()
    {
        return playersOnCamera.values();
    }

    public Collection<Summoner> getPlayersOnMonkey()
    {
        return playersOnMonkey.values();
    }

    public Collection<Summoner> getPlayersOnNonState()
    {
        return playersOnNonState.values();
    }

    public Collection<Summoner> getAllPlayers()
    {
        return players.values();
    }

    public void addNewPlayer(Summoner pl)
    {
        if(!players.containsKey(getKeyUser(pl))) {
            players.put(getKeyUser(pl), pl);
            playersOnNonState.put(getKeyUser(pl), pl);
            pl.setPlayerState(PlayersState.None);
        }
    }

    public void movePlayerToCamera(Summoner pl)
    {
        if(players.containsKey(getKeyUser(pl))) {
            playersOnNonState.remove(getKeyUser(pl));
            playersOnMonkey.remove(getKeyUser(pl));
            playersOnCamera.put(getKeyUser(pl),pl);
            pl.setPlayerState(PlayersState.in_camera);
        }
    }

    public Summoner getSummoner(long sumId,ServerId serId)
    {
        String key=getKeyUser(sumId,serId);
        if(players.containsKey(getKeyUser(sumId,serId)))
            return players.get(getKeyUser(sumId,serId));
        return null;
    }

    public void movePlayerToMonkey(Summoner pl)
    {
        if(players.containsKey(getKeyUser(pl))) {
            playersOnNonState.remove(getKeyUser(pl));
            playersOnMonkey.put(getKeyUser(pl),pl);
            playersOnCamera.remove(getKeyUser(pl));
            pl.setPlayerState(PlayersState.in_monkey);
        }
    }

    public void movePlayerToNoState(Summoner pl)
    {
        if(players.containsKey(getKeyUser(pl))) {
            playersOnCamera.remove(getKeyUser(pl));
            playersOnMonkey.remove(getKeyUser(pl));
            playersOnNonState.put(getKeyUser(pl),pl);
            pl.setPlayerState(PlayersState.None);
        }
    }

    public void Load()
    {
        loadUsers();
        System.out.println("Load : all users (count : "+players.size()+" )");
    }

    public static MainInformation Initialize()
    {
        return info;
    }

    public List<Monkey> getMonkeyList()
    {
        return _listMonkeys;
    }

    public List<Camera> getListCamera()
    {
        return _listCamera;
    }

    private void loadUsers()
    {
        List<PlayerBD> pList= DataBase.Initialization().getAllPlayers();
        for(PlayerBD p : pList)
            addNewPlayer(new Summoner(p));
    }

    public void giveAllInfo()
    {
        System.out.println("======== Main info ========");
        System.out.println("Count monkeys : "+_listMonkeys.size());
        System.out.println("Count cameras : "+_listCamera.size());
        System.out.println("Count players : "+players.size());
        System.out.println("Count players to camer: "+playersOnCamera.size());
        System.out.println("Count players to monkey: "+playersOnMonkey.size());
        System.out.println("Count players to NoState: "+playersOnNonState.size());
        System.out.println("STS Server : "+ ((STS.Initializ()==null) ? "False" : "True"));
        System.out.println("WWW Server : "+ ((Site.Initializ()==null) ? "False" : "True"));
        System.out.println("===========================");
    }

    public static String  getKeyUser(Summoner p)
    {
        return String.valueOf(p.getPlayer().getSummonerId())+" "+p.getPlayer().getServerId();
    }

    public static String  getKeyUser(long somId,ServerId s)
    {
        return String.valueOf(somId)+" "+s.toString();
    }
}
