package com.work.tdd.euler.card.impl;

import com.work.tdd.euler.card.Card;
import com.work.tdd.euler.card.Hand;
import com.work.tdd.euler.card.Rank;
import com.work.tdd.euler.card.RankComputer;

import java.util.Collection;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Predicates.not;
import static com.google.common.collect.Collections2.filter;
import static com.google.common.collect.Lists.reverse;
import static com.work.tdd.euler.card.impl.RankFunction.rankPredicate;

public class ThreeKindComputer implements RankComputer<ThreeKindRank> {
    @Override
    public ThreeKindRank handRank(Hand h) {
        Rank rank = RankFunction.threeOfAKind(h);
        if (rank == null) {
            return null;
        } else {
            Card[] otherCards = otherCards(h, rank);
            return new ThreeKindRank(rank, otherCards[0].getRank(), otherCards[1].getRank());
        }
    }

    private static Card[] otherCards(Hand h, Rank threeKindRank) {
        Collection<Card> otherCards = filter(reverse(h.getCards()), not(rankPredicate(threeKindRank)));
        checkState(otherCards.size() == 2);
        return otherCards.toArray(new Card[otherCards.size()]);
    }
}
