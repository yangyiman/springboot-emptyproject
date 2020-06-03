package com.yym.springboot.base.java.jdbc;

import java.sql.*;

/**
 * 模拟JDBC
 * 1. 获取驱动
 * 2. 获取连接
 * 3. 构建statement
 * 4. 设置sql并传入参数
 * 5. 执行sql,并获取结果
 * 6. 转换结果集并返回
 * 7. 释放相关资源
 */
public class MyJDBC {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        // 加载驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql:///sys?characterEncoding=utf8&useSSL=false&autoReconnect=true&failOverReadOnly=false&autoReconnectForPools=true&allowMultiQueries=true&serverTimezone=Hongkong";
            String password = "root";
            String username = "root";
            // 获取连接
            connection = DriverManager.getConnection(url, username, password);
            // 构建Statement
            statement = connection.createStatement();
            // 设置sql
            String sql = "select * from tb_class";
            // 执行sql,并获取结果集
            resultSet = statement.executeQuery(sql);
            // 转换结果集并输出
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String class_name = resultSet.getString("class_name");
                System.out.println("id为" + id + ",班名为" + class_name);
            }
        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (statement != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (resultSet != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
