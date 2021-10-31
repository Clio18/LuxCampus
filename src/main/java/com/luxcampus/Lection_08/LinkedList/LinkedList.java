package com.luxcampus.Lection_08.LinkedList;

public class LinkedList implements List {
   private int size;
   private Node first;
   private Node last;

    @Override
    public void add(Object value)  {
        //first Node has value = null, next = null, prev = null
        if (first == null) {
            //its will be a head and tail in the same time, first element
            Node newNode = new Node(value, null, null);
            first = newNode;
            last = newNode;
        } else {
            //it not the first, so its previous is last and next is null
            Node newNode = new Node(value, last, null);
            //Node last: some value, some prev, next -> newNode
            last.next = newNode;
            //and newNode become last Node
            last = newNode;
        }
        size++;

    }

    @Override
    public void add(Object value, int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            add(value);
        } else if (index == size) {
            //adding to the end, tail has: value, previous = last, next = null
            Node newNode = new Node(value, last, null);
            //old tail now has next is newNode
            last.next = newNode;
            //newNode now is a tail
            last = newNode;
        } else {
            //insert between a and b
            Node a = null;
            for (int i = 0; i < size; i++) {
                a = first.next;
            }
            Node b = a.next;
            // newNode will be between a and b, so prev will be a, next will be b
            Node node = new Node(value, a, b);
            //a change its next to node
            a.next = node;
            //b change its prev to node
            b.previous = node;
            size++;
        }

    }

    @Override
    public Object remove(int index) {
        Object obj = null;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else if (index==0){
            obj = first;
            first = first.next;
            size--;
        } else if (index==size-1){
            last = last.previous;
            size--;
        } else {
            Node a = first;
            for (int i = 0; i < index-1; i++) {
                a = a.next;
            }
            Node rem = a.next;
            obj = rem;
            a.next = rem.next;
            Node prev = rem.previous;
            prev.previous = a;
            size--;
        }
        return obj;
    }


    @Override
    public Object get(int index) {
        return null;
    }

    @Override
    public Object set(Object value, int index) {
        return null;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
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
        boolean result = false;
        if (value==null) {
            throw new IndexOutOfBoundsException();
        } else{
            if (first.value.equals(value) || last.value.equals(value)){
                result = true;
            } else {
              Node a = first;
                for (int i = 0; i < size-1; i++) {
                    if (a.value.equals(value)){
                        result = true;
                    }
                    a = a.next;
                }
            }
        }
        return result;
    }

    @Override
    public int indexOf(Object value) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object value) {
        return 0;
    }
}
