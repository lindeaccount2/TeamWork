package com.bjpowernode.mysql;

import java.sql.*;
import java.util.Random;

public class con_mysql {
	
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
            
            // 如果存在名为class1的表，进行删除
            sql = " drop table if exists class1";
            stmt.executeUpdate(sql);
            
            // 1.创建一个新表class1
            sql = "create table class1 (id INT(10) PRIMARY KEY AUTO_INCREMENT,score VARCHAR(5),attendance INT(10) )";
            stmt.executeUpdate(sql);
            
            // 2.进行class1表的插入(生成数据)
            int count=0;
            while(count<15) {
            	sql = "insert into class1(score,attendance) value('3.*',1)";
            	stmt.executeUpdate(sql);
            	count++;
            }
            while(count<60) {
            	sql = "insert into class1(score,attendance) value('2.*',1)";
            	stmt.executeUpdate(sql);
            	count++;
            }
            while(count<90) {
            	sql = "insert into class1(score,attendance) value('1.*',1)";
            	stmt.executeUpdate(sql);
            	count++;
            }
            
            // 3.修改class1表，录入“缺勤”的记录
            Random r = new Random();		//生成一个对象 r,利用随机数对象r来调用随机数函数
            
            int ab=1;
            int b;				//存放随机数
            while(ab<=1) {      //绩点大于3的同学在随机有1名同学缺勤
            	b= r.nextInt(15);   //调用 r 对象下面的nextInt，生成[0,10)之间的随机数，将结果传给 b
                sql = "update class1 set attendance=0 where id="+b+" ";
                stmt.executeUpdate(sql);
                ab++;
            }
            while(ab<=3) {      //绩点大于2的同学在随机有3名同学缺勤
            	b=r.nextInt(45)+15;
                sql = "update class1 set attendance=0 where id="+b+" ";
                stmt.executeUpdate(sql);
                ab++;
            }
            while(ab<=6) {      //绩点大于1的同学在随机有3名同学缺勤
            	b=r.nextInt(30)+60;
                sql = "update class1 set attendance=0 where id="+b+" ";
                stmt.executeUpdate(sql);
                ab++;
            }
            // 4.表Student的查询
            sql = "select id,score,attendance from class1 where attendance=0";
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
            
            // 将名单存入文件 ------“数据集”
            sql = " SELECT * FROM  work.class1  INTO  OUTFILE 'F:Vscode/github-Clone/TeamWork/data/class1/class1.txt' ";
            stmt.executeQuery(sql);
            
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

