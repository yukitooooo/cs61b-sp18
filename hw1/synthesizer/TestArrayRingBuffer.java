package synthesizer;
import org.junit.Test;

import java.util.Iterator;
import java.util.Optional;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    //static ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<Integer>();

    @Test(expected = Exception.class)
    public void testEnqueue() {
        ArrayRingBuffer ar1 = new ArrayRingBuffer(5);
        ar1.enqueue(0);
        ar1.enqueue(1);
        ar1.enqueue(2);
        ar1.enqueue(3);
        ar1.enqueue(4);
        ar1.enqueue(5);
    }

    @Test(expected = Exception.class)
    public void testDequeue1() {
        ArrayRingBuffer arb = new ArrayRingBuffer(5);
        arb.dequeue();
    }

    @Test
    public void testDequeue2() {
        ArrayRingBuffer arb = new ArrayRingBuffer(5);
        arb.enqueue(0);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        assertEquals(0, arb.dequeue());
        assertEquals(1, arb.dequeue());
        assertEquals(2, arb.dequeue());
        arb.enqueue(4);
        arb.enqueue(0);
        arb.enqueue(1);
        assertEquals(3, arb.dequeue());
        assertEquals(4, arb.dequeue());
        assertEquals(0, arb.dequeue());
    }

    @Test
    public void testIterator() {
        ArrayRingBuffer arb = new ArrayRingBuffer(5);
        arb.enqueue(0);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        arb.enqueue(4);
        arb.enqueue(0);
        arb.enqueue(1);
        Iterator<Integer> iter = arb.iterator();
        while(iter.hasNext()) {
            System.out.println(iter.next());
        }


    }

    @Test(expected = Exception.class)
    public void testPeek() {
        ArrayRingBuffer arb = new ArrayRingBuffer(5);
        arb.peek();
        arb.enqueue(5);
        assertEquals(5, arb.peek());
    }


    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }

}
