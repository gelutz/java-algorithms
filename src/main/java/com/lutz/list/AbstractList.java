package com.lutz.list;

import java.util.Iterator;

public abstract class AbstractList<T> implements Iterable<T> {
    protected int length = 0;
    public Integer size() {
        return length;
    }

    public abstract void sort();
    public abstract void add(T object);
    @SuppressWarnings("unchecked") public abstract void addMany(T... objects);
    public abstract void remove(int index);
    public abstract T get(int index);
    public abstract Integer index(T object);
    public abstract Iterator<T> iterator();
    public abstract AbstractList<T> sublist(int start, int end);
}
