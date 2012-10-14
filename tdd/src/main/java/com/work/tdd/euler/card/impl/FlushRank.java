package com.work.tdd.euler.card.impl;

import com.work.tdd.euler.card.HandRank;
import com.work.tdd.euler.card.Rank;
import org.apache.commons.lang.builder.CompareToBuilder;

import java.util.List;

public class FlushRank extends AbstractRank {
    private List<Rank> ranks;

    public FlushRank(List<Rank> ranks) {
        this.ranks = ranks;
    }

    @Override
    public Integer getLevel() {
        return 6;
    }

    @Override
    int compareCurrentLevel(HandRank o) {
        FlushRank next = (FlushRank) o;
        CompareToBuilder b = new CompareToBuilder();
        for (int i = 0; i < ranks.size(); i++) {
            b.append(ranks.get(i), next.ranks.get(i));
        }
        return b.toComparison();
    }
}
