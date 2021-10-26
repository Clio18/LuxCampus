package com.luxcampus.Lection_06;

public interface Queue {
    int size();

    boolean isEmpty();

    void enqueue(Object value);

    Object dequeue();

    Object peek();

    boolean contains(Object value);

    void clear();

    String toString();
}
