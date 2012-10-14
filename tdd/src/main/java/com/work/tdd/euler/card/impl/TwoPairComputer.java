package com.work.tdd.euler.card.impl;

import com.google.common.base.Predicate;
import com.work.tdd.euler.card.Card;
import com.work.tdd.euler.card.Hand;
import com.work.tdd.euler.card.Rank;
import com.work.tdd.euler.card.RankComputer;

import java.util.Collection;
import java.util.List;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Predicates.and;
import static com.google.common.base.Predicates.not;
import static com.google.common.collect.Collections2.filter;
import static com.work.tdd.euler.card.impl.RankFunction.rankPredicate;

public class TwoPairComputer implements RankComputer<TwoPairRank> {
    @Override
    public TwoPairRank handRank(Hand h) {
        List<Rank> rank = RankFunction.pairRanks(h);
        if (rank.size() != 2) {
            return null;
        }
        return new TwoPairRank(rank.get(1), rank.get(0), otherRank(h, rank.get(1), rank.get(0)));
    }

    private static Rank otherRank(Hand h, Rank pair1Rank, Rank pair2Rank) {
        Predicate<Card> pairFilter = and(not(rankPredicate(pair1Rank)), not(rankPredicate(pair2Rank)));
        Collection<Card> otherCard = filter(h.getCards(), pairFilter);
        checkState(otherCard.size() == 1);
        return otherCard.iterator().next().getRank();
    }
}
