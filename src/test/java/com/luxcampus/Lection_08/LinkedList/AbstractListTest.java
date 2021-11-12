package com.luxcampus.Lection_08.LinkedList;

import com.luxcampus.Lection_07.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractListTest {
    List list = getList();
    abstract com.luxcampus.Lection_07.List getList();
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

    @DisplayName("Test for adding elements and checking the size")
    @Test
    void testAddAndCheckSize() {
        //LinkedList list = new LinkedList();
        list.add(100);
        assertEquals(1, list.size());
    }
    @DisplayName("Test for adding elements by index and checking the size and if contains")
    @Test
    void testAddByIndexAndCheckSizeAndContains() {
        //LinkedList list = new LinkedList();
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

    @DisplayName("Test for removing last element and checking the size")
    @Test
    void testAddRemoveLastAndCheckSize() {
        //LinkedList list = new LinkedList();
        list.add(100);//0
        list.add(300);//1
        list.remove(1);
        assertEquals(1, list.size());
        assertTrue(list.contains(100));
        assertFalse(list.contains(300));
    }

    @DisplayName("Test for removing first element and checking the size")
    @Test
    void testAddRemoveFirstAndCheckSize() {
        //LinkedList list = new LinkedList();
        list.add(100);//0
        list.add(300);//1
        list.remove(0);
        assertEquals(1, list.size());
        assertTrue(list.contains(300));
        assertFalse(list.contains(100));
    }

    @DisplayName("Test for removing element with index 0")
    @Test
    void testAddRemoveIndexOne() {
        list.add(100);//0
        list.remove(0);
        assertEquals(0, list.size());;
    }

    @DisplayName("Test for adding, removing and checking the size")
    @Test
    void testAddRemoveAndCheckSize() {
       // LinkedList list = new LinkedList();
        list.add(100);//0
        list.add(200);//1
        list.add(300);//2
        list.add(400);//3
        list.add(500);//4
        list.remove(2);
        assertEquals(4, list.size());
        assertFalse(list.contains(300));
    }


    //Tests from ArrayListTest class
    @DisplayName("Test add 2 elements to empty list and remove twice from 0 index")
    @Test
    void testAddToEmptyTwoElementsAndRemoveTwiceByZeroIndex() {
       // LinkedList list = new LinkedList();
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
       // LinkedList list = new LinkedList();
        assertTrue(list.isEmpty());
        list.add(null);
        assertEquals(1, list.size());
        assertTrue(list.contains(null));
    }

    @DisplayName("Test remove from beginning and end")
    @Test
    void testRemoveFromStartAndEnd(){
        //LinkedList list = new LinkedList();
        for (int i = 0; i < 15; i++) {
            list.add(i);
        }
        list.remove(0);
        assertEquals(14, list.size());
        assertFalse(list.contains(0));
        list.remove(13);
        assertEquals(13, list.size());
        assertFalse(list.contains(14));
        Assertions.assertThrows(IndexOutOfBoundsException.class, ()->{
            list.remove(16);
        });
    }
    @DisplayName("Test remove from 1 to size-1 index")
    @Test
    void testRemoveFromMiddle(){
        //LinkedList list = new LinkedList();
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
       // LinkedList list = new LinkedList();
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
       // LinkedList list = new LinkedList();
        for (int i = 0; i < 5; i++) { //[0, 1, 2, 3, 4]
            list.add(i);
        }
        list.add(100, 0); //[100, 0, 1, 2, 3, 4]
        assertEquals(6, list.size());
        assertTrue(list.contains(0));
        assertTrue(list.contains(4));
        assertTrue(list.contains(100));

        list.add(200, 5); //[100, 0, 1, 2, 3, 200, 4]
        assertEquals(7, list.size());
        assertTrue(list.contains(4));
        assertTrue(list.contains(200));

        list.add(300, 7); //[100, 0, 1, 2, 3, 200, 300, 4]
        assertEquals(8, list.size());
        assertTrue(list.contains(4));
        assertTrue(list.contains(300));
        Assertions.assertThrows(IndexOutOfBoundsException.class, ()->{
            list.add(400, 9);
        });
        //assertEquals(8, list.size());
    }
    @DisplayName("Test add by index which is out of size must be exception")
    @Test
    void testAddByIndexOutOfSize() {
        //LinkedList list = new LinkedList();
        for (int i = 0; i < 5; i++) { //[0, 1, 2, 3, 4,....]
            list.add(i);
        }
        Assertions.assertThrows(IndexOutOfBoundsException.class, ()->{
            list.add(400, 9); //[100, 0, 1, 2, 3, 1000, 4, 300....]
        });
        assertEquals(5, list.size());
    }

    @DisplayName("Test add by index and remove by index")
    @Test
    void testAddByIndAndRemoveByInd(){
       // LinkedList list = new LinkedList();
        list.add(100, 0); //100
        list.add(200, 0); // 200, 100
        assertEquals(2, list.size()); //2
        list.remove(1); //200
        assertEquals(1, list.size());//1
        assertFalse(list.contains(100)); //true
    }

    @DisplayName("Test get and set element")
    @Test
    void testGetAndSet(){
       // LinkedList list = new LinkedList();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        assertEquals(2, list.get(2));
        assertEquals(2, list.set(100, 2));
        assertEquals(100, list.get(2));
    }
    @DisplayName("Test set method after clear")
    @Test
    void testSetAfterClear(){
     //   LinkedList list = new LinkedList();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        assertEquals(5, list.size());
        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        assertThrows(IllegalArgumentException.class, ()->{
            list.set(100, 0);
        });
    }
    @DisplayName("Test index of first, last and not presented elements")
    @Test
    void testIndexOf(){
        //LinkedList list = new LinkedList();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        assertEquals(0, list.indexOf(0));
        assertEquals(4, list.indexOf(list.size()-1));
        assertEquals(-1, list.indexOf(list.size()));
    }

    @DisplayName("Test last index of if list contains the same elements")
    @Test
    void testLastIndexOfElementsIfTheyMoreThanOne(){
       // LinkedList list = new LinkedList();
        list.add(1); //0
        list.add(100);
        list.add(0);
        list.add(100);
        list.add(100); //4
        assertEquals(4, list.lastIndexOf(100));
        list.remove(4);
        assertEquals(3, list.lastIndexOf(100));
    }

    @DisplayName("Test toString method on not empty list")
    @Test
    void testToStingOnNotEmpty(){
        //LinkedList list = new LinkedList();
        for (int i = 0; i < 3; i++) {
            list.add(100);
        }
        assertEquals("[100, 100, 100]", list.toString());
    }

    @DisplayName("Test toString method on empty list")
    @Test
    void testToStingOnEmpty(){
       // LinkedList list = new LinkedList();
        assertEquals("[]", list.toString());
    }
    @DisplayName("Test toString method after clear method")
    @Test
    void testToStingAfterClear(){
        //LinkedList list = new LinkedList();
        for (int i = 0; i < 3; i++) {
            list.add(100);
        }
        list.clear();
        assertEquals("[]", list.toString());
    }
    @DisplayName("Test toString method after remove all")
    @Test
    void testToStingAfterRemoveAll(){
       // LinkedList list = new LinkedList();
        for (int i = 0; i < 3; i++) {
            list.add(100);
        }
        for (int i = 0; i < 3; i++) {
            list.remove(0);
        }
        assertEquals("[]", list.toString());
    }

    @DisplayName("Test get and set element")
    @Test
    void testGetNode(){
        // LinkedList list = new LinkedList();
        for (int i = 0; i < 5; i++) { //[0, 1, 2, 3, 4,....]
            list.add(i);
        }
        int a = (int) list.get(2);
        assertEquals(2, a);
    }



}