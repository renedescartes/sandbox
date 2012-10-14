package com.work.tdd.euler.card;

import com.google.common.base.Function;
import com.work.tdd.euler.Utility;

import javax.annotation.Nullable;
import java.util.ArrayList;
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
        try {
            return new Hand(cards);
        } catch (Exception e) {
            throw new RuntimeException("Could not parse [" + hand + "]", e);
        }
    }

    public static List<List<Hand>> parseListOfGames(String resource) {
        List<String> strings = Utility.readFile(resource);
        List<List<Hand>> games = new ArrayList<>();
        for (String s : strings) {
            int midPoint = s.length() / 2;
            Hand hand1 = parseHand(s.substring(0, midPoint));
            Hand hand2 = parseHand(s.substring(midPoint + 1));
            games.add(asList(hand1, hand2));
        }
        return games;
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
