package deque;

import java.util.Iterator;

public class ArrayDeque <T> implements Deque<T>, Iterable<T> {
    private T[] items;

    private int size;

    private int firstNext;

    private int lastNext;

    private enum ShiftBy {
        ONE_UP,
        ONE_DOWN,
        NORMAL
    }


    public ArrayDeque () {
        this.items = (T[]) new Object[8];
        this.size = 0;
        this.firstNext = 4;
        this.lastNext = 5;
    }

    private int returnOneIndexDown(int index) {
        if (index == 0) {
            return this.items.length - 1;
        }
        return index - 1;
    }

    private void resize(int capacity) {
        T[] tempArray = (T []) new Object[capacity];

        int index = this.firstNext + 1;
        int count = 0;
        while (count < this.size) {
            index = index % (this.items.length);
            tempArray[count] = this.items[index];
            index++;
            count++;
        }

        this.firstNext = capacity - 1;
        this.lastNext = this.size;

        items = tempArray;
    }

    @Override
    public void addFirst(T item) {
        if (this.size == items.length) {
            resize(size * 2);
        }

        this.items[firstNext] = item;

        this.firstNext = returnOneIndexDown(this.firstNext);

        this.size++;
    }

    @Override
    public void addLast(T item) {
        if (this.size() == items.length) {
            this.resize(this.size * 2);
        }

        this.items[lastNext] = item;

        this.lastNext = (lastNext + 1) % this.items.length;

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
        int index = this.firstNext + 1;
        while (index != this.lastNext) {
            index = index % this.items.length;
            System.out.println(this.items[index] + " ");
            index++;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        if (this.size > 15 && this.size < items.length / 4) {
            this.resize(items.length / 4);
        }

        int currentFirst;

        if (this.firstNext == this.items.length - 1) {
            currentFirst = 0;
        } else {
            currentFirst = this.firstNext + 1;
        }

        T item = this.items[currentFirst];

        this.firstNext = currentFirst;

        this.items[currentFirst] = null;

        this.size--;

        return item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        if (this.size > 15 && this.size < items.length / 4) {
            this.resize(items.length / 4);
        }

        int currentLast;
        if (this.lastNext == 0) {
            currentLast = this.items.length - 1;
        } else {
            currentLast = this.lastNext - 1;
        }

        T item = this.items[currentLast];

        this.lastNext = currentLast;

        this.items[currentLast] = null;

        this.size--;

        return item;
    }

    @Override
    public T get(int index) {
        if (isEmpty()) {
            return null;
        }

        int finalIndex = (this.firstNext + 1 + index) % this.items.length;

        int last = returnOneIndexDown(lastNext);
        int first = (firstNext + 1) % this.items.length;

        if (last < first) {
            if (finalIndex > last && finalIndex < first) {
                return null;
            }
        } else {
            if (finalIndex < first || finalIndex > last) {
                return null;
            }
        }

        return this.items[finalIndex];
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Deque)) {
            return false;
        }

        if (((Deque<?>) object).size() != this.size()) {
            return false;
        }

        int index = this.firstNext + 1;
        int count = 0;
        while (count < this.size) {
            index = index % (this.items.length);

            if (!(this.items[index].equals(((Deque<?>) object).get(count)))) {
                return false;
            }

            index++;
            count++;
        }

        return true;
    }

    private class AllItemsIterator implements Iterator<T>, Iterable<T> {
        int pointer;

        AllItemsIterator() {
            pointer = (firstNext + 1) % items.length;
        }

        public boolean hasNext() {
            return pointer != returnOneIndexDown(lastNext);
        }

        public T next() {
            T item = items[pointer];
            pointer =  (pointer + 1) % items.length;
            return item;
        }

        public Iterator<T> iterator() {
            return this;
        }
    }

    public Iterator<T> iterator() {
        return new ArrayDeque.AllItemsIterator();
    }
}
