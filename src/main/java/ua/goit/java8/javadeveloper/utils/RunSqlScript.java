package ua.goit.java8.javadeveloper.utils;

/**
 * Created by t.oleksiv on 08/11/2017.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.ibatis.common.jdbc.ScriptRunner;

/**
 *
 @author Dhinakaran Pragasam
 */
public class RunSqlScript {

    public static void run() throws ClassNotFoundException,
            SQLException {

        String aSQLScriptFilePath = "path/to/sql/script.sql";

        // Create MySql Connection
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/database", "username", "password");
        Statement stmt = null;

        try {
            // Initialize object for ScripRunner
            ScriptRunner sr = new ScriptRunner(con, false, false);

            // Give the input file to Reader
            Reader reader = new BufferedReader(
                    new FileReader(aSQLScriptFilePath));

            // Exctute script
            sr.runScript(reader);

        } catch (Exception e) {
            System.err.println("Failed to Execute" + aSQLScriptFilePath
                    + " The error is " + e.getMessage());
        }
    }
}