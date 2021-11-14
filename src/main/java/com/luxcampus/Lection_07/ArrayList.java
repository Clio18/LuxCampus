package com.luxcampus.Lection_07;

import java.util.Iterator;
import java.util.StringJoiner;

public class ArrayList<T> implements List<T> {
    private int size;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[10];
    }

    public ArrayList(int size) {
        array = (T[]) new Object[size];
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Index should be in the range [0, size]");
        } else if (size == index) {
            array[index] = (T) value;
            size++;
        } else {
            for (int i = size; i > index; i--) {
                array[i] = array[i - 1];
            }
            array[index] = (T) value;
            size++;
        }
    }

    @Override
    public T remove(int index) {
        T result;
        // delete index 0
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index should be in the range [0, size-1]");
        }
        else if (index == size - 1) {
            result = array[size - 1];
            size--;
            array[index] = null;
        } else {
            result = array[index];
            size--;
            for (int i = index; i < size; i++) {
                array[i] = array[i + 1];
            }
        }
        return result;
    }

    @Override
    public T get(int index) {
        if (0 <= index && index < size) {
            return array[index];
        } else throw new ArrayIndexOutOfBoundsException("Wrong index!");
    }

    @Override
    public T set(Object value, int index) {
        T result;
        if (0 < index && index < size && size != 0) {
            result = array[index];
            array[index] = (T) value;
            return result;
        } else throw new IllegalArgumentException("Wrong index!");
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T value) {
        for (int i = 0; i < size; i++) {
            if (value == null) {
                if (array[i] == null) {
                    return true;
                }
            } else if (value.equals(array[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(T value) {
        int result = -1;
        if (contains(value)) {
            for (int i = 0; i < size; i++) {
                if (array[i].equals(value)) {
                    result = i;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public int lastIndexOf(T value) {
        int result = -1;
        if (contains(value)) {
            for (int i = 0; i < size; i++) {
                if (array[i].equals(value)) {
                    result = i;
                    continue;
                }
            }
        }
        return result;
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

//        for (Object o: this){
//            stringJoiner.add(o.toString());
//        }
        return stringJoiner.toString();
    }

    private void ensureCapacity() {
        if (array.length == size) {
            Object[] newArr = new Object[(3 * array.length) / 2];
            for (int i = 0; i < array.length; i++) {
                newArr[i] = array[i];
            }
            array = (T[]) newArr;
        }
    }


    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<T> {
        private int position = 0;

        @Override
        public boolean hasNext() {
            return position<size;
        }

        @Override
        public T next() {
            T value = array[position];
            position++;
            return value;
        }
        @Override
        public void remove(){
            if(hasNext()){
               ArrayList.this.remove(position);
            }

        }
    }
}
