package be.yfrickx.app.day9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SequenceTest {

    @Test
    void getNextValue() {
        assertEquals(18, new Sequence("0 3 6 9 12 15").getNextValue());
        assertEquals(28, new Sequence("1 3 6 10 15 21").getNextValue());
        assertEquals(68, new Sequence("10 13 16 21 30 45").getNextValue());
        assertEquals(17, new Sequence("-1 2 5 8 11 14").getNextValue());
    }

    @Test
    void getPreviousValue() {
        assertEquals(5, new Sequence("10 13 16 21 30 45").getPreviousValue());
    }
}