package com.King.module;

/**
 * Created by Wed on 09.02.16.
 */
public class ClientInfo {

    double CPU_IdleTime=0;
    double mem_used=0,mem_free=0,swap_used=0,swap_free=0;

    int countUsers=0;
    long timeForOneRound=0;
    long timeLastRequestonAPI=0;
    long timeLastRequestonInfo=0;

    public double getCPU_IdleTime() {
        return CPU_IdleTime;
    }

    public void setCPU_IdleTime(double CPU_IdleTime) {
        this.CPU_IdleTime = CPU_IdleTime;
    }

    public double getMem_used() {
        return mem_used;
    }

    public void setMem_used(double mem_used) {
        this.mem_used = mem_used;
    }

    public double getMem_free() {
        return mem_free;
    }

    public void setMem_free(double mem_free) {
        this.mem_free = mem_free;
    }

    public double getSwap_used() {
        return swap_used;
    }

    public void setSwap_used(double swap_used) {
        this.swap_used = swap_used;
    }

    public double getSwap_free() {
        return swap_free;
    }

    public void setSwap_free(double swap_free) {
        this.swap_free = swap_free;
    }

    public int getCountUsers() {
        return countUsers;
    }

    public void setCountUsers(int countUsers) {
        this.countUsers = countUsers;
    }

    public long getTimeForOneRound() {
        return timeForOneRound;
    }

    public void setTimeForOneRound(long timeForOneRound) {
        this.timeForOneRound = timeForOneRound;
    }

    public long getTimeLastRequestonAPI() {
        return timeLastRequestonAPI;
    }

    public void setTimeLastRequestonAPI(long timeLastRequestonAPI) {
        this.timeLastRequestonAPI = timeLastRequestonAPI;
    }

    public long getTimeLastRequestonInfo() {
        return timeLastRequestonInfo;
    }

    public void setTimeLastRequestonInfo(long timeLastRequestonInfo) {
        this.timeLastRequestonInfo = timeLastRequestonInfo;
    }
}
