package com.work.tdd.euler.card;

import org.apache.commons.lang.builder.CompareToBuilder;

import java.util.List;

public class HighestCardRank extends AbstractRank {

    private List<Rank> ranks;

    public HighestCardRank(List<Rank> ranks) {
        this.ranks = ranks;
    }

    @Override
    public Integer getLevel() {
        return 2;
    }

    @Override
    int compareCurrentLevel(HandRank o) {
        HighestCardRank next = (HighestCardRank) o;
        CompareToBuilder b = new CompareToBuilder();
        for (int i = 0; i < ranks.size(); i++) {
            b.append(ranks.get(i), next.ranks.get(i));
        }
        return b.toComparison();
    }
}