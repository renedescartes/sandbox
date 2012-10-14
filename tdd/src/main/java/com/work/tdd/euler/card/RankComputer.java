package com.work.tdd.euler.card;

public interface RankComputer<E extends HandRank> {

    E handRank(Hand h);
}
