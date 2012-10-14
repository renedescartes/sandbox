package com.work.tdd.euler.card;

import com.work.tdd.euler.card.impl.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand implements Comparable<Hand> {
    private final List<Card> cards;
    private final HandRank handRank;

    public Hand(List<Card> cards) {
        if (cards.size() != 5) {
            throw new IllegalArgumentException("Texas Holdem Poker needs five cards");
        }
        Collections.sort(cards);
        this.cards = cards;
        this.handRank = computeRank(this);
    }

    @Override
    public int compareTo(Hand o) {
        return handRank.compareTo(o.handRank);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    private static List<RankComputer<? extends HandRank>> rankComputers = new ArrayList<>();

    static {
        rankComputers.add(new RoyalFlushComputer());
        rankComputers.add(new StraightFlushComputer());
        rankComputers.add(new FourOfKindComputer());
        rankComputers.add(new FullHouseComputer());
        rankComputers.add(new FlushComputer());
        rankComputers.add(new StraightComputer());
        rankComputers.add(new ThreeKindComputer());
        rankComputers.add(new TwoPairComputer());
        rankComputers.add(new OnePairComputer());
        rankComputers.add(new HighestCardComputer());
    }

    public static HandRank computeRank(Hand h) {
        for (RankComputer r : rankComputers) {
            HandRank handRank = r.handRank(h);
            if (handRank != null) {
                return handRank;
            }
        }
        throw new RuntimeException("Could not compute rank for " + h);
    }

    @Override
    public String toString() {
        return "Hand{" +
                "cards=" + cards +
                ", handRank=" + handRank +
                '}';
    }

    public HandRank getHandRank() {
        return handRank;
    }
}