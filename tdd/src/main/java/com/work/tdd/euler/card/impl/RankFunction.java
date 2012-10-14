package com.work.tdd.euler.card.impl;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.work.tdd.euler.card.Card;
import com.work.tdd.euler.card.Hand;
import com.work.tdd.euler.card.Kind;
import com.work.tdd.euler.card.Rank;
import org.apache.commons.lang.builder.CompareToBuilder;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class RankFunction {

    public static int rankComparison(List<Rank> ranks1, List<Rank> ranks2) {
        Preconditions.checkState(ranks1.size() == ranks2.size(), "Two ranks are not same size");
        CompareToBuilder b = new CompareToBuilder();
        for (int i = 0; i < ranks1.size(); i++) {
            b.append(ranks1.get(i), ranks2.get(i));
        }
        return b.toComparison();
    }

    public static List<Rank> pairRanks(Hand h) {
        List<Rank> pairRanks = new ArrayList<>();
        for (Rank r : Rank.values()) {
            if (countForRank(h, r) == 2) {
                pairRanks.add(r);
            }
        }
        return pairRanks;
    }

    public static boolean checkStraight(Hand h) {
        List<Card> cards = h.getCards();
        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i).getRank().ordinal() != cards.get(i + 1).getRank().ordinal() - 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkFlush(Hand h) {
        for (Kind k : Kind.values()) {
            if (countForKind(h, k) == 5) {
                return true;
            }
        }
        return false;
    }

    public static Rank[] fullHouse(Hand h) {
        Rank r1 = threeOfAKind(h);
        List<Rank> pairRanks = pairRanks(h);
        if (r1 != null && !pairRanks.isEmpty()) {
            return new Rank[]{r1, pairRanks.get(0)};
        } else {
            return null;
        }
    }

    public static Rank threeOfAKind(Hand h) {
        for (Rank r : Rank.values()) {
            if (countForRank(h, r) == 3) {
                return r;
            }
        }
        return null;
    }

    public static Rank checkFourOfAKind(Hand h) {
        for (Rank r : Rank.values()) {
            if (countForRank(h, r) == 4) {
                return r;
            }
        }
        return null;
    }

    public static boolean isRoyalFlush(Hand h) {
        return containsRankOfAnyKind(h, Rank.TEN, 5);
    }

    public static Rank checkStraightFlush(Hand h) {
        for (Rank r : Rank.values()) {
            if (r.ordinal() < Rank.TEN.ordinal()) {
                if (containsRankOfAnyKind(h, r, 5)) {
                    return Rank.values()[r.ordinal() + 4];
                }
            }
        }
        return null;
    }

    public static boolean containsRankOfAnyKind(Hand h, Rank start, int successive) {
        for (Kind k : Kind.values()) {
            if (containsRanks(h, start, k, successive)) {
                return true;
            }
        }
        return false;
    }


    public static boolean containsRanks(Hand h, Rank start, Kind k, int successive) {
        for (int i = 0; i < successive; i++) {
            Rank rank = Rank.values()[start.ordinal() + successive];
            if (!containsCard(h, rank, k)) {
                return false;
            }
        }
        return true;
    }

    public static boolean containsCard(Hand h, Rank r, Kind k) {
        Card c = new Card(r, k);
        for (Card card : h.getCards()) {
            if (card.equals(c)) {
                return true;
            }
        }
        return false;
    }

    public static int countForRank(Hand h, Rank r) {
        int count = 0;
        for (Card c : h.getCards()) {
            if (c.getRank().equals(r)) {
                count++;
            }
        }
        return count;
    }

    public static int countForKind(Hand h, Kind k) {
        int count = 0;
        for (Card c : h.getCards()) {
            if (c.getKind().equals(k)) {
                count++;
            }
        }
        return count;
    }

    public static <T> T last(List<T> list) {
        return list.get(list.size() - 1);
    }

    public static Predicate<Card> rankPredicate(final Rank r) {
        return new Predicate<Card>() {
            @Override
            public boolean apply(@Nullable Card input) {
                return input.getRank().equals(r);
            }
        };
    }

    public static Function<Card, Rank> rankTransform() {
        return new Function<Card, Rank>() {
            @Override
            public Rank apply(@Nullable Card input) {
                return input.getRank();
            }
        };
    }
}
