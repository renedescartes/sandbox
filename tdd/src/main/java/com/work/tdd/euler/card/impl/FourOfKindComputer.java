package com.work.tdd.euler.card.impl;

import com.work.tdd.euler.card.*;

import java.util.Collection;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Collections2.filter;
import static com.work.tdd.euler.card.RankFunction.rankPredicate;

public class FourOfKindComputer implements RankComputer<FourOfKindRank> {
    @Override
    public FourOfKindRank handRank(Hand h) {
        Rank rank = RankFunction.checkFourOfAKind(h);
        return rank != null ? new FourOfKindRank(rank, otherRank(h, rank)) : null;
    }

    private static Rank otherRank(Hand h, Rank fourRank) {
        Collection<Card> cards = filter(h.getCards(), rankPredicate(fourRank));
        checkState(cards.size() == 1);
        return cards.iterator().next().getRank();
    }
}
