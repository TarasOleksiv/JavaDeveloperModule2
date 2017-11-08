package ua.goit.java8.javadeveloper;

import ua.goit.java8.javadeveloper.dao.ConnectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by t.oleksiv on 08/11/2017.
 */
public class ConsoleApp {
    public static void main(String[] args) throws SQLException {
        String sql = "SHOW databases;";
        Connection connectServer = ConnectionUtil.getConnection();
        Statement statement = connectServer.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            System.out.println(resultSet.getString("Database"));
        }

        try {
            resultSet.close();
            statement.close();
            connectServer.close();
        } finally {
            if(statement !=null){
                statement.close();
            }
            if(connectServer!=null){
                connectServer.close();
            }
        }

    }

}
