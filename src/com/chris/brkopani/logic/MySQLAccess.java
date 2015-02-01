/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chris.brkopani.logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xristos
 */
public class MySQLAccess {

    static Connection conn = null;
    static Statement stmt = null;
// JDBC driver name and database URL

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/bikr";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

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

    public static void querys(String name, String lastname, int age, int weight, String town) {
        System.out.println("Inserting records into the table...");
        try {
            stmt = conn.createStatement();
            String values = String.format("VALUES ('%s', '%s', %2d, %2d,'%s',300)", name, lastname, age, weight, town);
            String sql = "INSERT INTO bikr_bikers " + values;
            stmt.executeUpdate(sql);

            System.out.println("Inserted records into the table...");
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAccess.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}//end JDBCExample
