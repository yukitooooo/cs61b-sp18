package synthesizer;
// package <package name>;


import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        this.capacity = capacity;
        first = 0;
        last = 0;
        this.fillCount = 0;
    }


    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        if (last == capacity - 1) {
            last = 0;
        } else {
            last += 1;
        }
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        fillCount -= 1;
        int x = first;
        if (first == capacity - 1) {
            first = 0;
        } else {
            first += 1;
        }
        return rb[x];
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        return rb[first];
    }

    public Iterator<T> iterator() {
        return new ARBIterator();
    }

    private class ARBIterator implements Iterator<T> {
        private int wizPos = first;

        @Override
        public boolean hasNext() {
            return (!isEmpty() || wizPos != last);
        }

        @Override
        public T next() {
            if (hasNext()) {
                T returnItem = rb[wizPos];
                if (wizPos == capacity - 1) {
                    wizPos = 0;
                } else {
                    wizPos += 1;
                }
                return returnItem;
            } else {
                throw new RuntimeException("No next item");
            }
        }
    }
}
