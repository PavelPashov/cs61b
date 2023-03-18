package deque;

import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ArrayDequeTest {
    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<String> arrayDeque = new ArrayDeque<String>();

        assertTrue("A newly initialized LLDeque should be empty", arrayDeque.isEmpty());
        arrayDeque.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, arrayDeque.size());
        assertFalse("arrayDeque should now contain 1 item", arrayDeque.isEmpty());

        arrayDeque.addLast("middle");
        assertEquals(2, arrayDeque.size());

        arrayDeque.addLast("back");
        assertEquals(3, arrayDeque.size());

        System.out.println("Printing out deque: ");
        arrayDeque.printDeque();

    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> arrayDeque = new ArrayDeque<Integer>();
        // should be empty
        assertTrue("lld1 should be empty upon initialization", arrayDeque.isEmpty());

        arrayDeque.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", arrayDeque.isEmpty());

        arrayDeque.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", arrayDeque.isEmpty());

    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        arrayDeque.addFirst(3);

        arrayDeque.removeLast();
        arrayDeque.removeFirst();
        arrayDeque.removeLast();
        arrayDeque.removeFirst();

        int size = arrayDeque.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);

    }

    @Test
    /* Check if you can create ArrayDeques with different parameterized types*/
    public void multipleParamTest() {
        ArrayDeque<String>  arrayDeque1 = new ArrayDeque<String>();
        ArrayDeque<Double>  arrayDeque2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> arrayDeque3 = new ArrayDeque<Boolean>();

        arrayDeque1.addFirst("string");
        arrayDeque2.addFirst(3.14159);
        arrayDeque3.addFirst(true);

        String s = arrayDeque1.removeFirst();
        double d = arrayDeque2.removeFirst();
        boolean b = arrayDeque3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty ArrayDeque. */
    public void emptyNullReturnTest() {
        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> arrayDeque = new ArrayDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, arrayDeque.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, arrayDeque.removeLast());
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {
        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> arrayDeque = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            arrayDeque.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) arrayDeque.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) arrayDeque.removeLast(), 0.0);
        }
    }

    @Test
    public void checkEqualsTrueTest() {
        ArrayDeque<String> firstIntList = new ArrayDeque<String>();
        ArrayDeque<String> secondIntList = new ArrayDeque<String>();

        firstIntList.addFirst("second");
        firstIntList.addFirst("first");

        secondIntList.addFirst("second");
        secondIntList.addFirst("first");

        assertTrue(firstIntList.equals(secondIntList));
    }

    @Test
    public void checkGetTest() {
        ArrayDeque<String> strList = new ArrayDeque<String>();

        strList.addFirst("second");
        strList.addFirst("first");

        assertTrue(Objects.equals(strList.get(1), "second"));
    }

    @Test
    public void checkEqualsFalseTypesTest() {
        ArrayDeque<Integer> intList = new ArrayDeque<Integer>();
        ArrayDeque<String> stringList = new ArrayDeque<String>();

        intList.addFirst(1);
        intList.addFirst(2);
        stringList.addFirst("1");
        stringList.addFirst("2");

        assertFalse(intList.equals(stringList));
    }

    @Test
    public void checkEqualsFalseLengthTest() {
        ArrayDeque<Integer> firstIntList = new ArrayDeque<Integer>();
        ArrayDeque<Integer> secondIntList = new ArrayDeque<Integer>();

        firstIntList.addFirst(1);
        firstIntList.addFirst(2);

        secondIntList.addFirst(2);

        assertFalse(firstIntList.equals(secondIntList));
    }

    @Test
    public void checkEqualsFalseOrderTest() {
        ArrayDeque<Integer> firstIntList = new ArrayDeque<Integer>();
        ArrayDeque<Integer> secondIntList = new ArrayDeque<Integer>();

        firstIntList.addFirst(1);
        firstIntList.addFirst(2);

        secondIntList.addFirst(2);
        secondIntList.addFirst(1);

        assertFalse(firstIntList.equals(secondIntList));
    }

    @Test
    public void iterateTest() {
        ArrayDeque<Integer> intList = new ArrayDeque<Integer>();

        intList.addFirst(3);
        intList.addFirst(2);
        intList.addFirst(1);

        int index = 1;
        for (int item : intList) {
            assertEquals("Should be the same number", index, item);
            index++;
        }
    }
}
