package com.yym.springboot.base.java.binaryTree;

/**
 * 实现二分查找算法
 * 数组中的数是连续的
 */
public class BinarySearch {
    static int[] array = {2, 7, 13, 43, 54, 56, 75, 100};

    public static void main(String[] args) {
        int target = 54;
        //search(target);
        int position = searchDG(target,0,array.length-1);
        if (position == -1) {
            System.out.println("查找的是" + target + ",序列中没有该数！");
        } else {
            System.out.println("查找的是" + target + ",找到位置为：" + position);
        }
    }

    // 存在就打印出来
    // 二分查找 // 56
    private static int search(int target) {
        int low = 0;
        int high = array.length - 1;
        int middle = 0;
        while (low <= high) {
            if (target < array[low] || target > array[high]) {
                return -1;
            }
            middle = (low + high) / 2;
            if (array[middle] > target) {
                high = middle - 1;
            } else if (array[middle] < target) {
                low = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    private static int searchDG(int target, int low, int high) {
        int middle = (low + high) / 2;
        if (target < array[low] || target > array[high]) {
            return -1;
        }
        middle = (low + high) / 2;
        if (array[middle] > target) {
            high = middle - 1;
            return searchDG(target,low,high);
        } else if (array[middle] < target) {
            low = middle + 1;
            return searchDG(target,low,high);
        } else {
            return middle;
        }
    }
}
