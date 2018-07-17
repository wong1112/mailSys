package com.wong.dao;

import com.wong.entity.Mails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class mailDao {
    //查找全部邮件
    public List<Mails> getAll() throws SQLException {
        List<Mails> list = new ArrayList<Mails>();
        Connection con = DBUtil.getConn();
        String sql = "SELECT * FROM MAILS";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Mails mails = new Mails();
            mails.setMid(rs.getInt(1));
            mails.setMsender(rs.getString(2));
            mails.setMaccepter(rs.getString(3));
            mails.setMtitle(rs.getString(4));
            mails.setMcontent(rs.getString(5));
            mails.setMdate(rs.getDate(6));
            mails.setMisread(rs.getBoolean(7));
            list.add(mails);
        }
        return list;
    }

    //添加邮件
    public void addMails(Mails mails) {
        Connection connection = DBUtil.getConn();
        String sql = "INSERT INTO MAILS (msender, maccepter, mtitle, mcontent, mdate, misread) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, mails.getMsender());
            ps.setString(2, mails.getMaccepter());
            ps.setString(3, mails.getMtitle());
            ps.setString(4, mails.getMcontent());
            ps.setDate(5, mails.getMdate());
            ps.setBoolean(6, mails.getMisread());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(connection, ps);
        }

    }

    //删除邮件
    public void delMails(int mid) {
        Connection connection = DBUtil.getConn();
        String sql = "DELETE FROM MAILS WHERE mid = ?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, mid);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(connection, ps);
        }
    }

    //修改邮件
    public void alterMails(Mails mails) {
        Connection connection = DBUtil.getConn();
        String sql = "UPDATE MAILS SET msender = ?, maccepter = ?, mtitle = ?, mcontent = ?, mdate = ?, misread = ? WHERE mid = ?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, mails.getMsender());
            ps.setString(2, mails.getMaccepter());
            ps.setString(3, mails.getMtitle());
            ps.setString(4, mails.getMcontent());
            ps.setDate(5, mails.getMdate());
            ps.setBoolean(6, mails.getMisread());
            ps.setInt(7, mails.getMid());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(connection, ps);
        }
    }

    //按Sender查找邮件
    public List<Mails> getMailsBySender(String sender) {
        List<Mails> list = new ArrayList<Mails>();
        Connection connection = DBUtil.getConn();
        String sql = "SELECT * FROM MAILS WHERE msender = ?";
        PreparedStatement ps = null;
        ResultSet rs;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, sender);
            rs = ps.executeQuery();
            while (rs .next()){
                Mails mails = new Mails();
                mails.setMid(rs.getInt(1));
                mails.setMsender(rs.getString(2));
                mails.setMaccepter(rs.getString(3));
                mails.setMtitle(rs.getString(4));
                mails.setMcontent(rs.getString(5));
                mails.setMdate(rs.getDate(6));
                mails.setMisread(rs.getBoolean(7));
                list.add(mails);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(connection, ps);
        }

        return list;
    }

    //按Accepter查找邮件
    public List<Mails> getMailsByAccepter(String accepter) {
        List<Mails> list = new ArrayList<Mails>();
        Connection connection = DBUtil.getConn();
        String sql = "SELECT * FROM MAILS WHERE maccepter = ?";
        PreparedStatement ps = null;
        ResultSet rs;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, accepter);
            rs = ps.executeQuery();
            while (rs .next()){
                Mails mails = new Mails();
                mails.setMid(rs.getInt(1));
                mails.setMsender(rs.getString(2));
                mails.setMaccepter(rs.getString(3));
                mails.setMtitle(rs.getString(4));
                mails.setMcontent(rs.getString(5));
                mails.setMdate(rs.getDate(6));
                mails.setMisread(rs.getBoolean(7));
                list.add(mails);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(connection, ps);
        }

        return list;
    }
    //按Id查找邮件
    public Mails getMailsById(int id) {
        Mails mails = new Mails();
        Connection connection = DBUtil.getConn();
        String sql = "SELECT * FROM MAILS WHERE mid = ?";
        PreparedStatement ps = null;
        ResultSet rs;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs .next()){
                mails.setMid(rs.getInt(1));
                mails.setMsender(rs.getString(2));
                mails.setMaccepter(rs.getString(3));
                mails.setMtitle(rs.getString(4));
                mails.setMcontent(rs.getString(5));
                mails.setMdate(rs.getDate(6));
                mails.setMisread(rs.getBoolean(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(connection, ps);
        }

        return mails;
    }
}
