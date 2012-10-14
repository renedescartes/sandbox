package com.work.tdd.euler.card;

import java.util.Collection;

import static com.google.common.base.Predicates.not;
import static com.google.common.collect.Collections2.filter;
import static com.google.common.collect.Lists.reverse;
import static com.work.tdd.euler.card.RankFunction.rankPredicate;

public class ThreeKindComputer implements RankComputer<ThreeKindRank> {
    @Override
    public ThreeKindRank handRank(Hand h) {
        Rank rank = RankFunction.threeOfAKind(h);
        Card[] otherCards = otherCards(h, rank);
        return rank != null ? new ThreeKindRank(rank, otherCards[0].getRank(), otherCards[1].getRank()) : null;
    }

    private static Card[] otherCards(Hand h, Rank threeKindRank) {
        Collection<Card> otherCards = filter(reverse(h.getCards()), not(rankPredicate(threeKindRank)));
        return otherCards.toArray(new Card[otherCards.size()]);
    }
}
