package com.bjpowernode.mysql;

import java.sql.*;

public class Selection {
	//这里是MySQLDemo 类
	/*
	*java连接mysql数据库
	*1、加载驱动程序
	*2、数据库连接字符串"jdbc:mysql://localhost:3306/数据库名?"
	*3、数据库登录名
	*3、数据库登录密码
	*/	
				
	// MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
	//static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	// static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB";
 
    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
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
            
            sql = " SELECT * FROM  work.class1  INTO  OUTFILE 'F:mysql/class1.txt' ";
            stmt.executeQuery(sql);
            
            /*
            // 4.表Student的查询
            sql = "select id,score,attendance from class1";
            ResultSet rs = stmt.executeQuery(sql);	//executeQuery()把数据库响应的查询结果存放在ResultSet类对象中供我们使用
        
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("id");
                String s=rs.getString("score");
                int attendance = rs.getInt("attendance");
    
                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", 绩点:" + s);
                System.out.print(", 出勤情况: " + attendance);
                System.out.print("\n");
            }
            
            // 完成后关闭
            rs.close();*/
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

