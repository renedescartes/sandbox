package com.work.tdd.euler.card;

public class Card implements Comparable<Card> {

    private Rank rank;
    private Kind kind;

    public Card(Rank rank, Kind kind) {
        this.rank = rank;
        this.kind = kind;
    }

    public Rank getRank() {
        return rank;
    }

    public Kind getKind() {
        return kind;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (kind != card.kind) return false;
        if (rank != card.rank) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rank != null ? rank.hashCode() : 0;
        result = 31 * result + (kind != null ? kind.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{" + rank.c + kind.c + '}';
    }

    @Override
    public int compareTo(Card o) {
        return rank.compareTo(o.getRank());
    }
}