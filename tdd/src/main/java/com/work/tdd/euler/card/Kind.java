package com.work.tdd.euler.card;

public enum Kind {
    CLUB('C'), HEARTS('H'), SPADE('S'), DIAMOND('D');
    char c;

    Kind(char c) {
        this.c = c;
    }

    public static Kind parseKind(char target) {
        for (Kind k : values()) {
            if (k.c == target) {
                return k;
            }
        }
        throw new IllegalArgumentException("Invalid Kind - " + target);
    }
}
