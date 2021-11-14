package com.luxcampus.Lection_06;

public interface Queue<T> extends Iterable<T> {
    int size();

    boolean isEmpty();

    void enqueue(T value);

    T dequeue();

   T peek();

    boolean contains(T value);

    void clear();

    String toString();
}
