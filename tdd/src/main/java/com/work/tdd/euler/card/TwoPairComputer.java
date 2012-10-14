package com.work.tdd.euler.card;

import com.google.common.collect.Collections2;

import java.util.Collection;
import java.util.List;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Predicates.and;
import static com.work.tdd.euler.card.RankFunction.rankPredicate;

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
        Collection<Card> otherCard = Collections2.filter(h.getCards(), and(rankPredicate(pair1Rank), rankPredicate(pair2Rank)));
        checkState(otherCard.size() == 1);
        return otherCard.iterator().next().getRank();
    }
}
