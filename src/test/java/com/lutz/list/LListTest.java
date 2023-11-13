package com.lutz.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;


public class LListTest {
    LList<Integer> sut;

    @BeforeEach
    public void setUp() {
        sut = new LList<>();
    }

    @Test
    public void assertGrows() {
        sut.add(1);

        assert sut.size() == 1;
    }

    @Test
    public void assertShrinks() {
        sut.add(1);

        assert sut.size() == 1;

        sut.remove(0);

        assert sut.size() == 0;
    }

    @Test
    @DisplayName("Garante que não tem como retornar um elemento em um index que não existe")
    public void assertDoesNotAcceptIndexOutOfBounds() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> sut.get(0));
    }

    @Test
    public void assertElementWasAdded() {
        sut.add(1);

        assert sut.get(0).equals(1);
    }

    @Test
    public void assertAnyTypeOfObjectCanBeAdded() {
        LList<String> strings = new LList<>();
        strings.add("");

        assert strings.get(0) == "";

        LList<Boolean> booleans = new LList<>();
        booleans.add(true);

        assert booleans.get(0);
    }

    @Test
    public void assertGetsWithNegativeIndex() {
        sut.add(1);
        sut.add(2);
        sut.add(3);

        assert sut.get(-1).equals(3);
    }

    @Test
    public void assertCorrectElementsAreRemoved() {
        sut.add(1);
        sut.add(2);
        sut.add(3);

        sut.remove(1);

        assert sut.get(0).equals(1);
        assert sut.get(1).equals(3);

        sut.remove(0);

        assert sut.get(0).equals(3);
    }

    @Test
    public void assertThatFindsTheElement() {
        sut.add(1);
        sut.add(2);

        assert sut.find(1) == 0;
        assert sut.find(2) == 1;
    }

    @Test
    public void assertThatFindsInTheRightIndex() {
        sut.add(2);
        sut.add(3);
        sut.add(2); // repetido de propósito
        sut.add(1);

        assert sut.find(2) == 0;
        assert sut.find(1) == 3;
    }

    @Test
    public void assertThatItIsIterable() {
        sut.add(1);
        sut.add(2);
        sut.add(3);

        LList listed = new LList();

        for (Integer i: sut) {
            listed.add(i);
        }

        assert sut.get(0) == listed.get(0);
        assert sut.get(1) == listed.get(1);
        assert sut.get(2) == listed.get(2);
    }
}
