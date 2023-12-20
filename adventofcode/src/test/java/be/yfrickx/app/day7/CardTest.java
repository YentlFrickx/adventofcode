package be.yfrickx.app.day7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void compareTo() {
        assertTrue(new Card('A').compareTo(new Card('K')) > 0);
        assertTrue(new Card('A').compareTo(new Card('T')) > 0);
        assertTrue(new Card('A').compareTo(new Card('2')) > 0);
        assertTrue(new Card('2').compareTo(new Card('A')) < 0);
        assertTrue(new Card('2').compareTo(new Card('6')) < 0);
        assertTrue(new Card('A').compareTo(new Card('7')) > 0);
        assertTrue(new Card('9').compareTo(new Card('T')) < 0);
        assertTrue(new Card('J').compareTo(new Card('4')) > 0);
    }
}