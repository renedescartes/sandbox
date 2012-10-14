package com.work.tdd.euler.card;

import org.apache.commons.lang.builder.CompareToBuilder;

import java.util.List;

public class OnePairRank extends AbstractRank {

    private Rank pairRank;
    private List<Rank> otherRanks;

    public OnePairRank(Rank pairRank, List<Rank> otherRanks) {
        this.pairRank = pairRank;
        this.otherRanks = otherRanks;
    }

    @Override
    public Integer getLevel() {
        return 2;
    }

    @Override
    int compareCurrentLevel(HandRank o) {
        OnePairRank next = (OnePairRank) o;
        CompareToBuilder b = new CompareToBuilder().append(pairRank, next.pairRank);
        for (int i = 0; i < otherRanks.size(); i++) {
            b.append(otherRanks.get(i), next.otherRanks.get(i));
        }
        return b.toComparison();
    }
}
