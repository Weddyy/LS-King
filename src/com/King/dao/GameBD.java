package com.King.dao;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by wed on 07.02.16.
 */
@Entity
@Table(name = "game", schema = "", catalog = "Lol")
public class GameBD {
    private int id;
    private String serverId;
    private long idGame;
    private String players;
    private String filePath;
    private String cryptKey;
    private String lastChangInfo;
    private Collection<GameChampionBanBD> gameChampionBansById;
    private Collection<GameConfigBD> gameConfigsById;
    private Collection<GamePlayersBD> gamePlayersesById;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "serverId")
    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    @Basic
    @Column(name = "idGame")
    public long getIdGame() {
        return idGame;
    }

    public void setIdGame(long idGame) {
        this.idGame = idGame;
    }

    @Basic
    @Column(name = "players")
    public String getPlayers() {
        return players;
    }

    public void setPlayers(String players) {
        this.players = players;
    }

    @Basic
    @Column(name = "filePath")
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Basic
    @Column(name = "cryptKey")
    public String getCryptKey() {
        return cryptKey;
    }

    public void setCryptKey(String cryptKey) {
        this.cryptKey = cryptKey;
    }

    @Basic
    @Column(name = "lastChangInfo")
    public String getLastChangInfo() {
        return lastChangInfo;
    }

    public void setLastChangInfo(String lastChangInfo) {
        this.lastChangInfo = lastChangInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameBD gameBD = (GameBD) o;

        if (id != gameBD.id) return false;
        if (idGame != gameBD.idGame) return false;
        if (cryptKey != null ? !cryptKey.equals(gameBD.cryptKey) : gameBD.cryptKey != null) return false;
        if (filePath != null ? !filePath.equals(gameBD.filePath) : gameBD.filePath != null) return false;
        if (lastChangInfo != null ? !lastChangInfo.equals(gameBD.lastChangInfo) : gameBD.lastChangInfo != null)
            return false;
        if (players != null ? !players.equals(gameBD.players) : gameBD.players != null) return false;
        if (serverId != null ? !serverId.equals(gameBD.serverId) : gameBD.serverId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (serverId != null ? serverId.hashCode() : 0);
        result = 31 * result + (int) (idGame ^ (idGame >>> 32));
        result = 31 * result + (players != null ? players.hashCode() : 0);
        result = 31 * result + (filePath != null ? filePath.hashCode() : 0);
        result = 31 * result + (cryptKey != null ? cryptKey.hashCode() : 0);
        result = 31 * result + (lastChangInfo != null ? lastChangInfo.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "gameByGameId")
    public Collection<GameChampionBanBD> getGameChampionBansById() {
        return gameChampionBansById;
    }

    public void setGameChampionBansById(Collection<GameChampionBanBD> gameChampionBansById) {
        this.gameChampionBansById = gameChampionBansById;
    }

    @OneToMany(mappedBy = "gameByGameId")
    public Collection<GameConfigBD> getGameConfigsById() {
        return gameConfigsById;
    }

    public void setGameConfigsById(Collection<GameConfigBD> gameConfigsById) {
        this.gameConfigsById = gameConfigsById;
    }

    @OneToMany(mappedBy = "gameByGameId")
    public Collection<GamePlayersBD> getGamePlayersesById() {
        return gamePlayersesById;
    }

    public void setGamePlayersesById(Collection<GamePlayersBD> gamePlayersesById) {
        this.gamePlayersesById = gamePlayersesById;
    }
}
