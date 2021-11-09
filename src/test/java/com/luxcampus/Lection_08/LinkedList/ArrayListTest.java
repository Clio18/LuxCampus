package com.luxcampus.Lection_08.LinkedList;
import com.luxcampus.Lection_07.ArrayList;
import com.luxcampus.Lection_07.List;

public class ArrayListTest extends AbstractListTest {
    @Override
    List getList() {
        return new ArrayList();
    }
}
