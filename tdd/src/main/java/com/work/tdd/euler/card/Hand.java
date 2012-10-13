package com.work.tdd.euler.card;

import java.util.Collections;
import java.util.List;

import static com.work.tdd.euler.card.Hand.CardRank.*;
import static com.work.tdd.euler.card.RankFunction.*;

public class Hand implements Comparable<Hand> {
    private List<Card> cards;
    private Rank straightFlushHigh, fourOfAKindHigh, fullHouseThree, fullHousePair, straightHigh, threeOfKindHigh, twoPairHigh1, twoPairHigh2, onePairHigh;
    private CardRank cardRank;

    public Hand(List<Card> cards) {
        if (cards.size() != 5) {
            throw new IllegalArgumentException("Texas Holdem Poker needs five cards");
        }
        this.cards = cards;
        Collections.sort(cards);
        compute();
    }

    private void compute() {
        if (isRoyalFlush(this)) {
            cardRank = ROYAL_FLUSH;
        } else if ((straightFlushHigh = checkStraightFlush(this)) != null) {
            cardRank = STRAIGHT_FLUSH;
        } else if ((fourOfAKindHigh = checkFourOfAKind(this)) != null) {
            cardRank = FOUR_OF_KIND;
        } else if (fullHouse(this) != null) {
            Rank[] ranks = fullHouse(this);
            fullHouseThree = ranks[0];
            fullHousePair = ranks[1];
        }


    }


    @Override
    public int compareTo(Hand o) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<Card> getCards() {
        return cards;
    }

    enum CardRank {
        HIGH_CARD, ONE_PAIR, TWO_PAIRS, THREE_OF_A_KIND, STRAIGHT,
        FLUSH, FULL_HOUSE, FOUR_OF_KIND, STRAIGHT_FLUSH, ROYAL_FLUSH
    }

    public CardRank cardRank() {
        return cardRank;
    }

}