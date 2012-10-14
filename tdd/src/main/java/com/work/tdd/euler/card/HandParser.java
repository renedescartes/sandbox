package com.work.tdd.euler.card;

import com.google.common.base.Function;

import javax.annotation.Nullable;
import java.util.List;

import static com.google.common.base.Preconditions.*;
import static com.google.common.collect.Collections2.transform;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.Arrays.asList;
import static org.apache.commons.lang.StringUtils.isNotEmpty;

public class HandParser {

    public static Hand parseHand(String hand) {
        checkState(isNotEmpty(hand));
        String[] cardStrings = hand.split(" ");
        checkState(cardStrings.length == 5, "Need atleast 5 cards");
        List<Card> cards = newArrayList(transform(asList(cardStrings), stringToCardFunction()));
        return new Hand(cards);
    }

    private static Function<String, Card> stringToCardFunction() {
        return new Function<String, Card>() {
            @Override
            public Card apply(@Nullable String input) {
                checkNotNull(input);
                checkArgument(input.length() == 2);
                return new Card(Rank.parseRank(input.charAt(0)), Kind.parseKind(input.charAt(1)));
            }
        };
    }
}
