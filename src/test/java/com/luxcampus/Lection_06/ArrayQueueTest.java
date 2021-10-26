package com.luxcampus.Lection_06;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ArrayQueueTest extends TestCase {
    /*int size();
    boolean isEmpty();
    void enqueue(Object value);
    Object dequeue();
    Object peek();
    boolean contains(Object value);
    void clear();
    String toString();*/

    @Test
    public void testEnqueueAndDequeue() {
        ArrayQueue arrayQueue = new ArrayQueue();
        arrayQueue.enqueue("A");
        arrayQueue.enqueue("B");
        assertEquals(2, arrayQueue.size());
        assertEquals("A", arrayQueue.dequeue());
        assertEquals("B", arrayQueue.dequeue());
        assertEquals(0, arrayQueue.size());
        assertTrue(arrayQueue.isEmpty());
    }

    @Test
    public void testEnqueueAndPeek() {
        ArrayQueue arrayQueue = new ArrayQueue();
        arrayQueue.enqueue("A");
        arrayQueue.enqueue("B");
        assertEquals(2, arrayQueue.size());
        assertEquals("A", arrayQueue.peek());
        assertEquals("A", arrayQueue.peek());
        assertEquals(2, arrayQueue.size());
    }

    @Test
    public void testThrowIllegalStateExceptionWhenDequeueOnEmpty(){
        ArrayQueue arrayQueue = new ArrayQueue();
        Assertions.assertThrows(IllegalStateException.class, ()->{
            arrayQueue.dequeue();
        });
    }

    @Test
    public void testIsEmptyTrueOnNewQueue() {
        ArrayQueue arrayQueue = new ArrayQueue();
        assertTrue(arrayQueue.isEmpty());
    }

    @Test
    public void testIsEmptyFalseOnNewQueueWithElement() {
        ArrayQueue arrayQueue = new ArrayQueue();
        arrayQueue.enqueue("A");
        assertFalse(arrayQueue.isEmpty());
    }

    @Test
    public void testIsEmptyWhenClear() {
        ArrayQueue arrayQueue = new ArrayQueue();
        arrayQueue.enqueue("A");
        arrayQueue.clear();
        assertTrue(arrayQueue.isEmpty());
    }

    @Test
    public void testContainsTrue() {
        ArrayQueue arrayQueue = new ArrayQueue();
        arrayQueue.enqueue("A");
        assertTrue(arrayQueue.contains("A"));
    }

    @Test
    public void testContainsFalse() {
        ArrayQueue arrayQueue = new ArrayQueue();
        arrayQueue.enqueue("A");
        assertFalse(arrayQueue.contains("B"));
    }

    @Test
    public void testContainsFalseOnEmpty() {
        ArrayQueue arrayQueue = new ArrayQueue();
        assertFalse(arrayQueue.contains("B"));
    }

    //if capacity is 1, the size should be 1 too
    @Test
    public void testOverFlow() {
        ArrayQueue arrayQueue = new ArrayQueue(1);
        assertEquals(0, arrayQueue.size());
        arrayQueue.enqueue("A");
        assertEquals(1, arrayQueue.size());
    }

    //A->B->C...head->...->tail
    @Test
    public void testToString() {
        ArrayQueue arrayQueue = new ArrayQueue();
        arrayQueue.enqueue("A");
        arrayQueue.enqueue("B");
        assertEquals("[A, B]", arrayQueue.toString());
        arrayQueue.dequeue();
        assertEquals("[B]", arrayQueue.toString());

    }

    @Test
    public void testToStringOnEmpty() {
        ArrayQueue arrayQueue = new ArrayQueue();
        arrayQueue.enqueue("A");
        arrayQueue.enqueue("B");
        arrayQueue.clear();
        assertEquals("[]", arrayQueue.toString());
    }

}