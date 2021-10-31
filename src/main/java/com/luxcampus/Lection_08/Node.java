package com.luxcampus.Lection_08;

public class Node {
    Object value;
    Node previous;
    Node next;

    public Node(Object value, Node previous, Node next) {
        this.value = value;
        this.previous = previous;
        this.next = next;
    }
}
