package com.yym.springboot.common.util;


import org.apache.commons.lang3.StringUtils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * @author lizj on 2019/5/29 0029 上午 9:17
 */
public class IpUtils {

    private static String ip;

    public static String getIp() {
        if (StringUtils.isEmpty(ip)){
            ip = getHostIp();
        }
        return ip;
    }

    public static void setIp(String newIp) {
        LogUtil.info("本机的IP = " + newIp);
        ip = newIp;
    }

    private static String getHostIp() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress ip = (InetAddress) addresses.nextElement();
                    if (ip != null
                            && ip instanceof Inet4Address
                            && !ip.isLoopbackAddress()
                            && ip.getHostAddress().indexOf(":") == -1) {
                        LogUtil.info("生成IP = " + ip.getHostAddress());
                        return ip.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.error("获取ip地址异常：" + e.getMessage());
        }
        return null;
    }




}





