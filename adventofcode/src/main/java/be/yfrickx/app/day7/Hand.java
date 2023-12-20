package be.yfrickx.app.day7;

import java.util.*;
import java.util.stream.Collectors;

public class Hand implements Comparable<Hand> {

    private List<Card> cards = new ArrayList<>();

    private String handString;
    private final long bid;

    private final Type type;

    private enum Type {
        FiveOfAKind(7),
        FourOfAKind(6),
        FullHouse(5),
        ThreeOfAKind(4),
        TwoPair(3),
        Pair(2),
        HighCard(1);

        public final int value;
        Type(int i) {
            this.value = i;
        }
    }

    public Hand(String inputString) {
        String handString = inputString.split(" ")[0];
        char[] arr = handString.toCharArray();
        for (char c : arr) {
            cards.add(new Card(c));
        }

        this.handString = handString;

        this.type = getType();

        bid = Long.parseLong(inputString.split(" ")[1]);
    }

    private Type getType() {
        Map<Character, Long> handMap = new HashMap<>();
        cards.forEach(card -> {
            if (!handMap.containsKey(card.getCardValue())) {
                handMap.put(card.getCardValue(), cards.stream().filter(crd -> crd.equals(card)).count());
            }
        });

        int distinctCount = handMap.size();
        long maxAmount = handMap.values().stream().mapToLong(l -> l).max().getAsLong();

        if (maxAmount == 5) {
            return Type.FiveOfAKind;
        } else if (maxAmount == 4) {
            return Type.FourOfAKind;
        } else if (maxAmount == 3) {
            if (distinctCount == 2) {
                return Type.FullHouse;
            }
            return Type.ThreeOfAKind;
        } else if (maxAmount == 2) {
            if (distinctCount == 3) {
                return Type.TwoPair;
            } else {
                return Type.Pair;
            }
        }
        return Type.HighCard;
    }

    public long getBid() {
        return bid;
    }

    public String getHandString() {
        return handString;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hand)) return false;
        Hand hand = (Hand) o;
        return Objects.equals(cards, hand.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

    @Override
    public String toString() {
        return String.format("%s, type: %s", this.handString, this.type);
    }

    @Override
    public int compareTo(Hand o) {
        if (this.equals(o)) {
            return 0;
        }

        int compare = Integer.compare(this.type.value, o.type.value);

        if (compare == 0) {
            // same hand type -> first card counts
            if (!cards.get(0).equals(o.cards.get(0))) {
                return cards.get(0).compareTo(o.cards.get(0));
            }

            if (!cards.get(1).equals(o.cards.get(1))) {
                return cards.get(1).compareTo(o.cards.get(1));
            }
            if (!cards.get(2).equals(o.cards.get(2))) {
                return cards.get(2).compareTo(o.cards.get(2));
            }
            if (!cards.get(3).equals(o.cards.get(3))) {
                return cards.get(3).compareTo(o.cards.get(3));
            }
            return cards.get(4).compareTo(o.cards.get(4));
        }
        return compare;
    }
}
