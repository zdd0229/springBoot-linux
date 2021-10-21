package com.z.core.jdbc.HikariCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HikariCPTest {
    public static void main(String[] args) throws SQLException {

        Connection connection = HikariCPConfig.getConnection();

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

    }
}
