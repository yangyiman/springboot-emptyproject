package com.yym.springboot.common.util;

import java.util.UUID;

/**
 * 主键工具类
 *
 * @author xzc
 */
public class UUIDUtil {

    /**
     * 随机生成字符串
     *
     * @return
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        // 去掉"-"符号
        String temp = str.substring(0, 8) + str.substring(9, 13)
                + str.substring(14, 18) + str.substring(19, 23)
                + str.substring(24);
        return temp;

        /* return UUID.randomUUID().toString().replace("-", "");*/
    }

}
