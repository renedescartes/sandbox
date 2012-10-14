package com.work.tdd.euler.card;

import com.work.tdd.euler.card.impl.*;

import java.util.ArrayList;
import java.util.List;

public class HandRankComputer {

    private static List<RankComputer<? extends HandRank>> rankComputers = new ArrayList<>();

    static {
        rankComputers.add(new RoyalFlushComputer());
        rankComputers.add(new StraightFlushComputer());
        rankComputers.add(new FourOfKindComputer());
        rankComputers.add(new FullHouseComputer());
        rankComputers.add(new FlushComputer());
        rankComputers.add(new StraightComputer());
        rankComputers.add(new ThreeKindComputer());
        rankComputers.add(new TwoPairComputer());
        rankComputers.add(new OnePairComputer());
        rankComputers.add(new HighestCardComputer());
    }

    public static HandRank computeRank(Hand h) {
        for (RankComputer r : rankComputers) {
            HandRank handRank = r.handRank(h);
            if (handRank != null) {
                return handRank;
            }
        }
        throw new RuntimeException("Could not compute rank for " + h);
    }
}
