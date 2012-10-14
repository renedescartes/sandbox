package com.work.tdd.euler.card;

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
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
