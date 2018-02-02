package com.barry.dao;

import java.sql.*;

/**
 * Created by Barry on 2018/1/25.
 */
public class DbConnect {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("java反射Class.forName异常");
        }
    }

    public static Connection getConnection() {
        Connection con = null;
        String url = "jdbc:mysql://127.0.0.1:3306/testdb1?useunicode=true&amp;characterencoding=utf-8&useSSL=false";
        String userName = "root";
        String pwd = "Aa295671947";
        try {
            con = DriverManager.getConnection(url, userName, pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void closeConnection(ResultSet rs, PreparedStatement ps, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
