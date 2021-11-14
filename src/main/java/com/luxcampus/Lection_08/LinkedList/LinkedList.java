package com.luxcampus.Lection_08.LinkedList;

import com.luxcampus.Lection_07.List;

import java.util.Iterator;
import java.util.Objects;
import java.util.StringJoiner;

public class LinkedList<T> implements List<T> {
    private int size;
    private Node head;
    private Node tail;

    public static class Node {
        private Object value;
        private Node previous;
        private Node next;

        private Node(Object value, Node previous, Node next) {
            this.value = value;
            this.previous = previous;
            this.next = next;
        }
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        checkBounds(index);
        if (index == 0) {
            if (head == null) {
                //add(value);
                Node newNode = new Node(value, null, null);
                head = newNode;
                tail = newNode;
            } else {
                //it not the first, so its previous is last and next is null
                Node newNode = new Node(value, null, head);
                //Node last: some value, some prev, next -> newNode
                head.previous = newNode;
                //and newNode become last Node
                head = newNode;
            }
        } else if (index == size) {
            //adding to the end, tail has: value, previous = last, next = null
            Node newNode = new Node(value, tail, null);
            //old tail now has next is newNode
            tail.next = newNode;
            //newNode now is a tail
            tail = newNode;
        } else {
            Node current = getNode(index);
            Node prev = current.previous;
            Node newNode = new Node(value, prev, current);
            prev.next = newNode;
            current.previous = newNode;
        }
        size++;

    }

    @Override
    public T remove(int index) {
        T object = null;
        checkBounds(index);
        if (index == 0) {
            object = (T) head;
            head = head.next;
        } else if (index == size - 1) {
            tail = tail.previous;
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            Node removed = current.next;
            object = (T) removed;
            current.next = removed.next;
            Node previous = removed.previous;
            previous.previous = current;
        }
        size--;
        return object;
    }


    @Override
    public T get(int index) {
        return (T) getNode(index).value;
    }

    @Override
    public T set(Object value, int index) {
        if (index < 0 || index > size-1) {
            throw new IllegalArgumentException("Wrong index!");
        }
        Node current = getNode(index);
        T oldValue = (T) current.value;
        current.value = value;
        return oldValue;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(T value) {
        int result = -1;
        Node current = head;
        for (int i = 0; i < size; i++) {
            /*
            we should use Objects.equals() if the value might be null
            method equals() cannot be apply to the null
             */
            if (Objects.equals(current.value, value)) {
                result = i;
            }
            current = current.next;
        }
        return result;
    }

    @Override
    public int lastIndexOf(T value) {
        int result = -1;
        Node current = head;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(current.value, value)) {
                result = i;
                continue;
            }
            current = current.next;
        }
        return result;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            stringJoiner.add(String.valueOf(iterator.next()));
        }
        /*
        for(Object object:this){}
        */
        return stringJoiner.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator<T> implements Iterator<T> {
        private int position;

        @Override
        public boolean hasNext() {
            return position < size;
        }

        @Override
        public T next() {
            Node node = getNode(position);
            position++;
            return (T) node.value;
        }

        @Override
        public void remove(){
            if (hasNext()){
                LinkedList.this.remove(position);
            }
        }
    }

    Node getNode(int index) {
        checkBounds(index);
        Node current = null;
        if (index < (size / 2)) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.previous;
            }
        }
        return current;
    }

    void checkBounds(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }
}
