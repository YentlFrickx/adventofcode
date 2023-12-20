package be.yfrickx.app.day7;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    @Test
    void equals() {
        assertEquals(new Hand("AAAAA 1"), new Hand("AAAAA 1"));
        assertEquals(new Hand("22222 1"), new Hand("22222 1"));
    }

    @Test
    void compareTo() {
        assertEquals(new Hand("AAAAA 1").compareTo(new Hand("KKKKK 1")), 1);
        assertEquals(new Hand("AAKKK 1").compareTo(new Hand("KKKKK 1")), -1);
        assertEquals(new Hand("AAKKK 1").compareTo(new Hand("KKAAA 1")), 1);
        assertEquals(new Hand("33388 1").compareTo(new Hand("KKAAA 1")), -1);
        assertEquals(new Hand("22786 1").compareTo(new Hand("33675 1")), -1);
    }

    @Test
    void sort() {
        String input = "32T3K 765\n" +
                "T55J5 684\n" +
                "KK677 28\n" +
                "KTJJT 220\n" +
                "QQQJA 483";
        List<Hand> hands = input.lines().map(Hand::new).collect(Collectors.toList());

        Collections.sort(hands);
        int sum = 0;
        for (int i = 0; i < hands.size(); i++) {
            sum += hands.get(i).getBid() * (i + 1);
        }
        assertEquals(sum, 6440);
    }
}