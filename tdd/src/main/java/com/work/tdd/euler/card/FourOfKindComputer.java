package com.work.tdd.euler.card;

public class FourOfKindComputer implements RankComputer<FourOfKindRank> {
    @Override
    public FourOfKindRank handRank(Hand h) {
        Rank rank = RankFunction.checkFourOfAKind(h);
        return rank != null ? new FourOfKindRank(rank, otherRank(h, rank)) : null;
    }

    private static Rank otherRank(Hand h, Rank fourRank) {
        for (Card c : h.getCards()) {
            if (!c.getRank().equals(fourRank)) {
                return c.getRank();
            }
        }
        return null;
    }
}
