package com.yym.springboot.base.java.list;

/**
 * 双向链表
 */
public class MyLinkedList {
    private Node first;
    private Node last;
    private int size;

    // 增
    public void addLast(Object ele) {
        Node node = new Node(ele);
        if (size == 0) {
            this.first = node;
            this.last = node;
        } else {
            this.last.next = node;
            node.prev = this.last;
            this.last = node;
        }
        size++;
    }

    public void addFirst(Object ele) {
        Node node = new Node(ele);
        if (size == 0) {
            this.last = node;
            this.first = node;
        } else {
            this.first.prev = node;
            node.next = this.first;
            this.first = node;
        }
        size++;
    }

    // 删
    public void delete(Object ele) {
        Node node = this.get(ele);
        if (node != null) {
            if (ele.equals(this.first.ele)) {
                this.first.next.prev = null;
                this.first = this.first.next;
            } else if (ele.equals(this.last.ele)) {
                this.last.prev.next = null;
                this.last = this.last.prev;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                node = null;
            }
            size--;
        }
    }

    // 改
    public void update(Object ele) {
        Node node = this.get(ele);
        if (null != node) {
            node.ele = ele;
        }
    }

    // 查
    public Node get(Object ele) {
        Node node = this.first;
        while (true) {
            if (node.ele.equals(ele)) {
                return node;
            }
            if (node == this.last) {
                break;
            }
            node = node.next;
        }
        return null;
    }

    // 打印
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(size * 3 + 1);
        sb.append("[");
        Node node = this.first;
        while (true) {
            sb.append(node.ele.toString());
            if (this.last != node) {
                sb.append(",");
            } else {
                break;
            }
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }

    class Node {
        Node prev;
        Node next;
        Object ele;

        public Node(Object ele) {
            this.ele = ele;
        }
    }
}
