package com.luxcampus.Lection_06;

public class ArrayQueue implements Queue {
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
        size = size - 1;
        Object[] newArr = new Object[size];
        for (int i = 0; i < size; i++) {
            newArr[i] = array[i + 1];

        }
        array = newArr;
        return result;
    }

    @Override
    public Object peek() {
        return array[0];
    }

    @Override
    public boolean contains(Object value) {
        for (int i = 0; i < size; i++) {
            if (value.equals(array[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public String toString(){
        if(isEmpty()){
            return "[]";
        }
        String result = "";
        for (int i = 0; i < size; i++) {
            result = result+array[i];
            if(i<size-1){
                result = result + ", ";
            }
        }
        return "["+ result+"]";
    }


}
