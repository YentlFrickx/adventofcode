package be.yfrickx.app.day7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Card implements Comparable<Card> {

    List<Character> order = new ArrayList<>(
            Arrays.asList(
                    'A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2', '1')
    );

    private final char cardValue;
    public Card(char cardChar) {
        cardValue = cardChar;
    }

    public char getCardValue() {
        return cardValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return cardValue == card.cardValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardValue);
    }

    @Override
    public int compareTo(Card o) {
        if (this.equals(o)) {
            return 0;
        }

        return Integer.compare(order.indexOf(o.cardValue), order.indexOf(this.cardValue));
    }
}
