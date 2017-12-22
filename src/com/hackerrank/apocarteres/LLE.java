package com.hackerrank.apocarteres;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Iterator;

interface Node {
    Node getNext();

    void setNext(Node node);
}

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

public final class LLE {

    public static void main(String[] args) {
        Node n = new XNode(
                1,
                new XNode(2,
                        new XNode(
                                3, new XNode(
                                        4, null
                        )
                        )
                )
        );
        print(n);
        Node head = new LLE().swap(n, 3);
        print(head);
    }

    private static void print(Node head) {
        for (;;) {
            System.out.print(String.format("%s ",head));
            head = head.getNext();
            if (head == null) {
                break;
            }
        }
        System.out.println();
    }

    public Node swap(Node head, int k) {
        Deque<Node> queue = new LinkedList<>();
        Node current = head;
        queue.addFirst(current);
        for (int i = 0; i < k-1; i++) {
            Node n = current.getNext();
            if (n != null) {
                queue.addFirst(n);
                current = n;
            }
        }
        if (queue.isEmpty()) {
            return null;
        }
        Iterator<Node> it = queue.iterator();
        Node parent = it.next();
        Node c = parent.getNext();
        while (it.hasNext()) {
            Node n = it.next();
            parent.setNext(n);
            parent = n;
            n.setNext(null);
            it.remove();
        }
        if (c != null) {
            Node s = swap(c, k);
            parent.setNext(s);
        }
        return queue.getFirst();
    }
}
