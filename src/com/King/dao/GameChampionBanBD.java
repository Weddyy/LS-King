package com.King.dao;

import javax.persistence.*;

/**
 * Created by wed on 07.02.16.
 */
@Entity
@Table(name = "game_champion_ban", schema = "", catalog = "Lol")
public class GameChampionBanBD {
    private int id;
    private long charId;
    private long teamId;
    private Integer pickTurn;
    private GameBD gameByGameId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "charId")
    public long getCharId() {
        return charId;
    }

    public void setCharId(long charId) {
        this.charId = charId;
    }

    @Basic
    @Column(name = "teamId")
    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    @Basic
    @Column(name = "pickTurn")
    public Integer getPickTurn() {
        return pickTurn;
    }

    public void setPickTurn(Integer pickTurn) {
        this.pickTurn = pickTurn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameChampionBanBD that = (GameChampionBanBD) o;

        if (charId != that.charId) return false;
        if (id != that.id) return false;
        if (pickTurn != null ? !pickTurn.equals(that.pickTurn) : that.pickTurn != null) return false;
        if (teamId != that.teamId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (int) (charId ^ (charId >>> 32));
        result = 31 * result + (int) (teamId ^ (teamId >>> 32));
        result = 31 * result + (pickTurn != null ? pickTurn.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "gameId", referencedColumnName = "id", nullable = false)
    public GameBD getGameByGameId() {
        return gameByGameId;
    }

    public void setGameByGameId(GameBD gameByGameId) {
        this.gameByGameId = gameByGameId;
    }
}
