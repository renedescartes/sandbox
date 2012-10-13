package com.work.tdd.euler.card;

import java.util.List;

public class RankFunction {

    public static Rank computeFlush(Hand h) {
        for (Kind k : Kind.values()) {
            if (countForKind(h, k) == 5) {
                return last(h.getCards()).getRank();
            }
        }
        return null;
    }

    public static Rank[] fullHouse(Hand h) {
        Rank r1 = threeOfAKind(h);
        Rank r2 = pairKind(h);
        if (r1 != null && r2 != null) {
            return new Rank[]{r1, r2};
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

    public static Rank pairKind(Hand h) {
        for (Rank r : Rank.values()) {
            if (countForRank(h, r) == 2) {
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
}
