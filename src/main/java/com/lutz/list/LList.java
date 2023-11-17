package com.lutz.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")

public class LList<T> implements Iterable<T> {
    private T[] elements = (T[]) new Object[] {};
    private int length = 0;

    public LList() {}

    public static <T> LList of(T[] inputArray) {
        LList newList = new LList<>();

        for (T t : inputArray) {
            newList.add(t);
        }

        return newList;
    }

    public Iterator<T> iterator() {
        return new LIterator(0, elements);
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

    public int size() {
        return length;
    }

    public void add(T object) {
        grow();
        elements[length - 1] = object;
    }

    public void addMany(T... objects) {
        for (T o : objects) {
            add(o);
        }
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

    public int find(T element) {
        // TODO: implementar uma busca melhor (logn)
        for (int i = 0; i < elements.length; i++) {
            if (elements[i].equals(element)) {
                return i;
            }
        }

        throw new NoSuchElementException();
    }

    public LList<T> sublist(int start, int end) {
        int plus = (end == length) ? 0 : 1;
        T[] newList = Arrays.copyOfRange(elements, start, end + plus);
        return LList.of(newList);
    }

    public void sort() {
        Arrays.sort(elements);
    }

    private static final class LIterator<T> implements Iterator<T> {
        private int cursor;
        private final int end;
        private final T[] elements;

        public LIterator(int start, T[] items) {
            elements = items;
            cursor = start;
            end = items.length;
        }


        @Override
        public boolean hasNext() {
            return cursor < end;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return elements[cursor++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}
