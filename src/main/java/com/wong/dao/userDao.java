package com.wong.dao;

import com.wong.entity.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class userDao {
    //查询全部用户
    public List<Users> getAll() throws SQLException {
        List<Users> list = new ArrayList<Users>();
        Connection con = DBUtil.getConn();
        String sql = "SELECT * FROM USERS";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Users users = new Users();
            users.setUid(rs.getInt(1));
            users.setUname(rs.getString(2));
            users.setUpasswd(rs.getString(3));
            users.setUnickname(rs.getString(4));
            users.setUdate(rs.getDate(5));
            list.add(users);
        }
        return list;
    }

    //添加用户
    public void addUser(Users user) {
        Connection connection = DBUtil.getConn();
        String sql = "INSERT INTO USERS (uname, upassword, unickname, udate) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, user.getUname());
            ps.setString(2, user.getUpasswd());
            ps.setString(3, user.getUnickname());
            ps.setDate(4, user.getUdate());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(connection, ps);
        }

    }

    //删除用户信息
    public void delUser(int id) {
        Connection connection = DBUtil.getConn();
        String sql = "DELETE FROM USERS WHERE uid = ?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(connection, ps);
        }
    }

    //修改用户信息
    public void alterUser(Users user) {
        Connection connection = DBUtil.getConn();
        String sql = "UPDATE USERS SET uname = ?, upassword = ?, unickname = ?, udate = ? WHERE uid =?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, user.getUname());
            ps.setString(2, user.getUpasswd());
            ps.setString(3, user.getUnickname());
            ps.setDate(4, user.getUdate());
            ps.setInt(5, user.getUid());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(connection, ps);
        }
    }

    //按用户name查找用户信息
    public Users getUserByName(String name) {
        Users users = new Users();
        Connection connection = DBUtil.getConn();
        String sql = "SELECT * FROM USERS WHERE uname = ?";
        PreparedStatement ps = null;
        ResultSet rs;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()){
                users.setUid(rs.getInt(1));
                users.setUname(rs.getString(2));
                users.setUpasswd(rs.getString(3));
                users.setUnickname(rs.getString(4));
                users.setUdate(rs.getDate(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(connection, ps);
        }

        return users;
    }
}

