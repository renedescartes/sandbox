package com.work.tdd.euler.card;

import org.apache.commons.lang.builder.CompareToBuilder;

import static com.google.common.base.Preconditions.checkNotNull;

public class FourOfKindRank extends AbstractRank {

    private Rank fourKindRank, otherRank;

    public FourOfKindRank(Rank fourKindRank, Rank otherRank) {
        checkNotNull(fourKindRank);
        checkNotNull(otherRank);
        this.fourKindRank = fourKindRank;
        this.otherRank = otherRank;
    }

    @Override
    public Integer getLevel() {
        return 8;
    }

    @Override
    int compareCurrentLevel(HandRank o) {
        FourOfKindRank next = (FourOfKindRank) o;
        return new CompareToBuilder().append(fourKindRank, next.fourKindRank).
                append(otherRank, next.otherRank).toComparison();
    }
}
