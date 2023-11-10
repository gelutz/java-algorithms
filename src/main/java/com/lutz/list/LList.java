package com.lutz.list;

import java.util.Arrays;

@SuppressWarnings("unchecked")
public class LList<T> {
    private T[] elements = (T[]) new Object[] {};
    private int length = 0;

    public LList() {}


    public int size() {
        return length;
    }

    private void grow() { grow(1); }
    private void grow(int i) {
        elements = Arrays.copyOf(elements, length + i);
        length += i;
    }

    private void shrink() { shrink(1); }
    private void shrink(int i) {
        elements = Arrays.copyOf(elements, length - i);
        length -= i;
    }

    public void add(T o) {
        grow();
        elements[length - 1] = o;
    }

    public void remove(int i) {
        T[] firstSection = Arrays.copyOfRange(elements, 0, i);
        T[] secondSection = Arrays.copyOfRange(elements, i + 1, length);

        T[] tempElements = (T[]) new Object[length - 1];

        System.arraycopy(firstSection, 0, tempElements, 0, firstSection.length);

        for (int j = 0; j < secondSection.length; j++) {
            int index = firstSection.length + j;
            tempElements[index] = secondSection[j];
        }

        shrink();
        elements = tempElements;
    }

    public T get(int index) {
        if (index < 0) {
            index = length + index;
        }

        return elements[index];
    }
}
