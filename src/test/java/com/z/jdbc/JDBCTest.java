package com.z.jdbc;

import java.sql.*;

public class JDBCTest {

    public static void main(String[] args) throws SQLException {

        String url = "jdbc:mysql://localhost:3306/testxx?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
        String username="root";
        String password="root";

        Connection connection = DriverManager.getConnection(url,username,password);

        PreparedStatement statement = connection.prepareStatement("select * from student where name = ?");
        statement.setObject(1,"赵丁丁");

        ResultSet resultSet = statement.executeQuery();

        int column = resultSet.getMetaData().getColumnCount();

        while (resultSet.next()){
            for (int i = 1; i <= column; i++) {
                System.out.print(resultSet.getString(i)+" ");
            }
            System.out.println();
        }

    }

}
