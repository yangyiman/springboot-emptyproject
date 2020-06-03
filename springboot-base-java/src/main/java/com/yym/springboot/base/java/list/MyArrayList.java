package com.yym.springboot.base.java.list;

import java.util.Arrays;

/**
 * 自定义集合
 */
public class MyArrayList {
    // 数组
    private Object[] elements;
    // 数组元素的个数
    private int size = 0;

    // 默认长度为10
    public MyArrayList() {
        this(10);
    }

    public MyArrayList(int initCapacity) {
        elements = new Object[initCapacity];
    }

    // 增加
    public void add(Object ele) {
        // 考虑扩容,原始容量的两倍
        if(size == elements.length){
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
        elements[size++] = ele;
    }

    // 置顶角标删除元素
    public void delete(int index){
        if(index < -1 || index > elements.length-1){
            throw new IndexOutOfBoundsException();
        }
        System.arraycopy(elements,index+1,elements,index,size-index-1);
        elements[size--]=null;
    }

    // 获取指定元素下标
    public int getIndexByElement(Object ele){
        if(size > 0){
            for (int index = 0; index < size; index++) {
                if(elements[index].equals(ele)){
                    return index;
                }
            }
        }
        return -1;
    }

    // 更改
    public void updateElementByIndex(int index,Object newElement){
       if(index < elements.length){
           elements[index] = newElement;
       }
    }

    // 获取
    public Object get(int index){
        if(index < elements.length){
            return elements[index];
        }
        return -1;
    }

    // 打印
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(size*3+1);
        sb.append("[");
        for (int index = 0; index < size; index++) {
            sb.append(elements[index].toString());
            if(index < size-1){
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

}
