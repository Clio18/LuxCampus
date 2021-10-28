package com.luxcampus.Lection_07;

public class ArrayList implements List {
    private int size;
    private Object[] array;

    public ArrayList() {
        array = new Object[10];
    }

    public ArrayList(int size) {
        array = new Object[size];
    }

    public void ensureCapacity() {
        if (array.length == size) {
            Object[] newArr = new Object[(3 * array.length) / 2];
            for (int i = 0; i < array.length; i++) {
                newArr[i] = array[i];
            }
            array = newArr;
        }
    }

    @Override
    public void add(Object value) {
        ensureCapacity();
        array[size] = value;
        size++;
    }

    @Override
    public void add(Object value, int index) {
        // if add in 0
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Wrong index!");
        } else if (index == 0) {
            for (int i = size; i > 0; i--) {
                array[i] = array[i - 1];
            }
            array[0] = value;
            size++;
        } else if (size == index) {
            array[index] = value;
            size++;
        } else {
            for (int i = size; i > index; i--) {
                array[i] = array[i - 1];
            }
            array[index] = value;
            size++;
        }
    }

    @Override
    public Object remove(int index) {
        Object result;
        // delete index 0
        if (index < 0 || index > size - 1) {
            throw new ArrayIndexOutOfBoundsException("Wrong index!");
        } else if (index == 0) {
            result = array[0];
            size--;
            for (int i = 0; i < size; i++) {
                array[i] = array[i + 1];
            }
        } else if (index == size - 1) {
            result = array[size - 1];
            size--;
            array[index] = 0;
        } else {
            // delete index middle
            result = array[index];
            size--;
            for (int i = index; i < size; i++) {
                array[i] = array[i + 1];
            }
        }
        return result;
    }

    @Override
    public Object get(int index) {
        if (0 < index && index < size) {
            return array[index];
        } else throw new ArrayIndexOutOfBoundsException("Wrong index!");
    }

    @Override
    public Object set(Object value, int index) {
        Object result;
        if (0 < index && index < size && size != 0) {
            result = array[index];
            array[index] = value;
            return result;
        } else throw new ArrayIndexOutOfBoundsException("Wrong index!");
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = 0;
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
    public boolean contains(Object value) {
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
    public int indexOf(Object value) {
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
    public int lastIndexOf(Object value) {
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
        String result = "";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            stringBuilder.append(array[i].toString());
            if(i<size-1){
                stringBuilder.append(", ");
            }
        }
        return "["+ stringBuilder.toString()+"]";
    }
}
