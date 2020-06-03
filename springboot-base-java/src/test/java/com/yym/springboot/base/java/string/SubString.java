package com.yym.springboot.base.java.string;


import org.junit.Test;

public class SubString {
    final String str = "ftp://hbgd:hbgd@172.25.7.6:21//opt/wacos/CTMSData/dispatch/smg_dispatch/2018/12/29/16/324516483/CJY_smp_6689205-54304744-1-16.xml";
    @Test
    public void test1(){
        java.lang.String username=str.substring(str.indexOf("//")+2,str.indexOf(":",4));
        System.out.println("username = " + username);
        //密码 -- hbgd
        java.lang.String password=str.substring(str.indexOf(":",4)+1,str.indexOf("@"));
        System.out.println("password = " + password);
        //地址 -- 172.25.7.6
        java.lang.String host=str.substring(str.indexOf("@")+1,str.indexOf(":",str.indexOf("@")));
        System.out.println("host = " + host);
        //端口 -- 21
        java.lang.String port=str.substring(str.indexOf(":",str.indexOf("@"))+1,str.indexOf("//",str.indexOf("@")));
        System.out.println("port = " + port);
        //ftp文件路径 -- /opt/wacos/CTMSData/dispatch/smg_dispatch/2018/12/29/16/324516483/
        java.lang.String remotePath=str.substring(str.indexOf("//",str.indexOf("@"))+1,str.lastIndexOf("/")+1);
        System.out.println("remotePath = " + remotePath);
        //文件名 -- CJY_smp_6689205-54304744-1-16.xml
        java.lang.String fileName=str.substring(str.lastIndexOf("/")+1);
        System.out.println("fileName = " + fileName);
        // /opt/wacos/CTMSData/dispatch/response/2018/12/29/16/324516483/CJY_smp_6689205-54304744-1-16.xml
        String response=remotePath.replace("smg_dispatch","response")+fileName;
        System.out.println("response = " + response);


    }    
}
