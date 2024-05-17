package com.hackerrank.apocarteres.lle;

class XNode implements Node{
    Node next;
    int value;

    public XNode(int value, Node next) {
        this.value = value;
        this.next = next;
    }

    @Override
    public Node getNext() {
        return next;
    }

    @Override
    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
