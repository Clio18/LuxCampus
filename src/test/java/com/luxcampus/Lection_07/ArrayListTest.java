package com.luxcampus.Lection_07;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {
    @DisplayName("Test add 2 elements to empty list and remove twice from 0 index")
    @Test
    void testAddToEmptyTwoElementsAndRemoveTwiceByZeroIndex() {
        ArrayList list = new ArrayList();
        list.add("a");
        list.add("b");
        assertEquals(2, list.size());
        list.remove(0);
        assertEquals(1, list.size());
        assertTrue(list.contains("b"));
        list.remove(0);
        assertTrue(list.isEmpty());
    }
    @DisplayName("Test if contains null on empty list and adding null to list")
    @Test
    void testContainsNullAtEmptyAndAddNull() {
        ArrayList list = new ArrayList();
        assertTrue(list.isEmpty());
        assertFalse(list.contains(null));
        list.add(null);
        assertTrue(list.contains(null));
    }

    @DisplayName("Test remove from beginning and end")
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
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            list.remove(16);
        });
    }
    @DisplayName("Test remove from 1 to size-1 index")
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
    @DisplayName("Test add and remove with capacity ensuring")
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
    @DisplayName("Test add by index and verify content")
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
    @DisplayName("Test add by index which is out of size must be exception")
    @Test
    void testAddByIndexOutOfSize() {
        ArrayList list = new ArrayList();
        for (int i = 0; i < 5; i++) { //[0, 1, 2, 3, 4,....]
            list.add(i);
        }
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, ()->{
            list.add(400, 9); //[100, 0, 1, 2, 3, 1000, 4, 300....]
        });
        assertEquals(5, list.size());
    }

    @DisplayName("Test add by index and remove by index")
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
    @DisplayName("Test get and set element")
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
    @DisplayName("Test set method after clear")
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
    @DisplayName("Test index of first, last and not presented elements")
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
    @DisplayName("Test last index of if list contains the same elements")
    @Test
    void testLastIndexOfElementsIfTheyMoreThanOne(){
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

    @DisplayName("Test toString method on not empty list")
    @Test
    void testToStingOnNotEmpty(){
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 3; i++) {
            arrayList.add(100);
        }
        assertEquals("[100, 100, 100]", arrayList.toString());
    }

    @DisplayName("Test toString method on empty list")
    @Test
    void testToStingOnEmpty(){
        ArrayList arrayList = new ArrayList();
        assertEquals("[]", arrayList.toString());
    }
    @DisplayName("Test toString method after clear method")
    @Test
    void testToStingAfterClear(){
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 3; i++) {
            arrayList.add(100);
        }
        arrayList.clear();
        assertEquals("[]", arrayList.toString());
    }
    @DisplayName("Test toString method after remove all")
    @Test
    void testToStingAfterRemoveAll(){
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 3; i++) {
            arrayList.add(100);
        }
        for (int i = 0; i < 3; i++) {
            arrayList.remove(0);
        }
        assertEquals("[]", arrayList.toString());
    }



}