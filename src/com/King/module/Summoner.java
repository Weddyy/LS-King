package com.King.module;

import com.King.dao.PlayerBD;
import com.King.enums.PlayersState;

/**
 * Created by wed on 10.02.16.
 */
public class Summoner {
    PlayerBD playerBd;
    PlayersState state=PlayersState.None;

    public Summoner(PlayerBD p)
    {
        playerBd=p;
    }
    public PlayersState getState()
    {
        return state;
    }
    public void setPlayerState(PlayersState pl)
    {
        state=pl;
    }
    public PlayerBD getPlayer()
    {
        return playerBd;
    }
}
