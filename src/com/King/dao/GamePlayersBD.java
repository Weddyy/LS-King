package com.King.dao;

import javax.persistence.*;

/**
 * Created by wed on 07.02.16.
 */
@Entity
@Table(name = "game_players", schema = "", catalog = "Lol")
public class GamePlayersBD {
    private int id;
    private String name;
    private byte isBot;
    private long spell1D;
    private long spell2D;
    private long playerIcon;
    private long championId;
    private long playerId;
    private long teadId;
    private String rune;
    private String mastery;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "isBot")
    public byte getIsBot() {
        return isBot;
    }

    public void setIsBot(byte isBot) {
        this.isBot = isBot;
    }

    @Basic
    @Column(name = "spell1d")
    public long getSpell1D() {
        return spell1D;
    }

    public void setSpell1D(long spell1D) {
        this.spell1D = spell1D;
    }

    @Basic
    @Column(name = "spell2d")
    public long getSpell2D() {
        return spell2D;
    }

    public void setSpell2D(long spell2D) {
        this.spell2D = spell2D;
    }

    @Basic
    @Column(name = "playerIcon")
    public long getPlayerIcon() {
        return playerIcon;
    }

    public void setPlayerIcon(long playerIcon) {
        this.playerIcon = playerIcon;
    }

    @Basic
    @Column(name = "championId")
    public long getChampionId() {
        return championId;
    }

    public void setChampionId(long championId) {
        this.championId = championId;
    }


    @Basic
    @Column(name = "playerId")
    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    @Basic
    @Column(name = "teadId")
    public long getTeadId() {
        return teadId;
    }

    public void setTeadId(long teadId) {
        this.teadId = teadId;
    }

    @Basic
    @Column(name = "rune")
    public String getRune() {
        return rune;
    }

    public void setRune(String rune) {
        this.rune = rune;
    }

    @Basic
    @Column(name = "mastery")
    public String getMastery() {
        return mastery;
    }

    public void setMastery(String mastery) {
        this.mastery = mastery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GamePlayersBD that = (GamePlayersBD) o;

        if (championId != that.championId) return false;
        if (id != that.id) return false;
        if (isBot != that.isBot) return false;
        if (teadId != that.teadId) return false;
        if (mastery != null ? !mastery.equals(that.mastery) : that.mastery != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (playerIcon != that.playerIcon) return false;
        if (rune != null ? !rune.equals(that.rune) : that.rune != null) return false;
        if (spell1D != that.spell1D) return false;
        if (spell2D != that.spell2D) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) isBot;
        result = 31 * result + (int) (spell1D ^ (spell1D >>> 32));
        result = 31 * result + (int) (spell2D ^ (spell2D >>> 32));
        result = 31 * result + (int) (playerIcon ^ (playerIcon >>> 32));
        result = 31 * result + (int) (championId ^ (championId >>> 32));
        result = 31 * result + (int) (teadId ^ (teadId >>> 32));
        result = 31 * result + (rune != null ? rune.hashCode() : 0);
        result = 31 * result + (mastery != null ? mastery.hashCode() : 0);
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
