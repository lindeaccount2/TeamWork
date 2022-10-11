package com.bjpowernode.mysql;

import java.sql.*;
import java.util.Random;

public class Selection {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/work?useSSL=false&serverTimezone=UTC";
  
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "123456";
    static int record[] = new int[6];
    static int first=1;
    
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
            int course=0;
            int vis[] = new int[91];		//下表即代表学号，范围[1,90],vis[]=1表示在本次抽点中已被抽点过
            
            while(course<5) {
            	con_mysql c=new con_mysql();
            	c.main(args);
                int count1=0;
                int count2=0;
                int count3=0;
                int shoot=0;
                ResultSet rs= null;
                if(first==1) {
                    //抽点 3.* 层次的学生
                    while(true) {
                    	b=r.nextInt(15)+1;	//随机范围[0,15)
                    	if(vis[b]==1) continue;
                    	vis[b]=1;
                    	sql = "select id,score,attendance from class1 where id="+b+" ";
                        rs = stmt.executeQuery(sql);	
                        if(rs.next()) {
                        	int id  = rs.getInt("id");
                            String s=rs.getString("score");
                            int attendance = rs.getInt("attendance");
                            count1++;
                            if(attendance==0) {
                            	shoot++;
                            	System.out.println("ID: " + id + ", 绩点:" + s + ", 出勤情况: " + attendance);
                            	break;
                            }
                        }
                    }
                    
                    //抽点 2.* 层次的学生
                    while(true) {
                    	if(count2==11) break;		
                    	b=r.nextInt(45)+16;
                    	if(vis[b]==1) continue;
                    	vis[b]=1;
                    	sql = "select id,score,attendance from class1 where id="+b+" ";
                        rs = stmt.executeQuery(sql);	
                        if(rs.next()) {
                        	int id  = rs.getInt("id");
                            String s=rs.getString("score");
                            int attendance = rs.getInt("attendance");
                            count2++;
                            if(attendance==0) {
                            	shoot++;
                            	 System.out.println("ID: " + id + ", 绩点:" + s + ", 出勤情况: " + attendance);
                            	 break;
                            }
                        }
                       
                    }
                    //抽点 1.* 层次的学生
                    while(true) {
                    	b=r.nextInt(30)+61;
                    	if(vis[b]==1) continue;

                    	vis[b]=1;
                    	sql = "select id,score,attendance from class1 where id="+b+" ";
                        rs = stmt.executeQuery(sql);	
                        if(rs.next()) {
                        	int id  = rs.getInt("id");
                            String s=rs.getString("score");
                            int attendance = rs.getInt("attendance");
                            count3++;
                            if(attendance==0) {
                            	shoot++;
                            	 System.out.println("ID: " + id + ", 绩点:" + s + ", 出勤情况: " + attendance);
                            	 break;
                            }
                        }
                    }
                    first=0;
                }
                else {
                	for(int i=0;i<6;i++) {
                		int a=record[i];
                		vis[a]=1;
                		sql = "select id,score,attendance from class1 where id="+a+" ";
                        rs = stmt.executeQuery(sql);	
                        if(rs.next()) {
                        	int id  = rs.getInt("id");
                            String s=rs.getString("score");
                            int attendance = rs.getInt("attendance");
                            if(record[i]<=15) count1++;
                            else if(record[i]<=60) count2++;
                            else count3++;
                            if(attendance==0) {
                            	shoot++;
                            	 System.out.println("ID: " + id + ", 绩点:" + s + ", 出勤情况: " + attendance);
                            }
                        }
                	}
                    //抽点 1.* 层次的学生
                    while(true) {
                    	if(count3>=5) break;
                    	b=r.nextInt(30)+61;
                    	if(vis[b]==1) continue;
                    	vis[b]=1;
                    	sql = "select id,score,attendance from class1 where id="+b+" ";
                        rs = stmt.executeQuery(sql);	
                        if(rs.next()) {
                        	int id  = rs.getInt("id");
                            String s=rs.getString("score");
                            int attendance = rs.getInt("attendance");
                            count3++;
                            if(attendance==0) {
                            	shoot++;
                            	 System.out.println("ID: " + id + ", 绩点:" + s + ", 出勤情况: " + attendance);
                            	 break;
                            }
                        }
                    }
                	
                }
                //在控制台输出结果
                System.out.println("count1="+ count1 + ", count2=" + count2 + ", count3=" + count3);
                System.out.println("E = " + (shoot*1.0)/(count1+count2+count3));
                System.out.println();
                
                rs.close();
                course++;
            }
            // 完成后关闭
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
    
    public static void recall(int[] arr) {
    	for(int i=0;i<6;i++) record[i]=arr[i];
    }
}
