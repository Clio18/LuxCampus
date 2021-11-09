package com.luxcampus.Lection_08.LinkedList;
import com.luxcampus.Lection_07.List;

import java.util.Iterator;
import java.util.Objects;
import java.util.StringJoiner;

public class LinkedList implements List, Iterable {
    private int size;
    private Node head;
    private Node tail;

    @Override
    public void add(Object value) {
        //first Node has value = null, next = null, prev = null
        if (head == null) {
            //its will be a head and tail in the same time, first element
            Node newNode = new Node(value, null, null);
            head = newNode;
            tail = newNode;
        } else {
            //it not the first, so its previous is last and next is null
            Node newNode = new Node(value, tail, null);
            //Node last: some value, some prev, next -> newNode
            tail.next = newNode;
            //and newNode become last Node
            tail = newNode;
        }
        size++;

    }

    @Override
    public void add(Object value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            if (head == null) {
                add(value);
            } else {
                Node firstNew = new Node(value, null, head);
                head.previous = firstNew;
                head = firstNew;
                size++;
            }
        } else if (index == size) {
            //adding to the end, tail has: value, previous = last, next = null
            Node newNode = new Node(value, tail, null);
            //old tail now has next is newNode
            tail.next = newNode;
            //newNode now is a tail
            tail = newNode;
            size++;
        } else {
            //insert between a and b
            Node current = null;
            for (int i = 0; i < size; i++) {
                current = head.next;
            }
            Node b = current.next;
            // newNode will be between a and b, so prev will be a, next will be b
            Node node = new Node(value, current, b);
            //a change its next to node
            current.next = node;
            //b change its prev to node
            b.previous = node;
            size++;
        }

    }

    @Override
    public Object remove(int index) {
        Object object = null;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            object = head;
            head = head.next;
            size--;
        } else if (index == size - 1) {
            tail = tail.previous;
            size--;
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            Node removed = current.next;
            object = removed;
            current.next = removed.next;
            Node previous = removed.previous;
            previous.previous = current;
            size--;
        }
        return object;
    }


    @Override
    public Object get(int index) {
        Object result;
        if (index < 0 || index > size - 1) {
            throw new IllegalArgumentException("Wrong index!");
        } else if (index == 0) {
            result = head.value;
        } else if (index == size - 1) {
            result = tail.value;
        } else {
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            result = current.value;
        }
        return result;
    }

    @Override
    public Object set(Object value, int index) {
        Object result = null;
        if (index < 0 || index > size - 1) {
            throw new IllegalArgumentException("Wrong index!");
        } else {
            result = get(index);
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            current.value = value;
        }
        return result;
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
    public boolean contains(Object value) {
        return indexOf(value)!=-1;
    }

    @Override
    public int indexOf(Object value) {
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
    public int lastIndexOf(Object value) {
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
        Node current = head;
        for (int i = 0; i < size; i++) {
            stringJoiner.add(current.value.toString());
            current = current.next;
        }
        return stringJoiner.toString();
    }

    @Override
    public Iterator iterator() {
        return new LinkedListIterator();
    }
    private class LinkedListIterator implements Iterator{
        private int position;

        @Override
        public boolean hasNext() {
            return position<size;
        }

        @Override
        public Object next() {
            Object value = get(position);
            position++;
            return value;
        }
    }
}
