package com.work.tdd.euler.card;

import java.util.Collections;
import java.util.List;

public class Hand implements Comparable<Hand> {
    private List<Card> cards;

    public Hand(List<Card> cards) {
        if (cards.size() != 5) {
            throw new IllegalArgumentException("Texas Holdem Poker needs five cards");
        }
        this.cards = cards;
        Collections.sort(cards);
        compute();
    }

    private void compute() {

    }


    @Override
    public int compareTo(Hand o) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<Card> getCards() {
        return cards;
    }


}