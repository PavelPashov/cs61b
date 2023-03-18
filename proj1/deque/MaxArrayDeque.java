package deque;

import java.util.Comparator;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

public class MaxArrayDeque<T> extends ArrayDeque<T> implements Iterable<T> {
    Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        this.comparator = c;
    }

    public T max() {
        if (this.isEmpty()) {
            return null;
        }

        T maxItem = null;

        for (T item : this) {
            int result = comparator.compare(item, maxItem);

            if (result > 0) {
                maxItem = item;
            }
        }

        return maxItem;
    }

    public T max(Comparator<T> c) {
        if (this.isEmpty()) {
            return null;
        }

        T maxItem = null;

        for (T item : this) {
            int result = c.compare(item, maxItem);

            if (result > 0) {
                maxItem = item;
            }
        }

        return maxItem;
    }
}
