package com.yym.springboot;

import org.junit.Before;
import org.junit.Test;
import sun.security.util.Password;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试mycat实现读写分离
 */
public class MycatTest {
    Connection connection = null;

    @Before
    public void test() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://192.168.3.33:8066/mycat_table?serverTimeZone=HongKong";
        String password = "root";
        String user = "root";
        connection = DriverManager.getConnection(url, user, password);
    }

    @Test
    public void test2() throws Exception {
        String sql = "select * from tb_persion where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,1);
        ResultSet resultSet = statement.executeQuery();
        Map<String, Object> result = new HashMap<>();
        while (resultSet.next()){
            result.put("id",resultSet.getInt("id"));
            result.put("name",resultSet.getString("name"));
        }
        System.out.println("result = " + result);
    }
}
