package com.yym.springboot.base.java.file;

import org.junit.Test;

import java.io.*;

public class FileReaderAndWriterDemo {

    // 将hello.txt拷贝到hello_cy.txt文件中
    @Test
    public void test() throws Exception {
        Reader reader = new FileReader("hello.txt");
        Writer writer = new FileWriter(new File("hello_cy.txt"),true);
        char[] len = new char[1024*8];
        int a;
        while((a = reader.read(len)) != -1){
            writer.write(len,0,a);
        }
        writer.flush();
        reader.close();
        writer.close();
    }
}
