package com.King.dao;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by wed on 07.02.16.
 */
@Entity
@Table(name = "game_config", schema = "", catalog = "Lol")
public class GameConfigBD {
    private int id;
    private Timestamp gameStart;
    private Timestamp gameDate;
    private long mapId;
    private String gameMode;
    private String gameType;
    private long gameQueryConfig;
    private long gameLength;
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
    @Column(name = "gameStart")
    public Timestamp getGameStart() {
        return gameStart;
    }

    public void setGameStart(Timestamp gameStart) {
        this.gameStart = gameStart;
    }

    @Basic
    @Column(name = "gameDate")
    public Timestamp getGameDate() {
        return gameDate;
    }

    public void setGameDate(Timestamp gameStart) {
        this.gameDate = gameStart;
    }

    @Basic
    @Column(name = "mapId")
    public long getMapId() {
        return mapId;
    }

    public void setMapId(long mapId) {
        this.mapId = mapId;
    }

    @Basic
    @Column(name = "gameMode")
    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    @Basic
    @Column(name = "gameType")
    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    @Basic
    @Column(name = "gameQueryConfig")
    public long getGameQueryConfig() {
        return gameQueryConfig;
    }

    public void setGameQueryConfig(long gameQueryConfig) {
        this.gameQueryConfig = gameQueryConfig;
    }

    @Basic
    @Column(name = "gameLength")
    public long getGameLength() {
        return gameLength;
    }

    public void setGameLength(long gameLength) {
        this.gameLength = gameLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameConfigBD that = (GameConfigBD) o;

        if (id != that.id) return false;
        if (mapId != that.mapId) return false;
        if (gameMode != null ? !gameMode.equals(that.gameMode) : that.gameMode != null) return false;
        if (gameQueryConfig != that.gameQueryConfig) return false;
        if (gameStart != null ? !gameStart.equals(that.gameStart) : that.gameStart != null) return false;
        if (gameType != null ? !gameType.equals(that.gameType) : that.gameType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (gameStart != null ? gameStart.hashCode() : 0);
        result = 31 * result + (int) (mapId ^ (mapId >>> 32));
        result = 31 * result + (gameMode != null ? gameMode.hashCode() : 0);
        result = 31 * result + (gameType != null ? gameType.hashCode() : 0);
        result = 31 * result + (int) (gameQueryConfig ^ (gameQueryConfig >>> 32));
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
