package com.luxcampus.Lection_08.LinkedList;

import com.luxcampus.Lection_07.List;

public class LinkedListTest<T> extends AbstractListTest {
    @Override
    List getList() {
        return new LinkedList();
    }
}
