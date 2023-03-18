package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private int size;
    private final Node<T> sentinel;


    public LinkedListDeque() {
        this.size = 0;
        Node<T> sentinel = new Node<>(null);
        sentinel.prev = null;
        sentinel.next = null;
        this.sentinel = sentinel;
    }

    private static class Node<T> {
        private Node<T> prev;
        private Node<T> next;
        private final T item;

        private Node(T item) {
            this.item = item;
            this.prev = null;
            this.next = null;
        }
    }

    @Override
    public void addFirst(T item) {
        Node<T> newNode = new Node<>(item);

        Node<T> prevFirst = this.sentinel.next;
        if (prevFirst == null) {
            this.sentinel.prev = newNode;
            newNode.next = this.sentinel;
        } else {
            newNode.next = prevFirst;
            prevFirst.prev = newNode;
        }

        this.sentinel.next = newNode;
        newNode.prev = this.sentinel;

        this.size++;
    }

    @Override
    public void addLast(T item) {
        Node<T> newNode = new Node<>(item);

        Node<T> prevLast = this.sentinel.prev;
        if (prevLast == null) {
            this.sentinel.next = newNode;
            newNode.prev = this.sentinel;
        } else {
            newNode.prev = prevLast;
            prevLast.next = newNode;
        }

        this.sentinel.prev = newNode;
        newNode.next = this.sentinel;

        this.size++;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {
        if (this.isEmpty()) {
            return;
        }

        Node<T> currentNode = this.sentinel.next;

        while (currentNode != this.sentinel) {
            System.out.println(currentNode.item + " ");

            currentNode = currentNode.next;
        }

        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }

        Node<T> first = this.sentinel.next;

        if (this.size == 1) {
            this.sentinel.prev = null;
            this.sentinel.next = null;
        } else {
            this.sentinel.next = first.next;
            first.next.prev = this.sentinel;
        }

        this.size--;

        return first.item;
    }

    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }

        Node<T> last = this.sentinel.prev;

        if (this.size == 1) {
            this.sentinel.prev = null;
            this.sentinel.next = null;
        } else {
            this.sentinel.prev = last.prev;
            last.prev.next = this.sentinel;
        }

        this.size--;

        return last.item;
    }

    @Override
    public T get(int index) {
        if (this.isEmpty() || index >= this.size()) {
            return null;
        }

        Node<T> currentNode = this.sentinel.next;

        int count = 0;
        while (count < index) {
            currentNode = currentNode.next;
            count++;
        }

        return currentNode.item;
    }

    public T getRecursive(int index) {
        return getRecursive(index, sentinel.next);
    }

    private T getRecursive(int index, Node<T> node) {
        if (index == 0) {
            return node.item;
        }
        return getRecursive(index - 1, node.next);
    }


    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Deque)) {
            return false;
        }

        if (((Deque<?>) object).size() != this.size()) {
            return false;
        }

        int index = 0;

        while (index < this.size()) {

            if (!(this.get(index).equals(((Deque<?>) object).get(index)))) {
                return false;
            }

            index++;
        }

        return true;
    }


    private class AllItemsIterator implements Iterator<T>, Iterable<T> {
        Node<T> pointer;

        AllItemsIterator() {
            pointer = sentinel;
        }

        public boolean hasNext() {
            return pointer.next != sentinel && size != 0;
        }

        public T next() {
            pointer = pointer.next;
            return pointer.item;
        }

        public Iterator<T> iterator() {
            return this;
        }
    }

    public Iterator<T> iterator() {
        return new AllItemsIterator();
    }
}
