package com.bjpowernode.mysql;

import java.sql.*;
import java.util.Random;

public class Selection {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/work?useSSL=false&serverTimezone=UTC";
 
 
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "123456";
 
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
        
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // 创建statement对象 
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();		//创建一个statement对象，用来封装sql语句传送给数据库
            String sql;
            
            //随机抽点
            Random r = new Random();
            int b;
            int count=0;
            ResultSet rs;
            
            while(true) {
            	b=r.nextInt(15);
            	sql = "select id,score,attendance from class1 where id="+b+" ";
                rs = stmt.executeQuery(sql);	
                if(rs.next()) {
                	int id  = rs.getInt("id");
                    String s=rs.getString("score");
                    int attendance = rs.getInt("attendance");
                    count++;
                    if(attendance==0) {
                    	 System.out.println("ID: " + id + ", 绩点:" + s + ", 出勤情况: " + attendance);
                    	 break;
                    }
                }
            }
            while(true) {
            	b=r.nextInt(45)+15;
            	sql = "select id,score,attendance from class1 where id="+b+" ";
                rs = stmt.executeQuery(sql);	
                if(rs.next()) {
                	int id  = rs.getInt("id");
                    String s=rs.getString("score");
                    int attendance = rs.getInt("attendance");
                    count++;
                    if(attendance==0) {
                    	 System.out.println("ID: " + id + ", 绩点:" + s + ", 出勤情况: " + attendance);
                    	 break;
                    }
                }
            }
            while(true) {
            	b=r.nextInt(30)+60;
            	sql = "select id,score,attendance from class1 where id="+b+" ";
                rs = stmt.executeQuery(sql);	
                if(rs.next()) {
                	int id  = rs.getInt("id");
                    String s=rs.getString("score");
                    int attendance = rs.getInt("attendance");
                    count++;
                    if(attendance==0) {
                    	 System.out.println("ID: " + id + ", 绩点:" + s + ", 出勤情况: " + attendance);
                    	 break;
                    }
                }
            }
            System.out.println("E = " + 3.0/count);
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }	

}

