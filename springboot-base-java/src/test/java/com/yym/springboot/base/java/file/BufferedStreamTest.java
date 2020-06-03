package com.yym.springboot.base.java.file;

import org.junit.Test;

import java.io.*;

/**
 * 测试缓冲流
 */
public class BufferedStreamTest {

    // 字节缓冲流
    @Test
    public void test1() throws Exception {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("123_cy.txt"));
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("123.txt"));
        byte[] len = new byte[1024];
        int a = -1;
        while ((a = bis.read(len)) != -1){
            bos.write(len,0,a);
        }
        bos.close();
        bis.close();
    }

    // 字符缓冲流,多了一个raadline
    @Test
    public void teset() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("hello.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("hello_cy1.txt"));
        String str = null;
        while((str = br.readLine()) != null){
            bw.write(str,0,str.length());
        }
        br.close();
        bw.close();
    }
}
