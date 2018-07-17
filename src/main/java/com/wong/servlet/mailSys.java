package com.wong.servlet;

import com.wong.dao.mailDao;
import com.wong.dao.userDao;
import com.wong.entity.Mails;
import com.wong.entity.Users;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class mailSys {


    private static void Display(String str) throws SQLException {
        boolean k = true;
        while (k) {
            System.out.println("1.查看接收的邮件\t2.查看发送的邮件\t3.阅读邮件\t4.发送邮件\t5.回复邮件\t6.删除邮件\t7.查看所有用户\t\t0.退出登录");
            Scanner scanner = new Scanner(System.in);
            int i = scanner.nextInt();
            switch (i) {
                case 1:
                    viewMailAccept(str);
                    break;
                case 2:
                    viewMailSend(str);
                    break;
                case 3:
                    readMail();
                    break;
                case 4:
                    sendMail(str);
                    break;
                case 5:
                    reviewMail(str);
                    break;
                case 6:
                    delMail();
                    break;
                case 7:
                    showAllUsers();
                    break;
                case 0:
                    k = false;
                    break;
            }
        }
    }

    private static void viewMailAccept(String str) {
        mailDao mailDao = new mailDao();
        List<Mails> list = mailDao.getMailsByAccepter(str);
        System.out.println("\t邮件Id\t发送者\t邮件标题\t邮件发送日期\t是否已读");
        for (Mails aList : list) {
            System.out.println("\t" + aList.getMid() +
                    "\t\t" + aList.getMsender() +
                    "\t\t" + aList.getMtitle() +
                    "\t" + aList.getMdate() +
                    "\t" + aList.getMisread());
        }
    }

    private static void viewMailSend(String str) {
        mailDao mailDao = new mailDao();
        List<Mails> list = mailDao.getMailsBySender(str);
        System.out.println("\t邮件Id\t接受者\t邮件标题\t邮件发送日期\t是否已读");
        for (Mails aList : list) {
            System.out.println("\t" + aList.getMid() +
                    "\t\t" + aList.getMaccepter() +
                    "\t\t" + aList.getMtitle() +
                    "\t" + aList.getMdate() +
                    "\t" + aList.getMisread());
        }
    }

    private static void readMail() {
        mailDao mailDao = new mailDao();
        System.out.println("选择要查看的邮件ID:");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        String content = mailDao.getMailsById(i).getMcontent();
        mailDao.getMailsById(i).setMisread(true);
        mailDao.alterMails(mailDao.getMailsById(i));
        System.out.println("邮件内容为");
        System.out.println(content);
    }

    private static void sendMail(String str) {
        mailDao mailDao = new mailDao();
        Mails mails = new Mails();
        Scanner scanner = new Scanner(System.in);
        mails.setMsender(str);
        System.out.println("请输入邮件的接受者");
        mails.setMaccepter(scanner.next());
        System.out.println("请输入文件标题");
        mails.setMtitle(scanner.next());
        System.out.println("请输入文件内容");
        mails.setMcontent(scanner.next());
        mails.setMdate();
        mails.setMisread(false);

        mailDao.addMails(mails);
    }

    private static void reviewMail(String str) {
        mailDao mailDao = new mailDao();
        Mails mails = new Mails();
        if (mailDao.getMailsByAccepter(str) != null) {
            System.out.println("选择要回复的邮件ID:");
            Scanner scanner = new Scanner(System.in);
            int i = scanner.nextInt();
            mails.setMsender(str);
            mails.setMaccepter(mailDao.getMailsById(i).getMsender());
            System.out.println("请输入文件标题");
            mails.setMtitle(scanner.next());
            System.out.println("请输入文件内容");
            mails.setMcontent(scanner.next());
            mails.setMdate();
            mails.setMisread(false);

            mailDao.addMails(mails);
        }
        else {
            System.out.println("没有可回复的邮件");
        }

    }

    private static void delMail() {
        mailDao mailDao = new mailDao();
        System.out.println("选择要删除的邮件ID:");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        mailDao.delMails(i);
    }

    private static void showAllUsers() throws SQLException {
        userDao userDao = new userDao();
        List<Users> list = userDao.getAll();
        System.out.println("\t用户名\t昵称");
        for (Users aList : list) {
            System.out.println("\t" + aList.getUname() +
                    "\t\t" + aList.getUnickname());
        }
    }

    public static void main(String[] args) throws SQLException {
        userDao userDao = new userDao();
        while (true) {
            System.out.println("1.登录\t2.注册\t3.退出");
            Scanner scanner = new Scanner(System.in);
            int i = scanner.nextInt();
            switch (i) {
                case 1:
                    System.out.println("请输入用户名:");
                    String str = scanner.next();
                    System.out.println("请输入密码:");
                    String str1 = scanner.next();
                    if (userDao.getUserByName(str).getUname() == null) {
                        System.out.println("查无此人,请先注册");
                    } else {
                        if (!userDao.getUserByName(str).getUpasswd().equals(str1)) {
                            System.out.println("密码不正确!");
                        } else {
                            Display(userDao.getUserByName(str).getUname());
                        }
                    }
                    break;
                case 2:
                    System.out.println("请输入用户名");
                    Users users = new Users();
                    users.setUname(scanner.next());
                    System.out.println("请输入密码");
                    users.setUpasswd(scanner.next());
                    System.out.println("请输入昵称");
                    users.setUnickname(scanner.next());
                    users.setUdate(new Date());
                    userDao.addUser(users);
                    break;
                case 3:
                    System.exit(0);
            }
        }
    }
}
