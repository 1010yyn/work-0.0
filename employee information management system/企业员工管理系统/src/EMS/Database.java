//数据库操作
package EMS;

import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database
{
	private final static String ID = "root";
	private final static String PWD = "root";
	private final static String URL = "jdbc:mysql://localhost:3306/ems";
	public static ResultSet rs;
	public static Statement stmt;
	public static Connection connect;
	public static String dbms = "ems";

	public static void main(String args[])
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver"); // 加载MYSQL JDBC驱动程序
			System.out.println("Success loading Mysql Driver!\n");
		} catch (Exception e)
		{
			System.out.print("Error loading Mysql Driver!\n");
			e.printStackTrace();// 抛出异常
		}
		try
		{
			connect = DriverManager.getConnection(URL, ID, PWD);// 尝试链接数据库
			System.out.println("Success connect Mysql server!");
			stmt = connect.createStatement();
		} catch (Exception e)
		{
			try
			{// 如果连接数据库失败，新建数据库
				connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", ID, PWD);// 先链接到一个已有的数据库
				// 连接URL为 jdbc:mysql//服务器地址/数据库名
				System.out.println("Success connect Mysql server!");
				stmt = connect.createStatement();
				stmt.executeUpdate("create database EMS");// 创建数据库EMS
				stmt.close();// 关闭数据库
				connect.close();// 关闭连接
				connect = DriverManager.getConnection(URL, ID, PWD);// 重新连接到新建的数据库
				stmt = connect.createStatement();
				stmt.executeUpdate("create table Admin"// 管理员信息
						+ "(User_ID int not null primary key,"// 管理员编号
						+ "User_Name varchar(20) not null,"// 管理员姓名
						+ "Password varchar(20) not null);");// 管理员密码
				stmt.executeUpdate("insert into Admin values(1030415201,'Admin1','123456')");// 默认管理员信息1
				stmt.executeUpdate("insert into Admin values(1030415202,'Admin2','123456')");// 默认管理员信息2
				stmt.executeUpdate("insert into Admin values(1030415203,'Admin3','123456')");// 默认管理员信息3
				stmt.executeUpdate("create table RAP"// 奖惩信息
						+ "(ID varchar(20) not null primary key,"// 顺序号
						+ "R_Number int(20) not null,"// 奖惩编号
						+ "E_Number int(20) not null,"// 员工编号
						+ "R_Date datetime not null,"// 奖惩时间
						+ "R_Address varchar(50) not null,"// 奖惩地点
						+ "R_Causation varchar(200) not null,"// 奖惩原因
						+ "R_Remark varchar(500));");// 备注
				stmt.executeUpdate("create table Training"// 培训信息
						+ "(ID int not null primary key,"// 顺序号
						+ "T_Number varchar(20) not null,"// 培训编号
						+ "T_Content varchar(100) not null,"// 培训内容
						+ "E_Number int not null,"// 员工编号
						+ "T_Date int,"// 培训天数
						+ "T_Money int);");// 培训费用
				stmt.executeUpdate("create table Wage"// 薪资信息
						+ "(ID int not null primary key,"// 顺序号
						+ "W_Number int not null,"// 薪资编号
						+ "E_Number int not null,"// 员工编号
						+ "W_BasicWage decimal(18,2) not null,"// 基本工资
						+ "W_Boon decimal(18,2) not null,"// 福利
						+ "W_Bonus decimal(18,2) not null,"// 奖金
						+ "W_FactWage decimal(18,2) not null);");// 总薪资
				stmt.executeUpdate("create table Employee" + "(E_Number int not null primary key,"// 员工编号
						+ "E_Name varchar(30) not null,"// 姓名
						+ "E_Sex varchar(2) not null,"// 性别
						+ "E_BornDate datetime not null,"// 出生日期
						+ "E_Marriage varchar(4) not null,"// 婚姻
						+ "E_PoliticsVisage varchar(20) not null,"// 政治状况
						+ "E_SchoolAge varchar(20),"// 学历
						+ "E_EnterDate datetime,"// 进厂日期
						+ "E_InDueFormDate datetime not null,"// 转正日期
						+ "D_Name int not null,"// 部门编号
						+ "E_Headship varchar(20) not null,"// 职务
						+ "E_Estate varchar(10) not null,"// 在职否
						+ "E_Remark varchar(500));");// 备注
				stmt.executeUpdate("create table Department"// 部门信息
						+ "(D_Number int not null primary key,"// 编号
						+ "D_Name varchar(20) not null,"// 名称
						+ "D_Count int not null);");// 人数
				stmt.close();// 关闭数据库
				connect.close();// 关闭连接
			} catch (Exception e1)
			{
				System.out.print("get data error!");
				e1.printStackTrace();
			}
		}
	}

	public static boolean joinDB()
	{
		boolean flag = true;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e)
		{
			System.out.print("Error loading Mysql Driver!");
			return false;
		}
		try
		{
			connect = DriverManager.getConnection(URL, ID, PWD);// 链接数据库
			stmt = connect.createStatement();
		} catch (Exception e)
		{
			return false;
		}
		return true;
	}

	public static boolean query(String sqlString)
	{
		try
		{
			rs = stmt.executeQuery(sqlString);
		} catch (SQLException e)
		{
			return false;
		}
		return true;
	}

	public static boolean update(String sqlString)
	{
		try
		{
			stmt.executeUpdate(sqlString);
		} catch (SQLException e)
		{
			return false;
		}
		return true;
	}
	public static void close()
	{
		try
		{
			stmt.close();// 关闭数据库
			connect.close();// 关闭连接
		} catch (SQLException e)
		{
		}
	}
}