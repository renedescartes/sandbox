package com.work.tdd.euler.card.impl;

import com.google.common.collect.Collections2;
import com.work.tdd.euler.card.Hand;
import com.work.tdd.euler.card.Rank;
import com.work.tdd.euler.card.RankComputer;
import com.work.tdd.euler.card.RankFunction;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.reverse;
import static com.work.tdd.euler.card.RankFunction.rankTransform;

public class HighestCardComputer implements RankComputer<HighestCardRank> {
    @Override
    public HighestCardRank handRank(Hand h) {
        List<Rank> rank = RankFunction.pairRanks(h);
        if (rank.size() != 0) {
            return null;
        }
        return new HighestCardRank(ranks(h));
    }

    private static List<Rank> ranks(Hand h) {
        return reverse(newArrayList(Collections2.transform(h.getCards(), rankTransform())));
    }
}
