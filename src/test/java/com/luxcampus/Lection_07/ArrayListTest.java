package com.luxcampus.Lection_07;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {
    @Test
    void testAddAndRemoveAndContains() {
        ArrayList list = new ArrayList();
        list.add("a");
        list.add("b");
        assertEquals(2, list.size());
        list.remove(0);
        assertEquals(1, list.size());
        assertTrue(list.contains("b"));
        list.remove(0);
        assertTrue(list.isEmpty());
        list.add(null);
        assertTrue(list.contains(null));
    }
    @Test
    void testRemoveFromStartAndEnd(){
        ArrayList list = new ArrayList();
        for (int i = 0; i < 15; i++) {
            list.add(i);
        }
        list.remove(0);
        assertEquals(14, list.size());
        assertFalse(list.contains(0));
        list.remove(13);
        assertEquals(13, list.size());
        assertFalse(list.contains(14));
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, ()->{
            list.remove(16);
        });
    }
    @Test
    void testRemoveFromMiddle(){
        ArrayList list = new ArrayList();
        for (int i = 0; i < 15; i++) {
            list.add(i);
        }
        list.remove(10);
        assertEquals(14, list.size());
        assertFalse(list.contains(10));
    }
    @Test
    void testAddAndRemoveAndCapacity() {
        ArrayList list = new ArrayList();
        for (int i = 0; i < 15; i++) {
            list.add("a");
        }
        assertEquals(15, list.size());
        for (int i = 0; i < 15; i++) {
            list.remove(0);
        }
        assertEquals(0, list.size());
    }
    @Test
    void testAddByIndex() {
        ArrayList list = new ArrayList();
        for (int i = 0; i < 5; i++) { //[0, 1, 2, 3, 4,....]
            list.add(i);
        }
        list.add(100, 0); //[100, 0, 1, 2, 3, 4,....]
        assertEquals(6, list.size());
        assertTrue(list.contains(0));
        assertTrue(list.contains(100));

        list.add(200, 5); //[100, 0, 1, 2, 3, 200, 4....]
        assertEquals(7, list.size());
        assertTrue(list.contains(4));
        assertTrue(list.contains(200));

        list.add(300, 7); //[100, 0, 1, 2, 3, 1000, 4, 300....]
        assertEquals(8, list.size());
        assertTrue(list.contains(4));
        assertTrue(list.contains(300));
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, ()->{
            list.add(400, 9); //[100, 0, 1, 2, 3, 1000, 4, 300....]
        });
        assertEquals(8, list.size());
    }
    @Test
    void testAddByIndAndRemoveByInd(){
        ArrayList arrayList = new ArrayList();
        arrayList.add(100, 0);
        arrayList.add(200, 0);
        assertEquals(2, arrayList.size());
        arrayList.remove(1);
        assertEquals(1, arrayList.size());
        assertFalse(arrayList.contains(100));
    }
    @Test
    void testGetAndSet(){
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 5; i++) {
            arrayList.add(i);
        }
        assertEquals(2, arrayList.get(2));
        assertEquals(2, arrayList.set(100, 2));
        assertEquals(100, arrayList.get(2));
    }
    @Test
    void testSetAfterClear(){
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 5; i++) {
            arrayList.add(i);
        }
        assertEquals(5, arrayList.size());
        arrayList.clear();
        assertEquals(0, arrayList.size());
        assertTrue(arrayList.isEmpty());
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, ()->{
            arrayList.set(100, 0);
        });
    }
    @Test
    void testIndexOf(){
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 5; i++) {
            arrayList.add(i);
        }
        assertEquals(0, arrayList.indexOf(0));
        assertEquals(4, arrayList.indexOf(arrayList.size()-1));
        assertEquals(-1, arrayList.indexOf(arrayList.size()));
    }
    @Test
    void testLastIndexOf(){
        ArrayList arrayList = new ArrayList();
        arrayList.add(1); //0
        arrayList.add(100);
        arrayList.add(0);
        arrayList.add(100);
        arrayList.add(100); //4
        assertEquals(4, arrayList.lastIndexOf(100));
        arrayList.remove(4);
        assertEquals(3, arrayList.lastIndexOf(100));
    }
    @Test
    void testIndexOfAndLastIndexOf(){
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 5; i++) {
            arrayList.add(100);
        }
        assertEquals(0, arrayList.indexOf(100));
        assertEquals(-1, arrayList.indexOf(200));
        assertEquals(4, arrayList.lastIndexOf(100));
        assertEquals(-1, arrayList.lastIndexOf(200));

    }
    @Test
    void testToSting(){
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 3; i++) {
            arrayList.add(100);
        }
        assertEquals("[100, 100, 100]", arrayList.toString());
        arrayList.clear();
        assertEquals("[]", arrayList.toString());

    }


}