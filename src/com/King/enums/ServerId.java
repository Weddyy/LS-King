package com.King.enums;

/**
 * Created by wed on 07.02.16.
 */
public enum  ServerId {
    NA1(0),RU(1),TR1(2),KR(3),BR1(4),LA1(5),LA2(6),EUN1(7),OC1(8),EUW1(9),PBE1(10),LOLKING(11);

    private final int value;

    private ServerId(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}