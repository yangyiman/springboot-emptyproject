package com.yym.springboot.base.java.file;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 测试file类
 */
public class FileTest {

    /**
     * 分隔符
     */
    @Test
    public void test1() {
        // 路径分隔符,一般win为;,其他为:
        System.out.println("File.pathSeparator = " + File.pathSeparator);
        System.out.println("File.pathSeparatorChar = " + File.pathSeparatorChar);
        // 路径占位符,一般win为\\,也可为/,其他为/
        System.out.println("File.separator = " + File.separator);
        System.out.println("File.separatorChar = " + File.separatorChar);
    }

    /**
     * FILE,属性判断
     */
    @Test
    public void test2() {
        File file = new File("D:/data/123.txt");
        if (file.isFile()) {
            System.out.println("file.length() = " + file.length());
        }
        // 是否隐藏
        System.out.println("file.isHidden() = " + file.isHidden());
        // 是否可读
        System.out.println("file.canRead() = " + file.canRead());
        // 是否可写
        System.out.println("file.canWrite() = " + file.canWrite());
        // 是否可执行
        System.out.println("file.canExecute() = " + file.canExecute());
    }

    /**
     * 相对路径为当前项目的路径 D:\Idea\IdeaProjects\springboot-empty-project\springboot-base-java\123.txt
     *
     * @throws IOException
     */
    @Test
    public void test3() throws IOException {
        File file = new File("123.txt"); //D:\Idea\IdeaProjects\springboot-empty-project\springboot-base-java\123.txt
        System.out.println("file.getAbsolutePath() = " + file.getAbsolutePath()); // D:\Idea\IdeaProjects\springboot-empty-project\springboot-base-java\123.txt
        System.out.println("file.getCanonicalPath() = " + file.getCanonicalPath());
        System.out.println("file.getPath() = " + file.getPath()); // 123.txt
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    // 列出目录1>目录2>文件这样的结构
    //logs\zodiac\activity-vote\2019-12-04.log
    @Test
    public void test4() {
        final String path = "D:\\data\\logs\\zodiac\\activity-vote\\2019-12-04.log";
        File file = new File(path);
        List<String> list = new ArrayList<>();
        getParentName(file, list);
        // 将集合倒置
        Collections.reverse(list);
        // 拼接集合
        String collect = list.stream().filter(s -> !s.equals("")).collect(Collectors.joining(">"));
        System.out.println("collect = " + collect);
    }

    private void getParentName(File file, List<String> list) {
        File parentFile = file.getParentFile();
        if (parentFile != null) {
            list.add(parentFile.getName());
            getParentName(parentFile, list);
        }
    }

    @Test
    public void test33(){
        String file = "D:\\opt\\wacos\\CTMSData\\dispatch\\response\\2019\\11\\28\\5\\1116588053\\XNPD_smp_8899583-83709052-1-5.xml";
        File f = new File(file);
        System.out.println("f = " + f.exists());
        System.out.println("删除 = " + new File(file).delete());
    }

    /**
     * username = wacos
     * password = wacos
     * host = 172.19.64.12
     * port = 21
     * remotePath = /opt/wacos/CTMSData/picture/2020/01/06/
     * fileName = 20200106161035_2410717.jpg
     * -------------------------------------------------
     * username = wacos
     * password = wacos
     * host = 172.19.64.12
     * port = 21
     * remotePath = ftp://wacos:wacos@172.19.64.12:21/opt/wacos/CTMSData/picture/2020/01/06/
     * fileName = 20200106161035_2410717.jpg
     */
    @Test
    public void test55(){
        // 一个斜杠
        String ftpUrl = "ftp://wacos:wacos@172.19.64.12:21//opt/wacos/CTMSData/picture/2020/01/06/20200106161035_2410717.jpg";
        // 两个斜杠
        String ftpUrl1 = "ftp://wacos:wacos@172.19.64.12:21/opt/wacos/CTMSData/picture/2020/01/06/20200106161035_2410717.jpg";
        //用户名  -- hbgd
        String username = ftpUrl.substring(ftpUrl.indexOf("//") + 2, ftpUrl.indexOf(":", 4));
        System.out.println("username = " + username);
        //密码 -- hbgd
        String password = ftpUrl.substring(ftpUrl.indexOf(":", 4) + 1, ftpUrl.indexOf("@"));
        System.out.println("password = " + password);
        //地址 -- 172.25.7.6
        String host = ftpUrl.substring(ftpUrl.indexOf("@") + 1, ftpUrl.indexOf(":", ftpUrl.indexOf("@")));
        System.out.println("host = " + host);
        //端口 -- 21
        //String port = ftpUrl.substring(ftpUrl.indexOf(":", ftpUrl.indexOf("@")) + 1, ftpUrl.indexOf("//", ftpUrl.indexOf("@")));
        String port = ftpUrl.substring(ftpUrl.indexOf(":", ftpUrl.indexOf("@")) + 1, ftpUrl.indexOf("/", ftpUrl.indexOf("@")));

        System.out.println("port = " + port);
        //ftp文件路径 -- /opt/wacos/CTMSData/dispatch/smg_dispatch/2018/12/29/16/324516483/
        String remotePath = ftpUrl.substring(ftpUrl.indexOf("/", ftpUrl.indexOf("@")), ftpUrl.lastIndexOf("/") + 1);
        System.out.println("row ===== remotePath = " + remotePath);
        if(remotePath.startsWith("//")){
            remotePath = remotePath.substring(1);
        }
        System.out.println("remotePath = " + remotePath);
        //文件名 -- CJY_smp_6689205-54304744-1-16.xml
        String fileName = ftpUrl.substring(ftpUrl.lastIndexOf("/") + 1);
        System.out.println("fileName = " + fileName);
    }

    // 测试绝对路径
    @Test
    public void test11() throws Exception {
        /*File file = new File("C:/lottery1/excel");
        if(! file.exists()){
            file.mkdir();
        }*/
        File file = new File("/a/b");
        if(!file.exists()){
            file.mkdirs();
        }
        System.out.println("file.getPath() = " + file.getPath());
        String filePath = file.getPath()+File.separator+"abc.txt";
        System.out.println("filePath = " + filePath);
        OutputStream os = new FileOutputStream(filePath);
        os.write("hello".getBytes());
        os.close();
    }

    @Test
    public void test22(){
        String path = "/c/d";
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
    }


}
