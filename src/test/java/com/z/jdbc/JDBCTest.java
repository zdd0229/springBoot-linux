package com.z.jdbc;

import com.z.util.RunTime;

import java.sql.*;

public class JDBCTest {

    public static void main(String[] args) throws SQLException, InterruptedException {

        String url = "jdbc:mysql://101.132.128.127:3307/test?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
        String username = "root";
        String password = "root";

        JdbcPool.init(5);


        for (int i = 0; i < 1; i++) {

//            Connection connection = DriverManager.getConnection(url, username, password);
            Connection connection = JdbcPool.getConnection();

            PreparedStatement statement = connection.prepareStatement("select * from student where name = ?");
            statement.setObject(1, "赵丁丁");

            ResultSet resultSet = statement.executeQuery();

            int column = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {
                for (int j = 1; j <= column; j++) {
                    System.out.print(resultSet.getString(j) + " ");
                }
                System.out.println();
            }

//            connection.close();
        }

        JdbcPool.destroy();

    }

}
