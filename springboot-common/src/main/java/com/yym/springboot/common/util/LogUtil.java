package com.yym.springboot.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 日志工具
 *
 * @author xiazichao
 * @since 2019-04-12
 */
public class LogUtil {

    private final static Logger logger = LoggerFactory.getLogger(LogUtil.class);

    public static void info(String msg, Object... params) {
        logger.info(logStr(msg, params));
    }

    public static void error(String msg, Object... params) {
        logger.error(logStr(msg, params));
    }

    public static void warn(String msg, Object... params) {
        logger.warn(logStr(msg, params));
    }

    public static void debug(String msg, Object... params) {
        logger.debug(logStr(msg, params));
    }

    private static String logStr(String msg, Object... params) {
        StringBuilder sb = new StringBuilder();
        // time
        sb.append(DateUtil.formatDateTime(new Date()) + "--");
        sb.append(msg);
        if (params.length != 0) {
            sb.append("--");
        }
        for (Object item : params) {
            sb.append(item + "|");
        }
        return sb.toString();
    }

}
