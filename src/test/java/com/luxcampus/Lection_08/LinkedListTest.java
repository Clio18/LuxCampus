package com.luxcampus.Lection_08;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {
    /*
    void add(Object value);
    void add(Object value, int index);
    Object remove(int index);
    Object get(int index);
    Object set(Object value, int index);
    void clear();
    int size();
    boolean isEmpty();
    boolean contains(Object value);
    int indexOf(Object value);
    int lastIndexOf(Object value);
    String toString();
    */

    @Test
    void testAddAndCheckSize() {
        LinkedList list = new LinkedList();
        list.add(100);
        assertEquals(1, list.size());
    }

    @Test
    void testAddByIndexAndCheckSizeAndContains() {
        LinkedList list = new LinkedList();
        list.add(100);
        list.add(200, 0);
        list.add(300);
        assertEquals(3, list.size());
        list.add(5000, 2);
        assertEquals(4, list.size());
        assertTrue(list.contains(5000));
        assertTrue(list.contains(100));
        assertTrue(list.contains(300));
    }

    @Test
    void testAddRemoveLastAndCheckSize() {
        LinkedList list = new LinkedList();
        list.add(100);//0
        list.add(300);//1
        list.remove(1);
        assertEquals(1, list.size());
        assertTrue(list.contains(100));
        assertFalse(list.contains(300));
    }

    @Test
    void testAddRemoveFirstAndCheckSize() {
        LinkedList list = new LinkedList();
        list.add(100);//0
        list.add(300);//1
        list.remove(0);
        assertEquals(1, list.size());
        assertTrue(list.contains(300));
        assertFalse(list.contains(100));
    }

    @Test
    void testAddRemoveAndCheckSize() {
        LinkedList list = new LinkedList();
        list.add(100);//0
        list.add(200);//1
        list.add(300);//2
        list.add(400);//3
        list.add(500);//4
        list.remove(2);
        assertEquals(4, list.size());
        assertFalse(list.contains(300));
    }

}