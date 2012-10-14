package com.work.tdd.euler.card;

import org.apache.commons.lang.builder.CompareToBuilder;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.reverse;

public class FlushRank extends AbstractRank {
    private List<Card> cards;

    public FlushRank(List<Card> cards) {
        this.cards = reverse(newArrayList(cards));
    }

    @Override
    int compareCurrentLevel(HandRank o) {
        CompareToBuilder b = new CompareToBuilder();
        List<Card> otherCards = reverse(((FlushRank) o).cards);
        for (int i = 0; i < cards.size(); i++) {
            b.append(cards.get(i), otherCards.get(i));
        }
        return b.toComparison();
    }

    @Override
    public Integer getLevel() {
        return 9;
    }
}
