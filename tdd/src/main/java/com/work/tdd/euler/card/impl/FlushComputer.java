package com.work.tdd.euler.card.impl;

import com.work.tdd.euler.card.Hand;
import com.work.tdd.euler.card.Rank;
import com.work.tdd.euler.card.RankComputer;

import java.util.List;

import static com.google.common.collect.Collections2.transform;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.reverse;
import static com.work.tdd.euler.card.impl.RankFunction.checkFlush;
import static com.work.tdd.euler.card.impl.RankFunction.rankTransform;

public class FlushComputer implements RankComputer<FlushRank> {
    @Override
    public FlushRank handRank(Hand h) {
        return checkFlush(h) ? new FlushRank(ranks(h)) : null;
    }

    private static List<Rank> ranks(Hand h) {
        return reverse(newArrayList(transform(h.getCards(), rankTransform())));
    }
}
