package com.work.tdd.euler.card;

public enum Rank {
    TWO('2'), THREE('3'), FOUR('4'), FIVE('5'), SIX('6'), SEVEN('7'), EIGHT('8'),
    NINE('9'), TEN('T'), JACK('J'), QUEEN('Q'), KING('K'), ACE('A');
    char c;

    Rank(char c) {
        this.c = c;
    }
}
