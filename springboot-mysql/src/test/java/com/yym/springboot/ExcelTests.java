package com.yym.springboot;

import com.yym.springboot.common.util.DateUtil;
import com.yym.springboot.common.util.ExcelUtil;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelTests {

    @Test
    public void excelTest(){
        String filePath = "/excel1/hello1.xls";
        List<String> head = new ArrayList<>();
        head.add("id");
        head.add("name");
        head.add("age");
        List<List<Object>> data = new ArrayList<>();
        List<Object> row1 = new ArrayList<>();
        row1.add("1");
        row1.add("张三");
        row1.add(21);
        data.add(row1);
        List<Object> row2 = new ArrayList<>();
        row2.add("2");
        row2.add("李四");
        row2.add(22);
        data.add(row2);
        ExcelUtil.writeBySimple(filePath,data,head);
    }

    /**
     * 要求: 以年/月/文件名
     */
    @Test
    public void testDir(){
        String timeStr = DateUtil.formatDateTimeDD(new Date());
        String year = timeStr.substring(0, 4);
        String day = timeStr.substring(4);
        String fileName = "123.xls";
        String filePath = "/"+year+"/"+day+"/"+fileName;

        // 写到本地
        List<String> head = new ArrayList<>();
        head.add("id");
        head.add("name");
        head.add("age");
        List<List<Object>> data = new ArrayList<>();
        List<Object> row1 = new ArrayList<>();
        row1.add("1");
        row1.add("张三");
        row1.add(21);
        data.add(row1);
        List<Object> row2 = new ArrayList<>();
        row2.add("2");
        row2.add("李四");
        row2.add(22);
        data.add(row2);

        ExcelUtil.writeBySimple(filePath,data,head);

        System.out.println("表格已经生成,放在了 : " + filePath);
    }
}
