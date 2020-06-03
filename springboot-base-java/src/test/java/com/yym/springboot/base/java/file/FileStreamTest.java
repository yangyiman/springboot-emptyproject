package com.yym.springboot.base.java.file;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 *文件字节流
 */
public class FileStreamTest {
    @Test
    public void test() throws Exception {
        // 这是目标
        File file = new File("123.txt");
        // 这是水管,覆盖
        //OutputStream fileOutputStream = new FileOutputStream(file);
        // 追加
        OutputStream fileOutputStream = new FileOutputStream(file,true);
        // 这是源,一次写一个字节 -- AA
        fileOutputStream.write(65);
        // 一次写一个数组 -- AAabc
        fileOutputStream.write("abc".getBytes());
        // 从指定的字节数组中,指定写入的索引和长度,下例会将cd写入 -- AAabccd
        fileOutputStream.write("abcdef".getBytes(),2,2);
        fileOutputStream.close();
    }
}
