package com.yym.springboot.base.java.map;

public class MyHashMap<K, V> {
    private int size;
    private Node<K, V>[] table;
    private int length;
    private int threshold;
    private double loadFactor = 0.75D;

    public MyHashMap() {
        this(16);
    }

    public MyHashMap(int length) {
        this.length = length;
        this.threshold = (int) Math.ceil(length*loadFactor);
        table = new Node[length];
    }

    public K put(K key, V value) {
        int i = getIndex(key, table.length);
        // 如果之前数组中存在值,找到并替换
        for (Node no = table[i]; no != null; no = no.next) {
            if (no.key.equals(key)) {
                K value1 = (K) no.value;
                table[i].value = value;
                return value1;
            }
        }
        // 判断是否达到阈值,达到则扩容
        if(checkThresHold(i)){
            // 转移元素
            table = transfer();
        };
        Node node = new Node(key, value, table[i]);
        table[i] = node;
        size++;
        return null;
    }

    private Node<K,V>[] transfer() {
        Node<K,V>[] newTable = new Node[length*2];
        for (Node<K, V> node : table) {
            while(node != null){
                int i = getIndex(node.key, table.length);
                node.next = newTable[i];
                newTable[i] = node;
                node = node.next;
            }
        }
        return newTable;
    }

    private boolean checkThresHold(int index) {
        // 达到阈值或并且不为空,则需要扩容
        if(size >= threshold && table[index] != null){
            return true;
        }
        return false;
    }

    public V remove(K key) {
        if (size == 0) {
            return null;
        }
        int i = getIndex(key, table.length);
        for (Node node = table[i]; node != null; node = node.next) {
            if (node.key.equals(key)) {
                V value = (V) node.value;
                table[i] = null;
                size--;
                return value;
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    public V get(K key) {
        int i = getIndex(key, table.length);
        // 如果之前数组中存在值,找到并替换
        for (Node no = table[i]; no != null; no = no.next) {
            if (no.key.equals(key)) {
                return (V) no.value;
            }
        }
        // 如果没有值,则添加
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < table.length; i++) {
            for (Node no = table[i]; no != null; no = no.next) {
                sb.append(no.toString());
                if (i != table.length - 1) {
                    sb.append(",");
                }
            }
        }
        sb.append("}");
        return sb.toString();
    }

    private int getIndex(K key, int length) {
        int hash = key.hashCode();
        return hash % length;
    }

    class Node<K, V> {
        K key;
        V value;
        Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            return key + " = " + value;
        }
    }

}
