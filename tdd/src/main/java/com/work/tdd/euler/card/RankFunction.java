package com.work.tdd.euler.card;

import com.google.common.base.Predicate;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class RankFunction {

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
}
