package com.work.tdd.euler.card;

import java.util.Collections;
import java.util.List;

import static com.work.tdd.euler.card.HandRankComputer.computeRank;

public class Hand implements Comparable<Hand> {
    private final List<Card> cards;
    private final HandRank handRank;

    public Hand(List<Card> cards) {
        if (cards.size() != 5) {
            throw new IllegalArgumentException("Texas Holdem Poker needs five cards");
        }
        this.cards = cards;
        Collections.sort(cards);
        this.handRank = computeRank(this);
    }

    @Override
    public int compareTo(Hand o) {
        return handRank.compareTo(o.handRank);
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        return "Hand{" +
                "cards=" + cards +
                ", handRank=" + handRank +
                '}';
    }
}