package com.lutz.list;

import java.util.Iterator;

// empaquei tentando descobrir por que minha lista estava na ordem inversa, sendo que a lógica parece certa
// pesquisei um pouco mas não achei informações sobre isso ser o comportamento comum de uma linked list
// assim é mais parecido com um stack do que com uma lista (pra stack seria útil essa implementação)
public class LLinkedList<E> extends AbstractList<E> {
    private Node<E> head = new Node<>(null);
    private Node<E> tail = null;

    public LLinkedList() {}

    @Override
    public void add(E element) {
        Node<E> n = new Node<>(element);
        Node<E> temp = head;

        head = n;
        head.next = temp;
        if (tail == null) tail = n;

        length++;
    }

    @SuppressWarnings("unchecked")
    public void addMany(E... objects) {
        for(E o: objects) {
            add(o);
        }
    }

    @Override
    public void remove(int index) {

    }

    @Override
    public E get(int index) {
        if (index < 0) {
            index = length + index;
        }

        int count = 0;
        Node<E> temp = head;
        while (temp.data != null) {
            if (count == index) {
                return head.data;
            }

            temp = temp.next;
            count++;
        }

        throw new IndexOutOfBoundsException();
    }

    @Override
    public Integer index(E object) {
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return new LLinkedIterator<>(head);
    }

    public LLinkedList<E> sublist(int start, int end) {
        Node<E> cursor = head;
        int count = 0;

        LLinkedList<E> sublist = new LLinkedList<>();
        while (cursor.data != null) {
            if (count >= start && count <= end) {
                sublist.add(cursor.data);
            }

            count++;
            cursor = cursor.next;
        }

        return sublist;
    }

    public void sort() {}

    protected static class Node<T> {
        T data;
        Node<T> next;

        Node(T element) {
            data = element;
            next = null;
        }
    }

    private static final class LLinkedIterator<T> implements Iterator<T> {
        private Node<T> cursor;

        public LLinkedIterator(Node<T> start) {
            cursor = start;
        }

        @Override
        public boolean hasNext() {
            return cursor.next != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException();
            }

            T data = cursor.data;
            cursor = cursor.next;

            return data;
        }
    }
}
