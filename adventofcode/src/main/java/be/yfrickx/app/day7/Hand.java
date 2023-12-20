package be.yfrickx.app.day7;

import java.util.*;

public class Hand implements Comparable<Hand> {

    private List<Card> cards = new ArrayList<>();

    private String handString;
    private final long bid;

    private final Type type;

    enum Type {
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

    public Type getType() {
        Map<Character, Long> handMap = new HashMap<>();
        cards.forEach(card -> {
            if (!handMap.containsKey(card.getCardValue())) {
                handMap.put(card.getCardValue(), cards.stream().filter(crd -> crd.equals(card)).count());
            }
        });

        int distinctCount = handMap.size();
        long maxAmount = handMap.values().stream().mapToLong(l -> l).max().getAsLong();

        long amountJ = 0;
        if (handMap.containsKey('J')) {
            amountJ = handMap.get('J');
        }

        if (distinctCount == 1) {
            return Type.FiveOfAKind;
        } else if (distinctCount == 2) {
            // Possibilities (without special J): 4 of a kind, fullhouse
            if (amountJ > 0) {
                return Type.FiveOfAKind;
            } else if (maxAmount == 4) {
                return Type.FourOfAKind;
            } else {
                return Type.FullHouse;
            }
        } else if (distinctCount == 3) {
            if (maxAmount == 3) {
                // Three of a kind
                // JJJ45 -> 4 of a kind
                // 44498 -> 3 of a kind
                // 444J1 -> 4 of a kind
                // JJ not possible
                if (amountJ > 0) {
                    return Type.FourOfAKind;
                } else {
                    return Type.ThreeOfAKind;
                }
            } else {
                // maxAmount = 2
                // JJ22A
                // 22334
                // 2233J
                if (amountJ == 2) {
                    return Type.FourOfAKind;
                } else if (amountJ == 1) {
                    return Type.FullHouse;
                } else {
                    return Type.TwoPair;
                }
            }
        } else if (distinctCount == 4) {
            // 11234
            // JJ234
            // 1123J
            if (amountJ > 0) {
                return Type.ThreeOfAKind;
            } else {
                return Type.Pair;
            }
        } else {
            return Type.Pair;
        }
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
        return String.format("%s, type: %s, J: %d, Distinct: %s",
                this.handString,
                this.type,
                this.handString.chars().filter(ch -> ch == 'J').count(),
                this.handString.chars().distinct().count()
        );
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
