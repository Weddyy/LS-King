package com.King.dao;

import com.King.enums.ServerId;

import javax.persistence.*;

/**
 * Created by wed on 07.02.16.
 */
@Entity
@Table(name = "player", schema = "", catalog = "Lol")
public class PlayerBD {
    private int id;
    private long summonerId;
    private String serverId;
    private String name;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ServerId getServerIdE(){
        return ServerId.valueOf(serverId);
    }

    @Basic
    @Column(name = "summonerId")
    public long getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(long summonerId) {
        this.summonerId = summonerId;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerBD playerBD = (PlayerBD) o;

        if (id != playerBD.id) return false;
        if (summonerId != playerBD.summonerId) return false;
        if (name != null ? !name.equals(playerBD.name) : playerBD.name != null) return false;
        if (serverId != null ? !serverId.equals(playerBD.serverId) : playerBD.serverId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (int) (summonerId ^ (summonerId >>> 32));
        result = 31 * result + (serverId != null ? serverId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
