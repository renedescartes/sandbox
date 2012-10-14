package com.work.tdd.euler.card;

import org.apache.commons.lang.builder.CompareToBuilder;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class ThreeKindRank extends AbstractRank {

    private Rank threeKindRank, otherRank1, otherRank2;

    public ThreeKindRank(Rank threeKindRank, Rank otherRank1, Rank otherRank2) {
        checkNotNull(threeKindRank);
        checkNotNull(otherRank1);
        checkNotNull(otherRank2);
        checkArgument(otherRank1 != otherRank2, "In three kind the other ranks have to be different");
        this.threeKindRank = threeKindRank;
        this.otherRank1 = otherRank1;
        this.otherRank2 = otherRank2;
    }

    @Override
    public Integer getLevel() {
        return 4;
    }

    @Override
    int compareCurrentLevel(HandRank o) {
        ThreeKindRank next = (ThreeKindRank) o;
        return new CompareToBuilder().append(threeKindRank, next.threeKindRank).
                append(otherRank1, next.otherRank1).
                append(otherRank2, next.otherRank2).toComparison();
    }
}
