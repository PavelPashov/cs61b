package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {

    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> aListNoResizing = new AListNoResizing<>();
        BuggyAList<Integer> buggyAList = new BuggyAList<>();

        for (int i = 0; i < 3; i++) {
            aListNoResizing.addLast(i);
            buggyAList.addLast(i);
        }

        assertEquals(aListNoResizing.size(), buggyAList.size());

        for (int i = 2; i >= 0; i--) {
            assertEquals(aListNoResizing.removeLast(), buggyAList.removeLast());
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> buggyAList = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                buggyAList.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int buggySize = buggyAList.size();
                assertEquals(size, buggySize);
            } else if (operationNumber == 2 && L.size() > 0) {
                int last = L.getLast();
                int buggyLast = buggyAList.getLast();
                assertEquals(last, buggyLast);
            } else if (operationNumber == 3 && L.size() > 0) {
                int removed = L.removeLast();
                int buggyRemoved = buggyAList.removeLast();
                assertEquals(removed, buggyRemoved);
            }
        }
    }

}
