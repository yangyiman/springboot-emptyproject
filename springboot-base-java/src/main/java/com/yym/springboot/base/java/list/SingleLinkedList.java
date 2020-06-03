package com.yym.springboot.base.java.list;

/**
 * 单项链表
 */
public class SingleLinkedList {
    private Node first;
    private Node last;
    private int size;

    public void addLast(Object ele) {
        Node node = new Node(ele);
        node.next = null;
        if (size == 0) {
            this.first = node;
            this.last = node;
        } else {
            this.last.next = node;
            this.last = node;
        }
        size++;
    }

    public void addFirst(Object ele) {
        Node node = new Node(ele);
        if (size == 0) {
            this.first = node;
            this.last = node;
        } else {
            node.next = this.first;
            this.first = node;
        }
        size++;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(size * 3 + 1);
        sb.append("[");
        Node node = this.first;
        while (node != null) {
            sb.append(node.ele.toString());
            if (node.next != null) {
                sb.append(",");
            }
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }

    class Node {
        Node next;
        Object ele;

        public Node(Object ele) {
            this.ele = ele;
        }
    }
}
