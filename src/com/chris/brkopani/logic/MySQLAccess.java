package com.chris.brkopani.logic;

import com.chris.brkopani.user.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author xristos
 */
public class MySQLAccess {

    static Connection conn = null;
    static Statement stmt = null;
// JDBC driver name and database URL

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://46.101.157.57/bikr?useUnicode=yes&characterEncoding=UTF-8";

    //  Database credentials
    static final String USER = "redone";
    static final String PASS = "7Zdd31s0";

    private static int incr = 0;
    private int inGame = 1;

    private static final String order_by = " ORDER BY EX_TIME";

    public static void connection() {

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
          /*  try {
             if (conn != null) {
             conn.close();
             }
             } catch (SQLException se) {
             se.printStackTrace();
             }//end finally try
             */
        }//end try
        //System.out.println("Goodbye!");
    }//end conn

    protected static String generateFakeID() {
        String fake_id = null;
        incr += 1;
        fake_id = User.getUser() + incr;
        return fake_id;
    }

    public static void insertTime(int number, long time, int ingame, int lap) {
        System.out.println("Inserting records into the table...");
        try {

            stmt = conn.createStatement();
            String values = String.format("VALUES ('%s','%s','%2d', '%2d','%2d','%2d')", generateFakeID(), User.getUser(), lap,
                    number, ingame, time);
            String sql = "INSERT INTO br_RACE (FAKE_ID,U_NAME,LAP,BR_NUMBER,IN_GAME,EX_TIME)" + values;
            stmt.executeUpdate(sql);

            System.out.println("Inserted records into the table...");
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAccess.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void querys(String name, String lastname, int age, int weight, String town) {
        System.out.println("Inserting records into the table...");
        try {

            stmt = conn.createStatement();
            String values = String.format("VALUES ('%s','%s', '%s', %2d, %2d,'%s')", User.getUser(), name, lastname, age, weight, town);
            String sql = "INSERT INTO br_bikers (U_NAME ,FNAME,LNAME,AGE,WEIGHT,TOWN)" + values;
            stmt.executeUpdate(sql);

            System.out.println("Inserted records into the table...");
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAccess.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void selectAll(DefaultTableModel model) throws SQLException {
        stmt = conn.createStatement();
        ResultSet rs = stmt
                .executeQuery("SELECT * FROM br_bikers");

        while (rs.next()) {

            String n = rs.getString("BR_NUMBER");
            String fn = rs.getString("FNAME");
            String ln = rs.getString("LNAME");
            String age = rs.getString("AGE");
            String w = rs.getString("WEIGHT");
            String town = rs.getString("TOWN");
            System.out.println(n + fn + ln + age + w + town);
            model.addRow(new Object[]{n, fn, ln, age, w, town});

        }
    }

    public static int ageCount(int age_value) throws SQLException {
        int count = 0;
        stmt = conn.createStatement();
        String query = "SELECT COUNT(BR_NUMBER) FROM RANK_VIEW";
        switch (age_value) {
            case 0:
                query = query + " WHERE AGE >5 AND AGE <= 20";
                break;
            case 1:
                query = query + " WHERE AGE >20 AND AGE <= 35";
                break;
            case 2:
                query = query + " WHERE AGE >35";
                break;
            case 3:
                query = query;
                break;
        }
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            count = rs.getInt(1);
        }
        System.out.println(rs);
        return count;
    }

    public static void rankSelect(DefaultTableModel model, int age_value) throws SQLException {
        stmt = conn.createStatement();
        String query = "SELECT * FROM RANK_VIEW";
        switch (age_value) {
            case 0:
                query = query + " WHERE AGE >5 AND AGE <= 20" + order_by;
                break;
            case 1:
                query = query + " WHERE AGE >20 AND AGE <= 35" + order_by;
                break;
            case 2:
                query = query + " WHERE AGE >35" + order_by;
                break;
            case 3:
                query = query + order_by;
                break;
        }
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {

            String n = rs.getString("BR_NUMBER");
            String fn = rs.getString("FNAME");
            String ln = rs.getString("LNAME");
            String age = rs.getString("AGE");
            String w = rs.getString("WEIGHT");
            String town = rs.getString("TOWN");
            String ex_time = rs.getString("EX_TIME");
            long time = Long.parseLong(ex_time);
            long minutes = TimeUnit.MILLISECONDS.toMinutes(time);
            String in_game = rs.getString("IN_GAME");
            String lap = rs.getString("LAP");
            System.out.println(n + fn + ln + age + w + town + minutes + in_game + lap);
            model.addRow(new Object[]{n, fn, ln, age, w, town, minutes});

        }

    }

}//end JDBCExample
