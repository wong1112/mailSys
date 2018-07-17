package com.wong.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class DBUtil {
    //获取连接
    static Connection getConn() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");//加载驱动
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mailSys?useSSL=false", "root", "");//建立连接
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return con;
    }

    //关闭全部
    static void closeAll(Connection con, PreparedStatement ps) {
        try {

            if (con != null)
                con.close();
            if (ps != null)
                ps.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
