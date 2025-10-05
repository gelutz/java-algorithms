package com.lutz.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LList<E> extends AbstractList<E> {
    private E[] elements = (E[]) new Object[] {};

    public LList() {
    }

    static <E> LList<E> of(E[] inputArray) {
        LList<E> newList = new LList<>();

        for (E t : inputArray) {
            newList.add(t);
        }

        return newList;
    }

    private void grow() {
        grow(1);
    }

    private void grow(int i) {
        elements = Arrays.copyOf(elements, length + i);
        length += i;
    }

    private void shrink() {
        elements = Arrays.copyOf(elements, length - 1);
        length -= 1;
    }

    public Iterator<E> iterator() {
        return new LIterator<>(0, elements);
    }

    public void add(E object) {
        grow();
        elements[length - 1] = object;
    }

    public void addMany(E... objects) {
        grow(objects.length);

        Iterator<E> it = Arrays.stream(objects).iterator();
        for (int i = objects.length; i > 0; i--) {
            elements[length - i] = it.next();
        }
    }

    public void remove(int i) {
        E[] firstSection = Arrays.copyOfRange(elements, 0, i);
        E[] secondSection = Arrays.copyOfRange(elements, i + 1, length);

        E[] tempElements = (E[]) new Object[length - 1];

        System.arraycopy(firstSection, 0, tempElements, 0, firstSection.length);

        for (int j = 0; j < secondSection.length; j++) {
            int index = firstSection.length + j;
            tempElements[index] = secondSection[j];
        }

        shrink();
        elements = tempElements;
    }

    public E get(int index) {
        if (index < 0) {
            index = length + index;
        }

        return elements[index];
    }

    public Integer index(E element) {
        // TODO: implementar uma busca melhor (logn)
        for (int i = 0; i < elements.length; i++) {
            if (elements[i].equals(element)) {
                return i;
            }
        }

        throw new NoSuchElementException();
    }

    public LList<E> sublist(int start, int end) {
        int plus = (end == length) ? 0 : 1;
        E[] newList = Arrays.copyOfRange(elements, start, end + plus);
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

        public boolean hasNext() {
            return cursor < end;
        }

        public T next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException();
            }

            return elements[cursor++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}
