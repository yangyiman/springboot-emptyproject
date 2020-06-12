package com.yym.springboot.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 数组工具
 */
public class ArrayUtil {

    /**
     * 将数组转换成集合
     *
     * @param array
     * @param <T>
     * @return
     */
    public static <T> List<T> changeArrayToList(T[] array) {
        if (array.length > 0) {
            List<T> list = new ArrayList<>(array.length);
            Collections.addAll(list, array);
            return list;
        }
        return new ArrayList<>();
    }
}
