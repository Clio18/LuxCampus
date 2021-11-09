package com.luxcampus.Lection_06;

import java.util.Iterator;

public class ArrayQueue implements Queue, Iterable {
    private int size;
    private Object[] array;

    public ArrayQueue() {
        array = new Object[10];
    }

    public ArrayQueue(int initialCapacity) {
        array = new Object[initialCapacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (array.length == size) {
            Object[] newArray = new Object[array.length * 2];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
    }

    @Override
    public void enqueue(Object value) {
        ensureCapacity();
        array[size] = value;
        size++;
    }

    @Override
    public Object dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty!");
        }
        Object result = array[0];
        for (int i = 0; i < size-1; i++) {
            array[i] = array[++i];
        }
        size--;
        return result;
    }

    @Override
    public Object peek() {
        return array[0];
    }

    @Override
    public boolean contains(Object value) {
        for (int i = 0; i < size; i++) {
            if (value==null){
                if (array[i] == null){
                    return true;
                }
            } else if (value.equals(array[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = 0;
        }
        size = 0;
    }

    @Override
    public String toString(){
        if(isEmpty()){
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            stringBuilder.append(array[i].toString());
            if(i<size-1){
                stringBuilder.append(", ");
            }
        }
        return "["+ stringBuilder.toString()+"]";
    }


    @Override
    public Iterator iterator() {
        return new ArrayQueueIterator();
    }

    private class ArrayQueueIterator implements Iterator{
        private int position = 0;

        @Override
        public boolean hasNext() {
            return position<size;
        }

        @Override
        public Object next(){
            Object value = array[position];
            position++;
            return value;
        }
    }
}
