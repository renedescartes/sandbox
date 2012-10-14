package com.work.tdd.euler.card;

public enum Kind {
    CLUB('C'), HEARTS('H'), SPADE('S'), DIAMOND('D');
    char c;

    Kind(char c) {
        this.c = c;
    }
}
