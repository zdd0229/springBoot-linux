package com.z.core.connectionpool.test;

import com.z.util.RunTime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UseZpool {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(
                    () -> {
                        RunTime runTime = new RunTime();
                        runTime.start();
                        for (int j = 0; j < 10; j++) {
                            try {
                                Connection connection = MyZpool.getConnection();

                                PreparedStatement statement = null;
                                statement = connection.prepareStatement("select * from student where name = ?");
                                statement.setObject(1, "赵丁丁");

                                ResultSet resultSet = statement.executeQuery();

                                int column = resultSet.getMetaData().getColumnCount();

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.print(Thread.currentThread().getName() + ":::");
                        runTime.print();
                    }
            ).start();
        }
    }

}
