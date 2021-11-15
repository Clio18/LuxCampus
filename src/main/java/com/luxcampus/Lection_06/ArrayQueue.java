package com.luxcampus.Lection_06;

import com.luxcampus.Lection_07.ArrayList;

import java.util.Iterator;
import java.util.StringJoiner;

public class ArrayQueue<T> implements Queue<T> {
    private int size;
    private T[] array;

    public ArrayQueue() {
        array = (T[]) new Object[10];
    }

    public ArrayQueue(int initialCapacity) {
        array = (T[]) new Object[initialCapacity];
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
            T[] newArray = (T[]) new Object[array.length * 2];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
    }

    @Override
    public void enqueue(T value) {
        ensureCapacity();
        array[size] = value;
        size++;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty!");
        }
        T result = array[0];
        for (int i = 0; i < size-1; i++) {
            array[i] = array[++i];
        }
        size--;
        return result;
    }

    @Override
    public T peek() {
        return array[0];
    }

    @Override
    public boolean contains(T value) {
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
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public String toString(){
        if(isEmpty()){
            return "[]";
        }
        StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
        Iterator iterator = this.iterator();
        while (iterator.hasNext()){
            stringJoiner.add(iterator.next().toString());
        }
        return stringJoiner.toString();
    }


    @Override
    public Iterator iterator() {
        return new ArrayQueueIterator();
    }

    private class ArrayQueueIterator implements Iterator<T>{
        private int position = 0;

        @Override
        public boolean hasNext() {
            return position<size;
        }

        @Override
        public T next(){
            T value = array[position];
            position++;
            return value;
        }
        @Override
        public void remove(){
            if(hasNext()){
                ArrayQueue.this.dequeue();
            } else {
                throw new IllegalArgumentException("Already deleted");
            }
        }
    }
}
